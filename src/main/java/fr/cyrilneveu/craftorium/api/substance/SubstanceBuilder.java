package fr.cyrilneveu.craftorium.api.substance;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSortedSet;
import fr.cyrilneveu.craftorium.api.property.Aestheticism;
import fr.cyrilneveu.craftorium.api.property.Efficiency;
import fr.cyrilneveu.craftorium.api.property.Temperature;
import fr.cyrilneveu.craftorium.api.property.Toughness;
import fr.cyrilneveu.craftorium.api.substance.object.*;
import fr.cyrilneveu.craftorium.api.substance.property.ASubstanceProperty;
import fr.cyrilneveu.craftorium.api.substance.property.Composition;
import fr.cyrilneveu.craftorium.api.substance.property.ESubstanceProperties;
import stanhebben.zenscript.annotations.ZenMethod;

import javax.annotation.Nullable;
import java.util.*;

import static fr.cyrilneveu.craftorium.api.utils.Utils.ERROR_COLOR;
import static fr.cyrilneveu.craftorium.common.substance.Substances.SUBSTANCES_REGISTRY;
import static fr.cyrilneveu.craftorium.common.substance.SubstancesObjects.*;

public final class SubstanceBuilder {
    private String name;
    private Set<SubstanceStack> composition, chanced;
    @Nullable
    private Element element;
    @Nullable
    private Efficiency efficiency;
    private Toughness toughness = new Toughness(5.0f, 10.0f, "pickaxe", 2);
    private Temperature temperature = Temperature.EMPTY;
    private Map<ESubstanceProperties, ASubstanceProperty> properties = new HashMap<>();
    private Set<SubstanceItem> items = new TreeSet<>();
    private Set<SubstanceTool> tools = new TreeSet<>();
    private Set<SubstanceBlock> blocks = new TreeSet<>();
    private Set<SubstanceFluid> fluids = new TreeSet<>();
    private Map<ASubstanceObject, String> overrides = new HashMap<>();
    private String style = "metal";
    private boolean shiny, glow;
    private int baseColor, oreColor, fluidColor = ERROR_COLOR;

    public SubstanceBuilder(String name) {
        this.name = name;
    }

    @ZenMethod
    public SubstanceBuilder element(int atomicNumber, String symbol, String name, Element.EGroup group, double atomicMass) {
        this.element = new Element(atomicNumber, symbol, name, group, atomicMass);
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

    public SubstanceBuilder setHalogen() {
        items(INGOT, NUGGET, PLATE, CASING, DUST, FOIL, GEAR, RING, ROD, ROTOR, SCREW, SPRING, WIRE);
        tools(AXE, CUTTER, FILE, HAMMER, HOE, KNIFE, MORTAR, PICKAXE, SAW, SCREWDRIVER, SHOVEL, SWORD, WRENCH);
        blocks(BLOCK, FRAME, HULL, ORE);
        fluids(LIQUID);
        return this;
    }

    public SubstanceBuilder setNobleGas() {
        items(INGOT, NUGGET, PLATE, CASING, DUST, FOIL, GEAR, RING, ROD, ROTOR, SCREW, SPRING, WIRE);
        tools(AXE, CUTTER, FILE, HAMMER, HOE, KNIFE, MORTAR, PICKAXE, SAW, SCREWDRIVER, SHOVEL, SWORD, WRENCH);
        blocks(BLOCK, FRAME, HULL, ORE);
        fluids(LIQUID);
        return this;
    }

    public SubstanceBuilder setAlkaliMetal() {
        items(INGOT, NUGGET, PLATE, CASING, DUST, FOIL, GEAR, RING, ROD, ROTOR, SCREW, SPRING, WIRE);
        tools(AXE, CUTTER, FILE, HAMMER, HOE, KNIFE, MORTAR, PICKAXE, SAW, SCREWDRIVER, SHOVEL, SWORD, WRENCH);
        blocks(BLOCK, FRAME, HULL, ORE);
        fluids(LIQUID);
        return this;
    }

    public SubstanceBuilder setAlkalineEarthMetal() {
        items(INGOT, NUGGET, PLATE, CASING, DUST, FOIL, GEAR, RING, ROD, ROTOR, SCREW, SPRING, WIRE);
        tools(AXE, CUTTER, FILE, HAMMER, HOE, KNIFE, MORTAR, PICKAXE, SAW, SCREWDRIVER, SHOVEL, SWORD, WRENCH);
        blocks(BLOCK, FRAME, HULL, ORE);
        fluids(LIQUID);
        return this;
    }

    public SubstanceBuilder setMetalloid() {
        items(INGOT, NUGGET, PLATE, CASING, DUST, FOIL, GEAR, RING, ROD, ROTOR, SCREW, SPRING, WIRE);
        tools(AXE, CUTTER, FILE, HAMMER, HOE, KNIFE, MORTAR, PICKAXE, SAW, SCREWDRIVER, SHOVEL, SWORD, WRENCH);
        blocks(BLOCK, FRAME, HULL, ORE);
        fluids(LIQUID);
        return this;
    }

    public SubstanceBuilder setNonMetal() {
        items(INGOT, NUGGET, PLATE, CASING, DUST, FOIL, GEAR, RING, ROD, ROTOR, SCREW, SPRING, WIRE);
        tools(AXE, CUTTER, FILE, HAMMER, HOE, KNIFE, MORTAR, PICKAXE, SAW, SCREWDRIVER, SHOVEL, SWORD, WRENCH);
        blocks(BLOCK, FRAME, HULL, ORE);
        fluids(LIQUID);
        return this;
    }

    public SubstanceBuilder setPostTransitionMetal() {
        items(INGOT, NUGGET, PLATE, CASING, DUST, FOIL, GEAR, RING, ROD, ROTOR, SCREW, SPRING, WIRE);
        tools(AXE, CUTTER, FILE, HAMMER, HOE, KNIFE, MORTAR, PICKAXE, SAW, SCREWDRIVER, SHOVEL, SWORD, WRENCH);
        blocks(BLOCK, FRAME, HULL, ORE);
        fluids(LIQUID);
        return this;
    }

    public SubstanceBuilder setTransitionMetal() {
        items(INGOT, NUGGET, PLATE, CASING, DUST, FOIL, GEAR, RING, ROD, ROTOR, SCREW, SPRING, WIRE);
        tools(AXE, CUTTER, FILE, HAMMER, HOE, KNIFE, MORTAR, PICKAXE, SAW, SCREWDRIVER, SHOVEL, SWORD, WRENCH);
        blocks(BLOCK, FRAME, HULL, ORE);
        fluids(LIQUID);
        return this;
    }

    public SubstanceBuilder setLanthanide() {
        items(INGOT, NUGGET, PLATE, CASING, DUST, FOIL, GEAR, RING, ROD, ROTOR, SCREW, SPRING, WIRE);
        tools(AXE, CUTTER, FILE, HAMMER, HOE, KNIFE, MORTAR, PICKAXE, SAW, SCREWDRIVER, SHOVEL, SWORD, WRENCH);
        blocks(BLOCK, FRAME, HULL, ORE);
        fluids(LIQUID);
        return this;
    }

    public SubstanceBuilder setActinide() {
        items(INGOT, NUGGET, PLATE, CASING, DUST, FOIL, GEAR, RING, ROD, ROTOR, SCREW, SPRING, WIRE);
        tools(AXE, CUTTER, FILE, HAMMER, HOE, KNIFE, MORTAR, PICKAXE, SAW, SCREWDRIVER, SHOVEL, SWORD, WRENCH);
        blocks(BLOCK, FRAME, HULL, ORE);
        fluids(LIQUID);
        return this;
    }

    public SubstanceBuilder setUnknown() {
        items(INGOT, NUGGET, PLATE, CASING, DUST, FOIL, GEAR, RING, ROD, ROTOR, SCREW, SPRING, WIRE);
        tools(AXE, CUTTER, FILE, HAMMER, HOE, KNIFE, MORTAR, PICKAXE, SAW, SCREWDRIVER, SHOVEL, SWORD, WRENCH);
        blocks(BLOCK, FRAME, HULL, ORE);
        fluids(LIQUID);
        return this;
    }

    public SubstanceBuilder items(SubstanceItem... items) {
        this.items.addAll(Arrays.asList(items));
        return this;
    }

    public SubstanceBuilder tools(SubstanceTool... tools) {
        this.tools.addAll(Arrays.asList(tools));
        return this;
    }

    public SubstanceBuilder blocks(SubstanceBlock... blocks) {
        this.blocks.addAll(Arrays.asList(blocks));
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
    public Substance build() {
        Preconditions.checkArgument((composition != null && chanced != null && element == null) || (composition == null && chanced == null && element != null));
        Composition composition1 = element != null ? new Composition(element) : new Composition(composition, chanced);

        Substance substance = new Substance(name, composition1, efficiency, toughness, temperature, new Aestheticism(style, shiny, glow, baseColor, oreColor, fluidColor), ImmutableMap.copyOf(properties), ImmutableSortedSet.copyOf(items), ImmutableSortedSet.copyOf(tools), ImmutableSortedSet.copyOf(blocks), ImmutableSortedSet.copyOf(fluids), ImmutableMap.copyOf(overrides));
        SUBSTANCES_REGISTRY.put(name, substance);
        return substance;
    }
}
