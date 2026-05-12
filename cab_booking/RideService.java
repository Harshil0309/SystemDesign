import java.util.UUID;
public class RideService{
    private RideRepository rideRepository;

    public RideService(RideRepository rideRepository){
        this.rideRepository=rideRepository;
    }

    public Ride createRideRequest(String userId,String pickUpLocation,String dropLocation,VehicleType vehicleRequested){
        String rideId= UUID.randomUUID().toString();
        Ride ride=new Ride(rideId, userId,pickUpLocation,dropLocation, vehicleRequested);
        this.rideRepository.save(ride);
        return ride;

    }
    public void cancelRideRequest(String rideId){
        Ride ride= this.rideRepository.getRide(rideId);
        if(ride == null) {
            System.out.println("Ride not found1");
            return;
        }
        ride.setStatus(RideStatus.CANCELLED);
        this.rideRepository.save(ride);
        return;
    } 

    public void startRide(String rideId){
        Ride ride= this.rideRepository.getRide(rideId);
        if(ride == null) {
            System.out.println("Ride not found2");
            return;
        }
        ride.setStatus(RideStatus.STARTED);
        this.rideRepository.save(ride);
        return;
    }
    public void completeRide(String rideId){
        Ride ride= this.rideRepository.getRide(rideId);
        if(ride == null) {
            System.out.println("Ride not found3");
            return;
        }
        ride.setStatus(RideStatus.FINISHED);
        this.rideRepository.save(ride);
        return;
    }

    public void assignDriver(String rideId,String driverId){
        Ride ride= this.rideRepository.getRide(rideId);
        if(ride == null) {
            System.out.println("Ride not found4");
            return;
        }
        ride.setDriverId(driverId);
        ride.setStatus(RideStatus.ACCEPTED);
        this.rideRepository.save(ride);
        return;
    }
}