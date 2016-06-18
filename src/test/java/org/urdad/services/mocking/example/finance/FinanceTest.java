package org.urdad.services.mocking.example.finance;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.LinkedList;
import java.util.List;
import javax.inject.Inject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.urdad.services.ServiceUtilities;
import org.urdad.services.contractTest.CallRequirement;
import org.urdad.services.contractTest.ComponentTest;
import org.urdad.services.contractTest.EnvironmentInitializer;
import org.urdad.services.contractTest.ServiceTestData;
import org.urdad.services.contractTest.TestCaseData;
import org.urdad.services.contractTest.TestCaseValidator;
import org.urdad.services.contractTest.TestHarness;
import org.urdad.services.mocking.example.finance.accounts.Accounts;
import org.urdad.services.mocking.example.finance.bank.Banking;
import org.urdad.services.mocking.example.finance.paymentGateway.PaymentGateway;
import org.urdad.services.mocking.example.legalEntities.Address;
import org.urdad.services.mocking.example.legalEntities.Person;

/**
 * Can be executed in different test harnesses for unit and integration testing
 * @author fritz@solms.co.za
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {FinanceTest_configuration.class})
public class FinanceTest {
    @Test
    public void testComponentAgainstContract() throws Exception
    {
        Object[] dependencies = {banking, accounts, paymentGateway};
        try {
            testHarness = createTestHarness();
        }
        catch (Exception e) {
            Assert.fail("Could not create test harness");
            throw e;
        }
        ComponentTest.TestComponentResponse testResult 
            = componentTest.testComponent(new ComponentTest.TestComponentRequest
        (finance, dependencies, testHarness));
    }
    
    private TestHarness createTestHarness() 
            throws Exception {
        List<ServiceTestData> serviceTests = new LinkedList();
        serviceTests.add(createProcessPaymentTest());
        return new TestHarness(Finance.class, serviceTests);
    }
    private ServiceTestData createProcessPaymentTest() 
            throws Exception {
        List<TestCaseData> testCases = new LinkedList();
        testCases.add(createAccountDebitSuccess());
        return new ServiceTestData("processPayment", testCases);
    }   
    private TestCaseData createAccountDebitSuccess() 
            throws Exception {
        System.out.println("serviceUtilities:" + serviceUtilities);
                
        List<CallRequirement> callRequirements = new LinkedList();
        callRequirements.add(new CallRequirement(Banking.class,
            serviceUtilities.getService(banking.getClass(), "getBankAccountDetails"), null, null));
        callRequirements.add(new CallRequirement(Accounts.class, 
            serviceUtilities.getService(accounts.getClass(), "debitAccount"), null, null));
        return new TestCaseData(FinanceTest_scenarios.processPayment_accountDebit_success, 
                environmentInitializer,
                new Finance.ProcessPaymentRequest(amount, defaultPerson, accountPaymentMethod),
                new Finance.ProcessPaymentResponse(new Receipt(amount, defaultPerson, 
                accountPaymentMethod, LocalDateTime.now())),
            callRequirements, testCaseValidator);    
    }
//TODO Move test data into test harness??
    @Before
    public void createTestData()
    {
        defaultPerson = new Person("Peter", LocalDate.of(1995, Month.MARCH, 10), 
                            new Address("1 Desert Rd", "Timbuktu"));
        amount = 500;
        accountPaymentMethod = new AccountPayment("A123");
    }
    
    private Person defaultPerson;
    private double amount;
    private PaymentMethod accountPaymentMethod;
    
    private TestHarness testHarness;
    
    @Inject private Finance finance;
    @Inject private Banking banking;
    @Inject private Accounts accounts;
    @Inject private PaymentGateway paymentGateway;
    @Inject private EnvironmentInitializer environmentInitializer;
    @Inject private TestCaseValidator testCaseValidator; // use SimpleTestCaseValidator
    @Inject private ServiceUtilities serviceUtilities;
    @Inject private ComponentTest componentTest;
}
