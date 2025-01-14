package fr.cyrilneveu.craftorium.api.machine;

import com.google.common.base.Preconditions;
import fr.cyrilneveu.craftorium.Craftorium;
import fr.cyrilneveu.craftorium.api.block.CustomBlock;
import fr.cyrilneveu.craftorium.api.config.Settings;
import fr.cyrilneveu.craftorium.api.property.Aestheticism;
import fr.cyrilneveu.craftorium.api.render.*;
import fr.cyrilneveu.craftorium.api.substance.Tier;
import fr.cyrilneveu.craftorium.api.utils.ClientUtils;
import fr.cyrilneveu.craftorium.api.utils.CustomIterable;
import fr.cyrilneveu.craftorium.api.utils.RenderUtils;
import fr.cyrilneveu.craftorium.api.utils.Utils;
import fr.cyrilneveu.craftorium.common.machine.Machines;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.model.MultipartBakedModel;
import net.minecraft.client.renderer.block.model.multipart.ConditionAnd;
import net.minecraft.client.renderer.block.model.multipart.ConditionPropertyValue;
import net.minecraft.client.renderer.block.model.multipart.ICondition;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ChunkCache;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

import java.util.Random;
import java.util.Set;
import java.util.stream.IntStream;

import static fr.cyrilneveu.craftorium.api.inventory.GuiHandler.MACHINE_GUI_ID;
import static fr.cyrilneveu.craftorium.api.utils.RenderUtils.BLOCK_MODEL_BUILDER;

public class MachineBlock extends CustomBlock implements ITileEntityProvider {
    public static final PropertyEnum<EMachineStates> STATE = PropertyEnum.create("state", EMachineStates.class);
    public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
    private final Machine machine;
    private final Tier tier;

    public MachineBlock(Machine machine, Tier tier) {
        super(Material.IRON, Machines.getAestheticism(machine, tier));
        this.machine = machine;
        this.tier = tier;
        setSoundType(SoundType.METAL);
        setHardness(5.0F);
        setResistance(10.0F);
        setHarvestLevel("pickaxe", 3);
        setDefaultState(this.getBlockState().getBaseState().withProperty(FACING, EnumFacing.NORTH));
    }

    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        TileEntity tileEntity = worldIn instanceof ChunkCache ? ((ChunkCache) worldIn).getTileEntity(pos, Chunk.EnumCreateEntityType.CHECK) : worldIn.getTileEntity(pos);
        return tileEntity instanceof MachineTile machineTile ? state.withProperty(STATE, machineTile.getState()) : super.getActualState(state, worldIn, pos);
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FACING, STATE);
    }

    @Override
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        return getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        EnumFacing facing = EnumFacing.byIndex(meta);
        if (facing.getAxis() == EnumFacing.Axis.Y)
            facing = EnumFacing.NORTH;
        return getDefaultState().withProperty(FACING, facing);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(FACING).getIndex();
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (worldIn.isRemote)
            return true;

        if (worldIn.getTileEntity(pos) instanceof MachineTile && !playerIn.isSneaking()) {
            playerIn.openGui(Craftorium.instance, MACHINE_GUI_ID, worldIn, pos.getX(), pos.getY(), pos.getZ());
            return true;
        }

        return false;
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        TileEntity tile = worldIn.getTileEntity(pos);
        if (tile instanceof MachineTile) {
            IItemHandler itemStackHandler = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
            if (itemStackHandler != null)
                IntStream.range(0, itemStackHandler.getSlots()).forEach(i -> worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), itemStackHandler.getStackInSlot(i))));
        }

        super.breakBlock(worldIn, pos, state);
    }

    @Override
    public boolean hasTileEntity() {
        return true;
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new MachineTile(machine, tier);
    }

    @Override
    public void onModelRegister() {
        ModelLoader.setCustomStateMapper(this, RenderUtils.SIMPLE_STATE_MAPPER.apply(this));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void onModelBake(ModelBakeEvent event) {
        MultipartBakedModel.Builder builder = new MultipartBakedModel.Builder();
        FaceProvider[] faces = aestheticism.getFaceProviders();

        Preconditions.checkArgument(faces.length == 4, "This kind of block use exactly 4 textures!");

        for (EMachineStates s : STATE.getAllowedValues()) {
            for (EnumFacing f : FACING.getAllowedValues()) {
                BLOCK_MODEL_BUILDER.newOperation(ModelTemplates.BLOCK_TINTED_OVERLAY_ORIENTED);

                ModelBuilder face = BLOCK_MODEL_BUILDER.addTexture("layer0", faces[0].getTexture())
                        .addTexture("side", faces[1].getTexture())
                        .addTexture("north", faces[s == EMachineStates.WORKING ? 3 : 2].getTexture())
                        .mutate(m -> new RotateModel(m, Utils.getRotationForFacing(f)))
                        .build();

                ICondition faceCondition = new ConditionPropertyValue(FACING.getName(), f.getName());
                ICondition stateCondition = new ConditionPropertyValue(STATE.getName(), s.getName());
                ICondition conditions = new ConditionAnd(new CustomIterable<>(faceCondition, stateCondition));

                builder.putModel(conditions.getPredicate(blockState), face.getModel());
            }
        }

        event.getModelRegistry().putObject(RenderUtils.getSimpleModelLocation(this), builder.makeMultipartModel());
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState state, World worldIn, BlockPos pos, Random rand) {
        TileEntity tile = worldIn.getTileEntity(pos);
        if (tile instanceof MachineTile tile1 && tile1.getState() == EMachineStates.WORKING) {
            if (Settings.machinesSettings.spawnParticles)
                ClientUtils.spawnParticleAt(state, worldIn, pos, rand, state.getValue(FACING), EnumParticleTypes.FLAME);
            if (Settings.machinesSettings.playSounds)
                ClientUtils.playSoundAt(worldIn, pos, rand, SoundCategory.BLOCKS, SoundEvents.BLOCK_FURNACE_FIRE_CRACKLE);
        }
    }

    public static final class MachineItemBlock extends CustomItemBlock implements ICustomModel {
        private final Machine machine;
        private final Tier tier;
        private final Aestheticism.ObjectAestheticism aestheticism;

        public MachineItemBlock(MachineBlock block) {
            super(block);
            this.machine = block.machine;
            this.tier = block.tier;
            this.aestheticism = Machines.getAestheticism(machine, tier);
        }

        @Override
        public String getItemStackDisplayName(ItemStack stack) {
            return machine.getDisplayName();
        }

        @Override
        @SideOnly(Side.CLIENT)
        public int getItemStackColor(ItemStack stack, int layer) {
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
            ModelResourceLocation location = RenderUtils.getSimpleModelLocation(this);
            ModelBakery.registerItemVariants(this, location);
            ModelLoader.setCustomMeshDefinition(this, stack -> location);
        }

        @Override
        @SideOnly(Side.CLIENT)
        public void onModelBake(ModelBakeEvent event) {
            FaceProvider[] faces = aestheticism.getFaceProviders();

            Preconditions.checkArgument(faces.length == 4, "This kind of block use exactly 4 textures!");

            BLOCK_MODEL_BUILDER.newOperation(ModelTemplates.BLOCK_TINTED_OVERLAY_ORIENTED);

            BLOCK_MODEL_BUILDER.addTexture("layer0", faces[0].getTexture())
                    .addTexture("side", faces[1].getTexture())
                    .addTexture("north", faces[2].getTexture());

            event.getModelRegistry().putObject(RenderUtils.getSimpleModelLocation(this), BLOCK_MODEL_BUILDER.build().getModel());
        }
    }
}
