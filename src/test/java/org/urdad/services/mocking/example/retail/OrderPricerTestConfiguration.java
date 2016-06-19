package org.urdad.services.mocking.example.retail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.urdad.services.MethodOverrideChecker;
import org.urdad.services.MethodOverrideCheckerBean;
import org.urdad.services.ServiceUtilities;
import org.urdad.services.ServiceUtilitiesBean;
import org.urdad.services.contractTest.callLogging.CallLogger;
import org.urdad.services.contractTest.callLogging.CallLoggingAspect;
import org.urdad.services.contractTest.callLogging.SimpleCallLogger;
import org.urdad.services.mocking.example.shipping.ShippingQuoteProvider;
import org.urdad.services.mocking.example.shipping.ShippingQuoteProviderMock;
import org.urdad.services.validation.beanvalidation.ServiceValidationUtilities;
import org.urdad.validation.beanvalidation.BeanValidation;
import org.urdad.validation.beanvalidation.BeanValidationBean;

@Configuration
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class OrderPricerTestConfiguration 
{

    @Bean
    public CallLoggingAspect callLoggingAspect()
    {
        return new CallLoggingAspect();
    }

    @Bean
    public OrderPricer orderPricer()
    {
        logger.trace("Configuring order pricer bean.");
        return new OrderPricerBean();
    }

    @Bean
    public ItemPricer itemPricerMock()
    {
        logger.trace("Configuring mock item pricer.");
        return new ItemPricerMock();
    }

    @Bean
    public ShippingQuoteProvider shippingQuoteProviderMock()
    {
        logger.trace("Configuring mock shipping quote provider.");
        return new ShippingQuoteProviderMock();
    }

    @Bean
    public javax.validation.Validator validator() {
        return new LocalValidatorFactoryBean();
    }

    @Bean
    public BeanValidation.BeanValidationLocal beanValidation() {
        return new BeanValidationBean();
    }

    @Bean
    public ServiceValidationUtilities serviceValidationUtilities() {
        return new ServiceValidationUtilities();
    }

    @Bean
    public ServiceUtilities serviceUtilities() {
        return new ServiceUtilitiesBean();
    }

    @Bean
    public CallLogger callLogger() {
        return new SimpleCallLogger();
    }
    
    @Bean
    public MethodOverrideChecker methodOverrideChecker() {
        return new MethodOverrideCheckerBean();
    }

    private static final Logger logger = (Logger)LoggerFactory.getLogger(OrderPricerTestConfiguration.class);

}