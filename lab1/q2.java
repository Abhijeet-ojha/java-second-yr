class Room {

    int roomNo;
    String type;
    double price;

    // Constructor 1
    Room(int roomNo, String type) {
        this.roomNo = roomNo;
        this.type = type;
    }

    // Constructor 2
    Room(int roomNo, String type, double price) {
        this.roomNo = roomNo;
        this.type = type;
        this.price = price;
    }

    void display() {
        System.out.println(roomNo + " " + type + " " + price);
    }
}

class DeluxeRoom extends Room {

    boolean wifi, breakfast;

    DeluxeRoom(int no, String type, double price,
               boolean wifi, boolean breakfast) {

        super(no, type, price);

        this.wifi = wifi;
        this.breakfast = breakfast;
    }

    void display() {
        super.display();
        System.out.println("Wifi: " + wifi + " Breakfast: " + breakfast);
    }
}

class q2{
    public static void main(String[] args) {

        Room r1 = new Room(101, "Standard");
        Room r2 = new Room(102, "AC", 2000);

        DeluxeRoom d =
            new DeluxeRoom(201, "Deluxe", 4000, true, true);

        r1.display();
        r2.display();
        d.display();
    }
}
