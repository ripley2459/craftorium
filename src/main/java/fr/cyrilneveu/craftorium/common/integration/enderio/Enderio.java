package fr.cyrilneveu.craftorium.common.integration.enderio;

import fr.cyrilneveu.craftorium.api.substance.RegisterSubstancesEvent;
import fr.cyrilneveu.craftorium.api.substance.Substance;
import fr.cyrilneveu.craftorium.api.substance.SubstanceBuilder;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static fr.cyrilneveu.craftorium.common.integration.transverse.MoreSubstances.ENERGETIC_BLEND;
import static fr.cyrilneveu.craftorium.common.substance.Substances.*;

@Mod.EventBusSubscriber
public class Enderio {
    public static Substance ELECTRICAL_STEEL;
    public static Substance ENERGETIC_ALLOY;
    public static Substance VIBRANT_ALLOY;
    public static Substance CONDUCTIVE_IRON;
    public static Substance PULSATING_IRON;
    public static Substance DARK_STEEL;
    public static Substance SOULARIUM;
    public static Substance END_STEEL;
    public static Substance IRON_ALLOY;

    @SubscribeEvent
    public static void register(RegisterSubstancesEvent event) {
        if (!Loader.isModLoaded("enderio")) return;

        ELECTRICAL_STEEL = new SubstanceBuilder("electrical_steel")
                .packageMetalExtended()
                .composition(STEEL, 1, SILICON, 1)
                .build();
        ENERGETIC_ALLOY = new SubstanceBuilder("energetic_alloy")
                .packageMetalExtended()
                .composition(GOLD, 1, ENERGETIC_BLEND, 1)
                .build();
        VIBRANT_ALLOY = new SubstanceBuilder("vibrant_alloy")
                .packageMetalExtended()
                .composition(ENERGETIC_ALLOY, 1, ENDER, 1)
                .build();
        CONDUCTIVE_IRON = new SubstanceBuilder("conductive_iron")
                .packageMetalExtended()
                .composition(IRON, 1, REDSTONE, 1)
                .build();
        PULSATING_IRON = new SubstanceBuilder("pulsating_iron")
                .packageMetalExtended()
                .composition(IRON, 1, ENDER, 1)
                .build();
        DARK_STEEL = new SubstanceBuilder("dark_steel")
                .packageMetalExtended()
                .composition(STEEL, 1, OBSIDIAN, 1)
                .build();
        SOULARIUM = new SubstanceBuilder("soularium")
                .packageMetalExtended()
                .composition(GOLD, 1, SOUL_SAND, 1)
                .build();
        END_STEEL = new SubstanceBuilder("end_steel")
                .packageMetalExtended()
                .composition(STEEL, 1, END, 1)
                .build();
        IRON_ALLOY = new SubstanceBuilder("iron_alloy")
                .packageMetalExtended()
                .composition(LEAD, 2, IRON, 1)
                .build();
    }
}
