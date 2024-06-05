package fr.cyrilneveu.craftorium.api.integration.jei.recipe.machine;

import fr.cyrilneveu.craftorium.api.inventory.OreStack;
import fr.cyrilneveu.craftorium.api.utils.EColors;
import fr.cyrilneveu.craftorium.api.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;

import javax.annotation.Nullable;
import java.util.List;

public final class ItemStackRenderer extends mezz.jei.plugins.vanilla.ingredients.item.ItemStackRenderer {
    private final OreStack oreStack;
    private final int amount;
    private final int chance;
    private final boolean consumable;

    public ItemStackRenderer(ItemStack itemStack, int chance) {
        this(new OreStack(itemStack), chance);
    }

    public ItemStackRenderer(OreStack oreStack, int chance) {
        this.oreStack = oreStack;
        this.amount = oreStack.getAmount();
        this.chance = chance;
        this.consumable = oreStack.isConsumable();
    }

    @Override
    public void render(Minecraft minecraft, int xPosition, int yPosition, @Nullable ItemStack ingredient) {
        super.render(minecraft, xPosition, yPosition, ingredient);

        if (oreStack != null) {
            if (!consumable) {
                // TODO
            }

            if (chance != 100) {
                String l = Utils.localise("jei.craftorium.object.chance", chance);
                GlStateManager.pushMatrix();
                GlStateManager.scale(0.5, 0.5, 1);
                GlStateManager.translate(0f, 0f, 160f);
                FontRenderer fontRenderer = Minecraft.getMinecraft().fontRenderer;
                fontRenderer.drawStringWithShadow(l, (xPosition + 7) * 2 - fontRenderer.getStringWidth(l) + 20, (yPosition) * 2, EColors.ORANGE.getColor());
                GlStateManager.popMatrix();
                GlStateManager.enableBlend();
            }
        }
    }

    @Override
    public List<String> getTooltip(Minecraft minecraft, ItemStack ingredient, ITooltipFlag tooltipFlag) {
        List<String> tooltips = super.getTooltip(minecraft, ingredient, tooltipFlag);

        if (oreStack != null) {
            if (!consumable)
                tooltips.add(Utils.localise("jei.craftorium.object.not_consumable"));
            if (chance != 100)
                tooltips.add(Utils.localise("jei.craftorium.object.chance_b", chance));
        }

        return tooltips;
    }
}
