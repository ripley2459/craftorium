package fr.cyrilneveu.craftorium.api.block;

import com.google.common.base.Preconditions;
import fr.cyrilneveu.craftorium.api.render.FaceProvider;
import fr.cyrilneveu.craftorium.api.render.ICustomModel;
import fr.cyrilneveu.craftorium.api.render.ModelTemplate;
import fr.cyrilneveu.craftorium.api.render.RenderUtils;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.model.ModelLoader;
import org.jetbrains.annotations.Nullable;

import java.util.Set;

import static fr.cyrilneveu.craftorium.api.render.RenderUtils.BLOCK_MODEL_BUILDER;

public class CustomBlock extends Block implements ICustomModel, IBlockColor {
    private final FaceProvider[] faceProviders;

    public CustomBlock(Material material, FaceProvider[] faceProviders) {
        super(material);
        this.faceProviders = faceProviders;
    }

    @Override
    public int colorMultiplier(IBlockState state, @Nullable IBlockAccess worldIn, @Nullable BlockPos pos, int layer) {
        return faceProviders[layer].getColor();
    }

    @Override
    public void addTextures(Set<ResourceLocation> textures) {
        for (FaceProvider faceProvider : faceProviders)
            textures.add(faceProvider.getTexture());
    }

    @Override
    public void onModelRegister() {
        ModelLoader.setCustomStateMapper(this, RenderUtils.SIMPLE_STATE_MAPPER.apply(this));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, RenderUtils.getSimpleModelLocation(this));
    }

    @Override
    public void onModelBake(ModelBakeEvent event) {
        Preconditions.checkArgument(faceProviders.length == 1, "This kind of block use exactly 1 texture!");

        BLOCK_MODEL_BUILDER.newOperation(ModelTemplate.BLOCK);

        for (FaceProvider face : faceProviders)
            BLOCK_MODEL_BUILDER.addLayer(face.getTexture());

        event.getModelRegistry().putObject(RenderUtils.getSimpleModelLocation(this), BLOCK_MODEL_BUILDER.build().getModel());
    }
}
