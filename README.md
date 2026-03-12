# 🚀 High-Performance URL Shortener

A professional-grade RESTful API for shortening long URLs, built with the Spring Boot ecosystem. This project demonstrates core backend competencies: Layered Architecture, Database Persistence, and Automated Testing.

## 🛠️ Tech Stack
* **Language:** Java 21
* **Framework:** Spring Boot 3.5.11
* **Database:** PostgreSQL (Local Instance)
* **ORM:** Spring Data JPA (Hibernate)
* **Testing:** JUnit 5, Mockito
* **Build Tool:** Maven

## ✨ Key Features
* **Base62 Encoding:** Converts internal database IDs into short, unique, human-readable keys.
* **Layered Architecture:** Strict separation of concerns between Controller, Service, and Repository layers.
* **Global Error Handling:** Custom `@ControllerAdvice` for graceful 404 and 500 JSON error responses.
* **CSV Reporting:** Endpoint to export all shortened links into a `.csv` file for easy management.
* **Input Validation:** Ensures only correctly formatted URLs are processed using `jakarta.validation`.

## 🚀 Getting Started

### Prerequisites
* **JDK 21** or higher.
* **PostgreSQL** installed and running locally.

### Database Setup
1. Open your PostgreSQL terminal or tool (pgAdmin/DBeaver).
2. Create a new database:
   ```sql
   CREATE DATABASE url_shortener;
   ```
3. Update src/main/resources/application.properties with your local credentials:
   ```
	spring.datasource.url=jdbc:postgresql://localhost:5432/url_shortener
	spring.datasource.username=YOUR_POSTGRES_USER
	spring.datasource.password=YOUR_POSTGRES_PASSWORD
   ```
### Running the Application
1. Clone the repository:
   ```
   git clone https://github.com/Pranesh2288/URLshortner/
   ```
2. Run the Application using maven
   ``` Bash
   ./mvnw spring-boot:run
   ```
### API Endpoints

| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `POST` | `/api/v1/shorten` | Submit a long URL; returns a short key. |
| `GET` | `/{shortKey}` | Redirects to the original long URL. |
| `GET` | `/api/v1/report/csv` | Downloads a CSV of all stored links. |


### Testing
Run the automated test suite to verify logic and repository mocking:
```Bash
./mvnw clean test
```
