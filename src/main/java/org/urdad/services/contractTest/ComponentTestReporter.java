package org.urdad.services.contractTest;

import java.util.List;
import org.urdad.services.messaging.Request;
import org.urdad.services.messaging.Response;

/**
 * @author fritz@solms.co.za
 */
public interface ComponentTestReporter 
{
    public ReportComponentTestResponse reportComponentTest
        (ReportComponentTestRequest request);
        
    public ReportTestCaseResponse reportTestCase
        (ReportTestCaseRequest request);
    
    public class ReportTestCaseRequest implements Request {
        public ReportTestCaseRequest(TestCaseValidator.ValidateTestCaseResponse 
                testCaseResponse) {
            this.testCaseResponse = testCaseResponse;
        }
        public TestCaseValidator.ValidateTestCaseResponse getTestCaseResponse() 
            {return testCaseResponse;}
        
        private TestCaseValidator.ValidateTestCaseResponse testCaseResponse;
    }
    public class ReportTestCaseResponse implements Response 
    {
        public ReportTestCaseResponse(boolean pass, String textReport) {
            this.textReport = textReport;
            this.pass = pass;
        }
        public String getTextReport() {return textReport;}
        public boolean isPass() {return pass;}
        
        private String textReport;
        private boolean pass;
    }

    public class ReportComponentTestRequest implements Request {
    public ReportComponentTestRequest
            (List<TestCaseValidator.ValidateTestCaseResponse> testCaseResponses) {
                this.testCaseResponses = testCaseResponses;}
        public List<TestCaseValidator.ValidateTestCaseResponse> getTestCaseResponses() {
            return testCaseResponses;}
        private List<TestCaseValidator.ValidateTestCaseResponse> testCaseResponses;
    }
    public class ReportComponentTestResponse implements Response 
    {
        public ReportComponentTestResponse(boolean pass, String textReport) {
            this.textReport = textReport;
            this.pass = pass;
        }
        public String getTextReport() {return textReport;}
        public boolean isPass() {return pass;}
        
        private String textReport;
        private boolean pass;
    }
}
