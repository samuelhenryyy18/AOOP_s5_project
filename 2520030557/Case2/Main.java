
import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) {

        System.out.println("===== ORDER MANAGEMENT SYSTEM =====");

        // Create an order
        Order order = new Order(new OrderId("ORD101"));
        // Test removing a product while order is DRAFT
Order testOrder = new Order(new OrderId("ORD102"));

testOrder.addLine(new OrderLine(
        new ProductId("P103"),
        new BigDecimal("500"),
        1
));

testOrder.removeLine(new ProductId("P103"));

System.out.println("Product removal test: "
        + testOrder.getLines().size());

        // Create products
        OrderLine laptop = new OrderLine(
                new ProductId("P101"),
                new BigDecimal("50000"),
                2
        );

        OrderLine mouse = new OrderLine(
                new ProductId("P102"),
                new BigDecimal("1000"),
                1
        );

        // Add products to order
        order.addLine(laptop);
        order.addLine(mouse);

        System.out.println("Order ID: " + order.getOrderId());
        System.out.println("Initial Status: " + order.getStatus());

        // Display total
        System.out.println("Order Total: Rs. " + order.getTotal());

        // Place order
        order.placeOrder();
        System.out.println("After placing: " + order.getStatus());

        // Make payment
        order.pay();
        System.out.println("After payment: " + order.getStatus());

        // Ship order
        order.ship(new TrackingNumber("TRK12345"));
        System.out.println("After shipping: " + order.getStatus());

        System.out.println("Tracking Number: "
                + order.getShipment().trackingNumber());

        // Deliver order
        order.deliver();
        System.out.println("Final Status: " + order.getStatus());

        // Test 1: Cancel a delivered order
        System.out.println("\n===== TESTING INVALID OPERATIONS =====");

        try {
            order.cancel();
            System.out.println("Test 1 Failed");
        } catch (IllegalStateException e) {
            System.out.println("Test 1 Passed: " + e.getMessage());
        }

        // Test 2: Add product after placing order
        try {
            order.addLine(new OrderLine(
                    new ProductId("P103"),
                    new BigDecimal("500"),
                    1
            ));
            System.out.println("Test 2 Failed");
        } catch (IllegalStateException e) {
            System.out.println("Test 2 Passed: " + e.getMessage());
        }
        // Test 3: Cancel an order before shipping
Order cancelOrder = new Order(new OrderId("ORD103"));

cancelOrder.addLine(new OrderLine(
        new ProductId("P104"),
        new BigDecimal("2000"),
        1
));

cancelOrder.placeOrder();
cancelOrder.cancel();

System.out.println("Cancellation test: "
        + cancelOrder.getStatus());
        // Test 4: Prevent shipping without a tracking number
Order shipmentTest = new Order(new OrderId("ORD104"));

shipmentTest.addLine(new OrderLine(
        new ProductId("P105"),
        new BigDecimal("3000"),
        1
));

shipmentTest.placeOrder();
shipmentTest.pay();

try {
    shipmentTest.ship(null);
    System.out.println("Shipment test Failed");
} catch (IllegalArgumentException e) {
    System.out.println("Shipment test Passed: "
            + e.getMessage());
}

System.out.println("Status after invalid shipment: "
        + shipmentTest.getStatus());

System.out.println("Shipment details: "
        + shipmentTest.getShipment());

        System.out.println("\n===== EXPERIMENT COMPLETED =====");
    }
}
