package fr.cyrilneveu.craftorium.common.integration.tconstruct;

import fr.cyrilneveu.craftorium.api.utils.Utils;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.event.FMLInterModComms;

import static fr.cyrilneveu.craftorium.api.Registries.SUBSTANCES_REGISTRY;
import static fr.cyrilneveu.craftorium.common.substance.SubstancesObjects.LIQUID;

public final class TConstructPlugin {
    public static void init() {
        SUBSTANCES_REGISTRY.getAll().forEach((n, s) -> {
            String name = s.getName();

            NBTTagCompound tag = new NBTTagCompound();
            tag.setString("fluid", LIQUID.getName(s));
            tag.setString("ore", Utils.toUpperCamelCase(name));
            tag.setBoolean("toolforge", false);
            FMLInterModComms.sendMessage("tconstruct", "integrateSmeltery", tag);
        });
    }
}
