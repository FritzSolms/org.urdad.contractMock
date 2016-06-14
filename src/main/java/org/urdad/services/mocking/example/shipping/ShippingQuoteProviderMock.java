package org.urdad.services.mocking.example.shipping;

import javax.inject.Inject;
import org.springframework.stereotype.Service;
import org.urdad.services.mocking.BaseMock;
import org.urdad.services.mocking.Mock;
import org.urdad.services.validation.RequestNotValidException;
import org.urdad.services.validation.beanvalidation.ServiceValidationUtilities;

/**
 * A mock ShippingQuoteProvider which 
 * <ul>
 *   <li>throws {@link RailwayOnStrikeException} if set in a railwayOnStrike state, </li>
 *   <li>throws {@link DoNotShipToAddressException} if the shipping city is Timbuktu,</li>
 *   <li>returns a zero quote if the city is Pofadder, and</li>
 *   <li>otherwise returns a fixed cost of 888.88.</li>
 * </ul>  
 * 
 * @author fritz@solms.co.za
 *
 */
@Service
public class ShippingQuoteProviderMock extends BaseMock implements ShippingQuoteProvider
{
    public ShippingQuoteProviderMock() 
            {setState(State.externalRequirementsMet);}

    public GetShippingQuoteResponse getShippingQuote(GetShippingQuoteRequest request) 
                    throws RequestNotValidException, DoNotShipToAddressException, RailwayOnStrikeException
    {
    // Check pre-condition: Request must be valid.
    serviceValidationUtilities.validateRequest(GetShippingQuoteRequest.class, request);

        if (getState() == State.railwayOnStrike)
                throw new RailwayOnStrikeException();

        if (request.getAddress().getCity().trim().toLowerCase().equals("timbuktu"))
                throw new DoNotShipToAddressException();

        if (request.getAddress().getCity().trim().toLowerCase().equals("pofadder"))
                return new GetShippingQuoteResponse(0);
        else
                return new GetShippingQuoteResponse(888.88);
    }

    public enum State implements Mock.State{externalRequirementsMet, railwayOnStrike}
	
    @Inject
    private ServiceValidationUtilities serviceValidationUtilities;
}