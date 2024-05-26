package fr.cyrilneveu.craftorium.api.mui;

import fr.cyrilneveu.craftorium.api.utils.Position;
import fr.cyrilneveu.craftorium.api.utils.Size;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;

import static fr.cyrilneveu.craftorium.api.machine.behaviour.PlayerInventory.BR;
import static fr.cyrilneveu.craftorium.api.utils.Size.ZERO;

public class PlayerInventory extends AWidget implements ITextured {
    public PlayerInventory(Position position) {
        super(position, ZERO);
    }

    @Override
    public void drawBackground(int mouseX, int mouseY, float partialTicks) {
        Minecraft.getMinecraft().getTextureManager().bindTexture(getTexture());

        Position p1 = new Position(getRealPosition().getPosX(), getRealPosition().getPosY());
        fr.cyrilneveu.craftorium.api.machine.behaviour.PlayerInventory.forPlayerBagSlots((r, c) -> Gui.drawModalRectWithCustomSizedTexture(p1.getPosX() + c * 18, p1.getPosY() + r * 18, 0, 0, ItemSlot.SLOT_TEXTURE_SIZE.getSizeX(), ItemSlot.SLOT_TEXTURE_SIZE.getSizeY(), ItemSlot.SLOT_TEXTURE_SIZE.getSizeX(), ItemSlot.SLOT_TEXTURE_SIZE.getSizeY()));

        Position p2 = new Position(p1.getPosX(), getRealPosition().getPosY() + 3 * 18 + BR);
        fr.cyrilneveu.craftorium.api.machine.behaviour.PlayerInventory.forPlayerBarSlots(s -> Gui.drawModalRectWithCustomSizedTexture(p2.getPosX() + s * 18, p2.getPosY(), 0, 0, ItemSlot.SLOT_TEXTURE_SIZE.getSizeX(), ItemSlot.SLOT_TEXTURE_SIZE.getSizeY(), ItemSlot.SLOT_TEXTURE_SIZE.getSizeX(), ItemSlot.SLOT_TEXTURE_SIZE.getSizeY()));
    }

    @Override
    public ResourceLocation getTexture() {
        return ItemSlot.FREE;
    }

    @Override
    public Size getTextureSize() {
        return ItemSlot.SLOT_TEXTURE_SIZE;
    }
}
