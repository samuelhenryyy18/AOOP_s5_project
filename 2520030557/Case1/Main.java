
public class Main {

    public static void main(String[] args) {

        System.out.println("================================");
        System.out.println(" E-COMMERCE MONEY SYSTEM");
        System.out.println("================================");

        // Create money
        Money price = Money.of("100.00", Currency.EUR);

        System.out.println("\nOriginal Price: " + price);

        // Apply discount
        Percentage discount =
            Percentage.ofBasisPoints(2000);

        Money discountedPrice = price.minus(discount);

        System.out.println("Discount: 20%");
        System.out.println("Discounted Price: " + discountedPrice);

        // Create quantity
        Quantity quantity = Quantity.of(2);

        System.out.println("\nQuantity: " + quantity.getValue());

        // Calculate total
        Money total = discountedPrice.times(quantity);

        System.out.println("Total Price: " + total);

        // Create product
        Product product = new Product(
            "P101",
            "Laptop",
            discountedPrice
        );

        System.out.println("\nProduct: " + product.getName());
        System.out.println("SKU: " + product.getSku());

        // Create shopping cart
        Cart cart = new Cart("C101");

        cart.addProduct(product, quantity);

        System.out.println("\nCart ID: " + cart.getCartId());
        System.out.println("Cart Total: " + cart.getTotal());

        // Test currency mismatch
        System.out.println("\nTesting Currency Mismatch:");

        try {
            Money dollars = Money.of("50.00", Currency.USD);
            price.plus(dollars);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Test invalid quantity
        System.out.println("\nTesting Invalid Quantity:");

        try {
            Quantity.of(0);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Test invalid discount
        System.out.println("\nTesting Invalid Discount:");

        try {
            Percentage.ofBasisPoints(25000);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\n================================");
        System.out.println(" EXPERIMENT COMPLETED");
        System.out.println("================================");
    }
}
