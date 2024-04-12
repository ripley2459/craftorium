package fr.cyrilneveu.craftorium.api.item;

import fr.cyrilneveu.craftorium.api.substance.Substance;
import fr.cyrilneveu.craftorium.api.substance.object.ASubstanceObject;
import fr.cyrilneveu.craftorium.api.utils.NBTUtils;
import fr.cyrilneveu.craftorium.api.utils.Utils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class SubstanceItem extends CustomItem {
    protected final Substance substance;
    protected final ASubstanceObject reference;

    public SubstanceItem(ASubstanceObject reference, Substance substance) {
        super(reference.getFaces(substance));
        this.reference = reference;
        this.substance = substance;
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        return Utils.localise(String.join(".", getTranslationKey(), "name"), substance.getDisplayName());
    }

    /*@Override
    public void addInformation(ItemStack stack, @Nullable World world, List<String> tooltips, ITooltipFlag flag) {
        if (!substance.getComposition().getFormula().isEmpty())
            tooltips.add(Utils.localise("tooltip.craftorium.formula", substance.getComposition().getFormula()));
        super.addInformation(stack, world, tooltips, flag);
    }*/

    public static class SubstanceTool extends SubstanceItem {
        private Random random;

        public SubstanceTool(ASubstanceObject reference, Substance substance) {
            super(reference, substance);
            this.maxStackSize = 1;
        }

        public static int getToolDamage(ItemStack itemStack) {
            return NBTUtils.getValue("Damage", itemStack);
        }

        public static void setToolDamage(ItemStack itemStack, int newDamage) {
            NBTUtils.setValue("Damage", newDamage, itemStack);
        }

        public static int getToolMaxDamage(ItemStack itemStack) {
            return ((SubstanceTool) itemStack.getItem()).substance.getEfficiency().getDurability();
        }

        public static void damage(ItemStack itemStack, int amount, Random random, @Nullable EntityPlayer player) {
            if (amount > 0) {
                int actualDamage = getToolDamage(itemStack);
                actualDamage += amount;

                if (actualDamage >= getToolMaxDamage(itemStack)) {
                    itemStack.shrink(1);
                    return;
                }

                setToolDamage(itemStack, actualDamage);
            }
        }

        @Override
        public ItemStack getContainerItem(ItemStack itemStack) {
            ItemStack container = itemStack.copy();
            damage(container, 1, random, null);
            return container;
        }

        @Override
        public boolean showDurabilityBar(ItemStack itemStack) {
            return getToolDamage(itemStack) > 0;
        }

        @Override
        public double getDurabilityForDisplay(ItemStack itemStack) {
            double max = getToolMaxDamage(itemStack);
            return getToolDamage(itemStack) / max;
        }

        @Override
        public boolean isDamageable() {
            return true;
        }

        @Override
        public boolean hasContainerItem(ItemStack itemStack) {
            return true;
        }
    }
}
