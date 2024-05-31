package fr.cyrilneveu.craftorium.api.mui;

import fr.cyrilneveu.craftorium.api.inventory.ItemSlotData;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;

import static fr.cyrilneveu.craftorium.CraftoriumTags.MODID;

public class ItemSlot extends ASlot {
    public static final ResourceLocation INPUT = new ResourceLocation(MODID, "textures/interfaces/elements/slot_item_input.png");
    public static final ResourceLocation OUTPUT = new ResourceLocation(MODID, "textures/interfaces/elements/slot_item_output.png");
    public static final ResourceLocation FREE = new ResourceLocation(MODID, "textures/interfaces/elements/slot_item.png");

    public ItemSlot(ItemSlotData slotData) {
        super(slotData);
    }

    @Override
    public void drawBackground(int mouseX, int mouseY, float partialTicks) {
        if (!isActive())
            return;

        Minecraft.getMinecraft().getTextureManager().bindTexture(getTexture());
        Gui.drawModalRectWithCustomSizedTexture(getRealPosition().getPosX(), getRealPosition().getPosY(), 0, 0, getTextureSize().getSizeX(), getTextureSize().getSizeY(), getTextureSize().getSizeX(), getTextureSize().getSizeY());
    }

    @Override
    public ResourceLocation getTexture() {
        return switch (slotData.getFlow()) {
            case INPUT -> INPUT;
            case OUTPUT -> OUTPUT;
            default -> FREE;
        };
    }
}
