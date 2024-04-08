package fr.cyrilneveu.craftorium.api.property;

public final class Aestheticism {
    private final String style;
    private final boolean shiny, glow;
    private final int baseColor, oreColor, fluidColor;

    public Aestheticism(String style, boolean shiny, boolean glow, int baseColor) {
        this(style, shiny, glow, baseColor, baseColor, baseColor);
    }

    public Aestheticism(String style, boolean shiny, boolean glow, int baseColor, int oreColor, int fluidColor) {
        this.style = style;
        this.shiny = shiny;
        this.glow = glow;
        this.baseColor = baseColor;
        this.oreColor = oreColor;
        this.fluidColor = fluidColor;
    }

    public String getStyle() {
        return style;
    }

    public boolean isShiny() {
        return shiny;
    }

    public boolean isGlow() {
        return glow;
    }

    public int getBaseColor() {
        return baseColor;
    }

    public int getOreColor() {
        return oreColor;
    }

    public int getFluidColor() {
        return fluidColor;
    }
}
