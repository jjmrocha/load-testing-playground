AppsApp - Spring-boot version
=============================

Specs
-----

* Language: Java 11
* Framework: Spring-boot
* ORM: JPA
* DB Migration: Flyway
* Database: Postgres 12
* HTTP Server: Undertow

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
2020/06/20 23:08:18 [#...................] 5%
2020/06/20 23:08:18 [##..................] 10%
2020/06/20 23:08:19 [###.................] 15%
2020/06/20 23:08:19 [####................] 20%
2020/06/20 23:08:19 [#####...............] 25%
2020/06/20 23:08:20 [######..............] 30%
2020/06/20 23:08:20 [#######.............] 35%
2020/06/20 23:08:21 [########............] 40%
2020/06/20 23:08:21 [#########...........] 45%
2020/06/20 23:08:22 [##########..........] 50%
2020/06/20 23:08:22 [###########.........] 55%
2020/06/20 23:08:23 [############........] 60%
2020/06/20 23:08:23 [#############.......] 65%
2020/06/20 23:08:24 [##############......] 70%
2020/06/20 23:08:24 [###############.....] 75%
2020/06/20 23:08:24 [################....] 80%
2020/06/20 23:08:25 [#################...] 85%
2020/06/20 23:08:25 [##################..] 90%
2020/06/20 23:08:25 [###################.] 95%
2020/06/20 23:08:26 [####################] 100%
===== Stats =====
Executed requests: 10000
Time taken to complete: 14m9.383881312s
Requests per second: 1177.3240
Avg response time: 84.938388ms
===== Status 200 =====
10000 requests, with avg response time of 84.938388ms
And the following distribution:
- The fastest request took 4.619902ms
- 20% of requests under 56.070435ms
- 40% of requests under 71.131983ms
- 60% of requests under 85.354519ms
- 80% of requests under 107.268ms
- The slowest request took 434.943363ms
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
2020/06/20 23:10:55 [#...................] 5%
2020/06/20 23:10:55 [##..................] 10%
2020/06/20 23:10:56 [###.................] 15%
2020/06/20 23:10:56 [####................] 20%
2020/06/20 23:10:56 [#####...............] 25%
2020/06/20 23:10:57 [######..............] 30%
2020/06/20 23:10:57 [#######.............] 35%
2020/06/20 23:10:57 [########............] 40%
2020/06/20 23:10:57 [#########...........] 45%
2020/06/20 23:10:58 [##########..........] 50%
2020/06/20 23:10:58 [###########.........] 55%
2020/06/20 23:10:58 [############........] 60%
2020/06/20 23:10:59 [#############.......] 65%
2020/06/20 23:10:59 [##############......] 70%
2020/06/20 23:10:59 [###############.....] 75%
2020/06/20 23:10:59 [################....] 80%
2020/06/20 23:11:00 [#################...] 85%
2020/06/20 23:11:00 [##################..] 90%
2020/06/20 23:11:00 [###################.] 95%
2020/06/20 23:11:01 [####################] 100%
===== Stats =====
Executed requests: 10000
Time taken to complete: 19m30.92372107s
Requests per second: 1708.0532
Avg response time: 117.092372ms
===== Status 200 =====
10000 requests, with avg response time of 117.092372ms
And the following distribution:
- The fastest request took 2.8323ms
- 20% of requests under 86.737152ms
- 40% of requests under 99.738775ms
- 60% of requests under 113.526292ms
- 80% of requests under 135.143936ms
- The slowest request took 1.281689673s
```
