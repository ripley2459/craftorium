package fr.cyrilneveu.craftorium.api.property;

import net.minecraft.block.SoundType;

public final class Aestheticism {
    private final String style;
    private final boolean shiny, glow;
    private final int baseColor, oreColor, fluidColor;
    private final SoundType soundType;

    public Aestheticism(String style, boolean shiny, boolean glow, int baseColor, SoundType soundType) {
        this(style, shiny, glow, baseColor, baseColor, baseColor, soundType);
    }

    public Aestheticism(String style, boolean shiny, boolean glow, int baseColor, int oreColor, int fluidColor, SoundType soundType) {
        this.style = style;
        this.shiny = shiny;
        this.glow = glow;
        this.baseColor = baseColor;
        this.oreColor = oreColor;
        this.fluidColor = fluidColor;
        this.soundType = soundType;
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

    public SoundType getSoundType() {
        return soundType;
    }
}
