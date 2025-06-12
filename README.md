# Task Management API

A simple RESTful API for managing tasks and user authentication built with Spring Boot.

## Table of Contents
- [Overview](#overview)
- [Technologies Used](#technologies-used)
- [Project Structure](#project-structure)
- [Setup and Installation](#setup-and-installation)
- [API Endpoints](#api-endpoints)
- [Usage Examples](#usage-examples)
- [Testing](#testing)
- [Contributing](#contributing)

## Overview

This project is a Spring Boot application that provides a RESTful API for task management and user authentication. It allows users to create, retrieve, update, and delete tasks, as well as authenticate users.

The application uses an in-memory data store for simplicity, making it perfect for learning and demonstration purposes.

## Technologies Used

- Java 21
- Spring Boot 3.5.0
- Spring Web (for RESTful API)
- JUnit 5 (for testing)
- Maven (for dependency management and build)

## Project Structure

```
documenting_project/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── ndungutse/
│   │   │           └── documenting_project/
│   │   │               ├── DocumentingProjectApplication.java (Main application class)
│   │   │               ├── TaskController.java (REST controller for tasks)
│   │   │               ├── TaskModel.java (Task data model)
│   │   │               ├── UserController.java (REST controller for users)
│   │   │               └── UserModel.java (User data model)
│   │   └── resources/
│   │       └── application.properties (Application configuration)
│   └── test/
│       └── java/
│           └── com/
│               └── ndungutse/
│                   └── documenting_project/
│                       ├── DocumentingProjectApplicationTests.java
│                       └── TaskControllerTest.java (Tests for TaskController)
└── pom.xml (Maven configuration)
```

## Setup and Installation

### Prerequisites
- Java Development Kit (JDK) 21 or later
- Maven 3.6 or later

### Steps
1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd documenting_project
   ```

2. Build the project:
   ```bash
   mvn clean install
   ```

3. Run the application:
   ```bash
   mvn spring-boot:run
   ```

The application will start on `http://localhost:8080`.

## API Endpoints

### Task Management

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET    | /api/tasks | Get all tasks |
| GET    | /api/tasks/{title} | Get a task by title |
| PUT    | /api/tasks/{title} | Update a task by title |
| DELETE | /api/tasks/{title} | Delete a task by title |

### User Management

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST   | /api/users/login | Authenticate a user |

## Usage Examples

### Get All Tasks
```bash
curl -X GET http://localhost:8080/api/tasks
```

### Get Task by Title
```bash
curl -X GET http://localhost:8080/api/tasks/Learn%20Docker
```

### Update Task
```bash
curl -X PUT http://localhost:8080/api/tasks/Learn%20Docker \
  -H "Content-Type: application/json" \
  -d '{"title":"Learn Docker", "description":"Study Docker and Kubernetes"}'
```

### Delete Task
```bash
curl -X DELETE http://localhost:8080/api/tasks/Learn%20Docker
```

### User Login
```bash
curl -X POST http://localhost:8080/api/users/login \
  -H "Content-Type: application/json" \
  -d '{"username":"admin", "password":"admin123"}'
```

## Testing

The project includes integration tests for the TaskController. To run the tests:

```bash
mvn test
```

The tests verify:
- Getting tasks by title (both existing and non-existent tasks)
- Deleting tasks by title (including verification of deletion)

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request