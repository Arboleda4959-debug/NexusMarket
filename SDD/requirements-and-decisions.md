# Requerimientos, decisiones y trazabilidad

## Requerimientos trazados al modelo

| Requerimiento del dominio | Evidencia Java | Estado / observación |
|---|---|---|
| Usuarios con ID, email, un rol y estado | `models/User.java`, `Email`, `UserRole`, `UserStatus`, `UserRepositoryPort` | Modelo y tipos existen; unicidad requiere adaptador/constraint. |
| Buyer y Seller separados del User | `models/Buyer.java`, `models/Seller.java`, `BuyerStatus`, `SellerStatus` | Implementado; autorización verifica cadena de IDs. |
| Productos físicos/digitales | `ProductType`, `models/Product.java` | Añadido; constructor legado asume PHYSICAL por compatibilidad. |
| Producto propiedad de vendedor | `ValidateProductOwnershipService` | Implementado. |
| Inventario distribuido por almacén | `Inventory`, `Warehouse`, `InventoryQuantity` | Modelo implementado. |
| Cantidad de inventario nunca negativa | `InventoryQuantity`, servicios de inventario | Validado en VO y operaciones. Concurrencia depende del adaptador. |
| Tipos de movimiento | `MovementType`, `InventoryMovement` | Añadido modelo de movimiento append-only. |
| Reserva identificable | `InventoryReservation`, `ReservationStatus`, overload de `ReleaseInventoryReservationService` | Evita liberar dos veces la misma reserva identificada; persistencia atómica pendiente. |
| Carrito y total | `Cart`, `CartItem`, `CalculateCartTotalService` | Valida nulls, cantidades y moneda. |
| Ciclo de vida de pedido | `OrderStatus`, `ValidateOrderStatusTransitionService` | Implementado. |
| Pago duplicado | `ValidatePaymentUniquenessService`, `PaymentRepositoryPort` | Bloquea pago pendiente/aprobado/refundado previo; idempotencia concurrente pendiente del adaptador. |
| Estado de producto comprable | `ValidateProductStatusService` | Solo `PUBLISHED` o `ACTIVE` legado se pueden comprar. |
| Pagos vinculados a pedido | `ValidatePaymentOrderService` | Relación, importe y moneda comprobados. Prevención de doble cargo requiere consulta/idempotencia en use case/adaptador. |
| Devolución vinculada a pedido | `ValidateReturnOrderService` | Implementado con IDs no vacíos. |
| No reembolsar dos veces la misma devolución | `ValidateRefundEligibilityService`, `RefundRepositoryPort` | Implementado a nivel de regla/consulta. Atomicidad concurrente pendiente del adaptador. |
| Transiciones de estados de pago/devolución/reembolso/envío | `*StatusTransitionService` | Añadidas. Las reglas deben confirmarse con el profesor si su enunciado difiere. |
| Registro de operación y auditoría | `Operation`, `AuditLog`, puertos de auditoría | Modelo/contratos presentes; caso de uso/adaptador aún pendiente. |
| Vendedor solo registrado por admin | `ValidateSellerRegistrationAuthorizationService` | Validación de autorización implementada; creación/persistencia pendiente del caso de uso. |
| SDD y correlación 1:1 | `SDD/` | Documentación añadida; actualizarla junto con cambios futuros. |

## Decisiones tomadas explícitamente

1. La devolución debe estar en `RECEIVED` para habilitar un reembolso.
2. La política actual impide un segundo reembolso si existe uno `PENDING` o `PROCESSED`; `REJECTED` permite reintentar con un nuevo registro.
3. El importe máximo reembolsable se recibe como dato calculado por el caso de uso a partir de fuentes autoritativas; no se infiere solo del objeto Refund.
4. Las transiciones de estados se centralizan en validadores puros.
5. Los overloads de autorización que no reciben el perfil Buyer/Seller fallan cerrado.
6. Los adaptadores REST, base de datos, JWT y Docker no se declaran implementados en esta versión.

## Diferencia con el ejemplo del profesor

Se adopta la estructura SDD, la separación `ports/in` y `ports/out`, los servicios por subdominio y la trazabilidad. No se copian los roles ni entidades bancarias del ejemplo. Para NexusMarket se usan Buyer, Seller, Logistics Operator, Administrator, Supervisor y Internal Analyst.

## Pendientes antes de considerar producción

- Implementar casos de uso concretos que implementen input ports.
- Implementar adaptadores de persistencia y constraints de unicidad/relación.
- Implementar atomicidad e idempotencia para reembolso, pago e inventario.
- Decidir política de devoluciones por línea/cantidad, plazos y reembolsos parciales si lo exige el negocio.
- Confirmar con el profesor el alcance de la segunda entrega y añadir REST/security/database solo si está requerido.
