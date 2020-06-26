package app

import (
	"net/http"
	"strconv"

	"github.com/jinzhu/gorm"
	"github.com/labstack/echo/v4"
)

// Handler is the base for the application
type Handler struct {
	DB *gorm.DB
}

// GetApplication returns an Application object
func (h *Handler) GetApplication(c echo.Context) error {
	appID, err := strconv.Atoi(c.Param("app_id"))
	if err != nil {
		return c.String(http.StatusInternalServerError, "invalid app_id")
	}

	app := findByID(h.DB, appID)
	if app == nil {
		return c.String(http.StatusNotFound, "app not found!")
	}

	return c.JSON(http.StatusOK, app)
}

// StoreApplication creates ou updates the Application with the object received on the body
func (h *Handler) StoreApplication(c echo.Context) error {
	appID, err := strconv.Atoi(c.Param("app_id"))
	if err != nil {
		return c.String(http.StatusInternalServerError, "invalid app_id")
	}

	app := new(Application)
	if err := c.Bind(app); err != nil {
		return err
	}

	if app.AppID == 0 {
		return c.String(http.StatusBadRequest, "app-id is mandatory")
	}

	if app.Name == "" {
		return c.String(http.StatusBadRequest, "name is mandatory")
	}

	if appID != app.AppID {
		return c.String(http.StatusBadRequest, "app-id doesn't match path")
	}

	stored := findByID(h.DB, appID)

	if stored == nil {
		h.DB.Create(&app)
		return c.JSON(http.StatusCreated, app)
	}

	stored.Name = app.Name
	h.DB.Save(stored)
	return c.JSON(http.StatusOK, stored)
}

// DeleteApplication removes the application identified by the :app_id
func (h *Handler) DeleteApplication(c echo.Context) error {
	appID, err := strconv.Atoi(c.Param("app_id"))
	if err != nil {
		return c.String(http.StatusInternalServerError, "invalid app_id")
	}

	app := findByID(h.DB, appID)

	if app != nil {
		h.DB.Delete(app)
	}

	return c.String(http.StatusOK, "deleted!")
}

func findByID(db *gorm.DB, appID int) *Application {
	app := new(Application)

	if db.Where("app_id = ?", appID).First(app).RecordNotFound() {
		return nil
	}

	return app
}
