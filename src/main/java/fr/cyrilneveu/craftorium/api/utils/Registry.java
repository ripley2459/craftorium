package fr.cyrilneveu.craftorium.api.utils;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class Registry<K extends Comparable<K>, V> {
    private EState state = EState.NONE;
    private Map<K, V> values = new LinkedHashMap<>();

    public boolean put(K key, V value) {
        Preconditions.checkArgument(isOpen(), "Can't add an element from an already closed registry!");

        return values.put(key, value) == null;
    }

    public boolean remove(K key) {
        Preconditions.checkArgument(isOpen(), "Can't remove an element from an already closed registry!");

        return values.remove(key) != null;
    }

    @Nullable
    public V get(K key) {
        return values.get(key);
    }

    public Map<K, V> getAll() {
        return values;
    }

    public boolean contains(K key) {
        return values.containsKey(key);
    }

    public void initialize() {
        Preconditions.checkArgument(isOpen(), "Can't initialize an already closed registry!");

        state = EState.INITIALIZED;
    }

    public boolean isInitialized() {
        return state == EState.INITIALIZED;
    }

    public boolean isOpen() {
        return state != EState.CLOSED;
    }

    public Registry<K, V> order() {
        List<Map.Entry<K, V>> ordered = new ArrayList<>(values.entrySet());
        ordered.sort(Map.Entry.comparingByKey());
        values.clear();
        ordered.forEach(e -> values.put(e.getKey(), e.getValue()));
        return this; // For a friendly usage.
    }

    public void close() {
        Preconditions.checkArgument(isOpen(), "Can't close an already closed registry!");

        state = EState.CLOSED;
        values = ImmutableMap.copyOf(values);
    }

    private enum EState {
        NONE,
        INITIALIZED,
        CLOSED;
    }
}
