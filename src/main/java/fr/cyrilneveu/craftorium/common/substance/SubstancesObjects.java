package fr.cyrilneveu.craftorium.common.substance;

import fr.cyrilneveu.craftorium.api.block.CustomBlock;
import fr.cyrilneveu.craftorium.api.block.SubstanceItemBlock;
import fr.cyrilneveu.craftorium.api.substance.Substance;
import fr.cyrilneveu.craftorium.api.substance.object.*;
import fr.cyrilneveu.craftorium.api.utils.Registry;

import static fr.cyrilneveu.craftorium.CraftoriumTags.MODID;
import static fr.cyrilneveu.craftorium.common.ACommonProxy.BLOCKS_REGISTRY;
import static fr.cyrilneveu.craftorium.common.ACommonProxy.ITEMS_REGISTRY;
import static fr.cyrilneveu.craftorium.common.inventory.CreativeTabs.SUBSTANCES;
import static fr.cyrilneveu.craftorium.common.inventory.CreativeTabs.TOOLS;

public final class SubstancesObjects {
    public static final Registry<String, SubstanceItem> SUBSTANCE_ITEMS_REGISTRY = new Registry<>();
    public static final Registry<String, SubstanceTool> SUBSTANCE_TOOLS_REGISTRY = new Registry<>();
    public static final Registry<String, SubstanceBlock> SUBSTANCE_BLOCKS_REGISTRY = new Registry<>();
    public static final Registry<String, SubstanceFluid> SUBSTANCE_FLUIDS_REGISTRY = new Registry<>();

    public static final SubstanceItem INGOT = createItem("ingot").prefix("test").build();
    public static final SubstanceItem NUGGET = createItem("nugget").build();
    public static final SubstanceItem PLATE = createItem("plate").build();
    public static final SubstanceItem CASING = createItem("casing").build();
    public static final SubstanceItem DUST = createItem("dust").build();
    public static final SubstanceItem FOIL = createItem("foil").build();
    public static final SubstanceItem GEAR = createItem("gear").build();
    public static final SubstanceItem RING = createItem("ring").build();
    public static final SubstanceItem ROD = createItem("rod").build();
    public static final SubstanceItem ROTOR = createItem("rotor").build();
    public static final SubstanceItem SCREW = createItem("screw").build();
    public static final SubstanceItem SPRING = createItem("spring").build();
    public static final SubstanceItem WIRE = createItem("wire").build();

    public static final SubstanceTool AXE = createTool("axe").build();
    public static final SubstanceTool CUTTER = createTool("cutter").build();
    public static final SubstanceTool FILE = createTool("file").build();
    public static final SubstanceTool HAMMER = createTool("hammer").build();
    public static final SubstanceTool HOE = createTool("hoe").build();
    public static final SubstanceTool KNIFE = createTool("knife").build();
    public static final SubstanceTool MORTAR = createTool("mortar").build();
    public static final SubstanceTool PICKAXE = createTool("pickaxe").build();
    public static final SubstanceTool SAW = createTool("saw").build();
    public static final SubstanceTool SCREWDRIVER = createTool("screwdriver").build();
    public static final SubstanceTool SHOVEL = createTool("shovel").build();
    public static final SubstanceTool SWORD = createTool("sword").build();
    public static final SubstanceTool WRENCH = createTool("wrench").build();

    public static final SubstanceBlock BLOCK = createBlock("block", SubstancesObjects::createBlock).model(SubstanceBlock::defaultModel).faces(SubstanceBlock::defaultFaces).build();
    public static final SubstanceBlock FRAME = createBlock("frame", SubstancesObjects::createFrame).model(SubstanceBlock::defaultModel).faces(SubstanceBlock::defaultFaces).build();
    public static final SubstanceBlock HULL = createBlock("hull", SubstancesObjects::createHull).model(SubstanceBlock::defaultModel).faces(SubstanceBlock::defaultFaces).build();
    public static final SubstanceBlock ORE = createBlock("ore", SubstancesObjects::createOre).model(SubstanceBlock::oreModel).faces(SubstanceBlock::oreFaces).build();

    public static final SubstanceFluid LIQUID = createFluid("liquid", SubstancesObjects::createLiquid).build();

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

    private static void createTool(ASubstanceObject reference, Substance substance) {
        fr.cyrilneveu.craftorium.api.item.SubstanceItem item = new fr.cyrilneveu.craftorium.api.item.SubstanceItem(reference, substance);
        item.setRegistryName(reference.getName(substance));
        item.setTranslationKey(String.join(".", MODID, reference.getName(null)));
        item.setCreativeTab(TOOLS);

        ITEMS_REGISTRY.put(reference.getName(substance), item);
    }

    private static void createBlock(ASubstanceObject reference, Substance substance) {
        fr.cyrilneveu.craftorium.api.block.SubstanceBlock block = new fr.cyrilneveu.craftorium.api.block.SubstanceBlock(reference, substance);
        createBlock(reference, substance, block);
    }

    private static void createFrame(ASubstanceObject reference, Substance substance) {
        fr.cyrilneveu.craftorium.api.block.SubstanceBlock block = new fr.cyrilneveu.craftorium.api.block.SubstanceBlock(reference, substance);
        createBlock(reference, substance, block);
    }

    private static void createHull(ASubstanceObject reference, Substance substance) {
        fr.cyrilneveu.craftorium.api.block.SubstanceBlock block = new fr.cyrilneveu.craftorium.api.block.SubstanceBlock(reference, substance);
        createBlock(reference, substance, block);
    }

    private static void createOre(ASubstanceObject reference, Substance substance) {
        fr.cyrilneveu.craftorium.api.block.SubstanceBlock block = new fr.cyrilneveu.craftorium.api.block.SubstanceBlock(reference, substance);
        createBlock(reference, substance, block);
    }

    private static void createBlock(ASubstanceObject reference, Substance substance, CustomBlock block) {
        block.setRegistryName(reference.getName(substance));
        block.setTranslationKey(String.join(".", MODID, reference.getName(null)));
        block.setCreativeTab(SUBSTANCES);

        SubstanceItemBlock item = new SubstanceItemBlock(block, substance);
        item.setRegistryName(reference.getName(substance));
        item.setTranslationKey(String.join(".", MODID, reference.getName(null)));
        item.setCreativeTab(SUBSTANCES);

        BLOCKS_REGISTRY.put(reference.getName(substance), block);
        ITEMS_REGISTRY.put(reference.getName(substance), item);
    }

    private static void createLiquid(ASubstanceObject reference, Substance substance) {

    }
}
