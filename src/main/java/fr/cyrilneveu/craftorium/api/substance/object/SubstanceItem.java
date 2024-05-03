package fr.cyrilneveu.craftorium.api.substance.object;

import fr.cyrilneveu.craftorium.api.item.CustomItem;
import fr.cyrilneveu.craftorium.api.substance.Substance;
import fr.cyrilneveu.craftorium.api.utils.Utils;
import net.minecraft.item.ItemStack;

public class SubstanceItem extends CustomItem {
    protected final ASubstanceObject reference;
    protected final Substance substance;

    public SubstanceItem(ASubstanceObject reference, Substance substance) {
        super(reference.getFaces(substance), () -> reference.getTooltips(substance));
        this.reference = reference;
        this.substance = substance;
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        return Utils.localise(String.join(".", getTranslationKey(), "name"), substance.getDisplayName());
    }
}
