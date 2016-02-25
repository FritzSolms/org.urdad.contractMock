package org.urdad.services.mocking;

import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

import org.urdad.services.MethodOverrideChecker;
import org.urdad.services.MethodOverrideCheckerBean;
import org.urdad.services.ServiceUtilities;
import org.urdad.services.ServiceUtilitiesBean;
import org.urdad.services.ServiceUtilities.NotAServiceException;

/**
 * A basic implementation of a call logger which maintains a list of call desriptors
 * 
 * @author fritz@solms.co.za
 *
 */
public class SimpleCallLogger implements CallLogger 
{
	public SimpleCallLogger(BaseMock mock)
	{
		this.mock = mock;
	}
	
	@Override
	public void logCall(CallDescriptor callDescriptor) 
	{
		callLog.add(callDescriptor); 
	}

	@Override
	public List<CallDescriptor> getCallLog() { return callLog; }

	@Override
	public int getInvocationCount(Method method) 
	{
		int numCalls = 0;
		for (CallDescriptor callDescriptor : callLog)
		{
			if (methodOverrideChecker.overrides(method, callDescriptor.getMethod()))
				numCalls++;
		}
		return numCalls;
	}

	@Override
	public BaseMock getMockObject() { return mock; }
	
	@Override
	public int getInvocationCount(String serviceName) throws NotAServiceException
	{
		Method method = serviceUtilities.getService(mock, serviceName);
		return getInvocationCount(method);
	}
	
	private BaseMock mock;
	
//	@Inject
	private ServiceUtilities serviceUtilities = new ServiceUtilitiesBean();
//  @Inject
	MethodOverrideChecker methodOverrideChecker = new MethodOverrideCheckerBean();

	@Override
	public void clearLog() { callLog.clear(); }

	private List<CallDescriptor> callLog = new LinkedList<CallDescriptor>();
}
