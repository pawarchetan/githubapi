# githubapi
Ginmon Test

Technology Stack :
Java 8
Spring Boot
Spring Data JPA
Spring Retry
SQL Server
lombok (Used only for Logging Purpose)
log4j

Future Scope:
More Unit Test Coverage
Solution to advanced problem

Package Structure :

src
--- main
    --- java
        --- com.ginmon.api
            --- configuration
            --- controllers
            --- domains
            --- exceptions
            --- repositories
            --- services
            --- util
    --- resources
--- test
    --- java
        --- com.ginmin.api
            --- controllers

Table Structures :
1. repository_tbl
2. owner_tbl (Will contain login name , avatar URL etc)

How To Run :
1. Create connection to DB and Database
2. update required credentials in application.properties file
3. Clone project from GitHub
4. Run GitHubRestApiApplication.java file (It will create all required tables automatically, for new Strategy for creating
tables is 'create-drop' which will create and drop tables every time when we will restart server)

Endpoints:
1. create and display github repository metadata
 -- It will take data from GitHub API and will import it in SQL Server DB and then we will display it in JSON format.
 URL : http://localhost:8080/github/repositories/owner/:owner/repository/:repository
 Method : POST

2. get github repository metadata by name
 -- display metadata by repository name
 URL : http://localhost:8080/github/repositories/repository/:repository
 Method :  GET

3. get all github repository metadata
 -- display all github repository metadata
 URL : http://localhost:8080/github/repositories
 Method : GET





