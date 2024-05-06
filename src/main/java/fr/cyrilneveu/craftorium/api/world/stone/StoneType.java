package fr.cyrilneveu.craftorium.api.world.stone;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public final class StoneType implements Comparable<StoneType> {
    private final String name;
    private final int meta;
    private final ResourceLocation texture;
    private final IBlockState blockState;
    private final int dimension;

    public StoneType(String name, int meta, ResourceLocation texture, IBlockState blockState, int dimension) {
        this.name = name;
        this.meta = meta;
        this.texture = texture;
        this.blockState = blockState;
        this.dimension = dimension;
    }

    public String getName() {
        return name;
    }

    public int getMeta() {
        return meta;
    }

    public ResourceLocation getTexture() {
        return texture;
    }

    public IBlockState getBlockState() {
        return blockState;
    }

    public int getDimension() {
        return dimension;
    }

    @Override
    public int compareTo(@NotNull StoneType other) {
        return name.compareTo(other.getName());
    }
}
