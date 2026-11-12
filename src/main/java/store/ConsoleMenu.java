package store;

import java.util.Scanner;

public class ConsoleMenu {

    private Scanner scanner;
    private Product[] products;
    private Order order;

    public ConsoleMenu(Product[] products, Customer customer) {

        this.scanner = new Scanner(System.in);
        this.products = products;
        this.order = new Order(1, customer);
        this.order.setDiscountStrategy(new FixedDiscount(100));
    }

    public void start() {

        int choice = -1;

        do {

            System.out.println("\n===== ONLINE STORE =====");
            System.out.println("1. Show Products");
            System.out.println("2. Search Product");
            System.out.println("3. Add Product");
            System.out.println("4. Show Order");
            System.out.println("5. Register Order");
            System.out.println("6. Payment");
            System.out.println("7. Show Invoice");
            System.out.println("8. cancel order");
            System.out.println("0. Exit");

            System.out.print("Choose: ");

            try {

                choice = readInt("Choose: ");

                switch (choice) {

                    case 1:
                        showProducts();
                        break;

                    case 2:
                        System.out.print("Product name: ");
                        String name = scanner.nextLine();
                        searchProduct(name);
                        break;

                    case 3:
                        addProduct();
                        break;

                    case 4:
                        order.displayInformation();
                        break;

                    case 5:
                        order.registerOrder();
                        break;

                    case 6:
                        makePayment();
                        break;

                    case 7:
                        Invoice invoice = new Invoice(order);
                        invoice.printInvoice();
                        break;

                    case 8:
                        order.cancelOrder();
                        break;

                    case 0:
                        System.out.println("Goodbye!");
                        break;


                    default:
                        System.out.println("Invalid choice!");
                }

            } catch (NumberFormatException e) {

                System.out.println("Invalid number!");
                choice = -1;

            } catch (IllegalArgumentException e) {

                System.out.println("Error: " + e.getMessage());
                choice = -1;
            }

        } while (choice != 0);
    }

    private void showProducts() {

        for (Product p : products) {
            p.displayInformation();
            System.out.println("--------------------");
        }
    }

    private void searchProduct(String name) {

        boolean found = false;

        for (Product p : products) {

            if (p.getTitle().equalsIgnoreCase(name)) {

                p.displayInformation();
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Product not found!");
        }
    }

    private void addProduct() {

        if (order.isRegistered()) {
            System.out.println("Order already registered!");
            return;
        }

        for (int i = 0; i < products.length; i++) {

            System.out.println((i + 1) + ". "
                    + products[i].getTitle()
                    + " (Stock: " + products[i].getStock() + ")");
        }


        int index = readInt("Product number");

        if (index < 1 || index > products.length) {
            System.out.println("Invalid product!");
            return;
        }


        int quantity = readInt("Quantity: ");

        order.addItem(products[index - 1], quantity);
    }

    private void makePayment() {
        if (!order.isRegistered()) {
            System.out.println("Please register your order first.");
            return;
        }

        if (order.getStatus() == OrderStatus.CANCELLED) {
            System.out.println(" Cancelled order cannot be paid");
            return;
        }
        if (order.getStatus() == OrderStatus.PAID) {
            System.out.println("Order already paid!");
            return;
        }

        System.out.println("1. Cash Payment");
        System.out.println("2. Card Payment");

        System.out.print("Choose payment method: ");

        int method = Integer.parseInt(scanner.nextLine());

        PaymentMethod payment;

        if (method == 1) {

            System.out.print("Enter cash amount: ");

            double cash = readDouble("Enter cash amount: ");

            payment = new CashPayment(cash);

        } else if (method == 2) {

            System.out.print("Enter card number: ");

            String card = scanner.nextLine();

            payment = new CardPayment(card);

        } else {

            System.out.println("Invalid payment method!");
            return;
        }

        order.pay(payment);
    }

    private int readInt(String message) {
        while (true) {
            try {
                System.out.print(message);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number!");
            }
        }
    }

    private double readDouble(String message) {
        while (true) {
            try {
                System.out.print(message);
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid amount!");
            }
        }
    }
}
