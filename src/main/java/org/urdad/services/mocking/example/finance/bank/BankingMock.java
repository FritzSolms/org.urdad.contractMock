package org.urdad.services.mocking.example.finance.bank;

import javax.inject.Inject;
import org.springframework.stereotype.Service;
import org.urdad.services.mocking.BaseMock;
import org.urdad.services.validation.RequestNotValidException;
import org.urdad.services.validation.beanvalidation.ServiceValidationUtilities;

/**
 * @author fritz@solms.co.za
 */
@Service
public class BankingMock extends BaseMock implements Banking {

    /**
     * <ul>
     *   <li> if legalEntity=null, returns account with account holder="owner", 
     * swiftNo="sw111", accountNo="ac111" and bankName="theBank" </li>
     *   <li> if legalEntity.name="accountLess" (any case), throws NoBankAccountAvailableException </li>
     *   <li> otherwise returns account with account holder=legalEntity.name, 
     * swiftNo="sw222", accountNo="ac222" and bankName="theBank" </li>
     * </ul>
     */
    @Override
    public GetBankAccountDetailsResponse getBankAccountDetails
        (GetBankAccountDetailsRequest request) 
                throws RequestNotValidException, NoBankAccountAvailableException {
        // Check pre-condition: Request must be valid.
        serviceValidationUtilities.validateRequest(GetBankAccountDetailsRequest.class, request);
            
        if (request.getLegalEntity() == null)
            return new GetBankAccountDetailsResponse(
                    new BankAccountDetails("theBank", "sw111", "owner", "ac111"));
        if (request.getLegalEntity().getName().trim().toLowerCase().equals("accountLess"))
            throw new NoBankAccountAvailableException();
        return new GetBankAccountDetailsResponse(
                new BankAccountDetails("theBank", "sw222", 
                        request.getLegalEntity().getName(), "ac222"));
    }
    
    @Inject private ServiceValidationUtilities serviceValidationUtilities;
}
