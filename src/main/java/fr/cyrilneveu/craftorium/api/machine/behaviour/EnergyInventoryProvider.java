package fr.cyrilneveu.craftorium.api.machine.behaviour;

import fr.cyrilneveu.craftorium.api.inventory.EnergySlotData;
import fr.cyrilneveu.craftorium.api.machine.MachineTile;
import fr.cyrilneveu.craftorium.api.substance.Tier;
import fr.cyrilneveu.craftorium.api.utils.Position;

public class EnergyInventoryProvider implements IGetBehaviours {
    private final int posX;
    private final int posY;
    private final int capacity;
    private final int transfer;

    public EnergyInventoryProvider(int posX, int posY, int capacity, int transfer) {
        this.posX = posX;
        this.posY = posY;
        this.capacity = capacity;
        this.transfer = transfer;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getTransfer() {
        return transfer;
    }

    @Override
    public IMachineBehaviour get(MachineTile owner, Tier tier) {
        return new EnergyInventory(owner, new EnergySlotData(new Position(posX, posY), capacity, transfer));
    }
}
