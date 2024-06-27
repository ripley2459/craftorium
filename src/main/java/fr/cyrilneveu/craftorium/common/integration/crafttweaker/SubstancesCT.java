package fr.cyrilneveu.craftorium.common.integration.crafttweaker;

import com.google.common.base.Preconditions;
import crafttweaker.annotations.ZenRegister;
import fr.cyrilneveu.craftorium.api.substance.Substance;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import static fr.cyrilneveu.craftorium.CraftoriumTags.MODID;
import static fr.cyrilneveu.craftorium.api.Registries.SUBSTANCES_REGISTRY;

@ZenClass("mods." + MODID + ".substance.Substances")
@ZenRegister
public final class SubstancesCT {
    @ZenMethod
    public static SubstanceBuilderCT create(String name) {
        Preconditions.checkArgument(!SUBSTANCES_REGISTRY.contains(name));

        return new SubstanceBuilderCT(name);
    }

    @ZenMethod
    public static Substance get(String name) {
        return SUBSTANCES_REGISTRY.get(name);
    }

    @ZenMethod
    public static SubstanceCT[] getAll() {
        return SUBSTANCES_REGISTRY.getAll().values().stream().map(SubstanceCT::new).toArray(SubstanceCT[]::new);
    }
}
