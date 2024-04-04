package fr.cyrilneveu.craftorium.api.inventory;

import fr.cyrilneveu.craftorium.Craftorium;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import static fr.cyrilneveu.craftorium.CraftoriumTags.MODID;

public class CustomCreativeTab extends CreativeTabs {
    private final String item;

    public CustomCreativeTab(String label, String item) {
        super(label);
        this.item = item;
    }

    @Override
    public ItemStack createIcon() {
        Item icon = Item.getByNameOrId(String.join(":", MODID, item));
        if (icon == null) {
            Craftorium.LOGGER.error("Item can't be used for tab " + tabLabel);
            return new ItemStack(Blocks.BEDROCK);
        }

        return new ItemStack(icon);
    }
}
