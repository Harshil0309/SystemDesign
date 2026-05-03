import java.util.*;

public class Order{
    private String id;
    private List<OrderItem>items;
    private OrderStatus status;
    private String userId;
    private String restaurantId;
    private String riderId;
    private double billAmount;

    public Order(String id, List<OrderItem> items, String userId, String restaurantId, double billAmount) {
        this.id = id;
        this.items = items;
        this.userId = userId;
        this.restaurantId = restaurantId;
        this.billAmount = billAmount;
        this.status = OrderStatus.CREATED;
        this.riderId = null;
    }


    public String getId(){
        return this.id;
    }

    public void acceptOrder() {
        if (this.status != OrderStatus.CREATED) {
            throw new IllegalStateException("Order can only be accepted from CREATED state");
        }
        this.status = OrderStatus.ACCEPTED;
    }

    public void startPreparing() {
        if (this.status != OrderStatus.ACCEPTED) {
            throw new IllegalStateException("Order must be ACCEPTED first");
        }
        this.status = OrderStatus.PREPARING;
    }

    public void outForDelivery() {
        if (this.status != OrderStatus.PREPARING) {
            throw new IllegalStateException("Order must be PREPARING first");
        }
        this.status = OrderStatus.OUTFORDELIVERY;
    }

    public void markDelivered() {
        if (this.status != OrderStatus.OUTFORDELIVERY) {
            throw new IllegalStateException("Order must be OUT_FOR_DELIVERY first");
        }
        this.status = OrderStatus.DELIVERED;
    }

    public void rejectOrder(){
        if(this.status!=OrderStatus.CREATED){
            throw new IllegalStateException("Order cannot be cancelled now");
        }
        this.status=OrderStatus.REJECTED;
    }

    public void assignRider(String riderId) {
        if (this.riderId != null) {
            throw new RuntimeException("Rider already assigned");
        }

        if (this.status != OrderStatus.ACCEPTED && 
            this.status != OrderStatus.PREPARING) {
            throw new RuntimeException("Cannot assign rider in current state");
        }

        if (riderId == null || riderId.isEmpty()) {
            throw new IllegalArgumentException("Invalid riderId");
        }

        this.riderId = riderId;
    }

}