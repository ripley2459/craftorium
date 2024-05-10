package fr.cyrilneveu.craftorium.api.item.behaviour;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.List;

public interface IItemBehaviour {
    @SideOnly(Side.CLIENT)
    default void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {

    }

    @SideOnly(Side.CLIENT)
    default boolean showDurabilityBar(@Nonnull ItemStack itemStack) {
        return false;
    }

    @SideOnly(Side.CLIENT)
    default double getDurabilityForDisplay(@Nonnull ItemStack itemStack) {
        return -1;
    }
}
