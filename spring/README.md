AppsApp - Spring-boot version
=============================

Specs
-----

* Language: Java 11
* Framework: Spring-boot
* DB Migration: Flyway
* Database: Postgres 12
* HTTP Server: Undertow

How to Run
----------

1. Build images

    ```commandline
    docker-compose build
    ```

2. Launch database in backgound

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
