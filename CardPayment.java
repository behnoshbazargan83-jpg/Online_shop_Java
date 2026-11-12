package store;

public class CardPayment implements PaymentMethod {

    private String cardNumber;

    public CardPayment(String cardNumber) {
        setCardNumber(cardNumber);
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {

        if (cardNumber == null || !cardNumber.matches("\\d{16}")) {
            throw new IllegalArgumentException(
                    "Card number must be 16 digits"
            );
        }

        this.cardNumber = cardNumber;
    }

    @Override
    public boolean pay(double amount) {

        if (amount <= 0 || !Double.isFinite(amount)) {
            return false;
        }

        System.out.println("Paid " + amount + " by card");
        return true;
    }
}