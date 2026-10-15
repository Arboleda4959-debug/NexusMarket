# Input ports (ports in)

Los puertos de entrada describen operaciones de aplicación solicitadas por los actores de NexusMarket. En esta etapa Domain Foundation, las interfaces establecen contratos y no implementan HTTP, autenticación JWT ni persistencia.

Interfaces iniciales: `PublicAccessPort`, `BuyerPort`, `SellerPort`, `LogisticsOperatorPort`, `AdministratorPort`, `SupervisorPort` e `InternalAnalystPort`. Las implementaciones concretas pertenecen a `application.adapters.usecases` y deben invocar servicios de dominio; no se agregan controladores REST hasta que el alcance de la entrega lo exija.
