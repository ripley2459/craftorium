package fr.cyrilneveu.craftorium.api.fluid;

import fr.cyrilneveu.craftorium.api.render.FaceProvider;
import net.minecraft.block.material.Material;
import net.minecraftforge.fluids.BlockFluidClassic;
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
        return color;
    }

    public static class CustomFluidBlock extends BlockFluidClassic {
        public CustomFluidBlock(Fluid fluid, Material material) {
            super(fluid, material);
        }

        /*@Override
        @SideOnly(Side.CLIENT)
        public int colorMultiplier(IBlockState state, @Nullable IBlockAccess worldIn, @Nullable BlockPos pos, int tintIndex) {
            return getFluid().getColor();
        }

        @Override
        @SideOnly(Side.CLIENT)
        public int colorMultiplier(ItemStack stack, int tintIndex) {
            return getFluid().getColor();
        }*/
    }
}
