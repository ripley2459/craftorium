package fr.cyrilneveu.craftorium.api.tier.object;

import fr.cyrilneveu.craftorium.api.item.CustomItem;
import fr.cyrilneveu.craftorium.api.property.Aestheticism;
import fr.cyrilneveu.craftorium.api.tier.Tier;
import fr.cyrilneveu.craftorium.api.utils.Utils;
import net.minecraft.item.ItemStack;

public class TierItem extends CustomItem {
    protected final ATierObject reference;
    protected final Tier tier;

    public TierItem(ATierObject reference, Tier tier) {
        super(new Aestheticism.ObjectAestheticism(reference.getFaces(tier), () -> reference.getTooltips(tier), tier.getAestheticism().isGlint()));
        this.reference = reference;
        this.tier = tier;
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        return Utils.localise(String.join(".", getTranslationKey(), "name"), tier.getDisplayName());
    }
}
