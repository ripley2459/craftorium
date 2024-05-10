package fr.cyrilneveu.craftorium.common.substance;

import fr.cyrilneveu.craftorium.api.item.behaviour.FuelBehaviour;
import fr.cyrilneveu.craftorium.api.item.behaviour.IItemBehaviour;
import fr.cyrilneveu.craftorium.api.render.FaceProvider;
import fr.cyrilneveu.craftorium.api.render.ModelTemplate;
import fr.cyrilneveu.craftorium.api.render.ModelTemplates;
import fr.cyrilneveu.craftorium.api.substance.Substance;
import fr.cyrilneveu.craftorium.api.substance.object.*;
import fr.cyrilneveu.craftorium.api.substance.property.FuelProperty;
import fr.cyrilneveu.craftorium.api.utils.RenderUtils;
import fr.cyrilneveu.craftorium.api.utils.Utils;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidRegistry;

import java.util.ArrayList;
import java.util.List;

import static fr.cyrilneveu.craftorium.CraftoriumTags.MODID;
import static fr.cyrilneveu.craftorium.api.Registries.*;
import static fr.cyrilneveu.craftorium.api.substance.property.SubstanceProperties.KeyProperties.FUEL;
import static fr.cyrilneveu.craftorium.api.utils.Utils.*;
import static fr.cyrilneveu.craftorium.common.inventory.CreativeTabs.SUBSTANCES;
import static fr.cyrilneveu.craftorium.common.inventory.CreativeTabs.TOOLS;

public final class SubstancesObjects {
    public static ASubstanceObject.SubstanceItem CASING;
    public static ASubstanceObject.SubstanceItem DUST;
    public static ASubstanceObject.SubstanceItem FOIL;
    public static ASubstanceObject.SubstanceItem GEAR;
    public static ASubstanceObject.SubstanceItem GEM;
    public static ASubstanceObject.SubstanceItem INGOT;
    public static ASubstanceObject.SubstanceItem NUGGET;
    public static ASubstanceObject.SubstanceItem PEARL;
    public static ASubstanceObject.SubstanceItem PLATE;
    public static ASubstanceObject.SubstanceItem RING;
    public static ASubstanceObject.SubstanceItem ROD;
    public static ASubstanceObject.SubstanceItem ROTOR;
    public static ASubstanceObject.SubstanceItem SCREW;
    public static ASubstanceObject.SubstanceItem SPRING;
    public static ASubstanceObject.SubstanceItem WIRE;

    public static ASubstanceObject.SubstanceTool AXE;
    public static ASubstanceObject.SubstanceTool CUTTER;
    public static ASubstanceObject.SubstanceTool FILE;
    public static ASubstanceObject.SubstanceTool HAMMER;
    public static ASubstanceObject.SubstanceTool HOE;
    public static ASubstanceObject.SubstanceTool KNIFE;
    public static ASubstanceObject.SubstanceTool MORTAR;
    public static ASubstanceObject.SubstanceTool PICKAXE;
    public static ASubstanceObject.SubstanceTool SAW;
    public static ASubstanceObject.SubstanceTool SCREWDRIVER;
    public static ASubstanceObject.SubstanceTool SHOVEL;
    public static ASubstanceObject.SubstanceTool SWORD;
    public static ASubstanceObject.SubstanceTool WRENCH;

    public static ASubstanceObject.SubstanceBlock BLOCK;
    public static ASubstanceObject.SubstanceBlock FRAME;
    public static ASubstanceObject.SubstanceBlock HULL;
    public static ASubstanceObject.SubstanceBlock ORE;

    public static ASubstanceObject.SubstanceFluid LIQUID;

    public static void init() {
        if (SUBSTANCE_ITEMS_REGISTRY.isInitialized() || SUBSTANCE_TOOLS_REGISTRY.isInitialized() || SUBSTANCE_BLOCKS_REGISTRY.isInitialized() || SUBSTANCE_FLUIDS_REGISTRY.isInitialized())
            return;

        SUBSTANCE_ITEMS_REGISTRY.initialize();
        SUBSTANCE_TOOLS_REGISTRY.initialize();
        SUBSTANCE_BLOCKS_REGISTRY.initialize();
        SUBSTANCE_FLUIDS_REGISTRY.initialize();

        GEM = createItem("gem").self().amount(BASE_AMOUNT).build();
        INGOT = createItem("ingot").amount(BASE_AMOUNT).build();
        PEARL = createItem("pearl").amount(BASE_AMOUNT).build();
        PLATE = createItem("plate").amount(INGOT.getAmount()).build();
        CASING = createItem("casing").amount(PLATE.getAmount()).build();
        DUST = createItem("dust").amount(INGOT.getAmount()).build();
        FOIL = createItem("foil").amount(PLATE.getAmount() / 2).build();
        GEAR = createItem("gear").amount(PLATE.getAmount()).build();
        NUGGET = createItem("nugget").amount(INGOT.getAmount() / 9).build();
        ROD = createItem("rod").amount(INGOT.getAmount() / 4).build();
        RING = createItem("ring").amount(ROD.getAmount()).build();
        SCREW = createItem("screw").amount(ROD.getAmount() / 2).build();
        SPRING = createItem("spring").amount(ROD.getAmount()).build();
        WIRE = createItem("wire").amount(FOIL.getAmount() / 4).build();
        ROTOR = createItem("rotor").amount(PLATE.getAmount() * 2 + SCREW.getAmount() * 2 + RING.getAmount()).build();

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
        SHOVEL = createTool("shovel").amount(0).provider(SubstancesObjects::createShovel).build();
        SWORD = createTool("sword").provider(SubstancesObjects::createSword).build();
        WRENCH = createTool("wrench").build();

        BLOCK = createBlock("block", SubstancesObjects::createBlock).model(SubstancesObjects::blockModel).faces(SubstancesObjects::blockFaces).amount(BASE_AMOUNT * 9).build();
        FRAME = createBlock("frame", SubstancesObjects::createFrame).model(SubstancesObjects::blockModel).faces(SubstancesObjects::blockFaces).amount(ROD.getAmount() * 8).build();
        HULL = createBlock("hull", SubstancesObjects::createHull).model(SubstancesObjects::blockModel).faces(SubstancesObjects::blockFaces).amount(PLATE.getAmount() * 8).build();
        ORE = createBlock("ore", SubstancesObjects::createOre).model(SubstancesObjects::oreModel).faces(SubstancesObjects::oreFaces).build();

        LIQUID = createFluid("liquid", SubstancesObjects::createLiquid).faces(SubstancesObjects::fluidFaces).amount(1).build();
    }

    private static ASubstanceObjectBuilder.SubstanceItemBuilder createItem(String name) {
        ASubstanceObjectBuilder.SubstanceItemBuilder builder = new ASubstanceObjectBuilder.SubstanceItemBuilder(name);
        builder.provider(SubstancesObjects::createItem);
        builder.faces(SubstancesObjects::itemFaces);
        builder.behaviours(SubstancesObjects::defaultBehaviours);

        return builder;
    }

    private static ASubstanceObjectBuilder.SubstanceToolBuilder createTool(String name) {
        ASubstanceObjectBuilder.SubstanceToolBuilder builder = new ASubstanceObjectBuilder.SubstanceToolBuilder(name);
        builder.provider(SubstancesObjects::createTool);
        builder.faces(SubstancesObjects::toolFaces);
        builder.behaviours(SubstancesObjects::defaultBehaviours);

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
        SubstanceItem item = new SubstanceItem(reference, substance);
        item.setRegistryName(reference.getName(substance));
        item.setTranslationKey(String.join(".", MODID, reference.getName(null)));
        item.setCreativeTab(SUBSTANCES);

        ITEMS_REGISTRY.put(reference.getName(substance), item);
    }

    private static void createAxe(ASubstanceObject reference, Substance substance) {
        SubstanceTool.SubstanceAxe item = new SubstanceTool.SubstanceAxe(reference, substance);

        createTool(reference, substance, item);
    }

    private static void createHoe(ASubstanceObject reference, Substance substance) {
        SubstanceTool.SubstanceHoe item = new SubstanceTool.SubstanceHoe(reference, substance);

        createTool(reference, substance, item);
    }

    private static void createPickaxe(ASubstanceObject reference, Substance substance) {
        SubstanceTool.SubstancePickaxe item = new SubstanceTool.SubstancePickaxe(reference, substance);

        createTool(reference, substance, item);
    }

    private static void createShovel(ASubstanceObject reference, Substance substance) {
        SubstanceTool.SubstanceShovel item = new SubstanceTool.SubstanceShovel(reference, substance);

        createTool(reference, substance, item);
    }

    private static void createSword(ASubstanceObject reference, Substance substance) {
        SubstanceTool.SubstanceSword item = new SubstanceTool.SubstanceSword(reference, substance);

        createTool(reference, substance, item);
    }

    private static void createTool(ASubstanceObject reference, Substance substance) {
        SubstanceTool item = new SubstanceTool(reference, substance);

        createTool(reference, substance, item);
    }

    private static void createTool(ASubstanceObject reference, Substance substance, Item item) {
        item.setRegistryName(reference.getName(substance));
        item.setTranslationKey(String.join(".", MODID, reference.getName(null)));
        item.setCreativeTab(TOOLS);

        ITEMS_REGISTRY.put(reference.getName(substance), item);
    }

    private static void createBlock(ASubstanceObject reference, Substance substance) {
        SubstanceBlock block = new SubstanceBlock(Material.IRON, reference, substance);

        createBlock(reference, substance, block);
    }

    private static void createFrame(ASubstanceObject reference, Substance substance) {
        SubstanceBlock.FrameBlock block = new SubstanceBlock.FrameBlock(Material.IRON, reference, substance);

        createBlock(reference, substance, block);
    }

    private static void createHull(ASubstanceObject reference, Substance substance) {
        SubstanceBlock block = new SubstanceBlock(Material.IRON, reference, substance);

        createBlock(reference, substance, block);
    }

    private static void createOre(ASubstanceObject reference, Substance substance) {
        SubstanceBlock.OreBlock block = new SubstanceBlock.OreBlock(Material.ROCK, reference, substance);

        createBlock(reference, substance, block);
    }

    private static void createBlock(ASubstanceObject reference, Substance substance, Block block) {
        block.setRegistryName(reference.getName(substance));
        block.setTranslationKey(String.join(".", MODID, reference.getName(null)));
        block.setCreativeTab(SUBSTANCES);

        SubstanceBlock.SubstanceItemBlock item = new SubstanceBlock.SubstanceItemBlock(block, substance);
        item.setRegistryName(reference.getName(substance));
        item.setTranslationKey(String.join(".", MODID, reference.getName(null)));
        item.setCreativeTab(SUBSTANCES);

        BLOCKS_REGISTRY.put(reference.getName(substance), block);
        ITEMS_REGISTRY.put(reference.getName(substance), item);
    }

    private static void createLiquid(ASubstanceObject reference, Substance substance) {
        SubstanceFluid fluid = new SubstanceFluid(reference, substance);

        boolean gaseous = substance.getTemperature().getBoilingPoint() <= BASE_TEMPERATURE;

        fluid.setGaseous(gaseous);
        fluid.setLuminosity(15);
        fluid.setTemperature((int) (gaseous ? substance.getTemperature().getBoilingPoint() : substance.getTemperature().getMeltingPoint()));
        fluid.setDensity(gaseous ? -100 : 3000);
        fluid.setViscosity(gaseous ? 200 : 1000);
        fluid.setUnlocalizedName(String.join(".", MODID, reference.getName(null), "name"));

        FLUIDS_REGISTRY.put(reference.getName(substance), fluid);
        FluidRegistry.registerFluid(fluid);
        FluidRegistry.addBucketForFluid(fluid);

        SubstanceFluid.SubstanceFluidBlock block = new SubstanceFluid.SubstanceFluidBlock(fluid, Material.LAVA);
        block.setTranslationKey(String.join(".", MODID, reference.getName(null), "name"));
        block.setRegistryName(reference.getName(substance));

        BLOCKS_REGISTRY.put(reference.getName(substance), block);
    }

    public static void close() {
        SUBSTANCE_ITEMS_REGISTRY.order().close();
        SUBSTANCE_TOOLS_REGISTRY.order().close();
        SUBSTANCE_BLOCKS_REGISTRY.order().close();
        SUBSTANCE_FLUIDS_REGISTRY.order().close();
    }

    private static FaceProvider[] blockFaces(ASubstanceObject reference, Substance substance) {
        FaceProvider[] faces = new FaceProvider[substance.getAestheticism().isShiny() ? 2 : 1];
        faces[0] = new FaceProvider(new ResourceLocation(MODID, String.join("/", "blocks", "substances", "blocks", substance.getAestheticism().getStyle(), reference.getSelf() ? substance.getName() : reference.getName(null))), substance.getAestheticism().getBaseColor());
        if (substance.getAestheticism().isShiny())
            faces[1] = new FaceProvider(new ResourceLocation(MODID, String.join("/", "blocks", "substances", "blocks", substance.getAestheticism().getStyle(), (reference.getSelf() ? substance.getName() : reference.getName(null)).concat("_overlay"))), RenderUtils.WHITE_COLOR);
        return faces;
    }

    private static FaceProvider[] oreFaces(ASubstanceObject reference, Substance substance) {
        FaceProvider[] faces = new FaceProvider[1];
        faces[0] = new FaceProvider(new ResourceLocation(MODID, String.join("/", "blocks", "substances", "blocks", substance.getAestheticism().getStyle(), reference.getSelf() ? substance.getName() : reference.getName(null))), substance.getAestheticism().getOreColor());
        return faces;
    }

    private static ModelTemplate blockModel(ASubstanceObject reference, Substance substance) {
        return substance.getAestheticism().isShiny() ? ModelTemplates.BLOCK_TINTED_OVERLAY : ModelTemplates.BLOCK_TINTED;
    }

    private static ModelTemplate oreModel(ASubstanceObject reference, Substance substance) {
        return ModelTemplates.BLOCK_OVERLAY_TINTED;
    }

    private static FaceProvider[] fluidFaces(ASubstanceObject reference, Substance substance) {
        FaceProvider[] faces = new FaceProvider[2];
        faces[0] = new FaceProvider(new ResourceLocation(MODID, String.join("/", "fluids", "substances", "fluids", substance.getAestheticism().getStyle(), "still")), substance.getAestheticism().getFluidColor());
        faces[1] = new FaceProvider(new ResourceLocation(MODID, String.join("/", "fluids", "substances", "fluids", substance.getAestheticism().getStyle(), "flow")), substance.getAestheticism().getFluidColor());
        return faces;
    }

    private static FaceProvider[] toolFaces(ASubstanceObject reference, Substance substance) {
        FaceProvider[] faces = new FaceProvider[2];
        faces[0] = new FaceProvider(new ResourceLocation(MODID, String.join("/", "items", "substances", "tools", reference.getSelf() ? substance.getName() : reference.getName(null))), substance.getAestheticism().getBaseColor());
        faces[1] = new FaceProvider(new ResourceLocation(MODID, String.join("/", "items", "substances", "tools", (reference.getSelf() ? substance.getName() : reference.getName(null)).concat("_base"))), RenderUtils.WHITE_COLOR);
        return faces;
    }

    private static FaceProvider[] itemFaces(ASubstanceObject reference, Substance substance) {
        FaceProvider[] faces = new FaceProvider[substance.getAestheticism().isShiny() ? 2 : 1];
        faces[0] = new FaceProvider(new ResourceLocation(MODID, String.join("/", "items", "substances", "items", substance.getAestheticism().getStyle(), reference.getSelf() ? substance.getName() : reference.getName(null))), substance.getAestheticism().getBaseColor());
        if (substance.getAestheticism().isShiny())
            faces[1] = new FaceProvider(new ResourceLocation(MODID, String.join("/", "items", "substances", "items", substance.getAestheticism().getStyle(), (reference.getSelf() ? substance.getName() : reference.getName(null)).concat("_overlay"))), RenderUtils.WHITE_COLOR);
        return faces;
    }

    private static List<String> baseTooltips(ASubstanceObject reference, Substance substance) {
        List<String> lines = new ArrayList<>();
        String formula = substance.getComposition().getFormula();
        if (!formula.isEmpty())
            lines.add(Utils.localise("tooltip.craftorium.formula", formula));
        if (Utils.isAdvancedTooltipsOn())
            lines.add(Utils.localise("tooltip.craftorium.temperature", BASE_TEMPERATURE));
        lines.add(Utils.localise("tooltip.craftorium.state.solid"));
        return lines;
    }

    private static List<String> fluidTooltips(ASubstanceObject reference, Substance substance) {
        List<String> lines = new ArrayList<>();
        String formula = substance.getComposition().getFormula();
        if (!formula.isEmpty())
            lines.add(Utils.localise("tooltip.craftorium.formula", formula));
        boolean gaseous = substance.getTemperature().getBoilingPoint() <= BASE_TEMPERATURE;
        lines.add(Utils.localise("tooltip.craftorium.temperature", gaseous ? substance.getTemperature().getBoilingPoint() : substance.getTemperature().getMeltingPoint()));
        lines.add(Utils.localise(gaseous ? "tooltip.craftorium.state.gaseous" : "tooltip.craftorium.state.liquid"));
        return lines;
    }

    public static IItemBehaviour[] defaultBehaviours(ASubstanceObject reference, Substance substance) {
        List<IItemBehaviour> behaviours = new ArrayList<>();

        if (substance.getProperties().containsKey(FUEL)) {
            int duration = ((FuelProperty) substance.getProperties().get(FUEL)).getBurnDuration();
            if (duration > 0) {
                duration = (reference.getAmount() * duration) / BASE_AMOUNT;
                behaviours.add(new FuelBehaviour(duration));
            }
        }

        return behaviours.isEmpty() ? NO_BEHAVIOUR : behaviours.toArray(new IItemBehaviour[0]);
    }
}
