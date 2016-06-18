package org.urdad.services.contractTest;

/**
 * @author fritz@solms.co.za
 */
public class DefaultComponentTestReporter implements ComponentTestReporter {
    @Override
    public ReportComponentTestResponse reportComponentTest(ReportComponentTestRequest request) {
        StringBuilder textReport = new StringBuilder();
        boolean pass = true;
        for (TestCaseValidator.ValidateTestCaseResponse testCaseResponse:request.getTestCaseResponses())
        {
            ReportTestCaseResponse testCaseReport 
                = reportTestCase(new ReportTestCaseRequest(testCaseResponse));
            pass = pass && testCaseReport.isPass();
            textReport.append(testCaseReport.getTextReport());
        }    
        return new ReportComponentTestResponse(pass, textReport.toString());
    }

    @Override
    public ReportTestCaseResponse reportTestCase(ReportTestCaseRequest request) {
        TestCaseValidator.ValidateTestCaseResponse testCaseResponse 
                = request.getTestCaseResponse();
        StringBuilder textReport = new StringBuilder();
        textReport.append("#### ");
        textReport.append(testCaseResponse.getServiceProvider().getClass().getName());
        textReport.append(" (").append(testCaseResponse.getTestCaseIdentifier()).append(")");
        if (testCaseResponse.isPass())
            textReport.append(" PASS :");
        else
            textReport.append(" FAIL :");
        if (testCaseResponse.getMessage()!= null)
            textReport.append(": ").append(testCaseResponse.getMessage());
        textReport.append(eol);
        Exception exception = request.getTestCaseResponse().getExceptionRaised();
        if (exception != null) {
            textReport.append("Exception raised: ").append(exception);
            textReport.append(exception.getStackTrace());
        }
        return new ReportTestCaseResponse(request.getTestCaseResponse().isPass(),
            textReport.toString());
    }
    private static final String eol = System.getProperty("line.separator");
}
