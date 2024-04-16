# Veins

[CraftTweaker](https://www.curseforge.com/minecraft/mc-mods/crafttweaker) allows you to manipulate the Craftorium's ore generation.

**You have** to define a custom preprocessor:

```ZenScript
#loader craftorium
```

And importing some packages:

```ZenScript
import mods.craftorium.vein.Vein;
import mods.craftorium.vein.Builder;
```

With the `Vein` package you can list and delete veins. But also have access to information about a particular.

The `Builder` package gives you ways to create veins from scratch or modify existing ones.