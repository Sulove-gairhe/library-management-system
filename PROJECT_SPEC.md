\# Library Book Manager — Minimum Project Specification



\## Objective



Build a working desktop CRUD application for managing library books.



The application must demonstrate:



\* Java classes and objects

\* Encapsulation

\* Exception handling

\* JavaFX layouts, controls and events

\* HTTP REST communication

\* GET, POST, PUT and DELETE requests

\* MySQL database connectivity

\* Spring Boot

\* Spring Data JPA

\* Hibernate



\## Architecture



```text

JavaFX Frontend

&#x20;     |

&#x20;     | HTTP and JSON

&#x20;     v

Spring Boot REST API

&#x20;     |

&#x20;     | JPA and Hibernate

&#x20;     v

MySQL Database

```



The JavaFX application must never connect directly to MySQL.



\## Technology



\* Java 21

\* Maven

\* JavaFX

\* JavaFX CSS

\* Java HttpClient

\* Jackson Databind

\* Spring Boot

\* Spring Web

\* Spring Data JPA

\* Hibernate

\* MySQL Connector/J



Use a stable Spring Boot version compatible with Java 21.



Do not create `module-info.java`.



\## Project Structure



```text

advanced-java-library/

├── backend/

├── frontend/

├── database/

│   └── setup.sql

├── AGENTS.md

├── PROJECT\_SPEC.md

└── README.md

```



The backend and frontend must be separate Maven projects.



\## Book Entity



Create a `Book` entity with:



```text

id       Long, auto-generated

title    String, required

author   String, required

category String, required

quantity Integer, required and zero or greater

```



Map the entity to a database table named `books`.



\## Backend Classes



Create only the essential classes:



```text

Book

BookRepository

BookService

BookController

BackendApplication

```



A separate service interface and implementation are not required.



\## REST Endpoints



\### Get all books



```http

GET /api/books

```



Response:



```json

\[

&#x20; {

&#x20;   "id": 1,

&#x20;   "title": "Effective Java",

&#x20;   "author": "Joshua Bloch",

&#x20;   "category": "Programming",

&#x20;   "quantity": 4

&#x20; }

]

```



\### Get one book



```http

GET /api/books/{id}

```



Return HTTP 404 when the book does not exist.



\### Create book



```http

POST /api/books

Content-Type: application/json

```



Request:



```json

{

&#x20; "title": "Effective Java",

&#x20; "author": "Joshua Bloch",

&#x20; "category": "Programming",

&#x20; "quantity": 4

}

```



Return HTTP 201 when successful.



\### Update book



```http

PUT /api/books/{id}

Content-Type: application/json

```



Return HTTP 200 when successful and HTTP 404 when the book does not exist.



\### Delete book



```http

DELETE /api/books/{id}

```



Return HTTP 204 when successful and HTTP 404 when the book does not exist.



\## Backend Validation



Reject a request when:



\* Title is blank.

\* Author is blank.

\* Category is blank.

\* Quantity is null.

\* Quantity is less than zero.



Use simple, understandable validation responses.



\## Database



Use MySQL.



Database name:



```text

advanced\_java\_library

```



Create `database/setup.sql` containing:



```sql

CREATE DATABASE IF NOT EXISTS advanced\_java\_library;

```



Configure the backend using:



```properties

spring.datasource.url=jdbc:mysql://localhost:3306/advanced\_java\_library

spring.datasource.username=${DB\_USERNAME:root}

spring.datasource.password=${DB\_PASSWORD:}

spring.jpa.hibernate.ddl-auto=update

spring.jpa.show-sql=true

```



Do not hardcode a private password.



\## JavaFX Frontend



Build one JavaFX window.



Use Java code for the layout. FXML is not required.



Use a `BorderPane` or similar standard JavaFX layout.



The screen must contain:



\### Header



\* Application name: Library Book Manager

\* Small subtitle: Advanced Java CRUD Project



\### Book form



\* Title TextField

\* Author TextField

\* Category TextField

\* Quantity TextField

\* Add button

\* Update button

\* Clear button



\### Book table



Use a JavaFX `TableView` with columns:



\* ID

\* Title

\* Author

\* Category

\* Quantity



\### Table controls



\* Refresh button

\* Delete button

\* Status label



When a table row is selected, populate the form with that book's values.



\## Frontend Behaviour



\### Read



When the application starts:



1\. Send `GET /api/books`.

2\. Deserialize the JSON response.

3\. Display the records in the TableView.



\### Create



When Add is clicked:



1\. Validate the fields.

2\. Send `POST /api/books`.

3\. Refresh the table.

4\. Clear the form.

5\. Show a success message.



\### Update



When Update is clicked:



1\. Require a selected book.

2\. Validate the fields.

3\. Send `PUT /api/books/{id}`.

4\. Refresh the table.

5\. Clear the form.

6\. Show a success message.



\### Delete



When Delete is clicked:



1\. Require a selected book.

2\. Show a confirmation dialog.

3\. Send `DELETE /api/books/{id}`.

4\. Refresh the table.

5\. Show a success message.



\## Frontend API Classes



Create:



```text

Book

BookApiClient

MainApplication

```



Additional small UI helper classes may be created only when necessary.



`BookApiClient` must contain all HTTP communication.



Use Java's `HttpClient`.



Use Jackson for JSON serialization and deserialization.



\## Styling



Create one JavaFX CSS file.



Use:



\* White or light-gray background.

\* Dark text.

\* One restrained accent color.

\* Consistent spacing.

\* Clear input labels.

\* Simple buttons.

\* A readable TableView.

\* Small corner radiuses.



Do not use:



\* Gradients.

\* Glassmorphism.

\* Excessive animations.

\* Oversized dashboard cards.

\* Artificial AI-style glowing designs.



\## Error Handling



Display an alert when:



\* Backend cannot be reached.

\* Database request fails.

\* A required field is empty.

\* Quantity is not a valid integer.

\* Quantity is negative.

\* No table row is selected.

\* A requested book does not exist.



The program must not crash for normal user errors.



\## README



Document:



1\. Required software.

2\. How to create the MySQL database.

3\. How to set the database password.

4\. How to run the backend.

5\. How to run the frontend.

6\. REST endpoints.

7\. Example GET request.

8\. Example POST request.

9\. How to test using Postman.

10\. Optional Apache Tomcat deployment note.



\## Excluded Features



Do not implement:



\* Authentication

\* User roles

\* Dashboard

\* Reports

\* Search

\* Pagination

\* Images

\* File uploads

\* Email

\* Notifications

\* API activity viewer

\* Docker

\* Cloud deployment

\* Apache Tomcat deployment

\* Multiple database tables

\* Advanced design patterns

\* Unrequested features



