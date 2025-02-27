package fr.cyrilneveu.craftorium.api.utils;

import javax.annotation.Nullable;
import java.util.*;

public final class WeightedList<K> implements Iterable<K> {
    private final Map<K, Integer> values = new HashMap<>();
    private int total = 0;
    private boolean dirty = true;

    public void put(K item, int weight) {
        if (weight <= 0 || item == null)
            return;

        if (values.containsKey(item))
            remove(item);

        values.put(item, weight);
        dirty = true;
    }

    public void remove(K item) {
        values.remove(item);
        dirty = true;
    }

    @Nullable
    public K get(Random random) {
        if (dirty) {
            total = 0;
            values.values().forEach(i -> total += i);
            dirty = false;
        }

        double r = random.nextDouble() * total;
        for (Map.Entry<K, Integer> entry : values.entrySet()) {
            r -= entry.getValue();
            if (r <= 0)
                return entry.getKey();
        }

        return null;
    }

    public List<K> keys() {
        return new ArrayList<>(values.keySet());
    }

    public List<Integer> values() {
        return new ArrayList<>(values.values());
    }

    public Map<K, Integer> map() {
        return values;
    }

    public int size() {
        return values.size();
    }

    public boolean isEmpty() {
        return values.isEmpty();
    }

    @Override
    public Iterator<K> iterator() {
        return values.keySet().iterator();
    }
}
