package org.urdad.services.mocking.example.shipping;

import javax.validation.constraints.NotNull;

import org.urdad.services.messaging.PreconditionViolation;
import org.urdad.services.messaging.Request;
import org.urdad.services.messaging.Response;
import org.urdad.services.mocking.example.legalEntities.Address;
import org.urdad.services.validation.RequestNotValidException;

public interface ShippingQuoteProvider 
{
	public GetShippingQuoteResponse getShippingQuote(GetShippingQuoteRequest request) 
			throws RequestNotValidException, 
					DoNotShipToAddressException, 
					RailwayOnStrikeException;
	
	class GetShippingQuoteRequest implements Request
	{
		
		public GetShippingQuoteRequest(Address address) {
			super();
			this.address = address;
		}

		public Address getAddress() {
			return address;
		}

		public void setAddress(Address address) {
			this.address = address;
		}

		@NotNull
		private Address address;
	}
	
	public class GetShippingQuoteResponse implements org.urdad.services.messaging.Response
	{
		
		public GetShippingQuoteResponse(double price) {
			super();
			this.price = price;
		}

		public double getPrice() {
			return price;
		}

		public void setPrice(double price) {
			this.price = price;
		}

		private double price;

	}

	class RailwayOnStrikeException extends PreconditionViolation
	{

		public RailwayOnStrikeException(){}

		public RailwayOnStrikeException(String msg) {super(msg);}

		public RailwayOnStrikeException(Throwable cause) {super(cause);}

		public RailwayOnStrikeException(String msg, Throwable cause) {super(msg,cause);}

	}

	class DoNotShipToAddressException extends PreconditionViolation
	{

		public DoNotShipToAddressException() {super();}

		public DoNotShipToAddressException(String message, Throwable cause) 
		{
			super(message, cause);
		}

		public DoNotShipToAddressException(String message) {super(message);}

		public DoNotShipToAddressException(Throwable cause) {super(cause);}

	}
}
