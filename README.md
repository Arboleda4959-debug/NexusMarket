# NexusMarket
![NexusMarket CI](https://github.com/Arboleda4959-debug/NexusMarket/actions/workflows/maven.yml/badge.svg)
### Domain-Driven Marketplace Platform

NexusMarket is a marketplace software engineering project built with **Java 17 and Maven**, applying Domain-Driven Design (DDD) and Hexagonal Architecture principles.

The project focuses on domain modeling, business rules, domain services and maintainable software architecture.

## About the Project

NexusMarket explores the design of a marketplace platform that represents core e-commerce business operations.

Its main objective is to establish a structured domain foundation, keeping business logic independent from infrastructure and external technologies.

The project originated in the Software Construction II university course and continues to evolve as a software engineering learning project.

## Architecture

The project is organized around Hexagonal Architecture principles:

- **Domain:** Business models, value objects, domain exceptions and domain services.
- **Adapters:** Components that connect the application with external systems.
- **Infrastructure:** Technical implementations and infrastructure-related concerns.

The architectural goal is to protect business rules from dependencies on external technologies.
For more details, see the [Architecture Documentation](docs/ARCHITECTURE.md).

## Domain Modules

The domain foundation is organized into the following business areas:

| Module | Responsibility |
|---|---|
| Authorization | User and resource authorization |
| Cart | Shopping cart business rules |
| Inventory | Stock availability and reservations |
| Orders | Order-related business rules |
| Payments | Payment validation |
| Returns | Return-related validation |
| Refunds | Refund-related validation |
| Shipments | Shipment-related validation |

## Domain Model

The project models marketplace concepts such as:

- Users, buyers and sellers
- Products and warehouses
- Inventory
- Shopping carts
- Orders
- Payments
- Returns and refunds
- Shipments

Value Objects are used to represent domain concepts where appropriate.

## Technology Stack

- Java 17
- Apache Maven
- Git and GitHub
- Domain-Driven Design (DDD)
- Hexagonal Architecture

## Project Structure

```text
src/
└── main/
    └── java/
        └── application/
            ├── domain/
            │   ├── exceptions/
            │   ├── models/
            │   ├── ports/
            │   ├── services/
            │   └── valueobjects/
            ├── adapters/
            └── infrastructure/
```

## Getting Started

### Requirements

- JDK 17
- Apache Maven
- Git

### Clone the repository

```bash
git clone https://github.com/Arboleda4959-debug/NexusMarket.git
cd NexusMarket
```

### Compile the project

```bash
mvn clean compile
```

### Run tests

```bash
mvn test
```

## Engineering Principles

- Domain-Driven Design
- Hexagonal Architecture
- Separation of Concerns
- Object-Oriented Programming
- Maintainability
- Explicit business rules

## Project Status

NexusMarket is an evolving software engineering project. Its current focus is the domain foundation, business rules and architectural organization.

## Author

**Andrés Felipe Arboleda**  
Software Engineering Student  
Medellín, Colombia

[GitHub Profile](https://github.com/Arboleda4959-debug)

---

*Building today. Engineering tomorrow.*
