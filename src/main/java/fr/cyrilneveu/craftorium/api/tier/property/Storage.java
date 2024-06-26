package fr.cyrilneveu.craftorium.api.tier.property;

public final class Storage {
    private final float tankSize, tankIO, energyBuffer, energyIO;

    public Storage(float multiplier) {
        this(multiplier, multiplier, multiplier, multiplier);
    }

    public Storage(float tankSize, float tankIO, float energyBuffer, float energyIO) {
        this.tankSize = tankSize;
        this.tankIO = tankIO;
        this.energyBuffer = energyBuffer;
        this.energyIO = energyIO;
    }

    public float getTankSize() {
        return tankSize;
    }

    public float getTankIO() {
        return tankIO;
    }

    public float getEnergyBuffer() {
        return energyBuffer;
    }

    public float getEnergyIO() {
        return energyIO;
    }
}
