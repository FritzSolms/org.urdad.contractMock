package org.urdad.services.contractTest;

import org.urdad.services.contractTest.testData.TestHarness;
import java.util.List;
import org.urdad.services.messaging.Request;
import org.urdad.services.messaging.Response;

/**
 * @author fritz@solms.co.za
 */
public interface ComponentTest {
    public TestComponentResponse testComponent(TestComponentRequest request);
    
    public class TestComponentRequest implements Request {

        public TestComponentRequest(Object serviceProvider,
                TestHarness testHarness) {
            this.serviceProvider = serviceProvider;
            this.testHarness = testHarness;
        }
        public Object getServiceProvider() {return serviceProvider;}
        public TestHarness getTestHarness() {return testHarness;}

        final private Object serviceProvider;
        final private TestHarness testHarness;
    }
    public class TestComponentResponse implements Response {

        public TestComponentResponse(List<TestCaseValidator.ValidateTestCaseResponse> testCaseResponses) {
            this.testCaseResponses = testCaseResponses;
        }
        public List<TestCaseValidator.ValidateTestCaseResponse> getTestCaseResponses() {
            return testCaseResponses;
        }
        private List<TestCaseValidator.ValidateTestCaseResponse> testCaseResponses;
    }
}
