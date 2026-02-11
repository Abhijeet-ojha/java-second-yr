class Book {

    private int id;
    private String title;
    private String author;
    private double price;
    private boolean available;

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPrice(double price) {
        if (price > 0)
            this.price = price;
        else
            System.out.println("Invalid Price!");
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    // Getters
    public int getId() { return id; }

    public String getTitle() { return title; }

    public String getAuthor() { return author; }

    public double getPrice() { return price; }

    public boolean isAvailable() { return available; }
}

// Main Class
class Main {

    public static void main(String[] args) {

        Book b = new Book();

        b.setId(101);
        b.setTitle("Java Programming");
        b.setAuthor("James");

        b.setPrice(500);     // Valid
        // b.setPrice(-100); // Invalid (try this)

        b.setAvailable(true);

        // Display Details
        System.out.println("Book ID: " + b.getId());
        System.out.println("Title: " + b.getTitle());
        System.out.println("Author: " + b.getAuthor());
        System.out.println("Price: " + b.getPrice());
        System.out.println("Available: " + b.isAvailable());
    }
}
