package fr.cyrilneveu.craftorium.common.config;

import net.minecraftforge.common.config.Config;

import static fr.cyrilneveu.craftorium.CraftoriumTags.MODID;

@Config(modid = MODID)
public class Settings {
    @Config.Name("Generation settings")
    @Config.Comment("Settings for world generation related features.")
    @Config.RequiresMcRestart
    public static GenerationSettings generationSettings = new GenerationSettings();

    public static class GenerationSettings {
        @Config.Comment({"Enable vanilla ore generation.", "Default: false"})
        public boolean enableVanillaOreGeneration = false;
        @Config.Comment({"Enable Craftorium ore generation.", "Default: true"})
        public boolean enableOreGeneration = true;
        @Config.Comment({"Approximate amount of ore vein per chunk.", "Default: 8"})
        public int veinDensity = 8;
    }
}
