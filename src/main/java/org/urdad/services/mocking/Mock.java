package org.urdad.services.mocking;

import org.springframework.stereotype.Service;

/**
 * Specifies that any Mock object must be able to provide the call logger 
 * which is used to log the calls made to it in order to query the 
 * call history to assert that pre-conditions and post-conditions of a 
 * contract have been addressed.
 * @author fritz@solms.co.za
 */
@MockObject
@Service
public interface Mock
{
	/**
	 * Method to set the state of a mock object to some specific state in which it behaves in some particular way.
	 * @param state the state identifier
	 * @throws InvalidStateException if the mock object does not support the specified state
	 */
	public void setState(State state) throws InvalidStateException;
	
	/** @return the current state of the mock object */
	public State getState();
	
	public interface State{};
	
	public class InvalidStateException extends RuntimeException {};
}
