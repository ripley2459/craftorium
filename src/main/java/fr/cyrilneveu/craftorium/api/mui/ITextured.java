package fr.cyrilneveu.craftorium.api.mui;

import fr.cyrilneveu.craftorium.api.utils.Size;
import net.minecraft.util.ResourceLocation;

public interface ITextured {
    ResourceLocation getTexture();

    Size getTextureSize();
}
