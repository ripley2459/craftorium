# Veins

[CraftTweaker](https://www.curseforge.com/minecraft/mc-mods/crafttweaker) allows you to manipulate the Craftorium's ore generation.

**You have** to define a custom preprocessor:

```ZenScript
#loader craftorium
```

And import some packages:

```ZenScript
import mods.craftorium.vein.Veins;
import mods.craftorium.vein.Vein;
import mods.craftorium.vein.Builder;
```

With the `Veins` package you can manipulate the veins registry, such as creating, retrieving or registering a vein.

With the `Vein` package you can manipulate a particular vein.

The `Builder` package gives you ways to create veins from scratch.

## Get a vein

```ZenScript
var vein as Vein = Veins.get(string id);
```

## Get all veins

```ZenScript
var vein as Veins[] = Veins.getAll();
```

This can be a rough operation so use it with caution.

## Vein creation

### Initializing the process

To start the process of creating a vein you have to call `create` from the `Builder` package.

```ZenScript
var newVein = Builder.create(String id, int minY, int maxY, int sizeH, int sizeV, int chance, int dimension, [substance₁, chance₁, substance₂, chance₂, [...], substanceₙ, chanceₙ]);
```

The `create` method returns a `Builder` which follow the [builder pattern](https://en.wikipedia.org/wiki/Builder_pattern). So you can store it or chain methods.

If you use an `id` already in use, the existing vein will be overwritten.

The obtained `Builder` is filled with the provided values (see below for associated methods), so you can directly call `build` (see below) to get a valid vein.

### Ending the process

```ZenScript
newVein.build();
```

Once you are done, you have to call `build` to "transform" the builder into a vein and register it.

### Available methods

#### Y

```ZenScript
newVein.y(int minY, int maxY)
```

- `minY` ∈ [1;255]
- `maxY` ∈ [1;255]
- `minY` < `maxY`

#### Size

```ZenScript
newVein.size(int sizeH, int sizeV)
```

Higher values will create a larger vein with more ores.

- `sizeH` ∈ [1;8]
- `sizeV` ∈ [1;8]

#### Chance

```ZenScript
newVein.chance(int chance)
```

Veins with high chance value will be generated more often.

- `chance` ∈ ]0;100]

#### Dimension

```ZenScript
newVein.dimension(int dimension)
```

Dimension where this vein will be generated.

#### Composition

```ZenScript
newVein.composition([Substance/string substance₁, int chance₁, Substance/string substance₂, int chance₂, [...], Substance/string substanceₙ, int chanceₙ])
```

- `Substance/string` can either be a substance (see how to get a substance) or a substance id.
- `chance` > 0

The `composition` method will determine which substances are present in the vein and in what quantity.

Adding a substance without calling `veinMember()` on the said substance will crash your game.

### Example: Create

```ZenScript
#loader craftorium

import mods.craftorium.vein.Veins;
import mods.craftorium.substance.Substances;
import mods.craftorium.vein.Builder;

var newVein = Veins.create("test", // ID
                    1, 50, // Y
                    5, 5, // Sizes
                    1000, // Chance to spawn
                    0, // The dimension id
                    [Substances.get("iron"), 3, "copper", 3, "lead", 3]) // The composition
                   .build(); // Finalizes the process
```