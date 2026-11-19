public record ProductId(String value) {

    public ProductId {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Product ID cannot be empty");
        }
    }

    @Override
    public String toString() {
        return value;
    }
}