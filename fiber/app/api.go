package app

import (
	"strconv"

	"github.com/gofiber/fiber"
	"github.com/jinzhu/gorm"
)

// Handler is the base for the application
type Handler struct {
	DB *gorm.DB
}

// GetApplication returns an Application object
func (h *Handler) GetApplication(c *fiber.Ctx) {
	appID, err := strconv.Atoi(c.Params("app_id"))
	if err != nil {
		c.Status(fiber.StatusInternalServerError).SendString("invalid app_id")
		return
	}

	app := findByID(h.DB, appID)
	if app == nil {
		c.Status(fiber.StatusNotFound).SendString("app not found!")
		return
	}

	c.Status(fiber.StatusOK).JSON(app)
}

// StoreApplication creates ou updates the Application with the object received on the body
func (h *Handler) StoreApplication(c *fiber.Ctx) {
	appID, err := strconv.Atoi(c.Params("app_id"))
	if err != nil {
		c.Status(fiber.StatusInternalServerError).SendString("invalid app_id")
		return
	}

	app := new(Application)
	if err := c.BodyParser(app); err != nil {
		c.Status(fiber.StatusBadRequest).SendString("invalid body!")
		return
	}

	if app.AppID == 0 {
		c.Status(fiber.StatusBadRequest).SendString("app-id is mandatory")
		return
	}

	if app.Name == "" {
		c.Status(fiber.StatusBadRequest).SendString("name is mandatory")
		return
	}

	if appID != app.AppID {
		c.Status(fiber.StatusBadRequest).SendString("app-id doesn't match path")
		return
	}

	stored := findByID(h.DB, appID)

	if stored == nil {
		h.DB.Create(&app)

		c.Status(fiber.StatusCreated).JSON(app)
	} else {
		stored.Name = app.Name
		h.DB.Save(stored)

		c.Status(fiber.StatusOK).JSON(stored)
	}
}

// DeleteApplication removes the application identified by the :app_id
func (h *Handler) DeleteApplication(c *fiber.Ctx) {
	appID, err := strconv.Atoi(c.Params("app_id"))
	if err != nil {
		c.Status(fiber.StatusInternalServerError).SendString("invalid app_id")
		return
	}

	app := findByID(h.DB, appID)

	if app != nil {
		h.DB.Delete(app)
	}

	c.Status(fiber.StatusOK).SendString("deleted!")
}

// ErrorHandler implements the default error handling for the application
func (h *Handler) ErrorHandler(c *fiber.Ctx, err error) {
	code := fiber.StatusInternalServerError

	if e, ok := err.(*fiber.Error); ok {
		code = e.Code
	}

	c.Set(fiber.HeaderContentType, fiber.MIMETextPlainCharsetUTF8)
	c.Status(code).SendString(err.Error())
}

func findByID(db *gorm.DB, appID int) *Application {
	app := new(Application)

	if db.Where("app_id = ?", appID).First(app).RecordNotFound() {
		return nil
	}

	return app
}
