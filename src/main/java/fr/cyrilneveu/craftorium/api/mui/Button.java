package fr.cyrilneveu.craftorium.api.mui;

import fr.cyrilneveu.craftorium.api.utils.Position;
import fr.cyrilneveu.craftorium.api.utils.Size;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;

import static fr.cyrilneveu.craftorium.api.utils.Size.ZERO;

public class Button extends AWidget implements ITextured, IClickable {
    public static final Size TEXTURE_SIZE = new Size(32, 16);
    private final OnMouseClicked onMouseClicked;
    private final ResourceLocation texture;

    public Button(Position position, OnMouseClicked onMouseClicked, ResourceLocation texture) {
        super(position, ZERO);
        this.onMouseClicked = onMouseClicked;
        this.texture = texture;
    }

    @Override
    public void onMouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (isActive && isVisible && isHovered(mouseX, mouseY))
            onMouseClicked.onClick(mouseX, mouseY, mouseButton);
    }

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
        Minecraft.getMinecraft().getTextureManager().bindTexture(getTexture());
        Gui.drawModalRectWithCustomSizedTexture(getRealPosition().getPosX(), getRealPosition().getPosY(), isHovered(mouseX, mouseY) ? 16 : 0, 0, getSize().getSizeX(), getSize().getSizeY(), getTextureSize().getSizeX(), getTextureSize().getSizeY());
    }

    @FunctionalInterface
    public interface OnMouseClicked {
        void onClick(int mouseX, int mouseY, int mouseButton);
    }
}
