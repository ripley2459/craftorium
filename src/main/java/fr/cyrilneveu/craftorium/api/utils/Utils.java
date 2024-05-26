package fr.cyrilneveu.craftorium.api.utils;

import com.google.common.base.CaseFormat;
import fr.cyrilneveu.craftorium.api.item.behaviour.IItemBehaviour;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelRotation;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import org.apache.commons.lang3.StringUtils;
import org.lwjgl.input.Keyboard;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Utils {
    public static final float EPSILON = 0.001f;
    public static final float BASE_TEMPERATURE = 273.15f;
    public static final int BASE_AMOUNT = 144;
    public static final String EMPTY_FLUID_STACK = "Empty";
    public static final IItemBehaviour[] NO_BEHAVIOUR = new IItemBehaviour[0];

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

    public static String formatFluidAmount(int amount) {
        return amount < 1000 ? amount + "mB" : String.format("%.1fB", (double) amount / Fluid.BUCKET_VOLUME);
    }

    public static ModelRotation getRotationForFacing(EnumFacing facing) {
        return switch (facing) {
            case DOWN -> ModelRotation.X90_Y0;
            case UP -> ModelRotation.X270_Y0;
            case NORTH -> ModelRotation.X0_Y0;
            case SOUTH -> ModelRotation.X0_Y180;
            case WEST -> ModelRotation.X0_Y270;
            case EAST -> ModelRotation.X0_Y90;
        };
    }
}
