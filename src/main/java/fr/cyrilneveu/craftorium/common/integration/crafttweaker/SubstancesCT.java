package fr.cyrilneveu.craftorium.common.integration.crafttweaker;

import com.google.common.base.Preconditions;
import crafttweaker.annotations.ZenRegister;
import fr.cyrilneveu.craftorium.Craftorium;
import fr.cyrilneveu.craftorium.api.substance.Element;
import fr.cyrilneveu.craftorium.api.substance.Substance;
import fr.cyrilneveu.craftorium.api.substance.SubstanceStack;
import fr.cyrilneveu.craftorium.api.substance.object.ASubstanceObject;
import fr.cyrilneveu.craftorium.api.utils.Utils;
import fr.cyrilneveu.craftorium.common.substance.Substances;
import net.minecraft.block.SoundType;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import java.lang.reflect.Field;
import java.util.Map;

import static fr.cyrilneveu.craftorium.CraftoriumTags.MODID;
import static fr.cyrilneveu.craftorium.api.Registries.SUBSTANCES_REGISTRY;

@ZenClass("mods." + MODID + ".substance.Substances")
@ZenRegister
public final class SubstancesCT {
    @ZenMethod
    public static SubstanceBuilderCT create(String name) {
        Substances.init();

        return new SubstanceBuilderCT(name);
    }

    @ZenMethod
    public static Substance get(String name) {
        Substances.init();

        return SUBSTANCES_REGISTRY.get(name);
    }

    @ZenMethod
    public static SubstanceBuilderCT modify(String name) {
        Substances.init();

        Preconditions.checkArgument(SUBSTANCES_REGISTRY.isOpen());
        Preconditions.checkArgument(SUBSTANCES_REGISTRY.contains(name));

        return modify(SUBSTANCES_REGISTRY.get(name));
    }

    @ZenMethod
    public static SubstanceBuilderCT modify(Substance substance) { // I'm not really proud of this method.
        Substances.init();

        Preconditions.checkArgument(SUBSTANCES_REGISTRY.isOpen());

        SubstanceBuilderCT builder = new SubstanceBuilderCT(substance.getName());

        Field field = Utils.getField(fr.cyrilneveu.craftorium.common.substance.Substances.class, substance.getName().toUpperCase());
        if (field != null)
            builder.setModificationMode(field);

        if (substance.getComposition().isNative()) {
            Element element = substance.getComposition().getElement();
            builder.element(element.getAtomicNumber(), element.getSymbol(), element.getName(), element.getGroup(), element.getAtomicMass());
        } else {
            int i = 0;
            Object[] composition = new Object[substance.getComposition().getComposition().size() * 2];
            for (SubstanceStack substanceStack : substance.getComposition().getComposition()) {
                composition[i++] = substanceStack.getSubstance();
                composition[i++] = substanceStack.getAmount();
            }
            builder.composition(composition);

            i = 0;
            Object[] possible = new Object[substance.getComposition().getPossible().size() * 3];
            for (SubstanceStack substanceStack : substance.getComposition().getComposition()) {
                possible[i++] = substanceStack.getSubstance();
                possible[i++] = substanceStack.getAmount();
                possible[i++] = substanceStack.getChance();
            }
            builder.possible(possible);
        }

        substance.getProperties().forEach(builder::property);

        if (substance.getEfficiency() != null)
            builder.tools(substance.getEfficiency().getSpeed(), substance.getEfficiency().getDamage(), substance.getEfficiency().getDurability(), substance.getEfficiency().getHarvestLevel(), substance.getEfficiency().getEnchantability());

        builder.toughness(substance.getToughness().getHardness(), substance.getToughness().getResistance(), substance.getToughness().getToolClass(), substance.getToughness().getHarvestLevel());

        builder.temperature(substance.getTemperature().getMeltingPoint(), substance.getTemperature().getBoilingPoint());

        int i = 0;
        String[] items = new String[substance.getItems().size()];
        for (ASubstanceObject.SubstanceItem item : substance.getItems())
            items[i++] = item.getName(null);
        builder.items(items);

        i = 0;
        String[] tools = new String[substance.getTools().size()];
        for (ASubstanceObject.SubstanceTool tool : substance.getTools())
            tools[i++] = tool.getName(null);
        builder.tools(tools);

        i = 0;
        String[] blocks = new String[substance.getBlocks().size()];
        for (ASubstanceObject.SubstanceBlock block : substance.getBlocks())
            blocks[i++] = block.getName(null);
        builder.blocks(blocks);

        i = 0;
        String[] fluids = new String[substance.getFluids().size()];
        for (ASubstanceObject.SubstanceFluid fluid : substance.getFluids())
            fluids[i++] = fluid.getName(null);
        builder.fluids(fluids);

        i = 0;
        Object[] overrides = new Object[substance.getOverrides().size() * 2];
        for (Map.Entry<ASubstanceObject, String> override : substance.getOverrides().entrySet()) {
            overrides[i++] = override.getKey();
            overrides[i++] = override.getValue();
        }
        builder.overrides(overrides);

        builder.color(substance.getAestheticism().getBaseColor(), substance.getAestheticism().getOreColor(), substance.getAestheticism().getFluidColor());
        builder.style(substance.getAestheticism().getStyle());
        if (substance.getAestheticism().isShiny())
            builder.shiny();
        if (substance.getAestheticism().isGlow())
            builder.glow();
        if (substance.getAestheticism().getSoundType().equals(SoundType.METAL))
            builder.sound("metal");
        else if (substance.getAestheticism().getSoundType().equals(SoundType.WOOD))
            builder.sound("wood");
        else if (substance.getAestheticism().getSoundType().equals(SoundType.STONE))
            builder.sound("stone");
        else if (substance.getAestheticism().getSoundType().equals(SoundType.SAND))
            builder.sound("sand");
        else builder.sound("metal");

        return builder;
    }

    @ZenMethod
    public static boolean remove(String name) {
        Substances.init();

        boolean flag = SUBSTANCES_REGISTRY.remove(name);

        if (flag) {
            Field field = Utils.getField(fr.cyrilneveu.craftorium.common.substance.Substances.class, name.toUpperCase());
            if (field != null) {
                try {
                    field.set(Substance.class, null);
                } catch (IllegalAccessException e) {
                    Craftorium.LOGGER.catching(e);
                }
            }
        }

        return flag;
    }
}
