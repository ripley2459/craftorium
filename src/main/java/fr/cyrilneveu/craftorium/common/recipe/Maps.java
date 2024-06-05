package fr.cyrilneveu.craftorium.common.recipe;

import fr.cyrilneveu.craftorium.api.recipe.machine.RecipeMap;
import fr.cyrilneveu.craftorium.api.recipe.machine.RecipeMapBuilder;

import static fr.cyrilneveu.craftorium.api.Registries.MACHINE_MAPS_REGISTRY;

public final class Maps {
    public static final int CONFIGURATION_COMPRESSOR_ZIP = 1;
    public static final int CONFIGURATION_COMPRESSOR_UNZIP = 2;
    public static final int CONFIGURATION_COMPRESSOR_PRESSING = 3;
    public static final int CONFIGURATION_BENDER_REFORM = 1;
    public static final int CONFIGURATION_CUTTER_FINE = 1;
    public static final int CONFIGURATION_CUTTER_NORMAL = 2;
    public static final int CONFIGURATION_CUTTER_LARGE = 3;
    public static final int CONFIGURATION_MACERATOR_PULVERIZING = 1;
    public static final int CONFIGURATION_LATHE_SIMPLE = 1;
    public static final int CONFIGURATION_LATHE_ADVANCED = 2;
    public static RecipeMap ELECTROLYZING;
    public static RecipeMap MACERATING;
    public static RecipeMap BENDING;
    public static RecipeMap SPINNING;
    public static RecipeMap CUTTING;
    public static RecipeMap COMPRESSING;

    static {
        ELECTROLYZING = new RecipeMapBuilder("electrolyzing", 1, 1, 6, 6).build();
        MACERATING = new RecipeMapBuilder("macerating", 1, 0, 1, 0).build();
        BENDING = new RecipeMapBuilder("bending", 1, 0, 1, 0).build();
        SPINNING = new RecipeMapBuilder("spinning", 1, 0, 1, 0).build();
        CUTTING = new RecipeMapBuilder("cutting", 1, 0, 1, 0).build();
        COMPRESSING = new RecipeMapBuilder("cutting", 1, 0, 1, 0).build();
    }

    public static void close() {
        MACHINE_MAPS_REGISTRY.order().close();

        MACHINE_MAPS_REGISTRY.getAll().forEach((k, v) -> v.close());
    }
}
