package application.domain.services.status;

import application.domain.exceptions.DomainException;
import application.domain.services.order.ValidateOrderStatusTransitionService;
import application.domain.services.payment.ValidatePaymentStatusTransitionService;
import application.domain.services.refunds.ValidateRefundStatusTransitionService;
import application.domain.services.returns.ValidateReturnStatusTransitionService;
import application.domain.services.shipment.ValidateShipmentStatusTransitionService;
import application.domain.valueobjects.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StatusTransitionServicesTest {
    @Test void orderCannotMoveFromCancelledToPaid() {
        var service = new ValidateOrderStatusTransitionService();
        assertThrows(DomainException.class, () -> service.execute(OrderStatus.CANCELLED, OrderStatus.PAID));
    }
    @Test void paymentCanBeApprovedFromPending() {
        assertDoesNotThrow(() -> new ValidatePaymentStatusTransitionService().execute(PaymentStatus.PENDING, PaymentStatus.APPROVED));
    }
    @Test void paymentCannotBeApprovedTwice() {
        assertThrows(DomainException.class, () -> new ValidatePaymentStatusTransitionService().execute(PaymentStatus.APPROVED, PaymentStatus.APPROVED));
    }
    @Test void returnCannotBeCompletedBeforeReceived() {
        assertThrows(DomainException.class, () -> new ValidateReturnStatusTransitionService().execute(ReturnStatus.APPROVED, ReturnStatus.COMPLETED));
    }
    @Test void refundCanOnlyLeavePendingOnce() {
        var service = new ValidateRefundStatusTransitionService();
        assertDoesNotThrow(() -> service.execute(RefundStatus.PENDING, RefundStatus.PROCESSED));
        assertThrows(DomainException.class, () -> service.execute(RefundStatus.PROCESSED, RefundStatus.REJECTED));
    }
    @Test void deliveredShipmentIsTerminal() {
        assertThrows(DomainException.class, () -> new ValidateShipmentStatusTransitionService().execute(ShipmentStatus.DELIVERED, ShipmentStatus.IN_TRANSIT));
    }
}
