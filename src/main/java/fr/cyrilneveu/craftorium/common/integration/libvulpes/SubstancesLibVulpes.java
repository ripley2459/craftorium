package fr.cyrilneveu.craftorium.common.integration.libvulpes;

import fr.cyrilneveu.craftorium.api.substance.RegisterSubstancesEvent;
import fr.cyrilneveu.craftorium.api.substance.SubstanceBuilder;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public final class SubstancesLibVulpes {
    @SubscribeEvent
    public static void onRegisterSubstances(RegisterSubstancesEvent event) {
        if (!Loader.isModLoaded("libvulpes")) return;
        new SubstanceBuilder("dilithium")
                .packageGem()
                .color(0xFFc7bab7)
                .style("gem")
                .build();
    }
}
