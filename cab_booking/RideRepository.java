import java.util.*;

public class RideRepository{
    private Map<String,Ride> rideStore=new HashMap<>();

    public void save(Ride ride){
        this.rideStore.put(ride.getId(),ride);
    }

    public Ride getRide(String rideId){
        return this.rideStore.get(rideId);
    }

}