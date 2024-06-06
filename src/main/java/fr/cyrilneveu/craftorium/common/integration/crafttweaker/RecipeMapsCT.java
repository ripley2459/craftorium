package fr.cyrilneveu.craftorium.common.integration.crafttweaker;

import crafttweaker.annotations.ZenRegister;
import fr.cyrilneveu.craftorium.api.recipe.machine.MachineRecipe;
import fr.cyrilneveu.craftorium.api.recipe.machine.RecipeMap;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import static fr.cyrilneveu.craftorium.CraftoriumTags.MODID;
import static fr.cyrilneveu.craftorium.api.Registries.MACHINE_MAPS_REGISTRY;

@ZenClass("mods." + MODID + ".recipe.Maps")
@ZenRegister
public final class RecipeMapsCT {
    private final RecipeMap map;

    private RecipeMapsCT(RecipeMap map) {
        this.map = map;
    }

    @ZenMethod
    public static RecipeMapsCT get(String name) {
        return new RecipeMapsCT(MACHINE_MAPS_REGISTRY.get(name));
    }

    @ZenMethod
    public boolean addRecipe(MachineRecipe recipe) {
        return map.addRecipe(recipe);
    }

    @ZenMethod
    public boolean removeRecipe(String name) {
        return map.removeRecipe(name);
    }
}
