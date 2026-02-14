

public class Ride {
    private String rideId;
    private Rider rider;
    private Driver driver;
    private RideStatus status;

    public Ride(String rideId, Rider rider) {
        this.rideId = rideId;
        this.rider = rider;
    }

    public void assignDriver(Driver driver) {
        this.driver = driver;
        this.status = RideStatus.ACCEPTED;
        driver.markUnavailable();
    }

    public void cancelRide() {
        this.status = RideStatus.CANCELLED;
        this.driver.markAvailable();
    }

    public void completeRide() {
        this.status = RideStatus.COMPLETED;
        this.driver.markAvailable();
    }

    public RideStatus getStatus() {
        return this.status;
    }
}
