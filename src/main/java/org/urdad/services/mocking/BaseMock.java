package org.urdad.services.mocking;

import javax.inject.Inject;

/**
 * A minimal base class for mocking objects which ensures that the call logging aspect is applied to the logging object
 * and which can provide the call logger which was assigned to the mock it.
 * 
 * @author fritz@solms.co.za
 */
@MockObject
public class BaseMock implements Mock 
{
    public BaseMock() {}

    public State getState() {return state;}

    public void setState(State state) {this.state = state;}

    private State state;
}