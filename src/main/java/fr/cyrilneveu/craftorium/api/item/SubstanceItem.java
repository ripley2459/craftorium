package fr.cyrilneveu.craftorium.api.item;

import fr.cyrilneveu.craftorium.api.substance.Substance;
import fr.cyrilneveu.craftorium.api.substance.object.ASubstanceObject;
import fr.cyrilneveu.craftorium.api.utils.Utils;
import net.minecraft.item.ItemStack;

import static fr.cyrilneveu.craftorium.CraftoriumTags.MODID;
import static fr.cyrilneveu.craftorium.common.inventory.CreativeTabs.SUBSTANCES;

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
}
