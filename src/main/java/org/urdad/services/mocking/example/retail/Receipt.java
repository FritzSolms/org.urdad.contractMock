package org.urdad.services.mocking.example.retail;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import org.urdad.services.mocking.example.Person;

/**
 * @author fritz
 */
public class Receipt {
    public Receipt(Person buyer, double amount, PaymentMethod paymentMethod) {
        this.buyer = buyer;
        this.amount = amount;
        this.paymentMethod = paymentMethod;}

    public Person getBuyer() {return buyer;}
    public double getAmount() {return amount;}
    public PaymentMethod getPaymentMethod() {return paymentMethod;}

    @NotNull @Valid private Person buyer;
    @DecimalMin("0.0") private double amount;
    @NotNull @Valid private PaymentMethod paymentMethod;
}
