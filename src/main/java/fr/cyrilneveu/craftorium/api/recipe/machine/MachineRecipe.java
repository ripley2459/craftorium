package fr.cyrilneveu.craftorium.api.recipe.machine;

import fr.cyrilneveu.craftorium.api.inventory.OreStack;
import fr.cyrilneveu.craftorium.api.utils.WeightedList;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import java.util.List;

public final class MachineRecipe {
    private final String name;
    private final List<OreStack> itemsIn;
    private final List<FluidStack> fluidsIn;
    private final WeightedList<ItemStack> itemsOut;
    private final WeightedList<FluidStack> fluidsOut;
    private final int energyIn;
    private final int energyOut;
    private final int duration;
    private final int configuration;

    public MachineRecipe(String name, List<OreStack> itemsIn, List<FluidStack> fluidsIn, WeightedList<ItemStack> itemsOut, WeightedList<FluidStack> fluidsOut, int energyIn, int energyOut, int duration, int configuration) {
        this.name = name;
        this.itemsIn = itemsIn;
        this.fluidsIn = fluidsIn;
        this.itemsOut = itemsOut;
        this.fluidsOut = fluidsOut;
        this.energyIn = energyIn;
        this.energyOut = energyOut;
        this.duration = duration;
        this.configuration = configuration;
    }

    public String getName() {
        return name;
    }

    public List<OreStack> getItemsIn() {
        return itemsIn;
    }

    public List<FluidStack> getFluidsIn() {
        return fluidsIn;
    }

    public WeightedList<ItemStack> getItemsOut() {
        return itemsOut;
    }

    public WeightedList<FluidStack> getFluidsOut() {
        return fluidsOut;
    }

    public int getEnergyIn() {
        return energyIn;
    }

    public int getEnergyOut() {
        return energyOut;
    }

    public int getDuration() {
        return duration;
    }

    public int getConfiguration() {
        return configuration;
    }
}
