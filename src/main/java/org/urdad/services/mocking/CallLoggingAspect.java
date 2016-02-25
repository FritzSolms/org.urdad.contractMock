package org.urdad.services.mocking;

import java.lang.reflect.Method;
import java.time.LocalDateTime;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.urdad.services.messaging.Request;

/**
 * Logging aspect for Mock objects which intercepts all mock object calls and 
 * adds the call events to the call history of the mock object.
 * 
 * @author fritz@solms.co.za
 *
 */
@Aspect
public class CallLoggingAspect 
{
	/**
	 * Logs all service calls, but not other method calls.
	 * @param joinPoint
	 * @return
	 * @throws Throwable
	 */
	@Around(value = "@within(org.urdad.services.mocking.Mocking)"
 			+ "|| @annotation(org.urdad.services.mocking.Mocking)")
	public Object logCall(ProceedingJoinPoint joinPoint) throws Throwable 
	{
		LocalDateTime requestTime = LocalDateTime.now();
		 
		Method method = ((MethodSignature)joinPoint.getStaticPart().getSignature()).getMethod();
		Request request = null;
		try
		{
			request = (Request)joinPoint.getArgs()[0];
		}
		catch (Exception e){ /* Ignore all non-service requests, i.e. methods which do not receive a single service request */ }
		
		Object response = null;
		LocalDateTime responseTime;
		
		try
		{
		  response = joinPoint.proceed();
		  responseTime = LocalDateTime.now();
			 
		  if (request != null)
			  ((Mock)joinPoint.getTarget()).getCallLogger().logCall(
					new CallDescriptor(method, requestTime, request, response, responseTime));
		  return response;
		}
		catch (Exception e)
		{
			responseTime = LocalDateTime.now();
			((Mock)joinPoint.getTarget()).getCallLogger().logCall(
					new CallDescriptor(method, requestTime, request, e, responseTime));
			throw e;
		}
    }		
}
