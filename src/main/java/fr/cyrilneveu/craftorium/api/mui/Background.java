package fr.cyrilneveu.craftorium.api.mui;

import fr.cyrilneveu.craftorium.api.utils.RenderUtils;
import fr.cyrilneveu.craftorium.api.utils.Size;
import net.minecraft.util.ResourceLocation;

import static fr.cyrilneveu.craftorium.CraftoriumTags.MODID;
import static fr.cyrilneveu.craftorium.api.utils.Position.ORIGIN;

public class Background extends AWidget implements ITextured {
    public static final ResourceLocation BACKGROUND = new ResourceLocation(MODID, "textures/interfaces/elements/background.png");
    public static final Size TEXTURE_SIZE = new Size(256, 256);
    public static final Size RECT_OFFSET = new Size(4, 4);

    public Background(Size size) {
        super(ORIGIN, size);
    }

    @Override
    public ResourceLocation getTexture() {
        return BACKGROUND;
    }

    @Override
    public Size getTextureSize() {
        return TEXTURE_SIZE;
    }

    @Override
    public void drawBackground(int mouseX, int mouseY, float partialTicks) {
        if (!isActive())
            return;

        RenderUtils.renderNineSlicedTexture(getRealPosition(), getSize(), RECT_OFFSET, getTextureSize(), BACKGROUND);
    }
}
