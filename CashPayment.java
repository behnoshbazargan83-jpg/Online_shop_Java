package store;

public class CashPayment implements PaymentMethod {

    private double cashAmount;

    public CashPayment(double cashAmount) {
        setCashAmount(cashAmount);
    }

    public double getCashAmount() {
        return cashAmount;
    }

    public void setCashAmount(double cashAmount) {

        if (!Double.isFinite(cashAmount) || cashAmount < 0) {
            throw new IllegalArgumentException(
                    "Cash amount must be valid and non-negative"
            );
        }

        this.cashAmount = cashAmount;
    }

    @Override
    public boolean pay(double amount) {

        if (!Double.isFinite(amount) || amount <= 0) {
            System.out.println("Invalid payment amount");
            return false;
        }

        if (cashAmount < amount) {
            System.out.println("Cash payment failed: not enough cash");
            return false;
        }

        System.out.println("Paid " + amount + " in cash");

        double change = cashAmount - amount;

        if (change > 0) {
            System.out.println("Change: " + change);
        }

        return true;
    }
}