package org.urdad.services.mocking.example.finance.accounts;

import java.time.LocalDateTime;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import org.urdad.services.mocking.Mock;
import org.urdad.services.mocking.BaseMock;
import org.urdad.services.validation.RequestNotValidException;
import org.urdad.services.mocking.example.finance.CouldNotSourceFundsException;
import org.urdad.services.validation.beanvalidation.ServiceValidationUtilities;

/**
 * @author fritz@solms.co.za
 */
@Service
public class AccountsMock extends BaseMock implements Accounts {

    @Override
    public DebitAccountResponse debitAccount(DebitAccountRequest request) 
            throws RequestNotValidException, CouldNotSourceFundsException {
        serviceValidationUtilities.validateRequest(DebitAccountRequest.class, request);
        if (getState() == State.couldNotSourceFunding)
            throw new CouldNotSourceFundsException();
        if (getState() == State.accountDebitSuccess)
            return new DebitAccountResponse
                (request.getPayer(),request.getAmount(),LocalDateTime.now());
        throw new Mock.InvalidStateException();
    }
    
    public enum State implements Mock.State
        {accountDebitSuccess, couldNotSourceFunding}
    
    @Inject private ServiceValidationUtilities serviceValidationUtilities;
}
