package fr.cyrilneveu.craftorium.api.recipe.machine;

import com.google.common.base.Preconditions;
import fr.cyrilneveu.craftorium.api.inventory.OreStack;
import fr.cyrilneveu.craftorium.api.utils.Registry;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nullable;
import java.util.List;

public class RecipeMap {
    private final String name;
    private final int itemsIn;
    private final int fluidsIn;
    private final int itemsOut;
    private final int fluidsOut;
    private final Registry<String, MachineRecipe> recipes = new Registry<>();

    public RecipeMap(String name, int itemsIn, int fluidsIn, int itemsOut, int fluidsOut) {
        this.name = name;
        this.itemsIn = itemsIn;
        this.fluidsIn = fluidsIn;
        this.itemsOut = itemsOut;
        this.fluidsOut = fluidsOut;
    }

    public static boolean checkItems(List<ItemStack> proposed, List<OreStack> required) {
        for (OreStack asked : required) {
            int amount = asked.getAmount();
            for (ItemStack given : proposed) {
                if (asked.matches(given))
                    amount -= given.getCount();
            }

            if (amount > 0)
                return false;
        }

        return true;
    }

    public static boolean checkFluids(List<FluidStack> proposed, List<FluidStack> required) {
        for (FluidStack asked : required) {
            int amount = asked.amount;
            for (FluidStack given : proposed) {
                if (given.isFluidEqual(asked))
                    amount -= given.amount;
            }

            if (amount > 0)
                return false;
        }

        return true;
    }

    public static boolean isRecipeValid(List<ItemStack> proposedItems, List<FluidStack> proposedFluids, int configuration, MachineRecipe recipe) {
        if (configuration == recipe.getConfiguration() && proposedItems.size() >= recipe.getItemsIn().size() && proposedFluids.size() >= recipe.getFluidsIn().size())
            return checkItems(proposedItems, recipe.getItemsIn()) && checkFluids(proposedFluids, recipe.getFluidsIn());
        return false;
    }

    public boolean addRecipe(MachineRecipe recipe) {
        Preconditions.checkArgument(recipe.getItemsIn().size() <= itemsIn);
        Preconditions.checkArgument(recipe.getFluidsIn().size() <= fluidsIn);
        Preconditions.checkArgument(recipe.getItemsOut().size() <= itemsOut);
        Preconditions.checkArgument(recipe.getFluidsOut().size() <= fluidsOut);
        Preconditions.checkArgument(!recipes.contains(recipe.getName()));
        return recipes.put(recipe.getName(), recipe);
    }

    public MachineRecipe getRecipe(String name) {
        return recipes.get(name);
    }

    public boolean removeRecipe(String name) {
        return recipes.remove(name);
    }

    @Nullable
    public MachineRecipe getRecipe(List<ItemStack> items, List<FluidStack> fluids, int configuration, @Nullable MachineRecipe cache) {
        if (cache != null && isRecipeValid(items, fluids, configuration, cache))
            return cache;

        for (MachineRecipe recipe : recipes.getAll().values()) {
            if (isRecipeValid(items, fluids, configuration, recipe))
                return recipe;
        }

        return null;
    }

    public String getName() {
        return name;
    }

    public int getItemsIn() {
        return itemsIn;
    }

    public int getFluidsIn() {
        return fluidsIn;
    }

    public int getItemsOut() {
        return itemsOut;
    }

    public int getFluidsOut() {
        return fluidsOut;
    }

    public Registry<String, MachineRecipe> getRecipes() {
        return recipes;
    }

    public void close() {
        recipes.close();
    }
}
