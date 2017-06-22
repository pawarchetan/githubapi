# githubapi
Ginmon Test

Technology Stack :<br />
    Java 8<br />
    Spring Boot<br />
    Spring Data JPA<br />
    Spring Retry<br />
    SQL Server<br />
    lombok (Used only for Logging Purpose)<br />
    log4j<br />

Future Scope:<br />
    More Unit Test Coverage<br />
    Solution to advanced problem<br />



Table Structures :<br />
1. repository_tbl<br />
2. owner_tbl (Will contain login name , avatar URL etc)<br />

How To Run :<br />
1. Create connection to DB and Database<br />
2. update required credentials in application.properties file<br />
3. Clone project from GitHub<br /><br />
4. Run GitHubRestApiApplication.java file (It will create all required tables automatically, for new Strategy for creating
tables is 'create-drop' which will create and drop tables every time when we will restart server)<br />

Endpoints:<br /><br />
1. create and display github repository metadata<br />
 -- It will take data from GitHub API and will import it in SQL Server DB and then we will display it in JSON format.<br />
 URL : http://localhost:8080/github/repositories/owner/:owner/repository/:repository<br />
 Method : POST<br />

2. get github repository metadata by name<br />
 -- display metadata by repository name<br />
 URL : http://localhost:8080/github/repositories/repository/:repository<br />
 Method :  GET<br />

3. get all github repository metadata<br />
 -- display all github repository metadata<br />
 URL : http://localhost:8080/github/repositories<br />
 Method : GET<br />





