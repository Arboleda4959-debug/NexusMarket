# Output ports

Los contratos están en `src/main/java/application/domain/ports/out` y se implementarán desde adaptadores. Los métodos `Optional` distinguen explícitamente el recurso inexistente de una excepción técnica.

| Puerto | Propósito |
|---|---|
| `UserRepositoryPort` | Buscar usuario, consultar existencia de ID/email y guardar. |
| `BuyerRepositoryPort` | Buscar perfil por ID/usuario y guardar. |
| `SellerRepositoryPort` | Buscar vendedor por ID/usuario y guardar. |
| `ProductRepositoryPort` | Buscar producto y guardar. |
| `WarehouseRepositoryPort` | Buscar almacén y guardar. |
| `CartRepositoryPort` | Buscar carrito, buscar activo por comprador y guardar. |
| `InventoryRepositoryPort` | Buscar inventario por producto/almacén y guardar de forma atómica. |
| `OrderRepositoryPort` | Buscar y guardar pedido. |
| `PaymentRepositoryPort` | Buscar pago, listar pagos por pedido y guardar. |
| `ReturnRepositoryPort` | Buscar y guardar devolución. |
| `RefundRepositoryPort` | Buscar/listar/guardar reembolsos; necesario para prevenir duplicados secuenciales y persistir el resultado. |
| `ShipmentRepositoryPort` | Buscar envío por ID/pedido y guardar. |
| `InventoryReservationRepositoryPort` | Buscar, listar reservas activas por pedido y guardar reservas identificables. |
| `InventoryMovementRepositoryPort` | Añadir registro de movimiento de inventario. |
| `AuditLogRepositoryPort` | Añadir registro de auditoría. |

## Requisitos para los adaptadores

- Persistir IDs y relaciones con constraints de integridad referencial.
- `UserRepositoryPort`: índice único para email normalizado e ID.
- Inventario: operación transaccional/optimista o bloqueo para impedir sobreventa concurrente.
- Reembolsos: constraint, lock o idempotency key para impedir dos reembolsos simultáneos para la misma devolución según la política definida.
- Auditoría y movimientos: registros append-only cuando lo exija la trazabilidad.
- Los errores de infraestructura se traducen en errores de aplicación; no se filtran excepciones SQL al dominio.
