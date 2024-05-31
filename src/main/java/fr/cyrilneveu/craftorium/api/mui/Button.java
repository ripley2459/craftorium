package fr.cyrilneveu.craftorium.api.mui;

import fr.cyrilneveu.craftorium.api.utils.Position;
import fr.cyrilneveu.craftorium.api.utils.Size;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;

public abstract class Button extends AWidget implements ITextured {
    public static final Size TEXTURE_SIZE = new Size(32, 16);
    public static final Size BUTTON_SIZE = new Size(16, 16);
    private final ResourceLocation texture;

    public Button(Position position, ResourceLocation texture) {
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
}
