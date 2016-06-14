/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.urdad.services.mocking.example.retail;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;
import org.urdad.services.messaging.Request;
import org.urdad.services.messaging.Response;

import org.urdad.services.mocking.example.finance.InsufficientFundsException;
import org.urdad.services.mocking.example.finance.CouldNotConnectToBankException;
import org.urdad.services.mocking.example.finance.PaymentFailedException;
import org.urdad.services.mocking.example.stockManagement.InsufficientStockException;
import org.urdad.services.validation.RequestNotValidException;

/**
 *
 * @author fritz@solms.co.za
 */
public interface Sales {
    public ProcessOrderResponse processOrder(ProcessOrderRequest request)
        throws RequestNotValidException, InsufficientStockException, PaymentFailedException;
    
    public class ProcessOrderRequest implements Request
    {
        public ProcessOrderRequest(Order order) {this.order = order.clone();}
        public Order getOrder() {return order.clone();}
        // Composition
        private Order order;
    }
    
    public class ProcessOrderResponse implements Response
    {
//        @NotNull @Valid
//        private Receipt receipt;
        @NotNull @NotEmpty
        private String packageId;
    }
}
