package fr.cyrilneveu.craftorium.api.render;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Set;

@SideOnly(Side.CLIENT)
public interface ICustomModel {
    void addTextures(Set<ResourceLocation> textures);

    void onModelRegister();

    void onModelBake(ModelBakeEvent event);
}
