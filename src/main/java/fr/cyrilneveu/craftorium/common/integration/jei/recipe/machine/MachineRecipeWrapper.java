package fr.cyrilneveu.craftorium.common.integration.jei.recipe.machine;

import fr.cyrilneveu.craftorium.api.inventory.OreStack;
import fr.cyrilneveu.craftorium.api.machine.Machine;
import fr.cyrilneveu.craftorium.api.recipe.machine.MachineRecipe;
import fr.cyrilneveu.craftorium.api.utils.Position;
import fr.cyrilneveu.craftorium.api.utils.Size;
import fr.cyrilneveu.craftorium.api.utils.Utils;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IDrawableAnimated;
import mezz.jei.api.gui.IDrawableStatic;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static fr.cyrilneveu.craftorium.api.mui.ProgressArrow.*;

public final class MachineRecipeWrapper implements IRecipeWrapper {
    private final IDrawable arrow;
    private final IDrawableStatic arrowbackground;
    private final Machine machine;
    private final MachineRecipe recipe;

    public MachineRecipeWrapper(Machine machine, MachineRecipe recipe, IGuiHelper guiHelper) {
        this.machine = machine;
        this.recipe = recipe;
        this.arrowbackground = guiHelper.createDrawable(ARROW, 0, 0, TEXTURE_SIZE.getSizeX() / 2, TEXTURE_SIZE.getSizeY(), TEXTURE_SIZE.getSizeX(), TEXTURE_SIZE.getSizeY());
        IDrawableStatic arrowDrawable = guiHelper.createDrawable(ARROW, TEXTURE_SIZE.getSizeX() / 2, 0, TEXTURE_SIZE.getSizeX() / 2, TEXTURE_SIZE.getSizeY(), TEXTURE_SIZE.getSizeX(), TEXTURE_SIZE.getSizeY());
        this.arrow = guiHelper.createAnimatedDrawable(arrowDrawable, recipe.getDuration(), IDrawableAnimated.StartDirection.LEFT, false);
    }

    @Override
    public void drawInfo(Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
        Position position = machine.getJeiData().getArrow();
        Size offset = machine.getJeiData().getOffset();
        arrowbackground.draw(minecraft, position.getPosX() - offset.getSizeX(), position.getPosY() - offset.getSizeY());
        arrow.draw(minecraft, position.getPosX() - offset.getSizeX(), position.getPosY() - offset.getSizeY());
    }

    @Override
    public List<String> getTooltipStrings(int mouseX, int mouseY) {
        List<String> tooltips;
        Position position = machine.getJeiData().getArrow();
        Size offset = machine.getJeiData().getOffset();
        Size size = NORMAL_SIZE;

        if (mouseX >= position.getPosX() - offset.getSizeX() && mouseX < position.getPosX() - offset.getSizeX() + size.getSizeX() && mouseY >= position.getPosY() - offset.getSizeY() && mouseY < position.getPosY() - offset.getSizeY() + size.getSizeY()) {
            tooltips = new ArrayList<>();
            tooltips.add(Utils.localise("jei.craftorium.recipe.title"));
            if (Utils.isAdvancedTooltipsOn()) {
                tooltips.add(Utils.localise("jei.craftorium.recipe.name", recipe.getName()));
                tooltips.add(Utils.localise("jei.craftorium.recipe.map", machine.getJeiData().getMap()));
            }
            tooltips.add(Utils.localise("jei.craftorium.recipe.configuration", recipe.getConfiguration()));
            tooltips.add(Utils.localise("jei.craftorium.recipe.energy", recipe.getEnergyIn()));
            tooltips.add(Utils.localise("jei.craftorium.recipe.consumption", recipe.getEnergyIn() / recipe.getDuration()));
            tooltips.add(Utils.localise("jei.craftorium.recipe.duration", recipe.getDuration()));
        } else tooltips = Collections.emptyList();

        return tooltips;
    }

    @Override
    public void getIngredients(IIngredients ingredients) {
        List<List<ItemStack>> allItemsIn = new ArrayList<>();
        for (OreStack oreStack : recipe.getItemsIn())
            allItemsIn.add(oreStack.getStacks());

        List<List<FluidStack>> allFluidsIn = new ArrayList<>();
        for (FluidStack fluidStack : recipe.getFluidsIn()) {
            List<FluidStack> fIn = new ArrayList<>();
            fIn.add(fluidStack);
            allFluidsIn.add(fIn);
        }

        List<List<ItemStack>> allItemsOut = new ArrayList<>();
        for (ItemStack itemStack : recipe.getItemsOut()) {
            List<ItemStack> out = new ArrayList<>();
            out.add(itemStack);
            allItemsOut.add(out);
        }

        List<List<FluidStack>> allFluidsOut = new ArrayList<>();
        for (FluidStack fluidStack : recipe.getFluidsOut()) {
            List<FluidStack> fOut = new ArrayList<>();
            fOut.add(fluidStack);
            allFluidsOut.add(fOut);
        }

        if (!allItemsIn.isEmpty())
            ingredients.setInputLists(VanillaTypes.ITEM, allItemsIn);

        if (!allFluidsIn.isEmpty())
            ingredients.setInputLists(VanillaTypes.FLUID, allFluidsIn);

        if (!allItemsOut.isEmpty())
            ingredients.setOutputLists(VanillaTypes.ITEM, allItemsOut);

        if (!allFluidsOut.isEmpty())
            ingredients.setOutputLists(VanillaTypes.FLUID, allFluidsOut);
    }

    public MachineRecipe getRecipe() {
        return recipe;
    }
}
