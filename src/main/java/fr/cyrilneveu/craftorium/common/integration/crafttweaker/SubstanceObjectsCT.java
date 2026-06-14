package fr.cyrilneveu.craftorium.common.integration.crafttweaker;

import crafttweaker.annotations.ZenRegister;
import fr.cyrilneveu.craftorium.api.substance.Substance;
import fr.cyrilneveu.craftorium.api.substance.object.ASubstanceObject;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import static fr.cyrilneveu.craftorium.CraftoriumTags.MODID;
import static fr.cyrilneveu.craftorium.api.Registries.*;

@ZenClass("mods." + MODID + ".substance.SubstanceObjects")
@ZenRegister
public final class SubstanceObjectsCT {
    @ZenMethod
    public static ASubstanceObject getItem(String name) {
        return SUBSTANCE_ITEMS_REGISTRY.get(name);
    }

    @ZenMethod
    public static ASubstanceObject getBlock(String name) {
        return SUBSTANCE_BLOCKS_REGISTRY.get(name);
    }

    @ZenMethod
    public static ASubstanceObject getTool(String name) {
        return SUBSTANCE_TOOLS_REGISTRY.get(name);
    }

    @ZenMethod
    public static ASubstanceObject getFluid(String name) {
        return SUBSTANCE_FLUIDS_REGISTRY.get(name);
    }
}
