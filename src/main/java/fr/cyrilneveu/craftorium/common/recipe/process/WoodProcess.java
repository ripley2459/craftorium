package fr.cyrilneveu.craftorium.common.recipe.process;

import fr.cyrilneveu.craftorium.api.inventory.OreStack;
import fr.cyrilneveu.craftorium.api.recipe.machine.MachineRecipeBuilder;
import fr.cyrilneveu.craftorium.api.recipe.vanilla.RecipeManager;
import fr.cyrilneveu.craftorium.api.substance.Substance;

import static fr.cyrilneveu.craftorium.common.recipe.Maps.CONFIGURATION_CUTTER_LARGE;
import static fr.cyrilneveu.craftorium.common.recipe.Maps.CUTTING;
import static fr.cyrilneveu.craftorium.common.substance.SubstancesObjects.*;

public class WoodProcess extends DefaultProcess {
    public WoodProcess() {
        super("process_wood");
    }

    @Override
    protected void plate(Substance substance) {
        if (!substance.getItems().contains(PLATE))
            return;

        if (OreStack.oresExist(HAMMER.getOre(substance), INGOT.getOre(substance)))
            RecipeManager.addShapedRecipe(PLATE.getName(substance), PLATE.asItemStack(substance), "H", "I", "I", 'H', HAMMER.asIngredient(substance), 'I', INGOT.asIngredient(substance));

        if (OreStack.oresExist(BLOCK.getOre(substance)))
            CUTTING.addRecipe(new MachineRecipeBuilder(PLATE.getName(substance).concat(BLOCK.getName(substance)))
                    .consumeItem(BLOCK.getOre(substance), 1)
                    .produceItem(PLATE.asItemStack(substance, 9))
                    .consumeEnergy(45000)
                    .duration(1800)
                    .configuration(CONFIGURATION_CUTTER_LARGE)
                    .build());
    }

    @Override
    protected void hull(Substance substance) {
        if (!substance.getBlocks().contains(HULL))
            return;

        if (OreStack.oresExist(PLATE.getOre(substance), WRENCH.getOre(substance)))
            RecipeManager.addShapedRecipe(HULL.getName(substance), HULL.asItemStack(substance), "PPP", "P P", "PPP", 'P', PLATE.asIngredient(substance));
    }
}
