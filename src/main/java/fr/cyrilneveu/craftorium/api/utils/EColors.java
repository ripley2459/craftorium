package fr.cyrilneveu.craftorium.api.utils;

public enum EColors {
    WHITE(0, "white", 0xFFe4e4e4),
    ORANGE(1, "orange", 0xFFea7e35),
    MAGENTA(2, "magenta", 0xFFbe49c9),
    LIGHT_BLUE(3, "light_blue", 0xFF6387d2),
    YELLOW(4, "yellow", 0xFFc2b51c),
    LIME(5, "lime", 0xFF39ba2e),
    PINK(6, "pink", 0xFFd98199),
    GRAY(7, "gray", 0xFF414141),
    LIGHT_GRAY(8, "light_gray", 0xFFa0a7a7),
    CYAN(9, "cyan", 0xFF267191),
    PURPLE(10, "purple", 0xFF7e34bf),
    BLUE(11, "blue", 0xFF253193),
    BROWN(12, "brown", 0xFF56331c),
    GREEN(13, "green", 0xFF364b18),
    RED(14, "red", 0xFF9e2b27),
    BLACK(15, "black", 0xFF181414);

    private final int meta;
    private final String name;
    private final int color;

    EColors(int meta, String name, int color) {
        this.meta = meta;
        this.name = name;
        this.color = color;
    }

    public int getMeta() {
        return meta;
    }

    public String getName() {
        return name;
    }

    public int getColor() {
        return color;
    }
}
