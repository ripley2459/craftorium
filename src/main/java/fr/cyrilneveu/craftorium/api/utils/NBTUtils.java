package fr.cyrilneveu.craftorium.api.utils;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public final class NBTUtils {
    public static int getValue(String name, ItemStack itemStack) {
        NBTTagCompound nbt = getNBT(itemStack);
        return nbt.getInteger(name);
    }

    public static NBTTagCompound setValue(String name, int value, ItemStack itemStack) {
        NBTTagCompound nbt = getNBT(itemStack);
        nbt.setInteger(name, value);
        return nbt;
    }

    public static NBTTagCompound getNBT(ItemStack itemStack) {
        if (!itemStack.hasTagCompound()) itemStack.setTagCompound(new NBTTagCompound());
        return itemStack.getTagCompound();
    }

    public static int getValue(String name, NBTTagCompound nbt) {
        return nbt.getInteger(name);
    }

    public static NBTTagCompound setValue(String name, int value) {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setInteger(name, value);
        return nbt;
    }

    public static NBTTagCompound setValue(String name, int value, NBTTagCompound nbt) {
        nbt.setInteger(name, value);
        return nbt;
    }
}
