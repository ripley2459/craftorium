package fr.cyrilneveu.craftorium.client;

import fr.cyrilneveu.craftorium.api.block.CustomBlock;
import fr.cyrilneveu.craftorium.api.fluid.CustomFluid;
import fr.cyrilneveu.craftorium.api.render.ICustomModel;
import fr.cyrilneveu.craftorium.api.substance.Substance;
import fr.cyrilneveu.craftorium.api.utils.RenderUtils;
import fr.cyrilneveu.craftorium.common.ACommonProxy;
import fr.cyrilneveu.craftorium.common.substance.SubstancesObjects;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IThreadListener;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.client.model.ModelFluid;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static fr.cyrilneveu.craftorium.api.Registries.*;
import static fr.cyrilneveu.craftorium.common.substance.SubstancesObjects.LIQUID;

@SideOnly(Side.CLIENT)
@Mod.EventBusSubscriber(Side.CLIENT)
public final class ClientProxy extends ACommonProxy {
    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void onTextureStitching(TextureStitchEvent.Pre event) {
        Set<ResourceLocation> sprites = new HashSet<>();

        ITEMS_REGISTRY.getAll().values().stream().filter(i -> i instanceof ICustomModel).forEach(i -> ((ICustomModel) i).addTextures(sprites));
        BLOCKS_REGISTRY.getAll().values().stream().filter(b -> b instanceof ICustomModel).forEach(b -> ((ICustomModel) b).addTextures(sprites));
        for (Fluid fluid : FLUIDS_REGISTRY.getAll().values()) {
            event.getMap().registerSprite(fluid.getStill());
            event.getMap().registerSprite(fluid.getFlowing());
        }

        sprites.forEach(r -> event.getMap().registerSprite(r));
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void onModelRegister(ModelRegistryEvent event) {
        ITEMS_REGISTRY.getAll().values().stream().filter(i -> i instanceof ICustomModel).forEach(i -> ((ICustomModel) i).onModelRegister());
        BLOCKS_REGISTRY.getAll().values().stream().filter(b -> b instanceof ICustomModel).forEach(b -> ((ICustomModel) b).onModelRegister());
        for (Fluid fluid : FLUIDS_REGISTRY.getAll().values()) {
            Block block = fluid.getBlock();
            if (block instanceof CustomFluid.CustomFluidBlock)
                ModelLoader.setCustomStateMapper(block, RenderUtils.SIMPLE_STATE_MAPPER.apply(block));
        }
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void onModelBake(ModelBakeEvent event) {
        ITEMS_REGISTRY.getAll().values().stream().filter(i -> i instanceof ICustomModel).forEach(i -> ((ICustomModel) i).onModelBake(event));
        BLOCKS_REGISTRY.getAll().values().stream().filter(b -> b instanceof ICustomModel).forEach(b -> ((ICustomModel) b).onModelBake(event));
        for (Fluid fluid : FLUIDS_REGISTRY.getAll().values()) {
            Block block = fluid.getBlock();
            if (block instanceof CustomFluid.CustomFluidBlock) {
                ModelFluid modelFluid = new ModelFluid(fluid);
                IBakedModel bakedModel = modelFluid.bake(modelFluid.getDefaultState(), DefaultVertexFormats.ITEM, RenderUtils::getTexture);
                event.getModelRegistry().putObject(RenderUtils.getSimpleModelLocation(block), bakedModel);
            }
        }
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void onHandlingItemColors(ColorHandlerEvent.Item event) {
        ITEMS_REGISTRY.getAll().values().stream().filter(i -> i instanceof ICustomModel).forEach(i -> event.getItemColors().registerItemColorHandler((s, l) -> ((ICustomModel) i).getItemStackColor(s, l), i));
        BLOCKS_REGISTRY.getAll().values().stream().filter(b -> b instanceof ICustomModel).forEach(b -> event.getItemColors().registerItemColorHandler((s, l) -> ((ICustomModel) b).getItemStackColor(s, l), b));
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void onHandlingBlockColors(ColorHandlerEvent.Block event) {
        BLOCKS_REGISTRY.getAll().values().stream().filter(b -> b instanceof ICustomModel).forEach(b -> event.getBlockColors().registerBlockColorHandler((s, w, p, l) -> ((CustomBlock) b).getBlockColor(s, w, p, l), b));
        FLUIDS_REGISTRY.getAll().values().stream().filter(f -> f instanceof ICustomModel).forEach(f -> event.getBlockColors().registerBlockColorHandler((IBlockColor) f.getBlock(), f.getBlock()));
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void onItemTooltip(@Nonnull ItemTooltipEvent event) {
        ItemStack itemStack = event.getItemStack();

        if (itemStack.hasTagCompound()) {
            List<String> tooltips = new ArrayList<>();
            if (itemStack.getTagCompound().hasKey("FluidName")) {
                Substance substance = SUBSTANCES_REGISTRY.get(itemStack.getTagCompound().getString("FluidName"));
                if (substance != null)
                    tooltips.addAll(SubstancesObjects.fluidTooltips(LIQUID, substance));
            }

            for (String s : tooltips) {
                if (s == null || s.isEmpty())
                    continue;
                event.getToolTip().add(s);
            }
        }
    }



    @Override
    public EntityPlayer getPlayer(MessageContext context) {
        return context.side.isClient() ? Minecraft.getMinecraft().player : super.getPlayer(context);
    }

    @Override
    public IThreadListener getThread(MessageContext context) {
        return context.side.isClient() ? Minecraft.getMinecraft() : super.getThread(context);
    }

    @Override
    public void construct(FMLConstructionEvent event) {
        super.construct(event);
    }

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
    }
}
