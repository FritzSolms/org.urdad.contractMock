/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.urdad.services.mocking.example.finance.paymentGateway;

import java.time.LocalDateTime;
import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import org.hibernate.validator.constraints.NotEmpty;
import org.urdad.services.mocking.example.finance.bank.BankAccountDetails;
import org.urdad.services.mocking.example.finance.bank.CreditCardDetails;

/**
 *
 * @author fritz
 */
public class CreditCardPaymentConfirmation {
    public CreditCardPaymentConfirmation(CreditCardDetails cardDetails, 
            BankAccountDetails destinationAccountDetails, double amount, 
            LocalDateTime transactionDate) {
        this.cardDetails = cardDetails;
        this.destinationAccountDetails = destinationAccountDetails;
        this.amount = amount;
        this.transactionDate = transactionDate;}
    
        public LocalDateTime getTransactionDate() {return transactionDate;}
        public CreditCardDetails getCardDetails() {return cardDetails;}
        public BankAccountDetails getDestinationAccountDetails() {return destinationAccountDetails;}
        public double getAmount() {return amount;}

        @NotNull @Valid private CreditCardDetails cardDetails;
        @NotNull @Valid private BankAccountDetails destinationAccountDetails;
        @DecimalMin("0.0") private double amount;
        @Past private LocalDateTime transactionDate;
}
