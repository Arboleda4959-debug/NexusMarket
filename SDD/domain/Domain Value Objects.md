# Domain Value Objects y enums

## Records / Value Objects

| Tipo | Propiedades | Regla |
|---|---|---|
| `Email` | `value` | No vacío, formato básico validado, trim y minúsculas. Unicidad requiere `UserRepositoryPort` y constraint persistente. |
| `Money` | `BigDecimal amount`, `currency` | Importe no negativo; moneda obligatoria normalizada a mayúsculas. Los servicios de pago/reembolso exigen importe positivo cuando se ejecuta la operación. |
| `ProductPrice` | `BigDecimal amount`, `currency` | Importe no negativo; para producto vendible debe ser > 0 si la regla comercial así lo exige. |
| `OrderTotal` | `BigDecimal amount`, `currency` | Total no negativo y moneda normalizada. |
| `Quantity` | `int value` | No puede ser negativa. Las operaciones de reserva/liberación y los movimientos exigen > 0. |
| `InventoryQuantity` | `available`, `reserved` | Ambos valores no negativos. |
| `Address` | Datos de dirección | Validación de campos requeridos en el constructor. |
| `ContactInformation` | Datos de contacto | Encapsula información de contacto del perfil. |
| `TrackingCode` | `value` | No vacío y normalizado. |

## Enums

- `UserRole`: `BUYER`, `SELLER`, `LOGISTICS_OPERATOR`, `ADMINISTRATOR`, `SUPERVISOR`.
- `UserStatus`: `ACTIVE`, `INACTIVE`, `BLOCKED` (estado de acceso del usuario).
- `BuyerStatus`: `ACTIVE`, `INACTIVE`, `BLOCKED` (estado comercial del perfil comprador, separado del acceso).
- `SellerStatus`: `ACTIVE`, `INACTIVE`, `BLOCKED`.
- `ProductType`: `PHYSICAL`, `DIGITAL`.
- `ProductStatus`: estados canónicos `PUBLISHED`, `SUSPENDED`, `DISCONTINUED`. `ACTIVE`, `INACTIVE` y `OUT_OF_STOCK` se conservan marcados como obsoletos por compatibilidad con el código previo.
- `WarehouseStatus`: `ACTIVE`, `INACTIVE`, `BLOCKED`.
- `CartStatus`: `ACTIVE`, `CONVERTED`, `ABANDONED`.
- `OrderStatus`: `CREATED`, `PAYMENT_PENDING`, `PAID`, `PREPARING`, `SHIPPED`, `DELIVERED`, `COMPLETED`, `CANCELLED`.
- `PaymentStatus`: `PENDING`, `APPROVED`, `REJECTED`, `REFUNDED`.
- `ReturnStatus`: `REQUESTED`, `APPROVED`, `REJECTED`, `RECEIVED`, `COMPLETED`.
- `RefundStatus`: `PENDING`, `PROCESSED`, `REJECTED`.
- `ShipmentStatus`: `PENDING`, `PREPARING`, `SHIPPED`, `IN_TRANSIT`, `DELIVERED`, `CANCELLED`.
- `MovementType`: `INFLOW`, `RESERVATION`, `SALE`, `ADJUSTMENT`, `RETURN`.
- `ReservationStatus`: `ACTIVE`, `RELEASED`, `CONSUMED`, `CANCELLED`.
- `OperationType`: `CREATE`, `UPDATE`, `DELETE`, `PURCHASE`, `PAYMENT`, `SHIPMENT`, `RETURN`, `REFUND`, `INVENTORY_CHANGE`.
- `AuditSeverity`: niveles de severidad del registro de auditoría.

## Compatibilidad y migración

Se conservaron constantes y constructores antiguos cuando fue posible para reducir cambios bruscos. En código nuevo, se deben utilizar `BuyerStatus` para Buyer, `ProductType` explícito al crear Product y los estados canónicos de ProductStatus. Antes de integrar persistencia, definir migración de los valores legacy.
