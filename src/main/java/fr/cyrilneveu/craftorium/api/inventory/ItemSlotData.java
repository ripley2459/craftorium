package fr.cyrilneveu.craftorium.api.inventory;

import fr.cyrilneveu.craftorium.api.machine.behaviour.ESlotFlow;
import fr.cyrilneveu.craftorium.api.utils.Position;

public final class ItemSlotData extends ASlotData {
    public ItemSlotData(Position position, int index, ESlotFlow flow) {
        super(position, index, flow);
    }
}
