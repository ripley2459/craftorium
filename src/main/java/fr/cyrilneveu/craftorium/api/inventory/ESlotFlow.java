package fr.cyrilneveu.craftorium.api.inventory;

import net.minecraft.util.math.MathHelper;

public enum ESlotFlow {
    INPUT,
    OUTPUT,
    FREE,
    LOCK;

    public static ESlotFlow byIndex(int index) {
        return values()[MathHelper.abs(index % values().length)];
    }

    public ESlotFlow previous() {
        return values()[(ordinal() - 1 + values().length) % values().length];
    }

    public ESlotFlow next() {
        return values()[(ordinal() + 1) % values().length];
    }
}