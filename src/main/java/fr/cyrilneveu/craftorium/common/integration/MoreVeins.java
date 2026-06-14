package fr.cyrilneveu.craftorium.common.integration;

import fr.cyrilneveu.craftorium.api.utils.Utils;
import fr.cyrilneveu.craftorium.api.world.vein.VeinBuilder;
import net.minecraftforge.fml.common.Loader;

import static fr.cyrilneveu.craftorium.common.substance.Substances.*;

public final class MoreVeins {
    private static boolean INITIALIZED = false;

    public static void init() {
        if (INITIALIZED)
            return;

        INITIALIZED = true;

        if (Utils.atLeastOne(new String[]{"nuclearcraft"}, Loader::isModLoaded)) {
            // new VeinBuilder("rhodochrosite", 5, 255, 6, 6, 20, 0, "rhodochrosite", 9, MANGANESE, 1).build();
            // new VeinBuilder("fluorite", 5, 255, 6, 6, 20, 0, "fluorite", 9, CALCIUM, 1).build();
            // new VeinBuilder("villiaumite", 5, 255, 6, 6, 20, 0, "villiaumite", 8, SODIUM, 1, FLUORINE, 1).build();
            // new VeinBuilder("carobbiite", 0, 255, 3, 3, 5, -1, "carobbiite", 8, POTASSIUM, 1, FLUORINE, 1).build();
        }
    }
}
