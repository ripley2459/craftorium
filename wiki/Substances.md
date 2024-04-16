# Substances

[CraftTweaker](https://www.curseforge.com/minecraft/mc-mods/crafttweaker) allows you to manipulate items, blocks and fluids registered by Craftorium.

**You have** to define a custom preprocessor:

```ZenScript
#loader craftorium
```

And importing some packages:

```ZenScript
import mods.craftorium.substance.Substance;
import mods.craftorium.substance.Builder;
```

With the `Substance` package you can list and delete substances. But also have access to information about a particular.

The `Builder` package gives you ways to create substances from scratch or modify existing ones.