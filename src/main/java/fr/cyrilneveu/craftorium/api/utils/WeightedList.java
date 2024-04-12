package fr.cyrilneveu.craftorium.api.utils;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

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

    public boolean isEmpty() {
        return values.isEmpty();
    }

    @Override
    public Iterator<K> iterator() {
        return values.keySet().iterator();
    }
}
