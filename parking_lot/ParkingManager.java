package parking_lot;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ParkingManager {

    public Ticket assignSpot(Vehicle vehicle) {

        VehicleType vehicle_type = vehicle.getVehicleType();
        // parking strategy - for now start assigning from bottom
        List<ParkingSpot> availableSpots = new ArrayList<>();
        for (ParkingFloor floor : floors) {
            availableSpots = floor.getAvailableSpots(vehicle_type);
            if (availableSpots.size() != 0)
                break;

        }
        if (availableSpots.size() == 0) {
            System.out.println("No available spot");
        } else {
            ParkingSpot first_available = availableSpots.get(0);
            Ticket tkt = new Ticket(lot_id, vehicle, first_available, LocalDateTime.now());
            return tkt;
        }

    }
}
