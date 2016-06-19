package org.urdad.services.contractTest;

import org.urdad.services.contractTest.reporting.DefaultComponentTestReporter;
import org.urdad.services.contractTest.reporting.ComponentTestReporter;
import org.urdad.services.contractTest.callLogging.SimpleCallLogger;
import org.urdad.services.contractTest.callLogging.CallLogger;
import org.urdad.services.contractTest.callLogging.CallLoggingAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.urdad.services.MethodOverrideChecker;
import org.urdad.services.MethodOverrideCheckerBean;
import org.urdad.services.ServiceUtilities;
import org.urdad.services.ServiceUtilitiesBean;
import org.urdad.services.validation.beanvalidation.ServiceValidationUtilities;
import org.urdad.validation.beanvalidation.BeanValidation;
import org.urdad.validation.beanvalidation.BeanValidationBean;

/**
 * @author fritz@solms.co.za
 */
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class TestConfiguration 
{
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
        return new DefaultComponentTestReporter();}
}
