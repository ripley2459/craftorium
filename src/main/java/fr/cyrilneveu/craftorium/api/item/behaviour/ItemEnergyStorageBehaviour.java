package fr.cyrilneveu.craftorium.api.item.behaviour;

import fr.cyrilneveu.craftorium.api.inventory.CustomEnergyStorage;
import fr.cyrilneveu.craftorium.api.item.CustomItem;
import fr.cyrilneveu.craftorium.api.utils.ICapableItem;
import fr.cyrilneveu.craftorium.api.utils.IItemBehaviour;
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
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

import static fr.cyrilneveu.craftorium.api.utils.Utils.ENERGY_NBT_KEY;

public class ItemEnergyStorageBehaviour implements IItemBehaviour, ICapableItem {
    protected final int capacity;
    protected final int transfer;
    protected final CustomEnergyStorage energyStorage;

    public ItemEnergyStorageBehaviour(ItemStack stack, int capacity, int transfer) {
        this.capacity = capacity;
        this.transfer = transfer;
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
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemStack, @Nullable World world, List<String> tooltips, ITooltipFlag flag) {
        if (CustomItem.getMaxEnergy(itemStack) > 0)
            tooltips.add(Utils.localise("tooltip.craftorium.battery.energy", CustomItem.getEnergy(itemStack), CustomItem.getMaxEnergy(itemStack)));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean showDurabilityBar(@Nonnull ItemStack itemStack) {
        return CustomItem.getEnergy(itemStack) > 0;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public double getDurabilityForDisplay(@Nonnull ItemStack itemStack) {
        double max = CustomItem.getMaxEnergy(itemStack);
        return (max - CustomItem.getEnergy(itemStack)) / max;
    }

    @Override
    public ICapabilityProvider initCapabilities(@Nonnull ItemStack stack, @Nullable NBTTagCompound nbt) {
        return new ItemEnergyStorageBehaviour(stack, capacity, transfer);
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
