package parking_lot;

import java.util.*;

public class ParkingFloor {

    private String floor_id;
    private List<ParkingSpot> spots;

    public ParkingFloor(String floor_id, List<ParkingSpot> spots) {
        this.floor_id = floor_id;
        this.spots = spots;
    }

    public List<ParkingSpot> getAllSpots() {
        return this.spots;
    }

    public List<ParkingSpot> getAvailableSpots(VehicleType vehicle_type) {
        List<ParkingSpot> availableSpots = new ArrayList<>();

        for (ParkingSpot spot : spots) {
            if (spot.getAvailability() == true && spot.getVehicleType() == vehicle_type) {
                availableSpots.add(spot);
            }
        }
        return availableSpots;

    }

}
