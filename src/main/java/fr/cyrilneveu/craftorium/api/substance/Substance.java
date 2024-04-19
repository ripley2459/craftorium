package fr.cyrilneveu.craftorium.api.substance;

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
import fr.cyrilneveu.craftorium.api.utils.Utils;
import fr.cyrilneveu.craftorium.common.config.Settings;
import org.jetbrains.annotations.NotNull;
import stanhebben.zenscript.annotations.ZenClass;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.Set;

import static fr.cyrilneveu.craftorium.CraftoriumTags.MODID;

@ZenClass("mods." + MODID + ".substance.Substance")
@ZenRegister
public final class Substance implements Comparable<Substance> {
    private final String name;
    private final Composition composition;
    @Nullable
    private final Efficiency efficiency;
    private final Toughness toughness;
    private final Temperature temperature;
    private final Aestheticism aestheticism;
    private final AProcess process;
    private final Map<ESubstanceProperties, ASubstanceProperty> properties;
    private final Set<SubstanceItem> items;
    private final Set<SubstanceTool> tools;
    private final Set<SubstanceBlock> blocks;
    private final Set<SubstanceFluid> fluids;
    private final Map<ASubstanceObject, String> overrides;

    public Substance(String name, Composition composition, Efficiency efficiency, Toughness toughness, Temperature temperature, Aestheticism aestheticism, AProcess process, Map<ESubstanceProperties, ASubstanceProperty> properties, Set<SubstanceItem> items, Set<SubstanceTool> tools, Set<SubstanceBlock> blocks, Set<SubstanceFluid> fluids, Map<ASubstanceObject, String> overrides) {
        this.name = name;
        this.composition = composition;
        this.efficiency = efficiency;
        this.toughness = toughness;
        this.temperature = temperature;
        this.aestheticism = aestheticism;
        this.process = process;
        this.properties = properties;
        this.items = items;
        this.tools = tools;
        this.blocks = blocks;
        this.fluids = fluids;
        this.overrides = overrides;
    }

    public String getName() {
        return name;
    }

    public Composition getComposition() {
        return composition;
    }

    @Nullable
    public Efficiency getEfficiency() {
        return efficiency;
    }

    public Toughness getToughness() {
        return toughness;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public Aestheticism getAestheticism() {
        return aestheticism;
    }

    public AProcess getProcess() {
        return process;
    }

    public Map<ESubstanceProperties, ASubstanceProperty> getProperties() {
        return properties;
    }

    public Set<SubstanceItem> getItems() {
        return items;
    }

    public Set<SubstanceTool> getTools() {
        return tools;
    }

    public Set<SubstanceBlock> getBlocks() {
        return blocks;
    }

    public Set<SubstanceFluid> getFluids() {
        return fluids;
    }

    public Map<ASubstanceObject, String> getOverrides() {
        return overrides;
    }

    public String getDisplayName() {
        return Utils.localise(getTranslationKey());
    }

    public String getTranslationKey() {
        return String.join(".", "substance", MODID, name, "name");
    }

    public boolean shouldRegister(ASubstanceObject substanceObject) {
        return Settings.substancesSettings.registerOverrides || !overrides.containsKey(substanceObject);
    }

    public boolean shouldUse(ASubstanceObject substanceObject) {
        return !Settings.substancesSettings.registerOverrides && overrides.containsKey(substanceObject);
    }

    @Override
    public int compareTo(@NotNull Substance other) {
        return name.compareTo(other.getName());
    }
}
