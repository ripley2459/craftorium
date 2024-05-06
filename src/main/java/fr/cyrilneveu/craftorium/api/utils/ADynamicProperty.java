package fr.cyrilneveu.craftorium.api.utils;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import net.minecraft.block.properties.PropertyHelper;

import java.util.List;

public abstract class ADynamicProperty<T extends Comparable<T>> extends PropertyHelper<T> {
    private boolean closed = false;
    private List<T> values;

    public ADynamicProperty(String name, Class<T> clazz, T... values) {
        super(name, clazz);
        this.values = Lists.newArrayList(values);
    }

    @Override
    public List<T> getAllowedValues() {
        if (!closed) {
            values = ImmutableList.copyOf(values);
            closed = true;
        }

        return values;
    }
}
