package fr.cyrilneveu.craftorium.api.tier.property;

public final class Process {
    private final int simultaneous, chance;
    private final float speed;

    public Process(int simultaneous, int chance, float speed) {
        this.simultaneous = simultaneous;
        this.chance = chance;
        this.speed = speed;
    }

    public int getSimultaneous() {
        return simultaneous;
    }

    public int getChance() {
        return chance;
    }

    public float getSpeed() {
        return speed;
    }
}
