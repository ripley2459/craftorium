# Substances

[CraftTweaker](https://www.curseforge.com/minecraft/mc-mods/crafttweaker) allows you to manipulate items, blocks and fluids registered by Craftorium.

**You have** to define a custom preprocessor:

```ZenScript
#loader craftorium
```

And import some packages:

```ZenScript
import mods.craftorium.substance.Substances;
import mods.craftorium.substance.Substance;
import mods.craftorium.substance.Builder;
```

With the `Substances` package you can manipulate the substances registry, such as creating, retrieving or registering a substance.

With the `Substance` package you can manipulate a particular substance.

The `Builder` package gives you ways to create substances from scratch.

## Get a substance

```ZenScript
var substance as Substance = Substances.get(string id);
```

## Get all substances

```ZenScript
var substance as Substance[] = Substances.getAll();
```

This can be a rough operation so use it with caution.

## Substance creation

### Initializing the process

To start the process of creating a substance you have to call `create` from the `Builder` package.

```ZenScript
var newSubstance as Builder = Substances.create(string id);
```

The `create` method returns a `Builder` which follow the [builder pattern](https://en.wikipedia.org/wiki/Builder_pattern). So you can store it or chain methods.

If you use an `id` already in use, the existing substance will be overwritten. (**You can't override the default substances.**)

The obtained `Builder` is totally empty.

### Ending the process

```ZenScript
var mySubstance as Substance = newSubstance.build();
```

Once you are done, you have to call `build` to "transform" the builder into a substance and register it.

### Available methods

#### Composition

```ZenScript
newSubstance.composition([Substance/string substance₁, int amount₁, Substance/string substance₂, int amount₂, [...], Substance/string substanceₙ, int amountₙ]);
```

The `composition` method will determine which substances are present in the main substance and in what quantity.

- `Substance/string` can either be a substance (see how to get a substance) or a substance id.
- `amount` > 0

#### Possible

```ZenScript
newSubstance.possible([Substance/string substance₁, int amount₁, int chance₁, Substance/string substance₂, int amount₂, int chance₂, [...], Substance/string substanceₙ, int amountₙ, int chanceₙ]);
```

The `possible` method will determine which substances may be present in the main substance, in what quantity and at what frequency.

- `Substance/string` can either be a substance (see how to get a substance) or a substance id.
- `amount` > 0
- `chance` ∈ ]0;100]

#### Package

```ZenScript
newSubstance.packageMetalExtended();
```

These methods are macros to tell the substances which items, blocks, tools and fluids to register. In fact, it's like calling `items`, `tools`, `blocks` or `fluids` (see bellow).

<details>
  <summary><b>Packages possible values</b></summary>

##### Items

| Package                    | "casing" | "dust" | "foil" | "gear" | "gem" | "ingot" | "nugget" | "pearl" | "plate" | "ring" | "rod" | "rotor" | "screw" | "spring" | "wire" |
|----------------------------|----------|--------|--------|--------|-------|---------|----------|---------|---------|--------|-------|---------|---------|----------|--------|
| packageOre                 |          | ✔      |        |        |       |         |          |         |         |        |       |         |         |          |        |
| packageHalogen             |          | ✔      |        |        |       |         |          |         |         |        |       |         |         |          |        |
| packageNobleGas            |          | ✔      |        |        |       |         |          |         |         |        |       |         |         |          |        |
| packageAlkaliMetal         |          | ✔      |        |        |       | ✔       | ✔        |         |         |        |       |         |         |          |        |
| packageAlkalineEarthMetal  |          | ✔      |        |        |       | ✔       | ✔        |         |         |        |       |         |         |          |        |
| packageMetalloid           |          | ✔      |        |        |       | ✔       | ✔        |         |         |        |       |         |         |          |        |
| packageNonMetal            |          | ✔      |        |        |       |         |          |         |         |        |       |         |         |          |        |
| packagePostTransitionMetal | ✔        | ✔      | ✔      | ✔      |       | ✔       | ✔        |         | ✔       | ✔      | ✔     |         | ✔       | ✔        | ✔      |
| packageTransitionMetal     | ✔        | ✔      | ✔      | ✔      |       | ✔       | ✔        |         | ✔       | ✔      | ✔     |         | ✔       | ✔        | ✔      |
| packageLanthanide          |          | ✔      |        |        |       | ✔       | ✔        |         | ✔       |        | ✔     |         |         |          |        |
| packageActinide            |          | ✔      |        |        |       | ✔       | ✔        |         | ✔       |        | ✔     |         |         |          |        |
| packageMetalExtended       | ✔        | ✔      | ✔      | ✔      |       | ✔       | ✔        |         | ✔       | ✔      | ✔     | ✔       | ✔       | ✔        | ✔      |
| packageGem                 | ✔        | ✔      | ✔      | ✔      | ✔     |         | ✔        |         | ✔       |        | ✔     |         |         |          |        |
| packageMineral             |          | ✔      |        |        |       |         |          |         | ✔       |        | ✔     |         |         |          |        |

##### Tools

| Package                    | "axe" | "cutter" | "file" | "hammer" | "hoe" | "knife" | "mortar" | "pickaxe" | "saw" | "screwdriver" | "shovel" | "sword" | "wrench" |
|----------------------------|-------|----------|--------|----------|-------|---------|----------|-----------|-------|---------------|----------|---------|----------|
| packageOre                 |       |          |        |          |       |         |          |           |       |               |          |         |          |
| packageHalogen             |       |          |        |          |       |         |          |           |       |               |          |         |          |
| packageNobleGas            |       |          |        |          |       |         |          |           |       |               |          |         |          |
| packageAlkaliMetal         |       |          |        |          |       |         |          |           |       |               |          |         |          |
| packageAlkalineEarthMetal  |       |          |        |          |       |         |          |           |       |               |          |         |          |
| packageMetalloid           |       |          |        |          |       |         |          |           |       |               |          |         |          |
| packageNonMetal            |       |          |        |          |       |         |          |           |       |               |          |         |          |
| packagePostTransitionMetal |       |          |        |          |       |         |          |           |       |               |          |         |          |
| packageTransitionMetal     | ✔     | ✔        | ✔      | ✔        | ✔     | ✔       | ✔        | ✔         | ✔     | ✔             | ✔        | ✔       | ✔        |
| packageLanthanide          |       |          |        |          |       |         |          |           |       |               |          |         |          |
| packageActinide            |       |          |        |          |       |         |          |           |       |               |          |         |          |
| packageMetalExtended       | ✔     | ✔        | ✔      | ✔        | ✔     | ✔       | ✔        | ✔         | ✔     | ✔             | ✔        | ✔       | ✔        |
| packageGem                 | ✔     |          | ✔      | ✔        | ✔     | ✔       | ✔        | ✔         | ✔     |               | ✔        | ✔       | ✔        |
| packageMineral             |       |          |        |          |       |         |          |           |       |               |          |         |          |

##### Blocks

| Package                    | "block" | "frame" | "hull" | "ore" |
|----------------------------|---------|---------|--------|-------|
| packageOre                 |         |         |        | ✔     |
| packageHalogen             |         |         |        |       |
| packageNobleGas            |         |         |        |       |
| packageAlkaliMetal         | ✔       |         |        |       |
| packageAlkalineEarthMetal  | ✔       |         |        |       |
| packageMetalloid           | ✔       |         |        |       |
| packageNonMetal            |         |         |        |       |
| packagePostTransitionMetal | ✔       | ✔       | ✔      |       |
| packageTransitionMetal     | ✔       | ✔       | ✔      |       |
| packageLanthanide          | ✔       |         |        |       |
| packageActinide            | ✔       |         |        |       |
| packageMetalExtended       | ✔       | ✔       | ✔      |       |
| packageGem                 | ✔       | ✔       | ✔      |       |
| packageMineral             | ✔       |         |        |       |

##### Fluids

| Package                    | "liquid" |
|----------------------------|----------|
| packageOre                 |          |
| packageHalogen             | ✔        |
| packageNobleGas            | ✔        |
| packageAlkaliMetal         | ✔        |
| packageAlkalineEarthMetal  | ✔        |
| packageMetalloid           | ✔        |
| packageNonMetal            | ✔        |
| packagePostTransitionMetal | ✔        |
| packageTransitionMetal     | ✔        |
| packageLanthanide          | ✔        |
| packageActinide            | ✔        |
| packageMetalExtended       | ✔        |
| packageGem                 | ✔        |
| packageMineral             | ✔        |

</details>

#### Items, blocks, fluids and tools

```ZenScript
newSubstance.items([String item₁, String item₂, [...], String itemₙ]);
newSubstance.blocks([String block₁, String block₂, [...], String blockₙ]);
newSubstance.fluids([String fluid₁, String fluid₂, [...], String fluidₙ]);
newSubstance.tools([String tool₁, String tool₂, [...], String toolₙ]);
```

These methods will tell the substances which items, blocks, tools and fluids to register.

<details>
  <summary><b>Items possible values</b></summary>

- "casing"
- "dust"
- "foil"
- "gear"
- "gem"
- "ingot"
- "nugget"
- "pearl"
- "plate"
- "ring"
- "rod"
- "rotor"
- "screw"
- "spring"
- "wire"

</details>

<details>
  <summary><b>Tools possible values</b></summary>

- "axe"
- "cutter"
- "file"
- "hammer"
- "hoe"
- "knife"
- "mortar"
- "pickaxe"
- "saw"
- "screwdriver"
- "shovel"
- "sword"
- "wrench"

</details>

<details>
  <summary><b>Blocks possible values</b></summary>

- "hull"
- "frame"
- "ore"
- "block"

</details>

<details>
  <summary><b>Fluids possible values</b></summary>

- "liquid"

</details>

**Calling these methods with without parameters will remove the currently present objects.**

**Providing tools without giving their stats with the eponymous method will not register any tools.**

**If a substance is registered inside at least one vein, its mandatory to call `veinMember` (see below).**

#### Vein Member

```ZenScript
newSubstance.veinMember();
```

Ensures that the substance can be integrated in a vein and used during the world generation process.

#### Tools stats

```ZenScript
newSubstance.tools(float speed, float damage, int durability, int harvestLevel, int enchantability);
```

**Providing stats without giving the substance a list of tools with the eponymous method will not register any tools.**

#### Toughness

```ZenScript
newSubstance.toughness(float hardness, float resistance, String toolClass, int harvestLevel);
```

Indicates blocks hardness and resistances values as well which tool class is really effective to break this substance's blocks and the minimal level required.

Default: `5.0f, 10.0f, "pickaxe", 2`.

#### Temperature

```ZenScript
newSubstance.temperature(float meltingPoint, float boilingPoint);
```

- `meltingPoint` >= 0
- `boilingPoint` >= 0

```ZenScript
newSubstance.temperatureAverage();
```

Try to give average temperatures based on the composition.

Default: if no temperatures are provided, `temperatureAverage` will be called.

#### Color

```ZenScript
newSubstance.color(int base); // Will call "newSubstance.color(base, base, base);"
newSubstance.color(int base, int ore, int fluid);
```

```ZenScript
newSubstance.colorAverage();
```

Try to give an average color based on the composition.

Default: if no color is provided, `colorAverage` will be called to generate the color based on the composition. A pink color (0xFFff00ff) is the ultimate fallback.

#### Style

The `style` method will tell to the substance where to search for its assets.

```ZenScript
newSubstance.style(string style);
```

<details>
  <summary><b>Style possible values</b></summary>

**You can indicate your own style, but in this case you must provide a resource pack.**

- "gem"
- "metal"
- "mineral"
- "wood"

</details>

Default: `metal`.

#### Sound

```ZenScript
newSubstance.sound(string soundName);
```

<details>
  <summary><b>Sounds possible values</b></summary>

- "metal"
- "wood"
- "stone"
- "sand"

</details>

Default: `metal`.

#### Shiny

```ZenScript
newSubstance.shiny();
```

Call `shiny` to make the substance with a semi-transparent white layer.

Default: `false`.

#### Glint

```ZenScript
newSubstance.glint();
```

Call `glint` to make the substance with an enchantment effect.

Default: `false`.

#### Element

```ZenScript
newSubstance.element(int atomicNumber, String symbol, String name, String group, double atomicMass)
```

You can define your substance as an element. In that case, the composition and possible must be undefined.

<details>
  <summary><b>Group possible values</b></summary>

- "actinide",
- "alkaline_earth_metal",
- "alkali_metal",
- "halogen",
- "lanthanide",
- "metalloid",
- "noble_gas",
- "non_metal",
- "post_transition_metal",
- "transition_metal",
- "unknown"

</details>

### Example: Create

```ZenScript
#loader craftorium

import mods.craftorium.substance.Substances;
import mods.craftorium.substance.Substance; // Not required here
import mods.craftorium.substance.Builder;

var silicon as Substance = Substances.get("silicon");
var copper = Substances.get("copper");

var test = Substances.create("test_substance") // Starting the process
                     .composition([silicon, 3, "electrum", 1]) // Defining a composition
                     .possible([copper, 3, 20, "obsidian", 1, 5]) // And byproducts
                     .packageMetalExtended() // Adds a lot of things
                     .tools([]) // Remove every tools
                     .tools(["wrench"]) // But re-add the wrench
                     .tools(5.0f, 2.0f, 500, 3, 5) // Defines the tools properties
                     .style("metal") // Gives the substance a style
                     .shiny() // Makes it shiny (white layer over the texture)
                     .build(); // And finalizes the process
```