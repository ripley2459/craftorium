package fr.cyrilneveu.craftorium.common.substance;

import fr.cyrilneveu.craftorium.api.substance.Substance;
import fr.cyrilneveu.craftorium.api.substance.object.*;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraftforge.fluids.FluidRegistry;

import static fr.cyrilneveu.craftorium.CraftoriumTags.MODID;
import static fr.cyrilneveu.craftorium.api.Registries.*;
import static fr.cyrilneveu.craftorium.common.inventory.CreativeTabs.SUBSTANCES;
import static fr.cyrilneveu.craftorium.common.inventory.CreativeTabs.TOOLS;

public final class SubstancesObjects {
    public static SubstanceItem CASING;
    public static SubstanceItem DUST;
    public static SubstanceItem FOIL;
    public static SubstanceItem GEAR;
    public static SubstanceItem GEM;
    public static SubstanceItem INGOT;
    public static SubstanceItem NUGGET;
    public static SubstanceItem PEARL;
    public static SubstanceItem PLATE;
    public static SubstanceItem RING;
    public static SubstanceItem ROD;
    public static SubstanceItem ROTOR;
    public static SubstanceItem SCREW;
    public static SubstanceItem SPRING;
    public static SubstanceItem WIRE;

    public static SubstanceTool AXE;
    public static SubstanceTool CUTTER;
    public static SubstanceTool FILE;
    public static SubstanceTool HAMMER;
    public static SubstanceTool HOE;
    public static SubstanceTool KNIFE;
    public static SubstanceTool MORTAR;
    public static SubstanceTool PICKAXE;
    public static SubstanceTool SAW;
    public static SubstanceTool SCREWDRIVER;
    public static SubstanceTool SHOVEL;
    public static SubstanceTool SWORD;
    public static SubstanceTool WRENCH;

    public static SubstanceBlock BLOCK;
    public static SubstanceBlock FRAME;
    public static SubstanceBlock HULL;
    public static SubstanceBlock ORE;

    public static SubstanceFluid LIQUID;

    public static void init() {
        if (SUBSTANCE_ITEMS_REGISTRY.isInitialized() || SUBSTANCE_TOOLS_REGISTRY.isInitialized() || SUBSTANCE_BLOCKS_REGISTRY.isInitialized() || SUBSTANCE_FLUIDS_REGISTRY.isInitialized())
            return;

        SUBSTANCE_ITEMS_REGISTRY.initialize();
        SUBSTANCE_TOOLS_REGISTRY.initialize();
        SUBSTANCE_BLOCKS_REGISTRY.initialize();
        SUBSTANCE_FLUIDS_REGISTRY.initialize();

        CASING = createItem("casing").build();
        DUST = createItem("dust").build();
        FOIL = createItem("foil").build();
        GEAR = createItem("gear").build();
        GEM = createItem("gem").self().build();
        INGOT = createItem("ingot").build();
        NUGGET = createItem("nugget").build();
        PEARL = createItem("pearl").build();
        PLATE = createItem("plate").build();
        RING = createItem("ring").build();
        ROD = createItem("rod").build();
        ROTOR = createItem("rotor").build();
        SCREW = createItem("screw").build();
        SPRING = createItem("spring").build();
        WIRE = createItem("wire").build();

        AXE = createTool("axe").provider(SubstancesObjects::createAxe).build();
        CUTTER = createTool("cutter").build();
        FILE = createTool("file").build();
        HAMMER = createTool("hammer").build();
        HOE = createTool("hoe").provider(SubstancesObjects::createHoe).build();
        KNIFE = createTool("knife").build();
        MORTAR = createTool("mortar").build();
        PICKAXE = createTool("pickaxe").provider(SubstancesObjects::createPickaxe).build();
        SAW = createTool("saw").build();
        SCREWDRIVER = createTool("screwdriver").build();
        SHOVEL = createTool("shovel").provider(SubstancesObjects::createShovel).build();
        SWORD = createTool("sword").provider(SubstancesObjects::createSword).build();
        WRENCH = createTool("wrench").build();

        BLOCK = createBlock("block", SubstancesObjects::createBlock).model(SubstanceBlock::defaultModel).faces(SubstanceBlock::defaultFaces).build();
        FRAME = createBlock("frame", SubstancesObjects::createFrame).model(SubstanceBlock::defaultModel).faces(SubstanceBlock::defaultFaces).build();
        HULL = createBlock("hull", SubstancesObjects::createHull).model(SubstanceBlock::defaultModel).faces(SubstanceBlock::defaultFaces).build();
        ORE = createBlock("ore", SubstancesObjects::createOre).model(SubstanceBlock::oreModel).faces(SubstanceBlock::oreFaces).build();

        LIQUID = createFluid("liquid", SubstancesObjects::createLiquid).faces(SubstanceFluid::defaultFaces).build();
    }

    private static ASubstanceObjectBuilder.SubstanceItemBuilder createItem(String name) {
        ASubstanceObjectBuilder.SubstanceItemBuilder builder = new ASubstanceObjectBuilder.SubstanceItemBuilder(name);
        builder.provider(SubstancesObjects::createItem);
        builder.faces(SubstanceItem::defaultFaces);

        return builder;
    }

    private static ASubstanceObjectBuilder.SubstanceToolBuilder createTool(String name) {
        ASubstanceObjectBuilder.SubstanceToolBuilder builder = new ASubstanceObjectBuilder.SubstanceToolBuilder(name);
        builder.provider(SubstancesObjects::createTool);
        builder.faces(SubstanceTool::defaultFaces);

        return builder;
    }

    private static ASubstanceObjectBuilder.SubstanceBlockBuilder createBlock(String name, ASubstanceObject.ICreateObject provider) {
        ASubstanceObjectBuilder.SubstanceBlockBuilder builder = new ASubstanceObjectBuilder.SubstanceBlockBuilder(name, provider);

        return builder;
    }

    private static ASubstanceObjectBuilder.SubstanceFluidBuilder createFluid(String name, ASubstanceObject.ICreateObject provider) {
        ASubstanceObjectBuilder.SubstanceFluidBuilder builder = new ASubstanceObjectBuilder.SubstanceFluidBuilder(name, provider);

        return builder;
    }

    private static void createItem(ASubstanceObject reference, Substance substance) {
        fr.cyrilneveu.craftorium.api.item.SubstanceItem item = new fr.cyrilneveu.craftorium.api.item.SubstanceItem(reference, substance);
        item.setRegistryName(reference.getName(substance));
        item.setTranslationKey(String.join(".", MODID, reference.getName(null)));
        item.setCreativeTab(SUBSTANCES);

        ITEMS_REGISTRY.put(reference.getName(substance), item);
    }

    private static void createAxe(ASubstanceObject reference, Substance substance) {
        fr.cyrilneveu.craftorium.api.item.SubstanceItem.SubstanceAxe item = new fr.cyrilneveu.craftorium.api.item.SubstanceItem.SubstanceAxe(reference, substance);

        createTool(reference, substance, item);
    }

    private static void createHoe(ASubstanceObject reference, Substance substance) {
        fr.cyrilneveu.craftorium.api.item.SubstanceItem.SubstanceHoe item = new fr.cyrilneveu.craftorium.api.item.SubstanceItem.SubstanceHoe(reference, substance);

        createTool(reference, substance, item);
    }

    private static void createPickaxe(ASubstanceObject reference, Substance substance) {
        fr.cyrilneveu.craftorium.api.item.SubstanceItem.SubstancePickaxe item = new fr.cyrilneveu.craftorium.api.item.SubstanceItem.SubstancePickaxe(reference, substance);

        createTool(reference, substance, item);
    }

    private static void createShovel(ASubstanceObject reference, Substance substance) {
        fr.cyrilneveu.craftorium.api.item.SubstanceItem.SubstanceShovel item = new fr.cyrilneveu.craftorium.api.item.SubstanceItem.SubstanceShovel(reference, substance);

        createTool(reference, substance, item);
    }

    private static void createSword(ASubstanceObject reference, Substance substance) {
        fr.cyrilneveu.craftorium.api.item.SubstanceItem.SubstanceSword item = new fr.cyrilneveu.craftorium.api.item.SubstanceItem.SubstanceSword(reference, substance);

        createTool(reference, substance, item);
    }

    private static void createTool(ASubstanceObject reference, Substance substance) {
        fr.cyrilneveu.craftorium.api.item.SubstanceItem.SubstanceTool item = new fr.cyrilneveu.craftorium.api.item.SubstanceItem.SubstanceTool(reference, substance);

        createTool(reference, substance, item);
    }

    private static void createTool(ASubstanceObject reference, Substance substance, Item item) {
        item.setRegistryName(reference.getName(substance));
        item.setTranslationKey(String.join(".", MODID, reference.getName(null)));
        item.setCreativeTab(TOOLS);

        ITEMS_REGISTRY.put(reference.getName(substance), item);
    }

    private static void createBlock(ASubstanceObject reference, Substance substance) {
        fr.cyrilneveu.craftorium.api.block.SubstanceBlock block = new fr.cyrilneveu.craftorium.api.block.SubstanceBlock(Material.IRON, reference, substance);

        createBlock(reference, substance, block);
    }

    private static void createFrame(ASubstanceObject reference, Substance substance) {
        fr.cyrilneveu.craftorium.api.block.SubstanceBlock block = new fr.cyrilneveu.craftorium.api.block.SubstanceBlock(Material.IRON, reference, substance);

        createBlock(reference, substance, block);
    }

    private static void createHull(ASubstanceObject reference, Substance substance) {
        fr.cyrilneveu.craftorium.api.block.SubstanceBlock block = new fr.cyrilneveu.craftorium.api.block.SubstanceBlock(Material.IRON, reference, substance);

        createBlock(reference, substance, block);
    }

    private static void createOre(ASubstanceObject reference, Substance substance) {
        fr.cyrilneveu.craftorium.api.block.SubstanceBlock.OreBlock block = new fr.cyrilneveu.craftorium.api.block.SubstanceBlock.OreBlock(Material.ROCK, reference, substance);

        createBlock(reference, substance, block);
    }

    private static void createBlock(ASubstanceObject reference, Substance substance, Block block) {
        block.setRegistryName(reference.getName(substance));
        block.setTranslationKey(String.join(".", MODID, reference.getName(null)));
        block.setCreativeTab(SUBSTANCES);

        fr.cyrilneveu.craftorium.api.block.SubstanceBlock.SubstanceItemBlock item = new fr.cyrilneveu.craftorium.api.block.SubstanceBlock.SubstanceItemBlock(block, substance);
        item.setRegistryName(reference.getName(substance));
        item.setTranslationKey(String.join(".", MODID, reference.getName(null)));
        item.setCreativeTab(SUBSTANCES);

        BLOCKS_REGISTRY.put(reference.getName(substance), block);
        ITEMS_REGISTRY.put(reference.getName(substance), item);
    }

    private static void createLiquid(ASubstanceObject reference, Substance substance) {
        fr.cyrilneveu.craftorium.api.fluid.SubstanceFluid fluid = new fr.cyrilneveu.craftorium.api.fluid.SubstanceFluid(reference, substance);
        /*fluid.setGaseous(substance.getComposition().getState() == Element.ElementState.Gas).setLuminosity(15)
                .setDensity(substance.getComposition().getState() == Element.ElementState.Gas ? -100 : 3000)
                .setViscosity(substance.getComposition().getState() == Element.ElementState.Gas ? 200 : 1000)
                .setTemperature((int) substance.getComposition().getTemperature());*/
        fluid.setUnlocalizedName(String.join(".", MODID, reference.getName(null), "name"));

        FLUIDS_REGISTRY.put(reference.getName(substance), fluid);
        FluidRegistry.registerFluid(fluid);
        FluidRegistry.addBucketForFluid(fluid);

        fr.cyrilneveu.craftorium.api.fluid.SubstanceFluid.SubstanceFluidBlock block = new fr.cyrilneveu.craftorium.api.fluid.SubstanceFluid.SubstanceFluidBlock(fluid, Material.LAVA);
        block.setTranslationKey(String.join(".", MODID, reference.getName(null), "name"));
        block.setRegistryName(reference.getName(substance));

        BLOCKS_REGISTRY.put(reference.getName(substance), block);
    }

    public static void close() {
        SUBSTANCE_ITEMS_REGISTRY.close();
        SUBSTANCE_TOOLS_REGISTRY.close();
        SUBSTANCE_BLOCKS_REGISTRY.close();
        SUBSTANCE_FLUIDS_REGISTRY.close();
    }
}
