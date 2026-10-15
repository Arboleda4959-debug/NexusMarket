# Catálogo de Domain Services

Los servicios son clases Java puras: no realizan llamadas HTTP ni SQL y no conocen Spring. Los puertos de salida se utilizan cuando una regla requiere consultar estado persistido.

| Área | Servicio | Responsabilidad |
|---|---|---|
| Authorization | `ValidateUserAuthorizationService` | Usuario no nulo, ID presente, estado ACTIVE y rol presente. |
| Authorization | `ValidateBuyerCartAuthorizationService` | Verifica User activo, rol BUYER, Buyer activo, relación User→Buyer y propietario del Cart. |
| Authorization | `ValidateBuyerOrderAuthorizationService` | Verifica relación User→Buyer→Order. |
| Authorization | `ValidateBuyerResourceAuthorizationService` | Overload seguro recibe User, Buyer y Cart. El overload legado sin Buyer falla cerrado. |
| Authorization | `ValidateSellerAuthorizationService` | Verifica User activo, rol SELLER, Seller activo y User→Seller. |
| Authorization | `ValidateSellerResourceAuthorizationService` | Verifica User→Seller→Product; el overload legado sin Seller falla cerrado. |
| Authorization | `ValidateProductOwnershipService` | Verifica Product.sellerId == Seller.id y que los IDs existan. |
| Authorization | `ValidateWarehouseOwnershipService` | Verifica propiedad y estados activos del vendedor/almacén. |
| Authorization | `ValidateSellerInventoryAuthorizationService` | Verifica que producto, almacén e inventario pertenezcan al vendedor y entre sí. |
| Authorization | `ValidateUserUniquenessService` | Consulta el puerto de usuarios para impedir ID/correo duplicado; requiere constraint persistente para concurrencia. |
| Authorization | `ValidateSellerRegistrationAuthorizationService` | Solo ADMINISTRATOR activo puede autorizar registro de vendedor. |
| Cart | `CalculateCartTotalService` | Suma subtotales; rechaza ítems nulos, cantidad no positiva, precio inválido y monedas mezcladas. |
| Cart | `ValidateCartStatusService` | Valida estado y ofrece `validateCanModify` / `validateCanCheckout`. |
| Inventory | `ValidateInventoryAvailabilityService` | Exige inventario/cantidad válidos, cantidad > 0 y existencias suficientes. |
| Inventory | `ValidateReservedInventoryService` | Exige cantidad > 0 y reserva suficiente para liberar. |
| Inventory | `ReserveInventoryService` | Mueve cantidad de available a reserved tras validar. |
| Inventory | `ReleaseInventoryReservationService` | Mueve cantidad de reserved a available tras validar; overload con `InventoryReservation` impide liberar dos veces la misma reserva en el objeto actual. |
| Inventory | `ValidateInventoryOwnershipService` | Comprueba que el inventario pertenece al producto y almacén indicados. |
| Order | `ValidateOrderStatusTransitionService` | Valida transiciones de estado del pedido. |
| Payment | `ValidatePaymentUniquenessService` | Consulta pagos previos y bloquea un pago pendiente/exitoso duplicado. La persistencia debe respaldar la regla con idempotencia. |
| Cart | `ValidateProductStatusService` | Solo permite comprar productos publicados (y el estado ACTIVE legado). |
| Payment | `ValidatePaymentOrderService` | Valida relación pago-pedido e igualdad de importe/moneda para pago total. |
| Payment | `ValidatePaymentStatusService` | Exige pago y estado; las transiciones se controlan con `ValidatePaymentStatusTransitionService`. |
| Payment | `ValidatePaymentStatusTransitionService` | Controla transiciones PENDING→APPROVED/REJECTED y APPROVED→REFUNDED. |
| Returns | `ValidateReturnOrderService` | Comprueba relación devolución-pedido con IDs válidos. |
| Returns | `ValidateReturnStatusService` | Valida devolución y estado presentes. |
| Returns | `ValidateReturnStatusTransitionService` | Controla REQUESTED→APPROVED/REJECTED→(si aprobada) RECEIVED→COMPLETED. |
| Refunds | `ValidateRefundReturnService` | Comprueba relación devolución-reembolso e importe positivo. |
| Refunds | `ValidateRefundStatusService` | Comprueba estado y permite procesar solo PENDING mediante `validateCanProcess`. |
| Refunds | `ValidateRefundStatusTransitionService` | Solo PENDING→PROCESSED/REJECTED. |
| Refunds | `ValidateRefundEligibilityService` | Exige devolución RECEIVED, importe válido, moneda correcta, límite máximo y consulta historial. |
| Shipment | `ValidateShipmentOrderService` | Comprueba relación envío-pedido e ID/estado del pedido. |
| Shipment | `ValidateShipmentStatusService` | Valida envío y estado presentes. |
| Shipment | `ValidateShipmentStatusTransitionService` | Controla transiciones logísticas permitidas. |

## Reglas que dependen de datos persistidos

La existencia de un pago/reembolso anterior, unicidad de email/IDs, existencia de entidades y atomicidad de inventario no pueden garantizarse solo con objetos en memoria. Los casos de uso futuros deben consultar los puertos de salida y la persistencia debe usar constraints/transacciones/idempotencia.
