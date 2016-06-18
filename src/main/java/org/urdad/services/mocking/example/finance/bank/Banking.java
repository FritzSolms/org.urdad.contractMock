package org.urdad.services.mocking.example.finance.bank;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.urdad.services.messaging.Request;
import org.urdad.services.messaging.Response;
import org.urdad.services.mocking.example.legalEntities.LegalEntity;
import org.urdad.services.validation.RequestNotValidException;

/**
 * @author fritz@solms.co.za
 */
public interface Banking {
    /**
     * If {@link LegalEntity} not null, returns bank account details of that entity,
     * otherwise returns bank account details of the retail organization itself.
     * 
     * @throws NoBankAccountAvailableException if no bank account details 
     * available for the entity.
     */
    public GetBankAccountDetailsResponse getBankAccountDetails
        (GetBankAccountDetailsRequest request)
                throws RequestNotValidException, NoBankAccountAvailableException;        
        
    public class GetBankAccountDetailsRequest implements Request 
    {
        public GetBankAccountDetailsRequest(LegalEntity legalEntity) {
            this.legalEntity = legalEntity;
        }
        public LegalEntity getLegalEntity() {return legalEntity;}
        
        private LegalEntity legalEntity;
    }
    
    public class GetBankAccountDetailsResponse implements Response 
    {
        public GetBankAccountDetailsResponse(BankAccountDetails accountDetails) {
            this.accountDetails = accountDetails;
        }
        public BankAccountDetails getAccountDetails() {return accountDetails;}
        
        @NotNull @Valid private BankAccountDetails accountDetails;
    }
}
