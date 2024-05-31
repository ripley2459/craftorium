package fr.cyrilneveu.craftorium.api.mui;

import fr.cyrilneveu.craftorium.api.utils.Position;
import fr.cyrilneveu.craftorium.api.utils.Size;
import net.minecraft.util.ResourceLocation;

import static fr.cyrilneveu.craftorium.api.mui.Background.TEXTURE_SIZE;
import static fr.cyrilneveu.craftorium.api.utils.Position.ORIGIN;

public final class Tab extends AWidget implements ITextured {
    public static final Size TAB_NORMAL_SIZE = new Size(21, 24);
    private final AWidget[] children;
    private final ResourceLocation texture;
    private final Size openSize;
    private final int color;
    private boolean open = false;

    public Tab(Size openSize, AWidget[] children, ResourceLocation texture, int color) {
        super(ORIGIN, TAB_NORMAL_SIZE);
        this.openSize = openSize;
        this.children = children;
        this.texture = texture;
        this.color = color;

        for (AWidget child : children)
            child.setActive(this.open);
    }

    @Override
    public void init(Position parentPosition, Size parentSize) {
        super.init(parentPosition, parentSize);

        for (AWidget child : children)
            child.init(getRealPosition(), parentSize);
    }

    @Override
    public Size getSize() {
        return open ? openSize : size;
    }

    public Size getNormalSize() {
        return size;
    }

    public Size getOpenSize() {
        return openSize;
    }

    @Override
    public ResourceLocation getTexture() {
        return texture;
    }

    @Override
    public Size getTextureSize() {
        return TEXTURE_SIZE;
    }

    public int getColor() {
        return color;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;

        for (AWidget child : children)
            child.setActive(open);
    }

    public AWidget[] getChildren() {
        return children;
    }
}
