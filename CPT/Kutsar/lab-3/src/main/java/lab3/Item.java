package lab3;

public class Item {
    private final String name;
    private final int quantity;
    private final double price;

    public Item(String name, int quantity, double price) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("name required");
        if (quantity <= 0) throw new IllegalArgumentException("quantity > 0");
        if (price < 0) throw new IllegalArgumentException("price >= 0");
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public String getName() { return name; }
    public int getQuantity() { return quantity; }
    public double getPrice() { return price; }

    public double subtotal() { return quantity * price; }

    @Override
    public String toString() {
        return String.format("%s x%d @ %.2f", name, quantity, price);
    }
}
