package fr.cyrilneveu.craftorium.common.integration.nuclearcraft;

import fr.cyrilneveu.craftorium.api.substance.RegisterSubstancesEvent;
import fr.cyrilneveu.craftorium.api.substance.Substance;
import fr.cyrilneveu.craftorium.api.substance.SubstanceBuilder;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static fr.cyrilneveu.craftorium.common.substance.Substances.*;

@Mod.EventBusSubscriber
public final class SubstancesNuclearCraft {
    public static Substance MANGANESE_OXIDE;
    public static Substance MANGANESE_DIOXIDE;
    public static Substance ZIRCONIA;
    public static Substance TIN_OXIDE;
    public static Substance NICKEL_OXIDE;
    public static Substance COBALT_OXIDE;
    public static Substance RUTHENIUM_OXIDE;
    public static Substance IRIDIUM_OXIDE;
    public static Substance ZIRCALOY;
    public static Substance THERMOCONDUCTING_ALLOY;
    public static Substance HARD_CARBON;
    public static Substance FERROBORON;
    public static Substance TOUGH_ALLOY;
    public static Substance EXTREME_ALLOY;
    public static Substance LITHIUM_MANGANESE_DIOXIDE_ALLOY;
    public static Substance SILICON_CARBIDE;

    @SubscribeEvent
    public static void onRegisterSubstances(RegisterSubstancesEvent event) {
        if (!Loader.isModLoaded("nuclearcraft")) return;

        MANGANESE_OXIDE = new SubstanceBuilder("manganese_oxide")
                .packageOxide(MANGANESE)
                .build();
        MANGANESE_DIOXIDE = new SubstanceBuilder("manganese_dioxide")
                .packageOxide(MANGANESE_OXIDE)
                .build();
        ZIRCONIA = new SubstanceBuilder("zirconia")
                .packageOxide(ZIRCONIUM)
                .build();
        TIN_OXIDE = new SubstanceBuilder("tin_oxide")
                .packageOxide(TIN)
                .build();
        NICKEL_OXIDE = new SubstanceBuilder("nickel_oxide")
                .packageOxide(NICKEL)
                .build();
        COBALT_OXIDE = new SubstanceBuilder("cobalt_oxide")
                .packageOxide(COBALT)
                .build();
        RUTHENIUM_OXIDE = new SubstanceBuilder("ruthenium_oxide")
                .packageOxide(RUTHENIUM)
                .build();
        IRIDIUM_OXIDE = new SubstanceBuilder("iridium_oxide")
                .packageOxide(IRIDIUM)
                .build();

        new SubstanceBuilder("rhodochrosite")
                .veinMember()
                .composition(MANGANESE, 3, CARBON, 1, OXYGEN, 3)
                .possible(IRON, 1, 5, CALCIUM, 1, 5, MAGNESIUM, 1, 5, ZINC, 1, 5, COBALT, 1, 5, CADMIUM, 1, 5)
                .color(0xFFde5d83)
                .style("metal")
                .build();
        new SubstanceBuilder("fluorite")
                .veinMember()
                .composition(CALCIUM, 1, FLUORINE, 2)
                .possible(YTTRIUM, 1, 5, CESIUM, 1, 5, SILICON, 1, 5, ALUMINUM, 1, 5, IRON, 1, 5, MAGNESIUM, 1, 5, EUROPIUM, 1, 5, OXYGEN, 1, 5, CHLORINE, 1, 5, SAMARIUM, 1, 5)
                .color(0xFFb2f0e9)
                .style("metal")
                .build();
        new SubstanceBuilder("villiaumite")
                .veinMember()
                .composition(SODIUM, 1, FLUORINE, 1)
                .possible(ALUMINUM, 1, 4)
                .style("mineral")
                .shiny()
                .color(0xFFe03c31)
                .build();
        new SubstanceBuilder("carobbiite")
                .veinMember()
                .composition(POTASSIUM, 1, FLUORINE, 3)
                .color(0xFFe8f3f9)
                .style("mineral")
                .shiny()
                .build();

        ZIRCALOY = new SubstanceBuilder("zircaloy")
                .packageMetalExtended()
                .composition(ZIRCONIUM, 7, TIN, 1)
                .build();
        FERROBORON = new SubstanceBuilder("ferroboron")
                .packageMetalExtended()
                .composition(STEEL, 1, BORON, 1)
                .build();
        HARD_CARBON = new SubstanceBuilder("hard_carbon")
                .packageMetalloid()
                .composition(DIAMOND, 1, CARBON, 2)
                .build();
        TOUGH_ALLOY = new SubstanceBuilder("tough_alloy")
                .packageMetalloid()
                .composition(FERROBORON, 1, LITHIUM, 1)
                .build();
        EXTREME_ALLOY = new SubstanceBuilder("extreme_alloy")
                .packageMetalloid()
                .composition(TOUGH_ALLOY, 1, HARD_CARBON, 1)
                .build();
        THERMOCONDUCTING_ALLOY = new SubstanceBuilder("thermoconducting_alloy")
                .packageMetalExtended()
                .composition(EXTREME_ALLOY, 7, BORON_ARSENIDE_ALLOY, 1)
                .build();
        LITHIUM_MANGANESE_DIOXIDE_ALLOY = new SubstanceBuilder("lithium_manganese_dioxide_alloy")
                .packageMetalloid()
                .composition(LITHIUM, 1, MANGANESE_DIOXIDE, 1)
                .build();
        SILICON_CARBIDE = new SubstanceBuilder("silicon_carbide")
                .packageMetalloid()
                .composition(SILICON, 1, GRAPHITE, 1)
                .build();
    }
}
