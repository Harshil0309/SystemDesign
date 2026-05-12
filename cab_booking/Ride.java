public class Ride{
    private String id;
    private String userId;
    private String driverId;
    private RideStatus status;
    private String pickUpLocation;
    private String dropLocation;
    private VehicleType vehicleRequested;

    public Ride(String id, String userId,String pickUpLocation,String dropLocation,VehicleType vehicleRequested){
        this.id=id;
        this.userId=userId;
        this.status=RideStatus.SEARCHING;
        this.pickUpLocation=pickUpLocation;
        this.dropLocation=dropLocation;
        this.vehicleRequested=vehicleRequested;
    }

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getDriverId() {
        return driverId;
    }

    public RideStatus getStatus() {
        return status;
    }

    public String getPickUpLocation() {
        return pickUpLocation;
    }

    public String getDropLocation() {
        return dropLocation;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public void setStatus(RideStatus status) {
        this.status = status;
    }

    public void setPickUpLocation(String pickUpLocation) {
        this.pickUpLocation = pickUpLocation;
    }

    public void setDropLocation(String dropLocation) {
        this.dropLocation = dropLocation;
    }
}