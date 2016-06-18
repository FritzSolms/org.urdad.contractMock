package org.urdad.services.mocking.example.finance;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;
import org.urdad.services.mocking.example.finance.PaymentMethod;

/**
 * @author fritz@solms.co.za
 */
public class AccountPayment extends PaymentMethod {
    public AccountPayment(String accountIdentifier)
    {
        this.accountIdentifier = accountIdentifier;
    }

    public String getAccountIdentifier() {return accountIdentifier;}
    
    @NotNull @NotEmpty private final String accountIdentifier;
}
