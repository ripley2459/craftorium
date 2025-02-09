package fr.cyrilneveu.craftorium.api.substance;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSortedSet;
import fr.cyrilneveu.craftorium.api.machine.Machine;
import fr.cyrilneveu.craftorium.api.property.Aestheticism;
import fr.cyrilneveu.craftorium.api.property.Efficiency;
import fr.cyrilneveu.craftorium.api.property.Temperature;
import fr.cyrilneveu.craftorium.api.property.Toughness;
import fr.cyrilneveu.craftorium.api.recipe.process.AProcess;
import fr.cyrilneveu.craftorium.api.substance.object.ASubstanceObject;
import fr.cyrilneveu.craftorium.api.substance.property.Composition;
import fr.cyrilneveu.craftorium.api.substance.property.ISubstanceProperty;
import fr.cyrilneveu.craftorium.api.substance.property.SubstanceProperties;
import net.minecraft.block.SoundType;
import net.minecraftforge.common.util.EnumHelper;

import javax.annotation.Nullable;
import java.util.*;

import static fr.cyrilneveu.craftorium.api.Registries.SUBSTANCES_REGISTRY;
import static fr.cyrilneveu.craftorium.api.Registries.TIERS_REGISTRY;
import static fr.cyrilneveu.craftorium.api.substance.property.SubstanceProperties.KeyProperties.FUEL;
import static fr.cyrilneveu.craftorium.api.utils.RenderUtils.ERROR_COLOR;
import static fr.cyrilneveu.craftorium.api.utils.Utils.EPSILON;
import static fr.cyrilneveu.craftorium.common.machine.Machines.*;
import static fr.cyrilneveu.craftorium.common.recipe.Processes.DEFAULT_PROCESS;
import static fr.cyrilneveu.craftorium.common.substance.SubstancesObjects.*;

public class SubstanceBuilder {
    private String name;
    private Set<SubstanceStack> composition = new LinkedHashSet<>();
    private Set<SubstanceStack> possible = new LinkedHashSet<>();
    @Nullable
    private Element element;
    @Nullable
    private Efficiency efficiency;
    private Toughness toughness = new Toughness(5.0f, 10.0f, "pickaxe", 2);
    private Temperature temperature = Temperature.EMPTY;
    private AProcess process = DEFAULT_PROCESS;
    private Map<SubstanceProperties.KeyProperties, ISubstanceProperty> properties = new HashMap<>();
    private Set<ASubstanceObject.SubstanceItemDefinition> items = new TreeSet<>();
    private Set<ASubstanceObject.SubstanceToolDefinition> tools = new TreeSet<>();
    private Set<ASubstanceObject.SubstanceBlockDefinition> blocks = new TreeSet<>();
    private Set<ASubstanceObject.SubstanceFluidDefinition> fluids = new TreeSet<>();
    private Map<ASubstanceObject, String> overrides = new HashMap<>();
    private String style = "metal";
    private boolean shiny = false;
    private boolean glint = false;
    private int baseColor = ERROR_COLOR;
    private int oreColor = ERROR_COLOR;
    private int fluidColor = ERROR_COLOR;
    private SoundType soundType = SoundType.METAL;
    private boolean isTier = false;
    private int simultaneous = 1;
    private int chance = 0;
    private float recipeSpeedMultiplier = 1.0f;
    private float tankSize = 1.0f;
    private float energyBuffer = 1.0f;
    private float energyIO = 1.0f;
    private Set<Machine> machines = new TreeSet<>();
    private Substance carcass, mechanical, energy, fluid, heat;

    public SubstanceBuilder(String name) {
        this.name = name;
    }

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

    public SubstanceBuilder element(int atomicNumber, String symbol, String name, Element.EGroup group, double atomicMass) {
        this.element = new Element(atomicNumber, symbol, name, group, atomicMass);
        this.composition = null;
        this.possible = null;
        return this;
    }

    public SubstanceBuilder property(SubstanceProperties.KeyProperties key, ISubstanceProperty value) {
        properties.put(key, value);
        return this;
    }

    public SubstanceBuilder recipe(AProcess process) {
        this.process = process;
        return this;
    }

    @Deprecated
    public SubstanceBuilder tools(float speed, float damage, int durability, int harvestLevel, int enchantability) {
        this.efficiency = new Efficiency(speed, damage, durability, harvestLevel, enchantability);
        return this;
    }

    public SubstanceBuilder tools(int harvestLevel, int durability, float speed, float damage, int enchantability) {
        this.efficiency = new Efficiency(harvestLevel, durability, speed, damage, enchantability);
        return this;
    }

    public SubstanceBuilder toughness(float hardness, float resistance, String toolClass, int harvestLevel) {
        this.toughness = new Toughness(hardness, resistance, toolClass, harvestLevel);
        return this;
    }

    public SubstanceBuilder temperature(float meltingPoint, float boilingPoint) {
        this.temperature = new Temperature(meltingPoint, boilingPoint);
        return this;
    }

    public SubstanceBuilder temperatureAverage() {
        if (composition.isEmpty())
            return this;

        float melt = 0;
        float boil = 0;
        int total = 0;
        for (SubstanceStack stack : composition) {
            Temperature temperature = stack.getSubstance().getTemperature();
            if (temperature == Temperature.EMPTY)
                return this;

            melt += temperature.getMeltingPoint() * stack.getAmount();
            boil += temperature.getBoilingPoint() * stack.getAmount();
            total += stack.getAmount();
        }

        return this.temperature(melt / total, boil / total);
    }

    public SubstanceBuilder veinMember() {
        items(DUST);
        blocks(ORE);
        return property(SubstanceProperties.KeyProperties.VEIN_MEMBER, SubstanceProperties.VEIN_MEMBER_PROPERTY);
    }

    public SubstanceBuilder packageHalogen() {
        items(DUST);
        fluids(LIQUID);
        return this;
    }

    public SubstanceBuilder packageNobleGas() {
        items(DUST);
        fluids(LIQUID);
        return this;
    }

    public SubstanceBuilder packageAlkaliMetal() {
        items(DUST, INGOT, NUGGET);
        blocks(BLOCK);
        fluids(LIQUID);
        return this;
    }

    public SubstanceBuilder packageAlkalineEarthMetal() {
        items(DUST, INGOT, NUGGET);
        blocks(BLOCK);
        fluids(LIQUID);
        return this;
    }

    public SubstanceBuilder packageMetalloid() {
        items(DUST, INGOT, NUGGET);
        blocks(BLOCK);
        fluids(LIQUID);
        return this;
    }

    public SubstanceBuilder packageNonMetal() {
        items(DUST);
        fluids(LIQUID);
        return this;
    }

    public SubstanceBuilder packagePostTransitionMetal() {
        items(CASING, DUST, FOIL, GEAR, INGOT, NUGGET, PLATE, RING, ROD, SCREW, SPRING, WIRE);
        blocks(BLOCK, FRAME, HULL);
        fluids(LIQUID);
        return this;
    }

    public SubstanceBuilder packageTransitionMetal() {
        items(CASING, DUST, FOIL, GEAR, INGOT, NUGGET, PLATE, RING, ROD, SCREW, SPRING, WIRE);
        tools(AXE, CUTTER, FILE, HAMMER, HOE, KNIFE, MORTAR, PICKAXE, SAW, SCREWDRIVER, SHOVEL, SWORD, WRENCH);
        blocks(BLOCK, FRAME, HULL);
        fluids(LIQUID);
        return this;
    }

    public SubstanceBuilder packageLanthanide() {
        items(DUST, INGOT, NUGGET, PLATE, ROD);
        blocks(BLOCK);
        fluids(LIQUID);
        return this;
    }

    public SubstanceBuilder packageActinide() {
        items(DUST, INGOT, NUGGET, PLATE, ROD);
        blocks(BLOCK);
        fluids(LIQUID);
        return this;
    }

    public SubstanceBuilder packageMetalExtended() {
        items(CASING, DUST, FOIL, GEAR, INGOT, NUGGET, PLATE, RING, ROD, ROTOR, SCREW, SPRING, WIRE);
        tools(AXE, CUTTER, FILE, HAMMER, HOE, KNIFE, MORTAR, PICKAXE, SAW, SCREWDRIVER, SHOVEL, SWORD, WRENCH);
        blocks(BLOCK, FRAME, HULL);
        fluids(LIQUID);
        return this;
    }

    public SubstanceBuilder packageGem() {
        items(DUST, GEM, NUGGET, PLATE, ROD);
        tools(AXE, FILE, HAMMER, HOE, KNIFE, MORTAR, PICKAXE, SAW, SHOVEL, SWORD, WRENCH);
        blocks(BLOCK);
        fluids(LIQUID);
        return this;
    }

    public SubstanceBuilder packageMineral() {
        items(DUST, PLATE, ROD);
        blocks(BLOCK);
        fluids(LIQUID);
        return this;
    }

    public SubstanceBuilder packageTier() {
        if (isTier) {
            items(BATTERY, BUZZSAW, EMITTER, GRINDER, HEAT_EXCHANGER, MOTOR, PISTON, PUMP, ROBOT_ARM, SCANNER, SENSOR);
            blocks(MACHINE_FRAME);
            machines(ELECTROLYZER, MACERATOR, BENDER, LATHE, M_CUTTER, COMPRESSOR, FOUNDRY, MIXER, CIRCUIT_ASSEMBLER, ASSEMBLER);
        }
        return this;
    }

    public SubstanceBuilder items(ASubstanceObject.SubstanceItemDefinition... items) {
        if (items.length == 0)
            this.items = new TreeSet<>();
        this.items.addAll(Arrays.asList(items));
        return this;
    }

    public SubstanceBuilder tools(ASubstanceObject.SubstanceToolDefinition... tools) {
        if (tools.length == 0)
            this.tools = new TreeSet<>();
        this.tools.addAll(Arrays.asList(tools));
        return this;
    }

    public SubstanceBuilder blocks(ASubstanceObject.SubstanceBlockDefinition... blocks) {
        if (blocks.length == 0)
            this.blocks = new TreeSet<>();
        this.blocks.addAll(Arrays.asList(blocks));
        return this;
    }

    public SubstanceBuilder fluids(ASubstanceObject.SubstanceFluidDefinition... fluids) {
        if (fluids.length == 0)
            this.fluids = new TreeSet<>();
        this.fluids.addAll(Arrays.asList(fluids));
        return this;
    }

    public SubstanceBuilder fuel(int duration) {
        if (duration > 0)
            this.property(FUEL, new SubstanceProperties.FuelProperty(duration));
        return this;
    }

    public SubstanceBuilder colorAverage() {
        if (composition.isEmpty())
            return this.color(ERROR_COLOR);

        int color = 0;
        int total = 0;
        for (SubstanceStack stack : composition) {
            color += stack.getSubstance().getAestheticism().getBaseColor() * stack.getAmount();
            total += stack.getAmount();
        }

        return this.color(color / total);
    }

    public SubstanceBuilder color(int color) {
        this.color(color, color, color);
        return this;
    }

    public SubstanceBuilder color(int baseColor, int oreColor, int fluidColor) {
        this.baseColor = baseColor;
        this.oreColor = oreColor;
        this.fluidColor = fluidColor;
        return this;
    }

    public SubstanceBuilder shiny() {
        this.shiny = !this.shiny;
        return this;
    }

    public SubstanceBuilder glint() {
        this.glint = !this.glint;
        return this;
    }

    public SubstanceBuilder style(String style) {
        this.style = style;
        return this;
    }

    public SubstanceBuilder sound(SoundType soundType) {
        this.soundType = soundType;
        return this;
    }

    public SubstanceBuilder overrides(Object... overrides) {
        Preconditions.checkArgument(overrides.length % 2 == 0);

        for (int i = 0; i < overrides.length; i += 2) {
            Preconditions.checkArgument((overrides[i] instanceof ASubstanceObject) && (overrides[i + 1] instanceof String));

            this.overrides.put((ASubstanceObject) overrides[i], (String) overrides[i + 1]);
        }

        return this;
    }

    public SubstanceBuilder isTier() {
        isTier = true;
        return this;
    }

    public SubstanceBuilder fluidStorage(float tankSize) {
        this.tankSize = tankSize;
        return this;
    }

    public SubstanceBuilder energyStorage(float energyBuffer, float energyIO) {
        this.energyBuffer = energyBuffer;
        this.energyIO = energyIO;
        return this;
    }

    public SubstanceBuilder simultaneousRecipe(int simultaneous) {
        this.simultaneous = Math.min(1, simultaneous);
        return this;
    }

    public SubstanceBuilder additionalChance(int chance) {
        this.chance = Math.min(0, chance);
        return this;
    }

    public SubstanceBuilder recipeSpeedMultiplier(float recipeSpeedMultiplier) {
        this.recipeSpeedMultiplier = Math.max(EPSILON, recipeSpeedMultiplier);
        return this;
    }

    public SubstanceBuilder machines(Machine... machines) {
        if (machines.length == 0)
            this.machines = new TreeSet<>();
        this.machines.addAll(Arrays.asList(machines));
        return this;
    }

    public SubstanceBuilder pack(Substance carcass, Substance mechanical, Substance energy, Substance fluid, Substance heat) {
        this.carcass = carcass;
        this.mechanical = mechanical;
        this.energy = energy;
        this.fluid = fluid;
        this.heat = heat;
        return this;
    }

    public Substance build() {
        Preconditions.checkArgument((composition != null && element == null) || (composition == null && possible == null && element != null));

        Composition composition1 = element != null ? new Composition(element) : new Composition(composition, possible != null ? possible : ImmutableSet.of());

        if (baseColor == ERROR_COLOR)
            colorAverage();
        if (temperature == Temperature.EMPTY)
            temperatureAverage();
        Aestheticism.SubstanceAestheticism aestheticism1 = new Aestheticism.SubstanceAestheticism(style, shiny, glint, baseColor, oreColor, fluidColor, soundType);

        if (efficiency != null) {
            EnumHelper.addToolMaterial(name, efficiency.getHarvestLevel(), efficiency.getDurability(), efficiency.getSpeed(), efficiency.getDamage(), efficiency.getEnchantability());
            // .setRepairItem(INGOT.asItemStack(substance)); isn't called anywhere because the ingot.getOre(sub) is used to get the repair material.
        }

        if (isTier) {
            Tier tier = new Tier(name, composition1, efficiency, toughness, temperature, aestheticism1, process, ImmutableMap.copyOf(properties),
                    ImmutableSortedSet.copyOf(items),
                    efficiency == null ? ImmutableSet.of() : ImmutableSortedSet.copyOf(tools),
                    ImmutableSortedSet.copyOf(blocks),
                    ImmutableSortedSet.copyOf(fluids),
                    ImmutableMap.copyOf(overrides),
                    simultaneous, chance, recipeSpeedMultiplier, tankSize, energyBuffer, energyIO, machines, carcass, mechanical, energy, fluid, heat);
            TIERS_REGISTRY.put(name, tier);
            SUBSTANCES_REGISTRY.put(name, tier);
            return tier;
        }

        Substance substance = new Substance(name, composition1, efficiency, toughness, temperature, aestheticism1, process, ImmutableMap.copyOf(properties), ImmutableSortedSet.copyOf(items), efficiency == null ? ImmutableSet.of() : ImmutableSortedSet.copyOf(tools), ImmutableSortedSet.copyOf(blocks), ImmutableSortedSet.copyOf(fluids), ImmutableMap.copyOf(overrides));
        SUBSTANCES_REGISTRY.put(name, substance);
        return substance;
    }
}
