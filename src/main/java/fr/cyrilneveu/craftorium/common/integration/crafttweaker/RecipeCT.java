package fr.cyrilneveu.craftorium.common.integration.crafttweaker;

import crafttweaker.annotations.ZenRegister;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import static fr.cyrilneveu.craftorium.CraftoriumTags.MODID;

@ZenClass("mods." + MODID + ".recipe.Recipes")
@ZenRegister
public final class RecipeCT {
    @ZenMethod
    public static MachineRecipeBuilderCT create(String name) {
        return new MachineRecipeBuilderCT(name);
    }
}
