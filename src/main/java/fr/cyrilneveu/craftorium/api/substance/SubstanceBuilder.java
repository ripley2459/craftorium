package fr.cyrilneveu.craftorium.api.substance;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSortedSet;
import crafttweaker.CraftTweakerAPI;
import crafttweaker.annotations.ZenRegister;
import fr.cyrilneveu.craftorium.api.property.Aestheticism;
import fr.cyrilneveu.craftorium.api.property.Efficiency;
import fr.cyrilneveu.craftorium.api.property.Temperature;
import fr.cyrilneveu.craftorium.api.property.Toughness;
import fr.cyrilneveu.craftorium.api.recipe.AProcess;
import fr.cyrilneveu.craftorium.api.substance.object.*;
import fr.cyrilneveu.craftorium.api.substance.property.ASubstanceProperty;
import fr.cyrilneveu.craftorium.api.substance.property.Composition;
import fr.cyrilneveu.craftorium.api.substance.property.ESubstanceProperties;
import net.minecraft.block.SoundType;
import net.minecraftforge.common.util.EnumHelper;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import javax.annotation.Nullable;
import java.util.*;

import static fr.cyrilneveu.craftorium.CraftoriumTags.MODID;
import static fr.cyrilneveu.craftorium.api.utils.Utils.ERROR_COLOR;
import static fr.cyrilneveu.craftorium.common.recipe.Processes.DEFAULT_PROCESS;
import static fr.cyrilneveu.craftorium.common.substance.Substances.SUBSTANCES_REGISTRY;
import static fr.cyrilneveu.craftorium.common.substance.SubstancesObjects.*;

@ZenClass("mods." + MODID + ".substance.Builder")
@ZenRegister
public final class SubstanceBuilder {
    private String name;
    private Set<SubstanceStack> composition, possible;
    @Nullable
    private Element element;
    @Nullable
    private Efficiency efficiency;
    private Toughness toughness = new Toughness(5.0f, 10.0f, "pickaxe", 2);
    private Temperature temperature = Temperature.EMPTY;
    private AProcess process = DEFAULT_PROCESS;
    private Map<ESubstanceProperties, ASubstanceProperty> properties = new HashMap<>();
    private Set<SubstanceItem> items = new TreeSet<>();
    private Set<SubstanceTool> tools = new TreeSet<>();
    private Set<SubstanceBlock> blocks = new TreeSet<>();
    private Set<SubstanceFluid> fluids = new TreeSet<>();
    private Map<ASubstanceObject, String> overrides = new HashMap<>();
    private String style = "metal";
    private boolean shiny, glow = false;
    private int baseColor, oreColor, fluidColor = ERROR_COLOR;
    private SoundType soundType = SoundType.METAL;

    public SubstanceBuilder(String name) {
        this.name = name;
    }

    @ZenMethod
    public SubstanceBuilder composition(Object... composition) {
        Preconditions.checkArgument(composition.length % 2 == 0);

        Set<SubstanceStack> composition1 = new LinkedHashSet<>();

        for (int i = 0; i < composition.length; i += 2) {
            Preconditions.checkArgument((composition[i] instanceof Substance || composition[i] instanceof String) && (composition[i + 1] instanceof Integer));

            Substance substance;
            if (composition[i] instanceof String) {
                Preconditions.checkArgument(SUBSTANCES_REGISTRY.contains((String) composition[i]));

                substance = SUBSTANCES_REGISTRY.get((String) composition[i]);
            } else substance = (Substance) composition[i];

            composition1.add(new SubstanceStack(substance, (Integer) composition[i + 1]));
        }

        this.composition = ImmutableSet.copyOf(composition1);
        this.element = null;
        return this;
    }

    @ZenMethod
    public SubstanceBuilder possible(Object... chanced) {
        Preconditions.checkArgument(chanced.length % 3 == 0);

        Set<SubstanceStack> possible1 = new LinkedHashSet<>();

        for (int i = 0; i < chanced.length; i += 3) {
            Preconditions.checkArgument((chanced[i] instanceof Substance || chanced[i] instanceof String) && (chanced[i + 1] instanceof Integer) && (chanced[i + 2] instanceof Integer));

            Substance substance;
            if (chanced[i] instanceof String) {
                Preconditions.checkArgument(SUBSTANCES_REGISTRY.contains((String) chanced[i]));

                substance = SUBSTANCES_REGISTRY.get((String) chanced[i]);
            } else substance = (Substance) chanced[i];

            possible1.add(new SubstanceStack(substance, (Integer) chanced[i + 1], (Integer) chanced[i + 2]));
        }

        this.possible = ImmutableSet.copyOf(possible1);
        this.element = null;
        return this;
    }

    @ZenMethod
    public SubstanceBuilder element(int atomicNumber, String symbol, String name, Element.EGroup group, double atomicMass) {
        this.element = new Element(atomicNumber, symbol, name, group, atomicMass);
        this.composition = null;
        this.possible = null;
        return this;
    }

    @ZenMethod
    public SubstanceBuilder tools(float speed, float damage, int durability, int harvestLevel, int enchantability) {
        this.efficiency = new Efficiency(speed, damage, durability, harvestLevel, enchantability);
        return this;
    }

    @ZenMethod
    public SubstanceBuilder toughness(float hardness, float resistance, String toolClass, int harvestLevel) {
        this.toughness = new Toughness(hardness, resistance, toolClass, harvestLevel);
        return this;
    }

    @ZenMethod
    public SubstanceBuilder temperature(float meltingPoint, float boilingPoint) {
        this.temperature = new Temperature(meltingPoint, boilingPoint);
        return this;
    }

    @ZenMethod
    public SubstanceBuilder setHalogen() {
        items(INGOT, NUGGET, PLATE, CASING, DUST, FOIL, GEAR, RING, ROD, ROTOR, SCREW, SPRING, WIRE);
        tools(AXE, CUTTER, FILE, HAMMER, HOE, KNIFE, MORTAR, PICKAXE, SAW, SCREWDRIVER, SHOVEL, SWORD, WRENCH);
        blocks(BLOCK, FRAME, HULL, ORE);
        fluids(LIQUID);
        return this;
    }

    @ZenMethod
    public SubstanceBuilder setNobleGas() {
        items(INGOT, NUGGET, PLATE, CASING, DUST, FOIL, GEAR, RING, ROD, ROTOR, SCREW, SPRING, WIRE);
        tools(AXE, CUTTER, FILE, HAMMER, HOE, KNIFE, MORTAR, PICKAXE, SAW, SCREWDRIVER, SHOVEL, SWORD, WRENCH);
        blocks(BLOCK, FRAME, HULL, ORE);
        fluids(LIQUID);
        return this;
    }

    @ZenMethod
    public SubstanceBuilder setAlkaliMetal() {
        items(INGOT, NUGGET, PLATE, CASING, DUST, FOIL, GEAR, RING, ROD, ROTOR, SCREW, SPRING, WIRE);
        tools(AXE, CUTTER, FILE, HAMMER, HOE, KNIFE, MORTAR, PICKAXE, SAW, SCREWDRIVER, SHOVEL, SWORD, WRENCH);
        blocks(BLOCK, FRAME, HULL, ORE);
        fluids(LIQUID);
        return this;
    }

    @ZenMethod
    public SubstanceBuilder setAlkalineEarthMetal() {
        items(INGOT, NUGGET, PLATE, CASING, DUST, FOIL, GEAR, RING, ROD, ROTOR, SCREW, SPRING, WIRE);
        tools(AXE, CUTTER, FILE, HAMMER, HOE, KNIFE, MORTAR, PICKAXE, SAW, SCREWDRIVER, SHOVEL, SWORD, WRENCH);
        blocks(BLOCK, FRAME, HULL, ORE);
        fluids(LIQUID);
        return this;
    }

    @ZenMethod
    public SubstanceBuilder setMetalloid() {
        items(INGOT, NUGGET, PLATE, CASING, DUST, FOIL, GEAR, RING, ROD, ROTOR, SCREW, SPRING, WIRE);
        tools(AXE, CUTTER, FILE, HAMMER, HOE, KNIFE, MORTAR, PICKAXE, SAW, SCREWDRIVER, SHOVEL, SWORD, WRENCH);
        blocks(BLOCK, FRAME, HULL, ORE);
        fluids(LIQUID);
        return this;
    }

    @ZenMethod
    public SubstanceBuilder setNonMetal() {
        items(INGOT, NUGGET, PLATE, CASING, DUST, FOIL, GEAR, RING, ROD, ROTOR, SCREW, SPRING, WIRE);
        tools(AXE, CUTTER, FILE, HAMMER, HOE, KNIFE, MORTAR, PICKAXE, SAW, SCREWDRIVER, SHOVEL, SWORD, WRENCH);
        blocks(BLOCK, FRAME, HULL, ORE);
        fluids(LIQUID);
        return this;
    }

    @ZenMethod
    public SubstanceBuilder setPostTransitionMetal() {
        items(INGOT, NUGGET, PLATE, CASING, DUST, FOIL, GEAR, RING, ROD, ROTOR, SCREW, SPRING, WIRE);
        tools(AXE, CUTTER, FILE, HAMMER, HOE, KNIFE, MORTAR, PICKAXE, SAW, SCREWDRIVER, SHOVEL, SWORD, WRENCH);
        blocks(BLOCK, FRAME, HULL, ORE);
        fluids(LIQUID);
        return this;
    }

    @ZenMethod
    public SubstanceBuilder setTransitionMetal() {
        items(INGOT, NUGGET, PLATE, CASING, DUST, FOIL, GEAR, RING, ROD, ROTOR, SCREW, SPRING, WIRE);
        tools(AXE, CUTTER, FILE, HAMMER, HOE, KNIFE, MORTAR, PICKAXE, SAW, SCREWDRIVER, SHOVEL, SWORD, WRENCH);
        blocks(BLOCK, FRAME, HULL, ORE);
        fluids(LIQUID);
        return this;
    }

    @ZenMethod
    public SubstanceBuilder setLanthanide() {
        items(INGOT, NUGGET, PLATE, CASING, DUST, FOIL, GEAR, RING, ROD, ROTOR, SCREW, SPRING, WIRE);
        tools(AXE, CUTTER, FILE, HAMMER, HOE, KNIFE, MORTAR, PICKAXE, SAW, SCREWDRIVER, SHOVEL, SWORD, WRENCH);
        blocks(BLOCK, FRAME, HULL, ORE);
        fluids(LIQUID);
        return this;
    }

    @ZenMethod
    public SubstanceBuilder setActinide() {
        items(INGOT, NUGGET, PLATE, CASING, DUST, FOIL, GEAR, RING, ROD, ROTOR, SCREW, SPRING, WIRE);
        tools(AXE, CUTTER, FILE, HAMMER, HOE, KNIFE, MORTAR, PICKAXE, SAW, SCREWDRIVER, SHOVEL, SWORD, WRENCH);
        blocks(BLOCK, FRAME, HULL, ORE);
        fluids(LIQUID);
        return this;
    }

    @ZenMethod
    public SubstanceBuilder setUnknown() {
        items(INGOT, NUGGET, PLATE, CASING, DUST, FOIL, GEAR, RING, ROD, ROTOR, SCREW, SPRING, WIRE);
        tools(AXE, CUTTER, FILE, HAMMER, HOE, KNIFE, MORTAR, PICKAXE, SAW, SCREWDRIVER, SHOVEL, SWORD, WRENCH);
        blocks(BLOCK, FRAME, HULL, ORE);
        fluids(LIQUID);
        return this;
    }

    @ZenMethod
    public SubstanceBuilder items(String... items) {
        for (String item : items) {
            if (SUBSTANCE_ITEMS_REGISTRY.contains(item))
                this.items(SUBSTANCE_ITEMS_REGISTRY.get(item));
            else CraftTweakerAPI.logError("This type of item does not exists: " + item);
        }

        return this;
    }

    public SubstanceBuilder items(SubstanceItem... items) {
        this.items.addAll(Arrays.asList(items));
        return this;
    }

    @ZenMethod
    public SubstanceBuilder tools(String... tools) {
        for (String tool : tools) {
            if (SUBSTANCE_TOOLS_REGISTRY.contains(tool))
                this.tools(SUBSTANCE_TOOLS_REGISTRY.get(tool));
            else CraftTweakerAPI.logError("This type of tool does not exists: " + tool);
        }

        return this;
    }

    public SubstanceBuilder tools(SubstanceTool... tools) {
        this.tools.addAll(Arrays.asList(tools));
        return this;
    }

    @ZenMethod
    public SubstanceBuilder blocks(String... blocks) {
        for (String block : blocks) {
            if (SUBSTANCE_BLOCKS_REGISTRY.contains(block))
                this.blocks(SUBSTANCE_BLOCKS_REGISTRY.get(block));
            else CraftTweakerAPI.logError("This type of block does not exists: " + block);
        }

        return this;
    }

    public SubstanceBuilder blocks(SubstanceBlock... blocks) {
        this.blocks.addAll(Arrays.asList(blocks));
        return this;
    }

    @ZenMethod
    public SubstanceBuilder fluids(String... fluids) {
        for (String fluid : fluids) {
            if (SUBSTANCE_FLUIDS_REGISTRY.contains(fluid))
                this.fluids(SUBSTANCE_FLUIDS_REGISTRY.get(fluid));
            else CraftTweakerAPI.logError("This type of fluid does not exists: " + fluid);
        }

        return this;
    }

    public SubstanceBuilder fluids(SubstanceFluid... fluids) {
        this.fluids.addAll(Arrays.asList(fluids));
        return this;
    }

    @ZenMethod
    public SubstanceBuilder color(int color) {
        this.color(color, color, color);
        return this;
    }

    @ZenMethod
    public SubstanceBuilder color(int baseColor, int oreColor, int fluidColor) {
        this.baseColor = baseColor;
        this.oreColor = oreColor;
        this.fluidColor = fluidColor;
        return this;
    }

    @ZenMethod
    public SubstanceBuilder shiny() {
        this.shiny = !this.shiny;
        return this;
    }

    @ZenMethod
    public SubstanceBuilder glow() {
        this.glow = !this.glow;
        return this;
    }

    @ZenMethod
    public SubstanceBuilder style(String style) {
        this.style = style;
        return this;
    }

    @ZenMethod
    public SubstanceBuilder sound(String soundName) {
        switch (soundName) {
            case "metal" -> this.soundType = SoundType.METAL;
            case "wood" -> this.soundType = SoundType.WOOD;
            case "stone" -> this.soundType = SoundType.STONE;
            case "sand" -> this.soundType = SoundType.SAND;
            default -> CraftTweakerAPI.logError("This type of sound does not exists: " + soundName);
        }

        return this;
    }

    @ZenMethod
    public Substance build() {
        Preconditions.checkArgument((composition != null && element == null) || (composition == null && possible == null && element != null));
        Composition composition1 = element != null ? new Composition(element) : new Composition(composition, possible != null ? possible : ImmutableSet.of());

        Substance substance = new Substance(name, composition1, efficiency, toughness, temperature, new Aestheticism(style, shiny, glow, baseColor, oreColor, fluidColor, soundType), process, ImmutableMap.copyOf(properties), ImmutableSortedSet.copyOf(items), efficiency == null ? ImmutableSet.of() : ImmutableSortedSet.copyOf(tools), ImmutableSortedSet.copyOf(blocks), ImmutableSortedSet.copyOf(fluids), ImmutableMap.copyOf(overrides));

        if (efficiency != null) {
            /*
             * .setRepairItem(INGOT.asItemStack(substance)); isn't called anywhere because the ingot.getOre(sub) is used to get the repair material.
             */
            EnumHelper.addToolMaterial(name, efficiency.getHarvestLevel(), efficiency.getDurability(), efficiency.getSpeed(), efficiency.getDamage(), efficiency.getEnchantability());
        }

        SUBSTANCES_REGISTRY.put(name, substance);
        return substance;
    }
}
