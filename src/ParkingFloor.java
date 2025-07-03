import parkingspot.ParkingSpot;
import vehicle.Vehicle;

import java.util.List;
import java.util.Optional;

public class ParkingFloor {
    private final int floorNumber;
    private final List<ParkingSpot> parkingSpots;

    public ParkingFloor(int floorNumber, List<ParkingSpot> spots){
        this.floorNumber = floorNumber;
        this.parkingSpots = spots;
    }
    //Find first available spot which can fit the vehicle type
    public synchronized Optional<ParkingSpot> getAvailableSpots(Vehicle vehicle){
        return parkingSpots.stream()
                .filter(spot->spot.isAvailable() && spot.canFitVehicle(vehicle))
                .findFirst();
    }

    //getter for floor number
    public int getFloorNumber(){return  floorNumber;}
}
