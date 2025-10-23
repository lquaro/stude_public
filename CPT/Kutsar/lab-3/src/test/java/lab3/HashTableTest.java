package lab3;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class HashTableTest {

    @Test
    void basicPutGetRemove() {
        HashTable<String, Integer> ht = new HashTable<>();
        assertTrue(ht.isEmpty());
        ht.put("a", 1);
        ht.put("b", 2);
        ht.put("a", 3); // replace
        assertEquals(2, ht.size());
        assertEquals(3, ht.get("a"));
        assertEquals(2, ht.get("b"));
        assertNull(ht.get("c"));
        assertEquals(3, ht.remove("a"));
        assertEquals(1, ht.size());
        assertNull(ht.remove("a"));
    }

    @Test
    void ordersChangeStatus() {
        OrderTable store = new OrderTable();
        Order order = new Order(101, LocalDate.now(), List.of(new Item("Pen", 1, 1.5)), OrderStatus.NEW);
        store.insert(order);
        assertEquals(OrderStatus.NEW, store.find(101).getStatus());
        assertTrue(store.changeStatus(101, OrderStatus.SHIPPED));
        assertEquals(OrderStatus.SHIPPED, store.find(101).getStatus());
        assertFalse(store.changeStatus(999, OrderStatus.CANCELED));
    }
}
