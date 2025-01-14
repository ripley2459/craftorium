package fr.cyrilneveu.craftorium.api.machine;

import fr.cyrilneveu.craftorium.api.machine.behaviour.IMachineBehaviour;
import fr.cyrilneveu.craftorium.api.mui.Screen;
import fr.cyrilneveu.craftorium.api.substance.Tier;
import fr.cyrilneveu.craftorium.api.utils.CustomLazy;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
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
import static fr.cyrilneveu.craftorium.api.utils.NBTUtils.*;
import static fr.cyrilneveu.craftorium.common.machine.Machines.ELECTROLYZER;
import static fr.cyrilneveu.craftorium.common.substance.Substances.ONE;

public class MachineTile extends TileEntity implements ITickable {
    private Machine machine;
    private Tier tier;
    private IMachineBehaviour[] behaviours = new IMachineBehaviour[0];
    private final CustomLazy<Screen> screen = new CustomLazy<>(() -> machine.getScreen(this, tier), false);
    private EMachineStates state = EMachineStates.NOPOWER;

    public MachineTile() {
        this(ELECTROLYZER, ONE);
    }

    public MachineTile(Machine machine, Tier tier) {
        this.machine = machine;
        this.tier = tier;
        initBehaviours();
    }

    @Override
    public void update() {
        for (IMachineBehaviour behaviour : behaviours) {
            if (behaviour instanceof ITickable tickable)
                tickable.update();
        }
    }

    public Container createContainer(EntityPlayer player) {
        return new MachineContainer(this, player);
    }

    public GuiContainer createGui(EntityPlayer player) {
        return new MachineScreen(this, createContainer(player));
    }

    private void initBehaviours() {
        behaviours = machine.getBehaviours(this, tier);

        for (IMachineBehaviour behaviour : behaviours)
            behaviour.init();
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
            initBehaviours();
        }

        for (IMachineBehaviour behaviour : behaviours) {
            if (behaviour instanceof INBTSerializable) {
                INBTSerializable<NBTTagCompound> serializable = (INBTSerializable<NBTTagCompound>) behaviour;
                serializable.deserializeNBT(compound);
            }
        }
    }

    @Override
    public NBTTagCompound getUpdateTag() {
        NBTTagCompound nbtTagCompound = writeToNBT(super.getUpdateTag());
        nbtTagCompound.setInteger(STATE_NBT_KEY, state.ordinal());
        return nbtTagCompound;
    }

    @Override
    public void handleUpdateTag(NBTTagCompound tag) {
        this.readFromNBT(tag);
    }

    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        return new SPacketUpdateTileEntity(pos, 1, getUpdateTag());
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
        this.readFromNBT(pkt.getNbtCompound());

        int stateIndex = pkt.getNbtCompound().getInteger(STATE_NBT_KEY);
        if (world.isRemote && stateIndex != state.ordinal()) {
            state = EMachineStates.values()[stateIndex];
            world.markBlockRangeForRenderUpdate(pos, pos);
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

    @Nullable
    public Screen getScreen() {
        return screen.get();
    }

    public EMachineStates getState() {
        return state;
    }

    public void setState(EMachineStates state) {
        if (this.state != state) {
            this.state = state;
            markDirty();
            IBlockState blockState = world.getBlockState(pos);
            getWorld().notifyBlockUpdate(pos, blockState, blockState, 3);
        }
    }
}
