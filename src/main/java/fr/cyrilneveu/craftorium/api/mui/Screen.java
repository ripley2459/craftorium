package fr.cyrilneveu.craftorium.api.mui;

import fr.cyrilneveu.craftorium.api.utils.Size;

public final class Screen {
    private final AWidget[] widgets;
    private final Size size;

    public Screen(AWidget[] widgets, Size size) {
        this.widgets = widgets;
        this.size = size;
    }

    public AWidget[] getWidgets() {
        return widgets;
    }

    public Size getSize() {
        return size;
    }
}
