# C4 — vista conceptual

Los diagramas siguientes describen la arquitectura objetivo y distinguen claramente componentes implementados de componentes pendientes.

## Nivel 1 — Contexto

```mermaid
flowchart LR
  Buyer[Comprador] --> NM[NexusMarket]
  Seller[Vendedor] --> NM
  Logistics[Operador logístico] --> NM
  Admin[Administrador / Supervisor] --> NM
  Analyst[Analista interno] --> NM
  NM -. futuro .-> PaymentProvider[Proveedor de pagos]
  NM -. futuro .-> DeliveryProvider[Proveedor logístico]
```

## Nivel 2 — Contenedores lógicos

```mermaid
flowchart TB
  Client[Cliente web/móvil futuro] --> Rest[Adaptador REST futuro]
  Rest --> PortsIn[Input Ports declarados]
  PortsIn --> Domain[Domain Foundation implementado]
  Domain --> PortsOut[Output Ports declarados]
  PortsOut --> Persistence[Adaptadores de persistencia futuros]
  Persistence --> DB[(SQL/NoSQL por decidir)]
```

## Nivel 3 — Dominio

```mermaid
flowchart LR
  Auth[Authorization Services]
  Cart[Cart Services]
  Inv[Inventory Services]
  Order[Order Services]
  Payment[Payment Services]
  ReturnRefund[Return / Refund Services]
  Ship[Shipment Services]
  Auth --> Cart
  Cart --> Inv
  Cart --> Order
  Order --> Payment
  Order --> ReturnRefund
  Order --> Ship
```

## Nota de estado

REST, persistencia, autenticación y casos de uso se dibujan como futuros, no como código ya implementado. El diagrama debe actualizarse cuando se añadan esos componentes.
