package fr.cyrilneveu.craftorium.api.item.behaviour;

import fr.cyrilneveu.craftorium.api.inventory.OreStack;
import fr.cyrilneveu.craftorium.api.substance.Substance;
import fr.cyrilneveu.craftorium.api.utils.NBTUtils;
import fr.cyrilneveu.craftorium.api.utils.Utils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

import static fr.cyrilneveu.craftorium.api.utils.NBTUtils.DAMAGE_NBT_KEY;
import static fr.cyrilneveu.craftorium.common.substance.SubstancesObjects.INGOT;

public final class DurabilityBehaviour implements IItemBehaviour {
    private final OreStack repairable;
    private final int maxDamage;
    private int damage;

    public DurabilityBehaviour(Substance substance) {
        this.repairable = new OreStack(INGOT.getOre(substance));
        this.maxDamage = substance.getEfficiency().getDurability();
    }

    public static int getDamage(ItemStack stack) {
        return NBTUtils.getIntValue(DAMAGE_NBT_KEY, stack);
    }

    public static void setDamage(ItemStack stack, int newDamage) {
        NBTUtils.setValue(DAMAGE_NBT_KEY, newDamage, stack);
    }

    @Override
    public boolean isDamageable() {
        return true;
    }

    @Override
    public boolean hasContainerItem(ItemStack stack) {
        return true;
    }

    @Override
    public ItemStack getContainerItem(ItemStack stack) {
        ItemStack container = stack.copy();

        int actualDamage = getDamage(container);
        actualDamage++;

        if (actualDamage >= maxDamage) {
            container.shrink(1);
            return container;
        }

        setDamage(container, actualDamage);

        return container;
    }

    @Override
    public boolean showDurabilityBar(@NotNull ItemStack stack) {
        return getDamage(stack) > 0;
    }

    @Override
    public double getDurabilityForDisplay(@NotNull ItemStack stack) {
        return getDamage(stack) / (double) maxDamage;
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        return repairable.matches(repair);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World world, List<String> tooltips, ITooltipFlag flag) {
        tooltips.add(Utils.localise("tooltip.craftorium.tool.damage", maxDamage - getDamage(stack), maxDamage));
    }
}
