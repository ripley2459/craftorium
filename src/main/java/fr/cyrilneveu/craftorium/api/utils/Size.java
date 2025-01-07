package fr.cyrilneveu.craftorium.api.utils;

public final class Size {
    public static final Size ZERO = new Size(0, 0);
    private final int sizeX;
    private final int sizeY;

    public Size(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }
}
