package fr.cyrilneveu.craftorium.api.render;

import com.google.common.collect.ImmutableMap;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ModelBlock;
import net.minecraft.client.renderer.block.model.ModelRotation;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.common.model.IModelState;
import net.minecraftforge.common.model.TRSRTransformation;
import net.minecraftforge.common.model.animation.IClip;

import java.util.Collection;
import java.util.Optional;
import java.util.function.Function;

public final class RotateModel implements IModel {
    private final IModel model;
    private final ModelRotation rotation;

    public RotateModel(IModel model, ModelRotation rotation) {
        this.model = model;
        this.rotation = rotation;
    }

    @Override
    public IBakedModel bake(IModelState state, VertexFormat format, Function<ResourceLocation, TextureAtlasSprite> bakedTextureGetter) {
        return model.bake(TRSRTransformation.from(this.rotation), format, bakedTextureGetter);
    }

    @Override
    public Collection<ResourceLocation> getDependencies() {
        return model.getDependencies();
    }

    @Override
    public Collection<ResourceLocation> getTextures() {
        return model.getTextures();
    }

    @Override
    public IModelState getDefaultState() {
        return model.getDefaultState();
    }

    @Override
    public Optional<? extends IClip> getClip(String name) {
        return model.getClip(name);
    }

    @Override
    public IModel process(ImmutableMap<String, String> customData) {
        return model.process(customData);
    }

    @Override
    public IModel smoothLighting(boolean value) {
        return model.smoothLighting(value);
    }

    @Override
    public IModel gui3d(boolean value) {
        return model.gui3d(value);
    }

    @Override
    public IModel uvlock(boolean value) {
        return model.uvlock(value);
    }

    @Override
    public IModel retexture(ImmutableMap<String, String> textures) {
        return model.retexture(textures);
    }

    @Override
    public Optional<ModelBlock> asVanillaModel() {
        return model.asVanillaModel();
    }
}