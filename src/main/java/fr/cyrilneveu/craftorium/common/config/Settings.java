package fr.cyrilneveu.craftorium.common.config;

import net.minecraftforge.common.config.Config;

import static fr.cyrilneveu.craftorium.CraftoriumTags.MODID;

@Config(modid = MODID)
public class Settings {
    @Config.Name("Test settings")
    @Config.Comment("Test settings.")
    @Config.RequiresMcRestart
    public static TestSettings testSettings = new TestSettings();

    public static class TestSettings {
        @Config.Comment({"Test boolean.", "Default: false"})
        public boolean testBoolean = false;
        @Config.Comment({"Test amount.", "Default: 10000"})
        public int testAmount = 10000;
    }
}
