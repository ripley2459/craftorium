package fr.cyrilneveu.craftorium.api.render;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelBakeEvent;

import java.util.Set;

public interface ICustomModel {
    void addTextures(Set<ResourceLocation> textures);

    void onModelRegister();

    void onModelBake(ModelBakeEvent event);
}
