package lab7;

public class Main {
    public static void main(String[] args) {

        // Calling generic method with different data types
        Hotel.display(101);          // Integer (Room Number)
        Hotel.display("Deluxe");     // String (Room Type)
        Hotel.display(2500.75);      // Double (Price)
        Hotel.display(true);         // Boolean (Booking Status)
    }
}
