package org.urdad.services.contractTest.callLogging;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

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
    public CallDescriptor(Object target, Method method, LocalDateTime requestTime, 
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

    public LocalDateTime getRequestTime() {return requestTime;}
    public Method getMethod() {return method;}
    public Request getRequest() {return request;}
    public Object getResponse() {return response;}
    public LocalDateTime getResponseTime() {return responseTime;}

    public void setRequestTime(LocalDateTime requestTime) 
        {this.requestTime = requestTime;}
    public void setMethod(Method method) {this.method = method;}
    public void setRequest(Request request) {this.request = request;}
    public void setResponse(Object response) {this.response = response;}
    public void setResponseTime(LocalDateTime responseTime) 
        {this.responseTime = responseTime;}

    public String toString() {
        return target.getClass().getName() + "." + method.getName() 
                + " called at " + requestTime + " with " 
           + request + " returned at " + responseTime + " with " + response;
    }

    @NotNull @Past private LocalDateTime requestTime, responseTime;
    @NotNull private Object target;
    @NotNull private Method method;
    @NotNull @Valid private Request request;
    @NotNull @Valid private Object response; // either a response object or an exception
}