package fr.cyrilneveu.craftorium.api.recipe.vanilla;

import fr.cyrilneveu.craftorium.api.inventory.OreStack;
import fr.cyrilneveu.craftorium.api.recipe.machine.MachineRecipeBuilder;
import fr.cyrilneveu.craftorium.api.substance.Substance;
import fr.cyrilneveu.craftorium.api.substance.object.ASubstanceObject;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

import static fr.cyrilneveu.craftorium.CraftoriumTags.MODID;
import static fr.cyrilneveu.craftorium.common.recipe.Maps.*;

public final class RecipeManager {
    public static void removeRecipe(ResourceLocation location) {
        ForgeRegistries.RECIPES.register(new DummyRecipe().setRegistryName(location));
    }

    public static void addShapelessRecipe(String name, ItemStack output, Ingredient... inputs) {
        GameRegistry.addShapelessRecipe(new ResourceLocation(MODID, name), new ResourceLocation(MODID, "recipes"), output, inputs);
    }

    public static void addShapedRecipe(String name, ItemStack output, Object... params) {
        GameRegistry.addShapedRecipe(new ResourceLocation(MODID, name), new ResourceLocation(MODID, "recipes"), output, params);
    }

    public static void modifyShapedRecipe(String name, ItemStack output, Object... params) {
        GameRegistry.addShapedRecipe(new ResourceLocation("minecraft", name), null, output, params);
    }

    public static void zip(Substance substance, ASubstanceObject input, ASubstanceObject result) {
        zip(String.join("_", result.getName(substance), "from", input.getName(substance)), input.asIngredient(substance), result.asItemStack(substance));
        COMPRESSING.addRecipe(new MachineRecipeBuilder(result.getName(substance).concat(input.getName(substance))).consumeItem(input.getOre(substance), 9).produceItem(result.asItemStack(substance, 1)).consumeEnergy(1000).duration(100).configuration(CONFIGURATION_COMPRESSOR_ZIP).build());
    }

    public static void zip(String name, Ingredient input, ItemStack result) {
        addShapedRecipe(name, result, "III", "III", "III", 'I', input);
    }

    public static void unzip(Substance substance, ASubstanceObject input, ASubstanceObject result) {
        unzip(String.join("_", result.getName(substance), "from", input.getName(substance)), input.asIngredient(substance), result.asItemStack(substance, 9));
        COMPRESSING.addRecipe(new MachineRecipeBuilder(result.getName(substance).concat(input.getName(substance))).consumeItem(input.getOre(substance), 1).produceItem(result.asItemStack(substance, 9)).consumeEnergy(1000).duration(100).configuration(CONFIGURATION_COMPRESSOR_UNZIP).build());
    }

    public static void unzip(String name, Ingredient input, ItemStack result) {
        addShapelessRecipe(name, result, input);
    }

    public static void addFurnaceRecipe(ItemStack input, ItemStack output) {
        addFurnaceRecipe(input, output, 0f);
    }

    public static void addFurnaceRecipe(OreStack input, ItemStack output) {
        addFurnaceRecipe(input, output, 0.0f);
    }

    public static void addFurnaceRecipe(OreStack input, ItemStack output, float experience) {
        for (ItemStack inputStack : input.getStacks())
            addFurnaceRecipe(inputStack, output, experience);
    }

    public static void addFurnaceRecipe(ItemStack input, ItemStack output, float experience) {
        if (input.isEmpty() || output.isEmpty())
            return;

        FurnaceRecipes recipes = FurnaceRecipes.instance();
        if (recipes.getSmeltingResult(input).isEmpty())
            recipes.addSmeltingRecipe(input, output, experience);
    }

    public static void removeFurnaceRecipe(ItemStack input) {
        FurnaceRecipes.instance().getSmeltingList().keySet().removeIf(s -> s.getItem() == input.getItem() && (s.getMetadata() == OreDictionary.WILDCARD_VALUE || s.getMetadata() == input.getMetadata()));
    }
}
