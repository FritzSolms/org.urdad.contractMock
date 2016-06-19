package org.urdad.services.contractTest;

import org.urdad.services.contractTest.callLogging.CallDescriptor;
import org.urdad.services.contractTest.callLogging.CallRequirement;
import java.util.List;
import org.urdad.services.messaging.Request;
import org.urdad.services.messaging.Response;

/**
 * @author fritz@solms.co.za
 */
public interface TestCaseValidator {
    public ValidateTestCaseResponse validateTestCase(ValidateTestCaseRequest request);
    
        
    public class ValidateTestCaseRequest
    {
        public ValidateTestCaseRequest(Object serviceProvider, Object testCaseIdentifier,
                Request requiredRequest, Request madeRequest, Response requiredResponse, 
                Response madeResponse, List<CallRequirement> callRequirements, 
                List<CallDescriptor> callLog) {
            this.serviceProvider = serviceProvider;
            this.testCaseIdentifier = testCaseIdentifier;
            this.requiredRequest = requiredRequest;
            this.madeRequest = madeRequest;
            this.requiredResponse = requiredResponse;
            this.madeResponse = madeResponse;
            this.callRequirements = callRequirements;
            this.callLog = callLog;
        }
        public Object getServiceProvider() {return serviceProvider;}
        public Object getTestCaseIdentifier() {return testCaseIdentifier;}
        public Request getRequiredRequest() {return requiredRequest;}
        public Request getMadeRequest() {return madeRequest;}
        public Response getRequiredResponse() {return requiredResponse;}
        public Response getMadeResponse() {return madeResponse;}
        public List<CallRequirement> getCallRequirements() {return callRequirements;}
        public List<CallDescriptor> getCallLog() {return callLog;}
        
        private Object serviceProvider;
        private Object testCaseIdentifier;
        private Request requiredRequest, madeRequest;
        private Response requiredResponse, madeResponse;
        private List<CallRequirement> callRequirements; 
        private List<CallDescriptor> callLog; 
    }
    
    public class ValidateTestCaseResponse
    {
        public ValidateTestCaseResponse(Object serviceProvider,
                Object testCaseIdentifier, boolean pass, ValidationStatus validationStatus) {
            this(serviceProvider, testCaseIdentifier, pass, validationStatus, null);
        }
        public ValidateTestCaseResponse(Object serviceProvider,
                Object testCaseIdentifier, boolean pass, ValidationStatus validationStatus,
                String message) {
            this(serviceProvider, testCaseIdentifier, pass, validationStatus, message, null);
        }
        public ValidateTestCaseResponse(Object serviceProvider,
                Object testCaseIdentfier, boolean pass, ValidationStatus validationStatus, 
                String message, Exception exceptionRaised) {
            this.serviceProvider = serviceProvider;
            this.testCaseIdentifier = testCaseIdentfier;
            this.pass = pass;
            this.validationStatus = validationStatus;
            this.message = message;
            this.exceptionRaised = exceptionRaised;
        }
        public boolean isPass() {return pass;}
        public ValidationStatus getValidationStatus() {return validationStatus;}
        public String getMessage() {return message;}
        public Exception getExceptionRaised() {return exceptionRaised;}
        public Object getServiceProvider() {return serviceProvider;}
        public Object getTestCaseIdentifier() {return testCaseIdentifier;}
        
        public String toString()
        {
            return "Test Case: " + serviceProvider.getClass().getName() 
                    + " (" + testCaseIdentifier + "): " + validationStatus;
        }
        
        private Object serviceProvider;
        private Object testCaseIdentifier;
        private boolean pass;
        private ValidationStatus validationStatus;
        private String message;
        private Exception exceptionRaised;
    }
    public enum ValidationStatus {
        success, requestError, responseError, messagePatternError, generalFailure;}
}
