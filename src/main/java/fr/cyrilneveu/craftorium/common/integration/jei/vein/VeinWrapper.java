package fr.cyrilneveu.craftorium.common.integration.jei.vein;

import fr.cyrilneveu.craftorium.api.inventory.OreStack;
import fr.cyrilneveu.craftorium.api.substance.Substance;
import fr.cyrilneveu.craftorium.api.utils.Utils;
import fr.cyrilneveu.craftorium.api.world.vein.Vein;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

import static fr.cyrilneveu.craftorium.CraftoriumTags.MODID;
import static fr.cyrilneveu.craftorium.common.substance.SubstancesObjects.ORE;

public final class VeinWrapper implements IRecipeWrapper {
    public static final ResourceLocation BACKGROUND_OVERWORLD = new ResourceLocation(MODID, "textures/interfaces/elements/background_overworld.png");
    public static final ResourceLocation BACKGROUND_NETHER = new ResourceLocation(MODID, "textures/interfaces/elements/background_nether.png");
    public static final ResourceLocation BACKGROUND_END = new ResourceLocation(MODID, "textures/interfaces/elements/background_end.png");
    private final Vein vein;

    public VeinWrapper(Vein vein) {
        this.vein = vein;
    }

    @Override
    public void getIngredients(IIngredients ingredients) {
        List<List<ItemStack>> allItemsOut = new ArrayList<>();

        for (Substance substance : vein.getComposition())
            allItemsOut.add(new OreStack(ORE.getOre(substance)).getStacks());

        if (!allItemsOut.isEmpty())
            ingredients.setOutputLists(VanillaTypes.ITEM, allItemsOut);
    }

    @Override
    public void drawInfo(Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
        minecraft.fontRenderer.drawString(vein.getDisplayName(), 1, 1, 4210752);
        minecraft.fontRenderer.drawString(Utils.localise("jei.craftorium.vein.dimension", vein.getDimension()), 1, 9 + 1, 4210752);
        minecraft.fontRenderer.drawString(Utils.localise("jei.craftorium.vein.depth", vein.getMinY(), vein.getMaxY()), 1, 9 + 9 + 1, 4210752);
        minecraft.fontRenderer.drawString(Utils.localise("jei.craftorium.vein.chance", vein.getChance()), 1, 9 + 9 + 9 + 1, 4210752);
    }

    private ResourceLocation getBackground() {
        return switch (vein.getDimension()) {
            case -1 -> BACKGROUND_NETHER;
            case 1 -> BACKGROUND_END;
            default -> BACKGROUND_OVERWORLD;
        };
    }

    public Vein getVein() {
        return vein;
    }
}
