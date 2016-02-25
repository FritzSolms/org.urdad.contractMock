package org.urdad.services.mocking.example.retail;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.urdad.services.messaging.Request;
import org.urdad.services.messaging.Response;
import org.urdad.services.mocking.example.shipping.ShippingQuoteProvider;
import org.urdad.services.validation.RequestNotValidException;

public interface OrderPricer 
{

	GetOrderCostResponse getOrderCost(GetOrderCostRequest request) throws RequestNotValidException,
		ShippingQuoteProvider.DoNotShipToAddressException, ItemPricer.ItemNotAvailableException, ShippingQuoteProvider
		.RailwayOnStrikeException;
	
	class GetOrderCostRequest implements Request
	{
		
		public GetOrderCostRequest(Order order) {
			super();
			this.order = order;
		}

		public Order getOrder() {
			return order;
		}

		public void setOrder(Order order) {
			this.order = order;
		}

		@NotNull
		private Order order;
	}
	
	class GetOrderCostResponse implements Response
	{
		
		public GetOrderCostResponse(double cost) {
			super();
			this.cost = cost;
		}

		public double getCost() {
			return cost;
		}

		public void setCost(double cost) {
			this.cost = cost;
		}

		@Min(0)
		private double cost;
	}
	
}