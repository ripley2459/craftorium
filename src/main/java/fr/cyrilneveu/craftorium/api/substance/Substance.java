package fr.cyrilneveu.craftorium.api.substance;

import fr.cyrilneveu.craftorium.api.config.Settings;
import fr.cyrilneveu.craftorium.api.property.Aestheticism;
import fr.cyrilneveu.craftorium.api.property.Efficiency;
import fr.cyrilneveu.craftorium.api.property.Temperature;
import fr.cyrilneveu.craftorium.api.property.Toughness;
import fr.cyrilneveu.craftorium.api.recipe.process.AProcess;
import fr.cyrilneveu.craftorium.api.substance.object.ASubstanceObject;
import fr.cyrilneveu.craftorium.api.substance.property.Composition;
import fr.cyrilneveu.craftorium.api.substance.property.ISubstanceProperty;
import fr.cyrilneveu.craftorium.api.substance.property.SubstanceProperties;
import fr.cyrilneveu.craftorium.api.utils.Utils;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Map;
import java.util.Set;

import static fr.cyrilneveu.craftorium.CraftoriumTags.MODID;

public class Substance implements Comparable<Substance> {
    protected final String name;
    protected final Composition composition;
    @Nullable
    protected final Efficiency efficiency;
    protected final Toughness toughness;
    protected final Temperature temperature;
    protected final Aestheticism.SubstanceAestheticism aestheticism;
    protected final AProcess process;
    protected final Map<SubstanceProperties.KeyProperties, ISubstanceProperty> properties;
    protected final Set<ASubstanceObject.SubstanceItemDefinition> items;
    protected final Set<ASubstanceObject.SubstanceToolDefinition> tools;
    protected final Set<ASubstanceObject.SubstanceBlockDefinition> blocks;
    protected final Set<ASubstanceObject.SubstanceFluidDefinition> fluids;
    protected final Map<ASubstanceObject, String> overrides;

    public Substance(String name, Composition composition, Efficiency efficiency, Toughness toughness, Temperature temperature, Aestheticism.SubstanceAestheticism aestheticism, AProcess process, Map<SubstanceProperties.KeyProperties, ISubstanceProperty> properties, Set<ASubstanceObject.SubstanceItemDefinition> items, Set<ASubstanceObject.SubstanceToolDefinition> tools, Set<ASubstanceObject.SubstanceBlockDefinition> blocks, Set<ASubstanceObject.SubstanceFluidDefinition> fluids, Map<ASubstanceObject, String> overrides) {
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

    public Aestheticism.SubstanceAestheticism getAestheticism() {
        return aestheticism;
    }

    public AProcess getProcess() {
        return process;
    }

    public Map<SubstanceProperties.KeyProperties, ISubstanceProperty> getProperties() {
        return properties;
    }

    public Set<ASubstanceObject.SubstanceItemDefinition> getItems() {
        return items;
    }

    public Set<ASubstanceObject.SubstanceToolDefinition> getTools() {
        return tools;
    }

    public Set<ASubstanceObject.SubstanceBlockDefinition> getBlocks() {
        return blocks;
    }

    public Set<ASubstanceObject.SubstanceFluidDefinition> getFluids() {
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
    public int compareTo(@Nonnull Substance other) {
        return name.compareTo(other.getName());
    }
}
