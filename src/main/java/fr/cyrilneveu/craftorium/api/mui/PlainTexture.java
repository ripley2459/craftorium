package fr.cyrilneveu.craftorium.api.mui;

import fr.cyrilneveu.craftorium.api.utils.Position;
import fr.cyrilneveu.craftorium.api.utils.RenderUtils;
import fr.cyrilneveu.craftorium.api.utils.Size;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;

public class PlainTexture extends AWidget implements ITextured {
    private final ResourceLocation texture;
    private final boolean drawInForeground;

    public PlainTexture(Position position, Size size, ResourceLocation texture, boolean drawInForeground) {
        super(position, size);
        this.texture = texture;
        this.drawInForeground = drawInForeground;
    }

    @Override
    public ResourceLocation getTexture() {
        return texture;
    }

    @Override
    public Size getTextureSize() {
        return size;
    }

    @Override
    public void drawForeground(int mouseX, int mouseY) {
        if (!isActive() || !drawInForeground)
            return;

        RenderUtils.bindTexture(getTexture());
        Gui.drawModalRectWithCustomSizedTexture(getRealPosition().getPosX(), getRealPosition().getPosY(), 0, 0, getTextureSize().getSizeX(), getTextureSize().getSizeY(), getTextureSize().getSizeX(), getTextureSize().getSizeY());
    }

    @Override
    public void drawBackground(int mouseX, int mouseY, float partialTicks) {
        if (!isActive() || drawInForeground)
            return;

        RenderUtils.bindTexture(getTexture());
        Gui.drawModalRectWithCustomSizedTexture(getRealPosition().getPosX(), getRealPosition().getPosY(), 0, 0, getTextureSize().getSizeX(), getTextureSize().getSizeY(), getTextureSize().getSizeX(), getTextureSize().getSizeY());
    }
}
