package application.domain.services.refunds;

import application.domain.exceptions.DomainException;
import application.domain.models.Refund;
import application.domain.models.Return;
import application.domain.ports.out.RefundRepositoryPort;
import application.domain.valueobjects.Money;
import application.domain.valueobjects.RefundStatus;
import application.domain.valueobjects.ReturnStatus;
import java.util.List;
import java.util.Objects;

/**
 * Policy for this foundation: a return may have one successful refund at a time.
 * A pending refund blocks duplicates; a rejected refund may be retried by creating
 * a new refund. The maximum refundable amount must be calculated by the application
 * use case from authoritative payment/order/returned-item data and passed in here.
 */
public class ValidateRefundEligibilityService {
    private final RefundRepositoryPort refundRepository;
    public ValidateRefundEligibilityService(RefundRepositoryPort refundRepository) {
        this.refundRepository = Objects.requireNonNull(refundRepository, "Refund repository port is required");
    }

    public void execute(Return returnRequest, Money requestedAmount, Money maximumRefundableAmount) {
        if (returnRequest == null) throw new DomainException("Return is required.");
        if (blank(returnRequest.getId())) throw new DomainException("Return identifier is required.");
        if (returnRequest.getStatus() != ReturnStatus.RECEIVED) {
            throw new DomainException("A return must be received before its refund can be processed.");
        }
        validateMoney(requestedAmount, "Requested refund amount");
        validateMoney(maximumRefundableAmount, "Maximum refundable amount");
        if (requestedAmount.amount().signum() <= 0) throw new DomainException("Refund amount must be greater than zero.");
        if (!requestedAmount.currency().equals(maximumRefundableAmount.currency())) {
            throw new DomainException("Refund currency must match the refundable amount currency.");
        }
        if (requestedAmount.amount().compareTo(maximumRefundableAmount.amount()) > 0) {
            throw new DomainException("Refund amount exceeds the maximum refundable amount.");
        }
        List<Refund> previous = refundRepository.findByReturnId(returnRequest.getId());
        if (previous == null) throw new DomainException("Refund repository returned an invalid result.");
        for (Refund existing : previous) {
            if (existing == null) throw new DomainException("Refund history contains an invalid record.");
            if (existing.getStatus() == null) throw new DomainException("Refund history contains a refund without status.");
            if (existing.getStatus() == RefundStatus.PENDING || existing.getStatus() == RefundStatus.PROCESSED) {
                throw new DomainException("This return already has a pending or processed refund.");
            }
        }
    }

    private void validateMoney(Money money, String label) {
        if (money == null || money.amount() == null || money.currency() == null || money.currency().isBlank())
            throw new DomainException(label + " is required and must be valid.");
    }
    private boolean blank(String value) { return value == null || value.isBlank(); }
}
