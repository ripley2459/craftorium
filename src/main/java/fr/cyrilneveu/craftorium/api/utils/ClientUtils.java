package fr.cyrilneveu.craftorium.api.utils;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public final class ClientUtils {
    public static void spawnParticleAt(IBlockState state, World world, BlockPos pos, Random rand, EnumFacing side, EnumParticleTypes particleType) {
        double d0 = pos.getX() + 0.5D;
        double d1 = pos.getY() + 0.125D + rand.nextDouble() * 0.75D;
        double d2 = pos.getZ() + 0.5D;
        double d3 = 0.52D;
        double d4 = rand.nextDouble() * 0.6D - 0.3D;

        switch (side) {
            case WEST:
                world.spawnParticle(particleType, d0 - d3, d1, d2 + d4, 0D, 0D, 0D);
                break;
            case EAST:
                world.spawnParticle(particleType, d0 + d3, d1, d2 + d4, 0D, 0D, 0D);
                break;
            case NORTH:
                world.spawnParticle(particleType, d0 + d4, d1, d2 - d3, 0D, 0D, 0D);
                break;
            case SOUTH:
                world.spawnParticle(particleType, d0 + d4, d1, d2 + d3, 0D, 0D, 0D);
            default:
                break;
        }
    }

    public static void playSoundAt(World world, BlockPos pos, Random rand, SoundCategory soundCategory, SoundEvent sound) {
        world.playSound(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, sound, soundCategory, 1.0F, 1.0F, false);
    }

    public static void playSound(SoundEvent sound) {
        Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(sound, 1.0F));
    }
}
