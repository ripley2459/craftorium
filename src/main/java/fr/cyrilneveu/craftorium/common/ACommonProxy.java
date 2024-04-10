package fr.cyrilneveu.craftorium.common;

import crafttweaker.CraftTweakerAPI;
import fr.cyrilneveu.craftorium.CraftoriumTags;
import fr.cyrilneveu.craftorium.api.block.BlockBuilder;
import fr.cyrilneveu.craftorium.api.fluid.FluidBuilder;
import fr.cyrilneveu.craftorium.api.item.ItemBuilder;
import fr.cyrilneveu.craftorium.api.substance.Substance;
import fr.cyrilneveu.craftorium.api.substance.object.SubstanceFluid;
import fr.cyrilneveu.craftorium.api.utils.Registry;
import fr.cyrilneveu.craftorium.api.world.VeinGenerator;
import fr.cyrilneveu.craftorium.common.config.Settings;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.IThreadListener;
import net.minecraftforge.common.MinecraftForge;
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
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

import javax.annotation.Nullable;

import static fr.cyrilneveu.craftorium.common.substance.Substances.SUBSTANCES_REGISTRY;
import static fr.cyrilneveu.craftorium.common.substance.SubstancesObjects.*;
import static fr.cyrilneveu.craftorium.common.world.Veins.VEINS_REGISTRY;
import static net.minecraftforge.oredict.OreDictionary.WILDCARD_VALUE;

@Mod.EventBusSubscriber
public abstract class ACommonProxy {
    public static final Registry<String, Item> ITEMS_REGISTRY = new Registry<>();
    public static final Registry<String, Block> BLOCKS_REGISTRY = new Registry<>();
    public static final Registry<String, Fluid> FLUIDS_REGISTRY = new Registry<>();

    @SubscribeEvent
    public static void syncConfigValues(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.getModID().equals(CraftoriumTags.MODID))
            ConfigManager.sync(CraftoriumTags.MODID, Config.Type.INSTANCE);
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    protected static void registerItems(RegistryEvent.Register<Item> event) {
        SUBSTANCE_ITEMS_REGISTRY.getAll().forEach((k, v) -> SUBSTANCES_REGISTRY.getAll().values().stream().filter(s -> s.getItems().contains(v)).forEach(v::createObject));
        SUBSTANCE_TOOLS_REGISTRY.getAll().forEach((k, v) -> SUBSTANCES_REGISTRY.getAll().values().stream().filter(s -> s.getTools().contains(v)).forEach(v::createObject));

        ITEMS_REGISTRY.close();
        ITEMS_REGISTRY.getAll().forEach((k, v) -> event.getRegistry().register(v));
    }

    protected static ItemBuilder createItem(String name) {
        return new ItemBuilder(name);
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    protected static void registerBlocks(RegistryEvent.Register<Block> event) {
        SUBSTANCE_BLOCKS_REGISTRY.getAll().forEach((k, v) -> SUBSTANCES_REGISTRY.getAll().values().stream().filter(s -> s.getBlocks().contains(v)).forEach(v::createObject));
        SUBSTANCE_FLUIDS_REGISTRY.getAll().forEach((k, v) -> SUBSTANCES_REGISTRY.getAll().values().stream().filter(s -> s.getFluids().contains(v)).forEach(v::createObject));

        FLUIDS_REGISTRY.close();
        BLOCKS_REGISTRY.close();
        BLOCKS_REGISTRY.getAll().forEach((s, b) -> event.getRegistry().register(b));
    }

    protected static BlockBuilder createBlock(String name) {
        return new BlockBuilder(name);
    }

    protected static FluidBuilder createFluid(String name) {
        return new FluidBuilder(name);
    }

    protected static void createFluid(SubstanceFluid reference, Substance substance) {

    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    protected static void onRegisterRecipes(RegistryEvent.Register<IRecipe> event) {
        registerOres();
    }

    protected static void registerOres() {
        SUBSTANCES_REGISTRY.getAll().values().forEach(s -> {
            s.getItems().forEach(i -> OreDictionary.registerOre(i.getOre(s), i.getItemStack(s)));
            s.getTools().forEach(t -> OreDictionary.registerOre(t.getOre(s), t.getItemStack(s)));
            s.getBlocks().forEach(b -> OreDictionary.registerOre(b.getOre(s), b.getItemStack(s)));
        });
    }

    @Nullable
    public static Item getItem(String name) {
        return ITEMS_REGISTRY.get(name);
    }

    @Nullable
    public static ItemStack getItemStack(String name) {
        return getItemStack(name, 1);
    }

    @Nullable
    public static ItemStack getItemStack(String name, int count) {
        Item item = getItem(name);
        return item == null ? null : new ItemStack(item, count, WILDCARD_VALUE);
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

        SUBSTANCE_ITEMS_REGISTRY.close();
        SUBSTANCE_TOOLS_REGISTRY.close();
        SUBSTANCE_BLOCKS_REGISTRY.close();
        SUBSTANCE_FLUIDS_REGISTRY.close();
        VEINS_REGISTRY.close();
        SUBSTANCES_REGISTRY.close();
    }

    public void init(FMLInitializationEvent event) {
        if (!Settings.generationSettings.enableVanillaOreGeneration)
            MinecraftForge.ORE_GEN_BUS.register(new VeinGenerator.WorldGeneratorReplacer());
        if (Settings.generationSettings.enableOreGeneration)
            GameRegistry.registerWorldGenerator(new VeinGenerator(), 1);
    }

    public void postInit(FMLPostInitializationEvent event) {

    }
}
