package org.urdad.services.mocking.example.finance.bank;

/**
 * @author fritz
 */
public class BankAccountDetails {

    public BankAccountDetails(String BankName, String swiftCode, String accountHolder, String accountNumber) {
        this.BankName = BankName;
        this.swiftCode = swiftCode;
        this.accountHolder = accountHolder;
        this.accountNumber = accountNumber;
    }
    public String getBankName() {return BankName;}
    public String getSwiftCode() {return swiftCode;}
    public String getAccountHolder() {return accountHolder;}
    public String getAccountNumber() {return accountNumber;}

    private String BankName;
    private String swiftCode;
    private String accountHolder;
    private String accountNumber;
}
