package fr.cyrilneveu.craftorium.api.fluid;

import fr.cyrilneveu.craftorium.api.render.FaceProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import org.jetbrains.annotations.Nullable;

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

    public static class CustomFluidBlock extends BlockFluidClassic implements IBlockColor, IItemColor {
        public CustomFluidBlock(Fluid fluid, Material material) {
            super(fluid, material);
        }

        @Override
        public int colorMultiplier(IBlockState state, @Nullable IBlockAccess worldIn, @Nullable BlockPos pos, int tintIndex) {
            return getFluid().getColor();
        }

        @Override
        public int colorMultiplier(ItemStack stack, int tintIndex) {
            return getFluid().getColor();
        }
    }
}
