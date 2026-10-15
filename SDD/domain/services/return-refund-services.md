# Return and Refund services

- `ValidateReturnOrderService`: comprueba relación por IDs no vacíos.
- `ValidateReturnStatusService`: valida objeto/estado.
- `ValidateReturnStatusTransitionService`: REQUESTED→APPROVED/REJECTED; APPROVED→RECEIVED; RECEIVED→COMPLETED.
- `ValidateRefundReturnService`: verifica pertenencia, importe no nulo y mayor que cero.
- `ValidateRefundStatusService`: valida estado; `validateCanProcess` solo admite PENDING.
- `ValidateRefundStatusTransitionService`: PENDING→PROCESSED/REJECTED.
- `ValidateRefundEligibilityService`: consulta `RefundRepositoryPort`, requiere Return RECEIVED, moneda/importe correctos y no permite un reembolso PENDING/PROCESSED previo.

No se debe ejecutar un reembolso desde una validación aislada: un caso de uso futuro debe cargar Return/Order/Payment, calcular máximo reembolsable, validar, procesar el proveedor externo de forma idempotente, guardar el resultado y auditarlo.
