package fr.cyrilneveu.craftorium.api.utils;

import fr.cyrilneveu.craftorium.api.render.ModelBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

import java.util.Objects;
import java.util.function.Function;

import static fr.cyrilneveu.craftorium.CraftoriumTags.MODID;

public final class RenderUtils {
    public static final Function<Block, CustomStateMapper> SIMPLE_STATE_MAPPER = block -> new CustomStateMapper(getSimpleModelLocation(block));
    public static final ModelBuilder ITEM_MODEL_BUILDER = new ModelBuilder(DefaultVertexFormats.ITEM);
    public static final ModelBuilder BLOCK_MODEL_BUILDER = new ModelBuilder(DefaultVertexFormats.BLOCK);
    public static final ResourceLocation BACKGROUND = new ResourceLocation(MODID, "textures/interfaces/elements/background.png");
    public static final int ERROR_COLOR = 0xFFff00ff;
    public static final int WHITE_COLOR = 0xFFffffff;
    public static final int BLACK_COLOR = 0xFF000000;

    public static ModelResourceLocation getSimpleModelLocation(Block block) {
        return new ModelResourceLocation(Block.REGISTRY.getNameForObject(block), "");
    }

    public static ModelResourceLocation getSimpleModelLocation(Item item) {
        return new ModelResourceLocation(Objects.requireNonNull(Item.REGISTRY.getNameForObject(item)), "inventory");
    }

    public static TextureAtlasSprite getTexture(ResourceLocation location) {
        return Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(location.toString());
    }

    public static class CustomStateMapper extends StateMapperBase {
        private final ModelResourceLocation location;

        public CustomStateMapper(ModelResourceLocation location) {
            this.location = location;
        }

        @Override
        protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
            return location;
        }
    }
}
