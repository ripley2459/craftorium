package fr.cyrilneveu.craftorium.api.inventory;

import fr.cyrilneveu.craftorium.api.machine.behaviour.ESlotFlow;
import fr.cyrilneveu.craftorium.api.utils.Position;

public final class FluidSlotData extends ASlot {
    private final int capacity;

    public FluidSlotData(Position position, int index, ESlotFlow flow, int capacity) {
        super(position, index, flow);
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }
}
