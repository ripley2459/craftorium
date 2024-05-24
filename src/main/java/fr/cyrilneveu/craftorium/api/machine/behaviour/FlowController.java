package fr.cyrilneveu.craftorium.api.machine.behaviour;

import fr.cyrilneveu.craftorium.api.machine.MachineBlock;
import fr.cyrilneveu.craftorium.api.machine.MachineTile;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.util.INBTSerializable;

import javax.annotation.Nullable;
import java.util.LinkedHashMap;
import java.util.Map;

import static fr.cyrilneveu.craftorium.api.utils.NBTUtils.FACE_FLOW_NBT_KEY;

public final class FlowController implements IMachineBehaviour, INBTSerializable<NBTTagCompound> {
    private static final byte[] LEFT = {4, 5, 5, 4, 2, 3};
    private static final byte[] RIGHT = {5, 4, 4, 5, 3, 2};
    private static final byte[] FRONT = {0, 1, 2, 3, 4, 5};
    private static final byte[] BACK = {1, 0, 3, 2, 5, 4};
    private final MachineTile owner;
    private Map<EnumFacing, ESlotFlow> flowMap;

    public FlowController(MachineTile owner) {
        this.owner = owner;
        this.flowMap = new LinkedHashMap<>();
        for (EnumFacing value : EnumFacing.values())
            this.flowMap.put(value, ESlotFlow.LOCK);
    }

    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound nbt = new NBTTagCompound();
        int[] data = new int[EnumFacing.values().length];
        for (EnumFacing face : EnumFacing.values())
            data[face.getIndex()] = flowMap.get(face).ordinal();
        nbt.setIntArray(FACE_FLOW_NBT_KEY, data);
        return nbt;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        flowMap = new LinkedHashMap<>();
        if (nbt.hasKey(FACE_FLOW_NBT_KEY)) {
            int[] data = nbt.getIntArray(FACE_FLOW_NBT_KEY);
            for (EnumFacing face : EnumFacing.values())
                flowMap.put(face, ESlotFlow.values()[data[face.getIndex()]]);
        } else {
            for (EnumFacing face : EnumFacing.values())
                flowMap.put(face, ESlotFlow.FREE);
        }
    }

    public boolean canConnect(EnumFacing facing) {
        return canInput(facing) || canOutput(facing);
    }

    public boolean canInput(@Nullable EnumFacing facing) {
        return alignFacing(facing) == null || (flowMap.get(alignFacing(facing)) == ESlotFlow.FREE || flowMap.get(alignFacing(facing)) == ESlotFlow.INPUT);
    }

    public boolean canOutput(@Nullable EnumFacing facing) {
        return alignFacing(facing) == null || (flowMap.get(alignFacing(facing)) == ESlotFlow.FREE || flowMap.get(alignFacing(facing)) == ESlotFlow.OUTPUT);
    }

    private EnumFacing getFacing() {
        return owner.getWorld().getBlockState(owner.getPos()).getValue(MachineBlock.FACING);
    }

    private EnumFacing alignFacing(@Nullable EnumFacing facing) {
        if (facing == null || facing == EnumFacing.UP || facing == EnumFacing.DOWN)
            return facing;

        return switch (getFacing()) {
            case NORTH -> EnumFacing.byIndex(FRONT[facing.getIndex()]);
            case SOUTH -> EnumFacing.byIndex(BACK[facing.getIndex()]);
            case WEST -> EnumFacing.byIndex(LEFT[facing.getIndex()]);
            case EAST -> EnumFacing.byIndex(RIGHT[facing.getIndex()]);
            default -> facing;
        };
    }
}
