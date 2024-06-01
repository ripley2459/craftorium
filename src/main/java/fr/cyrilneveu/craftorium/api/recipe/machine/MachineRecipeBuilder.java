package fr.cyrilneveu.craftorium.api.recipe.machine;

import com.google.common.base.Preconditions;
import crafttweaker.CraftTweakerAPI;
import crafttweaker.api.item.IIngredient;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.item.IngredientStack;
import crafttweaker.api.liquid.ILiquidStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import crafttweaker.api.oredict.IOreDictEntry;
import fr.cyrilneveu.craftorium.api.inventory.OreStack;
import fr.cyrilneveu.craftorium.api.utils.WeightedList;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

import static fr.cyrilneveu.craftorium.api.machine.behaviour.RecipeProcessor.MACHINE_CONFIGURATION_MAX;
import static fr.cyrilneveu.craftorium.api.machine.behaviour.RecipeProcessor.MACHINE_CONFIGURATION_MIN;

public final class MachineRecipeBuilder {
    private String name;
    private List<OreStack> itemsIn = new ArrayList<>();
    private List<FluidStack> fluidsIn = new ArrayList<>();
    private WeightedList<ItemStack> itemsOut = new WeightedList<>();
    private WeightedList<FluidStack> fluidsOut = new WeightedList<>();
    private int energyIn = 5000;
    private int energyOut = 0;
    private int duration = 200;
    private int configuration = MACHINE_CONFIGURATION_MIN;

    public MachineRecipeBuilder(String name) {
        this.name = name;
    }

    @Nullable
    private static OreStack fromIIngredient(IIngredient input) {
        Preconditions.checkArgument(input != null);
        if (input instanceof IItemStack)
            return new OreStack(CraftTweakerMC.getItemStack((IItemStack) input));
        if (input instanceof IOreDictEntry)
            return new OreStack(((IOreDictEntry) input).getName(), 1);
        if (input instanceof IngredientStack && input.getInternal() instanceof IOreDictEntry)
            return new OreStack(((IOreDictEntry) input.getInternal()).getName(), input.getAmount());
        return null;
    }

    private static int validateChance(int chance) {
        return chance < 0 ? 0 : Math.min(chance, 100);
    }

    private static int validateEnergy(int energy) {
        return Math.max(0, energy);
    }

    private static int validateDuration(int duration) {
        return Math.max(0, duration);
    }

    private static int validateConfiguration(int configuration) {
        return MathHelper.clamp(configuration, MACHINE_CONFIGURATION_MIN, MACHINE_CONFIGURATION_MAX);
    }

    public MachineRecipeBuilder consumeItem(IIngredient ingredient) {
        OreStack input = fromIIngredient(ingredient);

        if (input == null)
            CraftTweakerAPI.logError("Unknown ingredient: " + ingredient);
        else return consumeItem(input);

        return this;
    }

    public MachineRecipeBuilder consumeItem(String ore, int amount) {
        return consumeItem(new OreStack(ore, amount));
    }

    public MachineRecipeBuilder consumeItem(ItemStack stack) {
        return consumeItem(new OreStack(stack));
    }

    public MachineRecipeBuilder consumeItem(OreStack oreStack) {
        this.itemsIn.add(oreStack);
        return this;
    }

    public MachineRecipeBuilder itemNotConsumed(IItemStack itemStack) {
        return itemNotConsumed(CraftTweakerMC.getItemStack(itemStack));
    }

    public MachineRecipeBuilder itemNotConsumed(ItemStack stack) {
        this.itemsIn.add(new OreStack(stack).setNotConsumable());
        return this;
    }

    public MachineRecipeBuilder consumeFluid(ILiquidStack liquidStack) {
        FluidStack fluidStack = CraftTweakerMC.getLiquidStack(liquidStack);
        return consumeFluid(fluidStack);
    }

    public MachineRecipeBuilder consumeFluid(String fluid, int amount) {
        return consumeFluid(FluidRegistry.getFluidStack(fluid, amount));
    }

    public MachineRecipeBuilder consumeFluid(FluidStack fluidStack) {
        this.fluidsIn.add(fluidStack);
        return this;
    }

    public MachineRecipeBuilder produceItem(IItemStack itemStack) {
        return produceItem(itemStack, 100);
    }

    public MachineRecipeBuilder produceItem(IItemStack itemStack, int chance) {
        return produceItem(CraftTweakerMC.getItemStack(itemStack), chance);
    }

    public MachineRecipeBuilder produceItem(ItemStack itemStack) {
        return produceItem(itemStack, 100);
    }

    public MachineRecipeBuilder produceItem(ItemStack itemStack, int chance) {
        chance = validateChance(chance);
        this.itemsOut.put(itemStack, chance);
        return this;
    }

    public MachineRecipeBuilder produceFluid(ILiquidStack liquidStack) {
        return produceFluid(liquidStack, 100);
    }

    public MachineRecipeBuilder produceFluid(ILiquidStack liquidStack, int chance) {
        FluidStack fluidStack = CraftTweakerMC.getLiquidStack(liquidStack);
        return produceFluid(fluidStack, chance);
    }

    public MachineRecipeBuilder produceFluid(String fluid, int amount) {
        return produceFluid(fluid, amount, 100);
    }

    public MachineRecipeBuilder produceFluid(String fluid, int amount, int chance) {
        Preconditions.checkArgument(amount > 0);
        return produceFluid(FluidRegistry.getFluidStack(fluid, amount), chance);
    }

    public MachineRecipeBuilder produceFluid(FluidStack fluidStack, int chance) {
        chance = validateChance(chance);
        this.fluidsOut.put(fluidStack, chance);
        return this;
    }

    public MachineRecipeBuilder consumeEnergy(int amount) {
        this.energyIn = validateEnergy(amount);
        return this;
    }

    public MachineRecipeBuilder produceEnergy(int amount) {
        this.energyOut = validateEnergy(amount);
        return this;
    }

    public MachineRecipeBuilder duration(int duration) {
        this.duration = validateDuration(duration);
        return this;
    }

    public MachineRecipeBuilder configuration(int configuration) {
        this.configuration = validateConfiguration(configuration);
        return this;
    }

    public MachineRecipe build() {
        return new MachineRecipe(name, itemsIn, fluidsIn, itemsOut, fluidsOut, energyIn, energyOut, duration, configuration);
    }
}
