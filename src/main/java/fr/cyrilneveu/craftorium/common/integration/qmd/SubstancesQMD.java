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
    public static Substance HAFNIUM;
    public static Substance STRONTIUM;
    public static Substance NEODYMIUM;
    public static Substance IODINE;
    public static Substance TERBIUM;
    public static Substance YTTERBIUM;
    public static Substance ERBIUM;

    public static Substance HAFNIUM_OXIDE;
    public static Substance COPPER_OXIDE;
    public static Substance NIOBIUM_TIN_ALLOY;
    public static Substance NIOBIUM_TITANIUM_ALLOY;
    public static Substance BSCCO;

    @SubscribeEvent
    public static void onRegisterSubstances(RegisterSubstancesEvent event) {
        if (!Loader.isModLoaded("qmd")) return;
        new SubstanceBuilder("tungsten_oxide")
                .packageOxide(TUNGSTEN)
                .build();

        HAFNIUM = new SubstanceBuilder("hafnium")
                .element(72, "Hf", "hafnium", Element.EGroup.TRANSITION_METAL, 178.492)
                .temperature(2506f, 4876f)
                .color(0xFF4dc2ff)
                .packageTransitionMetal()
                .build();
        NEODYMIUM = new SubstanceBuilder("neodymium")
                .element(60, "Nd", "neodymium", Element.EGroup.LANTHANIDE, 144.2423)
                .temperature(1297f, 3347f)
                .color(0xFFc7ffc7)
                .packageLanthanide()
                .build();
        STRONTIUM = new SubstanceBuilder("strontium")
                .element(38, "Sr", "strontium", Element.EGroup.ALKALINE_EARTH_METAL, 87.621)
                .temperature(1050f, 1650f)
                .color(0xFF00ff00)
                .packageAlkalineEarthMetal()
                .build();
        IODINE = new SubstanceBuilder("iodine")
                .element(53, "I", "iodine", Element.EGroup.HALOGEN, 126.904473)
                .temperature(386.85f, 457.4f)
                .color(0xFF940094)
                .packageHalogen()
                .build();
        TERBIUM = new SubstanceBuilder("terbium")
                .element(65, "Tb", "terbium", Element.EGroup.LANTHANIDE, 158.925352)
                .temperature(1629f, 3396f)
                .color(0xFF30ffc7)
                .packageLanthanide()
                .build();
        YTTERBIUM = new SubstanceBuilder("ytterbium")
                .element(70, "Yb", "ytterbium", Element.EGroup.LANTHANIDE, 173.0451)
                .temperature(1097f, 1469f)
                .color(0xFF00bf38)
                .packageLanthanide()
                .build();
        ERBIUM = new SubstanceBuilder("erbium")
                .element(68, "Er", "erbium", Element.EGroup.LANTHANIDE, 167.2593)
                .temperature(1802f, 3141f)
                .color(0xFF00e675)
                .packageLanthanide()
                .build();

        HAFNIUM_OXIDE = new SubstanceBuilder("hafnium_oxide")
                .packageOxide(HAFNIUM)
                .build();


        COPPER_OXIDE = new SubstanceBuilder("copper_oxide")
                .packageOxide(COPPER)
                .build();

        NIOBIUM_TIN_ALLOY = new SubstanceBuilder("niobium_tin_alloy")
                .composition(NIOBIUM, 3, TIN, 1)
                .packageMetalExtended()
                .build();
        NIOBIUM_TITANIUM_ALLOY = new SubstanceBuilder("niobium_titanium_alloy")
                .composition(NIOBIUM, 1, TITANIUM, 1)
                .packageMetalExtended()
                .build();
        BSCCO = new SubstanceBuilder("bscco")
                .composition(BISMUTH, 2, STRONTIUM, 2, CALCIUM, 2, COPPER_OXIDE, 3)
                .packageMetalExtended()
                .build();

        new SubstanceBuilder("zinc_sulfide")
                .composition(ZINC, 1, SULFUR, 1)
                .packageMetalloid()
                .build();
    }
}
