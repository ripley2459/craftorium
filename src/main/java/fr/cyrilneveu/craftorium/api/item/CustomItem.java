package fr.cyrilneveu.craftorium.api.item;

import fr.cyrilneveu.craftorium.api.render.FaceProvider;
import fr.cyrilneveu.craftorium.api.render.ICustomModel;
import fr.cyrilneveu.craftorium.api.render.ModelTemplate;
import fr.cyrilneveu.craftorium.api.utils.Utils;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.model.ModelLoader;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

import static fr.cyrilneveu.craftorium.api.utils.Utils.ITEM_MODEL_BUILDER;

public class CustomItem extends Item implements ICustomModel, IItemColor {
    private final FaceProvider[] faceProviders;

    public CustomItem(FaceProvider[] faceProviders) {
        this.faceProviders = faceProviders;
    }

    @Override
    public int colorMultiplier(@NotNull ItemStack itemStack, int layer) {
        return faceProviders[layer].getColor();
    }

    @Override
    public void addTextures(Set<ResourceLocation> textures) {
        for (FaceProvider faceProvider : faceProviders)
            textures.add(faceProvider.getTexture());
    }

    @Override
    public void onModelRegister() {
        ModelResourceLocation location = Utils.getSimpleModelLocation(this);
        ModelBakery.registerItemVariants(this, location);
        ModelLoader.setCustomMeshDefinition(this, stack -> location);
    }

    @Override
    public void onModelBake(ModelBakeEvent event) {
        ITEM_MODEL_BUILDER.newOperation(ModelTemplate.ITEM);

        for (FaceProvider face : faceProviders)
            ITEM_MODEL_BUILDER.addLayer(face.getTexture());

        event.getModelRegistry().putObject(Utils.getSimpleModelLocation(this), ITEM_MODEL_BUILDER.build().getModel());
    }
}
