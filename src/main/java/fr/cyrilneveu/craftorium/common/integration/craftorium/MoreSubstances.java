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
                    .color(0xFF403651)
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
                    .color(0xFF75a36d)
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
            Substance DARK_STEEL = new SubstanceBuilder("dark_steel")
                    .packageMetalExtended()
                    .composition(STEEL, 1, WITHER, 1, OBSIDIAN, 2)
                    .color(0xFF1f2021)
                    .style("metal")
                    .build();
            new SubstanceBuilder("soularium")
                    .packageMetalloid()
                    .composition(SOUL_SAND, 9, GOLD, 1)
                    .color(0xFF1f2021)
                    .style("metal")
                    .build();
            new SubstanceBuilder("end_steel")
                    .packageMetalloid()
                    .composition(END, 9, OBSIDIAN, 9, DARK_STEEL, 1)
                    .style("metal")
                    .color(0xFFdce0a3)
                    .build();

            if (Utils.atLeastOne(new String[]{"enderioendergy"}, Loader::isModLoaded)) {
                new SubstanceBuilder("crude_steel")
                        .packageMetalloid()
                        .composition(STONE, 1, FLINT, 1, CLAY, 1)
                        .color(0xFFa1a3a3)
                        .style("metal")
                        .build();
                Substance energeticSilver = new SubstanceBuilder("energetic_silver")
                        .packagePostTransitionMetal()
                        .composition(SILVER, 1, REDSTONE, 1, GLOWSTONE, 1)
                        .color(0xFF9fc6e0)
                        .style("metal")
                        .shiny()
                        .build();
                new SubstanceBuilder("vivid_alloy")
                        .packagePostTransitionMetal()
                        .composition(energeticSilver, 1, ENDER, 1)
                        .color(0xFF58bcce)
                        .style("metal")
                        .shiny()
                        .build();
                // TODO: Crystalline Pink Slime, Crystalline Alloy, Mellodic Alloy & Stellar Alloy
            }
        }

        if (Utils.atLeastOne(new String[]{"biomesoplenty"}, Loader::isModLoaded)) {
            Substance CHROMIUM = new SubstanceBuilder("chromium")
                    .element(24, "Cr", "chromium", Element.EGroup.TRANSITION_METAL, 51.99616)
                    .temperature(2180f, 2944f)
                    .color(0xFF8a99c7)
                    .packageTransitionMetal()
                    .build();
            Substance FLUORINE = new SubstanceBuilder("fluorine")
                    .element(9, "F", "fluorine", Element.EGroup.HALOGEN, 18.9984031636)
                    .temperature(53.48f, 85.03f)
                    .color(0xFF90e050)
                    .packageHalogen()
                    .build();
            Substance STRONTIUM = new SubstanceBuilder("strontium")
                    .element(38, "Sr", "strontium", Element.EGroup.ALKALINE_EARTH_METAL, 87.621)
                    .temperature(1050f, 1650f)
                    .color(0xFF00ff00)
                    .packageAlkalineEarthMetal()
                    .build();

            new SubstanceBuilder("amethyst")
                    .packageGem()
                    .veinMember()
                    .composition(SILICON, 1, OXYGEN, 2)
                    .possible(IRON, 3, 20)
                    .color(0xFFab24c9)
                    .style("gem")
                    .build();
            new SubstanceBuilder("ruby")
                    .packageGem()
                    .veinMember()
                    .composition(CHROMIUM, 1, ALUMINUM, 2, OXYGEN, 3)
                    .color(0xFF103e)
                    .style("gem")
                    .build();
            new SubstanceBuilder("peridot")
                    .packageGem()
                    .veinMember()
                    .composition(SILICON, 1, OXYGEN, 2, MAGNESIUM, 2, IRON, 2)
                    .color(0xFF2c6832)
                    .style("gem")
                    .build();
            new SubstanceBuilder("topaz")
                    .packageMineral()
                    .veinMember()
                    .composition(ALUMINUM, 2, SILICON, 1, OXYGEN, 4)
                    .possible(FLUORINE, 2, 20, HYDROGEN, 2, 30)
                    .color(0xFFbf600d)
                    .style("gem")
                    .build();
            new SubstanceBuilder("tanzanite")
                    .packageGem()
                    .veinMember()
                    .composition(CALCIUM, 2, ALUMINUM, 3, SILICON, 3, OXYGEN, 13, HYDROGEN, 1)
                    .possible(CHROMIUM, 1, 15, STRONTIUM, 1, 15)
                    .color(0xFF391447)
                    .style("gem")
                    .build();
            new SubstanceBuilder("malachite")
                    .packageMineral()
                    .veinMember()
                    .composition(COPPER, 2, OXYGEN, 4, HYDROGEN, 1)
                    .color(0xFF5f8465)
                    .style("gem")
                    .build();
            new SubstanceBuilder("sapphire")
                    .packageGem()
                    .veinMember()
                    .composition(ALUMINUM, 2, OXYGEN, 3)
                    .color(0xFF3424c9)
                    .style("gem")
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
