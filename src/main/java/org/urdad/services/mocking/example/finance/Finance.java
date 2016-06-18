package org.urdad.services.mocking.example.finance;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.urdad.services.contract.ServiceProviderContract;
import org.urdad.services.messaging.Request;
import org.urdad.services.messaging.Response;
import org.urdad.services.mocking.example.legalEntities.Person;
import org.urdad.services.validation.RequestNotValidException;

/**
 * @author fritz@solms.co.za
 */
@ServiceProviderContract
public interface Finance {
    public ProcessPaymentResponse processPayment(ProcessPaymentRequest request)
            throws RequestNotValidException, CouldNotProcessPaymentException;
    
    public static class ProcessPaymentRequest implements Request {
        public ProcessPaymentRequest(double amount, Person payer,
                PaymentMethod paymentMethod) {
                this.amount = amount;
                this.payer = payer;
                this.paymentMethod = paymentMethod; }

        public double getAmount() {return amount;}
        public Person getPayer() {return payer;}
        public PaymentMethod getPaymentMethod() {return paymentMethod;}

        private double amount;
        private Person payer;
        private PaymentMethod paymentMethod;
    }
    public static class ProcessPaymentResponse implements Response {
        public ProcessPaymentResponse(Receipt receipt) {this.receipt = receipt;}
        
        public Receipt getReceipt() {return receipt;}
        
        @NotNull @Valid private Receipt receipt;
    }
}
