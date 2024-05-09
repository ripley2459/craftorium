package fr.cyrilneveu.craftorium.api.render;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Set;

public interface ICustomModel {
    @SideOnly(Side.CLIENT)
    int getItemStackColor(ItemStack stack, int layer);

    @SideOnly(Side.CLIENT)
    void addTextures(Set<ResourceLocation> textures);

    @SideOnly(Side.CLIENT)
    void onModelRegister();

    @SideOnly(Side.CLIENT)
    void onModelBake(ModelBakeEvent event);
}
