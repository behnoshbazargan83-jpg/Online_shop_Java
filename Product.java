package store;

public abstract class Product {

    private String title;
    private double price;
    private int id;
    private int stock;

    public Product(String title, double price, int id, int stock) {
        setTitle(title);
        setPrice(price);
        setId(id);
        setStock(stock);
    }

    public abstract double calculateFinalPrice();

    public void setTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }
        this.title = title;
    }

    public void setPrice(double price) {
        if (!Double.isFinite(price) || price <= 0) {
            throw new IllegalArgumentException("Price must be positive");
        }
        this.price = price;
    }

    public void setId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID must be positive");
        }
        this.id = id;
    }

    public void setStock(int stock) {
        if (stock < 0) {
            throw new IllegalArgumentException("Stock cannot be negative");
        }
        this.stock = stock;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    public int getId() {
        return id;
    }

    public int getStock() {
        return stock;
    }
    public void reduceStock(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }

        if (quantity > stock) {
            throw new IllegalArgumentException("Not enough stock available");
        }

        stock -= quantity;
    }

    public void increaseStock(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }

        stock += quantity;
    }

    public void displayInformation() {
        System.out.println("Title: " + title);
        System.out.println("Price: " + price);
        System.out.println("ID: " + id);
        System.out.println("Stock: " + stock);
    }
}