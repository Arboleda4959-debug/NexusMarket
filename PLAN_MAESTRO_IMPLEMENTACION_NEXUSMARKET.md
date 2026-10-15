# Plan maestro de implementación — NexusMarket

**Fecha:** 16/10/2026  
**Base revisada:** ZIP `NexusMarket_DDD_Fase1` compartido por el estudiante.  
**Referencia del curso:** ZIP `construccion_de_software_2_2026_2-main` entregado por el profesor.

## 1. Conclusión ejecutiva

NexusMarket tiene una base de dominio Java con entidades, value objects/enums, servicios de dominio, puertos y pruebas unitarias. La última ejecución local comunicada por el estudiante terminó con `BUILD SUCCESS`.

Eso es una buena base, pero no es todavía una aplicación de marketplace completa. En el código inspeccionado, `application.App` imprime `NexusMarket - domain foundation`; los directorios de adaptadores REST y persistencia contienen README de orientación, no implementaciones funcionales; y no se encontraron clases concretas de casos de uso que implementen los puertos de entrada.

**Objetivo:** evolucionar el proyecto sin borrar ni reemplazar a ciegas lo que ya existe, hasta que los flujos principales puedan ejecutarse y demostrarse en el examen final.

## 2. Qué aporta realmente el ZIP del profesor

El ZIP de referencia contiene un proyecto de ejemplo de **gestión bancaria**, con Spring Boot, JPA/MySQL, MongoDB, seguridad y documentación extensa. Sus entidades y reglas bancarias no deben copiarse como si fueran requisitos de NexusMarket.

Lo más relevante para NexusMarket es `SDD/enunciado evaluativo.md`, que define la evaluación de la etapa de modelado DDD:
- SDD: 20%.
- Modelado de entidades Java: 30%.
- Value Objects frente a primitivos: 25%.
- Idioma y convenciones Java: 15%.
- Correspondencia SDD/código: 10%.

La rúbrica menciona expresamente entidades del marketplace, estados y tipos acotados, validaciones de negocio y arquitectura hexagonal. También describe puertos de entrada por rol, casos de uso concretos, REST/DTOs, JWT y adaptadores de persistencia.

**Límite documental importante:** el ZIP del profesor hace referencia a `especificacion_funcional_nexusmarket.md`, pero ese archivo no está incluido en el ZIP recibido. Por tanto, no se deben inventar reglas de negocio ausentes; hay que confirmar con el estudiante/profesor la especificación funcional completa si existe por separado.

## 3. Estado observado en la versión compartida

### Ya existe
- Código Java organizado por paquetes de dominio.
- Modelos del marketplace: usuarios y perfiles, productos, almacenes, inventario, carrito, pedidos, pagos, factura, envío, devoluciones, reembolsos, movimientos y reservas.
- Value objects y enums para varios estados, roles, cantidades y valores.
- Servicios de dominio para autorización, carrito, inventario, pedidos, pagos, devoluciones, reembolsos y envíos.
- Interfaces `ports/in` y `ports/out`.
- Documentación SDD y decisiones de dominio.
- Pruebas JUnit y reportes Surefire incluidos en el ZIP.
- Dockerfile/Compose para ejecutar la aplicación mínima de consola.

### Falta para que sea una aplicación funcional de extremo a extremo
- Casos de uso concretos que implementen los puertos de entrada y coordinen servicios.
- Adaptador REST con controladores, request/response DTOs y manejo de errores.
- Persistencia real con entidades de infraestructura, mappers y repositorios.
- Transacciones y restricciones para evitar inconsistencias concurrentes.
- Autenticación y autorización en la frontera HTTP, si forman parte del alcance acordado.
- Interfaz de usuario.
- Pruebas de integración que recorran flujos completos.
- Configuración de ejecución que arranque API y base de datos; el Docker actual solo ejecuta el `App` de consola.

## 4. Arquitectura objetivo

Mantener las dependencias hacia el dominio y no introducir Spring/JPA/HTTP dentro de las entidades ni de los servicios de dominio.

```text
Cliente web
    |
    v
REST Controllers + DTOs                 [pendiente]
    |
    v
Input Ports (interfaces ya declaradas)
    |
    v
Use Cases / Application Services         [pendiente]
    |
    +----> Domain Services / Models       [base existente]
    |
    v
Output Ports (interfaces ya declaradas)
    |
    v
Persistence Adapters + Mappers           [pendiente]
    |
    v
Base de datos                            [por confirmar/configurar]
```

La implementación debe seguir la estructura y los nombres reales del proyecto actual. No crear una segunda jerarquía paralela de modelos ni duplicar servicios que ya existen.

## 5. Orden recomendado de implementación

### Fase 0 — Congelar y validar la base
1. Conservar la versión actual como respaldo.
2. Ejecutar `.\mvnw.cmd clean test`.
3. Registrar el total real de pruebas y guardar la salida completa.
4. Revisar los cambios de Fase 1 clase por clase y añadir pruebas para cada invariante nueva.
5. No aceptar una modificación que haga pasar una prueba mientras rompa otra regla existente.

**Criterio de salida:** compilación y pruebas exitosas; diferencias de código identificadas.

### Fase 1 — Cerrar reglas del dominio
1. Revisar constructores y métodos mutadores de cada entidad.
2. Completar invariantes sin convertir cada texto descriptivo en un Value Object innecesario.
3. Normalizar y validar moneda en `Money`, `ProductPrice` y `OrderTotal`.
4. Alinear estados canónicos y retirar estados legados solo cuando sus consumidores y pruebas hayan migrado.
5. Hacer que las transiciones válidas cambien el estado de manera controlada; no dejar la regla únicamente en un validador que comprueba y no aplica el cambio.
6. Agregar pruebas positivas y negativas para cada regla.
7. Mantener documentada la diferencia entre validación local y reglas que necesitan persistencia atómica.

**Criterio de salida:** reglas del dominio ejecutables y cubiertas por pruebas.

### Fase 2 — Casos de uso de aplicación
Implementar clases concretas para los puertos de entrada existentes. Los casos de uso deben:
- comprobar el actor y su rol/perfil;
- recuperar entidades mediante puertos de salida;
- invocar los servicios de dominio en el orden correcto;
- guardar resultados mediante puertos;
- definir límites transaccionales;
- devolver resultados claros o errores de aplicación.

Empezar por el recorrido vertical mínimo: consultar catálogo, registrar/administrar producto, crear carrito, agregar artículo y calcular total. Luego continuar con pedido, reserva de inventario, pago simulado, envío y devolución/reembolso.

**Criterio de salida:** los flujos pueden probarse sin depender de HTTP ni de la interfaz.

### Fase 3 — Persistencia
1. Elegir la base de datos y confirmar la elección con el alcance del curso.
2. Implementar adaptadores para los puertos de salida que los casos de uso necesitan primero.
3. Crear mappers entre dominio y entidades de persistencia; no reutilizar entidades JPA como modelos de dominio.
4. Añadir claves únicas, relaciones, restricciones y migraciones/esquema.
5. Asegurar atomicidad en la reserva de inventario y protección contra operaciones duplicadas.
6. Añadir pruebas de integración.

**Criterio de salida:** los datos sobreviven al reinicio y las reglas críticas no dependen solo de validaciones en memoria.

### Fase 4 — API y seguridad
1. Crear controladores REST y DTOs de entrada/salida.
2. Mapear errores de dominio a respuestas HTTP consistentes.
3. Validar entradas antes de ejecutar casos de uso.
4. Implementar login/contraseñas y JWT solo si está confirmado como parte del alcance.
5. Aplicar autorización por rol y propiedad de los recursos en el backend, no solo ocultando botones del frontend.
6. Documentar endpoints y ejemplos de solicitudes/respuestas.

**Criterio de salida:** los flujos principales pueden ejecutarse mediante HTTP y los accesos no autorizados son rechazados.

### Fase 5 — Interfaz y demostración
1. Construir pantallas según los roles y los casos de uso ya funcionales.
2. Conectar la interfaz a la API real.
3. Mostrar errores de validación y estados de las operaciones.
4. Crear datos de demostración no sensibles.
5. Preparar un guion del examen: inicio, catálogo, carrito, pedido, inventario, pago simulado, seguimiento, devolución y permisos.
6. Mantener Docker/Compose alineado con la arquitectura real; no presentar el contenedor de consola como despliegue completo.

**Criterio de salida:** una demostración repetible de punta a punta, con pruebas automatizadas y documentación.

## 6. Primer flujo vertical que se recomienda construir

Construir un solo flujo completo antes de añadir todas las funcionalidades:

1. Consultar productos publicados.
2. Validar que el producto puede venderse.
3. Validar que el comprador está autorizado y que su perfil corresponde a su usuario.
4. Añadir producto y cantidad al carrito.
5. Calcular el total y validar moneda.
6. Crear pedido y sus artículos.
7. Reservar inventario de forma atómica.
8. Simular o registrar el pago sin integrar dinero real.
9. Confirmar el pedido solo si se cumplen las condiciones.
10. Probar casos de error: producto suspendido, cantidad inválida, stock insuficiente, perfil que no corresponde, moneda distinta y repetición de operación.

Este flujo sirve de patrón para envíos, devoluciones y reembolsos. No se debe integrar un proveedor de pago real hasta que exista una necesidad explícita y un diseño de seguridad adecuado.

## 7. Reglas de calidad para cada entrega

- Cambios pequeños y revisables; no reemplazar carpetas enteras por código generado sin comparar.
- Cada regla de negocio debe tener una prueba.
- Cada cambio de regla debe actualizar el SDD.
- No afirmar que un servicio está implementado si solo existe una interfaz o README.
- No afirmar que existe una API/base de datos porque Docker arranque.
- Mantener los nombres de clases, atributos, métodos y paquetes Java en inglés, de acuerdo con la rúbrica.
- Mantener el dominio libre de frameworks e infraestructura.
- Para funciones sensibles, probar autorización negativa y acceso a recursos de otro usuario.
- Ejecutar `.\mvnw.cmd clean test` después de cada bloque de cambios.
- Para funciones de persistencia/API, añadir pruebas de integración, no solo unitarias.

## 8. Lista de comprobación del examen final

- [ ] `.\mvnw.cmd clean test` termina en `BUILD SUCCESS`.
- [ ] Hay un comando documentado para iniciar la aplicación completa.
- [ ] Los datos se guardan en una base de datos real y sobreviven al reinicio.
- [ ] Hay una forma documentada de crear/cargar datos de demostración.
- [ ] Los flujos de compra y gestión de inventario funcionan de punta a punta.
- [ ] Las reglas de rol y propiedad se verifican en el backend.
- [ ] No se permiten cantidades negativas ni reservas duplicadas.
- [ ] Las transiciones de estado inválidas son rechazadas.
- [ ] Los pagos/reembolsos duplicados están protegidos.
- [ ] Hay pruebas de integración para los flujos principales.
- [ ] SDD, código, endpoints y pruebas coinciden.
- [ ] Docker/Compose arranca los componentes que realmente forman parte de la aplicación.
- [ ] Existe un guion de demostración y el estudiante puede explicar las capas.

## 9. Próxima acción concreta

La próxima iteración debe ser una revisión de compatibilidad de los cambios de Fase 1 y, después, la implementación de **un primer caso de uso completo de catálogo/carrito** usando los puertos y servicios que ya existen. Antes de elegir base de datos, frontend y autenticación, confirmar si el profesor entregó la especificación funcional NexusMarket por separado, porque el ZIP de referencia no incluye ese documento.

Este plan es una hoja de ruta técnica, no una afirmación de que las fases pendientes ya estén implementadas.
