package lab2;

class q1 {

    public static void main(String[] args) {

        // Primitive values
        int days = 3;
        double tariff = 1500.50;
        double service = 500.0;

        // Autoboxing (primitive → wrapper)
        Integer stayDays = days;
        Double roomTariff = tariff;
        Double serviceCharge = service;

        // Unboxing (wrapper → primitive)
        double total =
            (stayDays * roomTariff) + serviceCharge;

        // Display Bill
        System.out.println("Hotel Billing System");
        System.out.println("---------------------");

        System.out.println("Room Tariff per Day : " + roomTariff);
        System.out.println("Days Stayed         : " + stayDays);
        System.out.println("Service Charges     : " + serviceCharge);

        System.out.println("---------------------");
        System.out.println("Total Bill Amount   : " + total);
    }
}
