import parkingspot.ParkingSpot;
import vehicle.Vehicle;

import java.util.Date;
import java.util.UUID;

public class ParkingTicket {
    private final String TicketId;
    private final Vehicle vehicle;
    private final ParkingSpot spot;
    private final long entryTime;
    private long exitTime;

    public ParkingTicket(Vehicle vehicle, ParkingSpot spot){
        this.TicketId = UUID.randomUUID().toString();
        this.vehicle = vehicle;
        this.spot = spot;
        this.entryTime = new Date().getTime();
    }
    //setter for exitTime
    public void setExitTime(){
        this.exitTime = new Date().getTime();
    }

    //getters


    public String getTicketId() {
        return TicketId;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public ParkingSpot getSpot() {
        return spot;
    }

    public long getEntryTime() {
        return entryTime;
    }

    public long getExitTime() {
        return exitTime;
    }
}
