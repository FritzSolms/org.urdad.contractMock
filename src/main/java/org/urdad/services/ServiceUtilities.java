package org.urdad.services;

import java.lang.reflect.Method;
import org.springframework.stereotype.Service;
import org.urdad.services.messaging.PreconditionViolation;

/**
 * A container for some utility functions around services.
 * 
 * @author fritz@solms.co.za
 */
@Service
public interface ServiceUtilities 
{

	/**
	 * Returns the request class for a specified service of a specified service provider
	 * @param serviceProvider the service provider class hosting the required service
	 * @param serviceName the name of the service whose request class is required
	 * @return the request class for the specified service
	 * @throws NotAServiceException if either there is no method with serviceName or 
	 *  that method does not comply to the requirements of a service, i.e. does not 
	 *  have a single parameter of type Request and a return value of type Response.
	 */
	Class<? extends Object> getRequestClass(Object serviceProvider, String serviceName) throws NotAServiceException;

	/**
	 * Returns the response class for a specified service of a specified service provider
	 * @param serviceProvider the service provider class hosting the required service
	 * @param serviceName the name of the service whose request class is required
	 * @return the request class for the specified service
	 * @throws NotAServiceException if either there is no method with serviceName or 
	 *  that method does not comply to the requirements of a service, i.e. does not 
	 *  have a single parameter of type Request and a return value of type Response.
	 */
	Class<? extends Object> getResponseClass(Object serviceProvider, String serviceName) throws NotAServiceException;
	
	/**
	 * Returns the request class for a specified service.
	 * @param service the name of the service whose request class is required
	 * @return the request class for the specified service
	 * @throws NotAServiceException if the method does not comply to the 
	 *  requirements of a service, i.e. does not have a single parameter of type 
	 *  Request and a return value of type Response.
	 */
	Class<? extends Object> getRequestClass(Method service) throws NotAServiceException;
	
	/**
	 * Returns the response class for a specified service.
	 * @param service the name of the service whose request class is required
	 * @return the request class for the specified service
	 * @throws NotAServiceException if the method does not comply to the 
	 *  requirements of a service, i.e. does not have a single parameter of type 
	 *  Request and a return value of type Response.
	 */
	Class<? extends Object> getResponseClass(Method service) throws NotAServiceException;
	
	/**
	 * Returns the service (Method) of a specified service provider based on the service name.
	 * @param serviceProvider the service provider class hosting the required service
	 * @param methodName the name of the service whose request class is required
	 * @return the Method for the requested service.
	 * @throws NotAServiceException if the method does not comply to the requirements of a service, i.e. does not
	 * have a single parameter of type Request and a return value of type Response.
	 */
	Method getService(Object serviceProvider, String methodName) throws NotAServiceException;
        
        /**
	 * Returns the service (Method) of a specified service provider contract or class based on the service name.
         * @param theClassOrInterface
         * @param serviceName the name of the method representing the service whose request class is required
         * @return  the Method for the requested service.
         * @throws ServiceUtilities.NotAServiceException 
         */
        public Method getService(Class theClassOrInterface, String serviceName) throws NotAServiceException;
	
	/**
	 * An exception which is thrown if a service either does not exist or does not conform 
	 * to the requirements of a service (single request parameter and a response return type).
	 * 
	 * @author fritz@solms.co.za
	 */
	class NotAServiceException extends PreconditionViolation
	{
		public NotAServiceException() { super(); }
		public NotAServiceException(String message) { super(message); }
		public NotAServiceException(Throwable cause) { super(cause); }
		public NotAServiceException(String message, Throwable cause) {super(message, cause);}
	}
		
}