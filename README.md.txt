# parking system App

parking system is a backend service for parking slot management.

### Tech

It uses:

* Java8
* Spring boot
* Maven

### Swagger UI
To view our REST API go to {HOST}:{PORT}/swagger-ui.html
> http://localhost:8080/swagger-ui.html

## How to run

->  Please update db username and password in application.properties file
->  create database with the name parking

###Features

1. Park a vehicle  

api endpoint: Post api/park


2. Unpark a vehicle
              &
Calculate amount to be paid for parking based on time it was there

api endpoint: Patch api/unpark/vehicle/{vehicleNumber}


3. Check if any space is available for parking

api endpoint: Get api/available-slot/{vehicleType}


4.Check if any specific slot is empty
             &
Get the details of the vehicle at a specific slot

api endpoint: Get api/slot/{number}


