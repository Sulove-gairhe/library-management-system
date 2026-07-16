# AGENTS.md

## Project Goal

Build a minimal Advanced Java CRUD course project using:

* Java 21
* Spring Boot MVC and REST
* JSP, JSTL, HTML and CSS
* Spring Data JPA and Hibernate
* MySQL
* Maven
* External Apache Tomcat 10.1 WAR deployment

The application is an Inventory Product Manager.

## Repository Structure

* `backend/` contains the Spring Boot MVC and REST application.
* `database/` contains database setup scripts.
* `README.md` contains local setup, run and deployment instructions.

## Mandatory Rules

* Read `PROJECT_SPEC.md` before making changes.
* Implement only the requested phase.
* Do not add features outside the specification.
* Do not add authentication.
* Do not add dashboards or reports.
* Do not add pagination.
* Do not create `module-info.java`.
* Do not create another frontend.
* Do not add client-side scripting.
* Do not create unnecessary interfaces or abstract classes.
* Do not rewrite working code without a confirmed reason.
* Use straightforward code suitable for an Advanced Java student project.

## Verification Rules

After backend changes, run:

```bash
mvn test
mvn package
```

For WAR verification, run:

```bash
mvn clean test
mvn clean package
```

Fix compilation errors before completing a phase.

At the end of each phase, report:

1. Files created, modified or deleted.
2. Commands executed.
3. Whether compilation succeeded.
4. Manual verification steps.
5. Any remaining blocker.

## Definition of Done

The project is complete when:

* MySQL stores product records.
* Browser users can list, create, edit and delete products through JSP pages.
* GET returns products through the REST API.
* POST creates a product through the REST API.
* PUT updates a product through the REST API.
* DELETE removes a product through the REST API.
* Validation messages appear correctly.
* Duplicate SKU values are rejected.
* The backend starts using documented Maven commands.
* The WAR can be generated with Maven.
* README contains complete local setup and external Tomcat instructions.
