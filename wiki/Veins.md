# Veins

[CraftTweaker](https://www.curseforge.com/minecraft/mc-mods/crafttweaker) allows you to manipulate the Craftorium's ore generation.

**You have** to define a custom preprocessor:

```ZenScript
#loader craftorium
```

And importing some packages:

```ZenScript
import mods.craftorium.vein.Veins;
import mods.craftorium.vein.Builder;
```

With the `Veins` package you can list and delete veins. But also have access to information about a particular.

The `Builder` package gives you ways to create veins from scratch or modify existing ones.

## Vein creation

### Initializing the process

To start the process of creating a vein you have to call `createVein` from the `Builder` package.

```ZenScript
var newVein = Builder.createVein(String id, int minY, int maxY, int sizeH, int sizeV, int chance, int dimension, [substance₁, chance₁, substance₂, chance₂, [...], substanceₙ, chanceₙ]);
```

The `createVein` method returns a `Builder` which follow the [builder pattern](https://en.wikipedia.org/wiki/Builder_pattern). So you can store it or chain methods.

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

#### Size

```ZenScript
newVein.size(int sizeH, int sizeV)
```

Higher values will create a larger vein with more ores.

- `sizeH` ∈ [1;32]
- `sizeV` ∈ [1;32]

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

#### AddSubstances

```ZenScript
newVein.addSubstances([Substance/string substance₁, int chance₁, Substance/string substance₂, int chance₂, [...], Substance/string substanceₙ, int chanceₙ])
```

The `addSubstances` method will determine which substances are present in the vein and in what quantity.

### Example of creating a vein

```ZenScript
#loader craftorium

import mods.craftorium.vein.Veins;
import mods.craftorium.substance.Substances;
import mods.craftorium.vein.Builder;

var newVein = Builder.createVein("test",
                      1, 50,
                      5, 5,
                      1000,
                      0,
                      [Substances.get("iron"), 3, "copper", 3, "lead", 3])
                     .build();
```