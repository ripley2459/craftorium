package fr.cyrilneveu.craftorium.common.integration.crafttweaker;

import com.google.common.base.Preconditions;
import crafttweaker.annotations.ZenRegister;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import static fr.cyrilneveu.craftorium.CraftoriumTags.MODID;
import static fr.cyrilneveu.craftorium.api.Registries.VEINS_REGISTRY;

@ZenClass("mods." + MODID + ".vein.Veins")
@ZenRegister
public final class VeinsCT {
    @ZenMethod
    public static VeinBuilderCT create(String name, int minY, int maxY, int sizeH, int sizeV, int chance, int dimension, Object... composition) {
        Preconditions.checkArgument(!VEINS_REGISTRY.contains(name));

        return new VeinBuilderCT(name, minY, maxY, sizeH, sizeV, chance, dimension, composition);
    }

    @ZenMethod
    public static VeinCT get(String name) {
        return new VeinCT(VEINS_REGISTRY.get(name));
    }

    @ZenMethod
    public static VeinCT[] getAll() {
        return VEINS_REGISTRY.getAll().values().stream().map(VeinCT::new).toArray(VeinCT[]::new);
    }
}
