interface Amenities {

    void provideWifi();
    void provideBreakfast();
}

abstract class Room {

    int roomNo;
    double basePrice;

    Room(int no, double price) {
        roomNo = no;
        basePrice = price;
    }

    abstract double calculateTariff();

    void display() {
        System.out.println(roomNo + " " + basePrice);
    }
}

class StandardRoom extends Room implements Amenities {

    StandardRoom(int no, double price) {
        super(no, price);
    }

    double calculateTariff() {
        return basePrice + 500;
    }

    public void provideWifi() {
        System.out.println("Standard Wifi");
    }

    public void provideBreakfast() {
        System.out.println("Standard Breakfast");
    }
}

class LuxuryRoom extends Room implements Amenities {

    LuxuryRoom(int no, double price) {
        super(no, price);
    }

    double calculateTariff() {
        return basePrice + 2000;
    }

    public void provideWifi() {
        System.out.println("High-Speed Wifi");
    }

    public void provideBreakfast() {
        System.out.println("Premium Breakfast");
    }
}

class q4 {
    public static void main(String[] args) {

        Room r1 = new StandardRoom(101, 2000);
        Room r2 = new LuxuryRoom(201, 4000);

        r1.display();
        System.out.println(r1.calculateTariff());

        r2.display();
        System.out.println(r2.calculateTariff());

        Amenities a = new StandardRoom(102, 1800);
        a.provideWifi();
        a.provideBreakfast();
    }
}
