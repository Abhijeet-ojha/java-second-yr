package lab3;

class OrderTask implements Runnable {

    private String orderId;

    OrderTask(String id) {
        this.orderId = id;
    }

    @Override
    public void run() {

        try {

            System.out.println("Order " + orderId + ": Validation started...");
            Thread.sleep(1500);

            System.out.println("Order " + orderId + ": Payment processing...");
            Thread.sleep(2000);

            System.out.println("Order " + orderId + ": Shipment in progress...");
            Thread.sleep(1500);

            System.out.println("Order " + orderId + ": Completed.");

        } catch (InterruptedException e) {
            System.out.println("Order " + orderId + " interrupted.");
        }
    }
}

// Main Class
public class q2{
    public static void main(String[] args) {

        // Create order tasks
        OrderTask o1 = new OrderTask("A101");
        OrderTask o2 = new OrderTask("A102");
        OrderTask o3 = new OrderTask("A103");

        // Create threads
        Thread t1 = new Thread(o1);
        Thread t2 = new Thread(o2);
        Thread t3 = new Thread(o3);

        // Start all threads
        t1.start();
        t2.start();
        t3.start();
    }
}
