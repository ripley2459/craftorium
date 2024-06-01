package fr.cyrilneveu.craftorium.api.recipe.machine;

import static fr.cyrilneveu.craftorium.api.Registries.MACHINE_MAPS_REGISTRY;

public final class RecipeMapBuilder {
    private String name;
    private int itemsIn = 0;
    private int fluidsIn = 0;
    private int itemsOut = 0;
    private int fluidsOut = 0;

    public RecipeMapBuilder(String name, int itemsIn, int fluidsIn, int itemsOut, int fluidsOut) {
        this.name = name;
        this.itemsIn = itemsIn;
        this.fluidsIn = fluidsIn;
        this.itemsOut = itemsOut;
        this.fluidsOut = fluidsOut;
    }

    public RecipeMap build() {
        RecipeMap map = new RecipeMap(name, itemsIn, fluidsIn, itemsOut, fluidsOut);

        MACHINE_MAPS_REGISTRY.put(name, map);
        return map;
    }
}