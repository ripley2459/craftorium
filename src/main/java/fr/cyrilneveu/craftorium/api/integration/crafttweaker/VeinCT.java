package fr.cyrilneveu.craftorium.api.integration.crafttweaker;

import com.google.common.base.Preconditions;
import crafttweaker.annotations.ZenRegister;
import fr.cyrilneveu.craftorium.api.world.vein.Vein;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import static fr.cyrilneveu.craftorium.CraftoriumTags.MODID;
import static fr.cyrilneveu.craftorium.api.Registries.VEINS_REGISTRY;

@ZenClass("mods." + MODID + ".vein.Veins")
@ZenRegister
public final class VeinCT {
    @ZenMethod
    public static VeinBuilderCT create(String name, int minY, int maxY, int sizeH, int sizeV, int chance, int dimension, Object... composition) {
        Preconditions.checkArgument(!VEINS_REGISTRY.contains(name));

        return new VeinBuilderCT(name, minY, maxY, sizeH, sizeV, chance, dimension, composition);
    }

    @ZenMethod
    public static Vein get(String name) {
        return VEINS_REGISTRY.get(name);
    }
}
