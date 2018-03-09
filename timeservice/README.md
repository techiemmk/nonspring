# Time service

Simple restful web service implementation using jersey.

This service will return the current time for the request.

Steps:
1. Create simple web application using the following maven command
mvn archetype:generate -DgroupId=edu.mmk.web.rest -DartifactId=timeservice -DarchetypeArtifactId=maven-archetype-webapp -DinteractiveMode=false

2. Modify the pom.xml to add the required dependencies.

3. Modify the web.xml to add the rest mapping

4. Add the source code for the restful call

5. Modify the index.jsp to give the welcome message

6. Create the war file by running the following maven command
mvn clean install

7. Deploy the timeservice.war in any of the websever (I did it in Tomcat) and launch the following URL in a browser
http://localhost:8080/timeservice/rest/time