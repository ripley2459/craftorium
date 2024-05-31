package fr.cyrilneveu.craftorium.api.utils;

import com.google.common.base.Preconditions;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public class CustomLazy<T> {
    private final Supplier<T> supplier;
    private final boolean canBeNull;
    private boolean initialized;
    @Nullable
    private T value;

    public CustomLazy(Supplier<T> supplier, boolean canBeNull) {
        this.supplier = supplier;
        this.canBeNull = canBeNull;
    }

    @Nullable
    public T get() {
        if (!initialized) {
            initialized = true;
            value = supplier.get();
            Preconditions.checkArgument(canBeNull || value != null);
        }

        return value;
    }
}
