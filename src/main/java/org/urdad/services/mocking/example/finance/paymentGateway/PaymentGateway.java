package org.urdad.services.mocking.example.finance.paymentGateway;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import org.urdad.services.messaging.Request;
import org.urdad.services.messaging.Response;
import org.urdad.services.mocking.example.finance.CouldNotSourceFundsException;
import org.urdad.services.mocking.example.finance.bank.BankAccountDetails;
import org.urdad.services.mocking.example.finance.bank.CreditCardDetails;
import org.urdad.services.validation.RequestNotValidException;

/**
 * @author fritz@solms.co.za
 */
public interface PaymentGateway {
    public ProcessCreditCardPaymentResponse processCreditCardPayment(
        ProcessCreditCardPaymentRequest request)
            throws RequestNotValidException, CouldNotSourceFundsException,
            CouldNotCreditDestinationAccountException;
    
    public class ProcessCreditCardPaymentRequest implements Request {

        public ProcessCreditCardPaymentRequest(CreditCardDetails cardDetails, BankAccountDetails destinationAccountDetails, double amount) {
            this.cardDetails = cardDetails;
            this.destinationAccountDetails = destinationAccountDetails;
            this.amount = amount;
        }

        public CreditCardDetails getCardDetails() {return cardDetails;}
        public BankAccountDetails getDestinationAccountDetails() {return destinationAccountDetails;}
        public double getAmount() {return amount;}
        
        @NotNull @Valid private CreditCardDetails cardDetails;
        @NotNull @Valid private BankAccountDetails destinationAccountDetails;
        @DecimalMin("0.0") private double amount;
    }
    public class ProcessCreditCardPaymentResponse implements Response {

        public ProcessCreditCardPaymentResponse(CreditCardPaymentConfirmation transactionConfirmation) {
            this.transactionConfirmation = transactionConfirmation;}
        @NotNull @Valid private CreditCardPaymentConfirmation transactionConfirmation;
    }
}
