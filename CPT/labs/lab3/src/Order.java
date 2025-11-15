package src;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Order {
    private final int orderNumber;
    private final LocalDate createdAt;
    private final List<OrderItem> items = new ArrayList<>();
    private OrderStatus status;

    public Order(int orderNumber, LocalDate createdAt, List<OrderItem> items, OrderStatus status) {
        if (orderNumber <= 0) throw new IllegalArgumentException("orderNumber > 0");
        if (createdAt == null) throw new IllegalArgumentException("createdAt required");
        if (items == null || items.isEmpty()) throw new IllegalArgumentException("items required");
        this.orderNumber = orderNumber;
        this.createdAt = createdAt;
        this.items.addAll(items);
        this.status = status == null ? OrderStatus.NEW : status;
    }
    public int getOrderNumber() { return orderNumber; }
    public LocalDate getCreatedAt() { return createdAt; }
    public List<OrderItem> getItems() { return Collections.unmodifiableList(items); }
    public OrderStatus getStatus() { return status; }
    public void setStatus(OrderStatus newStatus) {
        if (newStatus == null) throw new IllegalArgumentException("status required");
        this.status = newStatus;
    }
    public void addItem(OrderItem item) { items.add(item); }
    public double totalAmount() {
        return items.stream().mapToDouble(OrderItem::lineTotal).sum();
    }
    public void changeStatus(OrderStatus newStatus) { setStatus(newStatus); }

    @Override
    public String toString() {
        return "Order{" +
               "num=" + orderNumber +
               ", createdAt=" + createdAt +
               ", status=" + status +
               ", total=" + String.format("%.2f", totalAmount()) +
               '}';
    }
}