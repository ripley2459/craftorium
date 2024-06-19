package fr.cyrilneveu.craftorium.api.mui;

import fr.cyrilneveu.craftorium.api.inventory.ESlotFlow;
import fr.cyrilneveu.craftorium.api.utils.*;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static fr.cyrilneveu.craftorium.CraftoriumTags.MODID;
import static fr.cyrilneveu.craftorium.api.machine.behaviour.FlowController.DEFAULT_SLOT_BEHAVIOUR;
import static fr.cyrilneveu.craftorium.api.machine.behaviour.RecipeProcessor.MACHINE_CONFIGURATION_MAX;
import static fr.cyrilneveu.craftorium.api.machine.behaviour.RecipeProcessor.MACHINE_CONFIGURATION_MIN;
import static fr.cyrilneveu.craftorium.api.utils.RenderUtils.FONT_HEIGHT;
import static fr.cyrilneveu.craftorium.api.utils.RenderUtils.WHITE_COLOR;

public abstract class AButton extends AWidget implements ITextured {
    public static final Size TEXTURE_SIZE = new Size(32, 16);
    public static final Size BUTTON_SIZE = new Size(16, 16);
    public static final ResourceLocation DEFAULT_BUTTON = new ResourceLocation(MODID, "textures/interfaces/elements/button.png");
    private final ResourceLocation texture;

    public AButton(Position position, ResourceLocation texture) {
        super(position, BUTTON_SIZE);
        this.texture = texture;
    }

    @Override
    public abstract boolean onMouseClicked(int mouseX, int mouseY, int mouseButton);

    @Override
    public ResourceLocation getTexture() {
        return texture;
    }

    @Override
    public Size getTextureSize() {
        return TEXTURE_SIZE;
    }

    @Override
    public void drawBackground(int mouseX, int mouseY, float partialTicks) {
        if (!isActive())
            return;

        Minecraft.getMinecraft().getTextureManager().bindTexture(getTexture());
        Gui.drawModalRectWithCustomSizedTexture(getRealPosition().getPosX(), getRealPosition().getPosY(), isHovered(mouseX, mouseY) ? 16 : 0, 0, getSize().getSizeX(), getSize().getSizeY(), getTextureSize().getSizeX(), getTextureSize().getSizeY());
    }

    public static final class ButtonConfiguration extends AButton {
        private int configuration = MACHINE_CONFIGURATION_MIN;

        public ButtonConfiguration(Position position) {
            super(position, DEFAULT_BUTTON);
        }

        @Override
        public boolean onMouseClicked(int mouseX, int mouseY, int mouseButton) {
            if (!isActive() || !isHovered(mouseX, mouseY) || (mouseButton != 0 && mouseButton != 1))
                return false;

            if (mouseButton == 1)
                configuration--;
            else configuration++;
            configuration = MathHelper.clamp(configuration, MACHINE_CONFIGURATION_MIN, MACHINE_CONFIGURATION_MAX);

            ClientUtils.playSound(SoundEvents.UI_BUTTON_CLICK);

            return true;
        }

        @Override
        public void drawForeground(int mouseX, int mouseY) {
            String l = String.valueOf(configuration);
            int posX = getPosition().getPosX() + (BUTTON_SIZE.getSizeX() / 2 - Minecraft.getMinecraft().fontRenderer.getStringWidth(l) / 2);
            int posY = getPosition().getPosY() + (BUTTON_SIZE.getSizeY() / 2 - FONT_HEIGHT / 2);
            RenderUtils.renderText(l, posX, posY, WHITE_COLOR, 1.0f, true, false);
        }

        @Override
        public List<String> getTooltips(int mouseX, int mouseY) {
            if (!isActive() || !isHovered(mouseX, mouseY))
                return Collections.emptyList();

            return Collections.singletonList(Utils.localise("tooltip.craftorium.machine.button.configuration", configuration));
        }

        @Override
        public void fromBytes(ByteBuf buf) {
            configuration = buf.readByte();
        }

        @Override
        public void toBytes(ByteBuf buf) {
            buf.writeByte(configuration);
        }
    }

    public static final class ButtonFlowControl extends AButton {
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

            List<String> tooltips = new LinkedList<>();
            tooltips.add(Utils.localise("tooltip.craftorium.machine.button.facing.".concat(facing.getName())));
            String on = Utils.localise("tooltip.craftorium.on");
            String off = Utils.localise("tooltip.craftorium.off");
            tooltips.add(Utils.localise("tooltip.craftorium.machine.button.input", (flow == ESlotFlow.INPUT || flow == ESlotFlow.FREE) ? on : off));
            tooltips.add(Utils.localise("tooltip.craftorium.machine.button.output", (flow == ESlotFlow.OUTPUT || flow == ESlotFlow.FREE) ? on : off));

            return tooltips;
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
}
