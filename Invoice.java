package store;

public class Invoice {

    private Order order;

    public Invoice(Order order) {

        if (order == null || order.getStatus() != OrderStatus.PAID) {
            throw new IllegalArgumentException("Order must be paid first!");
        }

        this.order = order;
    }

    public void printInvoice() {

        System.out.println("\n========== INVOICE ==========");
        System.out.println("Order ID: " + order.getOrderId());
        System.out.println("Customer: " + order.getCustomer().getName());
        System.out.println("Status: " + order.getStatus());
        System.out.println("-----------------------------");

        for (OrderItem item : order.getItems()) {

            System.out.println("Product: " + item.getProduct().getTitle());
            System.out.println("Quantity: " + item.getQuantity());
            System.out.println("Unit Price: " + item.getProduct().calculateFinalPrice());
            System.out.println("Subtotal: " + item.getSubtotal());
            System.out.println("-----------------------------");
        }

        System.out.println("Original Total: " + order.calculateTotal());
        System.out.println("Discount: " + (order.calculateTotal() - order.applyDiscount()));
        System.out.println("Final Total: " + order.applyDiscount());

        System.out.println("=============================");
        System.out.println("Thank you for your purchase!");
    }
}