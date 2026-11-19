import java.time.LocalDateTime;

public record Shipment(
        TrackingNumber trackingNumber,
        LocalDateTime shippedAt
) {

    public Shipment {
        if (trackingNumber == null) {
            throw new IllegalArgumentException("Tracking number cannot be null");
        }

        if (shippedAt == null) {
            throw new IllegalArgumentException("Shipment date cannot be null");
        }
    }
}