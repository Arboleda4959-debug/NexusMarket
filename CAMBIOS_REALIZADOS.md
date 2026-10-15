# Cambios realizados en esta versión

## Correcciones de dominio

- Corregida la cadena de autorización User → Buyer → Cart/Order.
- Corregida la cadena User → Seller → Product; las firmas antiguas que no reciben el perfil intermedio ahora fallan cerrado.
- Validación de usuario activo, rol presente, perfil comercial activo y estados activos de vendedor/almacén.
- Validaciones de IDs/relaciones en propiedad de producto, almacén, inventario, pedido, pago, devolución, envío y reembolso.
- Carrito: total con validación de ítems, cantidades y moneda; checkout solo para carrito activo y no vacío.
- Inventario: cantidades operativas > 0, existencia/reserva suficiente y modelo identificable `InventoryReservation` para evitar liberar dos veces la misma reserva.
- Pagos: relación con pedido, importe/moneda coincidentes y servicio de consulta para evitar pagos pendientes/exitosos duplicados.
- Estados: añadidas transiciones explícitas para pagos, devoluciones, reembolsos y envíos.
- Reembolsos: `ValidateRefundEligibilityService` consulta el historial por `RefundRepositoryPort`, requiere Return RECEIVED, importe positivo, moneda correcta, límite máximo y bloquea reembolsos pendientes/procesados anteriores.
- Añadidos `ProductType`, `MovementType`, `BuyerStatus`, `ReservationStatus`, `InventoryMovement` y `InventoryReservation`.
- Añadidos puertos de entrada y salida independientes de infraestructura.

## Documentación

- Añadida carpeta `SDD/` con modelo, Value Objects, servicios, puertos, arquitectura, C4 conceptual, reglas y trazabilidad.
- El SDD distingue explícitamente componentes implementados de componentes futuros; no afirma que exista REST, JWT o persistencia.

## Pruebas

- Eliminadas copias duplicadas/fuera de ubicación de `ValidateInventoryAvailabilityServiceTest`.
- Añadidas pruebas para autorización, carrito, pagos, reembolsos, transiciones y reservas.
- Total actual: 36 métodos de prueba en `src/test/java`.
- Ver `VALIDACION.md` para el detalle de la comprobación local y el comando Maven oficial.

## Decisiones de negocio documentadas

- Return debe estar en RECEIVED para que el reembolso sea elegible.
- Un reembolso PENDING/PROCESSED bloquea otro para esa devolución; un REJECTED permite reintentar.
- El importe máximo lo calcula el caso de uso a partir de datos autoritativos.
- Persistencia concurrente, idempotencia y constraints siguen siendo responsabilidad del adaptador de aplicación/persistencia futuro.
