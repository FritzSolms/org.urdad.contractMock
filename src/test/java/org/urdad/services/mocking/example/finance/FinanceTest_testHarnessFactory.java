package org.urdad.services.mocking.example.finance;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.LinkedList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.junit.Before;
import org.springframework.stereotype.Service;
import org.urdad.services.ServiceUtilities;
import org.urdad.services.contractTest.CallRequirement;
import org.urdad.services.contractTest.EnvironmentInitializer;
import org.urdad.services.contractTest.ServiceTestData;
import org.urdad.services.contractTest.TestCaseData;
import org.urdad.services.contractTest.TestCaseValidator;
import org.urdad.services.contractTest.TestHarness;
import org.urdad.services.contractTest.TestHarnessFactory;
import org.urdad.services.mocking.example.finance.accounts.Accounts;
import org.urdad.services.mocking.example.finance.bank.Banking;
import org.urdad.services.mocking.example.finance.paymentGateway.PaymentGateway;
import org.urdad.services.mocking.example.legalEntities.Address;
import org.urdad.services.mocking.example.legalEntities.Person;

/**
 * @author fritz
 */
@Service
@Singleton
public class FinanceTest_testHarnessFactory implements TestHarnessFactory {
        
    public ProvideTestHarnessResponse provideTestHarness
        (ProvideTestHarnessRequest request) 
            throws Exception {
        List<ServiceTestData> serviceTests = new LinkedList();
        serviceTests.add(createProcessPaymentTest());
        return new ProvideTestHarnessResponse
            (new TestHarness(Finance.class, serviceTests));
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
    private Person defaultPerson = new Person("Peter", LocalDate.of(1995, Month.MARCH, 10), 
                            new Address("1 Desert Rd", "Timbuktu"));
    private double amount = 500;
    private PaymentMethod accountPaymentMethod = new AccountPayment("A123");

    @Inject private Banking banking;
    @Inject private Accounts accounts;
    @Inject private PaymentGateway paymentGateway;
    
    @Inject private ServiceUtilities serviceUtilities;
    @Inject private EnvironmentInitializer environmentInitializer;
    @Inject private TestCaseValidator testCaseValidator;
}
