package fr.cyrilneveu.craftorium.common.integration.craftorium;

import fr.cyrilneveu.craftorium.api.substance.Substance;
import fr.cyrilneveu.craftorium.api.substance.SubstanceBuilder;
import fr.cyrilneveu.craftorium.api.utils.Utils;
import net.minecraftforge.fml.common.Loader;

import static fr.cyrilneveu.craftorium.common.substance.Substances.*;
import static fr.cyrilneveu.craftorium.common.substance.SubstancesObjects.*;

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
                    .packageTransitionMetal()
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

        if (Utils.atLeastOne(new String[]{"railcraft"}, Loader::isModLoaded)) {
            new SubstanceBuilder("brass")
                    .packageMetalExtended()
                    .composition(COPPER, 2, ZINC, 1)
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

        if (Utils.atLeastOne(new String[]{"techguns"}, Loader::isModLoaded)) {
            new SubstanceBuilder("obsidian_steel")
                    .packageTransitionMetal()
                    .composition(OBSIDIAN, 1, STEEL, 1)
                    .style("metal")
                    .build();
        }

        if (Utils.atLeastOne(new String[]{"biomesoplenty", "projectred-core"}, Loader::isModLoaded)) {
            new SubstanceBuilder("ruby")
                    .packageGem()
                    .veinMember()
                    .composition(ALUMINUM, 2, CHROMIUM, 1, OXYGEN, 3)
                    .color(0xFFe00700)
                    .style("gem")
                    .shiny()
                    .build();
            new SubstanceBuilder("peridot")
                    .packageGem()
                    .veinMember()
                    .composition(MAGNESIUM, 2, IRON, 2, SILICON, 1, OXYGEN, 4)
                    .color(0xFF1bd61b)
                    .style("gem")
                    .shiny()
                    .build();
        }

        if (Utils.atLeastOne(new String[]{"biomesoplenty", "projectred-core", "iceandfire"}, Loader::isModLoaded)) {
            new SubstanceBuilder("sapphire")
                    .packageGem()
                    .veinMember()
                    .composition(ALUMINUM, 2, OXYGEN, 3, TITANIUM, 1, IRON, 1)
                    .color(0xFF252ed1)
                    .style("gem")
                    .shiny()
                    .build();
        }

        if (Utils.atLeastOne(new String[]{"biomesoplenty", "iceandfire"}, Loader::isModLoaded)) {
            new SubstanceBuilder("amethyst")
                    .packageGem()
                    .veinMember()
                    .composition(SILICON, 1, OXYGEN, 2, IRON, 1)
                    .color(0xFFe24dc9)
                    .style("gem")
                    .shiny()
                    .build();
        }

        if (Utils.atLeastOne(new String[]{"iceandfire"}, Loader::isModLoaded)) {
            new SubstanceBuilder("ice_dragonsteel")
                    .packageTransitionMetal()
                    .overrides(INGOT, "iceandfire:dragonsteel_ice_ingot") // Dragon Forge result
                    .color(0xFF64acc1)
                    .shiny()
                    .build();
            new SubstanceBuilder("fire_dragonsteel")
                    .packageTransitionMetal()
                    .color(0xFF5e1718)
                    .overrides(INGOT, "iceandfire:dragonsteel_fire_ingot") // Dragon Forge result
                    .shiny()
                    .build();
            new SubstanceBuilder("lightning_dragonsteel")
                    .packageTransitionMetal()
                    .color(0xFF3a1338)
                    .overrides(INGOT, "iceandfire:dragonsteel_lightning_ingot") // Dragon Forge result
                    .shiny()
                    .build();
        }

        if (Utils.atLeastOne(new String[]{"biomesoplenty"}, Loader::isModLoaded)) {
            new SubstanceBuilder("topaz")
                    .packageGem()
                    .veinMember()
                    .composition(ALUMINUM, 2, SILICON, 1, OXYGEN, 6, IRON, 2, HYDROGEN, 2)
                    .color(0xFF1bd61b)
                    .style("gem")
                    .shiny()
                    .build();
            new SubstanceBuilder("tanzanite")
                    .packageGem()
                    .veinMember()
                    .composition(CALCIUM, 2, ALUMINUM, 3, OXYGEN, 13, SILICON, 3, HYDROGEN, 1, VANADIUM, 3)
                    .color(0xFF560c6b)
                    .style("gem")
                    .shiny()
                    .build();
            new SubstanceBuilder("malachite")
                    .packageGem()
                    .veinMember()
                    .composition(COPPER, 2, CARBON, 1, OXYGEN, 5, HYDROGEN, 2)
                    .color(0xFF4f9e7e)
                    .style("gem")
                    .shiny()
                    .build();
            new SubstanceBuilder("amber")
                    .packageGem()
                    .veinMember()
                    .composition()
                    .color(0xFFe2bd4d)
                    .style("gem")
                    .shiny()
                    .build();
        }
    }
}
