package fr.cyrilneveu.craftorium.api.mui;

import fr.cyrilneveu.craftorium.api.machine.behaviour.ESlotFlow;
import fr.cyrilneveu.craftorium.api.utils.ClientUtils;
import fr.cyrilneveu.craftorium.api.utils.Position;
import fr.cyrilneveu.craftorium.api.utils.Utils;
import io.netty.buffer.ByteBuf;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;

import java.util.Collections;
import java.util.List;

import static fr.cyrilneveu.craftorium.CraftoriumTags.MODID;
import static fr.cyrilneveu.craftorium.api.machine.behaviour.FlowController.DEFAULT_SLOT_BEHAVIOUR;

public final class ButtonFlowControl extends AButton {
    public static final ResourceLocation FLOW_ICON = new ResourceLocation(MODID, "textures/interfaces/elements/flow_icon.png");
    private static final ResourceLocation FLOW_LOCK = new ResourceLocation(MODID, "textures/interfaces/elements/flow_lock.png");
    private static final ResourceLocation FLOW_FREE = new ResourceLocation(MODID, "textures/interfaces/elements/flow_free.png");
    private static final ResourceLocation FLOW_INPUT = new ResourceLocation(MODID, "textures/interfaces/elements/flow_input.png");
    private static final ResourceLocation FLOW_OUTPUT = new ResourceLocation(MODID, "textures/interfaces/elements/flow_output.png");
    private final EnumFacing facing;
    private ESlotFlow flow = DEFAULT_SLOT_BEHAVIOUR;

    public ButtonFlowControl(Position position, EnumFacing facing) {
        super(position, FLOW_FREE);
        this.facing = facing;
    }

    @Override
    public boolean onMouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (!isActive() || !isHovered(mouseX, mouseY) || (mouseButton != 0 && mouseButton != 1))
            return false;

        if (mouseButton == 1)
            flow = flow.next();
        else flow = flow.previous();

        ClientUtils.playSound(SoundEvents.UI_BUTTON_CLICK);

        return true;
    }

    @Override
    public List<String> getTooltips(int mouseX, int mouseY) {
        if (!isActive() || !isHovered(mouseX, mouseY))
            return Collections.emptyList();

        return Collections.singletonList(Utils.localise("tooltip.craftorium.machine.facing_".concat(facing.getName())));
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.flow = ESlotFlow.byIndex(buf.readByte());
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeByte(flow.ordinal());
    }

    @Override
    public ResourceLocation getTexture() {
        return switch (flow) {
            case INPUT -> FLOW_INPUT;
            case OUTPUT -> FLOW_OUTPUT;
            case FREE -> FLOW_FREE;
            case LOCK -> FLOW_LOCK;
        };
    }
}
