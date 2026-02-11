package lab4;

class Hotel {

    private int availableRooms;

    Hotel(int rooms) {
        availableRooms = rooms;
    }

    // Book Room
    public synchronized void bookRoom(String name) {

        while (availableRooms == 0) {

            try {
                System.out.println(name + " waiting for room...");
                wait(); // wait if no room
            } catch (InterruptedException e) {}
        }

        availableRooms--;
        System.out.println(name + " booked a room. Rooms left: " 
                           + availableRooms);
    }

    // Release Room
    public synchronized void releaseRoom(String name) {

        availableRooms++;

        System.out.println(name + " released a room. Rooms left: " 
                           + availableRooms);

        notifyAll(); // wake waiting threads
    }
}

// Customer Thread
class Customer extends Thread {

    private Hotel hotel;
    private String name;

    Customer(Hotel h, String n) {
        hotel = h;
        name = n;
    }

    public void run() {

        hotel.bookRoom(name);

        try {
            Thread.sleep(2000); // stay time
        } catch (Exception e) {}

        hotel.releaseRoom(name);
    }
}

// Main Class
public class q1 {

    public static void main(String[] args) {

        Hotel hotel = new Hotel(2); // Only 2 rooms

        new Customer(hotel, "Customer A").start();
        new Customer(hotel, "Customer B").start();
        new Customer(hotel, "Customer C").start();
        new Customer(hotel, "Customer D").start();
    }
}
