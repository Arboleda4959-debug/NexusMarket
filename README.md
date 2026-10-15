# NexusMarket — Domain Foundation (Java 17)

NexusMarket es un marketplace con roles de comprador, vendedor, operador logístico, administrador, supervisor y analista interno. Esta entrega fortalece el **núcleo de dominio** siguiendo DDD y los principios de arquitectura hexagonal usados en el repositorio de referencia del profesor.

## Qué contiene

- Entidades de dominio en `src/main/java/application/domain/models`.
- Value Objects y enums en `src/main/java/application/domain/valueobjects`.
- Servicios de dominio por área: autorización, carrito, inventario, pedidos, pagos, devoluciones, reembolsos y envíos.
- Contratos `ports/in` y `ports/out`, independientes de Spring y de la base de datos.
- Documentación SDD con trazabilidad entre modelos, reglas, servicios y puertos.
- Pruebas unitarias JUnit 5 en `src/test/java`.

## Alcance honesto de esta entrega

Esta versión es una **base de dominio reforzada**, no una aplicación web de producción. Los contratos de puertos ya están declarados, pero todavía no existen adaptadores REST, casos de uso concretos, autenticación JWT ni adaptadores de persistencia SQL/NoSQL. Se deben implementar si la siguiente entrega del profesor los exige. No se agregan tecnologías de infraestructura por suposición.

## Requisitos

- JDK 17 o superior.
- Maven 3.9 o compatible.

Verifica Java y Maven:

```bash
java -version
mvn -version
```

Ejecuta las pruebas y compila:

```bash
mvn clean test
mvn package
```

Ejecuta la aplicación mínima de consola:

```bash
mvn exec:java
```

`application.App` solo confirma que el proyecto arranca; no representa una API REST.

## Decisiones relevantes

- Las cantidades de operaciones de inventario (reservar/liberar) deben ser mayores que cero. `Quantity` puede representar cero en otros contextos, pero los servicios operativos lo rechazan.
- Para la etapa actual, una devolución solo admite un reembolso pendiente/procesado. Un reembolso rechazado permite un nuevo intento con un nuevo registro. La política se centraliza en `ValidateRefundEligibilityService`.
- La elegibilidad exige que `ReturnStatus` sea `RECEIVED`; si la regla acordada con el profesor difiere, cambia el servicio y la documentación juntos.
- El importe máximo reembolsable lo calcula el caso de uso con información autoritativa del pago/pedido y se pasa al validador. El servicio no inventa ese importe.
- La consulta de reembolsos previos evita duplicados secuenciales; el adaptador de persistencia deberá garantizar atomicidad/idempotencia para solicitudes concurrentes.
- El constructor antiguo de `Product` se conserva por compatibilidad y asume `PHYSICAL`; el código nuevo debe indicar `ProductType` explícitamente.

## Documentación

Empieza por [`SDD/README.md`](SDD/README.md), después consulta `SDD/domain/Domain Model.md`, `SDD/domain/Domain Value Objects.md` y `SDD/domain/Domain Services.md`.

## Auditoría técnica DDD/SDD

Se incluye [`AUDITORIA_DDD_NEXUSMARKET.md`](AUDITORIA_DDD_NEXUSMARKET.md) con hallazgos, estado real de implementación y un plan priorizado. Es una auditoría de apoyo, no una nota oficial.
