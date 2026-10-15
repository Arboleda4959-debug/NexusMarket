# SDD — Software Design Document de NexusMarket

## Propósito

Especificar el dominio del marketplace, las reglas que protegen las operaciones y los límites entre dominio, aplicación y adaptadores. Se toma del repositorio del profesor la estructura documental, trazabilidad y separación Ports & Adapters; no se copian entidades ni reglas bancarias.

## Estado del alcance

- **Implementado:** modelos Java, Value Objects/enums, servicios de dominio puros, puertos declarados y pruebas unitarias iniciales.
- **Declarado como contrato, pendiente de implementación:** casos de uso, adaptadores REST, adaptadores de persistencia, transacciones, seguridad HTTP y configuración de infraestructura.
- **No debe inferirse:** que exista base de datos, API HTTP, JWT o despliegue Docker en esta etapa.

## Índice

- [Arquitectura](Software%20Architecture/Software%20Architecture.md)
- [Modelo de dominio](domain/Domain%20Model.md)
- [Value Objects](domain/Domain%20Value%20Objects.md)
- [Servicios de dominio](domain/Domain%20Services.md)
- [Puertos de entrada](domain/Input-ports.md)
- [Puertos de salida](domain/Output-ports.md)
- [Autorización](domain/services/authorization-services.md)
- [Carrito e inventario](domain/services/cart-inventory-services.md)
- [Pedidos, pagos, devoluciones, reembolsos y envíos](domain/services/order-payment-fulfillment-services.md)
- [C4 — contexto y contenedores](C4/README.md)
- [Decisiones y trazabilidad](requirements-and-decisions.md)

## Convenciones

- Código Java y nombres de clases/métodos en inglés; documentación en español.
- Los servicios de dominio no importan Spring, JPA, JDBC, MongoDB ni tipos HTTP.
- Los puertos de salida son interfaces del lado del dominio; las implementaciones concretas pertenecen a adaptadores.
- Una regla debe existir en código y documentarse aquí. Si cambia una regla, actualizar pruebas y SDD en el mismo cambio.

## Auditoría técnica

Consulta [`AUDITORIA_DDD_NEXUSMARKET.md`](../AUDITORIA_DDD_NEXUSMARKET.md) para el diagnóstico DDD/SDD, brechas verificables y plan de corrección.
