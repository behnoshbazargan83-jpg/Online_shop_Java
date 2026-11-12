package store;

public class PercentDiscount implements DiscountStartegy {

    private double percent;

    public PercentDiscount(double percent) {
        setPercent(percent);
    }

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        if (!Double.isFinite(percent) || percent < 0 || percent > 100) {
            throw new IllegalArgumentException("Percent must be between 0 and 100");
        }

        this.percent = percent;
    }

    @Override
    public double apply(double amount) {
        return amount - (amount * percent / 100);
    }
}
