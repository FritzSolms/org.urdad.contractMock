package org.urdad.services.mocking;

import java.lang.reflect.Method;
import java.time.LocalDateTime;

import org.urdad.services.messaging.Request;

/**
 * A call log which contains the method being requested, the request object and time, 
 * the response (response object or exception) as well as the response time.
 * 
 * @author fritz@solms.co.za
 *
 */
public class CallDescriptor
{	
	public CallDescriptor(
			Method method, 
			LocalDateTime requestTime, 
			Request request, 
			Object response, // either a response object or an exception 
			LocalDateTime responseTime) 
	{
		super();
		this.requestTime = requestTime;
		this.method = method;
		this.request = request;
		this.response = response;
		this.responseTime = responseTime;
	}
	
	public LocalDateTime getRequestTime() {
		return requestTime;
	}
	public void setRequestTime(LocalDateTime requestTime) {
		this.requestTime = requestTime;
	}
	public Method getMethod() {
		return method;
	}
	public void setMethod(Method method) {
		this.method = method;
	}
	public Request getRequest() {
		return request;
	}
	public void setRequest(Request request) {
		this.request = request;
	}
	public Object getResponse() {
		return response;
	}
	public void setResponse(Object response) {
		this.response = response;
	}
	
	public LocalDateTime getResponseTime() {
		return responseTime;
	}
	public void setResponseTime(LocalDateTime responseTime) {
		this.responseTime = responseTime;
	}
	
	public String toString()
	{
		return method.getName() + " called at " + requestTime + " with " + request + " returned at "
		  + responseTime + " with " + response;
	}

	private LocalDateTime requestTime, responseTime;
	private Method method;
	private Request request;
	private Object response; // either a response object or an exception
}