package org.urdad.services.mocking.example.finance;

import javax.inject.Inject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.urdad.services.contractTest.ComponentTest;
import org.urdad.services.contractTest.ComponentTestReporter;
import org.urdad.services.contractTest.TestHarnessFactory;

/**
 * Can be executed in different test harnesses for unit and integration testing
 * @author fritz@solms.co.za
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {FinanceTest_configuration_mock.class})
public class FinanceTest {
    @Test
    public void testComponentAgainstContract() throws Exception
    {
        componentTestReporter.reportComponentTest
            (new ComponentTestReporter.ReportComponentTestRequest
            (componentTest.testComponent
            (new ComponentTest.TestComponentRequest
            (finance, testHarnessFactory.provideTestHarness
            (new TestHarnessFactory.ProvideTestHarnessRequest()
              ).getTestHarness())).getTestCaseResponses()));
    }
    @Inject private Finance finance;
    @Inject private ComponentTest componentTest;
    @Inject private TestHarnessFactory testHarnessFactory;
    @Inject private ComponentTestReporter componentTestReporter;
}
