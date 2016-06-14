package org.urdad.services.mocking.example.retail;

import java.util.Date;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author fritz@solms.co.za
 */
public class CreditCardPayment extends PaymentMethod {
    public CreditCardPayment(String cardNo, String name, Date cardExpiryDate) {
        this.name = name;
        this.cardNo = cardNo;
        this.cardExpiryDate = (Date) cardExpiryDate.clone();
    }
    @NotNull @NotEmpty String name;
    @Future Date cardExpiryDate;
    @Pattern(regexp = "{(\\d{4}}( \\d{4}}){3})") String cardNo;
}
