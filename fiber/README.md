AppsApp - Echo version
======================

Specs
-----

* Language: GO
* Framework: Fiber
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
2020/07/05 12:09:54 [#...................] 5%
2020/07/05 12:09:55 [##..................] 10%
2020/07/05 12:09:55 [###.................] 15%
2020/07/05 12:09:55 [####................] 20%
2020/07/05 12:09:56 [#####...............] 25%
2020/07/05 12:09:56 [######..............] 30%
2020/07/05 12:09:57 [#######.............] 35%
2020/07/05 12:09:57 [########............] 40%
2020/07/05 12:09:57 [#########...........] 45%
2020/07/05 12:09:58 [##########..........] 50%
2020/07/05 12:09:58 [###########.........] 55%
2020/07/05 12:09:59 [############........] 60%
2020/07/05 12:09:59 [#############.......] 65%
2020/07/05 12:09:59 [##############......] 70%
2020/07/05 12:10:00 [###############.....] 75%
2020/07/05 12:10:00 [################....] 80%
2020/07/05 12:10:01 [#################...] 85%
2020/07/05 12:10:01 [##################..] 90%
2020/07/05 12:10:02 [###################.] 95%
2020/07/05 12:10:02 [####################] 100%
===== Stats =====
Executed requests: 10000
Time taken to complete: 13m48.155671523s
Requests per second: 1207.5024
Avg response time: 82.815567ms
===== Status 200 =====
10000 requests, with avg response time of 82.815567ms
And the following distribution:
- The fastest request took 1.671835ms
- 20% of requests under 3.199696ms
- 40% of requests under 4.128712ms
- 60% of requests under 5.994867ms
- 80% of requests under 263.60354ms
- The slowest request took 441.518753ms
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
2020/07/05 12:11:05 [#...................] 5%
2020/07/05 12:11:05 [##..................] 10%
2020/07/05 12:11:06 [###.................] 15%
2020/07/05 12:11:06 [####................] 20%
2020/07/05 12:11:07 [#####...............] 25%
2020/07/05 12:11:07 [######..............] 30%
2020/07/05 12:11:07 [#######.............] 35%
2020/07/05 12:11:08 [########............] 40%
2020/07/05 12:11:08 [#########...........] 45%
2020/07/05 12:11:09 [##########..........] 50%
2020/07/05 12:11:09 [###########.........] 55%
2020/07/05 12:11:09 [############........] 60%
2020/07/05 12:11:10 [#############.......] 65%
2020/07/05 12:11:10 [##############......] 70%
2020/07/05 12:11:11 [###############.....] 75%
2020/07/05 12:11:11 [################....] 80%
2020/07/05 12:11:11 [#################...] 85%
2020/07/05 12:11:12 [##################..] 90%
2020/07/05 12:11:12 [###################.] 95%
2020/07/05 12:11:14 [####################] 100%
===== Stats =====
Executed requests: 10000
Time taken to complete: 29m10.070391374s
Requests per second: 1142.8112
Avg response time: 175.007039ms
===== Status 200 =====
10000 requests, with avg response time of 175.007039ms
And the following distribution:
- The fastest request took 1.477104ms
- 20% of requests under 3.037636ms
- 40% of requests under 3.914771ms
- 60% of requests under 5.799581ms
- 80% of requests under 402.695285ms
- The slowest request took 7.435367253s
```
