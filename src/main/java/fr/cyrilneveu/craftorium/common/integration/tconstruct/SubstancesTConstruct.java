package fr.cyrilneveu.craftorium.common.integration.tconstruct;

import fr.cyrilneveu.craftorium.api.substance.RegisterSubstancesEvent;
import fr.cyrilneveu.craftorium.api.substance.Substance;
import fr.cyrilneveu.craftorium.api.substance.SubstanceBuilder;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static fr.cyrilneveu.craftorium.common.substance.Substances.*;
import static fr.cyrilneveu.craftorium.common.substance.SubstancesObjects.GEM;

@Mod.EventBusSubscriber
public final class SubstancesTConstruct {
    public static Substance ARDITE;
    public static Substance MANYULLYN;
    public static Substance ALUMINUM_BRASS;
    public static Substance ALUMITE; // Bouger ça dans le bon package

    @SubscribeEvent
    public static void onRegisterSubstances(RegisterSubstancesEvent event) {
        if (!Loader.isModLoaded("tconstruct")) return;

        ARDITE = new SubstanceBuilder("ardite")
                .packageTransitionMetal()
                .color(0xFFc34c17)
                .build();
        MANYULLYN = new SubstanceBuilder("manyullyn")
                .packageTransitionMetal()
                .composition(ARDITE, 1, COBALT, 1)
                .shiny()
                .build();
        ALUMINUM_BRASS = new SubstanceBuilder("aluminum_brass")
                .packageMetalExtended()
                .composition(COPPER, 1, ALUMINUM, 3)
                .build();
        ALUMITE = new SubstanceBuilder("alumite")
                .packageTransitionMetal()
                .composition(ALUMINUM, 1, IRON, 1, OBSIDIAN, 3)
                .build();
    }
}
