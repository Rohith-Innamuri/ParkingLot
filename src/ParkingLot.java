import parkingspot.ParkingSpot;
import vehicle.Vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class ParkingLot {
    private final List<ParkingFloor> floors = new ArrayList<>();
    private FeeCalculator feeCalculator;
    private final Map<String, ParkingTicket> activeTickets = new ConcurrentHashMap<>();

    public ParkingLot(){
        feeCalculator = new FeeCalculator();
    }

    //To add New Floor
    public void addFloor(ParkingFloor floor){
        floors.add(floor);
    }
    //check available spot for the vehicle and assign then generate the parkingTicket
    public synchronized ParkingTicket parkVehicle(Vehicle vehicle) throws Exception{
        for(ParkingFloor floor : floors){
            Optional<ParkingSpot> spotOpt = floor.getAvailableSpots(vehicle);
            if(spotOpt.isPresent()){
                ParkingSpot spot = spotOpt.get();
                if(spot.assignVehicle(vehicle)){
                    ParkingTicket ticket = new ParkingTicket(vehicle, spot);
                    activeTickets.put(vehicle.getLicensePlate(), ticket);
                    return ticket;
                }
            }
        }
        throw new Exception("No available spot for" + vehicle.getType());
    }

    //get the ticket, remove vehicle from spot, set Exit time and then calculate the fee
    public synchronized double unparkVehicle(String license) throws Exception {
        ParkingTicket ticket = activeTickets.remove(license);
        if(ticket == null) throw new Exception("Parking ticket not found");

        ticket.getSpot().removeVehicle();
        ticket.setExitTime();
        return feeCalculator.calculateFee(ticket);
    }
}
