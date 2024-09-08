package fr.cyrilneveu.craftorium.api.item.behaviour;

import fr.cyrilneveu.craftorium.api.inventory.CustomEnergyStorage;
import fr.cyrilneveu.craftorium.api.utils.NBTUtils;
import fr.cyrilneveu.craftorium.api.utils.Utils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

import static fr.cyrilneveu.craftorium.api.utils.NBTUtils.ENERGY_NBT_KEY;

public final class ItemEnergyStorageBehaviour implements IItemBehaviour, ICapableItem {
    private final int capacity;
    private final int transfer;
    private final CustomEnergyStorage energyStorage;

    public ItemEnergyStorageBehaviour(ItemStack stack, int capacity, int transfer) {
        this.capacity = capacity;
        this.transfer = transfer;
        this.energyStorage = new CustomEnergyStorage(0, capacity, transfer) {
            @Override
            public int receiveEnergy(int amount, boolean simulate) {
                if (!canReceive())
                    return 0;

                energy = NBTUtils.getIntValue(ENERGY_NBT_KEY, stack);
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

                energy = NBTUtils.getIntValue(ENERGY_NBT_KEY, stack);
                int extracted = Math.min(energy, Math.min(amount, transfer));
                if (!simulate)
                    energy -= extracted;
                serializeNBT();

                return extracted;
            }

            @Override
            public int getEnergyStored() {
                return NBTUtils.getIntValue(ENERGY_NBT_KEY, stack);
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
                return NBTUtils.setValue(ENERGY_NBT_KEY, energy, stack);
            }

            @Override
            public void deserializeNBT(NBTTagCompound nbt) {
                energy = NBTUtils.getIntValue(ENERGY_NBT_KEY, stack);
            }
        };
    }

    public static int getEnergy(ItemStack itemStack) {
        return itemStack.hasCapability(CapabilityEnergy.ENERGY, null) ? itemStack.getCapability(CapabilityEnergy.ENERGY, null).getEnergyStored() : -1;
    }

    public static int getMaxEnergy(ItemStack itemStack) {
        return itemStack.hasCapability(CapabilityEnergy.ENERGY, null) ? itemStack.getCapability(CapabilityEnergy.ENERGY, null).getMaxEnergyStored() : -1;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemStack, @Nullable World world, List<String> tooltips, ITooltipFlag flag) {
        if (getMaxEnergy(itemStack) > 0)
            tooltips.add(Utils.localise("tooltip.craftorium.battery.energy", getEnergy(itemStack), getMaxEnergy(itemStack)));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean showDurabilityBar(@Nonnull ItemStack stack) {
        return getEnergy(stack) > 0;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public double getDurabilityForDisplay(@Nonnull ItemStack stack) {
        double max = getMaxEnergy(stack);
        return (max - getEnergy(stack)) / max;
    }

    @Override
    public ICapabilityProvider initCapabilities(@Nonnull ItemStack stack, @Nullable NBTTagCompound nbt) {
        return new ItemEnergyStorageBehaviour(stack, capacity, transfer);
    }

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == CapabilityEnergy.ENERGY;
    }

    @Nullable
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
        return hasCapability(capability, facing) ? CapabilityEnergy.ENERGY.cast(energyStorage) : null;
    }
}
