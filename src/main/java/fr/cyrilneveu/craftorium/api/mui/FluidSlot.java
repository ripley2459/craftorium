package fr.cyrilneveu.craftorium.api.mui;

import fr.cyrilneveu.craftorium.api.inventory.FluidSlotData;
import fr.cyrilneveu.craftorium.api.utils.RenderUtils;
import fr.cyrilneveu.craftorium.api.utils.Utils;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import org.lwjgl.opengl.GL11;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;

import static fr.cyrilneveu.craftorium.CraftoriumTags.MODID;
import static fr.cyrilneveu.craftorium.api.utils.Utils.EMPTY_FLUID_STACK;

public class FluidSlot extends ASlot {
    public static final ResourceLocation INPUT = new ResourceLocation(MODID, "textures/interfaces/elements/slot_fluid_input.png");
    public static final ResourceLocation OUTPUT = new ResourceLocation(MODID, "textures/interfaces/elements/slot_fluid_output.png");
    public static final ResourceLocation FREE = new ResourceLocation(MODID, "textures/interfaces/elements/slot_fluid.png");
    private static final int FLUID_RENDER_SIZE = 16;
    @Nullable
    private FluidStack fluidStack = null;
    private int capacity = 0;

    public FluidSlot(FluidSlotData slotData) {
        super(slotData);
    }

    @Override
    public void drawForeground(int mouseX, int mouseY) {
        if (!isFluidStackValid(fluidStack))
            return;

        String l = Utils.formatFluidAmount(fluidStack.amount);
        float scale = 0.5f;
        float posX = getPosition().getPosX() + 16 - Minecraft.getMinecraft().fontRenderer.getStringWidth(l) * scale + 2;
        float posY = getPosition().getPosY() + 16 - 8 * scale + 2;
        RenderUtils.drawStringSized(l, posX, posY, 16777215, true, scale, false);
    }

    @Override
    public void drawBackground(int mouseX, int mouseY, float partialTicks) {
        Minecraft.getMinecraft().getTextureManager().bindTexture(getTexture());
        Gui.drawModalRectWithCustomSizedTexture(getRealPosition().getPosX(), getRealPosition().getPosY(), 0, 0, getTextureSize().getSizeX(), getTextureSize().getSizeY(), getTextureSize().getSizeX(), getTextureSize().getSizeY());

        if (!isFluidStackValid(fluidStack))
            return;

        ResourceLocation sprite;
        Fluid fluid = fluidStack.getFluid();
        if (fluid.getStill(fluidStack) != null)
            sprite = fluid.getStill(fluidStack);
        else if (fluid.getFlowing(fluidStack) != null)
            sprite = fluid.getFlowing(fluidStack);
        else sprite = FluidRegistry.LAVA.getStill();
        TextureAtlasSprite texture = FMLClientHandler.instance().getClient().getTextureMapBlocks().getAtlasSprite(sprite.toString());

        int renderAmount = Math.max(Math.min(FLUID_RENDER_SIZE, fluidStack.amount * FLUID_RENDER_SIZE / capacity), 1);

        Minecraft.getMinecraft().getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
        int color = fluid.getColor(fluidStack);
        GL11.glColor3ub((byte) (color >> FLUID_RENDER_SIZE & 0xFF), (byte) (color >> 8 & 0xFF), (byte) (color & 0xFF));

        for (int i = 0; i < FLUID_RENDER_SIZE; i += FLUID_RENDER_SIZE) {
            for (int j = 0; j < renderAmount; j += FLUID_RENDER_SIZE) {
                int drawWidth = Math.min(FLUID_RENDER_SIZE - i, FLUID_RENDER_SIZE);
                int drawHeight = Math.min(renderAmount - j, FLUID_RENDER_SIZE);

                int drawX = getRealPosition().getPosX() + i + 1;
                int drawY = (getRealPosition().getPosY() + getSize().getSizeY() - renderAmount) + j + 1;
                double v = texture.getMinV() + (texture.getMaxV() - texture.getMinV()) * drawHeight / FLUID_RENDER_SIZE;
                double u = texture.getMinU() + (texture.getMaxU() - texture.getMinU()) * drawWidth / FLUID_RENDER_SIZE;

                Tessellator tessellator = Tessellator.getInstance();
                BufferBuilder buffer = tessellator.getBuffer();
                buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
                buffer.pos(drawX, drawY + drawHeight, 0).tex(texture.getMinU(), v).endVertex();
                buffer.pos(drawX + drawWidth, drawY + drawHeight, 0).tex(u, v).endVertex();
                buffer.pos(drawX + drawWidth, drawY, 0).tex(u, texture.getMinV()).endVertex();
                buffer.pos(drawX, drawY, 0).tex(texture.getMinU(), texture.getMinV()).endVertex();
                tessellator.draw();
            }
        }

        GL11.glColor4ub((byte) 255, (byte) 255, (byte) 255, (byte) 255);
        GlStateManager.color(1, 1, 1, 1);
    }

    private boolean isFluidStackValid(FluidStack fluidStack) {
        return ((FluidSlotData) slotData).getCapacity() > 0 && fluidStack != null && fluidStack.getFluid() != null && fluidStack.amount > 0;
    }

    @Override
    public List<String> getTooltips(int mouseX, int mouseY) {
        if (!isActive || !isVisible || !isHovered(mouseX, mouseY))
            return Collections.emptyList();

        return isFluidStackValid(fluidStack) ? Collections.singletonList(Utils.localise("tooltip.craftorium.machine.fluid", fluidStack.getLocalizedName(), fluidStack.amount, capacity)) : Collections.emptyList();
    }

    @Override
    public ResourceLocation getTexture() {
        return switch (slotData.getFlow()) {
            case INPUT -> INPUT;
            case OUTPUT -> OUTPUT;
            default -> FREE;
        };
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        String name = ByteBufUtils.readUTF8String(buf);
        int amount = buf.readInt();
        fluidStack = name != EMPTY_FLUID_STACK && FluidRegistry.isFluidRegistered(name) ? new FluidStack(FluidRegistry.getFluid(name), amount) : null;
        capacity = buf.readInt();
    }
}
