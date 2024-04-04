package fr.cyrilneveu.craftorium.api.render;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.ModelLoaderRegistry;

import static fr.cyrilneveu.craftorium.CraftoriumTags.MODID;

public final class ModelTemplate {
    public static final ModelTemplate BLOCK = new ModelTemplate(MODID, "block/block");
    public static final ModelTemplate BLOCK_TINTED = new ModelTemplate(MODID, "block/block_tinted");
    public static final ModelTemplate BLOCK_OVERLAY = new ModelTemplate(MODID, "block/block_overlay");
    public static final ModelTemplate BLOCK_TINTED_OVERLAY = new ModelTemplate(MODID, "block/block_tinted_overlay");
    public static final ModelTemplate BLOCK_OVERLAY_TINTED = new ModelTemplate(MODID, "block/block_overlay_tinted");
    public static final ModelTemplate BLOCK_TINTED_OVERLAY_TINTED = new ModelTemplate(MODID, "block/block_tinted_overlay_tinted");
    public static final ModelTemplate ITEM = new ModelTemplate("minecraft", "item/generated");
    public static final ModelTemplate ITEM_HANDHELD = new ModelTemplate("minecraft", "item/handheld");
    private final IModel model;

    public ModelTemplate(String domain, String path) {
        this.model = ModelLoaderRegistry.getModelOrMissing(new ResourceLocation(domain, path));
    }

    public IModel getModel() {
        return model;
    }
}
