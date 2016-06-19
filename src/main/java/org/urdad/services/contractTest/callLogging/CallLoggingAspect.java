package org.urdad.services.contractTest.callLogging;
import org.urdad.services.contractTest.callLogging.CallDescriptor;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import javax.inject.Inject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.urdad.services.messaging.Request;
/**
 * Logging aspect for Mock objects which intercepts all mock object calls and 
 * adds the call events to the call history of the mock object.
 * @author fritz@solms.co.za
 */
@Aspect
public class CallLoggingAspect 
{
    /**
     * Logs all service calls, but not other method calls.
     * @param joinPoint
     * @throws Throwable */
    @Around("execution(org.urdad.services.messaging.Response+ *.*(org.urdad.services.messaging.Request+))"
                    + " && ( @within(org.urdad.services.mocking.MockObject)"
                    + " || @annotation(org.urdad.services.mocking.MockObject) )")
    public Object logCall(ProceedingJoinPoint joinPoint) throws Throwable 
    {
        LocalDateTime requestTime = LocalDateTime.now();
        Object target = joinPoint.getTarget();
        Method method = ((MethodSignature)joinPoint.getStaticPart().getSignature()).getMethod();
        Request request = null;
        request = (Request)joinPoint.getArgs()[0];

        Object response = null;
        LocalDateTime responseTime;
        try {
          response = joinPoint.proceed();
          responseTime = LocalDateTime.now();
          if (request != null)
            callLogger.logCall(new CallDescriptor(target, method, requestTime, request, response, responseTime));
          return response;
        }
        catch (Exception e) {
            responseTime = LocalDateTime.now();
            callLogger.logCall(new CallDescriptor(target, method, requestTime, request, e, responseTime));
            throw e; 
        }
    }
    @Inject
    private CallLogger callLogger;
}
