package fr.cyrilneveu.craftorium.common;

import crafttweaker.CraftTweakerAPI;
import fr.cyrilneveu.craftorium.CraftoriumTags;
import fr.cyrilneveu.craftorium.api.block.BlockBuilder;
import fr.cyrilneveu.craftorium.api.fluid.FluidBuilder;
import fr.cyrilneveu.craftorium.api.item.ItemBuilder;
import fr.cyrilneveu.craftorium.api.substance.Substance;
import fr.cyrilneveu.craftorium.api.world.VeinGenerator;
import fr.cyrilneveu.craftorium.common.config.Settings;
import fr.cyrilneveu.craftorium.common.recipe.RecipesHandler;
import fr.cyrilneveu.craftorium.common.substance.Substances;
import fr.cyrilneveu.craftorium.common.substance.SubstancesObjects;
import fr.cyrilneveu.craftorium.common.tier.Tiers;
import fr.cyrilneveu.craftorium.common.world.Veins;
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

import static fr.cyrilneveu.craftorium.api.Registries.*;
import static fr.cyrilneveu.craftorium.common.tier.TiersObjects.TIER_ITEMS_REGISTRY;
import static net.minecraftforge.oredict.OreDictionary.WILDCARD_VALUE;

@Mod.EventBusSubscriber
public abstract class ACommonProxy {
    @SubscribeEvent
    public static void syncConfigValues(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.getModID().equals(CraftoriumTags.MODID))
            ConfigManager.sync(CraftoriumTags.MODID, Config.Type.INSTANCE);
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    protected static void registerItems(RegistryEvent.Register<Item> event) {
        createItem("redstone_capacitor").addTexture("circuits/redstone_capacitor").build();
        createItem("redstone_chip").addTexture("circuits/redstone_chip").build();
        createItem("diode").addTexture("circuits/diode").build();
        createItem("vacuum_tube").addTexture("circuits/vacuum_tube").build();
        createItem("transistor").addTexture("circuits/transistor").build();
        createItem("resistor").addTexture("circuits/resistor").build();
        createItem("capacitor").addTexture("circuits/capacitor").build();
        createItem("chip").addTexture("circuits/chip").build();
        createItem("circuit_board_redstone").addTexture("circuits/circuit_board_redstone").build();
        createItem("circuit_board_1").addTexture("circuits/circuit_board_1").build();
        createItem("circuit_board_2").addTexture("circuits/circuit_board_2").build();
        createItem("circuit_board_3").addTexture("circuits/circuit_board_3").build();

        createItem("redstone_circuit").addTexture("circuits/redstone_circuit").build();
        createItem("advanced_redstone_circuit").addTexture("circuits/advanced_redstone_circuit").build();
        createItem("primitive_circuit").addTexture("circuits/primitive_circuit").build();
        createItem("advanced_circuit").addTexture("circuits/advanced_circuit").build();
        createItem("maxed_circuit").addTexture("circuits/maxed_circuit").build();
        createItem("microprocessor").addTexture("circuits/microprocessor").build();
        createItem("processor").addTexture("circuits/processor").build();
        createItem("advanced_processor").addTexture("circuits/advanced_processor").build();
        createItem("mainframe").addTexture("circuits/mainframe").build();
        createItem("quantum_microprocessor").addTexture("circuits/quantum_microprocessor").build();
        createItem("quantum_processor").addTexture("circuits/quantum_processor").build();
        createItem("wetware_microprocessor").addTexture("circuits/wetware_microprocessor").build();
        createItem("wetware_processor").addTexture("circuits/wetware_processor").build();
        createItem("wetware_mainframe").addTexture("circuits/wetware_mainframe").build();
        createItem("machine_spirit_infused_processor").addTexture("circuits/machine_spirit_infused_processor").build();

        TIER_ITEMS_REGISTRY.getAll().forEach((k, v) -> TIERS_REGISTRY.getAll().values().stream().filter(s -> s.getItems().contains(v)).forEach(v::createObject));

        SUBSTANCE_ITEMS_REGISTRY.getAll().forEach((k, v) -> SUBSTANCES_REGISTRY.getAll().values().stream().filter(s -> s.getItems().contains(v) && s.shouldRegister(v)).forEach(v::createObject));
        SUBSTANCE_TOOLS_REGISTRY.getAll().forEach((k, v) -> SUBSTANCES_REGISTRY.getAll().values().stream().filter(s -> s.getTools().contains(v) && s.shouldRegister(v)).forEach(v::createObject));

        ITEMS_REGISTRY.close();
        ITEMS_REGISTRY.getAll().forEach((k, v) -> event.getRegistry().register(v));
    }

    protected static ItemBuilder createItem(String name) {
        return new ItemBuilder(name);
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    protected static void registerBlocks(RegistryEvent.Register<Block> event) {
        SUBSTANCE_BLOCKS_REGISTRY.getAll().forEach((k, v) -> SUBSTANCES_REGISTRY.getAll().values().stream().filter(s -> s.getBlocks().contains(v) && s.shouldRegister(v)).forEach(v::createObject));
        SUBSTANCE_FLUIDS_REGISTRY.getAll().forEach((k, v) -> SUBSTANCES_REGISTRY.getAll().values().stream().filter(s -> s.getFluids().contains(v) && s.shouldRegister(v)).forEach(v::createObject));

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

    @SubscribeEvent(priority = EventPriority.LOWEST)
    protected static void onRegisterRecipes(RegistryEvent.Register<IRecipe> event) {
        registerOres();

        RecipesHandler.unregisterRecipes(event);
        RecipesHandler.registerRecipes(event);

        SUBSTANCES_REGISTRY.getAll().values().forEach(s -> s.getProcess().registerRecipes(s));
    }

    protected static void registerOres() {
        OreDictionary.registerOre("circuitTier0", getItem("redstone_circuit"));
        OreDictionary.registerOre("circuitTier1", getItem("advanced_redstone_circuit"));
        OreDictionary.registerOre("circuitTier1", getItem("primitive_circuit"));
        OreDictionary.registerOre("circuitTier2", getItem("advanced_circuit"));
        OreDictionary.registerOre("circuitTier2", getItem("microprocessor"));
        OreDictionary.registerOre("circuitTier3", getItem("maxed_circuit"));
        OreDictionary.registerOre("circuitTier3", getItem("processor"));
        OreDictionary.registerOre("circuitTier3", getItem("quantum_microprocessor"));
        OreDictionary.registerOre("circuitTier3", getItem("wetware_microprocessor"));
        OreDictionary.registerOre("circuitTier4", getItem("advanced_processor"));
        OreDictionary.registerOre("circuitTier4", getItem("quantum_processor"));
        OreDictionary.registerOre("circuitTier4", getItem("wetware_processor"));
        OreDictionary.registerOre("circuitTier5", getItem("mainframe"));
        OreDictionary.registerOre("circuitTier5", getItem("wetware_mainframe"));
        OreDictionary.registerOre("circuitTier6", getItem("machine_spirit_infused_processor"));

        TIERS_REGISTRY.getAll().values().forEach(tier -> tier.getItems().forEach(i -> OreDictionary.registerOre(i.getOre(tier), i.asItemStack(tier))));

        for (Substance substance : SUBSTANCES_REGISTRY.getAll().values()) {
            substance.getItems().forEach(i -> OreDictionary.registerOre(i.getOre(substance), i.asItemStack(substance)));
            substance.getTools().forEach(t -> OreDictionary.registerOre(t.getOre(substance), t.asItemStack(substance)));
            substance.getBlocks().forEach(b -> OreDictionary.registerOre(b.getOre(substance), b.asItemStack(substance)));
        }
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
        SubstancesObjects.init();
        Substances.init();

        if (Loader.isModLoaded("crafttweaker"))
            CraftTweakerAPI.tweaker.loadScript(false, CraftoriumTags.MODID);

        SubstancesObjects.close();
        Substances.close();
        Veins.close();
        Tiers.close();
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
