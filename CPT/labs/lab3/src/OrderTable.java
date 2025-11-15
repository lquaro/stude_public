package src;

public class OrderTable {
    private final HashTable<Integer, Order> table = new HashTable<>();

    public void insert(Order order) {
        if (order == null) throw new IllegalArgumentException("order required");
        table.put(order.getOrderNumber(), order);
    }
    public java.util.List<Order> all() {
        return table.values();
    }
    public Order find(int orderNumber) { return table.get(orderNumber); }
    public Order delete(int orderNumber) { return table.remove(orderNumber); }
    public boolean changeStatus(int orderNumber, OrderStatus newStatus) {
        Order o = table.get(orderNumber);
        if (o == null) return false;
        o.setStatus(newStatus);
        return true;
    }
    public int size() { return table.size(); }
    public boolean isEmpty() { return table.isEmpty(); }
}