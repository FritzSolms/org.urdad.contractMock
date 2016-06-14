package org.urdad.services.mocking.example.stockManagement;

import java.util.HashMap;
import java.util.Map;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;
import org.urdad.services.validation.RequestNotValidException;

/**
 * @author fritz@solms.co.za
 */
public interface StockManagement {
    public ReserveStockResponse reserveStock(ReserveStockRequest request)
            throws RequestNotValidException, InsufficientStockException;
    public ReleaseStockResponse releaseStock(ReleaseStockRequest request)
            throws RequestNotValidException;
    public IssueStockResponse issueStock(IssueStockRequest request)
            throws RequestNotValidException;
    
    public class ReserveStockRequest
    {
        public ReserveStockRequest(Map<String, Integer> requiredStockItems) {
            this.requiredStockItems = new HashMap(requiredStockItems);}
        @NotNull @NotEmpty        
        private Map<String, Integer> requiredStockItems;
    }
    public abstract class ReservationDetails {
        public ReservationDetails(String reservationId) {
            this.reservationId = reservationId;}
        private String reservationId;
    }
    public class ReserveStockResponse extends ReservationDetails {
        public ReserveStockResponse(String reservationId) {
            super(reservationId);}
    }
    public class ReleaseStockRequest extends ReservationDetails {
        public ReleaseStockRequest(String reservationId) {
            super(reservationId);}
    }
    public class IssueStockRequest extends ReservationDetails {
        public IssueStockRequest(String reservationId) {
            super(reservationId);}
    }
    public class ReleaseStockResponse {}
    public class IssueStockResponse {}
}
