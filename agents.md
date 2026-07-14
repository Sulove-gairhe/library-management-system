\# AGENTS.md



\## Project Goal



Build a minimal Advanced Java CRUD course project using:



\* Java 21

\* JavaFX desktop frontend

\* Spring Boot REST backend

\* Spring Data JPA and Hibernate

\* MySQL

\* Maven



\## Repository Structure



\* `backend/` contains the Spring Boot REST application.

\* `frontend/` contains the JavaFX desktop application.

\* `database/` contains database setup scripts.

\* `README.md` contains local setup and run instructions.



\## Mandatory Rules



\* Read `PROJECT\_SPEC.md` before making changes.

\* Implement only the requested phase.

\* Do not add features outside the specification.

\* Do not add authentication.

\* Do not add dashboards or reports.

\* Do not add Docker.

\* Do not add cloud deployment.

\* Do not add pagination.

\* Do not create `module-info.java`.

\* Do not connect JavaFX directly to MySQL.

\* Do not place HTTP logic directly inside JavaFX UI event handlers.

\* Do not create unnecessary interfaces or abstract classes.

\* Do not rewrite working code without a confirmed reason.

\* Use straightforward code suitable for an Advanced Java student project.



\## Verification Rules



After backend changes, run:



```bash

mvn test

mvn package

```



After frontend changes, run:



```bash

mvn compile

```



Fix compilation errors before completing a phase.



At the end of each phase, report:



1\. Files created or modified.

2\. Commands executed.

3\. Whether compilation succeeded.

4\. Manual verification steps.

5\. Any remaining blocker.



\## Definition of Done



The project is complete when:



\* MySQL stores book records.

\* GET returns books.

\* POST creates a book.

\* PUT updates a book.

\* DELETE removes a book.

\* JavaFX displays all books.

\* JavaFX can add, update and delete books.

\* Validation messages appear correctly.

\* The backend and frontend start using documented Maven commands.

\* README contains complete local setup instructions.



Apache Tomcat deployment is optional and must not be implemented unless specifically requested.



