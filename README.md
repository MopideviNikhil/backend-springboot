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

<details>
<summary>Click to expand</summary>
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

</details>

---

## ⚙️ Configuration

Before running the project, configure your environment by editing `src/main/resources/application.properties`:

```properties
# MySQL Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/agri_rental_db
spring.datasource.username=your_db_username
spring.datasource.password=your_db_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# Mail Configuration (optional)
spring.mail.host=smtp.example.com
spring.mail.port=587
spring.mail.username=your_email@example.com
spring.mail.password=your_email_password
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
```

---

🚀 How to Run the Project

Follow these steps to build and run the project:

1. 📥 Clone the repository
git clone https://github.com/your-username/agri-rental.git
cd agri-rental

2. 📦 Build the project
mvn clean install

3. ▶️ Run the application
mvn spring-boot:run

---

## 📑 API Overview

The project includes over 15 REST endpoints covering:

- Equipment management (CRUD)
- Rentals
- Users
- Categories / types
- Email notifications

View the full API list here:
👉 [Full API Reference](docs/API.md)  
<details>
<summary>📘 Click to view full list of API endpoints</summary>

| Method | Endpoint                    | Description                     |
|--------|-----------------------------|---------------------------------|
| GET    | `/api/equipment`            | List all equipment              |
| POST   | `/api/equipment`            | Add new equipment               |
| GET    | `/api/equipment/{id}`       | Get equipment by ID             |
| PUT    | `/api/equipment/{id}`       | Update equipment                |
| DELETE | `/api/equipment/{id}`       | Delete equipment                |
| GET    | `/api/rentals`              | List all rentals                |
| POST   | `/api/rentals`              | Create a new rental             |
| GET    | `/api/rentals/{id}`         | Get rental details              |
| PUT    | `/api/rentals/{id}`         | Update rental                   |
| DELETE | `/api/rentals/{id}`         | Cancel rental                   |
| POST   | `/api/email/notify`         | Send notification email         |
| GET    | `/api/categories`           | List equipment categories       |
| POST   | `/api/categories`           | Create equipment category       |
| POST   | `/api/users/register`       | Register a new user             |
| POST   | `/api/users/login`          | Login user                      |
| GET    | `/api/equipment/search`     | Search equipment (filter)       |
| ...    | `/...`                      | Additional endpoints as needed  |

</details>



