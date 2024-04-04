package fr.cyrilneveu.craftorium.common;

import crafttweaker.CraftTweakerAPI;
import fr.cyrilneveu.craftorium.CraftoriumTags;
import fr.cyrilneveu.craftorium.api.block.BlockBuilder;
import fr.cyrilneveu.craftorium.api.fluid.FluidBuilder;
import fr.cyrilneveu.craftorium.api.item.ItemBuilder;
import fr.cyrilneveu.craftorium.api.utils.Registry;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.IThreadListener;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

@Mod.EventBusSubscriber
public abstract class ACommonProxy {
    public static final Registry<String, Item> ITEMS = new Registry<>();
    public static final Registry<String, Block> BLOCKS = new Registry<>();
    public static final Registry<String, Fluid> FLUIDS = new Registry<>();

    @SubscribeEvent
    public static void syncConfigValues(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.getModID().equals(CraftoriumTags.MODID))
            ConfigManager.sync(CraftoriumTags.MODID, Config.Type.INSTANCE);
    }

    private static ItemBuilder createItem(String name) {
        return new ItemBuilder(name);
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    protected static void registerItems(RegistryEvent.Register<Item> event) {

        ITEMS.close();
        ITEMS.getAll().forEach((k, v) -> event.getRegistry().register(v));
    }

    private static BlockBuilder createBlock(String name) {
        return new BlockBuilder(name);
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    protected static void registerBlocks(RegistryEvent.Register<Block> event) {

        registerFluids(event);

        BLOCKS.close();
        BLOCKS.getAll().forEach((s, b) -> event.getRegistry().register(b));
    }

    private static FluidBuilder createFluid(String name) {
        return new FluidBuilder(name);
    }

    protected static void registerFluids(RegistryEvent.Register<Block> event) {

        FLUIDS.close();
        // FLUIDS.getAll().forEach((s, f) -> { /* *** */ }); // Fluids are registered when they are created to also register their associated block.
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    protected static void onRegisterRecipes(RegistryEvent.Register<IRecipe> event) {
        registerOres();
    }

    protected static void registerOres() {

    }

    public EntityPlayer getPlayer(MessageContext context) {
        return context.getServerHandler().player;
    }

    public IThreadListener getThread(MessageContext context) {
        return context.getServerHandler().player.server;
    }

    public void preInit(FMLPreInitializationEvent event) {
        if (Loader.isModLoaded("crafttweaker"))
            CraftTweakerAPI.tweaker.loadScript(false, CraftoriumTags.MODID);
    }

    public void init(FMLInitializationEvent event) {

    }

    public void postInit(FMLPostInitializationEvent event) {

    }
}
