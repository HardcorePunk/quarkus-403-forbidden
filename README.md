
When I run the application with `./mvnw quarkus:dev` and open Firefox to point at https://localhost:8000/ then index.html gets loaded and everything works just fine.

Yet, when I do `./mvnw clean install && java -jar target/quarkus-app/quarkus-run.jar` and point Firefox to https://localhost:8000/ I get an HTTP 403 Forbidden response.

What am I doing wrong?
