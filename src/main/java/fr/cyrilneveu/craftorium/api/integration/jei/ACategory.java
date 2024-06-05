package fr.cyrilneveu.craftorium.api.integration.jei;

import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;

import static fr.cyrilneveu.craftorium.CraftoriumTags.MODID;

public abstract class ACategory<T extends IRecipeWrapper> implements IRecipeCategory<T> {
    protected final IDrawable background;

    public ACategory(IDrawable background) {
        this.background = background;
    }

    @Override
    public String getModName() {
        return MODID;
    }

    @Override
    public IDrawable getBackground() {
        return background;
    }
}
