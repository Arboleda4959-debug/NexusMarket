# Order services

`ValidateOrderStatusTransitionService` permite únicamente: CREATED→PAYMENT_PENDING/CANCELLED; PAYMENT_PENDING→PAID/CANCELLED; PAID→PREPARING; PREPARING→SHIPPED; SHIPPED→DELIVERED; DELIVERED→COMPLETED. COMPLETED y CANCELLED son terminales. Cada transición tiene pruebas negativas/positivas en `StatusTransitionServicesTest`.

El modelo actual contiene `paymentId` y `shipmentId`; el caso de uso debe validar que esas referencias existen y corresponden al mismo pedido antes de cambiar el estado. El estado final no debe mutarse por un setter libre.
