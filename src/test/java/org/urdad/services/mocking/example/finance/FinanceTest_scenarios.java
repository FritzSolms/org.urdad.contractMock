package org.urdad.services.mocking.example.finance;

/**
 * @author fritz@solms.co.za
 */
public enum FinanceTest_scenarios {
        processPayment_accountDebit_success,
        processPayement_accountDebit_fail,
        processPayment_creditCard_success,
        processPayment_creditCard_couldNotSourceFunds,
        processPayment_creditCard_couldNotCreditDestinationAccount   
}
