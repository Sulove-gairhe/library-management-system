# Library Book Manager - Minimum Project Specification

## Objective

Build a working server-side Java CRUD application for managing library books.

The application must demonstrate:

* Java classes and objects
* Encapsulation
* Exception handling
* JSP, JSTL, HTML and CSS
* Spring MVC controllers
* Spring Boot REST controllers
* GET, POST, PUT and DELETE REST requests
* MySQL database connectivity
* Spring Boot
* Spring Data JPA
* Hibernate
* WAR deployment to external Apache Tomcat 10.1

## Architecture

Primary web application:

```text
Browser
  |
  | JSP forms
  v
BookWebController
  |
  v
BookService
  |
  v
BookRepository
  |
  v
MySQL Database
```

REST demonstration:

```text
Postman
  |
  | HTTP and JSON
  v
BookRestController
  |
  v
BookService
  |
  v
BookRepository
  |
  v
MySQL Database
```

The browser frontend is rendered by JSP pages deployed with the Spring Boot WAR. There is no separate client application.

## Technology

* Java 21
* Maven
* Spring Boot
* Spring Web MVC
* JSP
* JSTL
* HTML
* CSS
* Spring Data JPA
* Hibernate
* MySQL Connector/J
* Apache Tomcat 10.1

Use a stable Spring Boot version compatible with Java 21.

Do not create `module-info.java`.

## Project Structure

```text
advanced-java-library/
├── backend/
├── database/
│   └── setup.sql
├── AGENTS.md
├── PROJECT_SPEC.md
└── README.md
```

## Book Entity

Create a `Book` entity with:

```text
id       Long, auto-generated
title    String, required
author   String, required
category String, required
quantity Integer, required and zero or greater
```

Map the entity to a database table named `books`.

## Backend Classes

Use the essential backend classes:

```text
Book
BookRepository
BookService
BookRestController
BookWebController
BackendApplication
```

A separate service interface and implementation are not required.

`BookService` must expose plain Java methods and must not depend on HTTP-specific classes.

## JSP Web Routes

```text
GET  /books
GET  /books/new
POST /books
GET  /books/{id}/edit
POST /books/{id}/update
POST /books/{id}/delete
```

The web controller must use normal HTML form submissions. Update and delete actions use POST routes.

## REST Endpoints

### Get all books

```http
GET /api/books
```

Response:

```json
[
  {
    "id": 1,
    "title": "Effective Java",
    "author": "Joshua Bloch",
    "category": "Programming",
    "quantity": 4
  }
]
```

### Get one book

```http
GET /api/books/{id}
```

Return HTTP 404 when the book does not exist.

### Create book

```http
POST /api/books
Content-Type: application/json
```

Request:

```json
{
  "title": "Effective Java",
  "author": "Joshua Bloch",
  "category": "Programming",
  "quantity": 4
}
```

Return HTTP 201 when successful.

### Update book

```http
PUT /api/books/{id}
Content-Type: application/json
```

Return HTTP 200 when successful and HTTP 404 when the book does not exist.

### Delete book

```http
DELETE /api/books/{id}
```

Return HTTP 204 when successful and HTTP 404 when the book does not exist.

## Validation

Reject a request when:

* Title is blank.
* Author is blank.
* Category is blank.
* Quantity is null.
* Quantity is less than zero.

Use simple, understandable validation responses.

For web controller methods using validation, place `BindingResult` immediately after the validated model attribute parameter.

## JSP Views

Create JSP views under:

```text
backend/src/main/webapp/WEB-INF/views/
```

Required views:

```text
books/list.jsp
books/form.jsp
error.jsp
```

The list page must contain:

* Project heading
* Add Book link
* Table containing ID, title, author, category and quantity
* Edit action
* Delete form using POST submission
* Empty-state message

The form page must support both creating and editing books and must display validation errors.

Create one CSS file under:

```text
backend/src/main/resources/static/css/style.css
```

## Database

Use MySQL.

Database name:

```text
advanced_java_library
```

Create `database/setup.sql` containing:

```sql
CREATE DATABASE IF NOT EXISTS advanced_java_library;
```

Configure the backend using:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/advanced_java_library
spring.datasource.username=${DB_USERNAME:root}
spring.datasource.password=${DB_PASSWORD:}
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

Do not hardcode a private password.

## JSP Configuration

Configure JSP view resolution:

```properties
spring.mvc.view.prefix=/WEB-INF/views/
spring.mvc.view.suffix=.jsp
```

## WAR Deployment

The Maven backend project must use WAR packaging.

`BackendApplication` must extend `SpringBootServletInitializer`.

`spring-boot-starter-tomcat` must be marked as `provided` for external Tomcat deployment.

Use Jakarta-compatible imports for Spring Boot 3 and Tomcat 10.1.

## README

Document:

1. Required software.
2. How to create the MySQL database.
3. How to set the database password.
4. How to run the backend locally.
5. JSP browser routes.
6. REST endpoints.
7. Example GET request.
8. Example POST request.
9. How to test using Postman.
10. How to build and deploy the WAR to external Apache Tomcat 10.1.

## Excluded Features

Do not implement:

* Client-side scripting
* Additional browser client frameworks
* Authentication
* User roles
* Dashboard
* Reports
* Search
* Pagination
* Images
* File uploads
* Email
* Notifications
* API activity viewer
* Containerized deployment
* Hosted deployment
* Multiple database tables
* Advanced design patterns
* Unrequested features
