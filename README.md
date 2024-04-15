# Library Management System API

This project is a Library Management System API built using Spring Boot. It allows librarians to manage books, patrons, and borrowing records efficiently.

## Table of Contents
1. [Project Description](#project-description)
2. [Features](#features)
3. [Technologies Used](#technologies-used)
4. [API Documentation](#api-documentation)
5. [Testing](#testing)
6. [Additional Notes](#additional-notes)

## Project Description

The Library Management System API enables librarians to perform various tasks such as adding, updating, and deleting books and patrons, as well as managing borrowing records. It provides RESTful endpoints for interacting with the system and utilizes a database to persist data.

## Features

- **Book Management**:
  - Retrieve a list of all books
  - Retrieve details of a specific book by ID
  - Add a new book to the library
  - Update an existing book's information
  - Remove a book from the library

- **Patron Management**:
  - Retrieve a list of all patrons
  - Retrieve details of a specific patron by ID
  - Add a new patron to the system
  - Update an existing patron's information
  - Remove a patron from the system

- **Borrowing**:
  - Allow a patron to borrow a book
  - Record the return of a borrowed book by a patron

- **Security**:
  - Implement basic authentication or JWT-based authorization to protect API endpoints

- **Aspects**:
  - Implement logging using Aspect-Oriented Programming (AOP) to log method calls, exceptions, and performance metrics

- **Caching**:
  - Utilize Spring's caching mechanisms to improve system performance

- **Transaction Management**:
  - Ensure data integrity during critical operations using Spring's declarative transaction management

- **Testing**:
  - Write unit tests to validate the functionality of API endpoints

## Technologies Used

- Java
- Spring Boot
- Spring Data JPA
- Maven
- Spring AOP
- Spring Security 
- PostgreSQL Database
- JUnit
- Caching

## API Documentation
- **Swagger Documentation**:
  - Access the Swagger UI to explore and test the API endpoints:
    - URL: `http://localhost:5920/swagger-ui.html`

- **Book Endpoints**:
  - `GET /api/books`
  - `GET /api/books/{id}`
  - `POST /api/books`
  - `PUT /api/books/{id}`
  - `DELETE /api/books/{id}`

- **Patron Endpoints**:
  - `GET /api/patrons`
  - `GET /api/patrons/{id}`
  - `POST /api/patrons`
  - `PUT /api/patrons/{id}`
  - `DELETE /api/patrons/{id}`

- **Borrowing Endpoints**:
  - `POST /api/borrow/{bookId}/patron/{patronId}`
  - `PUT /api/return/{bookId}/patron/{patronId}`

## Testing

- Run unit tests using JUnit and Mockito to ensure the functionality of API endpoints.

## Additional Notes

- Make sure to provide proper input validation for API requests and handle exceptions gracefully.
- Configure database properties in `application.properties` based on your chosen database.
- Documentation on setting up authentication (if implemented) and running tests should be included.
