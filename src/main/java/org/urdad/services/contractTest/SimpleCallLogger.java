package org.urdad.services.contractTest;

import org.urdad.services.contractTest.CallLogger;
import org.urdad.services.contractTest.CallDescriptor;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;

import org.urdad.services.MethodOverrideChecker;
import org.urdad.services.ServiceUtilities;
import org.urdad.services.messaging.Request;

/**
 * A basic implementation of a call logger which maintains a list of call desriptors
 * 
 * @author fritz@solms.co.za
 *
 */
@Service
public class SimpleCallLogger implements CallLogger 
{
    public SimpleCallLogger() {}

    @Override
    public void logCall(CallDescriptor callDescriptor) {
        callLog.add(callDescriptor); 
    }
    @Override
    public List<CallDescriptor> getCallLog() { return callLog; }

    @Override
    public int getInvocationCount(Method method) {
        int numCalls = 0;
        for (CallDescriptor callDescriptor : callLog)
        {
            if (methodOverrideChecker.overrides(method, callDescriptor.getMethod()))
                numCalls++;
        }
        return numCalls;
    }
    @Override
    public int getInvocationCount(Method method, Request request) {
        int numCalls = 0;
        for (CallDescriptor callDescriptor : callLog) {
            if (methodOverrideChecker.overrides(method, callDescriptor.getMethod())
                    && callDescriptor.getRequest().equals(request))
                        numCalls++;
        }
        return numCalls;
    }

    @Override
    public void clearLog() { callLog.clear(); }

    @Inject private ServiceUtilities serviceUtilities;
    @Inject private MethodOverrideChecker methodOverrideChecker;

    private List<CallDescriptor> callLog = new LinkedList<CallDescriptor>();
}
