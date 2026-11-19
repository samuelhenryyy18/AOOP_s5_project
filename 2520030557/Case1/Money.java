
import java.math.BigDecimal;
import java.math.RoundingMode;

public final class Money {

    private final long minor;
    private final Currency currency;

    private Money(long minor, Currency currency) {
        this.minor = minor;
        this.currency = currency;
    }

    // Create money from a decimal string
    public static Money of(String decimal, Currency c) {

        if (decimal == null || c == null) {
            throw new IllegalArgumentException("Invalid money");
        }

        BigDecimal amount = new BigDecimal(decimal);

        if (amount.signum() < 0) {
            throw new IllegalArgumentException(
                "Price cannot be negative"
            );
        }

        if (amount.scale() > c.getMinorUnits()) {
            throw new IllegalArgumentException(
                "Excess decimal precision"
            );
        }

        long minor = amount
            .movePointRight(c.getMinorUnits())
            .longValueExact();

        return new Money(minor, c);
    }

    // Add two money amounts
    public Money plus(Money other) {

        requireSame(other);

        return new Money(
            Math.addExact(minor, other.minor),
            currency
        );
    }

    // Multiply money by quantity
    public Money times(Quantity q) {

        return new Money(
            Math.multiplyExact(minor, q.getValue()),
            currency
        );
    }

    // Apply discount using HALF_EVEN rounding
    public Money minus(Percentage p) {

        BigDecimal result = BigDecimal.valueOf(minor)
            .multiply(
                BigDecimal.valueOf(
                    10000 - p.getBasisPoints()
                )
            )
            .divide(
                BigDecimal.valueOf(10000),
                0,
                RoundingMode.HALF_EVEN
            );

        return new Money(result.longValueExact(), currency);
    }

    // Check whether currencies are the same
    private void requireSame(Money other) {

        if (other == null || currency != other.currency) {
            throw new IllegalArgumentException(
                "Currency mismatch"
            );
        }
    }

    public long getMinor() {
        return minor;
    }

    public Currency getCurrency() {
        return currency;
    }

    @Override
    public String toString() {

        return currency + " " +
            BigDecimal.valueOf(
                minor,
                currency.getMinorUnits()
            ).toPlainString();
    }
}
