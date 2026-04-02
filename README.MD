# Module 9 Project

## Overview
Module 9 Spring Security Project

**Base URL:** `/api/books`  
**Technology:** Spring Boot  
**Security:** Spring Security (Role‑based Authorization)

---
## List of Endpoints
| HTTP | Endpoint | Role |
|---|-----|-------------|
| GET | ?status=ALL/AVAILABLE/BORROWED | `ADMIN` `USER` |
| POST |  /add | `ADMIN` |
| PUT  | /update/{id} | `ADMIN` |
| DELETE  | /{id} | `ADMIN` |
| PUT  | /borrow/{id} | `USER` |
| PUT  | /return/{id} | `USER` |
---  

**Base URL:** `/api/users`  
**Technology:** Spring Boot  
**Security:** Spring Security (Role‑based Authorization)

---
## List of Endpoints
| HTTP | Endpoint | Role |
|---|-----|-------------|
| POST |  /CREATE | `ADMIN` |
---

## Authentication

All endpoints requires authentication.

| Role | Permissions |
|-----|-------------|
| `ADMIN` | Add, update, delete books |
| `USER` | View books, borrow books, return books |
| `ADMIN`, `USER` | View all, available and borrowed books |

---