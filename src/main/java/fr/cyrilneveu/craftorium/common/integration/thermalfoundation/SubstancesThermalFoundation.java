package fr.cyrilneveu.craftorium.common.integration.thermalfoundation;

import fr.cyrilneveu.craftorium.api.substance.RegisterSubstancesEvent;
import fr.cyrilneveu.craftorium.api.substance.SubstanceBuilder;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static fr.cyrilneveu.craftorium.common.substance.Substances.*;

@Mod.EventBusSubscriber
public final class SubstancesThermalFoundation {
    @SubscribeEvent
    public static void onRegisterSubstances(RegisterSubstancesEvent event) {
        if (!Loader.isModLoaded("thermalfoundation")) return;
        new SubstanceBuilder("invar")
                .packageMetalExtended()
                .composition(NICKEL, 1, IRON, 3)
                .color(0xFFdde1df)
                .style("metal")
                .build();
        new SubstanceBuilder("constantan")
                .packageMetalExtended()
                .composition(COPPER, 1, NICKEL, 1)
                .color(0xFFfad37c)
                .style("metal")
                .build();
        new SubstanceBuilder("signalum")
                .packageMetalExtended()
                .composition(COPPER, 3, SILVER, 1, REDSTONE, 10)
                .color(0xFFff991c)
                .style("metal")
                .shiny()
                .build();
        new SubstanceBuilder("lumium")
                .packageMetalExtended()
                .composition(TIN, 3, SILVER, 1, GLOWSTONE, 5)
                .color(0xFFf1f9df)
                .style("metal")
                .shiny()
                .build();
        new SubstanceBuilder("enderium")
                .packageMetalExtended()
                .composition(LEAD, 3, PLATINUM, 1, ENDER, 4)
                .color(0xFF0e5c5c)
                .style("metal")
                .build();
        new SubstanceBuilder("niter")
                .packageMineral()
                .composition(POTASSIUM, 1, NITROGEN, 1, OXYGEN, 3)
                .color(0xFF0e5c5c)
                .style("mineral")
                .build();
    }
}
