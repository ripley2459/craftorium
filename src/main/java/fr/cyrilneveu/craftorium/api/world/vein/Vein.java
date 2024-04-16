package fr.cyrilneveu.craftorium.api.world.vein;

import crafttweaker.annotations.ZenRegister;
import fr.cyrilneveu.craftorium.api.substance.Substance;
import fr.cyrilneveu.craftorium.api.utils.WeightedList;
import stanhebben.zenscript.annotations.ZenClass;

import static fr.cyrilneveu.craftorium.CraftoriumTags.MODID;

@ZenClass("mods." + MODID + ".vein.Vein")
@ZenRegister
public final class Vein {
    private final String name;
    private final int minY;
    private final int maxY;
    private final int sizeH;
    private final int sizeV;
    private final int chance;
    private final int dimension;
    private final WeightedList<Substance> substances;

    public Vein(String name, int minY, int maxY, int sizeH, int sizeV, int chance, int dimension, WeightedList<Substance> substances) {
        this.name = name;
        this.minY = minY;
        this.maxY = maxY;
        this.sizeH = sizeH;
        this.sizeV = sizeV;
        this.chance = chance;
        this.dimension = dimension;
        this.substances = substances;
    }

    public String getName() {
        return name;
    }

    public int getMinY() {
        return minY;
    }

    public int getMaxY() {
        return maxY;
    }

    public int getSizeH() {
        return sizeH;
    }

    public int getSizeV() {
        return sizeV;
    }

    public int getChance() {
        return chance;
    }

    public int getDimension() {
        return dimension;
    }

    public WeightedList<Substance> getSubstances() {
        return substances;
    }
}
