package fr.cyrilneveu.craftorium.common;

import crafttweaker.CraftTweakerAPI;
import fr.cyrilneveu.craftorium.Craftorium;
import fr.cyrilneveu.craftorium.api.config.Settings;
import fr.cyrilneveu.craftorium.api.inventory.GuiHandler;
import fr.cyrilneveu.craftorium.api.item.ItemBuilder;
import fr.cyrilneveu.craftorium.api.machine.MachineTile;
import fr.cyrilneveu.craftorium.api.substance.Substance;
import fr.cyrilneveu.craftorium.api.substance.object.SubstanceBlock;
import fr.cyrilneveu.craftorium.api.world.VeinGenerator;
import fr.cyrilneveu.craftorium.common.machine.Machines;
import fr.cyrilneveu.craftorium.common.recipe.RecipesHandler;
import fr.cyrilneveu.craftorium.common.substance.Substances;
import fr.cyrilneveu.craftorium.common.substance.SubstancesObjects;
import fr.cyrilneveu.craftorium.common.tier.Tiers;
import fr.cyrilneveu.craftorium.common.tier.TiersObjects;
import fr.cyrilneveu.craftorium.common.world.Veins;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IThreadListener;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

import javax.annotation.Nullable;

import static fr.cyrilneveu.craftorium.CraftoriumTags.MODID;
import static fr.cyrilneveu.craftorium.api.Registries.*;
import static net.minecraftforge.oredict.OreDictionary.WILDCARD_VALUE;

@Mod.EventBusSubscriber
public abstract class ACommonProxy {
    @SubscribeEvent
    public static void syncConfigValues(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.getModID().equals(MODID))
            ConfigManager.sync(MODID, Config.Type.INSTANCE);
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    protected static void registerItems(RegistryEvent.Register<Item> event) {
        new ItemBuilder("redstone_capacitor").addTexture("circuits/redstone_capacitor").build();
        new ItemBuilder("capacitor").addTexture("circuits/capacitor").build();
        new ItemBuilder("redstone_chip").addTexture("circuits/redstone_chip").build();
        new ItemBuilder("chip").addTexture("circuits/chip").build();
        new ItemBuilder("redstone_diode").addTexture("circuits/redstone_diode").build();
        new ItemBuilder("diode").addTexture("circuits/diode").build();
        new ItemBuilder("redstone_resistor").addTexture("circuits/redstone_resistor").build();
        new ItemBuilder("resistor").addTexture("circuits/resistor").build();
        new ItemBuilder("transistor").addTexture("circuits/transistor").build();
        new ItemBuilder("circuit_board_redstone").addTexture("circuits/circuit_board_redstone").build();
        new ItemBuilder("circuit_board_1").addTexture("circuits/circuit_board_1").build();
        new ItemBuilder("circuit_board_2").addTexture("circuits/circuit_board_2").build();
        new ItemBuilder("circuit_board_3").addTexture("circuits/circuit_board_3").build();

        new ItemBuilder("redstone_circuit").addTexture("circuits/redstone_circuit").build();
        new ItemBuilder("advanced_redstone_circuit").addTexture("circuits/advanced_redstone_circuit").build();
        new ItemBuilder("primitive_circuit").addTexture("circuits/primitive_circuit").build();
        new ItemBuilder("advanced_circuit").addTexture("circuits/advanced_circuit").build();
        new ItemBuilder("maxed_circuit").addTexture("circuits/maxed_circuit").build();
        new ItemBuilder("microprocessor").addTexture("circuits/microprocessor").build();
        new ItemBuilder("processor").addTexture("circuits/processor").build();
        new ItemBuilder("advanced_processor").addTexture("circuits/advanced_processor").build();
        new ItemBuilder("mainframe").addTexture("circuits/mainframe").build();
        new ItemBuilder("quantum_microprocessor").addTexture("circuits/quantum_microprocessor").build();
        new ItemBuilder("quantum_processor").addTexture("circuits/quantum_processor").build();
        new ItemBuilder("wetware_microprocessor").addTexture("circuits/wetware_microprocessor").build();
        new ItemBuilder("wetware_processor").addTexture("circuits/wetware_processor").build();
        new ItemBuilder("wetware_mainframe").addTexture("circuits/wetware_mainframe").build();
        new ItemBuilder("machine_spirit_infused_processor").addTexture("circuits/machine_spirit_infused_processor").build();

        TIER_ITEMS_REGISTRY.getAll().forEach((k, v) -> TIERS_REGISTRY.getAll().values().stream().filter(t -> t.getItems().contains(v)).forEach(v::createObject));

        SUBSTANCE_ITEMS_REGISTRY.getAll().forEach((k, v) -> SUBSTANCES_REGISTRY.getAll().values().stream().filter(s -> s.getItems().contains(v) && s.shouldRegister(v)).forEach(v::createObject));
        SUBSTANCE_TOOLS_REGISTRY.getAll().forEach((k, v) -> SUBSTANCES_REGISTRY.getAll().values().stream().filter(s -> s.getTools().contains(v) && s.shouldRegister(v)).forEach(v::createObject));

        ITEMS_REGISTRY.close();
        ITEMS_REGISTRY.getAll().forEach((k, v) -> event.getRegistry().register(v));
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    protected static void registerBlocks(RegistryEvent.Register<Block> event) {
        SUBSTANCE_BLOCKS_REGISTRY.getAll().forEach((k, v) -> SUBSTANCES_REGISTRY.getAll().values().stream().filter(s -> s.getBlocks().contains(v) && s.shouldRegister(v)).forEach(v::createObject));
        SUBSTANCE_FLUIDS_REGISTRY.getAll().forEach((k, v) -> SUBSTANCES_REGISTRY.getAll().values().stream().filter(s -> s.getFluids().contains(v) && s.shouldRegister(v)).forEach(v::createObject));

        createTile(MachineTile.class, "basic_machine");
        MACHINES_REGISTRY.getAll().forEach((k, v) -> TIERS_REGISTRY.getAll().values().stream().filter(t -> t.getMachines().contains(v)).forEach(t -> Machines.createMachine(v, t)));

        FLUIDS_REGISTRY.close();
        BLOCKS_REGISTRY.close();
        BLOCKS_REGISTRY.getAll().forEach((s, b) -> event.getRegistry().register(b));
    }

    public static void createTile(Class<? extends TileEntity> clazz, String name) {
        GameRegistry.registerTileEntity(clazz, new ResourceLocation(MODID, name));
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

    @SubscribeEvent
    public static void modifyFuelDuration(FurnaceFuelBurnTimeEvent event) {
        ItemStack stack = event.getItemStack();
        Block block = Block.getBlockFromItem(stack.getItem());
        if (block instanceof SubstanceBlock substanceBlock) {
            int duration = substanceBlock.getBlockBurnTime();
            if (duration > 0)
                event.setBurnTime(duration);
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
        Veins.init();
        TiersObjects.init();
        Machines.init();
        Tiers.init();

        if (Loader.isModLoaded("crafttweaker"))
            CraftTweakerAPI.tweaker.loadScript(false, MODID);

        SubstancesObjects.close();
        Substances.close();
        Veins.close();
        Machines.close();
        Tiers.close();
    }

    public void init(FMLInitializationEvent event) {
        NetworkRegistry.INSTANCE.registerGuiHandler(Craftorium.instance, new GuiHandler());

        if (!Settings.generationSettings.enableVanillaOreGeneration)
            MinecraftForge.ORE_GEN_BUS.register(new VeinGenerator.WorldGeneratorReplacer());
        if (Settings.generationSettings.enableOreGeneration)
            GameRegistry.registerWorldGenerator(new VeinGenerator(), 1);
    }

    public void postInit(FMLPostInitializationEvent event) {

    }
}
