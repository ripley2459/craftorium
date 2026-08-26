package fr.cyrilneveu.craftorium.api.machine.behaviour;

import fr.cyrilneveu.craftorium.api.machine.MachineTile;
import fr.cyrilneveu.craftorium.api.utils.Utils;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.util.INBTSerializable;

public class Charger implements IMachineBehaviour, ITickable, INBTSerializable<NBTTagCompound> {
    private final MachineTile owner;
    private ItemInventory itemInventory;
    private EnergyInventory energyInventory;

    public Charger(MachineTile owner) {
        this.owner = owner;
    }

    @Override
    public void init() {
        this.itemInventory = (ItemInventory) Utils.first(owner.getBehaviours(), b -> b instanceof ItemInventory);
        this.energyInventory = (EnergyInventory) Utils.first(owner.getBehaviours(), b -> b instanceof EnergyInventory);
    }

    @Override
    public void update() {

    }

    @Override
    public NBTTagCompound serializeNBT() {
        return null;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {

    }
}
