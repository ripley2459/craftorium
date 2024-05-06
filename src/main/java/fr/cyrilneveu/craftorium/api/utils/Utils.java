package fr.cyrilneveu.craftorium.api.utils;

import com.google.common.base.CaseFormat;
import fr.cyrilneveu.craftorium.api.render.ModelBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import org.apache.commons.lang3.StringUtils;
import org.lwjgl.input.Keyboard;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static fr.cyrilneveu.craftorium.CraftoriumTags.MODID;

public final class Utils {
    public static final float EPSILON = 0.001f;
    public static final int ERROR_COLOR = 0xFFff00ff;
    public static final int WHITE_COLOR = 0xFFffffff;
    public static final int BLACK_COLOR = 0xFF000000;
    public static final float BASE_TEMPERATURE = 273.15f;
    public static final String ENERGY_NBT_KEY = "Energy";
    public static final Function<Block, CustomStateMapper> SIMPLE_STATE_MAPPER = block -> new CustomStateMapper(getSimpleModelLocation(block));
    public static final ModelBuilder ITEM_MODEL_BUILDER = new ModelBuilder(DefaultVertexFormats.ITEM);
    public static final ModelBuilder BLOCK_MODEL_BUILDER = new ModelBuilder(DefaultVertexFormats.BLOCK);
    public static final IItemBehaviour[] NO_BEHAVIOUR = new IItemBehaviour[0];
    public static final ResourceLocation BACKGROUND = new ResourceLocation(MODID, "textures/interfaces/elements/background.png");

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

    public static Supplier<List<String>> generateTooltipProvider(String... localisationKeys) {
        return localisationKeys.length == 0 ? Collections::emptyList : () -> Arrays.asList(localisationKeys);
    }

    public static String toCamelCase(String s) {
        return CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, s);
    }

    public static String[] cleanArray(String[] array) {
        return Arrays.stream(array).filter(x -> !StringUtils.isBlank(x)).toArray(String[]::new);
    }

    public static <I> boolean all(I[] iterable, Function<I, Boolean> condition) {
        return all(Arrays.asList(iterable), condition);
    }

    public static <I> boolean all(Iterable<I> iterable, Function<I, Boolean> condition) {
        for (I value : iterable) {
            if (!condition.apply(value))
                return false;
        }

        return true;
    }

    public static <I> boolean atLeastOne(I[] iterable, Function<I, Boolean> condition) {
        return atLeastOne(Arrays.asList(iterable), condition);
    }

    public static <I> boolean atLeastOne(Iterable<I> iterable, Function<I, Boolean> condition) {
        for (I value : iterable) {
            if (condition.apply(value))
                return true;
        }

        return false;
    }

    public static <I> boolean atLeastHalf(I[] iterable, Function<I, Boolean> condition) {
        return atLeastHalf(Arrays.asList(iterable), condition);
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

    @Nullable
    public static <I> I first(I[] iterable, Function<I, Boolean> condition) {
        return first(Arrays.asList(iterable), condition);
    }

    @Nullable
    public static <I> I first(Iterable<I> iterable, Function<I, Boolean> condition) {
        for (I value : iterable) {
            if (condition.apply(value))
                return value;
        }

        return null;
    }

    public static String numbersToDown(String input) {
        Pattern pattern = Pattern.compile("\\d");
        Matcher matcher = pattern.matcher(input);
        StringBuffer buffer = new StringBuffer();
        while (matcher.find()) {
            int digit = Integer.parseInt(matcher.group());
            String replacement = toSubscript(digit);
            matcher.appendReplacement(buffer, replacement);
        }

        matcher.appendTail(buffer);
        return buffer.toString();
    }

    private static String toSubscript(int digit) {
        char subscriptDigit = (char) ('\u2080' + digit);
        return Character.toString(subscriptDigit);
    }

    public static ModelResourceLocation getSimpleModelLocation(Block block) {
        return new ModelResourceLocation(Block.REGISTRY.getNameForObject(block), "");
    }

    public static ModelResourceLocation getSimpleModelLocation(Item item) {
        return new ModelResourceLocation(Objects.requireNonNull(Item.REGISTRY.getNameForObject(item)), "inventory");
    }

    public static TextureAtlasSprite getTexture(ResourceLocation location) {
        return Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(location.toString());
    }

    public static NBTTagCompound getNBT(ItemStack itemStack) {
        if (!itemStack.hasTagCompound())
            itemStack.setTagCompound(new NBTTagCompound());
        return itemStack.getTagCompound();
    }

    public static int getIntValue(String name, ItemStack itemStack) {
        return getIntValue(name, getNBT(itemStack));
    }

    public static int getIntValue(String name, NBTTagCompound nbt) {
        return nbt.getInteger(name);
    }

    public static NBTTagCompound setValue(String name, int value, ItemStack itemStack) {
        return setValue(name, value, getNBT(itemStack));
    }

    public static NBTTagCompound setValue(String name, int value) {
        return setValue(name, value, new NBTTagCompound());
    }

    public static NBTTagCompound setValue(String name, int value, NBTTagCompound nbt) {
        nbt.setInteger(name, value);
        return nbt;
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
