# basic-restful-web-service
This is a small project on RESTful web service with Spring Boot

# REST
- Representational State Transfer
- Created by Roy Fielding
- It's an architectural approach
- Makes best use of HTTP - HTTP request methods (GET, POST, DELETE, etc.), HTTP status codes (200, 400, 404, etc.)
- Data Exchange Format: No restriction but JSON is popular
- Transport: only HTTP
- Service Definition: No standard but WADL/ Swagger is used mostly.
- It's a style of how we design our resources and exposing them using HTTP
- A resource is something that we'd like to expose to outside world through our application. 
- Each resource has a URI. 
- e.g. To get a particular post of a user = GET request to /users/name/posts/1
- A resource can have different representations - XML, JSON, HTML

The project consists of implementation of some advanced concepts
- Versioning
- Filtering
- Monitoring
- Content Negotiation
- Internationalization
- Swagger
- HATEOAS
- Exception Handling
- Validation

Tools / frameworks used
- Spring Boot
- JPA, H2 database
- Maven
- Postman
