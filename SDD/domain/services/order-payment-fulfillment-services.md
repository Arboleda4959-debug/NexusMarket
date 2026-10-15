# Pedidos, pagos, devoluciones, reembolsos y envíos

## Order

`CREATED → PAYMENT_PENDING → PAID → PREPARING → SHIPPED → DELIVERED → COMPLETED`; `CREATED` o `PAYMENT_PENDING` pueden cancelarse. Estados terminales no pueden reabrirse. Las reglas están en `ValidateOrderStatusTransitionService`.

## Payment

- Pago pertenece al pedido correcto.
- Importe y moneda del pago deben coincidir con `Order.total` para un pago total.
- Transiciones: `PENDING → APPROVED | REJECTED`; `APPROVED → REFUNDED`; `REJECTED` y `REFUNDED` terminales.
- Un caso de uso debe consultar pagos previos del pedido para evitar cobros duplicados y usar idempotency key con el proveedor de pagos.

## Return

- Debe pertenecer al pedido indicado.
- Transiciones: `REQUESTED → APPROVED | REJECTED`; `APPROVED → RECEIVED`; `RECEIVED → COMPLETED`; `REJECTED` y `COMPLETED` terminales.
- Esta etapa no modela política temporal de devoluciones ni cantidades devueltas por artículo. Si el profesor la exige, se debe añadir explícitamente a la entidad y a los casos de uso.

## Refund — política definida en esta versión

1. La devolución debe existir y estar en `RECEIVED`.
2. El importe solicitado debe ser > 0 y usar la moneda del máximo reembolsable.
3. El importe no puede superar el máximo calculado por el caso de uso desde el pago/pedido y los artículos autorizados para devolución.
4. Consultar `RefundRepositoryPort.findByReturnId(returnId)`.
5. Si ya existe un reembolso `PENDING` o `PROCESSED`, rechazar otro. Un reembolso `REJECTED` no bloquea un nuevo intento.
6. Transiciones de refund: `PENDING → PROCESSED | REJECTED`.
7. Una consulta previa no evita carrera concurrente. El adaptador debe serializar la operación o imponer una restricción/idempotency key apropiada.

La política de esta versión es **un reembolso activo/procesado por devolución**, no varios parciales acumulables. Si el profesor exige reembolsos parciales, se debe cambiar la regla para sumar los reembolsos exitosos y comparar con el máximo restante.

## Shipment

Transiciones: `PENDING → PREPARING | CANCELLED`; `PREPARING → SHIPPED | CANCELLED`; `SHIPPED → IN_TRANSIT | DELIVERED`; `IN_TRANSIT → DELIVERED`; `DELIVERED` y `CANCELLED` terminales. El pedido debe existir y el envío debe apuntar al mismo `orderId`.
