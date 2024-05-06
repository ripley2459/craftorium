package fr.cyrilneveu.craftorium.api.inventory;

import fr.cyrilneveu.craftorium.api.utils.Utils;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.energy.CapabilityEnergy;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

import static fr.cyrilneveu.craftorium.api.utils.Utils.ENERGY_NBT_KEY;

public class ItemEnergyStorageBehaviour implements ICapabilityProvider {
    protected final CustomEnergyStorage energyStorage;

    public ItemEnergyStorageBehaviour(ItemStack stack, int capacity, int transfer) {
        this.energyStorage = new CustomEnergyStorage(0, capacity, transfer) {
            @Override
            public int receiveEnergy(int amount, boolean simulate) {
                if (!canReceive())
                    return 0;

                energy = Utils.getIntValue(ENERGY_NBT_KEY, stack);
                int received = Math.min(capacity - energy, Math.min(transfer, amount));
                if (!simulate)
                    energy += received;
                serializeNBT();

                return received;
            }

            @Override
            public int extractEnergy(int amount, boolean simulate) {
                if (!canExtract())
                    return 0;

                energy = Utils.getIntValue(ENERGY_NBT_KEY, stack);
                int extracted = Math.min(energy, Math.min(amount, transfer));
                if (!simulate)
                    energy -= extracted;
                serializeNBT();

                return extracted;
            }

            @Override
            public int getEnergyStored() {
                return Utils.getIntValue(ENERGY_NBT_KEY, stack);
            }

            @Override
            public boolean canExtract() {
                return transfer > 0;
            }

            @Override
            public boolean canReceive() {
                return transfer > 0;
            }

            @Override
            public NBTTagCompound serializeNBT() {
                return Utils.setValue(ENERGY_NBT_KEY, energy, stack);
            }

            @Override
            public void deserializeNBT(NBTTagCompound nbt) {
                energy = Utils.getIntValue(ENERGY_NBT_KEY, stack);
            }
        };
    }

    @Override
    public boolean hasCapability(@NotNull Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == CapabilityEnergy.ENERGY;
    }

    @Nullable
    @Override
    public <T> T getCapability(@NotNull Capability<T> capability, @Nullable EnumFacing facing) {
        return hasCapability(capability, facing) ? CapabilityEnergy.ENERGY.cast(energyStorage) : null;
    }
}
