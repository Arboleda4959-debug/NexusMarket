package application.domain.services.refunds;

import application.domain.exceptions.DomainException;
import application.domain.models.Refund;
import application.domain.models.Return;
import application.domain.ports.out.RefundRepositoryPort;
import application.domain.valueobjects.Money;
import application.domain.valueobjects.RefundStatus;
import application.domain.valueobjects.ReturnStatus;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

class ValidateRefundEligibilityServiceTest {
    private static Money money(String value, String currency) { return new Money(new BigDecimal(value), currency); }
    private static Return receivedReturn() { return new Return("ret-1", "ord-1", "damaged", ReturnStatus.RECEIVED, LocalDateTime.now()); }
    private static RefundRepositoryPort repository(List<Refund> values) {
        return new RefundRepositoryPort() {
            public List<Refund> findByReturnId(String id) { return values; }
            public Optional<Refund> findById(String id) { return values.stream().filter(r -> r.getId().equals(id)).findFirst(); }
            public Refund save(Refund refund) { return refund; }
        };
    }

    @Test void acceptsEligibleRefundWhenNoPriorRefundExists() {
        RefundRepositoryPort repository = repository(List.of());
        var service = new ValidateRefundEligibilityService(repository);
        assertDoesNotThrow(() -> service.execute(receivedReturn(), money("25.00", "COP"), money("40.00", "COP")));
    }
    @Test void rejectsReturnNotYetReceived() {
        RefundRepositoryPort repository = repository(List.of());
        var service = new ValidateRefundEligibilityService(repository);
        Return requested = new Return("ret-1", "ord-1", "damaged", ReturnStatus.REQUESTED, LocalDateTime.now());
        assertThrows(DomainException.class, () -> service.execute(requested, money("25", "COP"), money("40", "COP")));
    }
    @Test void rejectsAmountAboveMaximum() {
        var service = new ValidateRefundEligibilityService(repository(List.of()));
        assertThrows(DomainException.class, () -> service.execute(receivedReturn(), money("41", "COP"), money("40", "COP")));
    }
    @Test void rejectsZeroAmount() {
        var service = new ValidateRefundEligibilityService(repository(List.of()));
        assertThrows(DomainException.class, () -> service.execute(receivedReturn(), money("0", "COP"), money("40", "COP")));
    }
    @Test void rejectsCurrencyMismatch() {
        var service = new ValidateRefundEligibilityService(repository(List.of()));
        assertThrows(DomainException.class, () -> service.execute(receivedReturn(), money("10", "USD"), money("40", "COP")));
    }
    @Test void rejectsPreviouslyProcessedRefund() {
        Refund prior = new Refund("ref-previous", "ret-1", money("25", "COP"), RefundStatus.PROCESSED, LocalDateTime.now());
        var service = new ValidateRefundEligibilityService(repository(List.of(prior)));
        assertThrows(DomainException.class, () -> service.execute(receivedReturn(), money("10", "COP"), money("40", "COP")));
    }
    @Test void rejectsPendingRefundButAllowsRetryAfterRejectedRefund() {
        Refund pending = new Refund("ref-pending", "ret-1", money("25", "COP"), RefundStatus.PENDING, null);
        var blocked = new ValidateRefundEligibilityService(repository(List.of(pending)));
        assertThrows(DomainException.class, () -> blocked.execute(receivedReturn(), money("10", "COP"), money("40", "COP")));
        Refund rejected = new Refund("ref-rejected", "ret-1", money("25", "COP"), RefundStatus.REJECTED, null);
        var retry = new ValidateRefundEligibilityService(repository(List.of(rejected)));
        assertDoesNotThrow(() -> retry.execute(receivedReturn(), money("10", "COP"), money("40", "COP")));
    }
}
