package org.urdad.services.mocking.example.finance;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.urdad.services.contractTest.GenericComponentTest;
import org.urdad.services.MethodOverrideChecker;
import org.urdad.services.MethodOverrideCheckerBean;
import org.urdad.services.ServiceUtilities;
import org.urdad.services.ServiceUtilitiesBean;
import org.urdad.services.contractTest.CallLogger;
import org.urdad.services.contractTest.CallLoggingAspect;
import org.urdad.services.contractTest.ComponentTest;
import org.urdad.services.contractTest.ComponentTestReporter;
import org.urdad.services.contractTest.EnvironmentInitializer;
import org.urdad.services.contractTest.JunitComponentTestReporter;
import org.urdad.services.contractTest.SimpleCallLogger;
import org.urdad.services.contractTest.SimpleMessagePatternValidator;
import org.urdad.services.contractTest.TestCaseValidator;
import org.urdad.services.contractTest.TestHarnessFactory;
import org.urdad.services.mocking.example.finance.accounts.Accounts;
import org.urdad.services.mocking.example.finance.accounts.AccountsMock;
import org.urdad.services.mocking.example.finance.bank.Banking;
import org.urdad.services.mocking.example.finance.bank.BankingMock;
import org.urdad.services.mocking.example.finance.paymentGateway.PaymentGateway;
import org.urdad.services.mocking.example.finance.paymentGateway.PaymentGatewayMock;
import org.urdad.services.validation.beanvalidation.ServiceValidationUtilities;
import org.urdad.validation.beanvalidation.BeanValidation;
import org.urdad.validation.beanvalidation.BeanValidationBean;

/**
 * @author fritz@solms.co.za
 */
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class FinanceTest_configuration_mock 
{

    @Bean public Finance finance() {
        return new FinanceMock();}
    
    @Bean public Accounts accounts() {
        return new AccountsMock();}
    
    @Bean public PaymentGateway paymentGateway() {
        return new PaymentGatewayMock(); }
    
    @Bean public Banking banking() {
        return new BankingMock();}

    @Bean public TestHarnessFactory testHarnessFactory() {
        return new FinanceTest_testHarnessFactory();}
    
    @Bean public EnvironmentInitializer environmentInitializer() {
        return new FinanceTest_environmentInitializer_mock();}
    
    @Bean public TestCaseValidator testValidator() {
        return new SimpleMessagePatternValidator();}
    
    @Bean public CallLoggingAspect callLoggingAspect() {
        return new CallLoggingAspect();}

    @Bean public javax.validation.Validator validator() {
        return new LocalValidatorFactoryBean();}

    @Bean public BeanValidation.BeanValidationLocal beanValidation() {
        return new BeanValidationBean();}

    @Bean public ServiceValidationUtilities serviceValidationUtilities() {
        return new ServiceValidationUtilities();}

    @Bean public ServiceUtilities serviceUtilities() {
        return new ServiceUtilitiesBean();}

    @Bean public CallLogger callLogger() {
        return new SimpleCallLogger();}
    
    @Bean public MethodOverrideChecker methodOverrideChecker() {
        return new MethodOverrideCheckerBean();}
    
    @Bean public ComponentTest componentTest() {
        return new GenericComponentTest();}
    
    @Bean public ComponentTestReporter componentTestReporter() {
        return new JunitComponentTestReporter();}
}
