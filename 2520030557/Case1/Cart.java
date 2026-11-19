
import java.util.ArrayList;
import java.util.List;

public class Cart {

    private final String cartId;
    private final List<CartLine> items = new ArrayList<>();

    public Cart(String cartId) {
        if (cartId == null || cartId.isBlank()) {
            throw new IllegalArgumentException(
                "Cart ID cannot be empty"
            );
        }

        this.cartId = cartId;
    }

    public void addProduct(Product product, Quantity quantity) {

        if (product == null || quantity == null) {
            throw new IllegalArgumentException(
                "Invalid cart item"
            );
        }

        items.add(new CartLine(product, quantity));
    }

    public Money getTotal() {

        if (items.isEmpty()) {
            throw new IllegalStateException(
                "Cart is empty"
            );
        }

        Money total = items.get(0).getProduct()
            .getPrice()
            .times(items.get(0).getQuantity());

        for (int i = 1; i < items.size(); i++) {

            Money itemTotal = items.get(i)
                .getProduct()
                .getPrice()
                .times(items.get(i).getQuantity());

            total = total.plus(itemTotal);
        }

        return total;
    }

    public String getCartId() {
        return cartId;
    }

    private static class CartLine {

        private final Product product;
        private final Quantity quantity;

        CartLine(Product product, Quantity quantity) {
            this.product = product;
            this.quantity = quantity;
        }

        Product getProduct() {
            return product;
        }

        Quantity getQuantity() {
            return quantity;
        }
    }
}
