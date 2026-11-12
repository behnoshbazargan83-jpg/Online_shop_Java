package store;

public class FixedDiscount implements DiscountStartegy {

    private double discountAmount;

    public FixedDiscount(double discountAmount) {
        setDiscountAmount(discountAmount);
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(double discountAmount) {
        if (!Double.isFinite(discountAmount) || discountAmount < 0) {
            throw new IllegalArgumentException("Discount cannot be negative");
        }

        this.discountAmount = discountAmount;
    }

    @Override
    public double apply(double amount) {
        double result = amount - discountAmount;

        if (result < 0) {
            return 0;
        }

        return result;
    }
}