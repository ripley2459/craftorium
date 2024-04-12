package fr.cyrilneveu.craftorium.api.fluid;

import fr.cyrilneveu.craftorium.api.substance.Substance;
import fr.cyrilneveu.craftorium.api.substance.object.ASubstanceObject;
import fr.cyrilneveu.craftorium.api.utils.Utils;
import net.minecraft.block.material.Material;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

public class SubstanceFluid extends CustomFluid {
    protected final ASubstanceObject reference;
    protected final Substance substance;

    public SubstanceFluid(ASubstanceObject reference, Substance substance) {
        super(reference.getName(substance), reference.getFaces(substance)[0], reference.getFaces(substance)[1], substance.getAestheticism().getFluidColor());
        this.reference = reference;
        this.substance = substance;
    }

    @Override
    public String getLocalizedName(FluidStack stack) {
        return Utils.localise(getUnlocalizedName(), substance.getDisplayName());
    }

    public static class SubstanceFluidBlock extends CustomFluidBlock {
        public SubstanceFluidBlock(Fluid fluid, Material material) {
            super(fluid, material);
        }
    }
}
