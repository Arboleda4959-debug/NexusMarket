# Input ports

Las interfaces están en `src/main/java/application/domain/ports/in`. Son contratos del lado de la aplicación y no controladores REST. Se adaptan a los roles de NexusMarket; los nombres bancarios del ejemplo del profesor no se copian literalmente porque pertenecen a otro dominio.

| Puerto | Actor | Operaciones del contrato |
|---|---|---|
| `PublicAccessPort` | Acceso público | Búsqueda/consulta de cuenta por email; autenticación real requerirá política de credenciales fuera de este Domain Foundation. |
| `BuyerPort` | Comprador | Calcular total, validar acceso a pedido, solicitar devolución y solicitar reembolso. |
| `SellerPort` | Vendedor | Validar acceso a producto, reservar inventario y liberar reserva. |
| `LogisticsOperatorPort` | Operador logístico | Validar envío-pedido y transición del estado de envío. |
| `AdministratorPort` | Administrador | Validar autorización para registrar vendedor. |
| `SupervisorPort` | Supervisor | Validar elegibilidad de reembolso. |
| `InternalAnalystPort` | Analista interno | Registrar una entrada de auditoría. |

**Estado:** los contratos Java están declarados. Aún no existen implementaciones de casos de uso en `adapters/usecases`; esto debe completarse si la entrega exige flujos ejecutables de extremo a extremo.
