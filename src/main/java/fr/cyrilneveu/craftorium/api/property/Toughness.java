package fr.cyrilneveu.craftorium.api.property;

public final class Toughness {
    private final float hardness, resistance;
    private final String toolClass;
    private final int harvestLevel;

    public Toughness(float hardness, float resistance, String toolClass, int harvestLevel) {
        this.hardness = hardness;
        this.resistance = resistance;
        this.toolClass = toolClass;
        this.harvestLevel = harvestLevel;
    }

    public float getHardness() {
        return hardness;
    }

    public float getResistance() {
        return resistance;
    }

    public String getToolClass() {
        return toolClass;
    }

    public int getHarvestLevel() {
        return harvestLevel;
    }
}
