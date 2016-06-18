package org.urdad.services.contractTest;

import org.springframework.stereotype.Service;
import org.urdad.services.messaging.Request;
import org.urdad.services.messaging.Response;

/**
 * @author fritz@solms.co.za
 */
@Service
public interface TestHarnessFactory {
    public ProvideTestHarnessResponse provideTestHarness(ProvideTestHarnessRequest request)
            throws Exception;
    
    public class ProvideTestHarnessRequest implements Request {}
    public class ProvideTestHarnessResponse implements Response {
        public ProvideTestHarnessResponse(TestHarness testHarness) {
            this.testHarness = testHarness;
        }
        public TestHarness getTestHarness() {return testHarness;}
        private TestHarness testHarness;
    }
}
