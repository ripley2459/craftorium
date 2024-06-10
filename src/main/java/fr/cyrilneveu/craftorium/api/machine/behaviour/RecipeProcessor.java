package fr.cyrilneveu.craftorium.api.machine.behaviour;

import fr.cyrilneveu.craftorium.api.inventory.FluidSlotData;
import fr.cyrilneveu.craftorium.api.inventory.ItemSlotData;
import fr.cyrilneveu.craftorium.api.inventory.OreStack;
import fr.cyrilneveu.craftorium.api.machine.EMachineStates;
import fr.cyrilneveu.craftorium.api.machine.MachineTile;
import fr.cyrilneveu.craftorium.api.mui.AWidget;
import fr.cyrilneveu.craftorium.api.mui.ButtonConfiguration;
import fr.cyrilneveu.craftorium.api.mui.ProgressArrow;
import fr.cyrilneveu.craftorium.api.mui.Tab;
import fr.cyrilneveu.craftorium.api.recipe.machine.MachineRecipe;
import fr.cyrilneveu.craftorium.api.recipe.machine.RecipeMap;
import fr.cyrilneveu.craftorium.api.utils.*;
import io.netty.buffer.ByteBuf;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;

public final class RecipeProcessor implements IMachineBehaviour, ITickable, INBTSerializable<NBTTagCompound> {
    public static final int MACHINE_CONFIGURATION_MIN = 1;
    public static final int MACHINE_CONFIGURATION_MAX = 9;
    public static final String MACHINE_CONFIGURATION_NBT = "MachineProcessingConfiguration";
    public static final String MACHINE_PROGRESS_NBT = "MachineProcessingProgress";
    public static final String MACHINE_RECIPE_NBT = "MachineProcessingRecipe";
    public static final String MACHINE_RECIPE_CACHE_NBT = "MachineProcessingRecipeCache";
    private final MachineTile owner;
    private final RecipeMap map;
    private final Position progressPosition;
    private final Position configurationButtonPosition;
    private final Random random = new Random();
    private final CustomLazy<FlowController> flowController;
    @Nullable
    private ItemInventory itemInventory;
    @Nullable
    private FluidInventory fluidInventory;
    private EnergyInventory energyInventory;
    private int configuration = MACHINE_CONFIGURATION_MIN;
    private int progress;
    private int progressMax;
    @Nullable
    private MachineRecipe recipe;
    @Nullable
    private MachineRecipe cache;

    public RecipeProcessor(MachineTile owner, RecipeMap map, Position progress, Position configuration) {
        this.owner = owner;
        this.map = map;
        this.progressPosition = progress;
        this.configurationButtonPosition = configuration;
        this.flowController = new CustomLazy<>(() -> (FlowController) Utils.first(owner.getBehaviours(), b -> b instanceof FlowController), true);
    }

    @Override
    public void init() {
        this.itemInventory = (ItemInventory) Utils.first(owner.getBehaviours(), b -> b instanceof ItemInventory);
        this.fluidInventory = (FluidInventory) Utils.first(owner.getBehaviours(), b -> b instanceof FluidInventory);
        this.energyInventory = (EnergyInventory) Utils.first(owner.getBehaviours(), b -> b instanceof EnergyInventory);
    }

    @Override
    public void update() {
        if (owner.getWorld().isRemote)
            return;

        if (energyInventory.getEnergyStored() > 0) {
            if (recipe != null) {
                if (progress <= progressMax && hasEnoughEnergy()) {
                    energyInventory.extractEnergy(getEnergyRequired(), false);
                    owner.setState(EMachineStates.WORKING);
                    progress++;
                    if (progress >= progressMax) {
                        progress = progressMax;
                        if (insertOutputs()) {
                            progress = 0;
                            cache = recipe;
                            recipe = null;
                            pushOutputs();
                            owner.markDirty();
                        }
                    }
                } else if (!hasEnoughEnergy()) {
                    // progress--; // TODO ?
                    owner.setState(EMachineStates.NOPOWER);
                    if (progress < 0)
                        progress = 0;
                    owner.markDirty();
                }
            } else {
                if (setRecipe()) {
                    consumeInputs();
                    owner.markDirty();
                } else owner.setState(EMachineStates.IDLE);
            }
        } else owner.setState(EMachineStates.NOPOWER);
    }

    private void consumeInputs() {
        for (OreStack item : recipe.getItemsIn()) {
            if (!item.isConsumable())
                continue;

            int asked = item.getAmount();
            while (asked > 0) {
                for (ItemSlotData slot : itemInventory.getSlotsData()) {
                    if (slot.isInput() && item.matches(itemInventory.getStackInSlot(slot.getIndex()))) {
                        asked -= itemInventory.takeItem(slot.getIndex(), asked, false).getCount();
                        if (asked <= 0)
                            break;
                    }
                }
            }
        }

        for (FluidStack fluid : recipe.getFluidsIn()) {
            int asked = fluid.amount;
            while (asked > 0) {
                for (FluidSlotData slot : fluidInventory.getSlotsData()) {
                    FluidStack fluidStack = fluidInventory.getStackInSlot(slot.getIndex());
                    if (fluidStack != null && slot.isInput() && fluid.isFluidEqual(fluidStack)) {
                        FluidStack toDrain = fluidStack.copy();
                        toDrain.amount = Math.min(fluid.amount, fluidStack.amount);
                        FluidStack extracted = fluidInventory.takeFluid(toDrain, true);
                        asked -= extracted == null ? 0 : extracted.amount;
                        if (asked <= 0)
                            break;
                    }
                }
            }
        }
    }

    private boolean hasEnoughEnergy() {
        return energyInventory.getEnergyStored() >= getEnergyRequired();
    }

    private int getEnergyRequired() {
        return recipe != null ? recipe.getEnergyIn() / progressMax : 0;
    }

    private boolean setRecipe() {
        recipe = map.getRecipe(itemInventory != null ? itemInventory.getStacksInInputs() : Collections.emptyList(), fluidInventory != null ? fluidInventory.getStacksInInputs() : Collections.emptyList(), configuration, cache);
        if (recipe != null) {
            progress = 0;
            progressMax = Math.max(1, Math.round(recipe.getDuration() / owner.getTier().getProcess().getSpeed()));
            owner.markDirty();
            return true;
        }

        progress = 0;
        progressMax = 0;
        return false;
    }

    private boolean insertOutputs() {
        WeightedList<ItemStack> itemStacks = recipe.getItemsOut();
        WeightedList<FluidStack> fluidStacks = recipe.getFluidsOut();

        itemInventory.startSimulation(); // Used to track the items without modifying the reel inventory!

        for (ItemStack itemStack : itemStacks) {
            if (!insertOutput(itemStack.copy(), false)) { // False because we don't want to simulate the simulation
                itemInventory.stopSimulation();
                return false;
            }
        }

        itemInventory.stopSimulation();

        for (FluidStack fluidStack : fluidStacks) {
            if (!insertOutput(fluidStack.copy(), true))
                return false;
        }

        for (Map.Entry<ItemStack, Integer> entry : itemStacks.map().entrySet()) {
            int rand = random.nextInt(101);
            if (entry.getValue() >= 100 || rand <= entry.getValue())
                insertOutput(entry.getKey().copy(), false);
        }

        for (Map.Entry<FluidStack, Integer> entry : fluidStacks.map().entrySet()) {
            int rand = random.nextInt(101);
            if (entry.getValue() >= 100 || rand <= entry.getValue())
                insertOutput(entry.getKey().copy(), false);
        }

        return true;
    }

    private boolean insertOutput(ItemStack output, boolean simulate) {
        return itemInventory.insertInOutputs(output, simulate).isEmpty();
    }

    private boolean insertOutput(FluidStack output, boolean simulate) {
        int amount = output.amount;
        return fluidInventory.insertInOutputs(output, !simulate) == amount;
    }

    private void pushOutputs() {
        for (EnumFacing side : EnumFacing.VALUES) {
            if (!(flowController.get() == null || (flowController.get() != null && flowController.get().canOutput(side))))
                continue;

            TileEntity tile = owner.getWorld().getTileEntity(owner.getPos().offset(side));
            if (tile == null)
                continue;

            if (tile.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, side.getOpposite())) {
                IItemHandler inv = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, side.getOpposite());
                if (inv == null || inv.getSlots() < 1)
                    continue;

                for (ItemSlotData slot : itemInventory.getSlotsData()) {
                    if (!slot.isOutput() || itemInventory.getStackInSlot(slot.getIndex()).isEmpty())
                        continue;

                    ItemStack initial = itemInventory.getStackInSlot(slot.getIndex()).copy();
                    ItemStack remaining = ItemHandlerHelper.insertItemStacked(inv, initial, false);

                    if (remaining.getCount() >= initial.getCount())
                        continue;

                    itemInventory.getStackInSlot(slot.getIndex()).shrink(initial.getCount() - remaining.getCount());
                    if (itemInventory.getStackInSlot(slot.getIndex()).getCount() <= 0)
                        itemInventory.setStackInSlot(slot.getIndex(), ItemStack.EMPTY);
                }
            }

            if (tile.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, side.getOpposite())) {
                IFluidHandler inv = tile.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, side.getOpposite());
                if (inv == null || inv.getTankProperties().length < 1)
                    continue;

                for (FluidSlotData slot : fluidInventory.getSlotsData()) {
                    FluidTank tank = fluidInventory.getTanks().get(slot.getIndex());
                    if (!slot.isOutput() || tank.getFluid() == null)
                        continue;
                    tank.drain(inv.fill(tank.drain(tank.getCapacity(), false), true), true);
                }
            }
        }
    }

    @Override
    public void pushWidgets(List<AWidget> widgets, List<Tab> leftTabs, List<Tab> rightTabs) {
        widgets.add(new ButtonConfiguration(configurationButtonPosition));
        widgets.add(new ProgressArrow(progressPosition));
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        configuration = buf.readByte();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeByte(configuration);
        buf.writeInt(progress);
        buf.writeInt(progressMax);
    }

    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound nbt = NBTUtils.setValue(MACHINE_CONFIGURATION_NBT, configuration, new NBTTagCompound());

        if (cache != null)
            NBTUtils.setValue(MACHINE_RECIPE_CACHE_NBT, cache.getName(), nbt);
        if (recipe != null) {
            NBTUtils.setValue(MACHINE_RECIPE_NBT, recipe.getName(), nbt);
            NBTUtils.setValue(MACHINE_PROGRESS_NBT, progress, nbt);
        }

        return nbt;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        if (nbt.hasKey(MACHINE_CONFIGURATION_NBT))
            configuration = nbt.getByte(MACHINE_CONFIGURATION_NBT);
        else configuration = MACHINE_CONFIGURATION_MIN;

        if (nbt.hasKey(MACHINE_RECIPE_CACHE_NBT))
            cache = map.getRecipe(NBTUtils.getStringValue(MACHINE_RECIPE_CACHE_NBT, nbt));

        if (nbt.hasKey(MACHINE_RECIPE_NBT)) {
            recipe = map.getRecipe(NBTUtils.getStringValue(MACHINE_RECIPE_NBT, nbt));
            if (recipe != null) {
                progress = NBTUtils.getIntValue(MACHINE_PROGRESS_NBT, nbt);
                progressMax = Math.max(1, Math.round(recipe.getDuration() / owner.getTier().getProcess().getSpeed()));
            } else {
                progress = 0;
                progressMax = 0;
            }
        }
    }
}
