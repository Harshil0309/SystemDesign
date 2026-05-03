package parking_lot;

public class ParkingSpot {
    private String id; // id-> floor+'_'+number
    private VehicleType vehicle_type;
    private Boolean isAvailable;
    private Vehicle parkedVehicle;

    public ParkingSpot(String id, VehicleType vehicleType) {
        this.id = id;
        this.vehicle_type = vehicleType;
        this.isAvailable = true;
        this.parkedVehicle = null;
    }

    public void markAvailable() {
        this.parkedVehicle = null;
        this.isAvailable = true;
    }

    public void markUnavailable(Vehicle vehicle) {
        this.parkedVehicle = vehicle;
        this.isAvailable = false;
    }

    public VehicleType getVehicleType() {
        return this.vehicle_type;
    }

    public Boolean getAvailability() {
        return this.isAvailable;
    }

    public String getId() {
        return this.id;
    }

    public Vehicle getVehicle() {
        return this.parkedVehicle;
    }

}
