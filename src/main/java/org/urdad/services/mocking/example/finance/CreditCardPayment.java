package org.urdad.services.mocking.example.finance;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.urdad.services.mocking.example.finance.bank.CreditCardDetails;

/**
 * @author fritz@solms.co.za
 */
public class CreditCardPayment extends PaymentMethod {

   public CreditCardPayment(CreditCardDetails cardDetails) {
        this.cardDetails = cardDetails.clone();}
   public CreditCardDetails getCardDetails() {return cardDetails.clone();}
    
   @NotNull @Valid private CreditCardDetails cardDetails;
}
