package org.urdad.services.mocking.example.retail;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import java.time.LocalDate;
import java.time.Month;
import java.util.Map;
import java.util.TreeMap;
import javax.inject.Inject;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.urdad.services.ServiceUtilities;
import org.urdad.services.mocking.CallLogger;
import org.urdad.services.mocking.example.Address;
import org.urdad.services.mocking.example.Person;
import org.urdad.services.mocking.example.shipping.ShippingQuoteProvider;
import org.urdad.services.mocking.example.shipping.ShippingQuoteProviderMock;
import org.urdad.services.validation.RequestNotValidException;

/**
 * A unit test which is used 
 * <ol>
 *   <li> as a test for the services-oriented mocking framework </li>
 *   <li> to provide a reference implementation for using unit testing with 
 *   		services-oriented mocking </li>
 * </ol>
 *   
 * @author fritz@solms.co.za
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {OrderPricerTestConfiguration.class})
public class OrderPricerTest 
{
    @After
    public void cleanup()
    {
        callLogger.clearLog();
    }

    /**
     * Unit test demonstrating normal execution with normal assertions as well as
     * using the mock-object's call log to assert that the required calls were made to address the
     * pre- and post-conditions for the service
     */
    @Test
    public void everythingKosherTest() 
    {
        Order order = new Order(createNormalPerson(), createDefaultOrderItems());

        // Set mock objects into desired states:
        shippingQuoteProvider.setState(ShippingQuoteProviderMock.State.externalRequirementsMet);

        try
        {
            double cost = orderPricer.getOrderCost(new OrderPricer.GetOrderCostRequest(order)).getCost();

            // Check return value state
            assertEquals(1488.82, cost, 1e-6);

            // Use the call logger populated by the call logging aspect to 
            // check calls used to address post-conditions.
            assertEquals(1,callLogger.getInvocationCount(serviceUtilities.getService(shippingQuoteProvider, "getShippingQuote")));
            assertEquals(2,callLogger.getInvocationCount(serviceUtilities.getService(itemPricer, "getItemPrice")));
        }
        catch (Exception e)
        {
            fail("Exception " + e.getClass().getName() + " thrown, but should not have been thrown.");
        }
    }

    /**
     * This test demonstrates the state management for mock objects. We are setting the 
     * mock object in a state which is independent of the input. In this case this is a state 
     * where the service provider refuses/cannot provide the service;
     * 
     * @throws Exception
     */
    @Test(expected = ShippingQuoteProvider.RailwayOnStrikeException.class) 
    public void railwayOnStrikeTest() throws Exception
    {
        shippingQuoteProvider.setState(ShippingQuoteProviderMock.State.railwayOnStrike);

        orderPricer.getOrderCost(new OrderPricer.GetOrderCostRequest
            (new Order(createNormalPerson(), createDefaultOrderItems())));
    }

    /**
     * Sets mock object into normal state and checks that exception associated with 
     * input-related pre-condition check raised.
     * 
     * @throws Exception
     */
    @Test(expected = ShippingQuoteProvider.DoNotShipToAddressException.class)
    public void testUnshippableAddress() throws Exception
    {
        shippingQuoteProvider.setState(ShippingQuoteProviderMock.State.externalRequirementsMet);

        orderPricer.getOrderCost(new OrderPricer.GetOrderCostRequest
            (new Order(createTimbuktuen(), createDefaultOrderItems())));
    }

    /**
     * Test a boundary value/extreme point for the input data -- that of an empty order.
     * 
     * @throws Exception
     */
    @Test
    public void emptyOrderTest() throws Exception
    {
        shippingQuoteProvider.setState(ShippingQuoteProviderMock.State.externalRequirementsMet);

        assertEquals(888.88, orderPricer.getOrderCost(new OrderPricer.GetOrderCostRequest
            (new Order(createNormalPerson(), new TreeMap<String, Order.Item> ()))).getCost(),1e-7);
    }

    @Test(expected=RequestNotValidException.class)
    public void nullOrderTest() throws Exception
    {
        shippingQuoteProvider.setState(ShippingQuoteProviderMock.State.externalRequirementsMet);

        orderPricer.getOrderCost(new OrderPricer.GetOrderCostRequest(null));
    }

    private static Person createNormalPerson()
    {
        Address address = new Address("Road to Nowhere", "Lusikisiki");
        return new Person("Abraham", LocalDate.of(1985, Month.APRIL, 22), address);
    }

    private static Person createTimbuktuen()
    {
        Address address = new Address("111 Library Road", "Timbuktu");
        return new Person("Fazil", LocalDate.of(1908, Month.APRIL, 1), address);
    }


    private static Map<String, Order.Item> createDefaultOrderItems()
    {
        Map<String, Order.Item> orderItems = new TreeMap<String, Order.Item>();
        orderItems.put("jam", new Order.Item("jam", 5, 12.5));
        orderItems.put("rooibosTea", new Order.Item("rooibosTea", 1, 20));
        return orderItems;
    }

    @Inject
    private ShippingQuoteProviderMock shippingQuoteProvider;
    @Inject
    private ItemPricerMock itemPricer;
    @Inject
    private OrderPricer orderPricer;
    @Inject
    private CallLogger callLogger;
    @Inject
    private ServiceUtilities serviceUtilities;
}
