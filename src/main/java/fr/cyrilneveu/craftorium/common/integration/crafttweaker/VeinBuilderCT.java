package fr.cyrilneveu.craftorium.common.integration.crafttweaker;

import crafttweaker.annotations.ZenRegister;
import fr.cyrilneveu.craftorium.api.world.vein.Vein;
import fr.cyrilneveu.craftorium.api.world.vein.VeinBuilder;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import static fr.cyrilneveu.craftorium.CraftoriumTags.MODID;

@ZenClass("mods." + MODID + ".vein.Builder")
@ZenRegister
public final class VeinBuilderCT {
    VeinBuilder builder;

    public VeinBuilderCT(String name, int minY, int maxY, int sizeH, int sizeV, int chance, int dimension, Object... composition) {
        this.builder = new VeinBuilder(name, minY, maxY, sizeH, sizeV, chance, dimension, composition);
    }

    @ZenMethod
    public VeinBuilderCT y(int minY, int maxY) {
        builder.y(minY, maxY);
        return this;
    }

    @ZenMethod
    public VeinBuilderCT size(int sizeH, int sizeV) {
        builder.size(sizeH, sizeV);
        return this;
    }

    @ZenMethod
    public VeinBuilderCT chance(int chance) {
        builder.chance(chance);
        return this;
    }

    @ZenMethod
    public VeinBuilderCT dimension(int dimension) {
        builder.dimension(dimension);
        return this;
    }

    @ZenMethod
    public VeinBuilderCT composition(Object... composition) {
        builder.composition(composition);
        return this;
    }

    @ZenMethod
    public Vein build() {
        return builder.build();
    }
}
