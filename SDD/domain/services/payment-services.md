# Payment services

- `ValidatePaymentOrderService`: no nulls, IDs válidos, relación payment.orderId/order.id, total e importe presentes, moneda e importe exactos.
- `ValidatePaymentStatusService`: estado presente; `validateCanProcess` aplica únicamente a Refund y no reemplaza `ValidatePaymentStatusTransitionService`.
- `ValidatePaymentStatusTransitionService`: PENDING→APPROVED/REJECTED y APPROVED→REFUNDED.
- Persistencia/caso de uso pendientes: consultar pagos existentes, evitar doble cargo con idempotency key y ejecutar confirmación de pago y transición del pedido en una operación coordinada.
