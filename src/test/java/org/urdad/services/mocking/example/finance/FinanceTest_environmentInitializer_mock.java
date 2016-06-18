package org.urdad.services.mocking.example.finance;

import javax.inject.Inject;
import org.urdad.services.ServiceUtilities;
import org.urdad.services.contractTest.EnvironmentInitializer;
import org.urdad.services.mocking.Mock;
import org.urdad.services.mocking.example.finance.accounts.Accounts;
import org.urdad.services.mocking.example.finance.accounts.AccountsMock;
import org.urdad.services.mocking.example.finance.bank.Banking;
import org.urdad.services.mocking.example.finance.paymentGateway.PaymentGateway;
import org.urdad.services.mocking.example.finance.paymentGateway.PaymentGatewayMock;
import org.urdad.services.validation.RequestNotValidException;

/**
 * @author fritz@solms.co.za
 */
public class FinanceTest_environmentInitializer_mock implements EnvironmentInitializer {

    @Override
    public InitializeEnvironmentResponse initializeEnvironment
        (InitializeEnvironmentRequest request) 
                throws RequestNotValidException, InitializationException {
        if (request.getTestCaseIdentifier().equals(FinanceTest_scenarios.processPayment_accountDebit_success))
            ((Mock)accounts).setState(AccountsMock.State.accountDebitSuccess);
        if (request.getTestCaseIdentifier().equals(FinanceTest_scenarios.processPayement_accountDebit_fail))
            ((Mock)accounts).setState(AccountsMock.State.couldNotSourceFunding);
        if (request.getTestCaseIdentifier().equals(FinanceTest_scenarios.processPayment_creditCard_success))
            ((Mock)accounts).setState(PaymentGatewayMock.State.sourcingAndCreditingSuccessfull);
        if (request.getTestCaseIdentifier().equals(FinanceTest_scenarios.processPayment_creditCard_couldNotSourceFunds))
            ((Mock)accounts).setState(PaymentGatewayMock.State.paymentFailedBecauseSourcingFailed);
        if (request.getTestCaseIdentifier().equals(FinanceTest_scenarios.processPayment_creditCard_couldNotCreditDestinationAccount))
            ((Mock)accounts).setState(PaymentGatewayMock.State.paymentFailedBecauseCouldNotCreditDestinationAccount);
        return new InitializeEnvironmentResponse();
    }
    @Inject private ServiceUtilities serviceUtilities;
    @Inject private Banking banking;
    @Inject private Accounts accounts;
    @Inject private PaymentGateway paymentGateway;    
}
