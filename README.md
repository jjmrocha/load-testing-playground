Load Testing Playground
=======================

Specs for the Tested Application
--------------------------------

1. The frameworks being tested will implement the following endpoints:
  * `PUT /apps/{app_id}` - With Body: 
    ```
    {"app-id": {app-id}, "name": "{app name}"}
    ```
    
    Response Status:
    - 200 If the `app_id` already exists, in this case the name must be updated
    - 201 If the `app_id` didn't exists, in this case the record should been created
  * `GET /apps/{app_id}`
  
    Response Status:
    - 200 If the `app_id` was found
    - 404 If the `app_id` doesn't exists
    
    Respose Body:
    ```
    {"app-id": {app-id}, "name": "{app name}"}
    ```
  * `DELETE /apps/{app_id}`
  2. The endpoinds should be available on `127.0.0.1:8080`
3. Data must be stored on a `Postgres 12` database


Frameworks
----------

Currently we have the following implementations:
* [Flask](flask) (Python)
* [Spring-boot](spring) (Java)
* [Starlette](starlette) (Python)


Load Testing
------------
### Testing With Beast (https://github.com/jjmrocha/beast)

___Setup___

You can install `the Beast` from [here](https://github.com/jjmrocha/beast/releases)

___Content___

* A CSV file with test data: `apps.csv`
* Requests templates for GET, PUT and DELETE: 
  * `apps_get.yaml`
  * `apps_put.yaml`
  * `apps_delete.yaml`
* Shell scripts to invoke `the Beast`:
  * `beast_put.sh`
  * `beast_delete.sh`
  * `apps_get.yaml` this one allows you to pass the number of requests and the number of concurrent requests

___How to Test___

1. Run script `beast_put.sh` to load data to the database
2. Run script `apps_get.sh` with different number of concurrent requests to check how the app keep up with the load:

    ```commandline
    % ./beast_get.sh 10000 10
    ===== System =====
    Operating System: darwin
    System Architecture: amd64
    Logical CPUs: 12
    ===== Test =====
    Request template: apps_get.yaml
    Sample Data: apps.csv
    Number of requests: 10000
    Number of concurrent requests: 10
    ===== Preparing =====
    - Loading data file
    - Loading request template
    - Generating requests
    ===== Executing =====
    2020/06/13 14:07:28 [#...................] 5%
    2020/06/13 14:07:29 [##..................] 10%
    2020/06/13 14:07:30 [###.................] 15%
    2020/06/13 14:07:31 [####................] 20%
    2020/06/13 14:07:31 [#####...............] 25%
    2020/06/13 14:07:32 [######..............] 30%
    2020/06/13 14:07:33 [#######.............] 35%
    2020/06/13 14:07:34 [########............] 40%
    2020/06/13 14:07:35 [#########...........] 45%
    2020/06/13 14:07:36 [##########..........] 50%
    2020/06/13 14:07:37 [###########.........] 55%
    2020/06/13 14:07:38 [############........] 60%
    2020/06/13 14:07:39 [#############.......] 65%
    2020/06/13 14:07:40 [##############......] 70%
    2020/06/13 14:07:41 [###############.....] 75%
    2020/06/13 14:07:42 [################....] 80%
    2020/06/13 14:07:43 [#################...] 85%
    2020/06/13 14:07:44 [##################..] 90%
    2020/06/13 14:07:45 [###################.] 95%
    2020/06/13 14:07:46 [####################] 100%
    ===== Stats =====
    Executed requests: 10000
    Time taken to complete: 3m15.658850273s
    Requests per second: 511.0937
    Avg response time: 19.565885ms
    ===== Status 200 =====
    10000 requests, with avg response time of 19.565885ms
    And the following distribution:
    - The fastest request took 5.785034ms
    - 20% of requests under 13.161764ms
    - 40% of requests under 16.415209ms
    - 60% of requests under 19.785001ms
    - 80% of requests under 24.651447ms
    - The slowest request took 243.130124ms
    ```

3. Run script `beast_delete.sh` to delete the data from the database

---
### Testing With JMeter (https://jmeter.apache.org/)

___Setup___

You can download `jmeter` from [apache.org](https://jmeter.apache.org/)
or install using `brew`:

```commandline
brew install jmeter 
```

___Content___

Two `JMeter` tests plans:
* `jmeter.jmx` - Similar to the approach used for `the Beast` tests; loads data from the same file, then run multiple GET requests and finally deletes all the data 
* `jmeter-alternative.jmx` - Runs multiple parallel requests, each one:
  * Adds a new record using `PUT`
  * Runs multiple (from 1 to 10) sequential `GET` requests 
  * Finally deletes the record using `DELETE` 


___Basic Script___

Load script:
```commandline
jmeter -t jmeter.jmx
```

You can customize the test by changing the setup:
![alt text](__images__/basic_setup.png "Script setup")

You can see the execution:
![alt text](__images__/basic_summary.png "Execution summary")

And see a graph with the GET results:
![alt text](__images__/basic_graph.png "Graph results")


___Alternative Script___

```commandline
jmeter -t jmeter-alternative.jmx
```

You can customize the test by changing the setup:
![alt text](__images__/alt_setup.png "Script setup")

You can see the execution:
![alt text](__images__/alt_summary.png "Execution summary")

And see a graph with the results for all endpoints:
![alt text](__images__/alt_graph.png "Graph results")


___Non GUI Execution___

To run without GUI, you can use the command:
```commandline
jmeter -t <scrip name> -n -l <output file>
```

Example:
```
$ jmeter -t jmeter.jmx -n -l jmeter.out                                                                                    1 ↵
Creating summariser <summary>
Created the tree successfully using jmeter.jmx
Starting standalone test @ Wed Jun 17 20:14:59 WEST 2020 (1592421299872)
Waiting for possible Shutdown/StopTestNow/HeapDump/ThreadDump message on port 4445
Warning: Nashorn engine is planned to be removed from a future JDK release
summary +      1 in 00:00:00 =    6.0/s Avg:    35 Min:    35 Max:    35 Err:     0 (0.00%) Active: 1 Started: 1 Finished: 0
summary +  10119 in 00:00:13 =  791.4/s Avg:     7 Min:     2 Max:    59 Err:     0 (0.00%) Active: 0 Started: 1 Finished: 1
summary =  10120 in 00:00:13 =  781.2/s Avg:     7 Min:     2 Max:    59 Err:     0 (0.00%)
Tidying up ...    @ Wed Jun 17 20:15:13 WEST 2020 (1592421313072)
... end of run
```


___Client-Server___

In case a single machine is unable to simulate enough users to stress the server you can use the client-server approach and control multiple JMeter servers (running remotely) from a single JMeter client. For more information please check the [documentation](https://jmeter.apache.org/usermanual/remote-test.html)


License
-------
Any contributions made under this project will be governed by the [MIT License](./LICENSE.md).
