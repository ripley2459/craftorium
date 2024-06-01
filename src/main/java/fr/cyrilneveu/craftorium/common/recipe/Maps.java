package fr.cyrilneveu.craftorium.common.recipe;

import fr.cyrilneveu.craftorium.api.recipe.machine.RecipeMap;
import fr.cyrilneveu.craftorium.api.recipe.machine.RecipeMapBuilder;

import static fr.cyrilneveu.craftorium.api.Registries.MACHINE_MAPS_REGISTRY;

public final class Maps {
    public static RecipeMap ELECTROLYZING;

    static {
        ELECTROLYZING = new RecipeMapBuilder("electrolyzer", 1, 1, 6, 6).build();
    }

    public static void close() {
        MACHINE_MAPS_REGISTRY.order().close();

        MACHINE_MAPS_REGISTRY.getAll().forEach((k, v) -> v.close());
    }
}
