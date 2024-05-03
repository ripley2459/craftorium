package fr.cyrilneveu.craftorium.api.item;

import fr.cyrilneveu.craftorium.api.render.FaceProvider;
import fr.cyrilneveu.craftorium.api.render.ICustomModel;
import fr.cyrilneveu.craftorium.api.render.ModelTemplates;
import fr.cyrilneveu.craftorium.api.utils.Utils;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

import static fr.cyrilneveu.craftorium.api.utils.Utils.ITEM_MODEL_BUILDER;

public class CustomItem extends Item implements ICustomModel, IItemColor {
    protected final FaceProvider[] faceProviders;
    @Nullable
    protected final Supplier<List<String>> toolTips;

    public CustomItem(FaceProvider[] faceProviders, @Nullable Supplier<List<String>> toolTips) {
        this.faceProviders = faceProviders;
        this.toolTips = toolTips;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        if (toolTips != null)
            tooltip.addAll(toolTips.get());
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int colorMultiplier(@NotNull ItemStack itemStack, int layer) {
        return faceProviders[layer].getColor();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addTextures(Set<ResourceLocation> textures) {
        for (FaceProvider faceProvider : faceProviders)
            textures.add(faceProvider.getTexture());
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void onModelRegister() {
        ModelResourceLocation location = Utils.getSimpleModelLocation(this);
        ModelBakery.registerItemVariants(this, location);
        ModelLoader.setCustomMeshDefinition(this, stack -> location);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void onModelBake(ModelBakeEvent event) {
        ITEM_MODEL_BUILDER.newOperation(ModelTemplates.ITEM);

        for (FaceProvider face : faceProviders)
            ITEM_MODEL_BUILDER.addLayer(face.getTexture());

        event.getModelRegistry().putObject(Utils.getSimpleModelLocation(this), ITEM_MODEL_BUILDER.build().getModel());
    }
}
