package org.urdad.services.mocking.example.retail;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.urdad.services.mocking.example.Person;

public class Order {

	public Order(Person buyer, Map<String, Integer> orderItems) {
		super();
		this.buyer = buyer;
		this.orderItems = orderItems;
	}
	
	public Person getBuyer() {
		return buyer;
	}
	public void setBuyer(Person buyer) {
		this.buyer = buyer;
	}
	public Map<String, Integer> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(Map<String, Integer> orderItems) {
		this.orderItems = orderItems;
	}
	
	@NotNull
	@Valid
	private Person buyer;
	
	@NotEmpty
	private Map<String,Integer> orderItems = new HashMap<String,Integer>();
}
