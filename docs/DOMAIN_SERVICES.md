# Domain Services

## Overview

Domain Services encapsulate business operations and validation rules that do not naturally belong to a single domain entity.

NexusMarket uses domain services to coordinate domain validations and protect business rules across different marketplace modules.

## Authorization Services

| Service | Responsibility |
|---|---|
| `ValidateUserAuthorizationService` | Validates that a user exists and has an active status. |
| `ValidateSellerAuthorizationService` | Validates the relationship between a user and a seller. |
| `ValidateBuyerCartAuthorizationService` | Validates buyer authorization and cart ownership. |
| `ValidateSellerInventoryAuthorizationService` | Coordinates seller, product and warehouse ownership validations. |

## Inventory Services

| Service | Responsibility |
|---|---|
| `ValidateInventoryAvailabilityService` | Checks inventory availability and requested quantity. |
| `ReserveInventoryService` | Coordinates inventory reservation. |
| `ValidateReservedInventoryService` | Checks whether enough inventory is reserved. |
| `ReleaseInventoryReservationService` | Coordinates the release of an inventory reservation. |
| `ValidateInventoryOwnershipService` | Validates the relationship between inventory, product and warehouse. |

## Ownership Services

| Service | Responsibility |
|---|---|
| `ValidateProductOwnershipService` | Validates product ownership by a seller. |
| `ValidateWarehouseOwnershipService` | Validates warehouse ownership. |

## Payment Services

| Service | Responsibility |
|---|---|
| `ValidatePaymentOrderService` | Validates the relationship between a payment and an order. |
| `ValidatePaymentStatusService` | Validates payment status. |

## Design Principles

The domain services follow these principles:

- Business rules remain inside the domain layer.
- Services coordinate validations when multiple domain objects are involved.
- Domain entities and Value Objects represent business concepts.
- Domain exceptions communicate domain rule violations.
- Application and infrastructure concerns should remain separated from domain logic.

## Development Status

This documentation describes the domain services identified during the current development stage.

The implementation and documentation may evolve as the domain model and business requirements are refined.
