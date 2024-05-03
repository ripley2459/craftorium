package fr.cyrilneveu.craftorium.api.world;

import com.google.common.base.Predicate;
import fr.cyrilneveu.craftorium.api.world.stone.StoneType;
import fr.cyrilneveu.craftorium.api.world.vein.Vein;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

import static fr.cyrilneveu.craftorium.api.substance.object.SubstanceBlock.OreBlock.STONE_VARIANT;
import static fr.cyrilneveu.craftorium.common.substance.SubstancesObjects.ORE;
import static fr.cyrilneveu.craftorium.common.world.StoneTypes.STONES_REGISTRY;

public final class VeinGeneration extends WorldGenerator {
    private final Vein vein;
    private final int numberOfBlocks;

    public VeinGeneration(Vein vein) {
        this.vein = vein;
        this.numberOfBlocks = vein.getSizeH() * vein.getSizeV() + 1;
    }

    @Override
    public boolean generate(World world, Random random, BlockPos position) {
        CustomPredicate predicate = new CustomPredicate(world.provider.getDimension());

        float randomBetween0Pi = random.nextFloat() * (float) Math.PI;
        double limitMinX = (float) (position.getX() + 8) + MathHelper.sin(randomBetween0Pi) * (float) this.numberOfBlocks / 8.0f;
        double limitMaxX = (float) (position.getX() + 8) - MathHelper.sin(randomBetween0Pi) * (float) this.numberOfBlocks / 8.0f;
        double limitMinZ = (float) (position.getZ() + 8) + MathHelper.cos(randomBetween0Pi) * (float) this.numberOfBlocks / 8.0f;
        double limitMaxZ = (float) (position.getZ() + 8) - MathHelper.cos(randomBetween0Pi) * (float) this.numberOfBlocks / 8.0f;
        double limitMinY = position.getY() + random.nextInt(vein.getSizeV()) - (double) vein.getSizeV() / 2;
        double limitMaxY = position.getY() + random.nextInt(vein.getSizeV()) - (double) vein.getSizeV() / 2;

        for (int i = 0; i < this.numberOfBlocks; ++i) {
            float progress = (float) i / (float) this.numberOfBlocks;

            double potentialX = limitMinX + (limitMaxX - limitMinX) * (double) progress;
            double potentialY = limitMinY + (limitMaxY - limitMinY) * (double) progress;
            double potentialZ = limitMinZ + (limitMaxZ - limitMinZ) * (double) progress;

            double d9 = random.nextDouble() * (double) this.numberOfBlocks / 16.0D;
            double d10 = (double) (MathHelper.sin((float) Math.PI * progress) + 1.0F) * d9 + 1.0D;
            double d11 = (double) (MathHelper.sin((float) Math.PI * progress) + 1.0F) * d9 + 1.0D;

            int j = MathHelper.floor(potentialX - d10 / 2.0D);
            int k = MathHelper.floor(potentialY - d11 / 2.0D);
            int l = MathHelper.floor(potentialZ - d10 / 2.0D);
            int i1 = MathHelper.floor(potentialX + d10 / 2.0D);
            int j1 = MathHelper.floor(potentialY + d11 / 2.0D);
            int k1 = MathHelper.floor(potentialZ + d10 / 2.0D);

            for (int posX = j; posX <= i1; ++posX) {
                double d12 = ((double) posX + 0.5D - potentialX) / (d10 / 2.0D);
                if (d12 * d12 < 1.0D) {
                    for (int posY = k; posY <= j1; ++posY) {
                        double d13 = ((double) posY + 0.5D - potentialY) / (d11 / 2.0D);
                        if (d12 * d12 + d13 * d13 < 1.0D) {
                            for (int posZ = l; posZ <= k1; ++posZ) {
                                double d14 = ((double) posZ + 0.5D - potentialZ) / (d10 / 2.0D);
                                if (d12 * d12 + d13 * d13 + d14 * d14 < 1.0D) {

                                    BlockPos blockpos = new BlockPos(posX, posY, posZ);
                                    IBlockState state = world.getBlockState(blockpos);
                                    if (state.getBlock().isReplaceableOreGen(state, world, blockpos, predicate) && predicate.getMeta() != null) {
                                        IBlockState block = ORE.asBlock(vein.getComposition().get(random)).getDefaultState().withProperty(STONE_VARIANT, STONE_VARIANT.getAllowedValues().get(predicate.getMeta()));
                                        world.setBlockState(blockpos, block, 2);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return true;
    }

    private static final class CustomPredicate implements Predicate<IBlockState> {
        private final int dimensionId;
        private Integer meta;

        public CustomPredicate(int dimensionId) {
            this.dimensionId = dimensionId;
        }

        @Override
        public boolean apply(@Nullable IBlockState other) {
            for (StoneType stoneType : STONES_REGISTRY.getAll().values()) {
                if (stoneType.getDimension() == dimensionId && other == stoneType.getBlockState()) {
                    meta = stoneType.getMeta();
                    return true;
                }
            }

            meta = null;

            return false;
        }

        public int getDimensionId() {
            return dimensionId;
        }

        @Nullable
        public Integer getMeta() {
            return meta;
        }
    }
}
