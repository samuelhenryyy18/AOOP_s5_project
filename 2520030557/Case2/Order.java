import java.util.*;

public class Order {

    private final OrderId orderId;
    private OrderStatus status;
    private final Map<ProductId, OrderLine> lines;
    private Shipment shipment;

    public Order(OrderId orderId) {
        if (orderId == null) {
            throw new IllegalArgumentException("Order ID cannot be null");
        }

        this.orderId = orderId;
        this.status = OrderStatus.DRAFT;
        this.lines = new HashMap<>();
    }

    public void addLine(OrderLine line) {

        if (status != OrderStatus.DRAFT) {
            throw new IllegalStateException("Cannot add items after placing order");
        }

        if (line == null) {
            throw new IllegalArgumentException("Order line cannot be null");
        }

        ProductId id = line.getProductId();

        if (lines.containsKey(id)) {
            lines.get(id).increaseQuantity(line.getQuantity());
        } else {
            lines.put(id, line);
        }
    }
    public void removeLine(ProductId productId) {

    if (status != OrderStatus.DRAFT) {
        throw new IllegalStateException(
            "Cannot remove items after placing order"
        );
    }

    if (productId == null || !lines.containsKey(productId)) {
        throw new IllegalArgumentException(
            "Product does not exist in this order"
        );
    }

    lines.remove(productId);
}

    public void placeOrder() {

        if (status != OrderStatus.DRAFT) {
            throw new IllegalStateException("Order is not in draft state");
        }

        if (lines.isEmpty()) {
            throw new IllegalStateException("Order must contain at least one item");
        }

        status = OrderStatus.PLACED;
    }

    public void pay() {

        if (status != OrderStatus.PLACED) {
            throw new IllegalStateException("Order must be placed before payment");
        }

        status = OrderStatus.PAID;
    }

    
public void ship(TrackingNumber trackingNumber) {

    if (status != OrderStatus.PAID) {
        throw new IllegalStateException("Only paid orders can be shipped");
    }

    if (trackingNumber == null) {
        throw new IllegalArgumentException("Tracking number cannot be null");
    }

    shipment = new Shipment(
        trackingNumber,
        java.time.LocalDateTime.now()
    );

    status = OrderStatus.SHIPPED;
}


    public void deliver() {

        if (status != OrderStatus.SHIPPED) {
            throw new IllegalStateException("Only shipped orders can be delivered");
        }

        status = OrderStatus.DELIVERED;
    }

    public void cancel() {

        if (status == OrderStatus.SHIPPED ||
            status == OrderStatus.DELIVERED ||
            status == OrderStatus.CANCELLED) {

            throw new IllegalStateException("This order cannot be cancelled");
        }

        status = OrderStatus.CANCELLED;
    }

    public java.math.BigDecimal getTotal() {

        java.math.BigDecimal total = java.math.BigDecimal.ZERO;

        for (OrderLine line : lines.values()) {
            total = total.add(line.getTotal());
        }

        return total;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public Shipment getShipment() {
        return shipment;
    }

    public OrderId getOrderId() {
        return orderId;
    }

    public Map<ProductId, OrderLine> getLines() {
        return Collections.unmodifiableMap(lines);
    }
}