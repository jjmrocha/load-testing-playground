Load Testing Playground
=======================

Specification for the Tested Application
----------------------------------------

1. The frameworks being tested will implement the following endpoints:
  * `PUT /apps/{app_id}`
  * `GET /apps/{app_id}`
  * `DELETE /apps/{app_id}`
2. The endpoinds should be available on `127.0.0.1:8080`
3. Data must be stored on a `Postgres 12` database

For more details please check the OpenAPI Specification file [app-open-api.yaml](https://editor.swagger.io/?url=https://raw.githubusercontent.com/jjmrocha/load-testing-playground/master/app-open-api.yaml)
  

Frameworks
----------

Currently we have the following implementations:
* [Flask](flask) (Python)
* [Spring-boot](spring) (Java)
* [Starlette](starlette) (Python)


Testing Tools
-------------

Currently we have the following scripts testing tools:
* [the Beast](beast)
* [JMeter](jmeter)

Some scripts load test data from the following CSV file [test_data.csv](test_data.csv).


License
-------
Any contributions made under this project will be governed by the [MIT License](./LICENSE.md).
