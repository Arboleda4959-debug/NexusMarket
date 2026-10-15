package application.domain.ports.in;

import application.domain.models.Return;
import application.domain.valueobjects.Money;

public interface SupervisorPort {
    void validateRefundEligibility(Return returnRequest, Money requestedAmount, Money maximumRefundableAmount);
}
