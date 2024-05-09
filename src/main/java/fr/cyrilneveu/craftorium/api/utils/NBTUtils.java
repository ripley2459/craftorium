package fr.cyrilneveu.craftorium.api.utils;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public final class NBTUtils {
    public static final String ENERGY_NBT_KEY = "Energy";

    public static NBTTagCompound getNBT(ItemStack itemStack) {
        if (!itemStack.hasTagCompound())
            itemStack.setTagCompound(new NBTTagCompound());
        return itemStack.getTagCompound();
    }

    public static int getIntValue(String name, ItemStack itemStack) {
        return getIntValue(name, getNBT(itemStack));
    }

    public static int getIntValue(String name, NBTTagCompound nbt) {
        return nbt.getInteger(name);
    }

    public static NBTTagCompound setValue(String name, int value, ItemStack itemStack) {
        return setValue(name, value, getNBT(itemStack));
    }

    public static NBTTagCompound setValue(String name, int value) {
        return setValue(name, value, new NBTTagCompound());
    }

    public static NBTTagCompound setValue(String name, int value, NBTTagCompound nbt) {
        nbt.setInteger(name, value);
        return nbt;
    }
}
