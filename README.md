# Spring Boot Microservice for Price Query

## Introduction

The project consists of building a microservice using Spring Boot that provides a REST endpoint to query product prices in an e-commerce system. Price information is stored in a table called PRICES in an in-memory database H2.

The Spring Boot project has been implemented with hexagonal architecture, OpenAPI and Database H2.

It has been decided to apply the hexagonal architecture in this project to separate the business logic from the implementation details, to facilitate the modification and maintenance of the application, because currently the project is implemented on the H2 database and you can easily adapt the code if you decide to change the database.

Another advantage of the hexagonal architecture is that the internal layers that contain the business logic are independent of the framework, which makes it easier to adapt to a new framework.

And with the hexagonal architecture, performing unit tests is considerably simplified.

## Prices Table (PRICES)

The `PRICES` table in the company's e-commerce database reflects the final price (PVP) and the rate applied to a product within certain dates. Below is an example of the table with relevant fields:

| BRAND_ID | START_DATE          | END_DATE            | PRICE_LIST | PRODUCT_ID | PRIORITY | PRICE | CURR |
|----------|---------------------|---------------------|------------|------------|----------|-------|------|
| 1        | 2020-06-14-00.00.00 | 2020-12-31-23.59.59 | 1          | 35455      | 0        | 35.50 | EUR  |
| 1        | 2020-06-14-15.00.00 | 2020-06-14-18.30.00 | 2          | 35455      | 1        | 25.45 | EUR  |
| 1        | 2020-06-15-00.00.00 | 2020-06-15-11.00.00 | 3          | 35455      | 1        | 30.50 | EUR  |
| 1        | 2020-06-15-16.00.00 | 2020-12-31-23.59.59 | 4          | 35455      | 1        | 38.95 | EUR  |

### Fields:

- `BRAND_ID`: Foreign key of the chain group.
- `START_DATE`, `END_DATE`: Range of dates in which the indicated price rate applies.
- `PRICE_LIST`: Identifier of the applicable price list.
- `PRODUCT_ID`: Product code identifier.
- `PRIORITY`: Price application disambiguator. If two rates coincide within a date range, the one with the higher priority (higher numerical value) is applied.
- `PRICE`: Final selling price.
- `CURR`: ISO currency code.

## Unit tests implemented:

These are the tests implemented in the unit tests:

- Accepts as input parameters: application date, product identifier, brand identifier.
- Returns as output data: product identifier, brand identifier, applicable rate, application dates and final price to apply.
- Use an in-memory database (H2 type) and initialize it with the example data.
- Implemented tests for the REST endpoint to validate the following requests to the service with the example data:
    - Test 1: request at 10:00 on day 14 for product 35455 for brand 1 (ZARA)
    - Test 2: request at 16:00 on day 14 for product 35455 for brand 1 (ZARA)
    - Test 3: request at 21:00 on day 14 for product 35455 for brand 1 (ZARA)
    - Test 4: request at 10:00 on day 15 for product 35455 for brand 1 (ZARA)
    - Test 5: request at 21:00 on day 16 for product 35455 for brand 1 (ZARA)

## Requirements and Development:

To run and develop this microservice, you need to have Java, Maven and Docker installed in your machine.

## Installation and Execution:

- 1. Clone this repository.
- 2. Run this command to generate the jar file from the project root path:
        `mvn clean package -DskipTests`
- 3. The Docker service has to be started and running
- 4. Run this command to create the docker image:
        `docker build -t microservice-prices:v1 .`
- 5. Finally the command docker-compose up -d is executed to start the container defined in a docker-compose.yml file:
        `docker-compose up -d`
- 6. Once the application is up and running, you can access the REST API to query prices.
- 7. The service can be accessed through the URL:
        `http://localhost:8095/ecommerce/prices/price?date=2020-06-14T16:00:00&productId=35455&brandId=1`
- 8. The swagger can be accessed via the URL to test the API:
        `http://localhost:8095/swagger`
- 9. You can also try it via Postman through the file obtained at this URL:
        `https://api.postman.com/collections/10884972-c783f14b-243e-4313-b294-e32d6ec4197c?access_key=PMAT-01HPKYE0274GJDPVJ0GRT66CXM`

## Autor 
This microservice was developed by Gustavo. For any questions or comments, please contact me at [gusolra@gmail.com].

