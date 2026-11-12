package store;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private int orderId;
    private Customer customer;
    private List<OrderItem> items;
    private OrderStatus status;
    private boolean registered = false;
    private DiscountStartegy discountStartegy;

    public Order(int orderId, Customer customer) {
        this.orderId = orderId;
        this.customer = customer;
        this.items = new ArrayList<>();
        this.status = OrderStatus.PENDING;
    }

    public int getOrderId() {
        return orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void addItem(Product product, int quantity) {

        if (registered || status != OrderStatus.PENDING) {
            System.out.println("Order cannot be modified.");
            return;
        }

        if (product == null || quantity <= 0) {
            System.out.println("Invalid product or quantity.");
            return;
        }

        if (product.getStock() < quantity) {
            System.out.println("Not enough stock.");
            return;
        }

        for (OrderItem item : items) {
            if (item.getProduct() == product) {
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
        }

        items.add(new OrderItem(product, quantity));
    }

    public double calculateTotal() {
        double total = 0;

        for (OrderItem item : items) {
            total += item.getSubtotal();
        }

        return total;
    }

    public double applyDiscount() {
        if (discountStartegy == null) {
            return calculateTotal();
        }

        return discountStartegy.apply(calculateTotal());
    }

    public void setDiscountStrategy(DiscountStartegy discountStrategy) {
        this.discountStartegy = discountStrategy;
    }

    public void registerOrder() {
        if (items.isEmpty()) {
            System.out.println("Order is empty.");
            return;
        }

        registered = true;
        System.out.println("Order registered successfully.");
    }

    public boolean isRegistered() {
        return registered;
    }

    public void pay(PaymentMethod paymentMethod) {

        if (status != OrderStatus.PENDING) {
            System.out.println("Order cannot be paid.");
            return;
        }

        if (!registered || items.isEmpty()) {
            System.out.println("Please register your order first.");
            return;
        }

        if (paymentMethod == null) {
            System.out.println("Invalid payment method.");
            return;
        }

        for (OrderItem item : items) {
            if (item.getProduct().getStock() < item.getQuantity()) {
                System.out.println("Not enough stock.");
                return;
            }
        }

        double total = applyDiscount();

        if (paymentMethod.pay(total)) {

            for (OrderItem item : items) {
                Product product = item.getProduct();

                product.setStock(
                        product.getStock() - item.getQuantity()
                );
            }

            status = OrderStatus.PAID;
            System.out.println("Payment successful.");

        } else {
            System.out.println("Payment failed.");
        }
    }

    public void cancelOrder() {

        if (status == OrderStatus.CANCELLED) {
            System.out.println("Order is already cancelled.");
            return;
        }

        if (status == OrderStatus.PAID) {
            System.out.println("Paid orders cannot be cancelled.");
            return;
        }

        status = OrderStatus.CANCELLED;
        System.out.println("Order cancelled successfully.");
    }

    public void displayInformation() {

        System.out.println("Order ID: " + orderId);
        System.out.println("Customer: " + customer.getName());
        System.out.println("Status: " + status);

        for (OrderItem item : items) {
            System.out.println(
                    item.getProduct().getTitle()
                            + " | Quantity: " + item.getQuantity()
                            + " | Subtotal: " + item.getSubtotal()
            );
        }

        System.out.println("Original Total: " + calculateTotal());
        System.out.println("Final Total: " + applyDiscount());
    }
}
