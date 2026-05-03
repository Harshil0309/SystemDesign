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



    public void acceptOrder() {
        if (this.status != OrderStatus.CREATED) {
            System.out.println("Order can only be accepted from CREATED state");
            return;
        }
        this.status = OrderStatus.ACCEPTED;
    }

    public void startPreparing() {
        if (this.status != OrderStatus.ACCEPTED) {
            System.out.println("Order must be ACCEPTED first");
            return;
        }
        this.status = OrderStatus.PREPARING;
    }

    public void outForDelivery() {
        if (this.status != OrderStatus.PREPARING) {
            System.out.println("Order must be PREPARING first");
            return;
        }
        this.status = OrderStatus.OUTFORDELIVERY;
    }

    public void markDelivered() {
        if (this.status != OrderStatus.OUTFORDELIVERY) {
            System.out.println("Order must be OUT_FOR_DELIVERY first");
            return;
        }
        this.status = OrderStatus.DELIVERED;
    }

    public void rejectOrder(){
        if(this.status!=OrderStatus.CREATED){
            System.out.println("Order cannot be cancelled now");
            return;
        }
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