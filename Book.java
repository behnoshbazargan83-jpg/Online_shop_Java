package store;

public class Book extends Product {

    private String author;
    private int publicationYear;
    private int bookPage;

    public Book(String title, double price, int productId, int stock,
                String author, int publicationYear, int bookPage) {

        super(title, price, productId, stock);

        setAuthor(author);
        setPublicationYear(publicationYear);
        setBookPage(bookPage);
    }

    public String getAuthor() {
        return author;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public int getBookPage() {
        return bookPage;
    }

    public void setAuthor(String author) {
        if (author == null || author.trim().isEmpty()) {
            throw new IllegalArgumentException("Author cannot be empty");
        }
        this.author = author;
    }

    public void setPublicationYear(int publicationYear) {
        if (publicationYear <= 0 || publicationYear > 2026) {
            throw new IllegalArgumentException("Invalid publication year");
        }
        this.publicationYear = publicationYear;
    }

    public void setBookPage(int bookPage) {
        if (bookPage <= 0) {
            throw new IllegalArgumentException("Book pages must be positive");
        }
        this.bookPage = bookPage;
    }

    @Override
    public double calculateFinalPrice() {
        return getPrice() * 1.1;
    }

    @Override
    public void displayInformation() {
        super.displayInformation();

        System.out.println("Author: " + author);
        System.out.println("Publication Year: " + publicationYear);
        System.out.println("Book Pages: " + bookPage);
        System.out.println("Final Price: " + calculateFinalPrice());
    }
}