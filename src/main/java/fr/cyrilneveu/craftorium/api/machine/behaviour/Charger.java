package fr.cyrilneveu.craftorium.api.machine.behaviour;

import fr.cyrilneveu.craftorium.api.machine.EMachineStates;
import fr.cyrilneveu.craftorium.api.machine.MachineTile;
import fr.cyrilneveu.craftorium.api.utils.Utils;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ITickable;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

public class Charger implements IMachineBehaviour, ITickable {
    private final MachineTile owner;
    private final int io;
    private ItemInventory itemInventory;
    private EnergyInventory energyInventory;

    public Charger(MachineTile owner, int io) {
        this.owner = owner;
        this.io = (int) (io * owner.getTier().getEnergyIO());
    }

    @Override
    public void init() {
        this.itemInventory = (ItemInventory) Utils.first(owner.getBehaviours(), b -> b instanceof ItemInventory);
        this.energyInventory = (EnergyInventory) Utils.first(owner.getBehaviours(), b -> b instanceof EnergyInventory);
    }

    @Override
    public void update() {
        if (owner.getWorld().isRemote)
            return;

        if (energyInventory.getEnergyStored() <= 0) {
            owner.setState(EMachineStates.NOPOWER);
            owner.markDirty();
            return;
        }

//        new dans boucle vraiment pas top !
//        if (!Utils.atLeastOne(itemInventory.getStacks(), stack -> !stack.isEmpty())) {
//            owner.setState(EMachineStates.IDLE);
//            return;
//        }

        for (int i = 0; i < itemInventory.getSlots(); i++) {
            if (energyInventory.getEnergyStored() <= 0) {
                owner.setState(EMachineStates.NOPOWER);
                owner.markDirty();
                return;
            }

            ItemStack stack = itemInventory.getStackInSlot(i);
            if (!stack.hasCapability(CapabilityEnergy.ENERGY, null))
                continue;

            IEnergyStorage energyStorage = stack.getCapability(CapabilityEnergy.ENERGY, null);
            if (energyStorage == null)
                continue;

            owner.setState(EMachineStates.WORKING);
            owner.markDirty();

            int extractedFromStorage = energyInventory.extractEnergy(this.io, true);
            int transferred = energyStorage.receiveEnergy(extractedFromStorage, false);
            if (transferred > 0)
                energyInventory.extractEnergy(transferred, false);
        }
    }
}
