import java.math.BigDecimal;

public class OrderLine {

    private final ProductId productId;
    private final BigDecimal unitPrice;
    private int quantity;

    public OrderLine(ProductId productId, BigDecimal unitPrice, int quantity) {

        if (productId == null) {
            throw new IllegalArgumentException("Product ID cannot be null");
        }

        if (unitPrice == null || unitPrice.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Invalid product price");
        }

        if (quantity < 1) {
            throw new IllegalArgumentException("Quantity must be at least 1");
        }

        this.productId = productId;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    public ProductId getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void increaseQuantity(int amount) {
        if (amount < 1) {
            throw new IllegalArgumentException("Amount must be positive");
        }

        quantity += amount;
    }

    public BigDecimal getTotal() {
        return unitPrice.multiply(BigDecimal.valueOf(quantity));
    }
}