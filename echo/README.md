AppsApp - Echo version
======================

Specs
-----

* Language: GO
* Framework: Echo
* ORM: GORM
* DB Migration: GORM
* Database: Postgres 12
* HTTP Server: Echo

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
2020/06/26 01:59:20 [#...................] 5%
2020/06/26 01:59:20 [##..................] 10%
2020/06/26 01:59:21 [###.................] 15%
2020/06/26 01:59:21 [####................] 20%
2020/06/26 01:59:22 [#####...............] 25%
2020/06/26 01:59:22 [######..............] 30%
2020/06/26 01:59:23 [#######.............] 35%
2020/06/26 01:59:23 [########............] 40%
2020/06/26 01:59:23 [#########...........] 45%
2020/06/26 01:59:24 [##########..........] 50%
2020/06/26 01:59:24 [###########.........] 55%
2020/06/26 01:59:25 [############........] 60%
2020/06/26 01:59:25 [#############.......] 65%
2020/06/26 01:59:26 [##############......] 70%
2020/06/26 01:59:26 [###############.....] 75%
2020/06/26 01:59:27 [################....] 80%
2020/06/26 01:59:27 [#################...] 85%
2020/06/26 01:59:27 [##################..] 90%
2020/06/26 01:59:28 [###################.] 95%
2020/06/26 01:59:28 [####################] 100%
===== Stats =====
Executed requests: 10000
Time taken to complete: 14m39.33777331s
Requests per second: 1137.2194
Avg response time: 87.933777ms
===== Status 200 =====
10000 requests, with avg response time of 87.933777ms
And the following distribution:
- The fastest request took 1.629668ms
- 20% of requests under 3.472906ms
- 40% of requests under 4.460666ms
- 60% of requests under 6.560593ms
- 80% of requests under 279.739388ms
- The slowest request took 429.242547ms
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
2020/06/26 01:59:55 [#...................] 5%
2020/06/26 01:59:55 [##..................] 10%
2020/06/26 01:59:56 [###.................] 15%
2020/06/26 01:59:56 [####................] 20%
2020/06/26 01:59:56 [#####...............] 25%
2020/06/26 01:59:57 [######..............] 30%
2020/06/26 01:59:57 [#######.............] 35%
2020/06/26 01:59:58 [########............] 40%
2020/06/26 01:59:58 [#########...........] 45%
2020/06/26 01:59:59 [##########..........] 50%
2020/06/26 01:59:59 [###########.........] 55%
2020/06/26 02:00:00 [############........] 60%
2020/06/26 02:00:00 [#############.......] 65%
2020/06/26 02:00:01 [##############......] 70%
2020/06/26 02:00:01 [###############.....] 75%
2020/06/26 02:00:02 [################....] 80%
2020/06/26 02:00:02 [#################...] 85%
2020/06/26 02:00:02 [##################..] 90%
2020/06/26 02:00:03 [###################.] 95%
2020/06/26 02:00:07 [####################] 100%
===== Stats =====
Executed requests: 10000
Time taken to complete: 30m44.102102823s
Requests per second: 1084.5386
Avg response time: 184.41021ms
===== Status 200 =====
10000 requests, with avg response time of 184.41021ms
And the following distribution:
- The fastest request took 1.503773ms
- 20% of requests under 3.42501ms
- 40% of requests under 4.364382ms
- 60% of requests under 6.461593ms
- 80% of requests under 428.994993ms
- The slowest request took 7.137388022s
```
