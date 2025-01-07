package fr.cyrilneveu.craftorium.common.integration.crafttweaker;

import crafttweaker.annotations.ZenRegister;
import fr.cyrilneveu.craftorium.api.substance.Substance;
import fr.cyrilneveu.craftorium.api.world.vein.Vein;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import java.util.Map;

import static fr.cyrilneveu.craftorium.CraftoriumTags.MODID;

@ZenClass("mods." + MODID + ".vein.Vein")
@ZenRegister
public final class VeinCT {
    private final Vein vein;

    public VeinCT(Vein vein) {
        this.vein = vein;
    }

    @ZenMethod
    public String getName() {
        return vein.getName();
    }

    @ZenMethod
    public int getMinY() {
        return vein.getMinY();
    }

    @ZenMethod
    public int getMaxY() {
        return vein.getMaxY();
    }

    @ZenMethod
    public int getSizeH() {
        return vein.getSizeH();
    }

    @ZenMethod
    public int getSizeV() {
        return vein.getSizeV();
    }

    @ZenMethod
    public int getChance() {
        return vein.getChance();
    }

    @ZenMethod
    public int getDimension() {
        return vein.getDimension();
    }

    @ZenMethod
    public int getColor() {
        int color = 0;
        int total = 0;

        for (Map.Entry<Substance, Integer> entry : vein.getComposition().map().entrySet()) {
            color += entry.getKey().getAestheticism().getBaseColor() * entry.getValue();
            total += entry.getValue();
        }

        return color / total;
    }
}
