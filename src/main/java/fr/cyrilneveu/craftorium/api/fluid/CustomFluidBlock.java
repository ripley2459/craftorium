package fr.cyrilneveu.craftorium.api.fluid;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import org.jetbrains.annotations.Nullable;

public class CustomFluidBlock extends BlockFluidClassic implements IBlockColor, IItemColor {
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
