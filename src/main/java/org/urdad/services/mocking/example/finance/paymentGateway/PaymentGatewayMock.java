package org.urdad.services.mocking.example.finance.paymentGateway;

import java.time.LocalDateTime;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import org.urdad.services.mocking.BaseMock;
import org.urdad.services.mocking.Mock;
import org.urdad.services.mocking.example.finance.CouldNotSourceFundsException;
import org.urdad.services.validation.RequestNotValidException;
import org.urdad.services.validation.beanvalidation.ServiceValidationUtilities;

/**
 * @author fritz@solms.co.za
 */
@Service
public class PaymentGatewayMock extends BaseMock implements PaymentGateway {
    /**
     * Throws
     */
    @Override
    public ProcessCreditCardPaymentResponse processCreditCardPayment
        (ProcessCreditCardPaymentRequest request) 
                throws RequestNotValidException, CouldNotSourceFundsException, 
                CouldNotCreditDestinationAccountException {
        serviceValidationUtilities.validateRequest
            (ProcessCreditCardPaymentRequest.class, request);
        if (getState() == State.paymentFailedBecauseSourcingFailed)
            throw new CouldNotCreditDestinationAccountException();
        if (getState() == State.paymentFailedBecauseCouldNotCreditDestinationAccount)
            throw new CouldNotCreditDestinationAccountException();
        if (getState() == State.sourcingAndCreditingSuccessfull)
            return new ProcessCreditCardPaymentResponse
                (new CreditCardPaymentConfirmation(request.getCardDetails(), 
                    request.getDestinationAccountDetails(), 
                    request.getAmount(), LocalDateTime.now()));
        throw new Mock.InvalidStateException();
    }
    public enum State implements Mock.State{sourcingAndCreditingSuccessfull, 
      paymentFailedBecauseSourcingFailed, 
      paymentFailedBecauseCouldNotCreditDestinationAccount}
    
    @Inject private ServiceValidationUtilities serviceValidationUtilities;
}
