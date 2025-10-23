package lab3;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Order {
    private final int orderNumber;
    private final LocalDate date;
    private final List<Item> items;
    private OrderStatus status;

    public Order(int orderNumber, LocalDate date, List<Item> items, OrderStatus status) {
        if (orderNumber <= 0) throw new IllegalArgumentException("orderNumber > 0");
        if (date == null) throw new IllegalArgumentException("date required");
        if (items == null || items.isEmpty()) throw new IllegalArgumentException("items required");
        if (status == null) status = OrderStatus.NEW;
        this.orderNumber = orderNumber;
        this.date = date;
        this.items = new ArrayList<>(items);
        this.status = status;
    }

    public int getOrderNumber() { return orderNumber; }
    public LocalDate getDate() { return date; }
    public List<Item> getItems() { return Collections.unmodifiableList(items); }
    public OrderStatus getStatus() { return status; }
    public void setStatus(OrderStatus newStatus) {
        if (newStatus == null) throw new IllegalArgumentException("status required");
        this.status = newStatus;
    }

    public double total() {
        return items.stream().mapToDouble(Item::subtotal).sum();
    }

    @Override
    public String toString() {
        return String.format("Order{%d, %s, items=%d, total=%.2f, status=%s}",
                orderNumber, date, items.size(), total(), status);
    }
}
