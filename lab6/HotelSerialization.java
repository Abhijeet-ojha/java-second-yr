import java.io.*;
import java.util.*;

public class HotelSerialization {

    static final String FILE_NAME = "rooms.ser";

    // Write all rooms to file
    public static void saveRooms(ArrayList<Room> rooms) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME));
        oos.writeObject(rooms);
        oos.close();
    }

    // Read all rooms from file
    public static ArrayList<Room> loadRooms() throws IOException, ClassNotFoundException {
        File file = new File(FILE_NAME);
        if (!file.exists()) return new ArrayList<>();

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME));
        ArrayList<Room> rooms = (ArrayList<Room>) ois.readObject();
        ois.close();
        return rooms;
    }

    // Add new room
    public static void addRoom(Room r) {
        try {
            ArrayList<Room> rooms = loadRooms();
            rooms.add(r);
            saveRooms(rooms);
            System.out.println("Room added!");
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    // Display all rooms
    public static void displayAll() {
        try {
            ArrayList<Room> rooms = loadRooms();
            if (rooms.isEmpty()) {
                System.out.println("No records found.");
                return;
            }
            for (Room r : rooms) r.display();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    // Search room by number
    public static void searchRoom(int roomNo) {
        try {
            ArrayList<Room> rooms = loadRooms();
            for (Room r : rooms) {
                if (r.roomNumber == roomNo) {
                    r.display();
                    return;
                }
            }
            System.out.println("Room not found!");
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    // Update booking status
    public static void updateStatus(int roomNo, boolean status, String guest) {
        try {
            ArrayList<Room> rooms = loadRooms();
            boolean found = false;

            for (Room r : rooms) {
                if (r.roomNumber == roomNo) {
                    r.isBooked = status;
                    r.guestName = guest;
                    found = true;
                    break;
                }
            }

            if (found) {
                saveRooms(rooms);
                System.out.println("Room updated!");
            } else {
                System.out.println("Room not found!");
            }

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    // Main menu
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Add Room\n2. Display All\n3. Search Room\n4. Update Booking\n5. Exit");
            int ch = sc.nextInt();

            switch (ch) {
                case 1:
                    System.out.print("Room No: ");
                    int rn = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Type: ");
                    String type = sc.nextLine();
                    System.out.print("Price: ");
                    double price = sc.nextDouble();
                    sc.nextLine();
                    System.out.print("Booked (true/false): ");
                    boolean status = sc.nextBoolean();
                    sc.nextLine();
                    System.out.print("Guest Name: ");
                    String guest = sc.nextLine();

                    addRoom(new Room(rn, type, price, status, guest));
                    break;

                case 2:
                    displayAll();
                    break;

                case 3:
                    System.out.print("Enter Room No: ");
                    searchRoom(sc.nextInt());
                    break;

                case 4:
                    System.out.print("Room No: ");
                    int r = sc.nextInt();
                    System.out.print("New Status (true/false): ");
                    boolean s = sc.nextBoolean();
                    sc.nextLine();
                    System.out.print("Guest Name: ");
                    String g = sc.nextLine();
                    updateStatus(r, s, g);
                    break;

                case 5:
                    System.exit(0);
            }
        }
    }
}