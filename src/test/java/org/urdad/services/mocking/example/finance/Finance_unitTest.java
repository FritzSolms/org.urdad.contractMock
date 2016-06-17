package org.urdad.services.mocking.example.finance;

import javax.inject.Inject;
import org.junit.Test;
import org.urdad.services.contractTest.ComponentTest;
import org.urdad.services.contractTest.TestHarness;
import org.urdad.services.mocking.example.finance.accounts.Accounts;
import org.urdad.services.mocking.example.finance.bank.Banking;
import org.urdad.services.mocking.example.finance.bank.BankingMock;
import org.urdad.services.mocking.example.finance.paymentGateway.PaymentGateway;
import org.urdad.services.mocking.example.finance.paymentGateway.PaymentGatewayMock;

/**
 * @author fritz@solms.co.za
 */
public class Finance_unitTest {
    @Test
    public void testComponentAgainstContract()
    {
        Object[] dependencies = {new BankingMock(), new AccountsMock(), 
            new PaymentGatewayMock()};
        ComponentTest.TestComponentResponse testResult 
            = componentTest.testComponent(new ComponentTest.TestComponentRequest
        (Finance.class, dependencies, testHarness));
    }
    @Inject private ComponentTest componentTest;
    @Inject private TestHarness testHarness;
}
