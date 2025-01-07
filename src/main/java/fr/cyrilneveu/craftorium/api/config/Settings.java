package fr.cyrilneveu.craftorium.api.config;

import net.minecraftforge.common.config.Config;

import static fr.cyrilneveu.craftorium.CraftoriumTags.MODID;

@Config(modid = MODID)
public final class Settings {
    @Config.Name("Global settings")
    @Config.Comment("Global settings.")
    @Config.RequiresMcRestart
    public static final GlobalsSettings globalSettings = new GlobalsSettings();
    @Config.Name("Substances settings")
    @Config.Comment("Config settings for substances related features.")
    @Config.RequiresMcRestart
    public static final SubstancesSettings substancesSettings = new SubstancesSettings();
    @Config.Name("Generation settings")
    @Config.Comment("Settings for world generation related features.")
    @Config.RequiresMcRestart
    public static final GenerationSettings generationSettings = new GenerationSettings();
    @Config.Name("Machines")
    @Config.Comment("Machines settings.")
    @Config.RequiresMcRestart
    public static MachinesSettings machinesSettings = new MachinesSettings();

    public static class GlobalsSettings {
        @Config.Comment({"Show tooltips on substances items? They are partially implemented.", "Default: false"})
        public boolean showAdvancedTooltips = false;
    }

    public static class MachinesSettings {
        @Config.Comment({"Amount of energy a machine can store. Increased by tier.", "Default: 5000"})
        public int machineBaseStorage = 50000;
        @Config.Comment({"Amount of energy a machine can transfer (i/o). Increased by tier.", "Default: 5000"})
        public int machineBaseTransfer = 5000;
        @Config.Comment({"Amount of energy a battery can store. Increased by tier.", "Default: 5000"})
        public int batteryBaseStorage = 5000;
        @Config.Comment({"Amount of energy a battery can transfer (i/o). Increased by tier.", "Default: 5000"})
        public int batteryBaseTransfer = 500;
        @Config.Comment({"Do the machines make sounds when they work?", "Default: true"})
        public boolean playSounds = true;
        @Config.Comment({"Do machines emit particles when they work?", "Default: true"})
        public boolean spawnParticles = true;
    }

    public static final class SubstancesSettings {
        @Config.Comment({"Should substances that overrides objects (like items or blocks) register their own objects?", "Default: false"})
        public boolean registerOverrides = false;
    }

    public static final class GenerationSettings {
        @Config.Comment({"Enable vanilla ore generation.", "Default: false"})
        public boolean enableVanillaOreGeneration = false;
        @Config.Comment({"Enable Craftorium ore generation.", "Default: true"})
        public boolean enableOreGeneration = true;
        @Config.Comment({"Approximate amount of ore vein per chunk.", "Default: 8"})
        public int veinDensity = 8;
    }
}
