package fr.cyrilneveu.craftorium.common.integration.jei.recipe.machine;

import fr.cyrilneveu.craftorium.api.inventory.ASlotData;
import fr.cyrilneveu.craftorium.api.utils.Position;
import fr.cyrilneveu.craftorium.api.utils.Size;

import java.util.List;

import static fr.cyrilneveu.craftorium.api.mui.ASlot.SLOT_TEXTURE_SIZE;
import static fr.cyrilneveu.craftorium.api.utils.Size.ZERO;

public final class MachineJeiData {
    private final String map;
    private final Position arrow;
    private final List<ASlotData> slots;
    private final Size minimalSize;
    private final Size offset;

    public MachineJeiData(String map, Position arrow, List<ASlotData> slots) {
        this.map = map;
        this.arrow = arrow;
        this.slots = slots;

        if (slots.isEmpty()) {
            this.minimalSize = ZERO;
            this.offset = ZERO;
            return;
        }

        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        int maxY = Integer.MIN_VALUE;
        ASlotData leftSlot = slots.get(0);
        ASlotData rightSlot = slots.get(0);
        ASlotData topSlot = slots.get(0);
        ASlotData bottomSlot = slots.get(0);

        for (ASlotData slot : slots) {
            if (slot.getPosition().getPosX() <= minX) {
                minX = slot.getPosition().getPosX();
                leftSlot = slot;
            }

            if (slot.getPosition().getPosY() <= minY) {
                minY = slot.getPosition().getPosY();
                topSlot = slot;
            }

            if (slot.getPosition().getPosX() >= maxX) {
                maxX = slot.getPosition().getPosX();
                rightSlot = slot;
            }

            if (slot.getPosition().getPosY() >= maxY) {
                maxY = slot.getPosition().getPosY();
                bottomSlot = slot;
            }
        }

        int left = leftSlot.getPosition().getPosX();
        int top = topSlot.getPosition().getPosY();
        int width = rightSlot.getPosition().getPosX() + SLOT_TEXTURE_SIZE.getSizeX();
        int height = bottomSlot.getPosition().getPosY() + SLOT_TEXTURE_SIZE.getSizeY();

        this.minimalSize = new Size(width, height);
        this.offset = new Size(left, top);
    }

    public String getMap() {
        return map;
    }

    public Position getArrow() {
        return arrow;
    }

    public List<ASlotData> getSlots() {
        return slots;
    }

    public Size getMinimalSize() {
        return minimalSize;
    }

    public Size getOffset() {
        return offset;
    }
}
