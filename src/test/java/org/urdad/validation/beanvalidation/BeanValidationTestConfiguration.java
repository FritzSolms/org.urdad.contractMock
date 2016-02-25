package org.urdad.validation.beanvalidation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.urdad.services.validation.beanvalidation.ServiceValidationUtilities;
import org.urdad.validation.beanvalidation.BeanValidation;
import org.urdad.validation.beanvalidation.BeanValidationBean;

@Configuration
public class BeanValidationTestConfiguration
{

    @Bean
    public javax.validation.Validator validator()
    {
        return new LocalValidatorFactoryBean();
    }

    @Bean
    public BeanValidation.BeanValidationLocal beanValidation()
    {
        return new BeanValidationBean();
    }

    @Bean
    public ServiceValidationUtilities serviceValidationUtilities()
    {
        return new ServiceValidationUtilities();
    }

}