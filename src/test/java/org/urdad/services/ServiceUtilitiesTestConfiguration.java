package org.urdad.services;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.urdad.services.ServiceUtilities;
import org.urdad.services.ServiceUtilitiesBean;

/**
 * The spring configuration for the {@link ServiceUtilitiesTest}.
 * 
 * @author fritz@solms.co.za
 *
 */
@EnableAspectJAutoProxy
@Configuration
public class ServiceUtilitiesTestConfiguration 
{
	@Bean
	public ServiceUtilities serviceUtilities()
	{
		return new ServiceUtilitiesBean();
	}
}
