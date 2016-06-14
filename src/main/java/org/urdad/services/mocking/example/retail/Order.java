package org.urdad.services.mocking.example.retail;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.urdad.services.mocking.example.Person;

public class Order implements Cloneable 
{
    public Order(Person buyer, Map<String, Item> orderItems) {
        super();
        this.buyer = buyer;
        this.orderItems = orderItems;}

    public Person getBuyer() {return buyer;}
    public void setBuyer(Person buyer) {this.buyer = buyer;}
    public Map<String, Item> getOrderItems() {return orderItems;}
    public void setOrderItems(Map<String, Item> orderItems) {this.orderItems = orderItems;}
    
    public Order clone() {
        Order copy = null;
        try {
            copy = (Order)super.clone();
            copy.orderItems = new HashMap(orderItems);
            return copy;
        } catch (CloneNotSupportedException e) {/* never thrown */}    
        return copy;}

    @NotNull @Valid private Person buyer;
    @NotEmpty @Valid private Map<String,Item> orderItems = new HashMap<String,Item>();
    
    public static class Item implements Cloneable
    {
        public Item(String productId, int quantity, double unitPrice)
        {
            this.productId = productId;
            this.quantity = quantity;
            this.unitPrice = unitPrice;
        }
        public String getProductId() {return productId;}
        public int getQuantity() {return quantity;}
        public double getUnitPrice() {return unitPrice;}
        
        @NotNull @NotEmpty private String productId;
        @Min(1) private int quantity;
        @DecimalMin("0.0") private double unitPrice;
    }
    
}
