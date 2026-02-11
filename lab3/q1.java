package lab3;

class ServiceTask implements Runnable {

    private String serviceName;

    ServiceTask(String name) {
        this.serviceName = name;
    }

    @Override
    public void run() {

        try {

            System.out.println(serviceName + " started...");

            // Simulate processing time
            Thread.sleep(2000);

            System.out.println(serviceName + " in progress...");

            Thread.sleep(2000);

            System.out.println(serviceName + " completed.");

        } catch (InterruptedException e) {
            System.out.println(serviceName + " interrupted.");
        }
    }
}

// Main Class
public class q1{

    public static void main(String[] args) {

        // Create tasks
        ServiceTask cleaning = new ServiceTask("Room Cleaning");
        ServiceTask food = new ServiceTask("Food Delivery");
        ServiceTask maintenance = new ServiceTask("Maintenance");

        // Create threads
        Thread t1 = new Thread(cleaning);
        Thread t2 = new Thread(food);
        Thread t3 = new Thread(maintenance);

        // Start threads (run concurrently)
        t1.start();
        t2.start();
        t3.start();
    }
}
