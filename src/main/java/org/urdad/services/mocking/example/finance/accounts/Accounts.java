package org.urdad.services.mocking.example.finance.accounts;

import java.time.LocalDateTime;
import java.util.Date;
import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import org.urdad.services.messaging.Request;
import org.urdad.services.messaging.Response;
import org.urdad.services.mocking.example.legalEntities.Person;
import org.urdad.services.mocking.example.finance.CouldNotSourceFundsException;
import org.urdad.services.validation.RequestNotValidException;

/**
 * @author fritz@solms.co.za
 */
public interface Accounts {
    public DebitAccountResponse debitAccount(DebitAccountRequest request)
            throws RequestNotValidException, CouldNotSourceFundsException;
    public static class DebitAccountRequest implements Request {

        public Person getPayer() {return payer;}
        public double getAmount() {return amount;}

        public DebitAccountRequest(Person payer, double amount) {
            this.payer = payer;
            this.amount = amount;
        }
        private Person payer;
        private double amount;
    }
    public class DebitAccountResponse implements Response {
        public DebitAccountResponse(Person payer, double amount, 
                LocalDateTime paymentDate) {
            this.payer = payer;
            this.amount = amount;
            this.paymentDate = paymentDate;}
        
        public Person getPayer() {return payer;}
        public double getAmount() {return amount;}
        public LocalDateTime getPaymentDate() {return paymentDate;}

        @NotNull @Valid private Person payer;
        @DecimalMin("0.0") private double amount;
        @Past private LocalDateTime paymentDate;
    }
}
