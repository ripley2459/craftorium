package fr.cyrilneveu.craftorium.api.inventory;

import fr.cyrilneveu.craftorium.api.utils.Utils;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.energy.IEnergyStorage;

import static fr.cyrilneveu.craftorium.api.utils.Utils.ENERGY_NBT_KEY;

public class CustomEnergyStorage implements IEnergyStorage, INBTSerializable<NBTTagCompound> {
    protected int energy;
    protected int capacity;
    protected int transfer;

    public CustomEnergyStorage(int energy, int capacity, int transfer) {
        this.energy = Math.min(energy, capacity);
        this.capacity = Math.max(0, capacity);
        this.transfer = Math.max(0, transfer);
    }

    @Override
    public int receiveEnergy(int amount, boolean simulate) {
        if (!canReceive())
            return 0;

        int energyReceived = Math.min(capacity - energy, Math.min(transfer, amount));
        if (!simulate)
            energy += energyReceived;

        return energyReceived;
    }

    @Override
    public int extractEnergy(int amount, boolean simulate) {
        if (!canExtract())
            return 0;

        int energyExtracted = Math.min(energy, Math.min(transfer, amount));
        if (!simulate)
            energy -= energyExtracted;

        return energyExtracted;
    }

    @Override
    public int getEnergyStored() {
        return energy;
    }

    @Override
    public int getMaxEnergyStored() {
        return capacity;
    }

    @Override
    public boolean canExtract() {
        return transfer > 0 && energy > 0;
    }

    @Override
    public boolean canReceive() {
        return transfer > 0 && energy < capacity;
    }

    @Override
    public NBTTagCompound serializeNBT() {
        return Utils.setValue(ENERGY_NBT_KEY, energy);
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        energy = Utils.getIntValue(ENERGY_NBT_KEY, nbt);
    }
}
