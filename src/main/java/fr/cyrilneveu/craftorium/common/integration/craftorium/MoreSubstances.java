package fr.cyrilneveu.craftorium.common.integration.craftorium;

import fr.cyrilneveu.craftorium.api.substance.Substance;
import fr.cyrilneveu.craftorium.api.substance.SubstanceBuilder;
import fr.cyrilneveu.craftorium.api.utils.Utils;
import net.minecraftforge.fml.common.Loader;

import static fr.cyrilneveu.craftorium.common.substance.Substances.*;
import static fr.cyrilneveu.craftorium.common.substance.SubstancesObjects.GEM;
import static fr.cyrilneveu.craftorium.common.substance.SubstancesObjects.LIQUID;

public final class MoreSubstances {
    private static boolean INITIALIZED = false;

    public static void init() {
        if (INITIALIZED)
            return;

        INITIALIZED = true;

        if (Utils.atLeastOne(new String[]{"nuclearcraft"}, Loader::isModLoaded)) {
            new SubstanceBuilder("zircaloy")
                    .packageMetalExtended()
                    .composition(ZIRCONIUM, 7, TIN, 1)
                    .color(0xFFff0000)
                    .style("metal")
                    .build();
            Substance ferroboron = new SubstanceBuilder("ferroboron")
                    .packageMetalExtended()
                    .composition(STEEL, 1, BORON, 1)
                    .color(0xFFff0000)
                    .style("metal")
                    .build();
            Substance hardCarbon = new SubstanceBuilder("hard_carbon")
                    .packageMetalloid()
                    .composition(DIAMOND, 1, CARBON, 2)
                    .color(0xFFff0000)
                    .style("metal")
                    .build();
            new SubstanceBuilder("magnesium_diboride")
                    .packageMetalloid()
                    .composition(MAGNESIUM, 1, BORON, 2)
                    .color(0xFFff0000)
                    .style("metal")
                    .build();
            Substance toughAlloy = new SubstanceBuilder("tough_alloy")
                    .packageMetalloid()
                    .composition(ferroboron, 1, LITHIUM, 1)
                    .color(0xFFff0000)
                    .style("metal")
                    .build();
            new SubstanceBuilder("extreme_alloy")
                    .packageMetalloid()
                    .composition(toughAlloy, 1, hardCarbon, 1)
                    .color(0xFFff0000)
                    .style("metal")
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

        if (Utils.atLeastOne(new String[]{"libvulpes"}, Loader::isModLoaded)) {
            new SubstanceBuilder("dilithium")
                    .packageGem()
                    .color(0xFFc7bab7)
                    .style("gem")
                    .build();
        }

        if (Utils.atLeastOne(new String[]{"advancedrocketry"}, Loader::isModLoaded)) {
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

        if (Utils.atLeastOne(new String[]{"tconstruct"}, Loader::isModLoaded)) {
            Substance ardite = new SubstanceBuilder("ardite")
                    .packageTransitionMetal()
                    .color(0xFFc34c17)
                    .style("metal")
                    .build();
            new SubstanceBuilder("manyullyn")
                    .packageTransitionMetal()
                    .composition(ardite, 1, COBALT, 1)
                    .color(0xFFa97de0)
                    .style("metal")
                    .shiny()
                    .build();
            new SubstanceBuilder("aluminum_brass")
                    .packageMetalExtended()
                    .composition(COPPER, 1, ALUMINUM, 3)
                    .color(0xFFf0d467)
                    .style("metal")
                    .build();
            new SubstanceBuilder("alumite")
                    .packageMineral()
                    .composition(ALUMINUM, 1, IRON, 1, OBSIDIAN, 3)
                    .color(0xFFf9ecf7)
                    .style("metal")
                    .build();
        }

        if (Utils.atLeastOne(new String[]{"thermalfoundation"}, Loader::isModLoaded)) {
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

        if (Utils.atLeastOne(new String[]{"draconicevolution"}, Loader::isModLoaded)) {
            new SubstanceBuilder("draconium")
                    .packageMetalExtended()
                    .veinMember()
                    .color(0xFFcca0f2)
                    .style("metal")
                    .build();
            new SubstanceBuilder("awakened_draconium")
                    .packageMetalExtended()
                    .color(0xFFffd200)
                    .style("metal")
                    .build();
        }

        if (Utils.atLeastOne(new String[]{"appliedenergistics2"}, Loader::isModLoaded)) {
            new SubstanceBuilder("certus_quartz")
                    .packageGem()
                    .veinMember()
                    .overrides(GEM, "appliedenergistics2:material")
                    .tools(3.0f, 1.0f, 250, 2, 15)
                    .color(0xFFafd4ea)
                    .style("gem")
                    .shiny()
                    .build();
            new SubstanceBuilder("charged_certus_quartz")
                    .packageGem()
                    .veinMember()
                    .overrides(GEM, "appliedenergistics2:material:1")
                    .color(0xFFb7dfe2)
                    .style("gem")
                    .build();
            new SubstanceBuilder("fluix")
                    .packageGem()
                    .overrides(GEM, "appliedenergistics2:material:7")
                    .color(0xFF614eab)
                    .style("gem")
                    .build();
        }
    }
}
