package fr.cyrilneveu.craftorium.common.integration.jei.recipe.machine;

import fr.cyrilneveu.craftorium.api.utils.Utils;
import fr.cyrilneveu.craftorium.common.substance.SubstancesObjects;
import mezz.jei.api.gui.IDrawable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nullable;
import java.util.List;

import static fr.cyrilneveu.craftorium.api.Registries.SUBSTANCES_REGISTRY;
import static fr.cyrilneveu.craftorium.api.utils.RenderUtils.WHITE_COLOR;
import static fr.cyrilneveu.craftorium.common.substance.SubstancesObjects.LIQUID;

public final class FluidStackRenderer extends mezz.jei.plugins.vanilla.ingredients.fluid.FluidStackRenderer {
    private final int chance;

    public FluidStackRenderer(int capacityMb, boolean showCapacity, int width, int height, @Nullable IDrawable overlay, int chance) {
        super(capacityMb, showCapacity, width, height, overlay);
        this.chance = chance;
    }

    @Override
    public void render(Minecraft minecraft, int xPosition, int yPosition, @Nullable FluidStack fluidStack) {
        super.render(minecraft, xPosition, yPosition, fluidStack);

        if (fluidStack != null) {
            String amount = Utils.formatFluidAmount(fluidStack.amount);
            GlStateManager.pushMatrix();
            GlStateManager.scale(0.5, 0.5, 1);
            GlStateManager.translate(0f, 0f, 160f);
            FontRenderer fontRenderer = Minecraft.getMinecraft().fontRenderer;
            fontRenderer.drawStringWithShadow(amount, (xPosition + 7) * 2 - fontRenderer.getStringWidth(amount) + 20, (yPosition + 13) * 2, 0xFFFFFF);
            GlStateManager.popMatrix();

            if (chance != 100) {
                String chanceL = Utils.localise("jei.craftorium.recipe.chance.tiny", chance);
                GlStateManager.pushMatrix();
                GlStateManager.scale(0.5, 0.5, 1);
                GlStateManager.translate(0f, 0f, 160f);
                fontRenderer.drawStringWithShadow(chanceL, (xPosition + 7) * 2 - fontRenderer.getStringWidth(chanceL) + 20, (yPosition) * 2, WHITE_COLOR);
                GlStateManager.popMatrix();
            }
        }
    }

    @Override
    public List<String> getTooltip(Minecraft minecraft, FluidStack fluidStack, ITooltipFlag tooltipFlag) {
        List<String> tooltips = super.getTooltip(minecraft, fluidStack, tooltipFlag);

        if (SUBSTANCES_REGISTRY.getAll().containsKey(fluidStack.getFluid().getName()))
            tooltips.addAll(SubstancesObjects.fluidTooltips(LIQUID, SUBSTANCES_REGISTRY.get(fluidStack.getFluid().getName())));

        if (fluidStack != null) {
            if (chance != 100)
                tooltips.add(Utils.localise("jei.craftorium.recipe.chance", chance));
        }

        return tooltips;
    }
}
