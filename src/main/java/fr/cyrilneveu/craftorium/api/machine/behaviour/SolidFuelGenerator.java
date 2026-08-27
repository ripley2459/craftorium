package fr.cyrilneveu.craftorium.api.machine.behaviour;

import fr.cyrilneveu.craftorium.api.machine.EMachineStates;
import fr.cyrilneveu.craftorium.api.machine.MachineTile;
import fr.cyrilneveu.craftorium.api.mui.ATabGroup;
import fr.cyrilneveu.craftorium.api.mui.AWidget;
import fr.cyrilneveu.craftorium.api.mui.ProgressArrow;
import fr.cyrilneveu.craftorium.api.mui.ProgressBurnTime;
import fr.cyrilneveu.craftorium.api.utils.CustomLazy;
import fr.cyrilneveu.craftorium.api.utils.Position;
import fr.cyrilneveu.craftorium.api.utils.Utils;
import io.netty.buffer.ByteBuf;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.util.INBTSerializable;

import java.util.List;

public class SolidFuelGenerator implements IMachineBehaviour, ITickable, INBTSerializable<NBTTagCompound> {
    public static final String MACHINE_BURN_TIME_LEFT_NBT = "MachineSolidFuelGeneratorBurnTimeLeft";
    public static final String MACHINE_MAX_BURN_TIME_NBT = "MachineSolidFuelGeneratorMaxBurnTime";
    private final MachineTile owner;
    private final int io;
    private final CustomLazy<FlowController> flowController;
    private final Position progressPosition;
    private ItemInventory itemInventory;
    private EnergyInventory energyInventory;
    private boolean burning;
    private int burnTime;
    private int maxBurnTime;

    public SolidFuelGenerator(MachineTile owner, int io, Position progressPosition) {
        this.owner = owner;
        this.io = (int) (io * owner.getTier().getEnergyIO());
        this.flowController = new CustomLazy<>(() -> (FlowController) Utils.first(owner.getBehaviours(), b -> b instanceof FlowController), true);
        this.progressPosition = progressPosition;
    }

    @Override
    public void init() {
        this.itemInventory = (ItemInventory) Utils.first(owner.getBehaviours(), b -> b instanceof ItemInventory);
        this.energyInventory = (EnergyInventory) Utils.first(owner.getBehaviours(), b -> b instanceof EnergyInventory);
    }

    @Override
    public void update() {
        if (owner.getWorld().isRemote)
            return;

        if (burning && burnTime > 0) {
            owner.setState(EMachineStates.WORKING);
            owner.markDirty();
            int received = energyInventory.receiveEnergy(io, true);
            if (received > 0) {
                burnTime--;
                energyInventory.receiveEnergy(io, false);
            }
            if (burnTime <= 0) {
                burning = false;
                maxBurnTime = 0;
                owner.setState(EMachineStates.IDLE);
                owner.markDirty();
            }
        } else {
            ItemStack fuel = itemInventory.getStackInSlot(0);
            if (fuel.isEmpty())
                return;

            int fuelBurnTime = TileEntityFurnace.getItemBurnTime(fuel);
            if (fuelBurnTime <= 0)
                return;

            fuel.shrink(1);
            maxBurnTime = fuelBurnTime;
            burnTime = fuelBurnTime;
            burning = true;
            owner.setState(EMachineStates.WORKING);
            owner.markDirty();
        }
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        // Rien
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(burnTime);
        buf.writeInt(maxBurnTime);
    }

    @Override
    public void pushWidgets(List<AWidget> widgets, List<ATabGroup.Tab> leftTabs, List<ATabGroup.Tab> rightTabs) {
        widgets.add(new ProgressBurnTime(progressPosition));
    }

    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound tag = new NBTTagCompound();
        tag.setInteger(MACHINE_BURN_TIME_LEFT_NBT, burnTime);
        tag.setInteger(MACHINE_MAX_BURN_TIME_NBT, maxBurnTime);
        return tag;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        if (nbt.hasKey(MACHINE_BURN_TIME_LEFT_NBT) && nbt.hasKey(MACHINE_MAX_BURN_TIME_NBT)) {
            burnTime = nbt.getInteger(MACHINE_BURN_TIME_LEFT_NBT);
            maxBurnTime = nbt.getInteger(MACHINE_MAX_BURN_TIME_NBT);
            if (burnTime > 0)
                burning = true;
        } else {
            burning = false;
            burnTime = 0;
            maxBurnTime = 0;
        }
    }
}
