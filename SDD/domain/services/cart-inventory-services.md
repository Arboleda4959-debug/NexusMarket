# Servicios de carrito e inventario

## Carrito

- Solo `CartStatus.ACTIVE` puede modificarse o pasar a checkout.
- No se permite checkout de carrito vacío.
- Cada `CartItem` debe tener cantidad y precio válidos; cantidad > 0.
- Todas las líneas del total deben compartir moneda; no sumar cantidades monetarias de monedas distintas.
- El total se calcula a partir del precio capturado en cada línea. El caso de uso debe definir cuándo refrescar precios y cómo validar disponibilidad antes de checkout.

## Inventario

- `InventoryQuantity.available >= 0` y `reserved >= 0`.
- Reserva: `available -= requested`, `reserved += requested`; requested > 0 y disponible suficiente.
- Liberación: `reserved -= requested`, `available += requested`; requested > 0 y reserva suficiente.
- Una liberación repetida no debe ser aceptada sin una reserva/operación identificable. La base actual modela cantidades agregadas; el caso de uso futuro debe persistir una reserva/operación identificable o idempotency key para impedir duplicados.
- `InventoryReservation` aporta identidad a la reserva; el overload preferido de `ReleaseInventoryReservationService` rechaza una reserva ya liberada/consumida.
- Para evitar overselling, el adaptador debe hacer lectura/actualización en transacción, bloqueo o control optimista de versión.
- Cada movimiento (INFLOW, RESERVATION, SALE, ADJUSTMENT, RETURN) debe dejar rastro en `InventoryMovementRepositoryPort` cuando se integre persistencia.
