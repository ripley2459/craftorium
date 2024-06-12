package fr.cyrilneveu.craftorium.api.tier;

import com.google.common.collect.ImmutableSortedSet;
import crafttweaker.CraftTweakerAPI;
import crafttweaker.annotations.ZenRegister;
import fr.cyrilneveu.craftorium.api.machine.Machine;
import fr.cyrilneveu.craftorium.api.property.Aestheticism;
import fr.cyrilneveu.craftorium.api.substance.Substance;
import fr.cyrilneveu.craftorium.api.tier.object.ATierObject;
import fr.cyrilneveu.craftorium.api.tier.property.Pack;
import fr.cyrilneveu.craftorium.api.tier.property.Process;
import fr.cyrilneveu.craftorium.api.tier.property.Storage;
import net.minecraft.block.SoundType;
import org.jetbrains.annotations.Nullable;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

import static fr.cyrilneveu.craftorium.CraftoriumTags.MODID;
import static fr.cyrilneveu.craftorium.api.Registries.*;
import static fr.cyrilneveu.craftorium.api.utils.RenderUtils.WHITE_COLOR;
import static fr.cyrilneveu.craftorium.api.utils.Utils.EPSILON;
import static fr.cyrilneveu.craftorium.common.machine.Machines.*;
import static fr.cyrilneveu.craftorium.common.tier.TiersObjects.*;

@ZenClass("mods." + MODID + ".tier.Builder")
@ZenRegister
public final class TierBuilder {
    private String name;
    private Aestheticism.SubstanceAestheticism aestheticism = new Aestheticism.SubstanceAestheticism("default", false, false, WHITE_COLOR, SoundType.METAL);
    @Nullable
    private Pack pack;
    private int simultaneousRecipe = 1;
    private int additionalChance = 0;
    private float recipeSpeed = 1f;
    private Storage storage = new Storage(1f);
    private Set<ATierObject.TierItem> items = new TreeSet<>();
    private Set<Machine> machines = new TreeSet<>();

    public TierBuilder(String name) {
        this.name = name;
    }

    @ZenMethod
    public TierBuilder color(int color) {
        this.aestheticism = new Aestheticism.SubstanceAestheticism(this.aestheticism.getStyle(), false, false, color, SoundType.METAL);
        return this;
    }

    @ZenMethod
    public TierBuilder style(String style) {
        this.aestheticism = new Aestheticism.SubstanceAestheticism(style, false, false, this.aestheticism.getBaseColor(), SoundType.METAL);
        return this;
    }

    @ZenMethod
    public TierBuilder storage(float multiplier) {
        this.storage = new Storage(multiplier);
        return this;
    }

    @ZenMethod
    public TierBuilder fluidStorage(float tankSize) {
        this.storage = new Storage(tankSize, this.storage.getEnergyBuffer(), this.storage.getEnergyIO());
        return this;
    }

    @ZenMethod
    public TierBuilder energyStorage(float energySize, float energyIO) {
        this.storage = new Storage(this.storage.getTankSize(), energySize, energyIO);
        return this;
    }

    @ZenMethod
    public TierBuilder simultaneousRecipe(int simultaneousRecipe) {
        this.simultaneousRecipe = Math.min(1, simultaneousRecipe);
        return this;
    }

    @ZenMethod
    public TierBuilder additionalChance(int additionalChance) {
        this.additionalChance = Math.min(0, additionalChance);
        return this;
    }

    @ZenMethod
    public TierBuilder recipeSpeed(float multiplier) {
        this.recipeSpeed = Math.max(EPSILON, multiplier);
        return this;
    }

    @ZenMethod
    public TierBuilder pack(Substance carcass, Substance mechanical, Substance energy, Substance fluid, Substance heat) {
        this.pack = new Pack(carcass, mechanical, energy, fluid, heat);
        return this;
    }

    @ZenMethod
    public TierBuilder packageFull() {
        items(BATTERY, BUZZSAW, EMITTER, GRINDER, HEAT_EXCHANGER, MACHINE_FRAME, MOTOR, PISTON, PUMP, ROBOT_ARM, SCANNER, SENSOR);
        machines(ELECTROLYZER, MACERATOR, BENDER, LATHE, CUTTER, COMPRESSOR, FOUNDRY, MIXER, CIRCUIT_ASSEMBLER);
        return this;
    }

    @ZenMethod
    public TierBuilder items(String... items) {
        for (String item : items) {
            if (TIER_ITEMS_REGISTRY.contains(item))
                this.items(TIER_ITEMS_REGISTRY.get(item));
            else CraftTweakerAPI.logError("This type of item does not exists: " + item);
        }

        return this;
    }

    public TierBuilder items(ATierObject.TierItem... items) {
        this.items.addAll(Arrays.asList(items));
        return this;
    }

    @ZenMethod
    public TierBuilder machines(String... machines) {
        for (String machine : machines) {
            if (MACHINES_REGISTRY.contains(machine))
                this.machines(MACHINES_REGISTRY.get(machine));
            else CraftTweakerAPI.logError("This type of machine does not exists: " + machine);
        }

        return this;
    }

    public TierBuilder machines(Machine... machines) {
        this.machines.addAll(Arrays.asList(machines));
        return this;
    }

    @ZenMethod
    public Tier build() {
        Tier tier = new Tier(name, aestheticism, pack, new Process(simultaneousRecipe, additionalChance, recipeSpeed), storage, ImmutableSortedSet.copyOf(items), ImmutableSortedSet.copyOf(machines));

        TIERS_REGISTRY.put(name, tier);

        return tier;
    }
}
