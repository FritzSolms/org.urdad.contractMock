package org.urdad.services.contractTest;

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
 *
 * @author fritz@solms.co.za
 */
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class UnitTestConfiguration {
    
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
    
}
