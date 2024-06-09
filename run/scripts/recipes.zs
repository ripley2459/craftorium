#norun

import mods.craftorium.recipe.Recipes;
import mods.craftorium.recipe.Builder;
import mods.craftorium.recipe.Maps;

var electrolyzing = Maps.get("electrolyzing");

var newRecipe = Recipes.create("test_recipe_ct")
                       .consumeItem(<ore:sand>)
                       .produceItem(<minecraft:iron_block> * 48)
                       .produceItem(<minecraft:gold_block> * 48)
                       .consumeEnergy(1) // Total amount of energy required
                       .duration(1) // Duration in tick (20 ticks = 1 sec)
                       .configuration(2) // Define the required machine configuration
                       .build();

electrolyzing.addRecipe(newRecipe);