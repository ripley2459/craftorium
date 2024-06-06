package fr.cyrilneveu.craftorium.common.integration.crafttweaker;

import com.google.common.base.Preconditions;
import crafttweaker.CraftTweakerAPI;
import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.item.IIngredient;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.item.IngredientStack;
import crafttweaker.api.liquid.ILiquidStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import crafttweaker.api.oredict.IOreDictEntry;
import fr.cyrilneveu.craftorium.api.inventory.OreStack;
import fr.cyrilneveu.craftorium.api.recipe.machine.MachineRecipe;
import fr.cyrilneveu.craftorium.api.recipe.machine.MachineRecipeBuilder;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import javax.annotation.Nullable;

import static fr.cyrilneveu.craftorium.CraftoriumTags.MODID;

@ZenClass("mods." + MODID + ".recipe.Builder")
@ZenRegister
public final class MachineRecipeBuilderCT {
    private MachineRecipeBuilder builder;

    public MachineRecipeBuilderCT(String name) {
        this.builder = new MachineRecipeBuilder(name);
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

    @ZenMethod
    public MachineRecipeBuilderCT consumeItem(IIngredient ingredient) {
        OreStack input = fromIIngredient(ingredient);

        if (input == null)
            CraftTweakerAPI.logError("Unknown ingredient: " + ingredient);
        else builder.consumeItem(input);

        return this;
    }

    @ZenMethod
    public MachineRecipeBuilderCT consumeItem(String ore, int amount) {
        this.builder.consumeItem(new OreStack(ore, amount));
        return this;
    }

    @ZenMethod
    public MachineRecipeBuilderCT itemNotConsumed(IItemStack itemStack) {
        this.builder.itemNotConsumed(CraftTweakerMC.getItemStack(itemStack));
        return this;
    }

    @ZenMethod
    public MachineRecipeBuilderCT consumeFluid(ILiquidStack liquidStack) {
        this.builder.consumeFluid(CraftTweakerMC.getLiquidStack(liquidStack));
        return this;
    }

    @ZenMethod
    public MachineRecipeBuilderCT consumeFluid(String fluid, int amount) {
        this.builder.consumeFluid(fluid, amount);
        return this;
    }

    @ZenMethod
    public MachineRecipeBuilderCT produceItem(IItemStack itemStack) {
        return produceItem(itemStack, 100);
    }

    @ZenMethod
    public MachineRecipeBuilderCT produceItem(IItemStack itemStack, int chance) {
        this.builder.produceItem(CraftTweakerMC.getItemStack(itemStack), chance);
        return this;
    }

    @ZenMethod
    public MachineRecipeBuilderCT produceFluid(ILiquidStack liquidStack) {
        return produceFluid(liquidStack, 100);
    }

    @ZenMethod
    public MachineRecipeBuilderCT produceFluid(ILiquidStack liquidStack, int chance) {
        this.builder.produceFluid(CraftTweakerMC.getLiquidStack(liquidStack), chance);
        return this;
    }

    @ZenMethod
    public MachineRecipeBuilderCT produceFluid(String fluid, int amount) {
        return produceFluid(fluid, amount, 100);
    }

    @ZenMethod
    public MachineRecipeBuilderCT produceFluid(String fluid, int amount, int chance) {
        this.builder.produceFluid(fluid, amount, chance);
        return this;
    }

    @ZenMethod
    public MachineRecipeBuilderCT consumeEnergy(int amount) {
        this.builder.consumeEnergy(amount);
        return this;
    }

    @ZenMethod
    public MachineRecipeBuilderCT produceEnergy(int amount) {
        this.builder.produceEnergy(amount);
        return this;
    }

    @ZenMethod
    public MachineRecipeBuilderCT duration(int duration) {
        this.builder.duration(duration);
        return this;
    }

    @ZenMethod
    public MachineRecipeBuilderCT configuration(int configuration) {
        this.builder.configuration(configuration);
        return this;
    }

    @ZenMethod
    public MachineRecipe build() {
        return builder.build();
    }
}
