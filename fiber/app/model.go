package app

// Application represents and application instance
type Application struct {
	AppID int    `json:"app-id" gorm:"primary_key;column:app_id"`
	Name  string `json:"name" gorm:"type:varchar(80)"`
}

// TableName specifies the name of the table to be created
func (a Application) TableName() string {
	return "application"
}
