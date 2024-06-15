package fr.cyrilneveu.craftorium.api.mui;

import fr.cyrilneveu.craftorium.api.utils.ClientUtils;
import fr.cyrilneveu.craftorium.api.utils.Position;
import fr.cyrilneveu.craftorium.api.utils.RenderUtils;
import fr.cyrilneveu.craftorium.api.utils.Utils;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.math.MathHelper;

import java.util.Collections;
import java.util.List;

import static fr.cyrilneveu.craftorium.api.machine.behaviour.RecipeProcessor.MACHINE_CONFIGURATION_MAX;
import static fr.cyrilneveu.craftorium.api.machine.behaviour.RecipeProcessor.MACHINE_CONFIGURATION_MIN;
import static fr.cyrilneveu.craftorium.api.utils.RenderUtils.FONT_HEIGHT;
import static fr.cyrilneveu.craftorium.api.utils.RenderUtils.WHITE_COLOR;

public final class ButtonConfiguration extends AButton {
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
