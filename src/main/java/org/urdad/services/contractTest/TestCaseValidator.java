package org.urdad.services.contractTest;

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
        public ValidateTestCaseRequest(Class serviceProvider, String testCaseIdentifier,
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
        public Class getServiceProvider() {return serviceProvider;}
        public String getTestCaseIdentifier() {return testCaseIdentifier;}
        public Request getRequiredRequest() {return requiredRequest;}
        public Request getMadeRequest() {return madeRequest;}
        public Response getRequiredResponse() {return requiredResponse;}
        public Response getMadeResponse() {return madeResponse;}
        public List<CallRequirement> getCallRequirements() {return callRequirements;}
        public List<CallDescriptor> getCallLog() {return callLog;}
        
        private Class serviceProvider;
        private String testCaseIdentifier;
        private Request requiredRequest, madeRequest;
        private Response requiredResponse, madeResponse;
        private List<CallRequirement> callRequirements; 
        private List<CallDescriptor> callLog; 
    }
    
    public class ValidateTestCaseResponse
    {
        public ValidateTestCaseResponse(Class serviceProvider,
                String testCaseIdentifier, boolean pass, ValidationStatus validationStatus) {
            this(serviceProvider, testCaseIdentifier, pass, validationStatus, null);
        }
        public ValidateTestCaseResponse(Class serviceProvider,
                String testCaseIdentifier, boolean pass, ValidationStatus validationStatus,
                String message) {
            this(serviceProvider, testCaseIdentifier, pass, validationStatus, message, null);}
                
        public ValidateTestCaseResponse(Class serviceProvider,
                String testCaseIdentfier, boolean pass, ValidationStatus validationStatus, 
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
        public Class getServiceProvider() {return serviceProvider;}
        public String getTestCaseIdentifier() {return testCaseIdentifier;}
        
        private Class serviceProvider;
        private String testCaseIdentifier;
        private boolean pass;
        private ValidationStatus validationStatus;
        private String message;
        private Exception exceptionRaised;
    }
    public enum ValidationStatus {
        success, requestError, responseError, messagePatternError, generalFailure;
    }
}