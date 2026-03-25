package practiceques;
abstract class  Product{
    int productid;
    double price;
    ProductCategory prodcat;

    Product(int pid, double p, ProductCategory pp){
        this.productid=pid;
        this.price=p;
        this.prodcat=pp;
    }
    abstract double calculatediscount();
    void displayProductDetails(){
        System.out.println("id:"+productid+" price:"+price+" cat:"+ prodcat);
    }
}
enum ProductCategory{
    Electronics, Clothing
}
interface shippingService{
    void providefreeshipping();
    void provideexpressdelivery();
}
class Electronics extends Product implements shippingService{
    Electronics(int id, double price){
        super(id,price,ProductCategory.Electronics);
    }
    double calculatediscount(){
        return price*0.90;
    }
    public void providefreeshipping(){System.out.println("free shipping is avl");}
    public void provideexpressdelivery(){System.out.println("express delivery is avl");}
}
class Clothing extends Product implements shippingService{
    Clothing(int id, double price){
        super(id,price,ProductCategory.Clothing);
    }
    double calculatediscount(){
        return price*0.85;
    }
    public void providefreeshipping(){System.out.println("free shipping is avl");}
    public void provideexpressdelivery(){System.out.println("express delivery is avl");}
}
class Inventory{
    int size;
    Inventory(int n){this.size=n;}
    synchronized void placeOrder(String s){
        while(size==0){
            try{
                System.err.println(s+"is unavailable waiting for stock!");
                wait();
            }
            catch(InterruptedException e){e.printStackTrace();}
        }
        size--;
        System.out.println("placed order for :"+s+"stocks left: "+size);
    }
    synchronized void cancelOrder(String s){
        size++;
        System.out.println("order cancelled for :"+s);
        notify();
    }
}
class OrderThread extends Thread {
    Inventory inventory;

    OrderThread(Inventory inv, String name) {
        super(name);
        this.inventory = inv;
    }

    public void run() {
        inventory.placeOrder(Thread.currentThread().getName());
    }
}

class CancelThread extends Thread {
    Inventory inventory;

    CancelThread(Inventory inv, String name) {
        super(name);
        this.inventory = inv;
    }

    public void run() {
        inventory.cancelOrder(Thread.currentThread().getName());
    }
}
public class question1 {
public static void main(String[] args) {

        // Polymorphism (base class reference)
        Product p1 = new Electronics(101, 50000);
        Product p2 = new Clothing(102, 2000);

        p1.displayProductDetails();
        System.out.println("Discount: " + p1.calculatediscount());

        p2.displayProductDetails();
        System.out.println("Discount: " + p2.calculatediscount());

        // Interface methods
        ((shippingService) p1).providefreeshipping();
        ((shippingService) p2).provideexpressdelivery();

        // Inventory + Threads
        Inventory inventory = new Inventory(2);

        Thread t1 = new OrderThread(inventory, "Customer-1");
        Thread t2 = new OrderThread(inventory, "Customer-2");
        Thread t3 = new CancelThread(inventory, "Customer-3");

        t1.start();
        t2.start();
        t3.start();
    }
}
