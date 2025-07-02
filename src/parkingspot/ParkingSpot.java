package parkingspot;

import vehicle.Vehicle;

public abstract class ParkingSpot {
private final String spotId;
private boolean isOccupied;
private Vehicle vehicle;

    public ParkingSpot(String spotId) {
        this.spotId = spotId;
        this.isOccupied = false;
    }
    //check Spot availability
    public synchronized boolean isAvailable(){
        return !isOccupied;
    }
    //assigning a vehicle to spot
    public synchronized boolean assignVehicle(Vehicle vehicle){
        if(isOccupied) return false;
        isOccupied = true;
        this.vehicle = vehicle;
        return true;
    }

    //removing vehicle from spot
    public synchronized  void removeVehicle(){
        vehicle = null;
        isOccupied = false;
    }

    //getters for vehicle and  spotId
    public String getSpotId() {
        return spotId;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    //Check for correct spot
    public abstract boolean canFitVehicle(Vehicle vehicle);
}
