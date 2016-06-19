package org.urdad.services.contractTest.testData;

import org.urdad.services.contractTest.callLogging.CallRequirement;
import java.util.List;
import org.urdad.services.contractTest.EnvironmentInitializer;
import org.urdad.services.contractTest.TestCaseValidator;
import org.urdad.services.messaging.Request;
import org.urdad.services.messaging.Response;

/**
 * @author fritz@solms.co.za
 */
public class TestCaseData {
    public TestCaseData(Object testCaseIdentifier,
            EnvironmentInitializer environmentInitializer, 
            Request request, Response response, 
            List<CallRequirement> messageExchangeList, 
            TestCaseValidator testCaseValidator) {
        this.testCaseIdentifier = testCaseIdentifier;
        this.request = request;
        this.response = response;
        this.callRequirements = messageExchangeList;
        this.environmentInitializer = environmentInitializer;
        this.testCaseValidator = testCaseValidator;
        
        Class theClass = TestCaseValidator.class;
    }
    public EnvironmentInitializer getEnvironmentInitializer() {
        return environmentInitializer;
    }
    public Object getTestCaseIdentifier() {return testCaseIdentifier;}
    public Request getRequest() {return request;}
    public Response getResponse() {return response;}
    public List<CallRequirement> getCallRequirements() {return callRequirements;}
    public TestCaseValidator getTestCaseValidator() {return testCaseValidator;}
    
    private Object testCaseIdentifier;
    private Request request;
    private Response response;
    private List<CallRequirement> callRequirements;
    private TestCaseValidator testCaseValidator;
    private EnvironmentInitializer environmentInitializer;
}
