# Flashcards-service
Flashcards are small note cards used for testing and improving memory through practiced information retrieval. This flashcard backend service is implemented using spring boot. The REST endpoints are secured by using spring security and JWT.

# Documentation
Access Swagger at http://localhost:8080/swagger-ui.html#/

To Run APIs follow below steps

Application comes with pre configured admin user with credentials (username: shreehari, password:shreehari)
1. Using postman or any other tool perform Authentication to get Access token and Refresh token

   http://localhost:8080/login
   
   Provide username and password as body parameters.
   
   In response headers you can find Access token and refresh token.

2. Use the Access token in the Swagger UI to test other APIs.

   Add value in the format "Bearer $tokenvalue" to perform authorization.
   
   Test other APIs.

