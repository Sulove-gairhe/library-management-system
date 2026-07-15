# Library Book Manager

This repository contains a Spring Boot library book manager with:

- REST CRUD endpoints for Postman testing
- Server-rendered JSP pages as the only frontend
- Spring Data JPA and Hibernate persistence with MySQL
- WAR packaging for external Apache Tomcat 10.1 deployment

## Required software

- Java 21
- Maven
- MySQL Server and MySQL command-line client
- Apache Tomcat 10.1

## Create the MySQL database

From the repository root, run this PowerShell command and enter the MySQL root password when prompted:

```powershell
Get-Content .\database\setup.sql | mysql -u root -p
```

In Command Prompt, use:

```bat
mysql -u root -p < database\setup.sql
```

The script creates the `advanced_java_library` database. Spring Boot creates or updates the `books` table on startup.

The backend connects to:

```text
jdbc:mysql://localhost:3306/advanced_java_library
```

Book records are stored in MySQL and remain available after the application restarts.

## Configure the database password

The backend defaults to MySQL user `root` with an empty password. For a normal MySQL password, set these environment variables before starting the backend.

PowerShell:

```powershell
$env:DB_USERNAME="root"
$env:DB_PASSWORD="your_mysql_password"
```

Command Prompt:

```bat
set DB_USERNAME=root
set DB_PASSWORD=your_mysql_password
```

## Start locally with Maven

From the repository root:

```text
cd backend
mvn spring-boot:run
```

The application starts at `http://localhost:8080`.

Browser pages:

- `http://localhost:8080/books`
- `http://localhost:8080/books/new`

The JSP pages are the only frontend. JavaScript is not required for CRUD.

## JSP browser routes

| Method | URL | Result |
|---|---|---|
| GET | `/books` | Show the book list |
| GET | `/books/new` | Show the create form |
| POST | `/books` | Create a book |
| GET | `/books/{id}/edit` | Show the edit form |
| POST | `/books/{id}/update` | Update a book |
| POST | `/books/{id}/delete` | Delete a book |

## REST endpoints

| Method | URL | Result |
|---|---|---|
| GET | `/api/books` | Return all books |
| GET | `/api/books/{id}` | Return one book or 404 |
| POST | `/api/books` | Create a book and return 201 |
| PUT | `/api/books/{id}` | Update a book or return 404 |
| DELETE | `/api/books/{id}` | Delete a book and return 204 |

## Postman CRUD examples

GET:

- Method: `GET`
- URL: `http://localhost:8080/api/books`

GET by ID:

- Method: `GET`
- URL: `http://localhost:8080/api/books/1`

POST:

- Method: `POST`
- URL: `http://localhost:8080/api/books`
- Header: `Content-Type: application/json`
- Body: `raw` JSON

```json
{
  "title": "Effective Java",
  "author": "Joshua Bloch",
  "category": "Programming",
  "quantity": 4
}
```

PUT:

- Method: `PUT`
- URL: `http://localhost:8080/api/books/1`
- Header: `Content-Type: application/json`
- Body: `raw` JSON

```json
{
  "title": "Effective Java",
  "author": "Joshua Bloch",
  "category": "Programming",
  "quantity": 5
}
```

DELETE:

- Method: `DELETE`
- URL: `http://localhost:8080/api/books/1`

Invalid or missing title, author, category, or quantity values receive a `400 Bad Request` response with field validation messages.

When deployed to Tomcat as `library.war`, use these URLs:

- Browser: `http://localhost:8080/library/books`
- REST: `http://localhost:8080/library/api/books`

## Verify with Maven

From the `backend` directory:

```text
mvn clean test
mvn clean package
```

## Build the WAR file

From the `backend` directory:

```text
mvn clean package
```

The WAR file is created in `backend/target`.

## Deploy to external Apache Tomcat 10.1

1. Install Java 21, Maven, MySQL, and Apache Tomcat 10.1.
2. Create the database using `database/setup.sql`.
3. Set `DB_USERNAME` and `DB_PASSWORD` in the environment used to start Tomcat.
4. Run `mvn clean package` from the `backend` directory.
5. Stop Tomcat if it is already running.
6. Delete any old `library.war` file and expanded `library` folder from Tomcat's `webapps` directory.
7. Copy `backend/target/library-backend-0.0.1-SNAPSHOT.war` into Tomcat's `webapps` directory as `library.war`.
8. Start Tomcat with `bin/startup.bat` on Windows or `bin/startup.sh` on macOS/Linux.
9. Open `http://localhost:8080/library/books`.

If Tomcat startup fails because Hibernate tries to modify an existing schema, temporarily change `spring.jpa.hibernate.ddl-auto=update` to `spring.jpa.hibernate.ddl-auto=none` in `backend/src/main/resources/application.properties`, rebuild the WAR, and deploy again.

## Stop and redeploy

1. Stop Tomcat with `bin/shutdown.bat` on Windows or `bin/shutdown.sh` on macOS/Linux.
2. Remove the old `webapps/library.war` file.
3. Remove the old expanded `webapps/library` directory.
4. Rebuild the project with `mvn clean package`.
5. Copy the new WAR into `webapps` as `library.war`.
6. Start Tomcat again.

## Manual JSP CRUD checklist

1. Open `http://localhost:8080/library/books`.
2. Confirm the list page loads and shows either books or the empty-state message.
3. Click Add Book and confirm `/library/books/new` opens an empty form.
4. Submit blank fields and confirm validation messages appear.
5. Create a valid book and confirm the app redirects to `/library/books`.
6. Click Edit and confirm the existing values load in the form.
7. Update the book and confirm the list shows the changed values.
8. Delete the book and confirm it is removed from the list.
9. Use Postman against `/library/api/books` and confirm REST data matches the browser data.
