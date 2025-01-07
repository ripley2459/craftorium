package fr.cyrilneveu.craftorium.api.item;

import fr.cyrilneveu.craftorium.api.item.behaviour.FuelBehaviour;
import fr.cyrilneveu.craftorium.api.item.behaviour.ICapableItem;
import fr.cyrilneveu.craftorium.api.item.behaviour.IItemBehaviour;
import fr.cyrilneveu.craftorium.api.property.Aestheticism;
import fr.cyrilneveu.craftorium.api.render.FaceProvider;
import fr.cyrilneveu.craftorium.api.render.ICustomModel;
import fr.cyrilneveu.craftorium.api.render.ModelTemplates;
import fr.cyrilneveu.craftorium.api.utils.CombinedCapabilityProvider;
import fr.cyrilneveu.craftorium.api.utils.RenderUtils;
import fr.cyrilneveu.craftorium.api.utils.Utils;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static fr.cyrilneveu.craftorium.api.utils.RenderUtils.ITEM_MODEL_BUILDER;

public class CustomItem extends Item implements ICustomModel {
    protected final IItemBehaviour[] behaviours;
    protected final Aestheticism.ObjectAestheticism aestheticism;

    public CustomItem(IItemBehaviour[] behaviours, Aestheticism.ObjectAestheticism aestheticism) {
        this.behaviours = behaviours;
        this.aestheticism = aestheticism;
    }

    @Override
    public int getItemBurnTime(ItemStack itemStack) {
        for (IItemBehaviour behaviour : behaviours) {
            if (behaviour instanceof FuelBehaviour fuel && fuel.getDuration() > 0)
                return fuel.getDuration();
        }

        return super.getItemBurnTime(itemStack);
    }

    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable NBTTagCompound nbt) {
        List<ICapabilityProvider> providers = new ArrayList<>();
        for (IItemBehaviour behaviour : behaviours) {
            if (behaviour instanceof ICapableItem capable)
                providers.add(capable.initCapabilities(stack, nbt));
        }

        return new CombinedCapabilityProvider(providers.toArray(new ICapabilityProvider[0]));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        for (IItemBehaviour behaviour : behaviours)
            behaviour.addInformation(stack, worldIn, tooltip, flagIn);
        if (aestheticism.getToolTips() != null)
            aestheticism.getToolTips().get().forEach(t -> tooltip.add(Utils.localise(t)));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack stack) {
        return aestheticism.isGlint();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean showDurabilityBar(ItemStack stack) { // TODO
        return Utils.atLeastOne(behaviours, b -> b.showDurabilityBar(stack)) || super.showDurabilityBar(stack);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public double getDurabilityForDisplay(ItemStack stack) { // TODO
        for (IItemBehaviour behaviour : behaviours) {
            double value = behaviour.getDurabilityForDisplay(stack);
            if (value >= 0d)
                return value;
        }

        return super.getDurabilityForDisplay(stack);
    }

    @Override
    public boolean isDamageable() {
        return Utils.atLeastOne(behaviours, IItemBehaviour::isDamageable) || super.isDamageable();
    }

    @Override
    public boolean hasContainerItem(ItemStack stack) {
        return Utils.atLeastOne(behaviours, b -> b.hasContainerItem(stack)) || super.hasContainerItem(stack);
    }

    @Override
    public ItemStack getContainerItem(ItemStack stack) {
        IItemBehaviour behaviour = Utils.first(behaviours, b -> b.hasContainerItem(stack));
        return behaviour != null ? behaviour.getContainerItem(stack) : super.getContainerItem(stack);
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        return super.getIsRepairable(toRepair, repair);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getItemStackColor(ItemStack stack, int layer) {
        return aestheticism.getFaceProviders()[layer].getColor();
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        return aestheticism.getDisplayName() != null ? aestheticism.getDisplayName().apply(stack) : super.getItemStackDisplayName(stack);
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
        ITEM_MODEL_BUILDER.newOperation(ModelTemplates.ITEM);

        for (FaceProvider face : aestheticism.getFaceProviders())
            ITEM_MODEL_BUILDER.addLayer(face.getTexture());

        event.getModelRegistry().putObject(RenderUtils.getSimpleModelLocation(this), ITEM_MODEL_BUILDER.build().getModel());
    }
}
