import parkingspot.ParkingSpot;
import parkingspot.ParkingSpotFactory;
import parkingspot.ParkingSpotType;
import vehicle.Bike;
import vehicle.Car;
import vehicle.Truck;
import vehicle.Vehicle;

import java.util.List;

//Demo for the parking lot
public class Main {
    public static void main(String[] args) {
        //Instance of Parking Lot
        ParkingLot parkingLot = new ParkingLot();

        //Creating ParkingSpots  for each Floor
        List<ParkingSpot> parkingSpotsFloor1 = List.of(
                ParkingSpotFactory.createParkingSpot(ParkingSpotType.BIKE,"101"),
                ParkingSpotFactory.createParkingSpot(ParkingSpotType.COMPACT,"102"),
                ParkingSpotFactory.createParkingSpot(ParkingSpotType.LARGE,"103")
        );

        List<ParkingSpot> parkingSpotsFloor2 = List.of(
                ParkingSpotFactory.createParkingSpot(ParkingSpotType.COMPACT,"201"),
                ParkingSpotFactory.createParkingSpot(ParkingSpotType.LARGE,"202")
        );

        //Creating floors and adding the floors to the Parking lot
        ParkingFloor floor1 = new ParkingFloor(1, parkingSpotsFloor1);
        ParkingFloor floor2 = new ParkingFloor(2, parkingSpotsFloor2);
        parkingLot.addFloor(floor1);
        parkingLot.addFloor(floor2);

        //Creating Vehicle Instances
        Vehicle car = new Car("BYZ 122");
        Vehicle truck = new Truck("APP 578");
        Vehicle bike = new Bike("BEE 689");

        //Parking and generating Tickets
        try {
            ParkingTicket parkingTicket1 = parkingLot.parkVehicle(car);
            System.out.println("Car  parked, Ticket Id: " + parkingTicket1.getTicketId());

            ParkingTicket parkingTicket2 = parkingLot.parkVehicle(bike);
            System.out.println("Bike parked, Ticket Id: " + parkingTicket2.getTicketId());

            ParkingTicket parkingTicket3 = parkingLot.parkVehicle(truck);
            System.out.println("truck parked, Ticket Id: " + parkingTicket3.getTicketId());

        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }

        //Unparking and fee calculation
        try{
            double fee = parkingLot.unparkVehicle(car.getLicensePlate());
            System.out.println("Vehicle: " + car.getLicensePlate() +", Fee:" + fee);
        } catch (Exception e) {
            System.out.println("Exception while unparking: " + e.getMessage());
        }
    }
}