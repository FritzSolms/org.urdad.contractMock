package org.urdad.services.contractTest;

import javax.inject.Inject;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;
import org.urdad.services.ServiceUtilities;
import org.urdad.services.messaging.Response;

/**
 * @author fritz@solms.co.za
 */
public class GenericComponentTest implements ComponentTest
{
    @Override
    public TestComponentResponse testComponent(TestComponentRequest request) 
    {
        List<TestCaseValidator.ValidateTestCaseResponse> testCaseResponses
                = new LinkedList();
        Class serviceProvider = request.getServiceProvider();
        for (ServiceTestData serviceTest:request.getTestHarness().getServiceTests())
        {
            try
            {
            Method service = serviceUtilities.getService
                (serviceProvider, serviceTest.getServiceName());
            for (TestCaseData testCase:serviceTest.getTestCases()) 
            {
                String testCaseId = testCase.getTestCaseIdentifier();
                try {
                    testCase.getEnvironmentInitializer().initializeEnvironment
                        (new EnvironmentInitializer.InitializeEnvironmentRequest(testCaseId));
                    
                    callLogger.clearLog();
                    Response response = (Response)service.invoke
                        (serviceProvider, testCase.getRequest());
                    List<CallDescriptor> callLog = callLogger.getCallLog();
                    
                    TestCaseValidator.ValidateTestCaseResponse testCaseResponse
                            = testCase.getTestCaseValidator().validateTestCase
                        (new TestCaseValidator.ValidateTestCaseRequest(
                          serviceProvider, testCaseId, request, request,
                          testCase.getResponse(), response, testCase.getCallRequirements(),
                          callLog));
                }
                catch (Exception e) {
                    testCaseResponses.add(new TestCaseValidator.ValidateTestCaseResponse(
                      serviceProvider, testCaseId, false, 
                            TestCaseValidator.ValidationStatus.generalFailure, "", e));
                }
            }
            }
            catch (ServiceUtilities.NotAServiceException e) {
                testCaseResponses.add(new TestCaseValidator.ValidateTestCaseResponse(
                  serviceProvider, null, false, 
                        TestCaseValidator.ValidationStatus.generalFailure, "", e));
            }
        }
        return new TestComponentResponse(testCaseResponses);
    }
    @Inject private CallLogger callLogger;
    @Inject private ServiceUtilities serviceUtilities;
}
