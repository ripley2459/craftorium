package fr.cyrilneveu.craftorium.api.item;

import fr.cyrilneveu.craftorium.api.inventory.OreStack;
import fr.cyrilneveu.craftorium.api.substance.Substance;
import fr.cyrilneveu.craftorium.api.substance.object.ASubstanceObject;
import fr.cyrilneveu.craftorium.api.utils.Utils;
import net.minecraft.item.ItemStack;

import static fr.cyrilneveu.craftorium.common.substance.SubstancesObjects.INGOT;

public class SubstanceItem extends CustomItem {
    protected final ASubstanceObject reference;
    protected final Substance substance;

    public SubstanceItem(ASubstanceObject reference, Substance substance) {
        super(reference.getFaces(substance));
        this.reference = reference;
        this.substance = substance;
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        return Utils.localise(String.join(".", getTranslationKey(), "name"), substance.getDisplayName());
    }

    public static class SubstanceTool extends SubstanceItem {
        public SubstanceTool(ASubstanceObject reference, Substance substance) {
            super(reference, substance);
            this.maxStackSize = 1;
            if (substance.getEfficiency() != null)
                this.setMaxDamage(substance.getEfficiency().getDurability());
        }

        @Override
        public boolean isDamageable() {
            return true;
        }

        @Override
        public ItemStack getContainerItem(ItemStack stack) {
            ItemStack container = stack.copy();
            container.setItemDamage(container.getItemDamage() + 1);
            return container;
        }

        @Override
        public boolean hasContainerItem(ItemStack stack) {
            return true;
        }

        @Override
        public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
            return new OreStack(INGOT.getOre(substance)).matches(repair);
        }
    }
}
