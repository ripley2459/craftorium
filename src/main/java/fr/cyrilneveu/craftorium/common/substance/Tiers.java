package fr.cyrilneveu.craftorium.common.substance;

import fr.cyrilneveu.craftorium.api.substance.SubstanceBuilder;
import fr.cyrilneveu.craftorium.api.substance.Tier;

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

        ONE = (Tier) new SubstanceBuilder("one")
                .isTier()
                .fluidStorage(8.0f)
                .energyStorage(8.0f, 8.0f)
                .recipeSpeedMultiplier(12.0f)
                .pack(STEEL, STEEL, COPPER, BRONZE, COPPER)
                .packageTier()
                .color(0xFFdcdcdc)
                .build();
        TWO = (Tier) new SubstanceBuilder("two")
                .isTier()
                .fluidStorage(8.0f)
                .energyStorage(8.0f, 8.0f)
                .recipeSpeedMultiplier(12.0f)
                .pack(ALUMINUM, HSLA_STEEL, ELECTRUM, BRONZE, CUPRONICKEL)
                .packageTier()
                .color(0xFFff6400)
                .build();
        THREE = (Tier) new SubstanceBuilder("three")
                .isTier()
                .fluidStorage(8.0f)
                .energyStorage(8.0f, 8.0f)
                .recipeSpeedMultiplier(12.0f)
                .pack(TUNGSTEN_STEEL, STAINLESS_STEEL, NICHROME, STAINLESS_STEEL, RTM_ALLOY)
                .packageTier()
                .color(0xFFffff1e)
                .build();
        FOUR = (Tier) new SubstanceBuilder("four")
                .isTier()
                .fluidStorage(8.0f)
                .energyStorage(8.0f, 8.0f)
                .recipeSpeedMultiplier(12.0f)
                .pack(HASTE_ALLOY, TUNGSTEN_CARBIDE_ALLOY, MAGNESIUM_DIBORIDE_ALLOY, TUNGSTEN_STEEL, BORON_ARSENIDE_ALLOY)
                .packageTier()
                .color(0xFF155f91)
                .build();
        FIVE = (Tier) new SubstanceBuilder("five")
                .isTier()
                .fluidStorage(16.0f)
                .energyStorage(16.0f, 16.0f)
                .recipeSpeedMultiplier(24.0f)
                .pack(SUPER_ALLOY, OSMIRIDIUM, YBCO, HASTE_ALLOY, GRAPHENE)
                .packageTier()
                .color(0xFFa979b5)
                .build();
    }

    public static void close() {
        TIERS_REGISTRY.close();
    }
}
