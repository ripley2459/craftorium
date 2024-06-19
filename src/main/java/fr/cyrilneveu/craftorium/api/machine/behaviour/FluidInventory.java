package fr.cyrilneveu.craftorium.api.machine.behaviour;

import fr.cyrilneveu.craftorium.api.inventory.CustomTank;
import fr.cyrilneveu.craftorium.api.inventory.FluidSlotData;
import fr.cyrilneveu.craftorium.api.machine.MachineTile;
import fr.cyrilneveu.craftorium.api.mui.ASlot;
import fr.cyrilneveu.craftorium.api.mui.ATabGroup;
import fr.cyrilneveu.craftorium.api.mui.AWidget;
import fr.cyrilneveu.craftorium.api.utils.CustomLazy;
import fr.cyrilneveu.craftorium.api.utils.Utils;
import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.FluidTankProperties;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidTankProperties;
import net.minecraftforge.fluids.capability.templates.EmptyFluidHandler;
import net.minecraftforge.fml.common.network.ByteBufUtils;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// TODO Fill and drain all tanks!
// If done check if there is the need to create a mirror.
// See ItemInventory$startSimulating()
public final class FluidInventory implements IMachineBehaviour, IFluidHandler, ICapabilityProvider, INBTSerializable<NBTTagCompound> {
    private final MachineTile owner;
    private final CustomLazy<FlowController> flowController;
    private final List<FluidSlotData> slots;
    private final List<CustomTank> tanks;
    @Nullable
    private EnumFacing side = null;

    public FluidInventory(MachineTile owner, List<FluidSlotData> slots) {
        this.owner = owner;
        this.flowController = new CustomLazy<>(() -> (FlowController) Utils.first(owner.getBehaviours(), b -> b instanceof FlowController), true);
        this.slots = slots;
        this.tanks = new LinkedList<>();
        for (FluidSlotData slot : slots)
            // this.tanks.add(new CustomTank((int) (slot.getCapacity() * owner.getTier().getStorage().getTankSize())));
            this.tanks.add(new CustomTank(slot.getCapacity()));
    }

    @Override
    public IFluidTankProperties[] getTankProperties() {
        if (tanks.isEmpty())
            return EmptyFluidHandler.EMPTY_TANK_PROPERTIES_ARRAY;

        IFluidTankProperties[] fluidTankProperties = new IFluidTankProperties[tanks.size()];
        for (int i = 0; i < tanks.size(); i++) {
            CustomTank tank = tanks.get(i);
            fluidTankProperties[i] = new FluidTankProperties(tank.getFluid(), tank.getCapacity(), slots.get(i).isInput(), slots.get(i).isOutput());
        }

        return fluidTankProperties;
    }

    @Override
    public int fill(FluidStack resource, boolean doFill) {
        owner.markDirty();

        for (int i = 0; i < slots.size(); i++) {
            if (slots.get(i).isInput() && (flowController.get() == null || (flowController.get() != null && flowController.get().canInput(side)))) {
                CustomTank tank = tanks.get(i);
                if (tank.canFillFluidType(resource) && !tank.isFull() && (tank.getFluid() == null || tank.getFluid().isFluidEqual(resource)))
                    return tank.fill(resource, doFill);
            }
        }

        return 0;
    }

    @Nullable
    @Override
    public FluidStack drain(FluidStack resource, boolean doDrain) {
        for (int i = 0; i < slots.size(); i++) {
            if (slots.get(i).isOutput() && (flowController.get() == null || (flowController.get() != null && flowController.get().canOutput(side)))) {
                CustomTank tank = tanks.get(i);
                if (tank.hasFluid() && resource.isFluidEqual(tank.getFluid()) && tank.drain(resource, false) != null)
                    return tank.drain(resource, doDrain);
            }
        }

        return null;
    }

    @Nullable
    @Override
    public FluidStack drain(int maxDrain, boolean doDrain) {
        owner.markDirty();

        for (int i = 0; i < slots.size(); i++) {
            if (slots.get(i).isOutput() && (flowController.get() == null || (flowController.get() != null && flowController.get().canOutput(side)))) {
                CustomTank tank = tanks.get(i);
                if (tank.hasFluid() && tank.drain(maxDrain, false) != null)
                    return tank.drain(maxDrain, doDrain);
            }
        }

        return null;
    }

    @Nullable
    public FluidStack takeFluid(FluidStack resource, boolean doDrain) {
        owner.markDirty();

        for (int i = 0; i < slots.size(); i++) {
            CustomTank tank = tanks.get(i);
            if (tank.hasFluid() && resource.isFluidEqual(tank.getFluid()) && tank.drain(resource, false) != null)
                return tank.drain(resource, doDrain);
        }

        return null;
    }

    public List<FluidStack> getStacksInInputs() {
        List<FluidStack> fluidStacks = new ArrayList<>();
        for (FluidSlotData slot : slots) {
            CustomTank tank = tanks.get(slot.getIndex());
            if (slot.isInput() && tank.hasFluid())
                fluidStacks.add(tank.getFluid());
        }

        return fluidStacks;
    }

    public List<FluidStack> getStacksInOutput() {
        List<FluidStack> fluidStacks = new ArrayList<>();
        for (FluidSlotData slot : slots) {
            CustomTank tank = tanks.get(slot.getIndex());
            if (slot.isOutput() && tank.hasFluid())
                fluidStacks.add(tank.getFluid());
        }

        return fluidStacks;
    }

    public FluidStack getStackInSlot(int slot) {
        return this.tanks.get(slot).getFluid();
    }

    public int insertInOutputs(FluidStack resource, boolean doDrain) {
        for (int i = 0; i < slots.size(); i++) {
            if (slots.get(i).isOutput()) {
                CustomTank tank = tanks.get(i);
                if (tank.canFillFluidType(resource) && !tank.isFull() && (tank.getFluid() == null || tank.getFluid().isFluidEqual(resource)))
                    return tank.fill(resource, doDrain);
            }
        }

        return 0;
    }

    public FluidInventory forSide(EnumFacing side) {
        this.side = side;
        return this;
    }

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
        if ((flowController.get() == null || (flowController.get() != null && flowController.get().canConnect(facing))))
            return capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY;
        return false;
    }

    @Nullable
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
        if ((flowController.get() == null || (flowController.get() != null && flowController.get().canConnect(facing))))
            return hasCapability(capability, facing) ? CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY.cast(this.forSide(facing)) : null;
        return null;
    }

    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound nbt = new NBTTagCompound();
        for (int i = 0; i < slots.size(); i++) {
            NBTTagCompound tankNBT = new NBTTagCompound();
            tanks.get(i).writeToNBT(tankNBT);
            nbt.setTag("Tank" + i, tankNBT);
        }

        return nbt;
    }

    @Override
    public void toBytes(ByteBuf buf) {
        for (CustomTank tank : tanks) {
            ByteBufUtils.writeUTF8String(buf, tank.getFluidName());
            buf.writeInt(tank.getFluidAmount());
            buf.writeInt(tank.getCapacity());
        }
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        for (int i = 0; i < slots.size(); i++) {
            if (nbt.hasKey("Tank" + i)) {
                NBTTagCompound tankNBT = nbt.getCompoundTag("Tank" + i);
                tanks.get(i).readFromNBT(tankNBT);
            }
        }
    }

    @Override
    public void pushWidgets(List<AWidget> widgets, List<ATabGroup.Tab> leftTabs, List<ATabGroup.Tab> rightTabs) {
        slots.forEach(s -> widgets.add(new ASlot.FluidSlot(s)));
    }

    public List<FluidSlotData> getSlotsData() {
        return slots;
    }

    public List<CustomTank> getTanks() {
        return tanks;
    }
}
