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

Performance
-----------


___100 Concurrent Requests___
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
2020/06/20 22:56:36 [#...................] 5%
2020/06/20 22:56:37 [##..................] 10%
2020/06/20 22:56:37 [###.................] 15%
2020/06/20 22:56:38 [####................] 20%
2020/06/20 22:56:38 [#####...............] 25%
2020/06/20 22:56:38 [######..............] 30%
2020/06/20 22:56:39 [#######.............] 35%
2020/06/20 22:56:39 [########............] 40%
2020/06/20 22:56:40 [#########...........] 45%
2020/06/20 22:56:40 [##########..........] 50%
2020/06/20 22:56:41 [###########.........] 55%
2020/06/20 22:56:41 [############........] 60%
2020/06/20 22:56:42 [#############.......] 65%
2020/06/20 22:56:42 [##############......] 70%
2020/06/20 22:56:42 [###############.....] 75%
2020/06/20 22:56:43 [################....] 80%
2020/06/20 22:56:43 [#################...] 85%
2020/06/20 22:56:44 [##################..] 90%
2020/06/20 22:56:44 [###################.] 95%
2020/06/20 22:56:45 [####################] 100%
===== Stats =====
Executed requests: 10000
Time taken to complete: 14m15.857253414s
Requests per second: 1168.4191
Avg response time: 85.585725ms
===== Status 200 =====
10000 requests, with avg response time of 85.585725ms
And the following distribution:
- The fastest request took 8.15032ms
- 20% of requests under 48.959765ms
- 40% of requests under 67.010712ms
- 60% of requests under 86.997616ms
- 80% of requests under 113.947368ms
- The slowest request took 444.604443ms
```

___200 Concurrent Requests___
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
Number of concurrent requests: 200
===== Preparing =====
- Reading configuration
- Loading data file
- Loading request template
- Generating requests
===== Executing =====
2020/06/20 22:57:52 [#...................] 5%
2020/06/20 22:57:53 [##..................] 10%
2020/06/20 22:57:53 [###.................] 15%
2020/06/20 22:57:54 [####................] 20%
2020/06/20 22:57:54 [#####...............] 25%
2020/06/20 22:57:54 [######..............] 30%
2020/06/20 22:57:55 [#######.............] 35%
2020/06/20 22:57:55 [########............] 40%
2020/06/20 22:57:56 [#########...........] 45%
2020/06/20 22:57:56 [##########..........] 50%
2020/06/20 22:57:57 [###########.........] 55%
2020/06/20 22:57:57 [############........] 60%
2020/06/20 22:57:58 [#############.......] 65%
2020/06/20 22:57:58 [##############......] 70%
2020/06/20 22:57:59 [###############.....] 75%
2020/06/20 22:57:59 [################....] 80%
2020/06/20 22:57:59 [#################...] 85%
2020/06/20 22:58:00 [##################..] 90%
2020/06/20 22:58:00 [###################.] 95%
2020/06/20 22:58:01 [####################] 100%
===== Stats =====
Executed requests: 10000
Time taken to complete: 29m40.416113077s
Requests per second: 1123.3329
Avg response time: 178.041611ms
===== Status 200 =====
10000 requests, with avg response time of 178.041611ms
And the following distribution:
- The fastest request took 22.921448ms
- 20% of requests under 69.995291ms
- 40% of requests under 96.721093ms
- 60% of requests under 149.834311ms
- 80% of requests under 252.453643ms
- The slowest request took 1.99482424s
```
