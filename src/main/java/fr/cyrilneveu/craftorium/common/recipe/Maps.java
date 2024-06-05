package fr.cyrilneveu.craftorium.common.recipe;

import fr.cyrilneveu.craftorium.api.recipe.machine.RecipeMap;
import fr.cyrilneveu.craftorium.api.recipe.machine.RecipeMapBuilder;

import static fr.cyrilneveu.craftorium.api.Registries.MACHINE_MAPS_REGISTRY;

public final class Maps {
    public static RecipeMap ELECTROLYZING;
    public static RecipeMap MACERATING;
    public static RecipeMap BENDING;
    public static RecipeMap SPINNING;

    static {
        ELECTROLYZING = new RecipeMapBuilder("electrolyzing", 1, 1, 6, 6).build();
        MACERATING = new RecipeMapBuilder("macerating", 1, 0, 1, 0).build();
        BENDING = new RecipeMapBuilder("bending", 1, 0, 1, 0).build();
        SPINNING = new RecipeMapBuilder("spinning", 1, 0, 1, 0).build();
    }

    public static void close() {
        MACHINE_MAPS_REGISTRY.order().close();

        MACHINE_MAPS_REGISTRY.getAll().forEach((k, v) -> v.close());
    }
}
