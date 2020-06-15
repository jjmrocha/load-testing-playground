AppsApp - Starlette version
===========================

Specs
-----

* Language: Python 3.7
* Framework: [Starlette](https://www.starlette.io/)
* ORM: Databases + SQLAlchemy
* DB Migration: Alembic
* Database: Postgres 12
* HTTP Server: Uvicorn

How to Run
----------

1. Build images

    ```commandline
    docker-compose build
    ```

2. Launch database in background

    ```commandline
    docker-compose up -d db
    ```

3. Start webapp

    ```commandline
    docker-compose up webapp
    ```

Clean Up
--------

```commandline
docker-compose down
```
