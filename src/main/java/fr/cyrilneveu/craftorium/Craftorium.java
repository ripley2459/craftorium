package fr.cyrilneveu.craftorium;

import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = CraftoriumTags.MODID, version = CraftoriumTags.VERSION, name = CraftoriumTags.MODNAME, acceptedMinecraftVersions = "[1.12.2]", dependencies = "after:crafttweaker")
public class Craftorium {
    public static final Logger LOGGER = LogManager.getLogger(CraftoriumTags.MODID);
}
