package org.urdad.services.contractTest;

/**
 * @author fritz@solms.co.za
 */
public class JunitComponentTestReporter implements ComponentTestReporter {
    @Override
    public ReportComponentTestResponse reportComponentTest(ReportComponentTestRequest request) {
        for (TestCaseValidator.ValidateTestCaseResponse testCaseResponse:request.getTestCaseResponses())
            reportTestCase(new ReportTestCaseRequest(testCaseResponse));
            //if (testCaseResponse.getValidationStatus()!=TestCaseValidator.ValidationStatus.success)
        return new ReportComponentTestResponse();
    }

    @Override
    public ReportTestCaseResponse reportTestCase(ReportTestCaseRequest request) {
        System.out.println("#############################################");
        System.out.println(request.getTestCaseResponse());
        if (request.getTestCaseResponse().getMessage() != null)
            System.out.println("Message: " + request.getTestCaseResponse().getMessage());
        Exception exception = request.getTestCaseResponse().getExceptionRaised();
        if (exception != null) {
            System.out.println("Exception raised: " + exception);
            exception.printStackTrace();
            
        }
        System.out.println("********************************************");
        return new ReportTestCaseResponse();
    }
    
}
