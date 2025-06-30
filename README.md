# 📚 Digital Library 

A Spring Boot-based backend system to manage a digital library. It supports book cataloging, user management, borrowing, returning, and late fee tracking.

![Java](https://img.shields.io/badge/Java-17-blue.svg)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.0-brightgreen.svg)
![License](https://img.shields.io/badge/License-MIT-lightgrey.svg)
![Status](https://img.shields.io/badge/status-active-success.svg)

---

## 🔧 Technologies Used

- Java 17
- Spring Boot 3.5.0
- Spring Data JPA
- MySQL
- Lombok
- Maven

---

## 📂 Project Structure

```
src/
├── main/
│   ├── java/com/example/
│   │   ├── advice/               # Global exception handling
│   │   ├── api/                  # REST controllers
│   │   ├── dto/                  # Data transfer objects
│   │   ├── entity/               # Entity classes
│   │   ├── exception/            # Custom exceptions
│   │   ├── repo/                 # JPA repositories
│   │   └── service/              # Business logic
│   └── resources/
│       └── application.properties
└── test/
    └── java/com/example/demo     # (Optional) Unit Tests
```

---

## 🚀 Features

- Add/search/update/delete books
- Author management
- Borrow and return books
- Penalty calculation for late returns
- Pagination support for book listings
- Book search by genre, name, or author

---

## 📬 API Endpoints

### 🔹 Book API (`/books`)

- `POST /books/authours` - Add a new author
- `POST /books` - Add a new book
- `GET /books` - Get all books
- `GET /books/{bookId}` - Get book by ID
- `GET /books/search/genre?genre=GENRE` - Search books by genre
- `GET /books/search/authour?authourId=id` - Search books by author
- `GET /books/search/book-name?book-name=name` - Search books by name
- `GET /books/page?pageNo=n&size=m` - Paginated books
- `POST /books/update` - Update book info
- `POST /books/remove/{bookId}` - Delete book

### 🔹 User API (`/users`)

- `POST /users` - Add new user
- `POST /users/borrow` - Borrow a book
- `PUT /users/return/{tid}` - Return book
- `GET /users/stock-check?Id=id` - Check book availability
- `GET /users/transactions?userId=id` - View user transactions

---

## ⚙️ Setup Instructions

1. **Clone the repository**
```bash
git clone https://github.com/Raghavendra1218/Digital_Library.git
cd Digital_Library
```

2. **Configure Database** (MySQL)
Update your `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/digital_library
spring.datasource.username=yourUsername
spring.datasource.password=yourPassword
spring.jpa.hibernate.ddl-auto=update
```

3. **Build and Run**
```bash
./mvnw spring-boot:run
```

---

## ✅ Validation & Error Handling

- Bean validation is used on DTOs and entities
- `@RestControllerAdvice` for global exception handling
- Custom `ApplicationException` for domain errors

---

## 📌 License

This project is licensed under the MIT License.

---

## 🙋‍♂️ Author

Developed by **Raghavendra Velpugonda**.
