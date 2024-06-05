package fr.cyrilneveu.craftorium.api.integration.jei.recipe.machine;

import fr.cyrilneveu.craftorium.api.utils.EColors;
import fr.cyrilneveu.craftorium.api.utils.Utils;
import mezz.jei.api.gui.IDrawable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nullable;
import java.util.List;

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
            String amountL = Utils.formatFluidAmount(fluidStack.amount);
            GlStateManager.pushMatrix();
            GlStateManager.scale(0.5, 0.5, 1);
            GlStateManager.translate(0f, 0f, 160f);
            FontRenderer fontRenderer = Minecraft.getMinecraft().fontRenderer;
            fontRenderer.drawStringWithShadow(amountL, (xPosition + 7) * 2 - fontRenderer.getStringWidth(amountL) + 20, (yPosition + 13) * 2, 0xFFFFFF);
            GlStateManager.popMatrix();
            GlStateManager.enableBlend();

            if (chance != 100) {
                String chanceL = Utils.localise("jei.craftorium.object.chance", chance);
                GlStateManager.pushMatrix();
                GlStateManager.scale(0.5, 0.5, 1);
                GlStateManager.translate(0f, 0f, 160f);
                fontRenderer.drawStringWithShadow(chanceL, (xPosition + 7) * 2 - fontRenderer.getStringWidth(chanceL) + 20, (yPosition) * 2, EColors.YELLOW.getColor());
                GlStateManager.popMatrix();
                GlStateManager.enableBlend();
            }
        }
    }

    @Override
    public List<String> getTooltip(Minecraft minecraft, FluidStack fluidStack, ITooltipFlag tooltipFlag) {
        List<String> tooltips = super.getTooltip(minecraft, fluidStack, tooltipFlag);

        if (fluidStack != null) {
            if (chance != 100)
                tooltips.add(Utils.localise("jei.craftorium.object.chance_b", chance));
        }

        return tooltips;
    }
}
