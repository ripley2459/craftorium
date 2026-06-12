package fr.cyrilneveu.craftorium.common.integration.advancedrocketry;

import fr.cyrilneveu.craftorium.api.substance.RegisterSubstancesEvent;
import fr.cyrilneveu.craftorium.api.substance.SubstanceBuilder;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static fr.cyrilneveu.craftorium.common.substance.Substances.*;
import static fr.cyrilneveu.craftorium.common.substance.SubstancesObjects.LIQUID;

@Mod.EventBusSubscriber
public final class SubstancesAdvancedRocketry {
    @SubscribeEvent
    public static void onRegisterSubstances(RegisterSubstancesEvent event) {
        if (!Loader.isModLoaded("advancedrocketry")) return;

        new SubstanceBuilder("titanium_aluminide")
                .composition(TITANIUM, 3, ALUMINUM, 7)
                .packageMetalExtended()
                .color(0xFFabbfda)
                .style("metal")
                .build();
        new SubstanceBuilder("titanium_iridium")
                .composition(TITANIUM, 1, IRIDIUM, 1)
                .packageMetalExtended()
                .color(0xFFd4dbe0)
                .style("metal")
                .build();
        new SubstanceBuilder("rocket_fuel")
                .composition(HYDROGEN, 1, OXYGEN, 1)
                .fluids(LIQUID)
                .style("lava")
                .build();
    }
}
