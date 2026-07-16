# Inventory Product Manager - Minimum Project Specification

## Objective

Build a working server-side Java CRUD application for managing inventory products.

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
ProductWebController
  |
  v
ProductService
  |
  v
ProductRepository
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
ProductRestController
  |
  v
ProductService
  |
  v
ProductRepository
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
advanced-java-inventory/
├── backend/
├── database/
│   └── setup.sql
├── AGENTS.md
├── PROJECT_SPEC.md
└── README.md
```

## Product Entity

Create a `Product` entity with:

```text
id       Long, auto-generated
name     String, required
sku      String, required and unique
category String, required
stock    Integer, required and zero or greater
```

Map the entity to a database table named `products`.

## Backend Classes

Use the essential backend classes:

```text
Product
ProductRepository
ProductService
ProductRestController
ProductWebController
InventoryApplication
```

A separate service interface and implementation are not required.

`ProductService` must expose plain Java methods and must not depend on HTTP-specific classes.

## JSP Web Routes

```text
GET  /products
GET  /products/new
POST /products
GET  /products/{id}/edit
POST /products/{id}/update
POST /products/{id}/delete
```

The web controller must use normal HTML form submissions. Update and delete actions use POST routes.

## REST Endpoints

### Get all products

```http
GET /api/products
```

Response:

```json
[
  {
    "id": 1,
    "name": "USB Keyboard",
    "sku": "KB-100",
    "category": "Accessories",
    "stock": 12
  }
]
```

### Get one product

```http
GET /api/products/{id}
```

Return HTTP 404 when the product does not exist.

### Create product

```http
POST /api/products
Content-Type: application/json
```

Request:

```json
{
  "name": "USB Keyboard",
  "sku": "KB-100",
  "category": "Accessories",
  "stock": 12
}
```

Return HTTP 201 when successful and HTTP 409 when the SKU already exists.

### Update product

```http
PUT /api/products/{id}
Content-Type: application/json
```

Return HTTP 200 when successful, HTTP 404 when the product does not exist, and HTTP 409 when the SKU belongs to another product.

### Delete product

```http
DELETE /api/products/{id}
```

Return HTTP 204 when successful and HTTP 404 when the product does not exist.

## Validation

Reject a request when:

* Name is blank.
* SKU is blank.
* SKU already exists.
* Category is blank.
* Stock is null.
* Stock is less than zero.

Use simple, understandable validation responses.

For web controller methods using validation, place `BindingResult` immediately after the validated model attribute parameter.

## JSP Views

Create JSP views under:

```text
backend/src/main/webapp/WEB-INF/views/
```

Required views:

```text
products/list.jsp
products/form.jsp
error.jsp
```

The list page must contain:

* Project heading
* Add Product link
* Table containing ID, product name, SKU, category and stock
* Edit action
* Delete form using POST submission
* Empty-state message

The form page must support both creating and editing products and must display validation errors.

Create one CSS file under:

```text
backend/src/main/resources/static/css/style.css
```

## Database

Use MySQL.

Database name:

```text
advanced_java_inventory
```

Create `database/setup.sql` containing:

```sql
CREATE DATABASE IF NOT EXISTS advanced_java_inventory;
```

Configure the backend using:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/advanced_java_inventory
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

`InventoryApplication` must extend `SpringBootServletInitializer`.

`spring-boot-starter-tomcat` must be marked as `provided` for external Tomcat deployment.

Use Jakarta-compatible imports for Spring Boot 3 and Tomcat 10.1.

Build output must be `backend/target/inventory.war`.

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
