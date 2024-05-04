package fr.cyrilneveu.craftorium.api.utils;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.List;

public interface IItemBehaviour {
    default void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {

    }

    default boolean showDurabilityBar(@Nonnull ItemStack itemStack) {
        return false;
    }

    default double getDurabilityForDisplay(@Nonnull ItemStack itemStack) {
        return -1;
    }
}
