package fr.cyrilneveu.craftorium.api.inventory;

import fr.cyrilneveu.craftorium.api.machine.behaviour.ESlotFlow;
import fr.cyrilneveu.craftorium.api.utils.Position;

public abstract class ASlotData {
    private final int index;
    private final Position position;
    private final ESlotFlow flow;

    public ASlotData(Position position, int index, ESlotFlow flow) {
        this.position = position;
        this.index = index;
        this.flow = flow;
    }

    public boolean isInput() {
        return flow == ESlotFlow.INPUT || flow == ESlotFlow.FREE;
    }

    public boolean isOutput() {
        return flow == ESlotFlow.OUTPUT || flow == ESlotFlow.FREE;
    }

    public Position getPosition() {
        return position;
    }

    public int getIndex() {
        return index;
    }

    public ESlotFlow getFlow() {
        return flow;
    }
}
