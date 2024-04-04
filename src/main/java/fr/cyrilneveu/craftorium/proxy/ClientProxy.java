package fr.cyrilneveu.craftorium.proxy;

import fr.cyrilneveu.craftorium.api.fluid.CustomFluidBlock;
import fr.cyrilneveu.craftorium.api.render.ICustomModel;
import fr.cyrilneveu.craftorium.api.render.RenderUtils;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IThreadListener;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.client.model.ModelFluid;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.HashSet;
import java.util.Set;

@SideOnly(Side.CLIENT)
@Mod.EventBusSubscriber(Side.CLIENT)
public final class ClientProxy extends ACommonProxy {
    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void onTextureStitching(TextureStitchEvent.Pre event) {
        Set<ResourceLocation> sprites = new HashSet<>();

        ITEMS.getAll().values().stream().filter(i -> i instanceof ICustomModel).forEach(i -> ((ICustomModel) i).addTextures(sprites));
        BLOCKS.getAll().values().stream().filter(b -> b instanceof ICustomModel).forEach(b -> ((ICustomModel) b).addTextures(sprites));
        FLUIDS.getAll().values().forEach(f -> {
            event.getMap().registerSprite(f.getStill());
            event.getMap().registerSprite(f.getFlowing());
        });

        sprites.forEach(r -> event.getMap().registerSprite(r));
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void onModelRegister(ModelRegistryEvent event) {
        ITEMS.getAll().values().stream().filter(i -> i instanceof ICustomModel).forEach(i -> ((ICustomModel) i).onModelRegister());
        BLOCKS.getAll().values().stream().filter(b -> b instanceof ICustomModel).forEach(b -> ((ICustomModel) b).onModelRegister());
        FLUIDS.getAll().values().forEach(f -> {
            Block block = f.getBlock();
            if (block instanceof CustomFluidBlock)
                ModelLoader.setCustomStateMapper(block, RenderUtils.SIMPLE_STATE_MAPPER.apply(block));
        });
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void onModelBake(ModelBakeEvent event) {
        ITEMS.getAll().values().stream().filter(i -> i instanceof ICustomModel).forEach(i -> ((ICustomModel) i).onModelBake(event));
        BLOCKS.getAll().values().stream().filter(b -> b instanceof ICustomModel).forEach(b -> ((ICustomModel) b).onModelBake(event));
        FLUIDS.getAll().values().forEach(f -> {
            Block block = f.getBlock();
            if (block instanceof CustomFluidBlock) {
                ModelFluid modelFluid = new ModelFluid(f);
                IBakedModel bakedModel = modelFluid.bake(modelFluid.getDefaultState(), DefaultVertexFormats.ITEM, RenderUtils::getTexture);
                event.getModelRegistry().putObject(RenderUtils.getSimpleModelLocation(block), bakedModel);
            }
        });
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void onHandlingItemColors(ColorHandlerEvent.Item event) {
        ITEMS.getAll().values().stream().filter(i -> i instanceof IItemColor).forEach(i -> event.getItemColors().registerItemColorHandler((IItemColor) i, i));
        BLOCKS.getAll().values().stream().filter(b -> b instanceof IItemColor).forEach(b -> event.getItemColors().registerItemColorHandler((IItemColor) b, b));
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void onHandlingBlockColors(ColorHandlerEvent.Block event) {
        BLOCKS.getAll().values().stream().filter(b -> b instanceof IBlockColor).forEach(b -> event.getBlockColors().registerBlockColorHandler((IBlockColor) b, b));
        FLUIDS.getAll().values().stream().filter(f -> f instanceof IBlockColor).forEach(f -> event.getBlockColors().registerBlockColorHandler((IBlockColor) f.getBlock(), f.getBlock()));
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
