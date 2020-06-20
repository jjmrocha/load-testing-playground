AppsApp - Flask version
=======================

Specs
-----

* Language: Python 3.7
* Framework: Flask + Flask-Restful
* ORM: SQLAlchemy
* DB Migration: Alembic
* Database: Postgres 12
* HTTP Server: Gunicorn

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

Performance
-----------

```
===== System =====
Operating System: darwin
System Architecture: amd64
Logical CPUs: 12
===== Test =====
Request template: apps_get.yaml
Sample Data: ../test_data.csv
Configuration: config.json
Number of requests: 10000
Number of concurrent requests: 100
===== Preparing =====
- Reading configuration
- Loading data file
- Loading request template
- Generating requests
===== Executing =====
2020/06/20 22:52:28 [#...................] 5%
2020/06/20 22:52:29 [##..................] 10%
2020/06/20 22:52:30 [###.................] 15%
2020/06/20 22:52:31 [####................] 20%
2020/06/20 22:52:32 [#####...............] 25%
2020/06/20 22:52:33 [######..............] 30%
2020/06/20 22:52:34 [#######.............] 35%
2020/06/20 22:52:35 [########............] 40%
2020/06/20 22:52:36 [#########...........] 45%
2020/06/20 22:52:37 [##########..........] 50%
2020/06/20 22:52:38 [###########.........] 55%
2020/06/20 22:52:40 [############........] 60%
2020/06/20 22:52:41 [#############.......] 65%
2020/06/20 22:52:42 [##############......] 70%
2020/06/20 22:52:43 [###############.....] 75%
2020/06/20 22:52:44 [################....] 80%
2020/06/20 22:52:45 [#################...] 85%
2020/06/20 22:52:46 [##################..] 90%
2020/06/20 22:52:47 [###################.] 95%
2020/06/20 22:52:48 [####################] 100%
===== Stats =====
Executed requests: 10000
Time taken to complete: 34m5.582007696s
Requests per second: 488.8584
Avg response time: 204.5582ms
===== Status 200 =====
10000 requests, with avg response time of 204.5582ms
And the following distribution:
- The fastest request took 21.888891ms
- 20% of requests under 185.109759ms
- 40% of requests under 198.555974ms
- 60% of requests under 210.417516ms
- 80% of requests under 224.141782ms
- The slowest request took 440.272229ms
```
