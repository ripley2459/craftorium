package fr.cyrilneveu.craftorium.common.world;

import com.google.common.base.Preconditions;
import fr.cyrilneveu.craftorium.api.utils.Registry;
import fr.cyrilneveu.craftorium.api.world.stone.StoneType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;

public final class StoneTypes {
    public static final Registry<String, StoneType> STONES_REGISTRY = new Registry<>();
    private static int META = 0;
    public static final StoneType STONE = createStone("stone", "minecraft:blocks/stone", Blocks.STONE.getStateFromMeta(0), 0);
    public static final StoneType GRANITE = createStone("granite", "minecraft:blocks/stone_granite", Blocks.STONE.getStateFromMeta(1), 0);
    public static final StoneType DIORITE = createStone("diorite", "minecraft:blocks/stone_diorite", Blocks.STONE.getStateFromMeta(3), 0);
    public static final StoneType ANDESITE = createStone("andesite", "minecraft:blocks/stone_andesite", Blocks.STONE.getStateFromMeta(5), 0);
    public static final StoneType NETHERRACK = createStone("netherrack", "minecraft:blocks/netherrack", Blocks.NETHERRACK.getDefaultState(), -1);
    public static final StoneType END_STONE = createStone("end_stone", "minecraft:blocks/end_stone", Blocks.END_STONE.getDefaultState(), 1);

    private static StoneType createStone(String name, String texture, IBlockState blockState, int dimension) {
        Preconditions.checkArgument(META + 1 >= 0 && META + 1 <= 16);

        StoneType stoneType = new StoneType(name, META++, new ResourceLocation(texture), blockState, dimension);
        STONES_REGISTRY.put(name, stoneType);

        return stoneType;
    }
}
