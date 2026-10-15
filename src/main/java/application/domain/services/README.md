# Domain Services

The Domain Services contain domain logic that does not naturally belong
to a single domain model.

Services are organized by responsibility and remain independent from
REST, HTTP, JSON, SQL, JPA, MongoDB, Spring and other infrastructure
technologies.

## Authorization

- ValidateUserAuthorizationService
- ValidateSellerAuthorizationService
- ValidateSellerResourceAuthorizationService
- ValidateSellerInventoryAuthorizationService
- ValidateProductOwnershipService
- ValidateWarehouseOwnershipService
- ValidateBuyerCartAuthorizationService
- ValidateBuyerOrderAuthorizationService
- ValidateBuyerResourceAuthorizationService

These services validate user authorization, roles, ownership and access
to buyer and seller resources.

## Cart

- CalculateCartTotalService
- ValidateCartStatusService

These services calculate cart totals and validate cart state.

## Inventory

- ValidateInventoryAvailabilityService
- ValidateInventoryOwnershipService
- ValidateReservedInventoryService
- ReserveInventoryService
- ReleaseInventoryReservationService

These services validate inventory availability and ownership and manage
inventory reservations and their release.

## Order

- ValidateOrderStatusTransitionService

This service validates transitions between order statuses.

## Payment

- ValidatePaymentOrderService
- ValidatePaymentStatusService

These services validate the relationship between payments and orders
and validate payment status.

## Return

- ValidateReturnOrderService
- ValidateReturnStatusService

These services validate the relationship between returns and orders
and validate return status.

## Refund

- ValidateRefundReturnService
- ValidateRefundStatusService

These services validate the relationship between refunds and returns
and validate refund status.

## Shipment

- ValidateShipmentOrderService
- ValidateShipmentStatusService

These services validate the relationship between shipments and orders
and validate shipment status.

## Service Pattern

Domain services expose their behavior through an `execute(...)` method.

Services can compose other domain services when a domain operation
requires multiple validations.

Example:

```java
service.execute(...);