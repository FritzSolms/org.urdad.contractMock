package org.urdad.services.contractTest.testData;

import java.util.List;

/**
 * @author fritz@solms.co.za
 */
public class ServiceTestData {

    public ServiceTestData(String serviceName, List<TestCaseData> testCases) {
        this.serviceName = serviceName;
        this.testCases = testCases;
    }
    public String getServiceName() {return serviceName;}
    public List<TestCaseData> getTestCases() {return testCases;}
    
   private String serviceName; 
   private List<TestCaseData> testCases; 
}
