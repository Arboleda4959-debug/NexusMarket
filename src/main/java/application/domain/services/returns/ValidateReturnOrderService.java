package application.domain.services.returns;

import application.domain.exceptions.DomainException;
import application.domain.models.Order;
import application.domain.models.Return;
import java.util.Objects;

public class ValidateReturnOrderService {
    public void execute(Order order, Return returnRequest) {
        if (order == null) throw new DomainException("Order is required.");
        if (returnRequest == null) throw new DomainException("Return is required.");
        if (blank(order.getId()) || blank(returnRequest.getOrderId())) throw new DomainException("Order and return identifiers are required.");
        if (!Objects.equals(returnRequest.getOrderId(), order.getId())) throw new DomainException("Return does not belong to the specified order.");
    }
    private boolean blank(String value) { return value == null || value.isBlank(); }
}
