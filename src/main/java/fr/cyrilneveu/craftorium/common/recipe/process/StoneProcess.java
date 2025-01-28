package fr.cyrilneveu.craftorium.common.recipe.process;

import fr.cyrilneveu.craftorium.api.inventory.OreStack;
import fr.cyrilneveu.craftorium.api.recipe.vanilla.RecipeManager;
import fr.cyrilneveu.craftorium.api.substance.Substance;

import static fr.cyrilneveu.craftorium.common.substance.SubstancesObjects.*;

public class StoneProcess extends DefaultProcess {
    public StoneProcess() {
        super("stone");
    }

    @Override
    protected void dust(Substance substance) {
        if (!substance.getItems().contains(DUST))
            return;

        super.dust(substance);

        if (OreStack.oresExist(HAMMER.getOre(substance), ORE.getOre(substance)))
            RecipeManager.addShapelessRecipe(DUST.getName(substance).concat(BLOCK.getName(substance)), DUST.asItemStack(substance, 9), HAMMER.asIngredient(substance), BLOCK.asIngredient(substance));
    }

    @Override
    protected void hull(Substance substance) {
        if (!substance.getBlocks().contains(HULL))
            return;

        if (OreStack.oresExist(PLATE.getOre(substance), FILE.getOre(substance)))
            RecipeManager.addShapedRecipe(HULL.getName(substance), HULL.asItemStack(substance), "PPP", "PWP", "PPP", 'P', PLATE.asIngredient(substance), 'W', FILE.asIngredient(substance));
    }
}