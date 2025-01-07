package fr.cyrilneveu.craftorium.api.inventory;

import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;

import javax.annotation.Nullable;

import static fr.cyrilneveu.craftorium.api.utils.Utils.EMPTY_FLUID_STACK;

public class CustomTank extends FluidTank {
    public CustomTank(int capacity) {
        super(capacity);
    }

    public CustomTank(@Nullable FluidStack fluidStack, int capacity) {
        super(fluidStack, capacity);
    }

    public String getFluidName() {
        if (fluid == null || fluid.amount <= 0)
            return EMPTY_FLUID_STACK;
        return fluid.getFluid().getName();
    }

    public boolean hasFluid() {
        return fluid != null && getFluidAmount() > 0;
    }

    public boolean isFull() {
        return getFluidAmount() >= getCapacity();
    }

    public void flush() {
        setFluid(null);
    }

    @Override
    protected void onContentsChanged() {
        if (tile != null)
            tile.markDirty();
    }
}
