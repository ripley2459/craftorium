package fr.cyrilneveu.craftorium.api.mui;

import fr.cyrilneveu.craftorium.api.utils.Size;

import static fr.cyrilneveu.craftorium.api.utils.Size.ZERO;

public final class Screen {
    private final AWidget[] widgets;
    private final Size size;
    private final Size minimalSize; // Used for JEI
    private final Size offset;

    public Screen(AWidget[] widgets, Size size) {
        this.widgets = widgets;
        this.size = size;

        if (widgets.length == 0) {
            this.minimalSize = ZERO;
            this.offset = ZERO;
            return;
        }

        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        int maxY = Integer.MIN_VALUE;
        AWidget leftSlot = widgets[0];
        AWidget rightSlot = widgets[0];
        AWidget topSlot = widgets[0];
        AWidget bottomSlot = widgets[0];

        for (AWidget slot : widgets) {
            if (!(slot instanceof ASlot))
                continue;

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
        int width = rightSlot.getPosition().getPosX() + rightSlot.getSize().getSizeX();
        int height = bottomSlot.getPosition().getPosY() + +rightSlot.getSize().getSizeY();

        this.minimalSize = new Size(width, height);
        this.offset = new Size(left, top);
    }

    public AWidget[] getWidgets() {
        return widgets;
    }

    public Size getSize() {
        return size;
    }

    public Size getMinimalSize() {
        return minimalSize;
    }

    public Size getOffset() {
        return offset;
    }
}
