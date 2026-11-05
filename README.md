# NexusMarket

### Domain-Driven Marketplace Platform

A marketplace software engineering project developed with Java 17 and Maven, applying Domain-Driven Design (DDD) and Hexagonal Architecture principles.

**Project focus:** Domain modeling, business rules, domain services and software architecture.

---

## About the Project

NexusMarket is a marketplace platform designed to represent the core business operations of an e-commerce ecosystem.

The project focuses on building a well-structured domain foundation, separating business logic from infrastructure and external technologies.

It was initially developed as part of the Software Construction II university course and is being used as a foundation for further software engineering improvements.

## Architecture

The project follows Hexagonal Architecture principles:

- Domain: Business models, value objects, exceptions and domain services.
- Adapters: Components responsible for communicating with external systems.
- Infrastructure: Technical implementations and infrastructure concerns.

The main architectural principle is dependency direction: business rules should remain independent of external technologies.

## Domain Modules

The domain is organized around the following business areas:

| Module | Responsibility |
|---|---|
| Authorization | User and resource authorization |
| Cart | Shopping cart operations |
| Inventory | Stock availability and reservations |
| Orders | Order business rules |
| Payments | Payment validation |
| Returns | Return validation |
| Refunds | Refund validation |
| Shipments | Shipment validation |

## Domain Concepts

The project includes domain models such as:

- User
- Buyer
- Seller
- Product
- Warehouse
- Inventory
- Cart
- Order
- Payment
- Shipment
- Return
- Refund

It also uses Value Objects to represent concepts such as Money, Email, Quantity, Address and domain statuses.

## Technology Stack

- Java 17
- Apache Maven
- JUnit 5
- Git
- GitHub
- Domain-Driven Design
- Hexagonal Architecture

## Project Structure

```text
NexusMarket/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── application/
│   │           ├── domain/
│   │           │   ├── exceptions/
│   │           │   ├── models/
│   │           │   ├── ports/
│   │           │   ├── services/
│   │           │   └── valueobjects/
│   │           ├── adapters/
│   │           └── infrastructure/
│   └── test/
├── pom.xml
├── README.md
└── .gitignore
```

## Getting Started

### Requirements

- Java Development Kit (JDK) 17
- Apache Maven 3.9+
- Git

### Clone the repository

```bash
git clone https://github.com/Arboleda4959-debug/NexusMarket.git
```

### Enter the project directory

```bash
cd NexusMarket
```

### Build the project

```bash
mvn clean compile
```

### Run tests

```bash
mvn test
```

## Engineering Principles

The project is developed with a focus on:

- Domain-Driven Design.
- Hexagonal Architecture.
- Separation of Concerns.
- Object-Oriented Programming.
- Maintainability.
- Clear business rules.

## Project Status

The repository contains the initial domain foundation and architectural structure.

Further development and improvements are part of the project's ongoing evolution.

## Author

**Andrés Felipe Arboleda**

Software Engineering Student  
Medellín, Colombia

[GitHub Profile](https://github.com/Arboleda4959-debug)

---

*Building today. Engineering tomorrow.*
