
public final class Quantity {

    private final int value;

    private Quantity(int value) {
        this.value = value;
    }

    public static Quantity of(int v) {

        if (v < 1) {
            throw new IllegalArgumentException(
                "Quantity must be at least 1"
            );
        }

        return new Quantity(v);
    }

    public int getValue() {
        return value;
    }

    public Quantity add(Quantity other) {
        return Quantity.of(
            Math.addExact(this.value, other.value)
        );
    }

    public Quantity subtract(Quantity other) {
        return Quantity.of(
            Math.subtractExact(this.value, other.value)
        );
    }
}
