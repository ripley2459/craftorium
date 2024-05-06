package fr.cyrilneveu.craftorium.api.property;

import fr.cyrilneveu.craftorium.api.render.FaceProvider;
import net.minecraft.block.SoundType;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Supplier;

public final class Aestheticism {
    public static final class ObjectAestheticism {
        private final FaceProvider[] faceProviders;
        @Nullable
        private final Supplier<List<String>> toolTips;
        private final boolean glint;

        public ObjectAestheticism(FaceProvider[] faceProviders, @Nullable Supplier<List<String>> toolTips, boolean glint) {
            this.faceProviders = faceProviders;
            this.toolTips = toolTips;
            this.glint = glint;
        }

        public FaceProvider[] getFaceProviders() {
            return faceProviders;
        }

        public Supplier<List<String>> getToolTips() {
            return toolTips;
        }

        public boolean isGlint() {
            return glint;
        }
    }

    public static final class SubstanceAestheticism {
        private final String style;
        private final boolean shiny, glint;
        private final int baseColor, oreColor, fluidColor;
        private final SoundType soundType;

        public SubstanceAestheticism(String style, boolean shiny, boolean glint, int baseColor, SoundType soundType) {
            this(style, shiny, glint, baseColor, baseColor, baseColor, soundType);
        }

        public SubstanceAestheticism(String style, boolean shiny, boolean glint, int baseColor, int oreColor, int fluidColor, SoundType soundType) {
            this.style = style;
            this.shiny = shiny;
            this.glint = glint;
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

        public boolean isGlint() {
            return glint;
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
}
