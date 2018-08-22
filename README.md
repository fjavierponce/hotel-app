# Hotel Management Application
## Introduction
This project was created to apply and strengthen the fundamentals of Object Oriented Programming,
clean code, and programming best practices. Also, this project describes the use of programming
concepts that enforce code readability, reusability and scalability.

## Installation
**Fork this repo**
```sh
$ git clone https://github.com/fjavierponce/hotel-app.git
$ cd hotel-app
```
**Compile with Maven**
```sh
$ mvn clean install
```
**If you want to temporarily skip tests**
```sh
$ mvn clean install -DskipTests=true
```


## Usage
**Run the server using Maven**
```sh
$ cd hotel-api-server
$ mvn spring-boot:run
```

## Technologies
* Spring Boot
* JDBC
* Liquibase
* Postgres

## Resources
This project was based on the following resources:
* [Effective Java (Second Edition)](https://www.amazon.com.mx/Effective-Java-Joshua-Bloch/dp/0321356683/ref=sr_1_2?ie=UTF8&qid=1534913010&sr=8-2&keywords=effective+java)
* [Clean Code](https://www.amazon.com.mx/Clean-Code-Handbook-Software-Craftsmanship/dp/0132350882/ref=sr_1_1?ie=UTF8&qid=1534912940&sr=8-1&keywords=clean+code)
* [Spring Boot Reference Guide](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)
* [Liquibase Docs](https://www.liquibase.org/documentation/index.html)
