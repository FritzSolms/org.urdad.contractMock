package org.urdad.services.mocking.example.finance;

import java.time.LocalDateTime;
import javax.inject.Inject;
import org.urdad.services.ServiceUtilities;
import org.urdad.services.mocking.BaseMock;
import org.urdad.services.contractTest.CallDescriptor;
import org.urdad.services.contractTest.CallLogger;
import org.urdad.services.mocking.Mock;
import org.urdad.services.mocking.example.finance.accounts.Accounts;
import org.urdad.services.mocking.example.finance.bank.BankAccountDetails;
import org.urdad.services.mocking.example.finance.bank.Banking;
import org.urdad.services.mocking.example.finance.paymentGateway.CreditCardPaymentConfirmation;
import org.urdad.services.mocking.example.finance.paymentGateway.PaymentGateway;
import org.urdad.services.validation.RequestNotValidException;
import org.urdad.services.validation.beanvalidation.ServiceValidationUtilities;

/**
 * @author fritz@solms.co.za
 */
public class FinanceMock extends BaseMock implements Finance {
    /**
     * Throws {@link CouldNotProcessPaymentException} if 
     * <ol>
     *   <li> in {@link State.paymentNotAccessibleState} </li>
     *   <li> if the amount on the credit card payment exceeds 5000 or </li>
     *   <li> if the amount on account payment exceeds 10000. </li>
     * <ol>
     * otherwise, returns 
     */
    @Override
    public ProcessPaymentResponse processPayment(ProcessPaymentRequest request) 
            throws RequestNotValidException, CouldNotProcessPaymentException 
    {
        serviceValidationUtilities.validateRequest(ProcessPaymentRequest.class, request);
        
        if (CreditCardPayment.class.isAssignableFrom(request.getPaymentMethod().getClass()))
        {
            CreditCardPayment cardPayment = (CreditCardPayment)request.getPaymentMethod();

            BankAccountDetails destinationAccount 
                    = new BankAccountDetails("ABANK", "swftNo", "Peter", "100");
            try
            {
              callLogger.logCall(
                new CallDescriptor(Banking.class,serviceUtilities.getService(
                        Banking.class, "getBankAcountDetails"), 
                    LocalDateTime.now(), 
                    new Banking.GetBankAccountDetailsRequest(null), 
                    new Banking.GetBankAccountDetailsResponse(destinationAccount), 
                    LocalDateTime.now()));                
              } catch (ServiceUtilities.NotAServiceException e) {
                  throw new RuntimeException("Coding error - service does not exist");}

            if (getState().equals(State.paymentGatewayNotAccessible))
                throw new CouldNotProcessPaymentException();
            if (request.getAmount() > 5000)
            {
              try {      
                callLogger.logCall(
                new CallDescriptor(PaymentGateway.class, serviceUtilities.getService(
                        PaymentGateway.class, "processCreditCardPayment"), 
                    LocalDateTime.now(), 
                    new PaymentGateway.ProcessCreditCardPaymentRequest
                        (cardPayment.getCardDetails(), destinationAccount,
                         request.getAmount()), 
                    new CouldNotSourceFundsException(), 
                    LocalDateTime.now()));                
              } catch (ServiceUtilities.NotAServiceException e) {
                  throw new RuntimeException("Coding error - service does not exist");}
              throw new CouldNotProcessPaymentException();
            }
            else 
            {
              try {      
                callLogger.logCall(
                new CallDescriptor(PaymentGateway.class, serviceUtilities.getService(
                        PaymentGateway.class, "processCreditCardPayment"), 
                    LocalDateTime.now(), 
                    new PaymentGateway.ProcessCreditCardPaymentRequest
                        (cardPayment.getCardDetails(), destinationAccount,
                         request.getAmount()), 
                    new PaymentGateway.ProcessCreditCardPaymentResponse
                        (new CreditCardPaymentConfirmation
                            (cardPayment.getCardDetails(), destinationAccount,
                            request.getAmount(), LocalDateTime.now())),
                        LocalDateTime.now()));                
              } catch (ServiceUtilities.NotAServiceException e) {
                  throw new RuntimeException("Coding error - service does not exist");}
              return new ProcessPaymentResponse(new Receipt(request.getAmount(),
                      request.getPayer(), request.getPaymentMethod(), LocalDateTime.now()));
            }
        }
        else if (AccountPayment.class.isAssignableFrom(request.getPaymentMethod().getClass()))
        {
            if (request.getAmount() > 10000)
            {
              try {      
                callLogger.logCall(
                  new CallDescriptor(PaymentGateway.class, serviceUtilities.getService(Accounts.class, "debitAccount"), 
                    LocalDateTime.now(), 
                    new Accounts.DebitAccountRequest(request.getPayer(), request.getAmount()), 
                    new CouldNotSourceFundsException(), 
                    LocalDateTime.now()));                
              } catch (ServiceUtilities.NotAServiceException e) {
                  throw new RuntimeException("Coding error - service does not exist");}
              throw new CouldNotProcessPaymentException();
            }    
            else
            {
              try {      
                callLogger.logCall(
                  new CallDescriptor(PaymentGateway.class, serviceUtilities.getService(Accounts.class, "debitAccount"), 
                    LocalDateTime.now(), 
                    new Accounts.DebitAccountRequest(request.getPayer(), request.getAmount()), 
                    new Accounts.DebitAccountResponse(request.getPayer(), request.getAmount(), null), 
                    LocalDateTime.now()));
              } catch (ServiceUtilities.NotAServiceException e) {
                  throw new RuntimeException("Coding error - service does not exist");}
            }    
        }
        else throw new RequestNotValidException("Unsupported payment method");
        return new ProcessPaymentResponse(
                new Receipt(request.getAmount(), request.getPayer(),
                request.getPaymentMethod(), LocalDateTime.now()));}

    public enum State implements Mock.State{externalRequirementsMet, paymentGatewayNotAccessible}    
    
    @Inject private CallLogger callLogger;
    @Inject private ServiceValidationUtilities serviceValidationUtilities;
    @Inject private ServiceUtilities serviceUtilities;
}
