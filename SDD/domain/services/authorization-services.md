# Servicios de autorización

## Cadena de propiedad

- Comprador: `User.id == Buyer.userId` y `Cart.buyerId == Buyer.id` o `Order.buyerId == Buyer.id`.
- Vendedor: `User.id == Seller.userId` y `Product.sellerId == Seller.id`.
- Inventario: `Product.id == Inventory.productId`, `Warehouse.id == Inventory.warehouseId`, y producto/almacén pertenecen al vendedor autenticado.

No comparar directamente `User.id` con `Cart.buyerId` ni con `Product.sellerId`: representan identidades distintas.

## Reglas

1. Usuario no nulo, ID presente, estado ACTIVE y rol presente.
2. Para Buyer se exige rol BUYER y perfil Buyer activo.
3. Para Seller se exige rol SELLER y perfil Seller activo.
4. El almacén debe estar activo para operaciones de inventario.
5. La creación de vendedor requiere ADMINISTRATOR activo.
6. El ID y correo únicos se validan consultando `UserRepositoryPort`; la persistencia debe reforzarlo con constraints.

## Overloads legados

`ValidateBuyerResourceAuthorizationService.execute(User, Cart)` y `ValidateSellerResourceAuthorizationService.execute(User, Product)` no reciben el perfil intermedio necesario para probar la relación. Por seguridad fallan cerrado. Los llamadores deben migrar a los overloads que reciben Buyer/Seller.
