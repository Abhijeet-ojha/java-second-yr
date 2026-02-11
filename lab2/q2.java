package lab2;
enum RoomType {

    STANDARD(1500),
    DELUXE(3000),
    SUITE(5000);

    // Data member
    private double baseTariff;

    // Enum Constructor
    RoomType(double tariff) {
        this.baseTariff = tariff;
    }

    // Method to get base tariff
    public double getBaseTariff() {
        return baseTariff;
    }

    // Method to calculate total cost
    public double calculateCost(int days) {
        return baseTariff * days;
    }
}

// Main Class
public class q2{
    public static void main(String[] args) {

        RoomType room = RoomType.DELUXE; // Select room type
        int days = 4;                   // Number of days

        System.out.println("Room Type : " + room);
        System.out.println("Base Tariff per Day : " + room.getBaseTariff());
        System.out.println("Days Stayed : " + days);

        double total = room.calculateCost(days);

        System.out.println("Total Room Cost : " + total);
    }
}
