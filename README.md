# 🚜 Agricultural Equipment Rental System

This is a Spring Boot-based REST API backend for managing the rental of agricultural equipment. The system enables equipment owners to list their tools and users to browse, rent, and manage them through RESTful endpoints.

---

## ✅ Features

- CRUD operations for agricultural equipment and rentals
- Equipment availability management
- User-friendly DTO structure for clean API contracts
- Custom input validation with annotations
- Email notifications using Spring Mail
- Global exception handling
- Enum-based status/roles for clarity
- MySQL database support

---

## 🛠️ Tech Stack

- **Java 17**
- **Spring Boot 3.5.4**
- **Spring Web**
- **Spring Data JPA**
- **MySQL**
- **Lombok**
- **Spring Boot Validation**
- **Spring Mail**
- **Maven**

---

## 📂 Project Structure

src/
└── main/
    ├── java/
    │   └── com/
    │       └── rent/
    │           └── agri/
    │               ├── controller/              # REST API controllers
    │               ├── exception/               # Custom exceptions and global handler
    │               ├── model/
    │               │   ├── dto/                 # DTOs for API requests and responses
    │               │   ├── entity/              # JPA entities mapped to DB
    │               │   └── enums/               # Enum types (e.g., status, roles)
    │               ├── repository/              # Spring Data JPA repositories
    │               ├── service/                 # Service interfaces
    │               │   └── impl/                # Service implementations
    │               ├── util/                    # Utility/helper classes
    │               ├── validation/              # Custom validation logic
    │               │   └── annotation/          # Custom validation annotations
    │               └── AgriApplication.java     # Main Spring Boot application class
    └── resources/
        ├── application.properties               # App configuration (DB, mail, etc.)
        ├── static/                              # Static assets (if used)
        └── templates/                           # Template files (if using Thymeleaf)

