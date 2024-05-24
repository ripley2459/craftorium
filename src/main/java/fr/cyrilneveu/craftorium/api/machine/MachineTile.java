package fr.cyrilneveu.craftorium.api.machine;

import fr.cyrilneveu.craftorium.api.machine.behaviour.IMachineBehaviour;
import fr.cyrilneveu.craftorium.api.tier.Tier;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static fr.cyrilneveu.craftorium.api.Registries.MACHINES_REGISTRY;
import static fr.cyrilneveu.craftorium.api.Registries.TIERS_REGISTRY;
import static fr.cyrilneveu.craftorium.api.utils.NBTUtils.MACHINE_NBT_KEY;
import static fr.cyrilneveu.craftorium.api.utils.NBTUtils.TIER_NBT_KEY;
import static fr.cyrilneveu.craftorium.common.machine.Machines.ELECTROLYZER;
import static fr.cyrilneveu.craftorium.common.tier.Tiers.ONE;

public final class MachineTile extends TileEntity implements ITickable {
    private Machine machine;
    private Tier tier;
    private IMachineBehaviour[] behaviours = new IMachineBehaviour[0];

    public MachineTile() {
        this(ELECTROLYZER, ONE);
    }

    public MachineTile(Machine machine, Tier tier) {
        this.machine = machine;
        this.tier = tier;
        this.behaviours = machine.getBehaviours(this, tier);
    }

    public EMachineStates getState() {
        return EMachineStates.IDLE;
    }

    @Override
    public void update() {
        for (IMachineBehaviour behaviour : behaviours) {
            if (behaviour instanceof ITickable tickable)
                tickable.update();
        }
    }

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
        for (IMachineBehaviour behaviour : behaviours) {
            if (behaviour instanceof ICapabilityProvider provider && provider.hasCapability(capability, facing))
                return true;
        }

        return super.hasCapability(capability, facing);
    }

    @Nullable
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
        for (IMachineBehaviour behaviour : behaviours) {
            if (behaviour instanceof ICapabilityProvider provider && provider.hasCapability(capability, facing)) {
                T capability1 = provider.getCapability(capability, facing);
                if (capability1 != null)
                    return capability1;
            }
        }

        return super.getCapability(capability, facing);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);

        compound.setString(TIER_NBT_KEY, tier.getName());
        compound.setString(MACHINE_NBT_KEY, machine.getName());

        for (IMachineBehaviour behaviour : behaviours) {
            if (behaviour instanceof INBTSerializable) {
                INBTSerializable<NBTTagCompound> serializable = (INBTSerializable<NBTTagCompound>) behaviour;
                compound.merge(serializable.serializeNBT());
            }
        }

        return compound;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);

        if (compound.hasKey(MACHINE_NBT_KEY) && compound.hasKey(TIER_NBT_KEY)) {
            machine = MACHINES_REGISTRY.get(compound.getString(MACHINE_NBT_KEY));
            tier = TIERS_REGISTRY.get(compound.getString(TIER_NBT_KEY));
            behaviours = machine.getBehaviours(this, tier);
        }

        for (IMachineBehaviour behaviour : behaviours) {
            if (behaviour instanceof INBTSerializable) {
                INBTSerializable<NBTTagCompound> serializable = (INBTSerializable<NBTTagCompound>) behaviour;
                serializable.deserializeNBT(compound);
            }
        }
    }

    public Machine getMachine() {
        return machine;
    }

    public Tier getTier() {
        return tier;
    }

    public IMachineBehaviour[] getBehaviours() {
        return behaviours;
    }
}
