import mods.craftorium.recipe.Recipes;
import mods.craftorium.recipe.Builder;
import mods.craftorium.recipe.Maps;

var electrolyzing = Maps.get("electrolyzing");

var newRecipe = Recipes.create("test_recipe_ct")
                       .consumeItem(<ore:ingotIron> * 4)
                       .consumeFluid(<liquid:aluminum> * 2000)
                       .produceItem(<minecraft:lapis_block> * 32)
                       .produceItem(<minecraft:iron_sword>)
                       .produceItem(<craftorium:copper_rod> * 64)
					   .produceFluid("iron", 5000)
					   .produceFluid("copper", 1000, 50)
                       .consumeEnergy(3000) // Total amount of energy required
                       .duration(200) // Duration in tick (20 ticks = 1 sec)
                       .configuration(5) // Define the required machine configuration
                       .build();

electrolyzing.addRecipe(newRecipe);