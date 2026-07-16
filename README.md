# Inventory Product Manager

This repository contains a Spring Boot Inventory Product Manager with:

- REST CRUD endpoints for Postman testing
- Server-rendered JSP pages as the only frontend
- Spring Data JPA and Hibernate persistence with MySQL
- WAR packaging for external Apache Tomcat 10.1 deployment

## Final architecture

Browser users work through JSP pages:

```text
Browser -> JSP/HTML/CSS -> ProductWebController -> ProductService -> ProductRepository -> MySQL
```

Postman or another HTTP client works through the REST API:

```text
Postman -> ProductRestController -> ProductService -> ProductRepository -> MySQL
```

## Required software

- Java 21
- Maven
- MySQL Server and MySQL command-line client
- Apache Tomcat 10.1

## Database setup

From the repository root, run this PowerShell command and enter the MySQL root password when prompted:

```powershell
Get-Content .\database\setup.sql | mysql -u root -p
```

In Command Prompt, use:

```bat
mysql -u root -p < database\setup.sql
```

The script creates the `advanced_java_inventory` database. Spring Boot creates or updates the `products` table on startup.

The backend connects to:

```text
jdbc:mysql://localhost:3306/advanced_java_inventory
```

Product records are stored in MySQL and remain available after the application restarts.

## Database environment variables

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

- `http://localhost:8080/products`
- `http://localhost:8080/products/new`

The JSP pages are the only frontend. JavaScript is not required for CRUD.

## Maven test command

From the `backend` directory:

```text
mvn clean test
```

## Maven package command

From the `backend` directory:

```text
mvn clean package
```

## WAR output path

The Maven build creates:

```text
backend/target/inventory.war
```

## Tomcat deployment steps

1. Install Java 21, Maven, MySQL, and Apache Tomcat 10.1.
2. Create the database using `database/setup.sql`.
3. Set `DB_USERNAME` and `DB_PASSWORD` in the environment used to start Tomcat.
4. Run `mvn clean package` from the `backend` directory.
5. Stop Tomcat if it is already running.
6. Delete any old `inventory.war` file and expanded `inventory` folder from Tomcat's `webapps` directory.
7. Copy `backend/target/inventory.war` into Tomcat's `webapps` directory.
8. Start Tomcat with `bin/startup.bat` on Windows or `bin/startup.sh` on macOS/Linux.
9. Open `http://localhost:8080/inventory/products`.

Deployed Tomcat URLs:

- Browser: `http://localhost:8080/inventory/products`
- REST: `http://localhost:8080/inventory/api/products`

## Browser routes

| Method | URL | Result |
|---|---|---|
| GET | `/products` | Show the product list |
| GET | `/products/new` | Show the create form |
| POST | `/products` | Create a product |
| GET | `/products/{id}/edit` | Show the edit form |
| POST | `/products/{id}/update` | Update a product |
| POST | `/products/{id}/delete` | Delete a product |

## REST routes

| Method | URL | Result |
|---|---|---|
| GET | `/api/products` | Return all products |
| GET | `/api/products/{id}` | Return one product or 404 |
| POST | `/api/products` | Create a product and return 201 |
| PUT | `/api/products/{id}` | Update a product or return 404 |
| DELETE | `/api/products/{id}` | Delete a product and return 204 |

## Postman examples

GET all:

- Method: `GET`
- URL: `http://localhost:8080/api/products`

GET by ID:

- Method: `GET`
- URL: `http://localhost:8080/api/products/1`

POST:

- Method: `POST`
- URL: `http://localhost:8080/api/products`
- Header: `Content-Type: application/json`
- Body: `raw` JSON

```json
{
  "name": "USB Keyboard",
  "sku": "KB-100",
  "category": "Accessories",
  "stock": 12
}
```

PUT:

- Method: `PUT`
- URL: `http://localhost:8080/api/products/1`
- Header: `Content-Type: application/json`
- Body: `raw` JSON

```json
{
  "name": "USB Keyboard",
  "sku": "KB-100",
  "category": "Accessories",
  "stock": 15
}
```

DELETE:

- Method: `DELETE`
- URL: `http://localhost:8080/api/products/1`

Invalid or missing name, SKU, category, or stock values receive a `400 Bad Request` response with field validation messages. Duplicate SKU values receive a `409 Conflict` response.

When deployed to Tomcat as `inventory.war`, use these URLs:

- Browser: `http://localhost:8080/inventory/products`
- REST: `http://localhost:8080/inventory/api/products`

## JSP CRUD QA checklist

1. Open `http://localhost:8080/inventory/products`.
2. Confirm the list page loads and shows either products or the empty-state message.
3. Click Add Product and confirm `/inventory/products/new` opens an empty form.
4. Submit blank fields and confirm validation messages appear.
5. Enter a negative stock value and confirm the stock validation message appears.
6. Create a valid product and confirm the app redirects to `/inventory/products`.
7. Click Edit and confirm the existing values load in the form.
8. Update the product and confirm the list shows the changed values.
9. Delete the product and confirm it is removed from the list.

For local Maven runs without the Tomcat context path, use `http://localhost:8080/products`.

## REST CRUD QA checklist

1. Send `POST /api/products` with valid JSON and confirm status `201`.
2. Send `GET /api/products` and confirm the created product appears.
3. Send `GET /api/products/{id}` and confirm status `200`.
4. Send `PUT /api/products/{id}` with changed values and confirm status `200`.
5. Send `DELETE /api/products/{id}` and confirm status `204`.
6. Send `GET /api/products/{id}` again and confirm status `404`.

For deployed Tomcat testing, prefix REST paths with `/inventory`.

## Duplicate SKU test

1. Create a product with SKU `KB-100`.
2. Try to create a second product with SKU `KB-100`.
3. Confirm the JSP form shows a visible SKU duplicate error.
4. Send a REST `POST /api/products` request with the same SKU.
5. Confirm the REST response status is `409 Conflict`.
6. Create two products with different SKUs, then try to update one product to the other product's SKU.
7. Confirm the update is rejected with the same duplicate SKU behavior.

## Persistence restart test

1. Create a product through the JSP pages or REST API.
2. Stop the backend or Tomcat.
3. Start the backend or Tomcat again.
4. Open the product list or send `GET /api/products`.
5. Confirm the product is still present from MySQL.

## Stop and redeploy instructions

1. Stop Tomcat with `bin/shutdown.bat` on Windows or `bin/shutdown.sh` on macOS/Linux.
2. Remove the old `webapps/inventory.war` file.
3. Remove the old expanded `webapps/inventory` directory.
4. Rebuild the project with `mvn clean package`.
5. Copy `backend/target/inventory.war` into `webapps`.
6. Start Tomcat again.
