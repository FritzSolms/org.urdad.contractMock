package org.urdad.services.mocking.example.finance;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.urdad.services.messaging.Request;
import org.urdad.services.messaging.Response;
import org.urdad.services.mocking.example.retail.PaymentMethod;
import org.urdad.services.validation.RequestNotValidException;

/**
 *
 * @author fritz@solms.co.za
 */
public interface Finance {
    public ProcessPaymentResponse processPayment(ProcessPaymentRequest request)
            throws RequestNotValidException, CouldNotProcessPaymentException;
    
    public static class ProcessPaymentRequest implements Request {
        public ProcessPaymentRequest(double amount, PaymentMethod paymentMethod) {
                this.amount = amount;
                this.paymentMethod = paymentMethod; }
        private double amount;
        private PaymentMethod paymentMethod;
    }
    public static class ProcessPaymentResponse implements Response {
        public ProcessPaymentResponse(Receipt receipt) {
            this.receipt = receipt;}
        @NotNull @Valid private Receipt receipt;
    }
}
