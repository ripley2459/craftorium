package fr.cyrilneveu.craftorium.api.utils;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;

import java.util.LinkedHashMap;
import java.util.Map;

public final class Registry<K extends Comparable<K>, V> {
    private boolean closed = false;
    private Map<K, V> values = new LinkedHashMap<>();

    public boolean put(K key, V value) {
        Preconditions.checkArgument(!closed, "Can't add an element from an already closed registry!");

        return values.put(key, value) == null;
    }

    public boolean remove(K key) {
        Preconditions.checkArgument(!closed, "Can't remove an element from an already closed registry!");

        return values.remove(key) != null;
    }

    public V get(K key) {
        return values.get(key);
    }

    public Map<K, V> getAll() {
        return values;
    }

    public void close() {
        Preconditions.checkArgument(!closed, "Can't close an already closed registry!");

        closed = true;

        /* List<Map.Entry<K, V>> entries = values.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .toList();
        ImmutableMap.Builder<K, V> builder = ImmutableMap.builder();
        entries.forEach(e -> builder.put(e.getKey(), e.getValue()));
        values = builder.build(); */

        values = ImmutableMap.copyOf(values);
    }
}
