package application.domain.services.payment;

import application.domain.exceptions.DomainException;
import application.domain.models.Order;
import application.domain.models.Payment;
import application.domain.valueobjects.*;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class ValidatePaymentOrderServiceTest {
    private final ValidatePaymentOrderService service = new ValidatePaymentOrderService();
    private Order order(String total, String currency) { return new Order("ord-1", "buyer-1", List.of(), new OrderTotal(new BigDecimal(total), currency), OrderStatus.PAYMENT_PENDING, "pay-1", null); }
    private Payment payment(String orderId, String amount, String currency) { return new Payment("pay-1", orderId, new Money(new BigDecimal(amount), currency), PaymentStatus.PENDING, LocalDateTime.now()); }
    @Test void acceptsMatchingPayment() { assertDoesNotThrow(() -> service.execute(order("100", "COP"), payment("ord-1", "100.00", "COP"))); }
    @Test void rejectsWrongOrder() { assertThrows(DomainException.class, () -> service.execute(order("100", "COP"), payment("ord-2", "100", "COP"))); }
    @Test void rejectsAmountMismatch() { assertThrows(DomainException.class, () -> service.execute(order("100", "COP"), payment("ord-1", "99", "COP"))); }
    @Test void rejectsCurrencyMismatch() { assertThrows(DomainException.class, () -> service.execute(order("100", "COP"), payment("ord-1", "100", "USD"))); }
}
