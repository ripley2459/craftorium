package fr.cyrilneveu.craftorium.api.block;

import com.google.common.base.Preconditions;
import fr.cyrilneveu.craftorium.api.property.Aestheticism;
import fr.cyrilneveu.craftorium.api.render.FaceProvider;
import fr.cyrilneveu.craftorium.api.render.ICustomModel;
import fr.cyrilneveu.craftorium.api.render.ModelTemplates;
import fr.cyrilneveu.craftorium.api.utils.Utils;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Set;

import static fr.cyrilneveu.craftorium.api.utils.Utils.BLOCK_MODEL_BUILDER;

public class CustomBlock extends Block implements ICustomModel, IBlockColor, IItemColor {
    protected final Aestheticism.ObjectAestheticism aestheticism;

    public CustomBlock(Material material, Aestheticism.ObjectAestheticism aestheticism) {
        super(material);
        this.aestheticism = aestheticism;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int colorMultiplier(IBlockState state, @Nullable IBlockAccess worldIn, @Nullable BlockPos pos, int layer) {
        return aestheticism.getFaceProviders()[layer].getColor();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int colorMultiplier(ItemStack stack, int layer) {
        return aestheticism.getFaceProviders()[layer].getColor();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addTextures(Set<ResourceLocation> textures) {
        for (FaceProvider faceProvider : aestheticism.getFaceProviders())
            textures.add(faceProvider.getTexture());
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void onModelRegister() {
        ModelLoader.setCustomStateMapper(this, Utils.SIMPLE_STATE_MAPPER.apply(this));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, Utils.getSimpleModelLocation(this));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void onModelBake(ModelBakeEvent event) {
        Preconditions.checkArgument(aestheticism.getFaceProviders().length == 1);

        BLOCK_MODEL_BUILDER.newOperation(ModelTemplates.BLOCK);

        for (FaceProvider face : aestheticism.getFaceProviders())
            BLOCK_MODEL_BUILDER.addLayer(face.getTexture());

        event.getModelRegistry().putObject(Utils.getSimpleModelLocation(this), BLOCK_MODEL_BUILDER.build().getModel());
    }

    public static class CustomItemBlock extends ItemBlock {
        public CustomItemBlock(Block block) {
            super(block);
        }

        @Override
        @SideOnly(Side.CLIENT)
        public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
            if (((CustomBlock) block).aestheticism.getToolTips() != null)
                tooltip.addAll(((CustomBlock) block).aestheticism.getToolTips().get());
        }

        @Override
        @SideOnly(Side.CLIENT)
        public boolean hasEffect(ItemStack stack) {
            return ((CustomBlock) block).aestheticism.isGlint();
        }
    }
}
