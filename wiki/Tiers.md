# Tiers

[CraftTweaker](https://www.curseforge.com/minecraft/mc-mods/crafttweaker) allows you to manipulate tiered items, blocks and fluids registered by Craftorium.

**You have** to define a custom preprocessor:

```ZenScript
#loader craftorium
```

And importing some packages:

```ZenScript
import mods.craftorium.tier.Tier;
import mods.craftorium.tier.Builder;
```

With the `Tier` package you can list and delete tiers. But also have access to information about a particular.

The `Builder` package gives you ways to create tiers from scratch or modify existing ones.