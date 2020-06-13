AppsApp - Flask version
=======================

## Specs

* Language: Python 3.7
* Language: Flask + Flask-Restful
* ORM: SQLAlchemy
* DB Migration: Alembic
* Database: Postgres 12
* HTTP Server: Gunicorn

## How to Run

1. Build images

    ```commandline
    docker-compose build
    ```

2. Lunch database

    ```commandline
    docker-compose up -d db
    ```

3. Start webapp

    ```commandline
    docker-compose up webapp
    ```

## Clean Up

```commandline
docker-compose down
```