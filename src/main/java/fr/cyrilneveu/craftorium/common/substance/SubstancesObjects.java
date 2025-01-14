package fr.cyrilneveu.craftorium.common.substance;

import fr.cyrilneveu.craftorium.api.config.Settings;
import fr.cyrilneveu.craftorium.api.item.CustomItem;
import fr.cyrilneveu.craftorium.api.item.behaviour.DurabilityBehaviour;
import fr.cyrilneveu.craftorium.api.item.behaviour.FuelBehaviour;
import fr.cyrilneveu.craftorium.api.item.behaviour.IItemBehaviour;
import fr.cyrilneveu.craftorium.api.item.behaviour.ItemEnergyStorageBehaviour;
import fr.cyrilneveu.craftorium.api.property.Aestheticism;
import fr.cyrilneveu.craftorium.api.property.Temperature;
import fr.cyrilneveu.craftorium.api.render.FaceProvider;
import fr.cyrilneveu.craftorium.api.render.ModelTemplate;
import fr.cyrilneveu.craftorium.api.render.ModelTemplates;
import fr.cyrilneveu.craftorium.api.substance.Substance;
import fr.cyrilneveu.craftorium.api.substance.Tier;
import fr.cyrilneveu.craftorium.api.substance.object.*;
import fr.cyrilneveu.craftorium.api.substance.property.SubstanceProperties;
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
    public static ASubstanceObject.SubstanceItemDefinition BATTERY;
    public static ASubstanceObject.SubstanceItemDefinition BUZZSAW;
    public static ASubstanceObject.SubstanceItemDefinition EMITTER;
    public static ASubstanceObject.SubstanceItemDefinition GRINDER;
    public static ASubstanceObject.SubstanceItemDefinition HEAT_EXCHANGER;
    public static ASubstanceObject.SubstanceItemDefinition MOTOR;
    public static ASubstanceObject.SubstanceItemDefinition PISTON;
    public static ASubstanceObject.SubstanceItemDefinition PUMP;
    public static ASubstanceObject.SubstanceItemDefinition ROBOT_ARM;
    public static ASubstanceObject.SubstanceItemDefinition SCANNER;
    public static ASubstanceObject.SubstanceItemDefinition SENSOR;

    public static ASubstanceObject.SubstanceItemDefinition CASING;
    public static ASubstanceObject.SubstanceItemDefinition DUST;
    public static ASubstanceObject.SubstanceItemDefinition FOIL;
    public static ASubstanceObject.SubstanceItemDefinition GEAR;
    public static ASubstanceObject.SubstanceItemDefinition GEM;
    public static ASubstanceObject.SubstanceItemDefinition INGOT;
    public static ASubstanceObject.SubstanceItemDefinition MESH;
    public static ASubstanceObject.SubstanceItemDefinition NUGGET;
    public static ASubstanceObject.SubstanceItemDefinition PEARL;
    public static ASubstanceObject.SubstanceItemDefinition PLATE;
    public static ASubstanceObject.SubstanceItemDefinition RING;
    public static ASubstanceObject.SubstanceItemDefinition ROD;
    public static ASubstanceObject.SubstanceItemDefinition ROTOR;
    public static ASubstanceObject.SubstanceItemDefinition SCREW;
    public static ASubstanceObject.SubstanceItemDefinition SPRING;
    public static ASubstanceObject.SubstanceItemDefinition WIRE;

    public static ASubstanceObject.SubstanceToolDefinition AXE;
    public static ASubstanceObject.SubstanceToolDefinition CUTTER;
    public static ASubstanceObject.SubstanceToolDefinition FILE;
    public static ASubstanceObject.SubstanceToolDefinition HAMMER;
    public static ASubstanceObject.SubstanceToolDefinition HOE;
    public static ASubstanceObject.SubstanceToolDefinition KNIFE;
    public static ASubstanceObject.SubstanceToolDefinition MORTAR;
    public static ASubstanceObject.SubstanceToolDefinition PICKAXE;
    public static ASubstanceObject.SubstanceToolDefinition SAW;
    public static ASubstanceObject.SubstanceToolDefinition SCREWDRIVER;
    public static ASubstanceObject.SubstanceToolDefinition SHOVEL;
    public static ASubstanceObject.SubstanceToolDefinition SWORD;
    public static ASubstanceObject.SubstanceToolDefinition WRENCH;

    public static ASubstanceObject.SubstanceBlockDefinition BLOCK;
    public static ASubstanceObject.SubstanceBlockDefinition FRAME;
    public static ASubstanceObject.SubstanceBlockDefinition HULL;
    public static ASubstanceObject.SubstanceBlockDefinition ORE;

    public static ASubstanceObject.SubstanceBlockDefinition MACHINE_FRAME;

    public static ASubstanceObject.SubstanceFluidDefinition LIQUID;

    public static void init() {
        if (SUBSTANCE_ITEMS_REGISTRY.isInitialized() || SUBSTANCE_TOOLS_REGISTRY.isInitialized() || SUBSTANCE_BLOCKS_REGISTRY.isInitialized() || SUBSTANCE_FLUIDS_REGISTRY.isInitialized())
            return;

        SUBSTANCE_ITEMS_REGISTRY.initialize();
        SUBSTANCE_TOOLS_REGISTRY.initialize();
        SUBSTANCE_BLOCKS_REGISTRY.initialize();
        SUBSTANCE_FLUIDS_REGISTRY.initialize();

        BATTERY = createTierItem("battery").provider(SubstancesObjects::createStandalone).tooltips(SubstancesObjects::tierTooltips).behaviours(SubstancesObjects::energyStorage).faces(SubstancesObjects::tierItemFaces).build();
        BUZZSAW = createTierItem("buzzsaw").faces(SubstancesObjects::tierItemFaces).tooltips(SubstancesObjects::tierTooltips).build();
        EMITTER = createTierItem("emitter").faces(SubstancesObjects::tierItemFaces).tooltips(SubstancesObjects::tierTooltips).build();
        GRINDER = createTierItem("grinder").faces(SubstancesObjects::tierItemFaces).tooltips(SubstancesObjects::tierTooltips).build();
        HEAT_EXCHANGER = createTierItem("heat_exchanger").faces(SubstancesObjects::tierItemFaces).tooltips(SubstancesObjects::tierTooltips).build();
        MOTOR = createTierItem("motor").faces(SubstancesObjects::tierItemFaces).tooltips(SubstancesObjects::tierTooltips).build();
        PISTON = createTierItem("piston").faces(SubstancesObjects::tierItemFaces).tooltips(SubstancesObjects::tierTooltips).build();
        PUMP = createTierItem("pump").faces(SubstancesObjects::tierItemFaces).tooltips(SubstancesObjects::tierTooltips).build();
        ROBOT_ARM = createTierItem("robot_arm").faces(SubstancesObjects::tierItemFaces).tooltips(SubstancesObjects::tierTooltips).build();
        SCANNER = createTierItem("scanner").faces(SubstancesObjects::tierItemFaces).tooltips(SubstancesObjects::tierTooltips).provider(SubstancesObjects::createStandalone).behaviours(SubstancesObjects::energyStorage).build();
        SENSOR = createTierItem("sensor").faces(SubstancesObjects::tierItemFaces).tooltips(SubstancesObjects::tierTooltips).build();

        GEM = createSubstanceItem("gem").self().amount(BASE_AMOUNT).tooltips(SubstancesObjects::baseTooltips).build();
        INGOT = createSubstanceItem("ingot").amount(BASE_AMOUNT).tooltips(SubstancesObjects::baseTooltips).build();
        PEARL = createSubstanceItem("pearl").amount(BASE_AMOUNT).tooltips(SubstancesObjects::baseTooltips).build();
        PLATE = createSubstanceItem("plate").amount(INGOT.getAmount()).build();
        CASING = createSubstanceItem("casing").amount(PLATE.getAmount()).build();
        DUST = createSubstanceItem("dust").amount(INGOT.getAmount()).tooltips(SubstancesObjects::baseTooltips).build();
        FOIL = createSubstanceItem("foil").amount(PLATE.getAmount() / 2).build();
        GEAR = createSubstanceItem("gear").amount(PLATE.getAmount()).build();
        NUGGET = createSubstanceItem("nugget").amount(INGOT.getAmount() / 9).build();
        MESH = createSubstanceItem("mesh").amount(INGOT.getAmount()).build();
        ROD = createSubstanceItem("rod").amount(INGOT.getAmount() / 4).build();
        RING = createSubstanceItem("ring").amount(ROD.getAmount()).build();
        SCREW = createSubstanceItem("screw").amount(ROD.getAmount() / 2).build();
        SPRING = createSubstanceItem("spring").amount(ROD.getAmount()).build();
        WIRE = createSubstanceItem("wire").amount(FOIL.getAmount() / 4).build();
        ROTOR = createSubstanceItem("rotor").amount(PLATE.getAmount() * 2 + SCREW.getAmount() * 2 + RING.getAmount()).build();

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

        BLOCK = createBlock("block", SubstancesObjects::createBlock).model(SubstancesObjects::blockModel).faces(SubstancesObjects::blockFaces).amount(BASE_AMOUNT * 9).build();
        FRAME = createBlock("frame", SubstancesObjects::createFrame).model(SubstancesObjects::blockModel).faces(SubstancesObjects::blockFaces).amount(ROD.getAmount() * 8).build();
        HULL = createBlock("hull", SubstancesObjects::createHull).model(SubstancesObjects::blockModel).faces(SubstancesObjects::blockFaces).amount(PLATE.getAmount() * 8).build();
        ORE = createBlock("ore", SubstancesObjects::createOre).tooltips(SubstancesObjects::baseTooltips).model(SubstancesObjects::oreModel).faces(SubstancesObjects::oreFaces).build();

        MACHINE_FRAME = createBlock("machine_frame", SubstancesObjects::createBlock).tooltips(SubstancesObjects::tierTooltips).model(SubstancesObjects::blockModel).faces(SubstancesObjects::tierBlockFaces).amount(BASE_AMOUNT * 9).build();

        LIQUID = createFluid("liquid", SubstancesObjects::createLiquid).faces(SubstancesObjects::fluidFaces).tooltips(SubstancesObjects::fluidTooltips).amount(1).build();
    }

    private static ASubstanceObjectBuilder.SubstanceItemBuilder createSubstanceItem(String name) {
        ASubstanceObjectBuilder.SubstanceItemBuilder builder = new ASubstanceObjectBuilder.SubstanceItemBuilder(name);
        builder.provider(SubstancesObjects::createItem);
        builder.faces(SubstancesObjects::itemFaces);
        builder.behaviours(SubstancesObjects::defaultBehaviours);

        return builder;
    }

    private static ASubstanceObjectBuilder.SubstanceItemBuilder createTierItem(String name) {
        ASubstanceObjectBuilder.SubstanceItemBuilder builder = new ASubstanceObjectBuilder.SubstanceItemBuilder(name);
        builder.provider(SubstancesObjects::createItem);
        builder.faces(SubstancesObjects::itemFaces);
        builder.behaviours(SubstancesObjects::defaultBehaviours);

        return builder;
    }

    private static void createStandalone(ASubstanceObject reference, Substance substance) {
        CustomItem item = new CustomItem(defaultBehaviours(reference, substance), defaultAestheticism(reference, substance));
        item.setRegistryName(reference.getName(substance));
        item.setTranslationKey(String.join(".", MODID, reference.getName(null)));
        item.setMaxStackSize(1);
        item.setCreativeTab(SUBSTANCES);

        ITEMS_REGISTRY.put(reference.getName(substance), item);
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
        CustomItem item = new CustomItem(defaultBehaviours(reference, substance), defaultAestheticism(reference, substance));
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
        IItemBehaviour[] behaviours = new IItemBehaviour[1];

        behaviours[0] = new DurabilityBehaviour(substance);

        CustomItem item = new CustomItem(behaviours, defaultAestheticism(reference, substance));
        item.setMaxStackSize(1);

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
        SUBSTANCE_ITEMS_REGISTRY.close();
        SUBSTANCE_TOOLS_REGISTRY.close();
        SUBSTANCE_BLOCKS_REGISTRY.close();
        SUBSTANCE_FLUIDS_REGISTRY.close();
    }

    public static FaceProvider[] blockFaces(ASubstanceObject reference, Substance substance) {
        FaceProvider[] faces = new FaceProvider[substance.getAestheticism().isShiny() ? 2 : 1];
        faces[0] = new FaceProvider(new ResourceLocation(MODID, String.join("/", "blocks", "substances", "blocks", substance.getAestheticism().getStyle(), reference.getSelf() ? substance.getName() : reference.getName(null))), substance.getAestheticism().getBaseColor());
        if (substance.getAestheticism().isShiny())
            faces[1] = new FaceProvider(new ResourceLocation(MODID, String.join("/", "blocks", "substances", "blocks", substance.getAestheticism().getStyle(), (reference.getSelf() ? substance.getName() : reference.getName(null)).concat("_overlay"))), RenderUtils.WHITE_COLOR);
        return faces;
    }

    public static FaceProvider[] tierBlockFaces(ASubstanceObject reference, Substance substance) {
        FaceProvider[] faces = new FaceProvider[2];
        faces[0] = new FaceProvider(new ResourceLocation(MODID, String.join("/", "blocks", "tiers", reference.getSelf() ? substance.getName() : reference.getName(null))), substance.getAestheticism().getBaseColor());
        faces[1] = new FaceProvider(new ResourceLocation(MODID, String.join("/", "blocks", "tiers", (reference.getSelf() ? substance.getName() : reference.getName(null)).concat("_overlay"))), RenderUtils.WHITE_COLOR);
        return faces;
    }

    public static FaceProvider[] oreFaces(ASubstanceObject reference, Substance substance) {
        FaceProvider[] faces = new FaceProvider[1];
        faces[0] = new FaceProvider(new ResourceLocation(MODID, String.join("/", "blocks", "substances", "blocks", substance.getAestheticism().getStyle(), reference.getSelf() ? substance.getName() : reference.getName(null))), substance.getAestheticism().getOreColor());
        return faces;
    }

    public static ModelTemplate blockModel(ASubstanceObject reference, Substance substance) {
        return substance.getAestheticism().isShiny() ? ModelTemplates.BLOCK_TINTED_OVERLAY : ModelTemplates.BLOCK_TINTED;
    }

    public static ModelTemplate oreModel(ASubstanceObject reference, Substance substance) {
        return ModelTemplates.BLOCK_OVERLAY_TINTED;
    }

    public static FaceProvider[] fluidFaces(ASubstanceObject reference, Substance substance) {
        FaceProvider[] faces = new FaceProvider[2];
        faces[0] = new FaceProvider(new ResourceLocation(MODID, String.join("/", "fluids", "substances", "fluids", substance.getAestheticism().getStyle(), "still")), substance.getAestheticism().getFluidColor());
        faces[1] = new FaceProvider(new ResourceLocation(MODID, String.join("/", "fluids", "substances", "fluids", substance.getAestheticism().getStyle(), "flow")), substance.getAestheticism().getFluidColor());
        return faces;
    }

    public static FaceProvider[] toolFaces(ASubstanceObject reference, Substance substance) {
        FaceProvider[] faces = new FaceProvider[2];
        faces[0] = new FaceProvider(new ResourceLocation(MODID, String.join("/", "items", "substances", "tools", reference.getSelf() ? substance.getName() : reference.getName(null))), substance.getAestheticism().getBaseColor());
        faces[1] = new FaceProvider(new ResourceLocation(MODID, String.join("/", "items", "substances", "tools", (reference.getSelf() ? substance.getName() : reference.getName(null)).concat("_base"))), RenderUtils.WHITE_COLOR);
        return faces;
    }

    public static FaceProvider[] itemFaces(ASubstanceObject reference, Substance substance) {
        FaceProvider[] faces = new FaceProvider[substance.getAestheticism().isShiny() ? 2 : 1];
        faces[0] = new FaceProvider(new ResourceLocation(MODID, String.join("/", "items", "substances", "items", substance.getAestheticism().getStyle(), reference.getSelf() ? substance.getName() : reference.getName(null))), substance.getAestheticism().getBaseColor());
        if (substance.getAestheticism().isShiny())
            faces[1] = new FaceProvider(new ResourceLocation(MODID, String.join("/", "items", "substances", "items", substance.getAestheticism().getStyle(), (reference.getSelf() ? substance.getName() : reference.getName(null)).concat("_overlay"))), RenderUtils.WHITE_COLOR);
        return faces;
    }

    public static FaceProvider[] tierItemFaces(ASubstanceObject reference, Substance substance) {
        FaceProvider[] faces = new FaceProvider[2];
        faces[0] = new FaceProvider(new ResourceLocation(MODID, String.join("/", "items", "tiers", reference.getSelf() ? substance.getName() : reference.getName(null))), substance.getAestheticism().getBaseColor());
        faces[1] = new FaceProvider(new ResourceLocation(MODID, String.join("/", "items", "tiers", (reference.getSelf() ? substance.getName() : reference.getName(null)).concat("_overlay"))), RenderUtils.WHITE_COLOR);
        return faces;
    }

    public static List<String> tierTooltips(ASubstanceObject reference, Substance substance) {
        List<String> lines = new ArrayList<>();
        lines.add(Utils.localise("tooltip.craftorium.tier.name", substance.getDisplayName()));
        return lines;
    }

    public static List<String> baseTooltips(ASubstanceObject reference, Substance substance) {
        List<String> lines = new ArrayList<>();
        String formula = substance.getComposition().getFormula();
        if (!formula.isEmpty())
            lines.add(Utils.localise("tooltip.craftorium.formula", formula));
        if (Utils.isAdvancedTooltipsOn())
            lines.add(Utils.localise("tooltip.craftorium.temperature", Math.round(BASE_TEMPERATURE)));
        lines.add(Utils.localise("tooltip.craftorium.state.solid"));
        return lines;
    }

    public static List<String> fluidTooltips(ASubstanceObject reference, Substance substance) {
        List<String> lines = new ArrayList<>();
        String formula = substance.getComposition().getFormula();
        if (!formula.isEmpty())
            lines.add(Utils.localise("tooltip.craftorium.formula", formula));
        boolean gaseous = substance.getTemperature().getBoilingPoint() <= BASE_TEMPERATURE;
        if (substance.getTemperature() != Temperature.EMPTY)
            lines.add(Utils.localise("tooltip.craftorium.temperature", Math.round(gaseous ? substance.getTemperature().getBoilingPoint() : substance.getTemperature().getMeltingPoint())));
        lines.add(Utils.localise(gaseous ? "tooltip.craftorium.state.gaseous" : "tooltip.craftorium.state.liquid"));
        return lines;
    }

    public static IItemBehaviour[] energyStorage(ASubstanceObject reference, Substance substance) {
        Tier tier = (Tier) substance;
        IItemBehaviour[] behaviours = new IItemBehaviour[1];
        behaviours[0] = new ItemEnergyStorageBehaviour(null, (int) (Settings.machinesSettings.batteryBaseStorage * tier.getEnergyBuffer()), (int) (Settings.machinesSettings.batteryBaseTransfer * tier.getEnergyIO()));
        return behaviours;
    }

    public static IItemBehaviour[] defaultBehaviours(ASubstanceObject reference, Substance substance) {
        List<IItemBehaviour> behaviours = new ArrayList<>();

        if (substance.getProperties().containsKey(FUEL)) {
            int duration = ((SubstanceProperties.FuelProperty) substance.getProperties().get(FUEL)).getBurnDuration();
            if (duration > 0) {
                duration = (reference.getAmount() * duration) / BASE_AMOUNT;
                behaviours.add(new FuelBehaviour(duration));
            }
        }

        return behaviours.isEmpty() ? NO_BEHAVIOUR : behaviours.toArray(new IItemBehaviour[0]);
    }

    public static Aestheticism.ObjectAestheticism defaultAestheticism(ASubstanceObject reference, Substance substance) {
        return new Aestheticism.ObjectAestheticism(reference.getFaces(substance),
                () -> reference.getTooltips(substance),
                substance.getAestheticism().isGlint(),
                s -> Utils.localise(String.join(".", s.getTranslationKey(), "name"), substance.getDisplayName())
        );
    }
}
