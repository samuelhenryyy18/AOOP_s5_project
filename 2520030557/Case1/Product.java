
public class Product {

    private final String sku;
    private String name;
    private Money price;

    public Product(String sku, String name, Money price) {

        if (sku == null || sku.isBlank()) {
            throw new IllegalArgumentException(
                "SKU cannot be empty"
            );
        }

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(
                "Product name cannot be empty"
            );
        }

        if (price == null) {
            throw new IllegalArgumentException(
                "Price cannot be null"
            );
        }

        this.sku = sku;
        this.name = name;
        this.price = price;
    }

    public String getSku() {
        return sku;
    }

    public String getName() {
        return name;
    }

    public Money getPrice() {
        return price;
    }

    public void updateName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(
                "Product name cannot be empty"
            );
        }

        this.name = name;
    }

    public void updatePrice(Money price) {
        if (price == null) {
            throw new IllegalArgumentException(
                "Price cannot be null"
            );
        }

        this.price = price;
    }
}
