package fr.cyrilneveu.craftorium.api.machine.behaviour;

import fr.cyrilneveu.craftorium.api.machine.MachineBlock;
import fr.cyrilneveu.craftorium.api.machine.MachineTile;
import fr.cyrilneveu.craftorium.api.mui.AWidget;
import fr.cyrilneveu.craftorium.api.mui.ButtonFlowControl;
import fr.cyrilneveu.craftorium.api.mui.Tab;
import fr.cyrilneveu.craftorium.api.mui.Text;
import fr.cyrilneveu.craftorium.api.utils.CustomLazy;
import fr.cyrilneveu.craftorium.api.utils.Position;
import fr.cyrilneveu.craftorium.api.utils.Size;
import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.util.INBTSerializable;

import javax.annotation.Nullable;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static fr.cyrilneveu.craftorium.api.mui.ButtonFlowControl.FLOW_ICON;
import static fr.cyrilneveu.craftorium.api.utils.NBTUtils.FACE_FLOW_NBT_KEY;
import static fr.cyrilneveu.craftorium.api.utils.RenderUtils.WHITE_COLOR;

public final class FlowController implements IMachineBehaviour, INBTSerializable<NBTTagCompound> {
    public static final ESlotFlow DEFAULT_SLOT_BEHAVIOUR = ESlotFlow.LOCK;
    private static final byte[] LEFT = {4, 5, 5, 4, 2, 3};
    private static final byte[] RIGHT = {5, 4, 4, 5, 3, 2};
    private static final byte[] FRONT = {0, 1, 2, 3, 4, 5};
    private static final byte[] BACK = {1, 0, 3, 2, 5, 4};
    private final MachineTile owner;
    private Map<EnumFacing, ESlotFlow> flowMap;
    private CustomLazy<EnumFacing> cachedFacing = new CustomLazy<>(this::getFacing, false);

    public FlowController(MachineTile owner) {
        this.owner = owner;
        this.flowMap = new LinkedHashMap<>();
        for (EnumFacing value : EnumFacing.values())
            this.flowMap.put(value, DEFAULT_SLOT_BEHAVIOUR);
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
                flowMap.put(face, DEFAULT_SLOT_BEHAVIOUR);
        }
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        for (EnumFacing value : EnumFacing.values())
            flowMap.put(value, ESlotFlow.byIndex(buf.readByte()));
    }

    @Override
    public void toBytes(ByteBuf buf) {
        for (EnumFacing value : EnumFacing.values())
            buf.writeByte(flowMap.get(value).ordinal());
    }

    public boolean canConnect(EnumFacing facing) {
        return canInput(facing) || canOutput(facing);
    }

    public boolean canInput(@Nullable EnumFacing facing) {
        EnumFacing aligned = alignFacing(facing);
        return aligned == null || (flowMap.get(aligned) == ESlotFlow.FREE || flowMap.get(aligned) == ESlotFlow.INPUT);
    }

    public boolean canOutput(@Nullable EnumFacing facing) {
        EnumFacing aligned = alignFacing(facing);
        return aligned == null || (flowMap.get(aligned) == ESlotFlow.FREE || flowMap.get(aligned) == ESlotFlow.OUTPUT);
    }

    private EnumFacing getFacing() {
        return owner.getWorld().getBlockState(owner.getPos()).getValue(MachineBlock.FACING);
    }

    @Nullable
    private EnumFacing alignFacing(@Nullable EnumFacing facing) {
        if (facing == null || facing == EnumFacing.UP || facing == EnumFacing.DOWN)
            return facing;

        return switch (cachedFacing.get()) {
            case NORTH -> EnumFacing.byIndex(FRONT[facing.getIndex()]);
            case SOUTH -> EnumFacing.byIndex(BACK[facing.getIndex()]);
            case WEST -> EnumFacing.byIndex(LEFT[facing.getIndex()]);
            case EAST -> EnumFacing.byIndex(RIGHT[facing.getIndex()]);
            default -> facing;
        };
    }

    @Override
    public void pushWidgets(List<AWidget> widgets, List<Tab> leftTabs, List<Tab> rightTabs) {
        List<AWidget> buttons = new LinkedList<>();

        buttons.add(new Text(new Position(6, 8), () -> "Flow controller", false, WHITE_COLOR));
        buttons.add(new ButtonFlowControl(new Position(51 - 8, 2 * 16 + 2 * 2 + 23), EnumFacing.DOWN));
        buttons.add(new ButtonFlowControl(new Position(51 - 8, 23), EnumFacing.UP));
        buttons.add(new ButtonFlowControl(new Position(51 - 8, 16 + 2 + 23), EnumFacing.NORTH));
        buttons.add(new ButtonFlowControl(new Position(51 + 8 + 2, 2 * 16 + 2 * 2 + 23), EnumFacing.SOUTH));
        buttons.add(new ButtonFlowControl(new Position(51 + 8 + 2, 16 + 2 + 23), EnumFacing.EAST));
        buttons.add(new ButtonFlowControl(new Position(51 - (8 + 2 + 16), 16 + 2 + 23), EnumFacing.WEST));

        widgets.addAll(buttons);
        leftTabs.add(new Tab(new Size(102, 81), buttons.toArray(buttons.toArray(new AWidget[0])), FLOW_ICON, 0xFF0000FF));
    }
}
