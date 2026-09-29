# NexusMarket

NexusMarket is the marketplace system selected for the Software Construction 2 semester project.

## Current implementation scope

This repository establishes the **Domain foundation** required for the project:

- Domain Models
- Value Objects
- Domain Exceptions
- Domain Services
- Domain-oriented validation
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

## Domain Exceptions

The domain contains a general `DomainException` used by domain services
to represent violations of domain rules and invalid domain operations.

## Domain Services

The project contains domain services organized according to their responsibility.

### Authorization

- ValidateUserAuthorizationService
- ValidateSellerAuthorizationService
- ValidateSellerResourceAuthorizationService
- ValidateSellerInventoryAuthorizationService
- ValidateProductOwnershipService
- ValidateWarehouseOwnershipService
- ValidateBuyerCartAuthorizationService
- ValidateBuyerOrderAuthorizationService
- ValidateBuyerResourceAuthorizationService

### Cart

- CalculateCartTotalService
- ValidateCartStatusService

### Inventory

- ValidateInventoryAvailabilityService
- ValidateInventoryOwnershipService
- ValidateReservedInventoryService
- ReserveInventoryService
- ReleaseInventoryReservationService

### Order

- ValidateOrderStatusTransitionService

### Payment

- ValidatePaymentOrderService
- ValidatePaymentStatusService

### Return

- ValidateReturnOrderService
- ValidateReturnStatusService

### Refund

- ValidateRefundReturnService
- ValidateRefundStatusService

### Shipment

- ValidateShipmentOrderService
- ValidateShipmentStatusService

## Domain Rules Represented

The domain currently represents rules related to:

- Inventory quantities cannot be negative.
- Inventory tracks available and reserved quantities.
- Inventory availability is validated before a reservation.
- Inventory reservations can be released only when sufficient reserved inventory exists.
- Products and warehouses are associated with their corresponding seller.
- Inventory is associated with a product and warehouse.
- Buyers can operate only on their own cart and orders.
- Sellers can operate only on their own resources.
- Users must be authorized according to their domain status.
- Cart status is validated before operations.
- Cart totals are calculated from its items.
- Payments must belong to the corresponding order.
- Payment status must be valid.
- Returns must belong to the corresponding order.
- Refunds must belong to the corresponding return.
- Shipments must belong to the corresponding order.
- Shipment status must be valid.
- Refund status must be valid.
- Order status transitions are validated through a dedicated domain service.

## Architecture Direction

The project follows the architecture described for the course:

Application
- Adapters
- Domain
- Infrastructure

Dependencies must point toward the Domain.

The Domain must not depend on adapters or infrastructure.

The current Domain layer contains:

- Models
- Value Objects
- Exceptions
- Domain Services
- Input and Output Ports structure

The Domain remains independent from REST, HTTP, JSON, SQL, JPA,
MongoDB, Spring and other infrastructure technologies.

## Project Structure

```text
src/main/java/application/domain
├── exceptions
├── models
├── ports
│   ├── in
│   └── out
├── services
│   ├── authorization
│   ├── cart
│   ├── inventory
│   ├── order
│   ├── payment
│   ├── refunds
│   ├── return
│   └── shipment
└── valueobjects