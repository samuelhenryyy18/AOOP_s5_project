public record TrackingNumber(String value) {

    public TrackingNumber {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Tracking number cannot be empty");
        }
    }

    @Override
    public String toString() {
        return value;
    }
}