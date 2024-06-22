package fr.cyrilneveu.craftorium.common.integration.craftorium;

import fr.cyrilneveu.craftorium.api.substance.Element;
import fr.cyrilneveu.craftorium.api.substance.Substance;
import fr.cyrilneveu.craftorium.api.substance.SubstanceBuilder;
import fr.cyrilneveu.craftorium.api.utils.Utils;
import net.minecraftforge.fml.common.Loader;

import static fr.cyrilneveu.craftorium.common.substance.Substances.*;

public final class MoreSubstances {
    private static boolean INITIALIZED = false;

    public static void init() {
        if (INITIALIZED)
            return;

        INITIALIZED = true;

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

        if (Utils.atLeastOne(new String[]{"thermalfoundation"}, Loader::isModLoaded)) {
            new SubstanceBuilder("invar")
                    .packageMetalExtended()
                    .composition(NICKEL, 1, IRON, 3)
                    .color(0xFFc3cdc2)
                    .style("metal")
                    .build();
            new SubstanceBuilder("constantan")
                    .packageMetalExtended()
                    .composition(COPPER, 1, NICKEL, 1)
                    .color(0xFFfdd27b)
                    .style("metal")
                    .build();
            new SubstanceBuilder("signalum")
                    .packageMetalExtended()
                    .composition(COPPER, 3, SILVER, 1, REDSTONE, 10)
                    .color(0xFFff9121)
                    .style("metal")
                    .shiny()
                    .build();
            new SubstanceBuilder("lumium")
                    .packageLanthanide()
                    .composition(TIN, 3, SILVER, 1, GLOWSTONE, 5)
                    .color(0xFFdee490)
                    .style("metal")
                    .shiny()
                    .build();
            new SubstanceBuilder("enderium")
                    .packageMetalExtended()
                    .composition(LEAD, 3, PLATINUM, 1, ENDER, 4)
                    .color(0xFF0e5c5c)
                    .style("metal")
                    .build();
        }

        if (Utils.atLeastOne(new String[]{"enderio"}, Loader::isModLoaded)) {
            new SubstanceBuilder("iron_alloy")
                    .packagePostTransitionMetal()
                    .composition(IRON, 1, TIN, 1, LEAD, 1)
                    .color(0xFF3b3742)
                    .style("metal")
                    .build();
            new SubstanceBuilder("conductive_iron")
                    .packagePostTransitionMetal()
                    .composition(IRON, 1, REDSTONE, 1)
                    .color(0xFFdbb8b8)
                    .style("metal")
                    .shiny()
                    .build();
            new SubstanceBuilder("electrical_steel")
                    .composition(IRON, 1, COAL, 1, SILICON, 1)
                    .packagePostTransitionMetal()
                    .color(0xFFbababa)
                    .style("metal")
                    .shiny()
                    .build();
            Substance energeticSilver = new SubstanceBuilder("energetic_silver")
                    .packagePostTransitionMetal()
                    .composition(SILVER, 1, REDSTONE, 1, GLOWSTONE, 1)
                    .color(0xFF638f8d)
                    .style("metal")
                    .shiny()
                    .build();
            Substance energeticAlloy = new SubstanceBuilder("energetic_alloy")
                    .packagePostTransitionMetal()
                    .composition(GOLD, 1, REDSTONE, 1, GLOWSTONE, 1)
                    .color(0xFFe38436)
                    .style("metal")
                    .shiny()
                    .build();
            new SubstanceBuilder("pulsating_iron")
                    .packagePostTransitionMetal()
                    .composition(IRON, 1, ENDER, 1)
                    .color(0xFF5d8756)
                    .style("metal")
                    .shiny()
                    .build();
            new SubstanceBuilder("vivid_alloy")
                    .packagePostTransitionMetal()
                    .composition(energeticSilver, 1, ENDER, 1)
                    .color(0xFFbae6e4)
                    .style("metal")
                    .shiny()
                    .build();
            new SubstanceBuilder("vibrant_alloy")
                    .packagePostTransitionMetal()
                    .composition(energeticAlloy, 1, ENDER, 1)
                    .color(0xFF7be86b)
                    .style("metal")
                    .shiny()
                    .build();
            new SubstanceBuilder("dark_steel")
                    .packageMetalExtended()
                    .composition(STEEL, 1, WITHER, 1, OBSIDIAN, 2)
                    .color(0xFF1a1821)
                    .style("metal")
                    .build();
        }

        if (Utils.atLeastOne(new String[]{"nuclearcraft"}, Loader::isModLoaded)) {
            new SubstanceBuilder("zirconium")
                    .element(40, "Zr", "zirconium", Element.EGroup.TRANSITION_METAL, 91.2242)
                    .packageTransitionMetal()
                    .temperature(2128f, 4650f)
                    .color(0xFF94e0e0)
                    .style("metal")
                    .build();
        }
    }
}
