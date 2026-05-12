import java.io.FileWriter;
import java.io.IOException;

public class Main {


    public static void main(String[] args) {

        UserRepository userRepository = new UserRepository();
        DriverRepository driverRepository = new DriverRepository();
        RideRepository rideRepository = new RideRepository();

        UserService userService = new UserService(userRepository);
        DriverService driverService = new DriverService(driverRepository);
        RideService rideService =new RideService(rideRepository);

        AppLogger.log("System started");

        User user1=userService.createUser( "Harshil","9999999999");

        AppLogger.log("User created: " +user1.getId() +" "+ user1.getName() +" "+ user1.getContact());


        Vehicle vehicle1 =
                new Vehicle(
                        "V1",
                        "KA01AB1234",
                        VehicleType.FOUR_WHEELER
                );

        Vehicle vehicle2 =
                new Vehicle(
                        "V2",
                        "KA02XY5678",
                        VehicleType.TWO_WHEELER
                );


        Driver d1=driverService.addDriver("Rahul","8888888888",vehicle1);
        Driver d2=driverService.addDriver("Aman","7777777777",vehicle2);

        AppLogger.log("Drivers added"+" " + d1.getName()+" "+d2.getName());


        Ride ride1 =
                rideService.createRideRequest(
                        user1.getId(),
                        "Marathahalli",
                        "Whitefield",
                        VehicleType.FOUR_WHEELER
                );

        Driver availableDriver= driverService.findAvailableDriver();
        AppLogger.log("Available driver is " + availableDriver.getName());
        driverService.updateAvailability(availableDriver.getId(),false);
        

        rideService.assignDriver(ride1.getId(),availableDriver.getId());

        AppLogger.log("Ride created with ID: " + ride1.getId()+" "+ride1.getUserId()+" "+ ride1.getDriverId()+" "+ride1.getStatus());



        rideService.cancelRideRequest(ride1.getId());

        AppLogger.log("Ride cancelled: R1");


        AppLogger.log("System finished");
    }
}