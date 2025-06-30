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

## 📌 Data Transfer Objects (DTOs)

### 📘 BookDTO

```json
{
  "bookId": 1,
  "bookName": "Clean Code",
  "publishedDate": "01-01-2023",
  "cost": 500.0,
  "stock": 10,
  "genre": "PROGRAMMING",
  "authId": 2
}
```

> 🔄 Used for both adding and updating book records.

### 📗 BorrowBookDTO

```json
{
  "bookId": 7,
  "userId": 3
}
```

> 🧾 Required for borrowing a book. Both fields are mandatory.

---

## 📋 Data Model (Entities)

### 🧑 Authour

| Field        | Type    | Constraints                          |
|--------------|---------|--------------------------------------|
| authourId    | int     | Primary Key                          |
| authourName  | String  | Required, not blank or null          |

### 📘 Book

| Field           | Type         | Constraints                                     |
|----------------|--------------|-------------------------------------------------|
| bookId         | int          | Primary Key                                     |
| bookName       | String       | Required, not blank                             |
| publishedDate  | LocalDate    | Format: `dd-MM-yyyy`, must be today or earlier  |
| cost           | float        | Must be ≥ 0                                     |
| stock          | int          | Must be ≥ 0                                     |
| authourId      | Authour      | Linked via foreign key                          |
| genre          | Enum (Genre) | Required, stored as string                      |

### 👤 User

| Field      | Type    | Constraints                     |
|------------|---------|---------------------------------|
| userId     | int     | Primary key                     |
| firstName  | String  | Optional                        |
| lastName   | String  | Optional                        |
| email      | String  | Must be valid and unique        |
| phoneNo    | long    | Must be unique                  |

### 🔄 Transaction

| Field         | Type        | Description                                           |
|---------------|-------------|-------------------------------------------------------|
| transactionId | int         | Primary key, auto-generated                          |
| borrowedDate  | LocalDate   | Automatically set when borrowed                      |
| returnedDate  | LocalDate   | Set when returned                                    |
| user          | User        | Borrower                                             |
| book          | Book        | Linked book                                          |
| amount        | float       | Total cost (base + penalty, if any)                  |
| status        | String      | `"Borrowed"` or `"Returned"`                        |
| penaltyId     | Penalty     | Linked penalty if applicable                         |

### 💸 Penalty

| Field       | Type   | Description                         |
|-------------|--------|-------------------------------------|
| penaltyId   | int    | Auto-generated primary key          |
| noOfDays    | int    | Days overdue                        |
| amount      | float  | Fine amount (₹30 per day)           |
| remarks     | String | Additional comments or status       |

---

## 🎭 Genre Enum

These values are valid for the `genre` field in `Book`:

```
FANTACY
COMEDY
HORROR
ROMANCE
SCIENCE
ACTION
HISTORY
THRILLER
BIOGRAPHY
```

> ⚠️ Use uppercase as-is. Typo note: “FANTACY” may be intended as “FANTASY”.

---

## 🛡️ Global Error Handling

All exceptions are handled using a centralized `@RestControllerAdvice`. Example:

```json
{
  "error": "Book not found with ID 5"
}
```

---

## 🧠 Service Layer

The application uses service interfaces and implementations to manage business logic.

### 📘 BookServiceImpl

- Add/search/update/delete books
- Handle pagination and sorting
- Search by genre, author, or name
- Validate entity existence

### 👤 UserServiceImpl

- Register users
- Borrow/return logic with:
  - Stock check
  - Penalty after 30 days (₹30/day)
- Updates transaction and book records

---

## 🔧 Build & Run Instructions

### ✅ Prerequisites

- Java 17+
- Maven
- MySQL
- IDE (IntelliJ / Eclipse)

### 🗃️ MySQL Setup

```sql
CREATE DATABASE digital_library;
```

Edit `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/digital_library
spring.datasource.username=yourUsername
spring.datasource.password=yourPassword
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### 🚀 Run the App

Via Maven:

```bash
mvn spring-boot:run
```

Or via IDE: Run `DigitalLibraryV11Application.java`

---

## 📝 Author

**Raghavendra Velpugonda**  
🔗 [GitHub Profile](https://github.com/Raghavendra1218)

---

## 📄 License

This project is licensed under the MIT License.
