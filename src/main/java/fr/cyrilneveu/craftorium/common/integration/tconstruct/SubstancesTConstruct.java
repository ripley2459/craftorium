package fr.cyrilneveu.craftorium.common.integration.tconstruct;

import fr.cyrilneveu.craftorium.api.substance.RegisterSubstancesEvent;
import fr.cyrilneveu.craftorium.api.substance.Substance;
import fr.cyrilneveu.craftorium.api.substance.SubstanceBuilder;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static fr.cyrilneveu.craftorium.common.substance.Substances.*;

@Mod.EventBusSubscriber
public final class SubstancesTConstruct {
    @SubscribeEvent
    public static void onRegisterSubstances(RegisterSubstancesEvent event) {
        if (!Loader.isModLoaded("tconstruct")) return;
        Substance ardite = new SubstanceBuilder("ardite")
                .packageTransitionMetal()
                .color(0xFFc34c17)
                .style("metal")
                .build();
        new SubstanceBuilder("manyullyn")
                .packageTransitionMetal()
                .composition(ardite, 1, COBALT, 1)
                .color(0xFFa97de0)
                .style("metal")
                .shiny()
                .build();
        new SubstanceBuilder("aluminum_brass")
                .packageMetalExtended()
                .composition(COPPER, 1, ALUMINUM, 3)
                .color(0xFFf0d467)
                .style("metal")
                .build();
        new SubstanceBuilder("alumite")
                .packageTransitionMetal()
                .composition(ALUMINUM, 1, IRON, 1, OBSIDIAN, 3)
                .color(0xFFf9ecf7)
                .style("metal")
                .build();
    }
}
