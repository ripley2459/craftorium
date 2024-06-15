package fr.cyrilneveu.craftorium.api.mui;

import fr.cyrilneveu.craftorium.api.inventory.EnergySlotData;
import fr.cyrilneveu.craftorium.api.utils.RenderUtils;
import fr.cyrilneveu.craftorium.api.utils.Size;
import fr.cyrilneveu.craftorium.api.utils.Utils;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;

import java.util.Collections;
import java.util.List;

import static fr.cyrilneveu.craftorium.CraftoriumTags.MODID;

public class EnergyBar extends AWidget implements ITextured {
    public static final ResourceLocation ENERGY = new ResourceLocation(MODID, "textures/interfaces/elements/energy.png");
    public static final Size SLOT_SIZE = new Size(14, 14);
    public static final Size TEXTURE_SIZE = new Size(28, 14);
    private int energy = 0;
    private int capacity = 0;

    public EnergyBar(EnergySlotData slotData) {
        super(slotData.getPosition(), new Size(14, 14));
        this.capacity = slotData.getCapacity();
    }

    @Override
    public ResourceLocation getTexture() {
        return ENERGY;
    }

    @Override
    public Size getTextureSize() {
        return TEXTURE_SIZE;
    }

    @Override
    public void drawBackground(int mouseX, int mouseY, float partialTicks) {
        if (!isActive())
            return;

        RenderUtils.bindTexture(getTexture());
        Gui.drawModalRectWithCustomSizedTexture(getRealPosition().getPosX(), getRealPosition().getPosY(), 0, 0, getSize().getSizeX(), getSize().getSizeY(), getTextureSize().getSizeX(), getTextureSize().getSizeY());

        if (capacity != 0) {
            int p = getTextureSize().getSizeY() * energy / capacity;
            Gui.drawModalRectWithCustomSizedTexture(getRealPosition().getPosX(), getRealPosition().getPosY() + getSize().getSizeY() - p, getSize().getSizeX(), getSize().getSizeY() - p, getSize().getSizeX(), p, getTextureSize().getSizeX(), getTextureSize().getSizeY());
        }
    }

    @Override
    public List<String> getTooltips(int mouseX, int mouseY) {
        if (!isActive() || !isHovered(mouseX, mouseY))
            return Collections.emptyList();

        return Collections.singletonList(Utils.localise("tooltip.craftorium.machine.slot.energy", energy, capacity));
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        energy = buf.readInt();
        capacity = buf.readInt();
    }
}
