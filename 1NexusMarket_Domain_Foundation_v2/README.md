# NexusMarket

NexusMarket is the marketplace system selected for the Software Construction 2 semester project.

## Current implementation scope

This repository establishes the **Domain foundation** required by the project:
- Domain Models
- Value Objects
- Domain-oriented validation for the Value Objects
- Initial package structure for Hexagonal Architecture / Ports and Adapters

The implementation is intentionally independent of REST, HTTP, JSON, SQL, JPA, MongoDB, Spring, and other infrastructure technologies.

## Domain Models

User, Buyer, Seller, Product, Warehouse, Inventory, Cart, CartItem, Order, OrderItem,
Payment, Invoice, Shipment, Return, Refund, Operation, AuditLog.

## Value Objects

Email, Money, Quantity, ProductPrice, OrderTotal, TrackingCode,
ContactInformation, Address, UserRole, UserStatus, SellerStatus, ProductStatus,
WarehouseStatus, InventoryQuantity, CartStatus, OrderStatus, PaymentStatus,
ShipmentStatus, ReturnStatus, RefundStatus, OperationType, AuditSeverity.

## Domain rules represented in this foundation

- Inventory quantities cannot be negative.
- Inventory tracks available and reserved quantities.
- Quantity is non-negative.
- Email validates its basic format.
- Monetary concepts carry amount and currency and cannot represent negative amounts.
- Domain concepts are separated from API DTOs and persistence representations.
- Value Objects are value-based concepts and are implemented as immutable Java records.

## Architecture direction

The project follows the architecture described for the course:
Application
- Adapters
- Domain
- Infrastructure

Dependencies must point toward the Domain. The Domain must not depend on adapters or infrastructure.

## Semester roadmap

1. Domain Models and Value Objects
2. Domain Services
3. Input and Output Ports
4. Domain Exceptions
5. Input/Output Adapters
6. Infrastructure
7. Application use cases and validations
8. Tests and integration

The later stages will be implemented only when their detailed domain rules are defined.
