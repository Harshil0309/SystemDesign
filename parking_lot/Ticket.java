package parking_lot;

import java.time.*;

public class Ticket {

    private String ticket_id;
    private Vehicle vehicle;
    private ParkingSpot spot;
    private LocalDateTime time;

    public Ticket(String ticket_id, Vehicle vehicle, ParkingSpot spot, LocalDateTime time) {
        this.ticket_id = ticket_id;
        this.vehicle = vehicle;
        this.spot = spot;
        this.time = LocalDateTime.now();
    }

    public Duration getDuration() {
        return Duration.between(LocalDateTime.now(), this.time);
    }

    public ParkingSpot getSpot() {
        return this.spot;
    }

}
