package fr.cyrilneveu.craftorium.common.integration.avaritia;

import fr.cyrilneveu.craftorium.api.substance.RegisterSubstancesEvent;
import fr.cyrilneveu.craftorium.api.substance.SubstanceBuilder;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class SubstancesAvaritia {
    @SubscribeEvent
    public static void onRegisterSubstances(RegisterSubstancesEvent event) {
        if (!Loader.isModLoaded("avaritia")) return;

        new SubstanceBuilder("neutronium")
                .packagePostTransitionMetal()
                .color(0xFF000000)
                .style("neutronium")
                .shiny()
                .glint()
                .style("metal")
                .build();

        new SubstanceBuilder("infinity")
                .packagePostTransitionMetal()
                .color(0xFFffffff)
                .style("infinity")
                .shiny()
                .glint()
                .build();
    }
}
