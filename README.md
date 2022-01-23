# Documentation

### How to build and run application

```
mvn clean install
mvn spring-boot:run
```

### How to access the endpoints

* http://localhost:8080/v3/api-docs

### Tech stack

* Spring Boot + Java8
* H2 database as suggested
* Spring JPA
* Slf4J
* Jupiter
* OpenAPI

### Areas of improvement

* Versioning of APIs - via HATEOS
* Application metrics via micrometer
* SSL for APIs
* Authentication of end points