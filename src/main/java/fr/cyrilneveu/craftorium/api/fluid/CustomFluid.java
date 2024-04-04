package fr.cyrilneveu.craftorium.api.fluid;

import fr.cyrilneveu.craftorium.api.render.FaceProvider;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

public class CustomFluid extends Fluid {
    private final int color;

    public CustomFluid(String name, FaceProvider still, FaceProvider flowing, int color) {
        super(name, still.getTexture(), flowing.getTexture());
        this.color = color;
    }

    @Override
    public int getColor() {
        return color;
    }

    @Override
    public int getColor(FluidStack stack) {
        return getColor();
    }
}
