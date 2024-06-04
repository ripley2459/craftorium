package fr.cyrilneveu.craftorium.api.mui;

import fr.cyrilneveu.craftorium.api.utils.Position;
import fr.cyrilneveu.craftorium.api.utils.RenderUtils;
import fr.cyrilneveu.craftorium.api.utils.Size;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;

import static fr.cyrilneveu.craftorium.CraftoriumTags.MODID;

public final class ProgressArrow extends AWidget implements ITextured {
    public static final ResourceLocation ARROW = new ResourceLocation(MODID, "textures/interfaces/elements/arrow.png");
    public static final Size TEXTURE_SIZE = new Size(44, 16);
    public static final Size NORMAL_SIZE = new Size(22, 16);
    private int actual, max;

    public ProgressArrow(Position position) {
        super(position, NORMAL_SIZE);
    }

    @Override
    public void drawBackground(int mouseX, int mouseY, float partialTicks) {
        RenderUtils.bindTexture(getTexture());

        Gui.drawModalRectWithCustomSizedTexture(getRealPosition().getPosX(), getRealPosition().getPosY(), 0, 0, getSize().getSizeX(), getSize().getSizeY(), getTextureSize().getSizeX(), getTextureSize().getSizeY());

        if (max != 0) {
            int p = getSize().getSizeX() * actual / max;
            Gui.drawModalRectWithCustomSizedTexture(getRealPosition().getPosX(), getRealPosition().getPosY(), getSize().getSizeX(), getSize().getSizeY(), p, getSize().getSizeY(), getTextureSize().getSizeX(), getTextureSize().getSizeY());
        }
    }

    @Override
    public ResourceLocation getTexture() {
        return ARROW;
    }

    @Override
    public Size getTextureSize() {
        return TEXTURE_SIZE;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        actual = buf.readInt();
        max = buf.readInt();
    }
}
