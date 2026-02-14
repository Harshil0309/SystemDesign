import java.util.*;

public class RideService {
    private DriverRepository driverRepo;

    public RideService(DriverRepository driverRepo) {
        this.driverRepo = driverRepo;
    }

    public Ride requestRide(String rideId, Rider rider) {
        Ride ride = new Ride(rideId, rider);
        List<Driver> list = driverRepo.getAvailableDrivers();
        if (list.isEmpty())
            throw new RuntimeException("No available drivers");

        ride.assignDriver(list.get(0));

        return ride;
    }
}
