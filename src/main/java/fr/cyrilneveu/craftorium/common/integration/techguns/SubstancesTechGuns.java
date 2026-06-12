package fr.cyrilneveu.craftorium.common.integration.techguns;

import fr.cyrilneveu.craftorium.api.substance.RegisterSubstancesEvent;
import fr.cyrilneveu.craftorium.api.substance.SubstanceBuilder;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static fr.cyrilneveu.craftorium.common.substance.Substances.*;

@Mod.EventBusSubscriber
public final class SubstancesTechGuns {
    @SubscribeEvent
    public static void onRegisterSubstances(RegisterSubstancesEvent event) {
        if (!Loader.isModLoaded("techguns")) return;
        new SubstanceBuilder("obsidian_steel")
                .packageTransitionMetal()
                .composition(OBSIDIAN, 1, STEEL, 1)
                .style("metal")
                .build();
    }
}
