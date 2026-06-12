package fr.cyrilneveu.craftorium.common.integration.iceandfire;

import fr.cyrilneveu.craftorium.api.substance.RegisterSubstancesEvent;
import fr.cyrilneveu.craftorium.api.substance.SubstanceBuilder;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static fr.cyrilneveu.craftorium.common.substance.SubstancesObjects.INGOT;

@Mod.EventBusSubscriber
public final class SubstancesIceAndFire {
    @SubscribeEvent
    public static void onRegisterSubstances(RegisterSubstancesEvent event) {
        if (!Loader.isModLoaded("iceandfire")) return;

        new SubstanceBuilder("ice_dragonsteel")
                .packageTransitionMetal()
                .overrides(INGOT, "iceandfire:dragonsteel_ice_ingot")
                .color(0xFF64acc1)
                .shiny()
                .build();
        new SubstanceBuilder("fire_dragonsteel")
                .packageTransitionMetal()
                .color(0xFF5e1718)
                .overrides(INGOT, "iceandfire:dragonsteel_fire_ingot")
                .shiny()
                .build();
        new SubstanceBuilder("lightning_dragonsteel")
                .packageTransitionMetal()
                .color(0xFF3a1338)
                .overrides(INGOT, "iceandfire:dragonsteel_lightning_ingot")
                .shiny()
                .build();
    }
}
