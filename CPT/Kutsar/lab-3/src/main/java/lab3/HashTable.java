package lab3;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * Универсальная хэш-таблица с методом цепочек.
 * Поддерживает операции put/get/remove/size/isEmpty и авто-ресайз по load factor.
 */
public class HashTable<K, V> {
    private static final int DEFAULT_CAPACITY = 16;
    private static final double DEFAULT_LOAD_FACTOR = 0.75;

    @SuppressWarnings("unchecked")
    private List<Entry<K,V>>[] table = new List[DEFAULT_CAPACITY];
    private int size = 0;
    private final double loadFactor;

    public HashTable() {
        this.loadFactor = DEFAULT_LOAD_FACTOR;
    }

    public HashTable(int initialCapacity, double loadFactor) {
        if (initialCapacity <= 0) throw new IllegalArgumentException("capacity > 0");
        if (loadFactor <= 0 || Double.isNaN(loadFactor)) throw new IllegalArgumentException("loadFactor > 0");
        // round up to power of two would be nice; keep simple:
        @SuppressWarnings("unchecked")
        List<Entry<K,V>>[] t = new List[initialCapacity];
        this.table = t;
        this.loadFactor = loadFactor;
    }

    public void put(K key, V value) {
        Objects.requireNonNull(key, "key must not be null");
        int idx = indexFor(key);
        if (table[idx] == null) table[idx] = new LinkedList<>();
        for (Entry<K,V> e : table[idx]) {
            if (Objects.equals(e.key, key)) {
                e.value = value; // replace
                return;
            }
        }
        table[idx].add(new Entry<>(key, value));
        size++;
        if (size > table.length * loadFactor) {
            resize(table.length * 2);
        }
    }

    public V get(K key) {
        Objects.requireNonNull(key, "key must not be null");
        int idx = indexFor(key);
        List<Entry<K,V>> bucket = table[idx];
        if (bucket == null) return null;
        for (Entry<K,V> e : bucket) {
            if (Objects.equals(e.key, key)) return e.value;
        }
        return null;
    }

    public V remove(K key) {
        Objects.requireNonNull(key, "key must not be null");
        int idx = indexFor(key);
        List<Entry<K,V>> bucket = table[idx];
        if (bucket == null) return null;
        for (var it = bucket.iterator(); it.hasNext(); ) {
            Entry<K,V> e = it.next();
            if (Objects.equals(e.key, key)) {
                it.remove();
                size--;
                return e.value;
            }
        }
        return null;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        for (int i = 0; i < table.length; i++) table[i] = null;
        size = 0;
    }

    private int indexFor(Object key) {
        int h = key.hashCode();
        h ^= (h >>> 16);
        return (h & 0x7fffffff) % table.length;
    }

    @SuppressWarnings("unchecked")
    private void resize(int newCapacity) {
        List<Entry<K,V>>[] old = table;
        List<Entry<K,V>>[] newTab = new List[newCapacity];
        table = newTab;
        int oldSize = size;
        size = 0;
        for (List<Entry<K,V>> bucket : old) {
            if (bucket == null) continue;
            for (Entry<K,V> e : bucket) {
                // rehash
                int idx = indexFor(e.key);
                if (table[idx] == null) table[idx] = new LinkedList<>();
                table[idx].add(new Entry<>(e.key, e.value));
                size++;
            }
        }
        // size should remain equal to oldSize
        if (size != oldSize) throw new IllegalStateException("rehash size mismatch");
    }

    private static class Entry<K,V> {
        final K key;
        V value;
        Entry(K key, V value) { this.key = key; this.value = value; }
    }
}
