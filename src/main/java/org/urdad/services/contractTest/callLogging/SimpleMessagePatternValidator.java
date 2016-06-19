package org.urdad.services.contractTest.callLogging;

import org.urdad.services.contractTest.callLogging.CallDescriptor;
import org.urdad.services.contractTest.callLogging.CallRequirement;
import java.util.Iterator;
import org.springframework.stereotype.Service;
import org.urdad.services.contractTest.TestCaseValidator;

/**
 * @author fritz@solms.co.za
 */
@Service
public class SimpleMessagePatternValidator implements TestCaseValidator {

    @Override
    public ValidateTestCaseResponse validateTestCase(ValidateTestCaseRequest request) 
    {
        if (request.getCallLog().size() != request.getCallRequirements().size())
            return new ValidateTestCaseResponse(request.getServiceProvider(), 
                request.getTestCaseIdentifier(), false, 
                    TestCaseValidator.ValidationStatus.messagePatternError);    

        Iterator<CallRequirement> callRequirements = request.getCallRequirements().iterator();
        for (CallDescriptor call : request.getCallLog()) 
          if (fails(call, callRequirements.next()))
            return new ValidateTestCaseResponse(request.getServiceProvider(), 
                request.getTestCaseIdentifier(), false, 
                    TestCaseValidator.ValidationStatus.messagePatternError);
        
        return new ValidateTestCaseResponse(request.getServiceProvider(), 
            request.getTestCaseIdentifier(), true, 
                TestCaseValidator.ValidationStatus.success);
    }
    
    private boolean fails(CallDescriptor call, CallRequirement callRequirement)
    {
        return call.getMethod().getName().equals(callRequirement.getMethod().getName());
    }
    
}
