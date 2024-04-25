package fr.cyrilneveu.craftorium.common.tier;

import fr.cyrilneveu.craftorium.api.tier.Tier;
import fr.cyrilneveu.craftorium.api.tier.TierBuilder;
import stanhebben.zenscript.annotations.ZenMethod;

import static fr.cyrilneveu.craftorium.api.Registries.TIERS_REGISTRY;
import static fr.cyrilneveu.craftorium.common.substance.Substances.*;

public final class Tiers {
    public static Tier ONE;
    public static Tier TWO;
    public static Tier THREE;
    public static Tier FOUR;
    public static Tier FIVE;

    public static void init() {
        if (TIERS_REGISTRY.isInitialized())
            return;

        TIERS_REGISTRY.initialize();

        ONE = createTier("one")
                .storage(1.0f)
                .setFull()
                .pack(STEEL, STEEL, COPPER, BRONZE, COPPER)
                .color(0xFFdcdcdc)
                .build();
        TWO = createTier("two")
                .storage(2.0f)
                .setFull()
                .pack(ALUMINUM, HSLA_STEEL, ELECTRUM, BRONZE, CUPRONICKEL)
                .color(0xFFff6400)
                .build();
        THREE = createTier("three")
                .storage(4.0f)
                .simultaneousRecipe(2)
                .setFull()
                .pack(TUNGSTEN_STEEL, STAINLESS_STEEL, NICHROME, STAINLESS_STEEL, RTM_ALLOY)
                .color(0xFFffff1e)
                .build();
        FOUR = createTier("four")
                .storage(8.0f)
                .simultaneousRecipe(3)
                .setFull()
                .pack(HASTE_ALLOY, TUNGSTEN_CARBIDE_ALLOY, MAGNESIUM_DIBORIDE_ALLOY, TUNGSTEN_STEEL, BORON_ARSENIDE_ALLOY)
                .color(0xFF155f91)
                .build();
        FIVE = createTier("five")
                .storage(16.0f)
                .simultaneousRecipe(4)
                .setFull()
                .pack(SUPER_ALLOY, OSMIRIDIUM, YBCO, HASTE_ALLOY, GRAPHENE)
                .color(0xFFa979b5)
                .build();
    }

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

    public static void close() {
        TIERS_REGISTRY.close();
    }
}
