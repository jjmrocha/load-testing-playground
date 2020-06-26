package main

import (
	"fmt"
	"log"
	"os"

	"github.com/jinzhu/gorm"
	_ "github.com/jinzhu/gorm/dialects/postgres"
	"github.com/jjmrocha/load-testing-playground/echo/app"
	"github.com/labstack/echo/v4"
)

func main() {
	// Connect to db
	db := connectDB()
	defer db.Close()
	// Migrate tables
	migrate(db)
	// create API handler
	h := &app.Handler{DB: db}
	// Create routes
	e := createServer(h)
	e.Logger.Fatal(e.Start(":8080"))
}

func connectDB() *gorm.DB {
	host := os.Getenv("DB_SERVER_NAME")
	port := os.Getenv("DB_SERVER_PORT")
	user := os.Getenv("DB_USER")
	dbName := os.Getenv("DB_NAME")
	password := os.Getenv("DB_PASSWORD")
	connectStr := fmt.Sprintf("sslmode=disable host=%s port=%s dbname=%s user=%s  password=%s",
		host, port, dbName, user, password)

	db, err := gorm.Open("postgres", connectStr)
	if err != nil {
		log.Fatalf("Failed to connect database: '%v'", err)
	}

	return db
}

func migrate(db *gorm.DB) {
	db.AutoMigrate(&app.Application{})
}

func createServer(h *app.Handler) *echo.Echo {
	e := echo.New()
	e.GET("/apps/:app_id", h.GetApplication)
	e.PUT("/apps/:app_id", h.StoreApplication)
	e.DELETE("/apps/:app_id", h.DeleteApplication)
	return e
}
