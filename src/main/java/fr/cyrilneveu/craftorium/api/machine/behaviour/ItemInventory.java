package fr.cyrilneveu.craftorium.api.machine.behaviour;

import com.google.common.base.Preconditions;
import fr.cyrilneveu.craftorium.api.inventory.CustomSlot;
import fr.cyrilneveu.craftorium.api.inventory.ItemSlotData;
import fr.cyrilneveu.craftorium.api.machine.MachineTile;
import fr.cyrilneveu.craftorium.api.mui.AWidget;
import fr.cyrilneveu.craftorium.api.mui.ItemSlot;
import fr.cyrilneveu.craftorium.api.mui.Tab;
import fr.cyrilneveu.craftorium.api.utils.CustomLazy;
import fr.cyrilneveu.craftorium.api.utils.Utils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemHandlerHelper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public final class ItemInventory implements IItemHandlerModifiable, IMachineBehaviour, IContainable, ICapabilityProvider, INBTSerializable<NBTTagCompound> {
    private final MachineTile owner;
    private final CustomLazy<FlowController> flowController;
    private final List<ItemSlotData> slots;
    private NonNullList<ItemStack> stacks;
    @Nullable
    private EnumFacing side = null;

    public ItemInventory(MachineTile owner, List<ItemSlotData> slots) {
        this.owner = owner;
        this.flowController = new CustomLazy<>(() -> (FlowController) Utils.first(owner.getBehaviours(), b -> b instanceof FlowController), true);
        this.slots = slots;
        this.stacks = NonNullList.withSize(this.slots.size(), ItemStack.EMPTY);
    }

    public void setSize(int size) {
        this.stacks = NonNullList.withSize(size, ItemStack.EMPTY);
    }

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
        if ((flowController.get() == null || (flowController.get() != null && flowController.get().canConnect(facing))))
            return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY;
        return false;
    }

    @Nullable
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
        if ((flowController.get() == null || (flowController.get() != null && flowController.get().canConnect(facing))))
            return hasCapability(capability, facing) ? CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(this.forSide(facing)) : null;
        return null;
    }

    @Override
    public int getSlots() {
        return stacks.size();
    }

    public List<ItemSlotData> getSlotsData() {
        return slots;
    }

    public List<ItemStack> getStacksInInputs() {
        List<ItemStack> itemStacks = new ArrayList<>();
        for (ItemSlotData slot : slots) {
            if (slot.isInput())
                itemStacks.add(getStackInSlot(slot.getIndex()));
        }

        return itemStacks;
    }

    public List<ItemStack> getStacksInOutput() {
        List<ItemStack> itemStacks = new ArrayList<>();
        for (ItemSlotData slot : slots) {
            if (slot.isOutput())
                itemStacks.add(getStackInSlot(slot.getIndex()));
        }

        return itemStacks;
    }

    @Nonnull
    @Override
    public ItemStack getStackInSlot(int slot) {
        validateSlotIndex(slot);

        return this.stacks.get(slot);
    }

    @Override
    public void setStackInSlot(int slot, @Nonnull ItemStack stack) {
        validateSlotIndex(slot);

        this.stacks.set(slot, stack);
        owner.markDirty();
    }

    @Override
    @Nonnull
    public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
        return slots.get(slot).isInput() && (flowController.get() == null || (flowController.get() != null && flowController.get().canInput(side))) ?
                defaultInsert(slot, stack, simulate) : stack;
    }

    @Nonnull
    private ItemStack defaultInsert(int slot, @Nonnull ItemStack stack, boolean simulate) {
        if (stack.isEmpty())
            return ItemStack.EMPTY;

        validateSlotIndex(slot);

        ItemStack existing = this.stacks.get(slot);

        int limit = getStackLimit(slot, stack);

        if (!existing.isEmpty()) {
            if (!ItemHandlerHelper.canItemStacksStack(stack, existing))
                return stack;

            limit -= existing.getCount();
        }

        if (limit <= 0)
            return stack;

        boolean reachedLimit = stack.getCount() > limit;

        if (!simulate) {
            if (existing.isEmpty())
                this.stacks.set(slot, reachedLimit ? ItemHandlerHelper.copyStackWithSize(stack, limit) : stack);
            else existing.grow(reachedLimit ? limit : stack.getCount());

            owner.markDirty();
        }

        return reachedLimit ? ItemHandlerHelper.copyStackWithSize(stack, stack.getCount() - limit) : ItemStack.EMPTY;
    }

    @Nonnull
    public ItemStack insertInOutputs(@Nonnull ItemStack stack, boolean simulate) {
        ItemStack remaining = ItemStack.EMPTY;
        for (ItemSlotData slot : slots) {
            remaining = slot.isOutput() ? defaultInsert(slot.getIndex(), stack, simulate) : stack;
            if (remaining.isEmpty())
                break;
        }

        return remaining;
    }

    @Nonnull
    public ItemStack takeItem(int slot, int amount, boolean simulate) {
        return defaultExtract(slot, amount, simulate);
    }

    @Override
    @Nonnull
    public ItemStack extractItem(int slot, int amount, boolean simulate) {
        return slots.get(slot).isOutput() && (flowController.get() == null || (flowController.get() != null && flowController.get().canOutput(side)))
                ? defaultExtract(slot, amount, simulate) : ItemStack.EMPTY;
    }

    @Nonnull
    private ItemStack defaultExtract(int slot, int amount, boolean simulate) {
        if (amount == 0)
            return ItemStack.EMPTY;

        validateSlotIndex(slot);

        ItemStack existing = this.stacks.get(slot);

        if (existing.isEmpty())
            return ItemStack.EMPTY;

        int toExtract = Math.min(amount, existing.getMaxStackSize());

        if (existing.getCount() <= toExtract) {
            if (!simulate) {
                this.stacks.set(slot, ItemStack.EMPTY);
                owner.markDirty();
            }
            return existing;
        } else {
            if (!simulate) {
                this.stacks.set(slot, ItemHandlerHelper.copyStackWithSize(existing, existing.getCount() - toExtract));
                owner.markDirty();
            }

            return ItemHandlerHelper.copyStackWithSize(existing, toExtract);
        }
    }

    @Override
    public int getSlotLimit(int slot) {
        return 64;
    }

    public int getStackLimit(int slot, @Nonnull ItemStack stack) {
        return Math.min(getSlotLimit(slot), stack.getMaxStackSize());
    }

    @Override
    public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
        return true;
    }

    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagList nbtTagList = new NBTTagList();
        for (int i = 0; i < stacks.size(); i++) {
            if (!stacks.get(i).isEmpty()) {
                NBTTagCompound itemTag = new NBTTagCompound();
                itemTag.setInteger("Slot", i);
                stacks.get(i).writeToNBT(itemTag);
                nbtTagList.appendTag(itemTag);
            }
        }
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setTag("Items", nbtTagList);
        nbt.setInteger("Size", stacks.size());
        return nbt;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        setSize(nbt.hasKey("Size", Constants.NBT.TAG_INT) ? nbt.getInteger("Size") : stacks.size());
        NBTTagList tagList = nbt.getTagList("Items", Constants.NBT.TAG_COMPOUND);
        for (int i = 0; i < tagList.tagCount(); i++) {
            NBTTagCompound itemTags = tagList.getCompoundTagAt(i);
            int slot = itemTags.getInteger("Slot");
            if (slot >= 0 && slot < stacks.size())
                stacks.set(slot, new ItemStack(itemTags));
        }
    }

    public void validateSlotIndex(int slot) {
        Preconditions.checkArgument(slot >= 0 && slot < stacks.size());
    }

    @Override
    public void initContainer(Container container, EntityPlayer playerIn) {
        slots.forEach(s -> container.addSlotToContainer(new CustomSlot(this, s)));
    }

    public ItemInventory forSide(EnumFacing side) {
        this.side = side;
        return this;
    }

    @Override
    public void pushWidgets(List<AWidget> widgets, List<Tab> leftTabs, List<Tab> rightTabs) {
        slots.forEach(s -> widgets.add(new ItemSlot(s)));
    }
}
