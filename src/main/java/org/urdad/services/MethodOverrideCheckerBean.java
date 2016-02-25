package org.urdad.services;

import java.lang.reflect.Method;

import org.springframework.stereotype.Service;

/**
 * A simple implementation of the {@link MethodOverrideChecker} contract.
 * 
 * @author fritz@solms.co.za
 * @see MethodOverrideChecker
 * 
 */
@Service
public class MethodOverrideCheckerBean implements MethodOverrideChecker 
{

	@Override
	public boolean overrides(Method base, Method specialized) 
	{	
		// FIXME - over-simplified
		if (!base.getName().equals(specialized.getName()))
			return false;
		else
			return sameParameters(base, specialized); // && sameReturnType(base, specialized);
	}
	
	/**
	 * 
	 * @param m1 method 1
	 * @param m2 method 2
	 * @return true if methods m1 and m2 have the same method parameter signatures
	 */
	private boolean sameParameters(Method m1, Method m2)
	{
		if (m1.getParameterCount() != m2.getParameterCount())
			return false;

		for (int i=0; i<m1.getParameters().length; ++i)
			if (!m1.getParameters()[i].getType().equals(m2.getParameters()[i].getType()))
				return false;
		
		return true;
	}
	
	/**
	 * 
	 * @param m1 method 1
	 * @param m2 method 2
	 * @return true if methods m1 and m2 have the same return types
	 */
	private Boolean sameReturnType(Method m1, Method m2)
	{
		if (m1.getReturnType().equals(m2.getReturnType()))
			return true;
		else
			return false;
	}

}
