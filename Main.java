package store;

public class Main {

    public static void main(String[] args) {

        Book booky = new Book("Belladona", 890000, 1, 200,
                "Adlin gris", 2010, 350);

        Book booky2 = new Book("hertless", 780000, 2, 100,
                "Marisa main", 2015, 280);

        Stationery item = new Stationery("blue pen", 130000, 3, 400,
                "penter", "blue", "pen");

        Stationery item2 = new Stationery("colored pencil", 870000, 4, 300,
                "Faber castel", "multicolor", "pencil");

        Product[] products = {booky, booky2, item, item2};

        Customer c1 = new Customer("behnosh", "09201383607", 4056, true);

        ConsoleMenu menu = new ConsoleMenu(products, c1);

        menu.start();

    }

}