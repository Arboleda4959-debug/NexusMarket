# Output ports (ports out)

Los puertos de salida abstraen almacenamiento y servicios externos. Las interfaces Java viven en este paquete y no dependen de Spring, JPA, SQL ni MongoDB. Los adaptadores futuros implementarán estos contratos.

`RefundRepositoryPort.findByReturnId` es obligatorio para verificar historial antes de crear un reembolso. La prevención definitiva de duplicados requiere una operación atómica/constraint o idempotency key en el adaptador; una consulta previa por sí sola no protege solicitudes concurrentes.
