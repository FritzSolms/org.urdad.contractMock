/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.urdad.services.mocking.example.stockManagement;

import javax.inject.Inject;
import org.urdad.services.mocking.BaseMock;
import org.urdad.services.validation.RequestNotValidException;
import org.urdad.services.validation.beanvalidation.ServiceValidationUtilities;

/**
 * Returns a reservation if and only if the order does not contain more than
 * five items of jam.
 * @author fritz
 */
public class StockManagementMock extends BaseMock implements StockManagement
{
    /**
     * Returns a reservation response with a reservation id of "111" if the order
     * does not contain more than one
     * @param request The reservation request
     * @return A reservation confirmation with a reservation number of "111"
     * @throws InsufficientStockException if the order contains more than 5 items of jam
     */
    @Override
    public ReserveStockResponse reserveStock(ReserveStockRequest request) 
            throws RequestNotValidException, InsufficientStockException {
        serviceValidationUtilities.validateRequest(ReserveStockRequest.class, request);
    }

    @Override
    public ReleaseStockResponse releaseStock(ReleaseStockRequest request) 
        throws RequestNotValidException {return new ReleaseStockResponse();}

    @Override
    public IssueStockResponse issueStock(IssueStockRequest request) {
        return new IssueStockResponse();}
    
    @Inject private ServiceValidationUtilities serviceValidationUtilities;    
}
