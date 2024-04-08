package fr.cyrilneveu.craftorium.api.utils;

import com.google.common.base.CaseFormat;
import fr.cyrilneveu.craftorium.api.render.ModelBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import org.apache.commons.lang3.StringUtils;
import org.lwjgl.input.Keyboard;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Function;

public final class Utils {
    public static final Function<Block, CustomStateMapper> SIMPLE_STATE_MAPPER = block -> new CustomStateMapper(getSimpleModelLocation(block));
    public static final ModelBuilder ITEM_MODEL_BUILDER = new ModelBuilder(DefaultVertexFormats.ITEM);
    public static final ModelBuilder BLOCK_MODEL_BUILDER = new ModelBuilder(DefaultVertexFormats.BLOCK);
    public static final int ERROR_COLOR = 0xFFff00ff;
    public static final int WHITE_COLOR = 0xFFffffff;

    public static String localise(String localisationKey, Object... substitutions) {
        return FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER ? net.minecraft.util.text.translation.I18n.translateToLocalFormatted(localisationKey, substitutions) : net.minecraft.client.resources.I18n.format(localisationKey, substitutions);
    }

    public static boolean isShiftDown() {
        return Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT);
    }

    public static boolean isCtrlDown() {
        return Keyboard.isKeyDown(Keyboard.KEY_LCONTROL) || Keyboard.isKeyDown(Keyboard.KEY_RCONTROL);
    }

    public static boolean isAdvancedTooltipsOn() {
        return Minecraft.getMinecraft().gameSettings.advancedItemTooltips;
    }

    public static String toCamelCase(String s) {
        return CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, s);
    }

    public static String[] cleanArray(String[] array) {
        return Arrays.stream(array).filter(x -> !StringUtils.isBlank(x)).toArray(String[]::new);
    }

    public static <I> boolean atLeastOne(Iterable<I> iterable, Function<I, Boolean> condition) {
        for (I value : iterable) {
            if (condition.apply(value))
                return true;
        }

        return false;
    }

    public static <I> boolean atLeastHalf(Iterable<I> iterable, Function<I, Boolean> condition) {
        int size = 0;
        int validate = 0;
        for (I value : iterable) {
            size++;
            if (condition.apply(value))
                validate++;
        }

        return validate > size / 2;
    }

    public static ModelResourceLocation getSimpleModelLocation(Block block) {
        return new ModelResourceLocation(Block.REGISTRY.getNameForObject(block), "");
    }

    public static ModelResourceLocation getSimpleModelLocation(Item item) {
        return new ModelResourceLocation(Objects.requireNonNull(Item.REGISTRY.getNameForObject(item)), "inventory");
    }

    public static TextureMap getTextureMap() {
        return Minecraft.getMinecraft().getTextureMapBlocks();
    }

    public static TextureAtlasSprite getTexture(String location) {
        return getTextureMap().getAtlasSprite(location);
    }

    public static TextureAtlasSprite getTexture(ResourceLocation location) {
        return getTexture(location.toString());
    }

    private static class CustomStateMapper extends StateMapperBase {
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
