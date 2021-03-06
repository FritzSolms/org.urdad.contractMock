package org.urdad.services.contractTest;

import org.urdad.services.messaging.Request;
import org.urdad.services.messaging.Response;
import org.urdad.services.validation.RequestNotValidException;

/**
 * @author fritz@solms.co.za
 */
public interface EnvironmentInitializer {
    /**
     * 
     * @param request
     * @return
     * @throws RequestNotValidException if the environmentStateIdentifier does not
     * identify a known environment.
     * @throws org.urdad.services.contractTest.EnvironmentInitializer.InitializationException 
     */
    public InitializeEnvironmentResponse initializeEnvironment
        (InitializeEnvironmentRequest request) 
                throws RequestNotValidException, InitializationException;
        
    public class InitializeEnvironmentRequest implements Request {

        public InitializeEnvironmentRequest(Object testCaseIdentifier) {
            this.testCaseIdentifier = testCaseIdentifier;
        }

        public Object getTestCaseIdentifier() {
            return testCaseIdentifier;
        }
        private Object testCaseIdentifier;
    }    
    public class InitializeEnvironmentResponse implements Response {
        
    }
    public class InitializationException extends Exception {}
}
