package fr.cyrilneveu.craftorium.api.utils;

public final class Position {
    public static final Position ORIGIN = new Position(0, 0);
    private final int posX;
    private final int posY;

    public Position(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public Position add(Position other) {
        return new Position(posX + other.getPosX(), posY + other.getPosY());
    }

    public Position add(Size other) {
        return new Position(posX + other.getSizeX(), posY + other.getSizeY());
    }

    public Position subtract(Position other) {
        return new Position(posX - other.getPosX(), posY - other.getPosY());
    }

    public Position subtract(Size other) {
        return new Position(posX - other.getSizeX(), posY - other.getSizeY());
    }
}
