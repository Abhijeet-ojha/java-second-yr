class Room {

    int roomNo;
    double baseTariff;

    // REQUIRED constructor
    Room(int no, double tariff) {
        roomNo = no;
        baseTariff = tariff;
    }

    double calculateTariff() {
        return baseTariff;
    }
}

// Standard Room
class StandardRoom extends Room {

    StandardRoom(int no, double tariff) {
        super(no, tariff);   // must match Room constructor
    }

    double calculateTariff() {
        return baseTariff + 500;
    }
}

// Luxury Room
class LuxuryRoom extends Room {

    LuxuryRoom(int no, double tariff) {
        super(no, tariff);   // must match Room constructor
    }

    double calculateTariff() {
        return baseTariff + 2000;
    }
}

public class q3 {

    public static void main(String[] args) {

        Room r;

        r = new StandardRoom(101, 2000);
        System.out.println("Standard: " + r.calculateTariff());

        r = new LuxuryRoom(201, 4000);
        System.out.println("Luxury: " + r.calculateTariff());
    }
}
