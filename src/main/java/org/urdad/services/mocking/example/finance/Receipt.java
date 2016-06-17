package org.urdad.services.mocking.example.finance;

import java.time.LocalDateTime;
import java.util.Date;
import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import org.urdad.services.mocking.example.legalEntities.Person;

/**
 * @author fritz@solms.co.za
 */
public class Receipt {

    public Receipt(double amount, Person payer, 
            PaymentMethod paymentMethod, LocalDateTime paymentDate) {
        this.amount = amount;
        this.payer = payer;
        this.paymentMethod = paymentMethod;
        this.paymentDate = paymentDate;
    }

    public double getAmount() {return amount;}
    public Person getPayer() {return payer;}
    public PaymentMethod getPaymentMethod() {return paymentMethod;}
    public LocalDateTime getPaymentDate() {return paymentDate;}
    
    @DecimalMin("0.0") private double amount;
    @NotNull private Person payer;
    @NotNull @Valid private PaymentMethod paymentMethod;
    @Past private LocalDateTime paymentDate;
}
