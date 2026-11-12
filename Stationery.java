package store;

public class Stationery extends Product {

    private String brand;
    private String color;
    private String type;

    public Stationery(String title, double price, int productId,
                      int stock, String brand, String color, String type) {

        super(title, price, productId, stock);

        setBrand(brand);
        setColor(color);
        setType(type);
    }

    public String getBrand() {
        return brand;
    }

    public String getColor() {
        return color;
    }

    public String getType() {
        return type;
    }

    public void setBrand(String brand) {
        if (brand == null || brand.trim().isEmpty()) {
            throw new IllegalArgumentException("Brand cannot be empty");
        }
        this.brand = brand;
    }

    public void setColor(String color) {
        if (color == null || color.trim().isEmpty()) {
            throw new IllegalArgumentException("Color cannot be empty");
        }
        this.color = color;
    }

    public void setType(String type) {
        if (type == null || type.trim().isEmpty()) {
            throw new IllegalArgumentException("Type cannot be empty");
        }
        this.type = type;
    }

    @Override
    public double calculateFinalPrice() {
        return getPrice() * 1.1;
    }

    @Override
    public void displayInformation() {
        super.displayInformation();

        System.out.println("Brand: " + brand);
        System.out.println("Color: " + color);
        System.out.println("Type: " + type);
        System.out.println("Final Price: " + calculateFinalPrice());
    }
}