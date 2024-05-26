package fr.cyrilneveu.craftorium.api.machine.behaviour;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

public interface IContainable {
    void initContainer(Container container, EntityPlayer playerIn);
}
