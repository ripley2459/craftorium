package fr.cyrilneveu.craftorium.api.utils;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public final class NBTUtils {
    public static final String ENERGY_NBT_KEY = "Energy";
    public static final String DAMAGE_NBT_KEY = "Damage";
    public static final String FACE_FLOW_NBT_KEY = "FaceFlow";
    public static final String MACHINE_NBT_KEY = "Machine";
    public static final String TIER_NBT_KEY = "Tier";
    public static final String STATE_NBT_KEY = "State";

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

    public static NBTTagCompound setValue(String name, int value, NBTTagCompound nbt) {
        nbt.setInteger(name, value);
        return nbt;
    }

    public static String getStringValue(String name, NBTTagCompound nbt) {
        return nbt.getString(name);
    }

    public static NBTTagCompound setValue(String name, String value, NBTTagCompound nbt) {
        nbt.setString(name, value);
        return nbt;
    }
}
