package org.urdad.services.mocking.example.finance.paymentGateway;

import java.time.LocalDateTime;
import javax.inject.Inject;
import org.urdad.services.mocking.BaseMock;
import org.urdad.services.mocking.Mock;
import org.urdad.services.mocking.example.finance.CouldNotSourceFundsException;
import org.urdad.services.validation.RequestNotValidException;
import org.urdad.services.validation.beanvalidation.ServiceValidationUtilities;

/**
 * @author fritz@solms.co.za
 */
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
        if (getState().equals(State.paymentFailedBecauseSourcingFailed))
            throw new CouldNotCreditDestinationAccountException();
        if (getState().equals(State.paymentFailedBecauseCouldNotCreditDestinationAccount))
            throw new CouldNotCreditDestinationAccountException();
        //State.sourcingAndCreditingSuccessfull
        return new ProcessCreditCardPaymentResponse
            (new CreditCardPaymentConfirmation(request.getCardDetails(), 
                request.getDestinationAccountDetails(), 
                request.getAmount(), LocalDateTime.now()));
    }
    public enum State implements Mock.State{sourcingAndCreditingSuccessfull, 
      paymentFailedBecauseSourcingFailed, 
      paymentFailedBecauseCouldNotCreditDestinationAccount}
    
    @Inject private ServiceValidationUtilities serviceValidationUtilities;
}
