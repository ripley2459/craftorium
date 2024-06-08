# Recipes

[CraftTweaker](https://www.curseforge.com/minecraft/mc-mods/crafttweaker) allows you to create recipes used by machines.

Import some packages:

```ZenScript
import mods.craftorium.recipe.Recipes;
import mods.craftorium.recipe.Builder;
import mods.craftorium.recipe.Maps;
```

With the `Recipes` package you can list recipes. But also have access to information about a particular.

The `Builder` package gives you ways to create recipes from scratch.

## Recipe Map

Internally, recipes are grouped inside maps and maps can be bound to one or more machine type.

### Get a map

```ZenScript
var myMap = Maps.get(string id);
```

### Removing a recipe

```ZenScript
myMap.removeRecipe(string id);
```

### Adding a recipe

```ZenScript
myMap.addRecipe(newRecipe)
```

## Recipe creation

### Initializing the process

To start the process of creating a recipe you have to call `create` from the `Builder` package.

```ZenScript
var newRecipe = Builder.create(String id);
```

The `create` method returns a `Builder` which follow the [builder pattern](https://en.wikipedia.org/wiki/Builder_pattern). So you can store it or chain methods.

If you use an `id` already in use, the existing recipe will be overwritten.

The obtained `Builder` is filled with the provided values (see below for associated methods), so you can directly call `build` (see below) to get a valid recipe.

### Ending the process

```ZenScript
newRecipe.build()
```

Once you are done, you have to call `build` to "transform" the builder into a recipe.

### Registering a recipe

```ZenScript
myMap.addRecipe(newRecipe)
```

To be effectively registered inside the game, the recipe must be added inside a map.

### Available methods

#### Consume Item

```ZenScript
newRecipe.consumeItem(IIngredient ingredient)
newRecipe.consumeItem(String ore, int amount)
newRecipe.itemNotConsumed(IItemStack itemStack)
```

- `amount` > 0

#### Consume Fluid

```ZenScript
newRecipe.consumeFluid(ILiquidStack liquidStack)
newRecipe.consumeFluid(String fluid, int amount)
```

- `amount` > 0

#### Produce Fluid

```ZenScript
newRecipe.produceItem(IItemStack itemStack)
newRecipe.produceItem(IItemStack itemStack, int chance)
```

- `chance` ∈ ]0;100]

#### Produce Fluid

```ZenScript
newRecipe.produceFluid(ILiquidStack liquidStack)
newRecipe.produceFluid(ILiquidStack liquidStack, int chance)
newRecipe.produceFluid(String fluidName, int amount)
newRecipe.produceFluid(String fluidName, int amount, int chance)
```

- `amount` > 0
- `chance` ∈ ]0;100]

#### Consume Energy

```ZenScript
newRecipe.consumeEnergy(int amount)
```

- `amount` >= 0

#### Produce Energy

```ZenScript
newRecipe.produceEnergy(int amount)
```

- `amount` >= 0

#### Duration

```ZenScript
newRecipe.duration(int duration)
```

- `duration` >= 0

#### Configuration

```ZenScript
newRecipe.configuration(int configuration)
```

- `chance` ∈ [1;9]

### Example: Create

```ZenScript
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
```