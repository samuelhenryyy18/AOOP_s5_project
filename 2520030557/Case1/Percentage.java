
import java.util.Objects;

public final class Percentage {

    private final int basisPoints;

    private Percentage(int basisPoints) {
        this.basisPoints = basisPoints;
    }

    public static Percentage ofBasisPoints(int bp) {

        if (bp < 0 || bp > 10000) {
            throw new IllegalArgumentException(
                "Percentage must be between 0 and 100"
            );
        }

        return new Percentage(bp);
    }

    public int getBasisPoints() {
        return basisPoints;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;

        if (!(obj instanceof Percentage)) return false;

        Percentage other = (Percentage) obj;

        return basisPoints == other.basisPoints;
    }

    @Override
    public int hashCode() {
        return Objects.hash(basisPoints);
    }
}
