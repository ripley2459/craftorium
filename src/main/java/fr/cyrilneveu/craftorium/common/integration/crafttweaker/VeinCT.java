package fr.cyrilneveu.craftorium.common.integration.crafttweaker;

import com.google.common.base.Preconditions;
import crafttweaker.annotations.ZenRegister;
import fr.cyrilneveu.craftorium.Craftorium;
import fr.cyrilneveu.craftorium.api.substance.Substance;
import fr.cyrilneveu.craftorium.api.utils.Utils;
import fr.cyrilneveu.craftorium.api.world.vein.Vein;
import fr.cyrilneveu.craftorium.common.world.Veins;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import java.lang.reflect.Field;
import java.util.Map;

import static fr.cyrilneveu.craftorium.CraftoriumTags.MODID;
import static fr.cyrilneveu.craftorium.api.Registries.VEINS_REGISTRY;

@ZenClass("mods." + MODID + ".vein.Veins")
@ZenRegister
public final class VeinCT {
    @ZenMethod
    public static VeinBuilderCT create(String name, int minY, int maxY, int sizeH, int sizeV, int chance, int dimension, Object... composition) {
        Veins.init();

        return new VeinBuilderCT(name, minY, maxY, sizeH, sizeV, chance, dimension, composition);
    }

    @ZenMethod
    public static Vein get(String name) {
        Veins.init();

        return VEINS_REGISTRY.get(name);
    }

    @ZenMethod
    public static VeinBuilderCT modify(String name) {
        Veins.init();

        Preconditions.checkArgument(VEINS_REGISTRY.isOpen());
        Preconditions.checkArgument(VEINS_REGISTRY.contains(name));

        return modify(VEINS_REGISTRY.get(name));
    }

    @ZenMethod
    public static VeinBuilderCT modify(Vein vein) { // I'm not really proud of this method.
        Veins.init();

        Preconditions.checkArgument(VEINS_REGISTRY.isOpen());

        int i = 0;
        Object[] composition = new Object[vein.getComposition().size() * 2];
        for (Map.Entry<Substance, Integer> pair : vein.getComposition().getValues().entrySet()) {
            composition[i++] = pair.getKey();
            composition[i++] = pair.getValue();
        }

        VeinBuilderCT builder = new VeinBuilderCT(vein.getName(), vein.getMinY(), vein.getMaxY(), vein.getSizeH(), vein.getSizeV(), vein.getChance(), vein.getDimension(), composition);

        Field field = Utils.getField(fr.cyrilneveu.craftorium.common.world.Veins.class, ("vein_" + vein.getName()).toUpperCase());
        if (field != null)
            builder.setModificationMode(field);

        return builder;
    }

    @ZenMethod
    public static boolean remove(String name) {
        Veins.init();

        boolean flag = VEINS_REGISTRY.remove(name);

        if (flag) {
            Field field = Utils.getField(fr.cyrilneveu.craftorium.common.world.Veins.class, ("vein_" + name).toUpperCase());
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
