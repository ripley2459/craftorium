package fr.cyrilneveu.craftorium.api.render;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.ModelLoaderRegistry;

public final class ModelTemplate {
    private final IModel model;

    public ModelTemplate(String domain, String path) {
        this.model = ModelLoaderRegistry.getModelOrMissing(new ResourceLocation(domain, path));
    }

    public IModel getModel() {
        return model;
    }
}
