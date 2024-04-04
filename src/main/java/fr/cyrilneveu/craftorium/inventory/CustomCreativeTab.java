package fr.cyrilneveu.craftorium.inventory;

import fr.cyrilneveu.craftorium.Craftorium;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import static fr.cyrilneveu.craftorium.CraftoriumTags.MODID;

public class CustomCreativeTab extends CreativeTabs {
    public static CreativeTabs tabCommon = new CustomCreativeTab("common", "test_item_a");
    public static CreativeTabs tabSubstances = new CustomCreativeTab("substances", "test_item_b");
    public static CreativeTabs tabTools = new CustomCreativeTab("tools", "test_item_c");
    public static CreativeTabs tabMachines = new CustomCreativeTab("machines", "test_item_d");
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
