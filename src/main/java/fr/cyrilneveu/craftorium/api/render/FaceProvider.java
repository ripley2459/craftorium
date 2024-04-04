package fr.cyrilneveu.craftorium.api.render;

import net.minecraft.util.ResourceLocation;

public final class FaceProvider {
    private final ResourceLocation texture;
    private final int color;

    public FaceProvider(ResourceLocation texture, int color) {
        this.texture = texture;
        this.color = color;
    }

    public ResourceLocation getTexture() {
        return texture;
    }

    public int getColor() {
        return color;
    }
}
