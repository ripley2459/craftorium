package fr.cyrilneveu.craftorium.api.utils;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public final class CustomOptional<T> {
    private final Supplier<T> supplier;
    private boolean initialized;
    @Nullable
    private T value;

    public CustomOptional(Supplier<T> supplier) {
        this.supplier = supplier;
    }

    @Nullable
    public T getValue() {
        if (!initialized) {
            initialized = true;
            value = supplier.get();
        }

        return value;
    }
}
