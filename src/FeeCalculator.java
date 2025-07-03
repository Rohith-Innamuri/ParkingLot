public class FeeCalculator {
    public static final double RATE_PER_HOUR = 10.0;

    public double calculateFee(ParkingTicket parkingTicket){
        long duration = parkingTicket.getExitTime() - parkingTicket.getEntryTime();
        long hours = (duration/(1000 * 60 * 60))+1;
        return hours * RATE_PER_HOUR;
    }
}
