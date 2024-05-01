package fr.cyrilneveu.craftorium.common.integration.jei;

import fr.cyrilneveu.craftorium.api.utils.Utils;
import fr.cyrilneveu.craftorium.api.world.vein.Vein;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;

import static fr.cyrilneveu.craftorium.CraftoriumTags.MODID;
import static fr.cyrilneveu.craftorium.api.utils.Utils.BACKGROUND;

public final class VeinCategory extends ACategory<VeinWrapper> {
    public VeinCategory(IGuiHelper guiHelper) {
        super(guiHelper.drawableBuilder(BACKGROUND, 4, 4, 128 - 2 * 4, 128 - 2 * 4).build());
    }

    @Override
    public String getUid() {
        return String.join(":", MODID, "vein_generation");
    }

    @Override
    public String getTitle() {
        return Utils.localise(String.join(".", "jei", MODID, "vein_generation", "name"));
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, VeinWrapper recipeWrapper, IIngredients ingredients) {
        IGuiItemStackGroup items = recipeLayout.getItemStacks();
        Vein vein = recipeWrapper.getVein();

        int slot = 0;
        if (!vein.getSubstances().isEmpty()) {
            for (int i = 0; i < ingredients.getOutputs(VanillaTypes.ITEM).size(); i++)
                items.init(slot++, false, i * 16, 4 * 9);
        }

        slot = 0;
        if (!vein.getSubstances().isEmpty()) {
            for (int i = 0; i < ingredients.getOutputs(VanillaTypes.ITEM).size(); i++)
                items.set(slot++, ingredients.getOutputs(VanillaTypes.ITEM).get(i));
        }
    }
}
