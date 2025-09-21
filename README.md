# 🐦 Tweet + OAuth2

This project demonstrates a **Spring Boot REST API** with user authentication and authorization using **JWT (JSON Web Token)** and **OAuth2 principles**.  
It includes user management, secured endpoints, and role-based access (e.g., admin).

---

## ✨ Features

- User registration
- JWT-based authentication
- Role-based access control (e.g., Admin vs. Regular User)
- Tweet feed with pagination
- Secure login flow with token expiration
- Dockerized environment for easy setup

---

## 🛠 Tech Stack

- **Java 17+**
- **Maven**
- **Spring Boot** (Web, JPA, Security)
- **MySQL**
- **Docker**

---

## 🚀 Getting Started

### ⚙️ Prerequisites
- Java 17 or later
- Maven 3.8+
- Docker (optional but recommended)

### - Clone the Repository
```bash
  git clone https://github.com/your-username/tweet-oauth2.git
  cd tweet-oauth2
```
### - Run with Docker

```bash
  Run with Docker
  docker-compose up --build
```

## 🚪 API Endpoints

### 1. Create User (Public)

**POST** `/user`

Request:

```json
{
  "name": "Test",
  "password": "12345"
}
```

---

### 2. Login (Generate JWT Token)

**POST** `/login`

Request:

```json
{
  "name": "Test",
  "password": "12345"
}
```

Response:

```json
{
  "token": "eyJhbGciOiJSUzI1NiJ9...",
  "expiresIn": 400
}
```

---

### 3. Get All Users (Admin only)

**GET** `/user`

Requires header:

```
Authorization: Bearer <token>
```

---

### 4. Get Tweets with Pagination

**GET** `/feed?page=0&size=1`

Example response:

```json
[
  {
    "id": 1,
    "author": "Test",
    "content": "Hello world!",
    "createdAt": "2025-09-20T12:34:56Z"
  }
]
```
