package org.urdad.services.mocking.example.finance.bank;

import java.util.Date;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.NotEmpty;
/**
 *
 * @author fritz@solms.co.za
 */
public class CreditCardDetails implements Cloneable {

    public CreditCardDetails(String cardType, String cardNo, String name, Date cardExpiryDate) {
        this.name = name;
        this.cardNo = cardNo;
        this.cardType = cardType;
        this.cardExpiryDate = (Date) cardExpiryDate.clone();
    }
    public String getName() {return name;}
    public Date getCardExpiryDate() {return cardExpiryDate;}
    public String getCardNo() {return cardNo;}
    public String getCardType() {return cardType;}
    
    public CreditCardDetails clone()
    {
        CreditCardDetails copy = null;
        try {
            copy = (CreditCardDetails)super.clone();
        } catch (CloneNotSupportedException e) { /* never throws */ }
        return copy;}
    
    @NotNull @NotEmpty private String name;
    @Future private Date cardExpiryDate;
    @Pattern(regexp = "{(\\d{4}}( \\d{4}}){3})") private String cardNo;
    @Pattern(regexp = "VISA|MASTER") private String cardType;
}