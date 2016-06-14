package org.urdad.services.mocking.example.stockManagement;

import java.util.HashMap;
import java.util.Map;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;
import org.urdad.services.messaging.Request;
import org.urdad.services.messaging.Response;
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
    public OrderStockResponse orderStock(OrderStockRequest request)
            throws RequestNotValidException;
 
    public class ItemsListRequest implements Request
    {
        public ItemsListRequest(Map<String, Integer> requiredStockItems) {
            this.requiredStockItems = new HashMap(requiredStockItems);}
        
        public Map<String, Integer> getRequiredStockItems() {
            return new HashMap(requiredStockItems);}
        @NotNull @NotEmpty private Map<String, Integer> requiredStockItems;
    }
    
    public class ReserveStockRequest extends ItemsListRequest {
        public ReserveStockRequest(Map<String, Integer> requiredStockItems) {
            super(requiredStockItems);}
    }
    public abstract class ReservationDetails {
        public ReservationDetails(String reservationId) {
            this.reservationId = reservationId;}
        @NotNull @NotEmpty private String reservationId;
    }
    public class ReserveStockResponse extends ReservationDetails implements Response {
        public ReserveStockResponse(String reservationId) {
            super(reservationId);}
    }
    public class ReleaseStockRequest extends ReservationDetails implements Request {
        public ReleaseStockRequest(String reservationId) {
            super(reservationId);}
    }
    public class ReleaseStockResponse implements Response {}
    public class IssueStockRequest extends ReservationDetails implements Request {
        public IssueStockRequest(String reservationId) {
            super(reservationId);}
    }
    public class IssueStockResponse implements Response {}
    public class OrderStockRequest extends ItemsListRequest {
        public OrderStockRequest(Map<String, Integer> requiredStockItems) {
            super(requiredStockItems);}
    }
    public class OrderStockResponse implements Response {
        public OrderStockResponse(String orderNo) {
            this.orderNo = orderNo;}
        @NotNull @NotEmpty private String orderNo;
    }
    
}
