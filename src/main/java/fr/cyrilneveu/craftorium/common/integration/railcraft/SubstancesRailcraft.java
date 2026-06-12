package fr.cyrilneveu.craftorium.common.integration.railcraft;

import fr.cyrilneveu.craftorium.api.substance.RegisterSubstancesEvent;
import fr.cyrilneveu.craftorium.api.substance.SubstanceBuilder;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static fr.cyrilneveu.craftorium.common.substance.Substances.*;

@Mod.EventBusSubscriber
public final class SubstancesRailcraft {
    @SubscribeEvent
    public static void onRegisterSubstances(RegisterSubstancesEvent event) {
        if (!Loader.isModLoaded("railcraft")) return;
        new SubstanceBuilder("brass")
                .packageMetalExtended()
                .composition(COPPER, 2, ZINC, 1)
                .style("metal")
                .build();
    }
}
