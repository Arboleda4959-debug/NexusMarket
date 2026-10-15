package application.domain.ports.in;

import application.domain.models.Inventory;
import application.domain.models.Product;
import application.domain.models.Seller;
import application.domain.models.User;
import application.domain.models.Warehouse;
import application.domain.valueobjects.Quantity;

public interface SellerPort {
    void validateProductAccess(User user, Seller seller, Product product);
    void reserveInventory(Seller seller, Product product, Warehouse warehouse, Inventory inventory, Quantity quantity);
    void releaseInventoryReservation(Seller seller, Product product, Warehouse warehouse, Inventory inventory, Quantity quantity);
}
