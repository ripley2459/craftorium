package fr.cyrilneveu.craftorium.api.inventory;

import fr.cyrilneveu.craftorium.api.utils.Position;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nullable;

public final class FluidSlotData extends ASlotData {
    private final int capacity;
    @Nullable
    private FluidStack fluidStack = null;

    public FluidSlotData(Position position, int index, ESlotFlow flow, int capacity) {
        super(position, index, flow);
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }
}
