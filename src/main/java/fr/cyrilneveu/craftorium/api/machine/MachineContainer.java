package fr.cyrilneveu.craftorium.api.machine;

import fr.cyrilneveu.craftorium.api.machine.behaviour.IContainable;
import fr.cyrilneveu.craftorium.api.machine.behaviour.IMachineBehaviour;
import fr.cyrilneveu.craftorium.api.mui.AWidget;
import fr.cyrilneveu.craftorium.api.net.NetManager;
import fr.cyrilneveu.craftorium.api.net.SMachinePacket;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.item.ItemStack;

public final class MachineContainer extends Container {
    private final MachineTile owner;

    public MachineContainer(MachineTile owner, EntityPlayer playerIn) {
        this.owner = owner;

        for (IMachineBehaviour behaviour : owner.getBehaviours()) {
            if (behaviour instanceof IContainable containable)
                containable.initContainer(this, playerIn);
        }
    }

    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();

        if (owner.getWorld().isRemote)
            return;

        for (IContainerListener listener : listeners) {
            if (listener instanceof EntityPlayerMP playerMP) {
                SMachinePacket packet = new SMachinePacket(owner);
                NetManager.sendTo(packet, playerMP);
            }
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return !owner.isInvalid() && playerIn.getDistanceSq(owner.getPos().add(0.5D, 0.5D, 0.5D)) <= 64D;
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
        return ItemStack.EMPTY;
    }

    public MachineTile getOwner() {
        return owner;
    }
}
