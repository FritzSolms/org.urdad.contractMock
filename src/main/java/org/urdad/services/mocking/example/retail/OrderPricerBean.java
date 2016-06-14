package org.urdad.services.mocking.example.retail;

import java.util.Map;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import org.urdad.services.mocking.example.Address;
import org.urdad.services.mocking.example.shipping.ShippingQuoteProvider;
import org.urdad.services.validation.RequestNotValidException;
import org.urdad.services.validation.beanvalidation.ServiceValidationUtilities;

@Service
public class OrderPricerBean implements OrderPricer
{

    @Override
    public GetOrderCostResponse getOrderCost(GetOrderCostRequest request) throws RequestNotValidException,
    ShippingQuoteProvider.DoNotShipToAddressException, ItemPricer.ItemNotAvailableException, ShippingQuoteProvider.RailwayOnStrikeException
    {
        // Check pre-condition: Request must be valid.
        serviceValidationUtilities.validateRequest(GetOrderCostRequest.class, request);

        Map<String,Order.Item> orderItems = request.getOrder().getOrderItems();
        double total = 0;

        for (String itemCode : orderItems.keySet())
        {
            ItemPricer.GetItemPriceRequest req = new ItemPricer.GetItemPriceRequest(request.getOrder().getBuyer(),
            itemCode);
            total += itemPricer.getItemPrice(req).getPrice() * orderItems.get(itemCode).getQuantity();
        }

        Address shippingAddress = request.getOrder().getBuyer().getAddress();
        total += shippingQuoteProvider.getShippingQuote
                (new ShippingQuoteProvider.GetShippingQuoteRequest(shippingAddress)).getPrice();

        return new GetOrderCostResponse(total);
    }

    @Inject private ServiceValidationUtilities serviceValidationUtilities;
    @Inject private ItemPricer itemPricer;
    @Inject private ShippingQuoteProvider shippingQuoteProvider;
}