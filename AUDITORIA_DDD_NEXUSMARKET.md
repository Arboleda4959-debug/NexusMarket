# Auditoría técnica de NexusMarket — DDD, SDD y brechas de implementación

**Fecha:** 16/10/2026  
**Base revisada:** `1NexusMarket_Domain_Foundation_v2` del ZIP compartido.  
**Referencia de comparación:** repositorio del profesor `construccion_de_software_2_2026_2-main`, especialmente `SDD/enunciado evaluativo.md`, `SDD/enunciado.md` y la estructura SDD del ejemplo.

## Resumen ejecutivo

NexusMarket ya tiene una base de dominio considerable: 19 modelos, 26 tipos de valor/enums, servicios de dominio separados por área, 7 puertos de entrada, 15 puertos de salida y 36 pruebas registradas en los reportes Surefire incluidos. Los reportes muestran **36 pruebas, 0 fallos, 0 errores y 0 omitidas**.

El proyecto todavía debe considerarse una **fundación de dominio**, no una aplicación web terminada. Los adaptadores REST y persistencia son marcadores `README.md`; no se encontraron clases Java de implementación en `application/adapters/in/rest` ni `application/adapters/out/persistence`. Tampoco hay casos de uso concretos que implementen los puertos de entrada. El `App` actual imprime `NexusMarket - domain foundation`. Docker confirma que esa clase se ejecuta en un contenedor; no demuestra que exista una API o una base de datos.

Esta auditoría distingue:
- **Implementado:** se puede observar en el código o en los reportes incluidos.
- **Parcial:** existe el contrato o validador, pero falta la coordinación, persistencia o protección necesaria.
- **Pendiente:** no se encontraron implementaciones concretas.

No se ha asignado una nota oficial. La rúbrica del profesor es una guía para identificar riesgos, no permite garantizar la calificación.

---

## 1. Auditoría frente a la rúbrica de Etapa 1 DDD

### Criterio 1 — Especificación SDD (20 %)

**Fortalezas**
- Existe `SDD/domain/Domain Model.md`.
- Existe `SDD/domain/Domain Value Objects.md`.
- Existe `SDD/domain/Domain Services.md` y documentación de servicios por área.
- Existen `SDD/domain/Input-ports.md`, `SDD/domain/Output-ports.md`, `SDD/Software Architecture/Software Architecture.md`, `SDD/C4/README.md` y `SDD/requirements-and-decisions.md`.
- La documentación diferencia expresamente componentes implementados de los futuros.

**Brechas**
- `SDD/C4/README.md` solo presenta una vista conceptual resumida. El ejemplo tiene diagramas separados por niveles y componentes. Para la etapa de modelado, esto es una mejora documental; no es prueba de que debamos implementar todo C4.
- El modelo de dominio necesita una tabla de trazabilidad más exhaustiva: cada atributo importante, tipo Java, invariante, constructor/método que la aplica y prueba asociada.
- La documentación de reglas debe indicar con claridad cuáles son reglas locales de dominio y cuáles requieren transacción, constraint o idempotencia en persistencia.
- El documento de arquitectura describe puertos y adaptadores futuros correctamente, pero el README principal debe mantener esa misma distinción y no presentar el proyecto como una aplicación funcional de extremo a extremo.

**Prioridad:** alta para la entrega de modelado. Mantener la documentación coherente con el código en cada cambio.

### Criterio 2 — Modelado de entidades Java (30 %)

**Fortalezas**
- Las entidades están agrupadas en `application/domain/models`.
- Hay separación entre `User` y perfiles `Buyer` / `Seller`.
- Se modelan productos, almacenes, inventario, reservas, movimientos, carritos, pedidos, pagos, facturas, envíos, devoluciones, reembolsos y auditoría.
- `Cart` y `Order` devuelven listas no modificables, evitando modificación externa directa de sus colecciones.
- `InventoryReservation` protege las operaciones de liberar/consumir una reserva activa.

**Hallazgos que conviene corregir**
1. **Validación desigual en constructores.** `User`, `Product`, `Seller`, `Warehouse`, `Inventory`, `CartItem`, `OrderItem`, `InventoryMovement` e `InventoryReservation` aplican algunas validaciones. En cambio, los constructores de `Invoice`, `Operation`, `AuditLog`, `Order`, `Payment`, `Refund`, `Return` y `Shipment` no validan de forma consistente IDs, referencias, estados ni valores obligatorios. Esto permite que se creen objetos inválidos y que el error aparezca más tarde en un servicio.
2. **Estados mutables sin transición aplicada por la entidad.** Hay servicios `Validate*StatusTransitionService` que validan pares de estados, pero validar un par no cambia el estado del objeto. Antes de integrar casos de uso, debe existir una operación de dominio que valide y aplique el cambio de forma coordinada, o un patrón equivalente probado. No añadir setters públicos que permitan saltarse las transiciones.
3. **Identificadores de relaciones como `String`.** Esto puede ser aceptable para una fundación independiente de persistencia, pero hay que validar que no sean nulos/vacíos donde la regla lo requiera y documentar las relaciones. No hace falta convertir todos los IDs en objetos distintos si la rúbrica no lo exige.
4. **`Invoice` y `Operation` parecen más estructuras de datos que entidades con invariantes.** Definir sus reglas mínimas (IDs, número de factura, total, fecha, actor y tipo de operación) antes de implementar casos de uso.
5. **Mutaciones de perfil y catálogo.** Revisar que los cambios de nombre, precio, estado y datos de perfil validen entradas y no permitan estados comerciales inválidos.

**Prioridad:** alta. Corregir entidades en grupos pequeños y agregar pruebas para cada invariante, sin hacer cambios masivos sin ejecutar la suite.

### Criterio 3 — Value Objects frente a primitivos (25 %)

**Fortalezas**
- Se utilizan enums para roles y estados (`UserRole`, `UserStatus`, `BuyerStatus`, `SellerStatus`, `ProductStatus`, `OrderStatus`, `PaymentStatus`, `ReturnStatus`, `RefundStatus`, `ShipmentStatus`, etc.).
- Existen records para `Email`, `Money`, `ProductPrice`, `OrderTotal`, `Quantity`, `InventoryQuantity`, `Address`, `ContactInformation` y `TrackingCode`.
- `Email` normaliza el valor; `InventoryQuantity` impide existencias negativas; los artículos del carrito/pedido y los movimientos requieren cantidad positiva.

**Brechas**
1. `ProductStatus` conserva `ACTIVE`, `INACTIVE` y `OUT_OF_STOCK` como valores obsoletos. Esto facilita compatibilidad, pero mantiene estados ambiguos. Migrar llamadas y pruebas a `PUBLISHED`, `SUSPENDED` y `DISCONTINUED`; retirar los valores obsoletos solo cuando no existan consumidores.
2. `Money`, `ProductPrice` y `OrderTotal` guardan la moneda como `String`. La moneda es un código acotado; validar y normalizar mayúsculas, y considerar un VO/enum de moneda si la especificación lo necesita. Evitar sumar importes de monedas diferentes.
3. `Quantity` permite cero por diseño. Las operaciones de reserva, liberación y movimiento ya deben exigir `> 0`; mantener esa regla en el servicio/constructor de operación y cubrir los casos de cero con pruebas.
4. `Address` debe validar los campos obligatorios con reglas que coincidan con la especificación del marketplace; `ContactInformation` debe validar el teléfono si la regla funcional fija formato o longitud.
5. Revisar todos los atributos de estado/tipo en entidades para confirmar que usan enums y no `String`. El escaneo general de código debe acompañarse de revisión semántica; una `String` para descripción o nombre no es un error de “primitivos”.

**Prioridad:** alta por el peso del criterio. No convertir campos descriptivos normales en Value Objects por cumplir una cuota; crear VOs cuando representen reglas del dominio.

### Criterio 4 — Idioma y convenciones Java (15 %)

**Fortalezas**
- Los nombres de clases, paquetes, métodos y tipos de dominio observados están mayoritariamente en inglés.
- El paquete raíz sigue un esquema consistente: `application.domain.models`, `valueobjects`, `services`, `ports`.

**Mejoras**
- Mantener nombres de métodos y errores en inglés de forma uniforme.
- Eliminar imports sin uso y formatear las clases que tienen múltiples declaraciones en una línea (por ejemplo, algunas clases de inventario y artículos), para que el código sea más fácil de revisar en una sustentación.
- El warning de deprecación registrado en `AuthorizationServicesTest` debe resolverse migrando las pruebas del overload legado a la API que recibe el perfil Buyer/Seller; conservar el overload solo si se necesita compatibilidad y dejar clara su retirada futura.

**Prioridad:** media; mejora mantenibilidad y presentación.

### Criterio 5 — Correlación SDD vs. Java (10 %)

**Fortalezas**
- Los documentos SDD nombran servicios y tipos que existen en Java.
- `SDD/requirements-and-decisions.md` distingue explícitamente lo implementado de la infraestructura pendiente.
- Las decisiones de autorización documentan la cadena `User → Buyer → Cart/Order` y `User → Seller → Product`.

**Brechas**
- Añadir una matriz por clase: requisito → archivo SDD → clase/método Java → prueba → estado.
- Actualizar la matriz cada vez que se añada o renombre una clase.
- Verificar los atributos de cada entidad contra `SDD/domain/Domain Model.md`; una tabla de responsabilidades no sustituye una lista completa de campos e invariantes.
- En documentación de servicios, no decir “implementado” si solo existe validación de datos y falta la consulta/persistencia/operación coordinada que la regla necesita.

**Prioridad:** alta para la entrega de modelado.

---

## 2. Relaciones e invariantes de negocio

### Autorización de comprador
La relación correcta es:
- `User.id == Buyer.userId`
- `Cart.buyerId == Buyer.id`
- `Order.buyerId == Buyer.id`

`ValidateBuyerCartAuthorizationService` recibe User, Buyer y Cart y verifica esa cadena. Los overloads antiguos que no reciben Buyer deben fallar cerrado, porque no pueden probar la relación completa. Los casos de uso futuros deben llamar la variante segura.

### Autorización de vendedor
La relación correcta es:
- `User.id == Seller.userId`
- `Product.sellerId == Seller.id`

`ValidateSellerResourceAuthorizationService` recibe Seller en su overload seguro. No comparar directamente `User.id` con `Product.sellerId`.

### Inventario
- `Inventory.productId` debe coincidir con el producto.
- `Inventory.warehouseId` debe coincidir con la bodega.
- Producto y bodega deben pertenecer al vendedor autorizado.
- `available` y `reserved` nunca pueden ser negativos.
- La cantidad solicitada debe ser positiva.
- Una reserva debe tener identidad para impedir liberar dos veces la misma reserva.
- La garantía contra sobreventa necesita operación atómica en el adaptador de persistencia; el objeto Java por sí solo no resuelve carreras entre procesos.

### Pedido y pago
- Confirmar que pago y pedido se refieren al mismo pedido.
- El importe y la moneda del pago total deben coincidir con el total del pedido.
- La consulta de pagos anteriores ayuda a detectar duplicados, pero no garantiza exclusión entre dos solicitudes concurrentes.
- Las transiciones terminales del pedido no deben poder reabrirse.
- Las referencias `paymentId` y `shipmentId` deben verificarse cuando el caso de uso las utilice.

### Devolución y reembolso
- La devolución debe pertenecer al pedido correcto.
- La política SDD actual exige `ReturnStatus.RECEIVED` antes de procesar reembolso.
- El reembolso debe tener importe positivo, moneda correcta y no exceder el máximo calculado con datos autoritativos.
- `ValidateRefundEligibilityService` bloquea reembolsos anteriores `PENDING` o `PROCESSED`, pero la consulta no impide duplicados concurrentes sin una restricción/idempotencia en persistencia.
- La política de reembolsos parciales, múltiples artículos y ventanas de devolución debe confirmarse con los requisitos del curso antes de implementarla.

---

## 3. SDD contra implementación: estado real

| Componente | Estado observado | Qué falta |
|---|---|---|
| Modelos de dominio | Implementados | Validación consistente de invariantes y comportamiento de entidades |
| Value Objects / enums | Implementados | Depurar estados obsoletos y validar códigos monetarios/campos |
| Servicios de dominio | Implementados para varias validaciones | Cubrir todas las reglas con pruebas y coordinar acciones en casos de uso |
| Puertos de entrada | Interfaces declaradas | Casos de uso concretos que los implementen |
| Puertos de salida | Interfaces declaradas | Adaptadores concretos y garantías transaccionales |
| Adaptador REST | Solo README en el directorio | Controladores, DTOs, validación y manejo de errores, si lo exige la entrega |
| Persistencia | Solo README en el directorio | Repositorios, mappers, entidades de persistencia y configuración, si lo exige la entrega |
| Autenticación/JWT | Documentada como futura | Implementación, solo si la exige el alcance |
| Docker | Dockerfile y Compose funcionan para el `App` actual | No es una API ni una base de datos; actualizar si aparecen dependencias/runtime nuevos |
| Pruebas | Reportes Surefire: 36/36 aprobadas | Agregar pruebas de invariantes, entidades y casos límite |
| Documentación C4 | Vista conceptual resumida | Diagramas separados si el entregable exige el nivel de detalle del ejemplo |

### Importante sobre el ejemplo del profesor
El repositorio de referencia describe un **banco**, mientras NexusMarket es un **marketplace**. Debemos reutilizar patrones de organización, puertos, servicios, adaptadores y documentación; no copiar entidades ni reglas bancarias. La rúbrica incluye roles bancarios en algunos apartados, pero la especificación de modelado también menciona los roles de NexusMarket. Si hay contradicción en la consigna de evaluación, confirmar con el profesor qué parte aplica a la entrega.

---

## 4. Plan de corrección recomendado

### Fase 1 — Consolidar la etapa DDD (antes de infraestructura)
1. Crear matriz completa de requisitos/atributos/invariantes con evidencia Java y prueba.
2. Añadir pruebas de constructores para entidades que aceptan valores nulos, IDs vacíos o estados faltantes.
3. Centralizar la aplicación de transiciones de estado para que los servicios de validación no sean la única protección.
4. Migrar tests y usos de `ProductStatus.ACTIVE/INACTIVE/OUT_OF_STOCK` a estados canónicos.
5. Validar formato de teléfono/dirección/moneda según reglas explícitas del negocio.
6. Ejecutar `.\mvnw.cmd clean test` y conservar el resultado. No dar por terminada una corrección que no compile.

### Fase 2 — Completar los flujos de aplicación
1. Implementar casos de uso que implementen los input ports.
2. Inyectar servicios de dominio y puertos de salida; mantener el dominio libre de Spring, HTTP y SQL.
3. Definir qué operaciones requieren guardar y qué deben hacer si falla una persistencia.
4. Probar los casos de uso con dobles/fakes de los puertos.

### Fase 3 — Infraestructura solo si la entrega lo pide
1. Definir el stack solicitado por el profesor.
2. Implementar adaptadores de persistencia y constraints de unicidad/referencias.
3. Asegurar transacciones/locking/idempotencia para inventario, pagos y reembolsos.
4. Añadir REST, autenticación, DTOs y manejo de errores.
5. Actualizar Docker/Compose para ejecutar el servicio real y sus dependencias.
6. Actualizar diagramas C4 y README a la implementación real.

---

## 5. Comandos de verificación local

Desde la carpeta que contiene `pom.xml`:

```powershell
.\mvnw.cmd clean test
docker compose build
docker compose run --rm nexusmarket
docker compose config
```

Resultados esperados para la versión actual:
- Maven: `BUILD SUCCESS`.
- Suite actual: 36 tests, 0 failures, 0 errors, 0 skipped (según reportes Surefire incluidos).
- Docker: `NexusMarket - domain foundation`.
- Compose: servicio `nexusmarket` con imagen `nexusmarket:1.0`.

**No se pudo volver a ejecutar Maven en este entorno de auditoría porque Maven Wrapper no pudo descargar la distribución de Maven desde Maven Central.** Por eso los resultados de pruebas se verificaron leyendo los reportes Surefire que vienen en el ZIP; deben volver a ejecutarse localmente después de cada cambio.

---

## Conclusión

NexusMarket tiene una base útil y coherente para la etapa de dominio. Las brechas más importantes no son “tener pocas tecnologías”, sino: invariantes inconsistentes en algunas entidades, transiciones que se validan pero no necesariamente se aplican desde el propio agregado, falta de casos de uso concretos y ausencia de adaptadores ejecutables. La prioridad es consolidar DDD y la trazabilidad SDD-código antes de añadir REST o bases de datos.

Este documento es una auditoría técnica de apoyo, no una calificación oficial del profesor.

## Actualización de implementación — 16 de octubre de 2026

Se aplicó una primera corrección incremental a los constructores de `Invoice`, `Operation`, `AuditLog`, `Order`, `Payment`, `Refund`, `Return` y `Shipment`:
- identificadores y referencias obligatorias ahora rechazan `null`, cadenas vacías o espacios;
- objetos de valor, estados y fechas obligatorias ahora rechazan `null`;
- `AuditLog.details` normaliza `null` a cadena vacía y recorta espacios;
- los campos opcionales que ya eran compatibles con el flujo existente, como `Refund.processedAt` y `Payment.processedAt`, siguen permitiendo `null`.

La corrección es deliberadamente conservadora para mantener compatibilidad con las pruebas y los servicios actuales. **No se pudo confirmar una ejecución nueva de Maven después de estos cambios en este entorno**; se debe ejecutar `./mvnw.cmd clean test` desde la raíz del proyecto en Windows antes de entregar. La implementación de cambios de estado atómicos, validación de moneda y casos de uso concretos queda pendiente.
