package fr.cyrilneveu.craftorium.api.utils;

import fr.cyrilneveu.craftorium.api.render.ModelBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
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
    public static final int TEXT_COLOR = 4210752;
    public static final int FONT_HEIGHT = 9;

    public static ModelResourceLocation getSimpleModelLocation(Block block) {
        return new ModelResourceLocation(Block.REGISTRY.getNameForObject(block), "");
    }

    public static ModelResourceLocation getSimpleModelLocation(Item item) {
        return new ModelResourceLocation(Objects.requireNonNull(Item.REGISTRY.getNameForObject(item)), "inventory");
    }

    public static TextureAtlasSprite getTexture(ResourceLocation location) {
        return Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(location.toString());
    }

    public static void bindTexture(ResourceLocation texture) {
        Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
    }

    public static void renderNineSlicedTexture(Position position, Size size, Size rectOffset, Size textureSize, ResourceLocation texture) {
        Minecraft.getMinecraft().getTextureManager().bindTexture(texture);

        // Corners - TL>TR>BR>BL
        Gui.drawModalRectWithCustomSizedTexture(position.getPosX(), position.getPosY(), 0, 0, rectOffset.getSizeX(), rectOffset.getSizeY(), textureSize.getSizeX(), textureSize.getSizeY());
        Gui.drawModalRectWithCustomSizedTexture(position.getPosX() + size.getSizeX() - rectOffset.getSizeX(), position.getPosY(), textureSize.getSizeX() - rectOffset.getSizeX(), 0, rectOffset.getSizeX(), rectOffset.getSizeY(), textureSize.getSizeX(), textureSize.getSizeY());
        Gui.drawModalRectWithCustomSizedTexture(position.getPosX() + size.getSizeX() - rectOffset.getSizeX(), position.getPosY() + size.getSizeY() - rectOffset.getSizeY(), textureSize.getSizeX() - rectOffset.getSizeX(), textureSize.getSizeY() - rectOffset.getSizeY(), rectOffset.getSizeX(), rectOffset.getSizeY(), textureSize.getSizeX(), textureSize.getSizeY());
        Gui.drawModalRectWithCustomSizedTexture(position.getPosX(), position.getPosY() + size.getSizeY() - rectOffset.getSizeY(), 0, textureSize.getSizeY() - rectOffset.getSizeY(), rectOffset.getSizeX(), rectOffset.getSizeY(), textureSize.getSizeX(), textureSize.getSizeY());

        // Borders - T>R>B>L
        Gui.drawModalRectWithCustomSizedTexture(position.getPosX() + rectOffset.getSizeX(), position.getPosY(), rectOffset.getSizeX(), 0, size.getSizeX() - 2 * rectOffset.getSizeX(), rectOffset.getSizeY(), textureSize.getSizeX(), textureSize.getSizeY());
        Gui.drawModalRectWithCustomSizedTexture(position.getPosX() + size.getSizeX() - rectOffset.getSizeX(), position.getPosY() + rectOffset.getSizeY(), textureSize.getSizeX() - rectOffset.getSizeX(), rectOffset.getSizeY(), rectOffset.getSizeX(), size.getSizeY() - 2 * rectOffset.getSizeY(), textureSize.getSizeX(), textureSize.getSizeY());
        Gui.drawModalRectWithCustomSizedTexture(position.getPosX() + rectOffset.getSizeX(), position.getPosY() + size.getSizeY() - rectOffset.getSizeY(), rectOffset.getSizeX(), textureSize.getSizeY() - rectOffset.getSizeY(), size.getSizeX() - 2 * rectOffset.getSizeX(), rectOffset.getSizeY(), textureSize.getSizeX(), textureSize.getSizeY());
        Gui.drawModalRectWithCustomSizedTexture(position.getPosX(), position.getPosY() + rectOffset.getSizeY(), 0, rectOffset.getSizeY(), rectOffset.getSizeX(), size.getSizeY() - 2 * rectOffset.getSizeY(), textureSize.getSizeX(), textureSize.getSizeY());

        Gui.drawModalRectWithCustomSizedTexture(position.getPosX() + rectOffset.getSizeX(), position.getPosY() + rectOffset.getSizeY(), rectOffset.getSizeX(), rectOffset.getSizeY(), size.getSizeX() - 2 * rectOffset.getSizeX(), size.getSizeY() - 2 * rectOffset.getSizeY(), textureSize.getSizeX(), textureSize.getSizeY());
    }

    public static void resetRenderColor() {
        setRenderColor(WHITE_COLOR);
    }

    public static void setRenderColor(int color) {
        GlStateManager.color(((color >> 16) & 0xff) / 255.0f, ((color >> 8) & 0xff) / 255.0f, (color & 0xff) / 255.0f, 1.0F);
    }

    public static int getTextWidth(String text) {
        return Minecraft.getMinecraft().fontRenderer.getStringWidth(text);
    }

    public static void renderText(String text, Position position, int color) {
        renderText(text, position, color, 1.0f, false, false);
    }

    public static void renderText(String text, Position position, int color, float scale, boolean dropShadow, boolean center) {
        renderText(text, position.getPosX(), position.getPosY(), color, scale, dropShadow, center);
    }

    public static void renderText(String text, int posX, int posY, int color, float scale, boolean dropShadow, boolean center) {
        GlStateManager.pushMatrix();
        FontRenderer fontRenderer = Minecraft.getMinecraft().fontRenderer;
        double scaledTextWidth = center ? fontRenderer.getStringWidth(text) * scale : 0.0;
        GlStateManager.translate(posX - scaledTextWidth / 2.0, posY, 0.0f);
        GlStateManager.scale(scale, scale, scale);
        fontRenderer.drawString(text, 0, 0, color, dropShadow);
        GlStateManager.popMatrix();
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
