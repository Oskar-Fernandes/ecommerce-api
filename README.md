# 🛒 Ecommerce API

A RESTful API for a simplified e-commerce platform built with Java 17, Spring Boot 3, PostgreSQL, and Docker. This project demonstrates domain modeling, relational database relationships, and business logic implementation using modern Java backend technologies.

## 📌 Features

- User registration with automatic cart creation
- Product and category management (CRUD)
- Shopping cart with add/remove items
- Order checkout with automatic total calculation
- Fake payment processing with automatic approval
- Relational data modeling with JPA/Hibernate

## 🛠️ Tech Stack

| Technology | Version |
|------------|---------|
| Java | 17 |
| Spring Boot | 3.5.11 |
| Spring Data JPA | - |
| Hibernate | 6.x |
| PostgreSQL | 16 |
| Docker | - |
| Lombok | - |
| Maven | 3.x |

## 📦 Project Structure
```
src/main/java/com/oskar/ecommerce_api/
├── controller/       # REST endpoints
├── entity/           # JPA entities (database tables)
├── enums/            # OrderStatus, PaymentStatus
├── exception/        # Error handling
├── repository/       # Data access layer
└── service/          # Business logic
```

## 🗄️ Database Schema

- **users** — registered users
- **carts** — one cart per user (created automatically)
- **cart_items** — products inside a cart
- **categories** — product categories
- **products** — product catalog
- **orders** — placed orders
- **order_items** — products inside an order (with price snapshot)
- **payments** — payment record per order

## 🚀 Getting Started

### Prerequisites

- Java 17
- Docker

### Run the database
```bash
docker compose up postgres -d
```

### Run the application
```bash
./mvnw spring-boot:run
```

The API will be available at `http://localhost:8080`

## 📡 API Endpoints

### Users
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | /api/users | List all users |
| GET | /api/users/{id} | Get user by ID |
| POST | /api/users | Create user |
| DELETE | /api/users/{id} | Delete user |

### Categories
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | /api/categories | List all categories |
| GET | /api/categories/{id} | Get category by ID |
| POST | /api/categories | Create category |
| DELETE | /api/categories/{id} | Delete category |

### Products
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | /api/products | List all products |
| GET | /api/products/{id} | Get product by ID |
| POST | /api/products | Create product |
| PUT | /api/products/{id} | Update product |
| DELETE | /api/products/{id} | Delete product |

### Cart
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | /api/cart/{userId} | Get cart by user |
| POST | /api/cart/{userId}/items?productId=&quantity= | Add item to cart |
| DELETE | /api/cart/{userId}/items/{productId} | Remove item from cart |

### Orders
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | /api/orders/checkout/{userId} | Checkout and place order |
| GET | /api/orders/user/{userId} | List orders by user |

## 📋 Usage Example

### 1. Create a user
```json
POST /api/users
{
    "name": "John Doe",
    "email": "john@email.com"
}
```

### 2. Create a category
```json
POST /api/categories
{
    "name": "Electronics"
}
```

### 3. Create a product
```json
POST /api/products
{
    "name": "Laptop",
    "description": "Gaming laptop",
    "price": 1500.00,
    "stock": 10,
    "category": {"id": 1}
}
```

### 4. Add product to cart
```
POST /api/cart/1/items?productId=1&quantity=2
```

### 5. Checkout
```
POST /api/orders/checkout/1
```

## 👤 Author

**José Oskarithio Fernandes**  
Backend Developer | Java & Python  
[GitHub](https://github.com/Oskar-Fernandes)