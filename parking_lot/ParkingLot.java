package parking_lot;

import java.time.LocalDateTime;
import java.util.*;

public class ParkingLot {
    private String lot_id;
    private List<ParkingFloor> floors;

    public ParkingLot(String lot_id, List<ParkingFloor> floors) {
        this.lot_id = lot_id;
        this.floors = floors;
    }

    public ParkingSpot assignSpot(Vehicle vehicle) {

        VehicleType vehicle_type = vehicle.getVehicleType();
        // parking strategy - for now start assigning from bottom
        List<ParkingSpot> availableSpots = new ArrayList<>();
        for (ParkingFloor floor : floors) {
            availableSpots = floor.getAvailableSpots(vehicle_type);
            if (availableSpots.size() != 0){
                return availableSpots.get(0);
            }
        }
        if (availableSpots.size() == 0) {
            System.out.println("No available spot");
        } 
        return null;

    }

    public void checkOut() {

    }

}
