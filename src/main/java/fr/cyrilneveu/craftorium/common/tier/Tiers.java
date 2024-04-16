package fr.cyrilneveu.craftorium.common.tier;

import fr.cyrilneveu.craftorium.api.tier.Tier;
import fr.cyrilneveu.craftorium.api.tier.TierBuilder;
import fr.cyrilneveu.craftorium.api.utils.Registry;
import stanhebben.zenscript.annotations.ZenMethod;

public final class Tiers {
    public static final Registry<String, Tier> TIERS_REGISTRY = new Registry<>();

    public static final Tier ONE = createTier("one")
            .setFull()
            .color(0xFFdcdcdc)
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
