package fr.cyrilneveu.craftorium.api.utils;

import java.util.Iterator;

public class CustomIterable<T> implements Iterable<T> {
    private final T[] elements;

    public CustomIterable(T... elements) {
        this.elements = elements;
    }

    @Override
    public Iterator<T> iterator() {
        return new CustomIterator();
    }

    private class CustomIterator implements Iterator<T> {
        private int index = 0;

        @Override
        public boolean hasNext() {
            return index < elements.length;
        }

        @Override
        public T next() {
            return elements[index++];
        }
    }
}
