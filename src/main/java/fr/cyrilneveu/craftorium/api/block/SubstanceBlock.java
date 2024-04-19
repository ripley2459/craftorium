package fr.cyrilneveu.craftorium.api.block;

import fr.cyrilneveu.craftorium.api.render.FaceProvider;
import fr.cyrilneveu.craftorium.api.render.ModelTemplate;
import fr.cyrilneveu.craftorium.api.substance.Substance;
import fr.cyrilneveu.craftorium.api.substance.object.ASubstanceObject;
import fr.cyrilneveu.craftorium.api.utils.Utils;
import fr.cyrilneveu.craftorium.api.world.stone.StoneProperty;
import fr.cyrilneveu.craftorium.api.world.stone.StoneType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.Nullable;

import static fr.cyrilneveu.craftorium.api.utils.Utils.BLOCK_MODEL_BUILDER;
import static fr.cyrilneveu.craftorium.api.utils.Utils.WHITE_COLOR;
import static fr.cyrilneveu.craftorium.common.world.StoneTypes.STONE;
import static fr.cyrilneveu.craftorium.common.world.StoneTypes.STONES_REGISTRY;

public class SubstanceBlock extends CustomBlock {
    protected final ASubstanceObject reference;
    protected final Substance substance;

    public SubstanceBlock(Material material, ASubstanceObject reference, Substance substance) {
        super(material, reference.getFaces(substance));
        this.reference = reference;
        this.substance = substance;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void onModelBake(ModelBakeEvent event) {
        BLOCK_MODEL_BUILDER.newOperation(reference.getModelTemplate(substance));

        for (FaceProvider face : faceProviders)
            BLOCK_MODEL_BUILDER.addLayer(face.getTexture());

        event.getModelRegistry().putObject(Utils.getSimpleModelLocation(this), BLOCK_MODEL_BUILDER.build().getModel());
    }

    public static class OreBlock extends SubstanceBlock {
        public static final StoneProperty STONE_VARIANT = new StoneProperty();

        public OreBlock(Material material, ASubstanceObject reference, Substance substance) {
            super(material, reference, substance);
            setDefaultState(getBlockState().getBaseState().withProperty(STONE_VARIANT, STONE));
        }

        @Override
        protected BlockStateContainer createBlockState() {
            return new BlockStateContainer(this, STONE_VARIANT);
        }

        @Override
        public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos pos) {
            return state;
        }

        @Override
        public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
            return getDefaultState().withProperty(STONE_VARIANT, STONE_VARIANT.getAllowedValues().get(placer.getHeldItem(hand).getMetadata()));
        }

        @Override
        public IBlockState getStateFromMeta(int meta) {
            return getDefaultState().withProperty(STONE_VARIANT, StoneProperty.getByMeta(meta));
        }

        @Override
        public int getMetaFromState(IBlockState state) {
            return state.getValue(STONE_VARIANT).getMeta();
        }

        @Override
        public int damageDropped(IBlockState state) {
            return state.getValue(STONE_VARIANT).getMeta();
        }

        @Override
        @SideOnly(Side.CLIENT)
        public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
            for (StoneType stoneType : STONES_REGISTRY.getAll().values()) {
                items.add(new ItemStack(this, 1, stoneType.getMeta()));
            }
        }

        @Override
        @SideOnly(Side.CLIENT)
        public int colorMultiplier(IBlockState state, @Nullable IBlockAccess worldIn, @Nullable BlockPos pos, int layer) {
            return layer == 1 ? faceProviders[0].getColor() : WHITE_COLOR;
        }

        @Override
        @SideOnly(Side.CLIENT)
        public int colorMultiplier(ItemStack stack, int layer) {
            return layer == 1 ? faceProviders[0].getColor() : WHITE_COLOR;
        }

        @Override
        @SideOnly(Side.CLIENT)
        public void onModelRegister() {
            ModelLoader.setCustomStateMapper(this, new StateMapperBase() {
                @Override
                protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
                    return new ModelResourceLocation(state.getBlock().getRegistryName().toString() + "_" + state.getValue(STONE_VARIANT).getName());
                }
            });

            for (IBlockState s : blockState.getValidStates()) {
                StoneType stoneType = s.getValue(STONE_VARIANT);
                ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), stoneType.getMeta(), new ModelResourceLocation(getRegistryName() + "_" + stoneType.getName()));
            }
        }

        @Override
        @SideOnly(Side.CLIENT)
        public void onModelBake(ModelBakeEvent event) {
            ModelTemplate template = reference.getModelTemplate(substance);

            for (IBlockState s : blockState.getValidStates()) {
                BLOCK_MODEL_BUILDER.newOperation(template);

                StoneType stoneType = s.getValue(STONE_VARIANT);
                BLOCK_MODEL_BUILDER.addLayer(STONES_REGISTRY.get(stoneType.getName()).getTexture());
                BLOCK_MODEL_BUILDER.addLayer(faceProviders[0].getTexture()); // The ore return a single texture.

                event.getModelRegistry().putObject(new ModelResourceLocation(getRegistryName() + "_" + stoneType.getName()), BLOCK_MODEL_BUILDER.build().getModel());
            }
        }
    }
}
