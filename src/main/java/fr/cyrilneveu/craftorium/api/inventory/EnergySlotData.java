package fr.cyrilneveu.craftorium.api.inventory;

import fr.cyrilneveu.craftorium.api.utils.Position;

public final class EnergySlotData extends ASlotData {
    private final int capacity;
    private final int transfer;

    public EnergySlotData(Position position, int capacity, int transfer) {
        super(position, 0, ESlotFlow.FREE);
        this.capacity = capacity;
        this.transfer = transfer;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getTransfer() {
        return transfer;
    }
}
