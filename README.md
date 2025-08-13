# Spring Boot JWT Authentication

A simple Spring Boot project for user registration, login, and JWT-based authentication.

---

## Features

- User registration
- User login
- JWT token generation and validation
- Password encryption using BCrypt
- RESTful API endpoints

---

---

## Technologies Used

- Java 17+
- Spring Boot 3+
- Spring Security
- Spring Data JPA
- H2 / MySQL (any JPA-supported database)
- JWT (io.jsonwebtoken)
- Maven / Gradle

---

## API Endpoints

| Method | Endpoint          | Description                        |
|--------|-----------------|------------------------------------|
| GET    | `/`              | Returns "Hello World!"             |
| POST   | `/register`      | Register a new user                |
| POST   | `/login`         | Login and receive JWT token        |
| POST   | `/verify-token`  | Verify JWT token and get username  |

### Example Requests

**Register**
```json
POST /register
{
  "username": "user1",
  "password": "password123"
}

POST /verify-token
{
  "token": "<your-jwt-token>"
}
```

# JWT secret key
jwt.secret=your-secret-key

# Database configuration (H2 example)
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=update


