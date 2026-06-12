package fr.cyrilneveu.craftorium.common.integration.qmd;

import fr.cyrilneveu.craftorium.api.substance.Element;
import fr.cyrilneveu.craftorium.api.substance.RegisterSubstancesEvent;
import fr.cyrilneveu.craftorium.api.substance.Substance;
import fr.cyrilneveu.craftorium.api.substance.SubstanceBuilder;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static fr.cyrilneveu.craftorium.common.substance.Substances.*;

@Mod.EventBusSubscriber
public final class SubstancesQMD {
    @SubscribeEvent
    public static void onRegisterSubstances(RegisterSubstancesEvent event) {
        if (!Loader.isModLoaded("qmd")) return;
        new SubstanceBuilder("tungsten_oxide")
                .packageOxide(TUNGSTEN)
                .build();

        Substance hafnium = new SubstanceBuilder("hafnium")
                .element(72, "Hf", "hafnium", Element.EGroup.TRANSITION_METAL, 178.492)
                .temperature(2506f, 4876f)
                .color(0xFF4dc2ff)
                .packageTransitionMetal()
                .build();
        new SubstanceBuilder("hafnium_oxide")
                .packageOxide(hafnium)
                .build();

        Substance strontium = new SubstanceBuilder("strontium")
                .element(38, "Sr", "strontium", Element.EGroup.ALKALINE_EARTH_METAL, 87.621)
                .temperature(1050f, 1650f)
                .color(0xFF00ff00)
                .packageAlkalineEarthMetal()
                .build();
        Substance copperOxide = new SubstanceBuilder("copper_oxide")
                .packageOxide(COPPER)
                .build();
        new SubstanceBuilder("bscco")
                .composition(BISMUTH, 2, strontium, 2, CALCIUM, 2, copperOxide, 3)
                .packageMetalExtended()
                .build();

        new SubstanceBuilder("zinc_sulfide")
                .composition(ZINC, 1, SULFUR, 1)
                .packageMetalloid()
                .build();
    }
}
