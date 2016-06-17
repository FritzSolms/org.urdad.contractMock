package org.urdad.services.contractTest;

import java.util.List;
import java.util.Map;

/**
 * @author fritz@solms.co.za
 */
public class TestHarness {

    public TestHarness(Class contract, List<ServiceTestData> serviceTests) {
        this.contract = contract;
        this.serviceTests = serviceTests;
    }
    public Class getContract() {return contract;}
    public List<ServiceTestData> getServiceTests() {return serviceTests;}
    
    private Class contract;
    private List<ServiceTestData> serviceTests;
}
