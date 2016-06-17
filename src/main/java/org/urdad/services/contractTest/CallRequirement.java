package org.urdad.services.contractTest;

import java.lang.reflect.Method;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.urdad.services.messaging.Request;

/**
 * @author fritz@solms.co.za
 */
public class CallRequirement {

    public CallRequirement(Object target, Method method, Request request, Object response) {
        this.target = target;
        this.method = method;
        this.request = request;
        this.response = response;
    }

    public Object getTarget() {return target;}
    public Method getMethod() {return method;}
    public Request getRequest() {return request;}
    public Object getResponse() {return response;}
    
    @NotNull private Object target;
    @NotNull private Method method;
    @Valid private Request request;
    @Valid private Object response; // either a response object or an exception
}

