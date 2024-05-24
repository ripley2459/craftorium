package fr.cyrilneveu.craftorium.api.machine.behaviour;

import fr.cyrilneveu.craftorium.api.inventory.EnergySlotData;
import fr.cyrilneveu.craftorium.api.machine.MachineTile;
import fr.cyrilneveu.craftorium.api.utils.NBTUtils;
import fr.cyrilneveu.craftorium.api.utils.Position;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;

import static fr.cyrilneveu.craftorium.api.utils.NBTUtils.ENERGY_NBT_KEY;

public final class EnergyInventory implements IEnergyStorage, IMachineBehaviour, INBTSerializable<NBTTagCompound>, ICapabilityProvider {
    private final MachineTile owner;
    private final Position position;
    private int energy;
    private int capacity;
    private int transfer;

    public EnergyInventory(MachineTile owner, EnergySlotData slot) {
        this.owner = owner;
        this.capacity = (int) (slot.getCapacity() * owner.getTier().getStorage().getEnergyBuffer());
        this.transfer = (int) (slot.getTransfer() * owner.getTier().getStorage().getEnergyBuffer());
        this.position = slot.getPosition();
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
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == CapabilityEnergy.ENERGY;
    }

    @Nullable
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
        return hasCapability(capability, facing) ? CapabilityEnergy.ENERGY.cast(this) : null;
    }

    @Override
    public NBTTagCompound serializeNBT() {
        return NBTUtils.setValue(ENERGY_NBT_KEY, energy);
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        if (nbt.hasKey(ENERGY_NBT_KEY))
            energy = NBTUtils.getIntValue(ENERGY_NBT_KEY, nbt);
    }
}
