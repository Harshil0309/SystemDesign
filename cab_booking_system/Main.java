
public class Main {
    public static void main(String[] args) {
        System.out.println("fghjkertyu");
        DriverRepository driverRepo = new DriverRepository();
        driverRepo.addDriver(new Driver("D1", "Raj"));

        Rider rider = new Rider("R1", "Amit");

        RideService rideService = new RideService(driverRepo);

        Ride ride = rideService.requestRide("Ride1", rider);
        System.out.println(ride.getStatus());
        // ride.cancelRide();

        if (ride.getStatus() != RideStatus.CANCELLED) {
            ride.completeRide();
        }

        System.out.println(ride.getStatus());
    }
}