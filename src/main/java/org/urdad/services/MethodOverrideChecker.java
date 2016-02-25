package org.urdad.services;

import java.lang.reflect.Method;

/**
 * A pure function which returns true if the specialized method does override the base method,
 * i.e. if the base method is defined either in one of its super classes or in an interface
 * which is directly or indirectly implemented.
 *
 * @author fritz@solms.co.za
 */
@FunctionalInterface
public interface MethodOverrideChecker 
{
	/**
	 *
	 * @param base the interface or class method which is potentially overridden by the specialized method
	 * @param specialized the method which potentially overrides the base method.
	 * @return true if the specialized method does override the base method and false otherwise.
	 */
	boolean overrides(Method base, Method specialized);

}