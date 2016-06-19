package org.urdad.services.mocking.example.finance;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.urdad.services.contractTest.EnvironmentInitializer;
import org.urdad.services.contractTest.callLogging.SimpleMessagePatternValidator;
import org.urdad.services.contractTest.TestCaseValidator;
import org.urdad.services.contractTest.testData.TestHarnessFactory;
import org.urdad.services.mocking.example.finance.accounts.Accounts;
import org.urdad.services.mocking.example.finance.accounts.AccountsMock;
import org.urdad.services.mocking.example.finance.bank.Banking;
import org.urdad.services.mocking.example.finance.bank.BankingMock;
import org.urdad.services.mocking.example.finance.paymentGateway.PaymentGateway;
import org.urdad.services.mocking.example.finance.paymentGateway.PaymentGatewayMock;

/**
 * @author fritz@solms.co.za
 */
@Configuration
@Import(org.urdad.services.contractTest.TestConfiguration.class)
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class FinanceTest_componentConfiguration_mock 
{
    @Bean public Finance finance() {return new FinanceMock();}
    @Bean public Accounts accounts() {return new AccountsMock();}
    @Bean public PaymentGateway paymentGateway() {return new PaymentGatewayMock(); }
    @Bean public Banking banking() {return new BankingMock();}

    @Bean public TestHarnessFactory testHarnessFactory() {
        return new FinanceTest_testHarnessFactory();}
    
    @Bean public EnvironmentInitializer environmentInitializer() {
        return new FinanceTest_environmentInitializer_mock();}
    
    @Bean public TestCaseValidator testValidator() {
        return new SimpleMessagePatternValidator();}
}
