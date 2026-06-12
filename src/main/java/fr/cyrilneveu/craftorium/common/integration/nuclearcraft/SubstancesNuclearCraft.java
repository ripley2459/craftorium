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
    @SubscribeEvent
    public static void onRegisterSubstances(RegisterSubstancesEvent event) {
        if (!Loader.isModLoaded("nuclearcraft")) return;
        new SubstanceBuilder("zircaloy")
                .packageMetalExtended()
                .composition(ZIRCONIUM, 7, TIN, 1)
                .build();
        Substance ferroboron = new SubstanceBuilder("ferroboron")
                .packageMetalExtended()
                .composition(STEEL, 1, BORON, 1)
                .build();
        Substance hardCarbon = new SubstanceBuilder("hard_carbon")
                .packageMetalloid()
                .composition(DIAMOND, 1, CARBON, 2)
                .build();
        new SubstanceBuilder("magnesium_diboride")
                .packageMetalloid()
                .composition(MAGNESIUM, 1, BORON, 2)
                .build();
        Substance toughAlloy = new SubstanceBuilder("tough_alloy")
                .packageMetalloid()
                .composition(ferroboron, 1, LITHIUM, 1)
                .build();
        new SubstanceBuilder("extreme_alloy")
                .packageMetalloid()
                .composition(toughAlloy, 1, hardCarbon, 1)
                .build();

        Substance manganeseOxide = new SubstanceBuilder("manganese_oxide")
                .packageOxide(MANGANESE)
                .build();
        new SubstanceBuilder("manganese_dioxide")
                .packageOxide(manganeseOxide)
                .build();
        new SubstanceBuilder("zirconia")
                .packageOxide(ZIRCONIUM)
                .build();
        new SubstanceBuilder("tin_oxide")
                .packageOxide(TIN)
                .build();
        new SubstanceBuilder("nickel_oxide")
                .packageOxide(NICKEL)
                .build();
        new SubstanceBuilder("cobalt_oxide")
                .packageOxide(COBALT)
                .build();
        new SubstanceBuilder("ruthenium_oxide")
                .packageOxide(RUTHENIUM)
                .build();
        new SubstanceBuilder("iridium_oxide")
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
    }
}
