/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.urdad.services.mocking.example.retail;

import org.urdad.services.mocking.example.finance.PaymentMethod;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;
import org.urdad.services.messaging.Request;
import org.urdad.services.messaging.Response;

import org.urdad.services.mocking.example.finance.CouldNotSourceFundsException;
import org.urdad.services.mocking.example.finance.CouldNotConnectToBankException;
import org.urdad.services.mocking.example.finance.CouldNotProcessPaymentException;
import org.urdad.services.mocking.example.stockManagement.InsufficientStockException;
import org.urdad.services.validation.RequestNotValidException;

/**
 * @author fritz@solms.co.za
 */
public interface Sales {
    public ProcessOrderResponse processOrder(ProcessOrderRequest request)
        throws RequestNotValidException, InsufficientStockException, CouldNotProcessPaymentException;
    
    public class ProcessOrderRequest implements Request
    {
        public ProcessOrderRequest(Order order) {this.order = order.clone();}
        public Order getOrder() {return order.clone();}

        @NotNull @Valid private Order order;
        @NotNull @Valid private PaymentMethod paymentMethod;
    }
    
    public class ProcessOrderResponse implements Response
    {
//        @NotNull @Valid
//        private Receipt receipt;
        @NotNull @NotEmpty private String packageId;
    }
}
