# SIMPLIFIED E-COMMERCE

## TECHNICAL INFORMATION

### Development Environment

* [Swagger API Documentation](http://localhost:8082/restapidoc.html) 
* [H2 Database Console](http://localhost:8082/h2-console/) 

### Project Structure

Project is formed as a Maven module based project that contains one module for Persistence and another for Api Web Application that has dependency to Persistence module.

    Simplified E-Commerce - Base                                       [pom]
    |-->Simplified E-Commerce - Persistence Module                     [jar]
    |-->Simplified E-Commerce - API (Runnable)                         [jar]

### Running The Application

You can execute the following command to run the application

    java -jar simplyecomm-api/target/simplyecomm-api-0.0.1-SNAPSHOT.jar -Dspring.profiles.active=default
    
### Building The Application

Run Maven Goal Install

Or Run Maven Wrapper given inside the project. For more information about Maven Wrapper check: [https://github.com/takari/maven-wrapper](https://github.com/takari/maven-wrapper)

    .\mvnw clean install

### Testing The Application

#### Run All Tests

Code containing all tests that can be executed using maven test goal.

    .\mvnw test

### Run Integration Tests

Code containing integration tests that can only be executed in maven install phase and not in test phase.

    .\mvnw install


### Extra Libraries

#### Lombok

This project uses Lombok annotations to create getters, setters, loggers etc. for Java objects. 

Lombok dependency is enough to build project with Maven but you need to configure the Lombok for your IDE : [eclipse](https://projectlombok.org/setup/eclipse) or [intellij](https://projectlombok.org/setup/intellij) 

#### Liquibase Database Initialization

Application uses Liquibase library to manage database changes. 

See [https://www.liquibase.org/](https://www.liquibase.org/) for more information.

    classpath:/db/changelog/db.changelog-master.xml
    
------------------------------------------------------------

## SETTINGS

Settings can be modified in application.properties file.

------------------------------------------------------------

## CHECKOUT API

The simplified e-commerce API provides a single endpoint that performs a checkout action. The single endpoint takes a list of watches and return the total cost. 

Supports localization for English, Arabic and Turkish

### Sample Product Catalogue : Watches

Below is a catalogue of four watches and their associated prices:

| Watch ID      | Watch Name    | Unit Price    | Discount      |
| ------------- | ------------- | ------------- | ------------- |
| 001           | Rolex         | 100           | 3 for 200     |
| 002           | Michael Kors  |  80           | 2 for 120     |
| 003           | Swatch        |  50           | N/A           |
| 004           | Casio         |  30           | N/A           |

### Requirements:

* The first two products have a possible discount. As an example, if the user attempts to checkout three or six Rolex watches then they will receive the discount price once or twice,respectively.
* There is no limit to the number of items or combinations of watches a user can checkout.
* There is no limit to the number of times a discount can be used.
* Similarly, a user can checkout a single item if they wish.

### Endpoint reference

As a further guideline here's an endpoint definition that you can use to design your API endpoint.

#### Request

POST http://localhost:8080/checkout

    curl --insecure -X "POST" "http://localhost:8082/checkout/" -H "accept: application/json" -H "Content-Type: application/json" -H "Accept-Language:ar" -d "["001",  "002",  "001",  "004",  "003"]"

##### Headers

    Accept: application/json
    Content-Type: application/json
    Accept-Language:ar

##### Body

    [  "001",  "002",  "001",  "004",  "003" ]

#### Response

##### Headers

    Content-Type: application/json

##### Body

    { "price": 360 }


    