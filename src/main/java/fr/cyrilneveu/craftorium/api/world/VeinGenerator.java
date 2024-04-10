package fr.cyrilneveu.craftorium.api.world;

import fr.cyrilneveu.craftorium.api.world.vein.Vein;
import fr.cyrilneveu.craftorium.common.config.Settings;
import fr.cyrilneveu.craftorium.common.world.Veins;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.event.terraingen.OreGenEvent;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Arrays;
import java.util.Random;

public class VeinGenerator implements IWorldGenerator {
    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        for (int i = 0; i < Settings.generationSettings.veinDensity; i++) {
            Vein vein = Veins.getVein(random, world.provider.getDimension());
            if (vein == null)
                break;

            VeinGeneration ore = new VeinGeneration(vein);
            int heightDiff = vein.getMaxY() - vein.getMinY() + 1;
            int x = chunkX * 16 + random.nextInt(16);
            int y = vein.getMinY() + random.nextInt(heightDiff);
            int z = chunkZ * 16 + random.nextInt(16);
            ore.generate(world, random, new BlockPos(x, y, z));
        }
    }

    public static class WorldGeneratorReplacer {
        private static final OreGenEvent.GenerateMinable.EventType[] PREVENT = new OreGenEvent.GenerateMinable.EventType[]{
                OreGenEvent.GenerateMinable.EventType.COAL,
                OreGenEvent.GenerateMinable.EventType.DIAMOND,
                OreGenEvent.GenerateMinable.EventType.EMERALD,
                OreGenEvent.GenerateMinable.EventType.GOLD,
                OreGenEvent.GenerateMinable.EventType.IRON,
                OreGenEvent.GenerateMinable.EventType.LAPIS,
                OreGenEvent.GenerateMinable.EventType.REDSTONE,
                OreGenEvent.GenerateMinable.EventType.QUARTZ,
        };

        @SubscribeEvent
        public void onGenerateMinable(OreGenEvent.GenerateMinable event) {
            if (Arrays.asList(PREVENT).contains(event.getType()))
                event.setResult(Event.Result.DENY);
        }
    }
}
