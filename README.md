# financial-health-indicator-server

## How to run

Prerequisite

* JDK 17

```
mvn install
```

Run

```
mvn spring-boot:run
```

## There are four microservices
- Service Registry
- Cloud gateway
- User service
- Financial indicator service

## Clone repository
  ```
  https://github.com/tanveer-rajeev/financial-health-indicator-server.git
  ```
## Application starting process
  ```
  # Have to start sequentially
  - Service registry
  - Cloud gateway
  - User service
  - Financial indicator service
  ```
## API Documentation
- User service APIs endpoints
  ```
   Register user api- http://localhost:9090/api/v1/auth/register
   Login user api - http://localhost:9090/api/v1/auth/login
  ```
- Financial service APIs endpoints
  - Prerequisite
     - For Postman you have to pass a JWT token into an Authorization header that will get when the login API call
  ```
   save data api - http://localhost:9090/api/v1/data/save
   calculate financial health api - http://localhost:9090/api/v1/data/calculateHealth/1
   get data api - http://localhost:9090/api/v1/data/1
  ```
