package fr.cyrilneveu.craftorium.common.integration.jei.recipe.machine;

import fr.cyrilneveu.craftorium.api.inventory.ASlotData;
import fr.cyrilneveu.craftorium.api.inventory.FluidSlotData;
import fr.cyrilneveu.craftorium.api.inventory.ItemSlotData;
import fr.cyrilneveu.craftorium.api.machine.Machine;
import fr.cyrilneveu.craftorium.api.mui.ASlot;
import fr.cyrilneveu.craftorium.api.mui.Background;
import fr.cyrilneveu.craftorium.api.utils.RenderUtils;
import fr.cyrilneveu.craftorium.common.integration.jei.ACategory;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IGuiFluidStackGroup;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.util.List;
import java.util.stream.Collectors;

import static fr.cyrilneveu.craftorium.CraftoriumTags.MODID;
import static fr.cyrilneveu.craftorium.api.mui.ASlot.SLOT_TEXTURE_SIZE;

public final class MachineRecipeCategory extends ACategory<MachineRecipeWrapper> {
    private final Machine machine;

    public MachineRecipeCategory(Machine machine, IGuiHelper guiHelper) {
        super(guiHelper.drawableBuilder(Background.BACKGROUND, 4, 4, machine.getJeiData().getMinimalSize().getSizeX() - machine.getJeiData().getOffset().getSizeX(), machine.getJeiData().getMinimalSize().getSizeY() - machine.getJeiData().getOffset().getSizeY()).build());
        this.machine = machine;
    }

    @Override
    public String getUid() {
        return String.join(":", MODID, machine.getName());
    }

    @Override
    public String getTitle() {
        return machine.getDisplayName();
    }

    @Override
    public void drawExtras(Minecraft minecraft) {
        machine.getJeiData().getSlots().forEach(this::drawSlot);
    }

    private void drawSlot(ASlotData slot) {
        RenderUtils.bindTexture(getTexture(slot));
        Gui.drawModalRectWithCustomSizedTexture(slot.getPosition().getPosX() - machine.getJeiData().getOffset().getSizeX(), slot.getPosition().getPosY() - machine.getJeiData().getOffset().getSizeY(), 0, 0, SLOT_TEXTURE_SIZE.getSizeX(), SLOT_TEXTURE_SIZE.getSizeY(), SLOT_TEXTURE_SIZE.getSizeX(), SLOT_TEXTURE_SIZE.getSizeY());
    }

    private ResourceLocation getTexture(ASlotData slotData) {
        if (slotData instanceof ItemSlotData data) {
            return switch (data.getFlow()) {
                case INPUT -> ASlot.ItemSlot.INPUT;
                case OUTPUT -> ASlot.ItemSlot.OUTPUT;
                default -> ASlot.ItemSlot.FREE;
            };
        } else if (slotData instanceof FluidSlotData data) {
            return switch (data.getFlow()) {
                case INPUT -> ASlot.FluidSlot.INPUT;
                case OUTPUT -> ASlot.FluidSlot.OUTPUT;
                default -> ASlot.FluidSlot.FREE;
            };
        }

        return ASlot.ItemSlot.FREE;
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, MachineRecipeWrapper recipeWrapper, IIngredients ingredients) {
        IGuiItemStackGroup items = recipeLayout.getItemStacks();
        IGuiFluidStackGroup fluids = recipeLayout.getFluidStacks();

        int slotIndex = 0;

        List<ASlotData> itemsIn = machine.getJeiData().getSlots().stream().filter(d -> (d.isInput() && (d instanceof ItemSlotData))).collect(Collectors.toList());
        if (!itemsIn.isEmpty()) {
            for (int i = 0; i < ingredients.getInputs(VanillaTypes.ITEM).size(); i++) {
                ASlotData s = itemsIn.get(i);
                items.init(slotIndex++, true, new ItemStackRenderer(recipeWrapper.getRecipe().getItemsIn().get(i), 100), s.getPosition().getPosX() - machine.getJeiData().getOffset().getSizeX() + 1, s.getPosition().getPosY() - machine.getJeiData().getOffset().getSizeY() + 1, 16, 16, 0, 0);
            }
        }

        List<ASlotData> fluidsIn = machine.getJeiData().getSlots().stream().filter(d -> (d.isInput() && (d instanceof FluidSlotData))).collect(Collectors.toList());
        if (!fluidsIn.isEmpty()) {
            for (int i = 0; i < ingredients.getInputs(VanillaTypes.FLUID).size(); i++) {
                ASlotData slot = fluidsIn.get(i);
                fluids.init(slotIndex++, true, new FluidStackRenderer(1, false, 16, 16, null, 100), slot.getPosition().getPosX() - machine.getJeiData().getOffset().getSizeX() + 1, slot.getPosition().getPosY() - machine.getJeiData().getOffset().getSizeY() + 1, 16, 16, 0, 0);
            }
        }

        List<ASlotData> itemsOut = machine.getJeiData().getSlots().stream().filter(d -> (d.isOutput() && (d instanceof ItemSlotData))).collect(Collectors.toList());
        List<ItemStack> itemStacksOut = recipeWrapper.getRecipe().getItemsOut().keys();
        List<Integer> itemStacksChances = recipeWrapper.getRecipe().getItemsOut().values();
        if (!itemsOut.isEmpty()) {
            for (int i = 0; i < ingredients.getOutputs(VanillaTypes.ITEM).size(); i++) {
                ASlotData slot = itemsOut.get(i);
                items.init(slotIndex++, false, new ItemStackRenderer(itemStacksOut.get(i), itemStacksChances.get(i)), slot.getPosition().getPosX() - machine.getJeiData().getOffset().getSizeX() + 1, slot.getPosition().getPosY() - machine.getJeiData().getOffset().getSizeY() + 1, 16, 16, 0, 0);
            }
        }

        List<ASlotData> fluidsOut = machine.getJeiData().getSlots().stream().filter(d -> (d.isOutput() && (d instanceof FluidSlotData))).collect(Collectors.toList());
        List<Integer> fluidStacksChances = recipeWrapper.getRecipe().getFluidsOut().values();
        if (!fluidsOut.isEmpty()) {
            for (int i = 0; i < ingredients.getOutputs(VanillaTypes.FLUID).size(); i++) {
                ASlotData slot = fluidsOut.get(i);
                fluids.init(slotIndex++, false, new FluidStackRenderer(1, false, 16, 16, null, fluidStacksChances.get(i)), slot.getPosition().getPosX() - machine.getJeiData().getOffset().getSizeX() + 1, slot.getPosition().getPosY() - machine.getJeiData().getOffset().getSizeY() + 1, 16, 16, 0, 0);
            }
        }

        slotIndex = 0;
        if (!itemsIn.isEmpty()) {
            for (int i = 0; i < ingredients.getInputs(VanillaTypes.ITEM).size(); i++)
                items.set(slotIndex++, ingredients.getInputs(VanillaTypes.ITEM).get(i));
        }

        if (!fluidsIn.isEmpty()) {
            for (int i = 0; i < ingredients.getInputs(VanillaTypes.FLUID).size(); i++)
                fluids.set(slotIndex++, ingredients.getInputs(VanillaTypes.FLUID).get(i));
        }

        if (!itemsOut.isEmpty()) {
            for (int i = 0; i < ingredients.getOutputs(VanillaTypes.ITEM).size(); i++)
                items.set(slotIndex++, ingredients.getOutputs(VanillaTypes.ITEM).get(i));
        }

        if (!fluidsOut.isEmpty()) {
            for (int i = 0; i < ingredients.getOutputs(VanillaTypes.FLUID).size(); i++)
                fluids.set(slotIndex++, ingredients.getOutputs(VanillaTypes.FLUID).get(i));
        }
    }
}
