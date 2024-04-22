package fr.cyrilneveu.craftorium.common.tier;

import fr.cyrilneveu.craftorium.api.tier.Tier;
import fr.cyrilneveu.craftorium.api.tier.TierBuilder;
import fr.cyrilneveu.craftorium.api.utils.Registry;
import stanhebben.zenscript.annotations.ZenMethod;

import static fr.cyrilneveu.craftorium.common.substance.Substances.*;

public final class Tiers {
    public static final Registry<String, Tier> TIERS_REGISTRY = new Registry<>();

    public static final Tier ONE = createTier("one")
            .storage(1.0f)
            .setFull()
            .pack(STEEL, STEEL, COPPER, BRONZE, COPPER)
            .color(0xFFdcdcdc)
            .build();
    public static final Tier TWO = createTier("two")
            .storage(2.0f)
            .setFull()
            .pack(ALUMINUM, HSLA_STEEL, ELECTRUM, BRONZE, CUPRONICKEL)
            .color(0xFFff6400)
            .build();
    public static final Tier THREE = createTier("three")
            .storage(4.0f)
            .simultaneousRecipe(2)
            .setFull()
            .pack(TUNGSTEN_STEEL, STAINLESS_STEEL, NICHROME, STAINLESS_STEEL, RTM_ALLOY)
            .color(0xFFffff1e)
            .build();
    public static final Tier FOUR = createTier("four")
            .storage(8.0f)
            .simultaneousRecipe(3)
            .setFull()
            .pack(HASTE_ALLOY, TUNGSTEN_CARBIDE_ALLOY, MAGNESIUM_DIBORIDE_ALLOY, TUNGSTEN_STEEL, BORON_ARSENIDE_ALLOY)
            .color(0xFF155f91)
            .build();
    public static final Tier FIVE = createTier("five")
            .storage(16.0f)
            .simultaneousRecipe(4)
            .setFull()
            .pack(SUPER_ALLOY, OSMIRIDIUM, YBCO, HASTE_ALLOY, GRAPHENE)
            .color(0xFFa979b5)
            .build();

    private static TierBuilder createTier(String name) {
        return new TierBuilder(name);
    }

    @ZenMethod
    public static Tier get(String name) {
        return TIERS_REGISTRY.get(name);
    }

    @ZenMethod
    public static boolean remove(String name) {
        return TIERS_REGISTRY.remove(name);
    }
}
