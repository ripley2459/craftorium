package fr.cyrilneveu.craftorium.api.inventory;

import fr.cyrilneveu.craftorium.api.machine.behaviour.ItemInventory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;

public class CustomSlot extends SlotItemHandler {
    protected final ItemInventory inventory;
    protected final ASlotData data;

    public CustomSlot(IItemHandler itemHandler, ASlotData data) {
        super(itemHandler, data.getIndex(), data.getPosition().getPosX() + 1, data.getPosition().getPosY() + 1);
        this.data = data;
        this.inventory = (ItemInventory) itemHandler;
    }

    @Override
    public boolean canTakeStack(EntityPlayer playerIn) {
        return data.isOutput() ? super.canTakeStack(playerIn) : !this.inventory.takeItem(data.getIndex(), 1, true).isEmpty();
    }

    @Nonnull
    public ItemStack decrStackSize(int amount) {
        return data.isOutput() ? super.decrStackSize(amount) : this.inventory.takeItem(data.getIndex(), amount, false);
    }
}
