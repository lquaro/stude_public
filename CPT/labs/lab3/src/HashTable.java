package src;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class HashTable<A, M> {
    private static final int DEFAULT_CAPACITY = 16;
    private static final double DEFAULT_LOAD_FACTOR = 0.75;

    private List<Entry<A,M>>[] table = new List[DEFAULT_CAPACITY];
    private int size = 0;
    private final double loadFactor = DEFAULT_LOAD_FACTOR;

    public void put(A key, M value) {
        Objects.requireNonNull(key, "key");
        int idx = indexFor(key);
        if (table[idx] == null) table[idx] = new LinkedList<>();
        for (Entry<A,M> e : table[idx]) {
            if (Objects.equals(e.key, key)) { e.value = value; return; }
        }
        table[idx].add(new Entry<>(key, value));
        size++;
        if (size > table.length * loadFactor) resize(table.length * 2);
    }
    public M get(A key) {
        Objects.requireNonNull(key, "key");
        List<Entry<A,M>> bucket = table[indexFor(key)];
        if (bucket == null) return null;
        for (Entry<A,M> e : bucket) if (Objects.equals(e.key, key)) return e.value;
        return null;
    }
    public M remove(A key) {
        Objects.requireNonNull(key, "key");
        int idx = indexFor(key);
        List<Entry<A,M>> bucket = table[idx];
        if (bucket == null) return null;
        for (var it = bucket.iterator(); it.hasNext();) {
            Entry<A,M> e = it.next();
            if (Objects.equals(e.key, key)) { it.remove(); size--; return e.value; }
        }
        return null;
    }
    public int size() { return size; }
    public boolean isEmpty() { return size == 0; }

    public java.util.List<M> values() {
        java.util.ArrayList<M> out = new java.util.ArrayList<>(size);
        for (java.util.List<Entry<A,M>> bucket : table) {
            if (bucket == null) continue;
            for (Entry<A,M> e : bucket) out.add(e.value);
        }
        return out;
    }
    private int indexFor(Object key) {
        int h = key.hashCode();
        h ^= (h >>> 16);
        return (h & 0x7fffffff) % table.length;
    }
    @SuppressWarnings("unchecked")
    private void resize(int newCap) {
        List<Entry<A,M>>[] old = table;
        List<Entry<A,M>>[] nt = new List[newCap];
        table = nt; int oldSize = size; size = 0;
        for (List<Entry<A,M>> bucket : old) {
            if (bucket == null) continue;
            for (Entry<A,M> e : bucket) {
                int idx = indexFor(e.key);
                if (table[idx] == null) table[idx] = new LinkedList<>();
                table[idx].add(new Entry<>(e.key, e.value));
                size++;
            }
        }
        if (size != oldSize) throw new IllegalStateException("rehash mismatch");
    }
    private static class Entry<A,M> {
        final A key; M value;
        Entry(A a, M m) { key = a; value = m; }
    }
}