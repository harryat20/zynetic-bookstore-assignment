
# 📚 Zynetic Bookstore – Spring Boot REST API

A secure, scalable, and production-grade REST API for managing books and users, built with Spring Boot, PostgreSQL, and JWT authentication.

---

## 🚀 Tech Stack

- **Backend**: Java 21, Spring Boot 3.4
- **Database**: PostgreSQL
- **Security**: Spring Security + JWT
- **ORM**: Spring Data JPA (Hibernate)
- **Docs**: Swagger / OpenAPI 3
- **Testing**: JUnit 5, Mockito, MockMvc
- **Containerization**: Docker, Docker Compose

---

## 🔧 Setup Instructions

### 1️⃣ Clone the Repository

```bash
git clone https://github.com/your-username/zynetic-bookstore-api.git
cd zynetic-bookstore-api
```

### 2️⃣ Start with Docker (Recommended)

```bash
docker-compose up --build
```

This will:
- Start PostgreSQL on port 5432
- Build and run the Spring Boot app on port 8080

### 3️⃣ Manual Setup (Optional)

Update DB credentials in `application.properties` and run:

```bash
mvn spring-boot:run
```

---

## 📘 API Endpoints & Sample Requests

### 🧑‍💻 User Signup (Public)
`POST /signup`
```json
{ "email": "test@example.com", "password": "password123" }
```

### 🔐 User Login (Public)
`POST /login`
```json
{ "email": "test@example.com", "password": "password123" }
```

**Returns:** JWT token

### 📚 Book APIs (Protected)

**Add `Authorization: Bearer <token>` to headers**

- `GET /books` — All books
- `POST /books` — Create book
- `GET /books/{id}` — Get by ID
- `PUT /books/{id}` — Update book
- `DELETE /books/{id}` — Delete book
- `GET /books/search?title=alch` — Search books by title (partial match).
- `GET /books/filter?author=xyz&category=abc&rating=4.5`   — Filter books by one or more criteria: author, category, rating
- `GET /books/paginated?page=0&size=5`  — Returns paginated book results.
- `GET /books/sorted?sortBy=price&direction=desc`    — Returns sorted books by price or rating in asc or desc order.

---

## 🔐 Authorization Header Example

```
Authorization: Bearer <your-token-here>
```

---

## 🧠 Assumptions

- Login requires email & password
- All book routes are JWT-protected
- Rating range: 0.0 - 5.0
- Valid published date format: `yyyy-MM-dd`

---

## 💡 Enhancements Implemented

- ✅ JWT Auth
- ✅ Swagger/OpenAPI Docs
- ✅ Validation + Exception Handling
- ✅ Pagination, Sorting, Filtering
- ✅ Unit + Integration Tests
- ✅ Dockerized

---

## 🧪 Running Tests

```bash
mvn test
```

---

## 📊 Swagger Docs

🔗 [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

---

## 👤 Author

**Harjinder Singh Saini** – Java Backend Developer
