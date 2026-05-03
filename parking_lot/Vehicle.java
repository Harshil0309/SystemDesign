package parking_lot;

public class Vehicle {
    private String vehicle_number;
    private VehicleType vehicle_type;

    public Vehicle(String vehicle_number, VehicleType vehicle_type) {
        this.vehicle_number = vehicle_number;
        this.vehicle_type = vehicle_type;
    }

    public VehicleType getVehicleType() {
        return this.vehicle_type;
    }

}
