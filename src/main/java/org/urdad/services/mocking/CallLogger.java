package org.urdad.services.mocking;

import java.lang.reflect.Method;
import java.util.List;

import org.urdad.services.messaging.Request;

/**
 * A contract for a utility class which can maintain the call log for a mock object.
 * 
 * @author fritz@solms.co.za
 *
 */
public interface CallLogger 
{
    /**
     * Logs a specific call made to a mock object.
     * @param callDescriptor the call to be logged.
     */
    public void logCall(CallDescriptor callDescriptor);

    /**
     * 
     * @return the list of call logs describing the calls made to the mock object.
     */
    public List<CallDescriptor> getCallLog();

    /**
     * The number of invocations made to the specified method (i.e. any concrete 
     * method which implements the method signature or is that method or overrides this method).
     * @param method the method who's invocation count is required
     * @return the number of invocations
     */
    public int getInvocationCount(Method method);

    /**
     * The number of invocations made to the service identified by the provided service name
     * (as per our services oriented approach), i.e. any concrete 
     * method which implements the method signature or is that method or overrides this method).
     * @param serviceName the method who's invocation count is required
     * @return the number of invocations
     * @throws NotAServiceException when there is either no method with that name which complies 
     * to the requirements of a service
     */
    //public int getInvocationCount(String serviceName) throws NotAServiceException;

    /**
     * Returns the number of invocations of a specified method with a request which is 
     * equal to (as per equals() method) to a provided request object.
     * @param method
     * @param request
     * @return the number of times specified method has been invoked with a request
     * equal to the provided request.
     */
    public int getInvocationCount(Method method, Request request);

    /**
     * Clears the call history removing all stored call descriptors.
     */
    public void clearLog();
}
