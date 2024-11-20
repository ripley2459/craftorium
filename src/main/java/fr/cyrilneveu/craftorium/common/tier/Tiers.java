package fr.cyrilneveu.craftorium.common.tier;

import fr.cyrilneveu.craftorium.api.tier.Tier;
import fr.cyrilneveu.craftorium.api.tier.TierBuilder;

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

        ONE = new TierBuilder("one")
                .storage(1.0f)
                .packageFull()
                .pack(STEEL, STEEL, COPPER, BRONZE, COPPER)
                .color(0xFFdcdcdc)
                .build();
        TWO = new TierBuilder("two")
                .storage(2.0f)
                .recipeSpeed(3.0f)
                .packageFull()
                .pack(ALUMINUM, HSLA_STEEL, ELECTRUM, BRONZE, CUPRONICKEL)
                .color(0xFFff6400)
                .build();
        THREE = new TierBuilder("three")
                .storage(4.0f)
                .recipeSpeed(6.0f)
                .packageFull()
                .pack(TUNGSTEN_STEEL, STAINLESS_STEEL, NICHROME, STAINLESS_STEEL, RTM_ALLOY)
                .color(0xFFffff1e)
                .build();
        FOUR = new TierBuilder("four")
                .storage(8.0f)
                .recipeSpeed(12.0f)
                .packageFull()
                .pack(HASTE_ALLOY, TUNGSTEN_CARBIDE_ALLOY, MAGNESIUM_DIBORIDE_ALLOY, TUNGSTEN_STEEL, BORON_ARSENIDE_ALLOY)
                .color(0xFF155f91)
                .build();
        FIVE = new TierBuilder("five")
                .storage(16.0f)
                .recipeSpeed(24.0f)
                .packageFull()
                .pack(SUPER_ALLOY, OSMIRIDIUM, YBCO, HASTE_ALLOY, GRAPHENE)
                .color(0xFFa979b5)
                .build();
    }

    public static void close() {
        TIERS_REGISTRY.close();
    }
}
