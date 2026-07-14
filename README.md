# Library Book Manager

This repository contains the Phase 1 Spring Boot REST backend for the Advanced Java CRUD course project. The JavaFX frontend is intentionally not included in Phase 1.

## Required software

- Java 21
- Maven
- MySQL Server and MySQL command-line client

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

## Start the backend

From the repository root:

```text
cd backend
mvn spring-boot:run
```

The API starts at `http://localhost:8080`.

## REST endpoints

| Method | URL | Result |
|---|---|---|
| GET | `/api/books` | Return all books |
| GET | `/api/books/{id}` | Return one book or 404 |
| POST | `/api/books` | Create a book and return 201 |
| PUT | `/api/books/{id}` | Update a book or return 404 |
| DELETE | `/api/books/{id}` | Delete a book and return 204 |

## Postman examples

GET:

- Method: `GET`
- URL: `http://localhost:8080/api/books`

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

Invalid or missing title, author, category, or quantity values receive a `400 Bad Request` response with field validation messages.
