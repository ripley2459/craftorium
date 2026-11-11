package fr.cyrilneveu.craftorium.common.recipe.process;

import fr.cyrilneveu.craftorium.api.inventory.OreStack;
import fr.cyrilneveu.craftorium.api.substance.Substance;
import fr.cyrilneveu.craftorium.common.recipe.RecipeUtils;

import static fr.cyrilneveu.craftorium.common.substance.SubstancesObjects.*;

public class FlintProcess extends DefaultProcess {
    protected void file(Substance substance) {
        if (!substance.getTools().contains(FILE))
            return;

        if (OreStack.oresExist(KNIFE.getOre(substance), GEM.getOre(substance), "stickWood"))
            RecipeUtils.addShapedRecipe(FILE.getName(substance), FILE.asItemStack(substance), "GK", "S ", 'G', GEM.asIngredient(substance), 'K', KNIFE.asIngredient(substance), 'S', "stickWood");
    }

    protected void knife(Substance substance) {
        if (!substance.getTools().contains(KNIFE))
            return;

        if (OreStack.oresExist(GEM.getOre(substance), "stickWood"))
            RecipeUtils.addShapedRecipe(KNIFE.getName(substance), KNIFE.asItemStack(substance), "G", "S", 'G', GEM.asIngredient(substance), 'S', "stickWood");
    }

    protected void saw(Substance substance) {
        if (!substance.getTools().contains(SAW))
            return;

        if (OreStack.oresExist(KNIFE.getOre(substance), FILE.getOre(substance), GEM.getOre(substance), "stickWood"))
            RecipeUtils.addShapedRecipe(SAW.getName(substance), SAW.asItemStack(substance), "FK", "GS", 'F', FILE.asIngredient(substance), 'K', KNIFE.asIngredient(substance), 'G', GEM.asIngredient(substance), 'S', "stickWood");
    }
}
