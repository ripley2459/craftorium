package fr.cyrilneveu.craftorium.api.block;

import fr.cyrilneveu.craftorium.api.substance.Substance;
import fr.cyrilneveu.craftorium.api.utils.Utils;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class SubstanceItemBlock extends CustomItemBlock {
    protected final Substance substance;

    public SubstanceItemBlock(Block block, Substance substance) {
        super(block);
        this.substance = substance;
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        return Utils.localise(String.join(".", getTranslationKey(), "name"), substance.getDisplayName());
    }
}
