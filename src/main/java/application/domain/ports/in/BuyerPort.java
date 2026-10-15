package application.domain.ports.in;

import application.domain.models.Buyer;
import application.domain.models.Cart;
import application.domain.models.Order;
import application.domain.models.Return;
import application.domain.models.User;
import application.domain.valueobjects.Money;
import java.math.BigDecimal;

public interface BuyerPort {
    BigDecimal calculateCartTotal(User user, Buyer buyer, Cart cart);
    void validateOrderAccess(User user, Buyer buyer, Order order);
    void requestReturn(User user, Buyer buyer, Order order, Return returnRequest);
    void requestRefund(User user, Buyer buyer, Return returnRequest, Money amount);
}
