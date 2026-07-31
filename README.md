# Smart Expense Tracker API

A RESTful Expense Tracker API built using **Java 17**, **Spring Boot**, and **Jackson** for the Diligent Software Engineering Apprenticeship Assignment.

The application allows users to manage their personal expenses with data stored in a local JSON file (no database required).

---

## Features

- Add a new expense
- View all expenses
- Filter expenses by category
- Calculate total expenses
- Calculate total expenses by category
- Delete an expense
- Request validation
- JSON file storage
- Swagger/OpenAPI documentation

---

## Tech Stack

- Java 17
- Spring Boot
- Maven
- Jackson
- Lombok
- Spring Validation
- Swagger / OpenAPI

---

## Project Structure

```
ExpenseTracker/
│
├── .mvn/
├── data/
├── src/
├── .gitattributes
├── .gitignore
├── AI_NOTES.md
├── README.md
├── mvnw
├── mvnw.cmd
├── pom.xml
└── ...
```

---

# Prerequisites

Before running the project, ensure the following software is installed.

- Java 17
- Maven 3.9+
- Git

Verify installation:

```bash
java -version
```

```bash
mvn -version
```

---

# Clone the Repository

```bash
git clone <YOUR_GITHUB_REPOSITORY_URL>
```

Move into the project directory.

```bash
cd expensetracker
```

---

# Install Dependencies

Run the following command:

```bash
mvn clean install
```

This command will:

- Download all dependencies
- Compile the project
- Run all tests
- Build the application

---

# Run the Application

Start the Spring Boot application using:

```bash
mvn spring-boot:run
```

The application starts on

```
http://localhost:8080
```

---

# Swagger Documentation

After the application starts, open:

```
http://localhost:8080/swagger-ui/index.html
```

Swagger UI provides interactive documentation for all available REST endpoints.

---

# API Endpoints

## Add Expense

**POST**

```
/api/expenses
```

Example Request

```json
{
  "title": "Groceries",
  "amount": 650,
  "category": "Food",
  "date": "2026-07-31"
}
```

---

## View All Expenses

**GET**

```
/api/expenses
```

---

## Filter Expenses by Category

**GET**

```
/api/expenses?category=Food
```

---

## Calculate Total Expenses

**GET**

```
/api/expenses/summary
```

---

## Calculate Total Expenses by Category

**GET**

```
/api/expenses/summary?category=Food
```

---

## Delete Expense

**DELETE**

```
/api/expenses/{id}
```

Example

```
DELETE /api/expenses/0d5f4a7d-3d16-4a83-9c5b-f3b4c59d2abc
```

---

# Running Tests

Execute all tests using:

```bash
mvn test
```

The project contains unit and integration tests covering:

- Service Layer
- Controller Layer
- Application Context Loading

---

# Data Storage

The application stores data in a local JSON file.

```
data/expenses.json
```

No external database is required.

If the file does not exist, it will be created automatically when the application starts.

---

# Validation

The API validates incoming requests.

Examples include:

- Title cannot be blank
- Amount must be greater than zero
- Category cannot be blank
- Date cannot be null

Invalid requests return HTTP **400 Bad Request**.

---

# HTTP Status Codes

| Status | Description |
|---------|-------------|
| 200 | Success |
| 201 | Expense Created |
| 204 | Expense Deleted |
| 400 | Validation Error |
| 404 | Expense Not Found |

---

# Notes

- Data is persisted using a local JSON file.
- No relational database is used.
- UUIDs are generated automatically for new expenses.
- The project follows a layered architecture (Controller → Service → Repository).
