package fr.cyrilneveu.craftorium.common.substance;

import crafttweaker.annotations.ZenRegister;
import fr.cyrilneveu.craftorium.api.substance.Element;
import fr.cyrilneveu.craftorium.api.substance.Substance;
import fr.cyrilneveu.craftorium.api.substance.SubstanceBuilder;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import static fr.cyrilneveu.craftorium.CraftoriumTags.MODID;
import static fr.cyrilneveu.craftorium.api.Registries.SUBSTANCES_REGISTRY;
import static fr.cyrilneveu.craftorium.common.substance.SubstancesObjects.*;

@ZenClass("mods." + MODID + ".substance.Substances")
@ZenRegister
public final class Substances {
    public static Substance OXYGEN;
    public static Substance BERYLLIUM;
    public static Substance VANADIUM;
    public static Substance TIN;
    public static Substance COPPER;
    public static Substance BRONZE;
    public static Substance ALUMINUM;
    public static Substance IRON;
    public static Substance CARBON;
    public static Substance STEEL;
    public static Substance GRAPHITE;
    public static Substance COAL;
    public static Substance CHARCOAL;
    public static Substance MANGANESE;
    public static Substance HSLA_STEEL;
    public static Substance PYROLUSITE;
    public static Substance GOLD;
    public static Substance MAGNETITE;
    public static Substance SILVER;
    public static Substance ELECTRUM;
    public static Substance NICKEL;
    public static Substance CUPRONICKEL;
    public static Substance TUNGSTEN;
    public static Substance TUNGSTEN_STEEL;
    public static Substance TUNGSTEN_CARBIDE_ALLOY;
    public static Substance MAGNESIUM;
    public static Substance CHROMIUM;
    public static Substance STAINLESS_STEEL;
    public static Substance NICHROME;
    public static Substance CHROMITE;
    public static Substance RUTHENIUM;
    public static Substance MOLYBDENUM;
    public static Substance RTM_ALLOY;
    public static Substance COBALT;
    public static Substance TITANIUM;
    public static Substance HASTE_ALLOY;
    public static Substance HEMATITE;
    public static Substance ILMENITE;
    public static Substance BORON;
    public static Substance MAGNESIUM_DIBORIDE_ALLOY;
    public static Substance ARSENIC;
    public static Substance BORON_ARSENIDE_ALLOY;
    public static Substance SMALTITE;
    public static Substance NIOBIUM;
    public static Substance RHENIUM;
    public static Substance SUPER_ALLOY;
    public static Substance OSMIUM;
    public static Substance IRIDIUM;
    public static Substance OSMIRIDIUM;
    public static Substance YTTRIUM;
    public static Substance BARIUM;
    public static Substance YBCO;
    public static Substance SILICON;
    public static Substance GRAPHENE;
    public static Substance EMERALD;
    public static Substance OBSIDIAN;
    public static Substance RUBBER;
    public static Substance PLASTIC;
    public static Substance ENDER;
    public static Substance DIAMOND;
    public static Substance QUARTZ;
    public static Substance TANTALUM;
    public static Substance RUTILE;
    public static Substance REDSTONE;
    public static Substance REDSTONE_ALLOY;
    public static Substance SODIUM;
    public static Substance CALCIUM;
    public static Substance SCHEELITE;
    public static Substance POTASSIUM;
    public static Substance SULFUR;
    public static Substance LAZURITE;
    public static Substance BARITE;
    public static Substance CARROLLITE;
    public static Substance CHLORINE;
    public static Substance SODALITE;
    public static Substance LAPIS_LAZULI;
    public static Substance GALLIUM;
    public static Substance BAUXITE;
    public static Substance ANTIMONY;
    public static Substance STIBNITE;
    public static Substance THORIUM;
    public static Substance PLATINUM;
    public static Substance GARNIERITE;
    public static Substance PENTLANDITE;
    public static Substance LEAD;
    public static Substance GALENA;
    public static Substance ZINC;
    public static Substance CERUSSITE;
    public static Substance SELENIUM;
    public static Substance ARGENTITE;
    public static Substance RHODIUM;
    public static Substance MOLYBDENITE;
    public static Substance URANIUM;
    public static Substance CESIUM;
    public static Substance BERYL;
    public static Substance HYDROGEN;
    public static Substance AZURITE;
    public static Substance CARNOTITE;
    public static Substance MALACHITE;
    public static Substance BISMUTH;
    public static Substance BISMUTHINITE;
    public static Substance SCANDIUM;
    public static Substance GERMANIUM;
    public static Substance TELLURIUM;
    public static Substance CHALCOPYRITE;
    public static Substance MERCURY;
    public static Substance CINNABAR;
    public static Substance RUBIDIUM;
    public static Substance ORTHOCLASE;
    public static Substance THALLIUM;
    public static Substance PYRITE;
    public static Substance FLUORINE;
    public static Substance PYROCHLORE;
    public static Substance INDIUM;
    public static Substance CASSITERITE;
    public static Substance CADMIUM;
    public static Substance SMITHSONITE;
    public static Substance LITHIUM;
    public static Substance SPDODUMENE;
    public static Substance STONE;

    public static void init() {
        if (SUBSTANCES_REGISTRY.isInitialized())
            return;

        SUBSTANCES_REGISTRY.initialize();

        initElements();
        initOres();

        STONE = createSubstance("stone")
                .items(DUST, PLATE, ROD)
                .tools(HAMMER)
                .blocks(BLOCK, HULL)
                .tools(4.0f, 1.0f, 131, 1, 5)
                .overrides(BLOCK, "minecraft:cobblestone", HOE, "minecraft:stone_hoe", PICKAXE, "minecraft:stone_pickaxe", HOE, "minecraft:stone_hoe", SHOVEL, "minecraft:stone_shovel", SWORD, "minecraft:stone_sword", AXE, "minecraft:stone_axe")
                .style("mineral")
                .color(0xFFb5b5b5)
                .build();
        BRONZE = createSubstance("bronze")
                .packageMetalExtended()
                .composition(COPPER, 3, TIN, 1)
                .color(0xFFf5a945)
                .shiny()
                .build();
        STEEL = createSubstance("steel")
                .composition(IRON, 1, CARBON, 3)
                .tools(7.5f, 4.0f, 375, 4, 15)
                .packageMetalExtended()
                .build();
        GRAPHITE = createSubstance("graphite")
                .composition(CARBON, 1)
                .packageMetalExtended()
                .packageOre()
                .color(0xFF1b1b1b)
                .build();
        COAL = createSubstance("coal")
                .composition(CARBON, 1)
                .items(DUST, GEM)
                .blocks(BLOCK)
                .packageOre()
                .overrides(BLOCK, "minecraft:coal_block", GEM, "minecraft:coal:0")
                .color(0xFF1a1a1a)
                .style("wood")
                .build();
        CHARCOAL = createSubstance("charcoal")
                .composition(CARBON, 1)
                .items(DUST, GEM)
                .blocks(BLOCK)
                .overrides(GEM, "minecraft:coal:1")
                .color(0xFF291c0b)
                .style("wood")
                .build();
        HSLA_STEEL = createSubstance("hsla_steel")
                .composition(IRON, 4, GRAPHITE, 1, MANGANESE, 1)
                .packageMetalExtended()
                .tools(9.3f, 6f, 563, 6, 16)
                .build();
        ELECTRUM = createSubstance("electrum")
                .composition(GOLD, 1, SILVER, 1)
                .packageMetalExtended()
                .shiny()
                .build();
        CUPRONICKEL = createSubstance("cupronickel")
                .composition(COPPER, 1, NICKEL, 1)
                .packageMetalExtended()
                .build();
        TUNGSTEN_STEEL = createSubstance("tungsten_steel")
                .composition(TUNGSTEN, 1, STEEL, 1)
                .packageMetalExtended()
                .tools(4.6f, 4.0f, 2047, 4, 8)
                .build();
        TUNGSTEN_CARBIDE_ALLOY = createSubstance("tungsten_carbide_alloy")
                .composition(TUNGSTEN, 1, CARBON, 1)
                .packageMetalExtended()
                .tools(14.6f, 10.0f, 1266, 10, 18)
                .build();
        STAINLESS_STEEL = createSubstance("stainless_steel")
                .composition(IRON, 4, MAGNESIUM, 3, MANGANESE, 1, CHROMIUM, 1)
                .packageMetalExtended()
                .tools(11.75f, 8.0f, 844, 8, 17)
                .build();
        NICHROME = createSubstance("nichrome")
                .composition(NICKEL, 2, CHROMIUM, 8)
                .packageMetalExtended()
                .build();
        RTM_ALLOY = createSubstance("rtm_alloy")
                .composition(RUTHENIUM, 4, TUNGSTEN, 2, MOLYBDENUM, 1)
                .packageMetalExtended()
                .shiny()
                .build();
        HASTE_ALLOY = createSubstance("haste_alloy")
                .composition(NICKEL, 2, COBALT, 2, IRON, 2, TITANIUM, 1, ALUMINUM, 1)
                .tools(4.80f, 5.0f, 5117, 5, 9)
                .packageMetalExtended()
                .build();
        MAGNESIUM_DIBORIDE_ALLOY = createSubstance("magnesium_diboride_alloy")
                .composition(MAGNESIUM, 1, BORON, 2)
                .packageMetalExtended()
                .build();
        BORON_ARSENIDE_ALLOY = createSubstance("boron_arsenide_alloy")
                .composition(BORON, 1, ARSENIC, 1)
                .packageMetalExtended()
                .build();
        SUPER_ALLOY = createSubstance("super_alloy")
                .composition(HASTE_ALLOY, 2, CARBON, 2, NIOBIUM, 1, RHENIUM, 1, TUNGSTEN, 1)
                .tools(5.1f, 6.0f, 12793, 6, 10)
                .packageMetalExtended()
                .build();
        OSMIRIDIUM = createSubstance("osmiridium")
                .composition(OSMIUM, 6, IRIDIUM, 3)
                .packageMetalExtended()
                .tools(18.3f, 12.0f, 1844, 12, 19)
                .build();
        YBCO = createSubstance("ybco")
                .composition(YTTRIUM, 1, BARIUM, 2, COPPER, 3)
                .packageMetalExtended()
                .build();
        GRAPHENE = createSubstance("graphene")
                .composition(GRAPHITE, 2, SILICON, 1, CARBON, 3)
                .packageMetalExtended()
                .build();
        EMERALD = createSubstance("emerald")
                .composition(BERYLLIUM, 3, ALUMINUM, 2, SILICON, 18, OXYGEN, 18)
                .possible(CHROMIUM, 2, 3, VANADIUM, 2, 3, IRON, 2, 3)
                .packageGem()
                .packageOre()
                .overrides(BLOCK, "minecraft:emerald_block", GEM, "minecraft:emerald")
                .color(0xFF45b32d)
                .style("gem")
                .shiny()
                .build();
        OBSIDIAN = createSubstance("obsidian")
                .composition(SILICON, 1, OXYGEN, 2)
                .possible(IRON, 3, 25)
                .packageMineral()
                .overrides(BLOCK, "minecraft:obsidian")
                .color(0xFF15071c)
                .style("mineral")
                .build();
        RUBBER = createSubstance("rubber")
                .packageMetalExtended()
                .build();
        PLASTIC = createSubstance("plastic")
                .packageMetalExtended()
                .build();
        ENDER = createSubstance("ender")
                .items(DUST, PEARL)
                .blocks(BLOCK)
                .overrides(PEARL, "minecraft:ender_pearl")
                .color(0xFF0b4d42)
                .style("gem")
                .shiny()
                .build();
        DIAMOND = createSubstance("diamond")
                .packageGem()
                .packageOre()
                .tools(8.0f, 3.0f, 1561, 3, 10)
                .overrides(BLOCK, "minecraft:diamond_block", GEM, "minecraft:diamond", HOE, "minecraft:diamond_hoe", PICKAXE, "minecraft:diamond_pickaxe", HOE, "minecraft:diamond_hoe", SHOVEL, "minecraft:diamond_shovel", SWORD, "minecraft:diamond_sword", AXE, "minecraft:diamond_axe")
                .color(0xFF91f5e6)
                .style("gem")
                .shiny()
                .build();
        QUARTZ = createSubstance("quartz")
                .packageGem()
                .packageOre()
                .overrides(BLOCK, "minecraft:quartz_block:0", GEM, "minecraft:quartz")
                .color(0xFFc9c5b9)
                .style("gem")
                .build();
        REDSTONE = createSubstance("redstone")
                .items(DUST)
                .blocks(BLOCK, ORE)
                .fluids(LIQUID)
                .overrides(BLOCK, "minecraft:redstone_block", DUST, "minecraft:redstone")
                .color(0xFFc80000)
                .style("mineral")
                .shiny()
                .build();
        REDSTONE_ALLOY = createSubstance("redstone_alloy")
                .composition(REDSTONE, 3, COPPER, 1)
                .packageMetalExtended()
                .color(0xFFc80000)
                .shiny()
                .build();
        LAPIS_LAZULI = createSubstance("lapis_lazuli")
                .composition(LAZURITE, 1, SODALITE, 1)
                .packageMineral()
                .blocks(ORE)
                .items(GEM)
                .overrides(BLOCK, "minecraft:lapis_block", GEM, "minecraft:dye:4")
                .style("mineral")
                .color(0xFF1b0f8a)
                .build();
    }

    private static void initOres() {
        SCHEELITE = createSubstance("scheelite")
                .composition(CALCIUM, 1, TUNGSTEN, 1, OXYGEN, 4)
                .possible(MOLYBDENUM, 1, 2, NIOBIUM, 1, 2, TANTALUM, 1, 2)
                .packageOre()
                .color(0xFF784008)
                .build();
        LAZURITE = createSubstance("lazurite")
                .composition(SODIUM, 8, CALCIUM, 8, ALUMINUM, 12, SILICON, 12, OXYGEN, 24, SULFUR, 2)
                .packageOre()
                .color(0xFF191b59)
                .style("mineral")
                .build();
        BARITE = createSubstance("barite")
                .composition(BARIUM, 1, SULFUR, 1, OXYGEN, 4)
                .packageOre()
                .color(0xFFb3a689)
                .style("mineral")
                .build();
        PYROLUSITE = createSubstance("pyrolusite")
                .composition(MANGANESE, 1, OXYGEN, 2)
                .packageOre()
                .color(0xFF8c7379)
                .build();
        MAGNETITE = createSubstance("magnetite")
                .composition(IRON, 3, OXYGEN, 4)
                .possible(GOLD, 2, 10)
                .packageOre()
                .color(0xFF1b1b1c)
                .build();
        CHROMITE = createSubstance("chromite")
                .composition(IRON, 1, CHROMIUM, 2, OXYGEN, 4)
                .packageOre()
                .color(0xFFf2f5c6)
                .style("mineral")
                .build();
        SMALTITE = createSubstance("smaltite")
                .composition(COBALT, 1, ARSENIC, 2)
                .packageOre()
                .color(0xFF4c5361)
                .style("mineral")
                .shiny()
                .build();
        RUTILE = createSubstance("rutile")
                .composition(TITANIUM, 1, OXYGEN, 2)
                .possible(IRON, 1, 10, TANTALUM, 1, 2, NIOBIUM, 1, 2, CHROMIUM, 1, 2, VANADIUM, 1, 2, TIN, 1, 2)
                .packageOre()
                .color(0xFF2b2a29)
                .style("mineral")
                .build();
        ILMENITE = createSubstance("ilmenite")
                .composition(IRON, 1, TITANIUM, 1, OXYGEN, 3)
                .possible(MANGANESE, 2, 3, MAGNESIUM, 2, 3, VANADIUM, 2, 3)
                .packageOre()
                .color(0xFF281e29)
                .build();
        HEMATITE = createSubstance("hematite")
                .composition(IRON, 2, OXYGEN, 3)
                .packageOre()
                .possible(TITANIUM, 1, 5, ALUMINUM, 1, 5, MANGANESE, 1, 5)
                .color(0xFF454c52)
                .build();
        CARROLLITE = createSubstance("carrollite")
                .composition(COPPER, 1, COBALT, 2, SULFUR, 4)
                .packageOre()
                .color(0xFF68695c)
                .style("mineral")
                .build();
        SODALITE = createSubstance("sodalite")
                .composition(SODIUM, 8, ALUMINUM, 6, SILICON, 6, OXYGEN, 24, SULFUR, 2, CHLORINE, 2)
                .possible(IRON, 1, 5, MANGANESE, 1, 5, POTASSIUM, 1, 5, CALCIUM, 1, 5)
                .packageOre()
                .style("mineral")
                .color(0xFF3b4852)
                .build();
        BAUXITE = createSubstance("bauxite")
                .composition(ALUMINUM, 2, OXYGEN, 3)
                .possible(GALLIUM, 4, 20)
                .packageOre()
                .color(0xFFe6a16a)
                .style("mineral")
                .build();
        STIBNITE = createSubstance("stibnite")
                .composition(ANTIMONY, 2, SULFUR, 3)
                .packageOre()
                .color(0xFF3c414a)
                .style("mineral")
                .shiny()
                .build();
        GARNIERITE = createSubstance("garnierite")
                .composition(NICKEL, 1, MAGNESIUM, 1)
                .possible(OSMIUM, 1, 2, IRIDIUM, 1, 2, PLATINUM, 1, 2)
                .packageOre()
                .color(0xFF0f3813)
                .style("mineral")
                .build();
        PENTLANDITE = createSubstance("pentlandite")
                .composition(IRON, 9, NICKEL, 9, SULFUR, 8)
                .possible(OSMIUM, 1, 2, IRIDIUM, 1, 2, PLATINUM, 1, 2)
                .packageOre()
                .color(0xFF65703a)
                .style("mineral")
                .build();
        GALENA = createSubstance("galena")
                .composition(LEAD, 1, SULFUR, 1)
                .possible(SILVER, 2, 5)
                .packageOre()
                .color(0xFF2b1d38)
                .style("mineral")
                .build();
        CERUSSITE = createSubstance("cerussite")
                .composition(LEAD, 1, COPPER, 1, OXYGEN, 3)
                .possible(ZINC, 1, 5)
                .packageOre()
                .color(0xFF919179)
                .style("mineral")
                .build();
        ARGENTITE = createSubstance("argentite")
                .composition(SILVER, 2, SULFUR, 1)
                .possible(SELENIUM, 2, 2)
                .packageOre()
                .color(0xFF706f75)
                .style("mineral")
                .build();
        MOLYBDENITE = createSubstance("molybdenite")
                .composition(MOLYBDENUM, 1, SULFUR, 2)
                .possible(RHENIUM, 1, 2, RHODIUM, 1, 2, SELENIUM, 1, 2, SILVER, 1, 2)
                .packageOre()
                .color(0xFF0d0c0c)
                .style("mineral")
                .build();
        BERYL = createSubstance("beryl")
                .composition(BERYLLIUM, 3, ALUMINUM, 2, SILICON, 6, OXYGEN, 18)
                .possible(CHROMIUM, 2, 5, VANADIUM, 2, 5, CESIUM, 2, 5, IRON, 2, 3)
                .packageOre()
                .color(0xFF5a8c7b)
                .style("gem")
                .shiny()
                .build();
        AZURITE = createSubstance("azurite")
                .composition(COPPER, 3, CARBON, 2, OXYGEN, 8, HYDROGEN, 2)
                .packageOre()
                .color(0xFF193075)
                .style("mineral")
                .build();
        CARNOTITE = createSubstance("carnotite")
                .composition(URANIUM, 2, VANADIUM, 2, OXYGEN, 15, HYDROGEN, 6)
                .possible(CALCIUM, 1, 2, BARIUM, 1, 2, MAGNESIUM, 1, 2, IRON, 1, 2, SODIUM, 1, 2)
                .packageOre()
                .color(0xFF8e9c4f)
                .style("mineral")
                .build();
        MALACHITE = createSubstance("malachite")
                .composition(COPPER, 2, CARBON, 1, OXYGEN, 5, HYDROGEN, 2)
                .packageOre()
                .color(0xFF48b565)
                .style("gem")
                .shiny()
                .build();
        BISMUTHINITE = createSubstance("bismuthinite")
                .composition(BISMUTH, 2, SULFUR, 3)
                .packageOre()
                .color(0xFF73694d)
                .style("mineral")
                .build();
        CHALCOPYRITE = createSubstance("chalcopyrite")
                .composition(COPPER, 1, IRON, 1, SULFUR, 2)
                .possible(TELLURIUM, 2, 3)
                .packageOre()
                .color(0xFF78532f)
                .style("mineral")
                .build();
        CINNABAR = createSubstance("cinnabar")
                .composition(MERCURY, 1, SULFUR, 1)
                .packageOre()
                .color(0xFF40111d)
                .style("mineral")
                .build();
        ORTHOCLASE = createSubstance("orthoclase")
                .composition(POTASSIUM, 1, ALUMINUM, 1, SILICON, 3, OXYGEN, 8)
                .possible(SODIUM, 1, 3, IRON, 1, 3, BARIUM, 1, 3, RUBIDIUM, 1, 3, CALCIUM, 1, 3)
                .packageOre()
                .color(0xFFa38688)
                .style("mineral")
                .build();
        PYRITE = createSubstance("pyrite")
                .composition(IRON, 2, SULFUR, 1)
                .possible(SILVER, 2, 5, GOLD, 2, 5, NICKEL, 1, 5, COBALT, 1, 5, ARSENIC, 1, 5, ZINC, 1, 5, THALLIUM, 1, 5, SELENIUM, 1, 5)
                .packageOre()
                .color(0xFFe3f542)
                .style("mineral")
                .build();
        PYROCHLORE = createSubstance("pyrochlore")
                .composition(SODIUM, 2, CALCIUM, 2, NIOBIUM, 2, OXYGEN, 7, HYDROGEN, 1, FLUORINE, 1)
                .packageOre()
                .color(0xFF1c1c10)
                .style("mineral")
                .build();
        CASSITERITE = createSubstance("cassiterite")
                .composition(TIN, 1, OXYGEN, 2)
                .possible(TANTALUM, 1, 2, NIOBIUM, 1, 2, TUNGSTEN, 1, 2, MANGANESE, 1, 2, SCANDIUM, 1, 2, GERMANIUM, 1, 2, INDIUM, 1, 2, SCANDIUM, 1, 2)
                .packageOre()
                .color(0xFFe6e6e6)
                .style("mineral")
                .build();
        SMITHSONITE = createSubstance("smithsonite")
                .composition(ZINC, 1, CARBON, 1, OXYGEN, 3)
                .possible(IRON, 1, 2, COBALT, 1, 2, COPPER, 1, 2, MANGANESE, 1, 2, CALCIUM, 1, 2, CADMIUM, 1, 2, MAGNESIUM, 1, 2, INDIUM, 1, 2)
                .packageOre()
                .color(0xFF708082)
                .style("mineral")
                .build();
        SPDODUMENE = createSubstance("spdodumene")
                .composition(LITHIUM, 1, ALUMINUM, 1, SILICON, 2, OXYGEN, 6)
                .possible(IRON, 1, 2, MANGANESE, 1, 2, MAGNESIUM, 1, 2, CALCIUM, 1, 2, SODIUM, 1, 2, POTASSIUM, 1, 2)
                .packageOre()
                .color(0xFFbdacb0)
                .style("mineral")
                .build();
    }

    private static void initElements() {
        SILVER = createSubstance("silver")
                .element(47, "Ag", "silver", Element.EGroup.TRANSITION_METAL, 107.86822)
                .temperature(1234.93f, 2435f)
                .packageTransitionMetal()
                .packageOre()
                .color(0xFFc0c0c0)
                .build();
        NICKEL = createSubstance("nickel")
                .element(28, "Ni", "nickel", Element.EGroup.TRANSITION_METAL, 58.69344)
                .temperature(1728f, 3003f)
                .color(0xFF50d050)
                .packageTransitionMetal()
                .build();
        MAGNESIUM = createSubstance("magnesium")
                .element(12, "Mg", "magnesium", Element.EGroup.ALKALINE_EARTH_METAL, 24.305)
                .temperature(923f, 1363f)
                .color(0xFF8aff00)
                .packageAlkalineEarthMetal()
                .packageOre()
                .build();
        RUTHENIUM = createSubstance("ruthenium")
                .element(44, "Ru", "ruthenium", Element.EGroup.TRANSITION_METAL, 101.072)
                .temperature(2607f, 4423f)
                .color(0xFF248f8f)
                .packageTransitionMetal()
                .build();
        MOLYBDENUM = createSubstance("molybdenum")
                .element(42, "Mo", "molybdenum", Element.EGroup.TRANSITION_METAL, 95.951)
                .temperature(2896f, 4912f)
                .color(0xFF54b5b5)
                .packageTransitionMetal()
                .packageOre()
                .build();
        COBALT = createSubstance("cobalt")
                .element(27, "Co", "cobalt", Element.EGroup.TRANSITION_METAL, 58.9331944)
                .temperature(1768f, 3200f)
                .color(0xFFf090a0)
                .packageTransitionMetal()
                .packageOre()
                .build();
        TITANIUM = createSubstance("titanium")
                .element(22, "Ti", "titanium", Element.EGroup.TRANSITION_METAL, 47.8671)
                .temperature(1941f, 3560f)
                .color(0xFFbfc2c7)
                .packageTransitionMetal()
                .build();
        CHROMIUM = createSubstance("chromium")
                .element(24, "Cr", "chromium", Element.EGroup.TRANSITION_METAL, 51.99616)
                .temperature(2180f, 2944f)
                .color(0xFF8a99c7)
                .packageTransitionMetal()
                .packageOre()
                .build();
        TUNGSTEN = createSubstance("tungsten")
                .element(74, "W", "tungsten", Element.EGroup.TRANSITION_METAL, 183.841)
                .temperature(3695f, 6203f)
                .color(0xFF2194d6)
                .packageTransitionMetal()
                .packageOre()
                .build();
        OXYGEN = createSubstance("oxygen")
                .element(8, "O", "oxygen", Element.EGroup.HALOGEN, 15.999)
                .temperature(54.36f, 90.188f)
                .color(0xFFff0d0d)
                .packageHalogen()
                .build();
        BERYLLIUM = createSubstance("beryllium")
                .element(4, "Be", "beryllium", Element.EGroup.ALKALINE_EARTH_METAL, 9.01218315)
                .temperature(1560f, 2742f)
                .color(0xFFc2ff00)
                .packageAlkalineEarthMetal()
                .packageOre()
                .build();
        VANADIUM = createSubstance("vanadium")
                .element(23, "V", "vanadium", Element.EGroup.TRANSITION_METAL, 50.94151)
                .temperature(2183f, 3680f)
                .color(0xFFa6a6ab)
                .packageTransitionMetal()
                .packageOre()
                .build();
        TIN = createSubstance("tin")
                .element(50, "Sn", "tin", Element.EGroup.POST_TRANSITION_METAL, 118.7107)
                .temperature(505.08f, 2875f)
                .color(0xFF668080)
                .packagePostTransitionMetal()
                .packageOre()
                .build();
        COPPER = createSubstance("copper")
                .element(29, "Cu", "copper", Element.EGroup.TRANSITION_METAL, 63.5463)
                .temperature(1357.77f, 2835f)
                .color(0xFFc88033)
                .packageTransitionMetal()
                .packageOre()
                .build();
        THALLIUM = createSubstance("thallium")
                .element(81, "Tl", "thallium", Element.EGroup.POST_TRANSITION_METAL, 204.38)
                .temperature(577f, 1746f)
                .color(0xFFa6544d)
                .packagePostTransitionMetal()
                .build();
        FLUORINE = createSubstance("fluorine")
                .element(9, "F", "fluorine", Element.EGroup.HALOGEN, 18.9984031636)
                .temperature(53.48f, 85.03f)
                .color(0xFF90e050)
                .packageHalogen()
                .build();
        INDIUM = createSubstance("indium")
                .element(49, "In", "indium", Element.EGroup.POST_TRANSITION_METAL, 114.8181)
                .temperature(429.7485f, 2345f)
                .color(0xFFa67573)
                .packagePostTransitionMetal()
                .build();
        CADMIUM = createSubstance("cadmium")
                .element(48, "Cd", "cadmium", Element.EGroup.TRANSITION_METAL, 112.4144)
                .temperature(594.22f, 1040f)
                .color(0xFFffd98f)
                .packageTransitionMetal()
                .build();
        LITHIUM = createSubstance("lithium")
                .element(3, "Li", "lithium", Element.EGroup.ALKALI_METAL, 6.94)
                .temperature(453.65f, 1603f)
                .color(0xFFcc80ff)
                .packageAlkaliMetal()
                .build();
        SCANDIUM = createSubstance("scandium")
                .element(21, "Sc", "scandium", Element.EGroup.TRANSITION_METAL, 44.9559085)
                .temperature(1814f, 3109f)
                .color(0xFFe6e6e6)
                .packageTransitionMetal()
                .build();
        GERMANIUM = createSubstance("germanium")
                .element(32, "Ge", "germanium", Element.EGroup.METALLOID, 72.6308)
                .temperature(1211.4f, 3106f)
                .color(0xFF668f8f)
                .packageMetalloid()
                .build();
        TELLURIUM = createSubstance("tellurium")
                .element(52, "Te", "tellurium", Element.EGroup.METALLOID, 127.603)
                .temperature(722.66f, 1261f)
                .color(0xFFd47a00)
                .packageMetalloid()
                .build();
        MERCURY = createSubstance("mercury")
                .element(80, "Hg", "mercury", Element.EGroup.TRANSITION_METAL, 200.5923)
                .temperature(234.321f, 629.88f)
                .color(0xFFb8b8d0)
                .packageTransitionMetal()
                .build();
        RUBIDIUM = createSubstance("rubidium")
                .element(37, "Rb", "rubidium", Element.EGroup.ALKALI_METAL, 85.46783)
                .temperature(312.45f, 961f)
                .color(0xFF702eb0)
                .packageAlkaliMetal()
                .build();
        GOLD = createSubstance("gold")
                .element(79, "Au", "gold", Element.EGroup.TRANSITION_METAL, 196.9665695)
                .temperature(1337.33f, 3243f)
                .packageTransitionMetal()
                .packageOre()
                .tools(12.0f, 0.0f, 32, 0, 22)
                .overrides(BLOCK, "minecraft:gold_block", INGOT, "minecraft:gold_ingot", NUGGET, "minecraft:gold_nugget", HOE, "minecraft:golden_hoe", PICKAXE, "minecraft:golden_pickaxe", HOE, "minecraft:golden_hoe", SHOVEL, "minecraft:golden_shovel", SWORD, "minecraft:golden_sword", AXE, "minecraft:golden_axe")
                .color(0xFFffd123)
                .build();
        OSMIUM = createSubstance("osmium")
                .element(76, "Os", "osmium", Element.EGroup.TRANSITION_METAL, 190.233)
                .temperature(3306f, 5285f)
                .color(0xFF266696)
                .packageTransitionMetal()
                .packageOre()
                .build();
        IRIDIUM = createSubstance("iridium")
                .element(77, "Ir", "iridium", Element.EGroup.TRANSITION_METAL, 192.2173)
                .temperature(2719f, 4403f)
                .color(0xFF175487)
                .packageTransitionMetal()
                .packageOre()
                .build();
        YTTRIUM = createSubstance("yttrium")
                .element(39, "Y", "yttrium", Element.EGroup.TRANSITION_METAL, 88.905842)
                .temperature(1799f, 3203f)
                .color(0xFF94ffff)
                .packageTransitionMetal()
                .build();
        ARSENIC = createSubstance("arsenic")
                .element(33, "As", "arsenic", Element.EGroup.METALLOID, 74.9215956)
                .temperature(Float.NaN, Float.NaN)
                .color(0xFFbd80e3)
                .packageMetalloid()
                .build();
        BORON = createSubstance("boron")
                .element(5, "B", "boron", Element.EGroup.METALLOID, 10.81)
                .temperature(2349f, 4200f)
                .color(0xFFffb5b5)
                .packageMetalloid()
                .packageOre()
                .build();
        NIOBIUM = createSubstance("niobium")
                .element(41, "Nb", "niobium", Element.EGroup.TRANSITION_METAL, 92.906372)
                .temperature(2750f, 5017f)
                .color(0xFF73c2c9)
                .packageTransitionMetal()
                .packageOre()
                .build();
        RHENIUM = createSubstance("rhenium")
                .element(75, "Re", "rhenium", Element.EGroup.TRANSITION_METAL, 186.2071)
                .temperature(3459f, 5869f)
                .color(0xFF267dab)
                .packageTransitionMetal()
                .packageOre()
                .build();
        BARIUM = createSubstance("barium")
                .element(56, "Ba", "barium", Element.EGroup.ALKALINE_EARTH_METAL, 137.3277)
                .temperature(1000f, 2118f)
                .color(0xFF00c900)
                .packageAlkalineEarthMetal()
                .packageOre()
                .build();
        SILICON = createSubstance("silicon")
                .element(14, "Si", "silicon", Element.EGroup.METALLOID, 28.085)
                .temperature(1687f, 3538f)
                .color(0xFFf0c8a0)
                .packageMetalloid()
                .build();
        MANGANESE = createSubstance("manganese")
                .element(25, "Mn", "manganese", Element.EGroup.TRANSITION_METAL, 54.9380443)
                .temperature(1519f, 2334f)
                .color(0xFF9c7ac7)
                .packageTransitionMetal()
                .packageOre()
                .build();
        ALUMINUM = createSubstance("aluminum")
                .element(13, "Al", "aluminum", Element.EGroup.POST_TRANSITION_METAL, 26.98153857)
                .temperature(933.47f, 2743f)
                .tools(4.41f, 3f, 819, 3, 7)
                .color(0xFFbfa6a6)
                .packagePostTransitionMetal()
                .packageOre()
                .build();
        IRON = createSubstance("iron")
                .element(26, "Fe", "iron", Element.EGroup.TRANSITION_METAL, 55.8452)
                .temperature(1811f, 3134f)
                .packageTransitionMetal()
                .packageOre()
                .tools(6.0f, 2.0f, 250, 2, 14)
                .overrides(BLOCK, "minecraft:iron_block", INGOT, "minecraft:iron_ingot", NUGGET, "minecraft:iron_nugget", HOE, "minecraft:iron_hoe", PICKAXE, "minecraft:iron_pickaxe", HOE, "minecraft:iron_hoe", SHOVEL, "minecraft:iron_shovel", SWORD, "minecraft:iron_sword", AXE, "minecraft:iron_axe")
                .color(0xFFd4d4d4, 0xFFd4d4d4, 0xFFa31000)
                .shiny()
                .build();
        CHLORINE = createSubstance("chlorine")
                .element(17, "Cl", "chlorine", Element.EGroup.HALOGEN, 35.45)
                .temperature(171.6f, 239.11f)
                .color(0xFF1ff01f)
                .packageHalogen()
                .build();
        ANTIMONY = createSubstance("antimony")
                .element(51, "Sb", "antimony", Element.EGroup.METALLOID, 121.7601)
                .temperature(903.78f, 1908f)
                .color(0xFF9e63b5)
                .packageMetalloid()
                .packageOre()
                .build();
        GALLIUM = createSubstance("gallium")
                .element(31, "Ga", "gallium", Element.EGroup.POST_TRANSITION_METAL, 69.7231)
                .temperature(302.9146f, 2673f)
                .color(0xFFc28f8f)
                .packagePostTransitionMetal()
                .packageOre()
                .build();
        THORIUM = createSubstance("thorium")
                .element(90, "Th", "thorium", Element.EGroup.ACTINIDE, 232.03774)
                .temperature(2023f, 5061f)
                .color(0xFF00baff)
                .packageActinide()
                .packageOre()
                .build();
        TANTALUM = createSubstance("tantalum")
                .element(73, "Ta", "tantalum", Element.EGroup.TRANSITION_METAL, 180.947882)
                .temperature(3290f, 5731f)
                .packageTransitionMetal()
                .packageOre()
                .color(0xFF4da6ff)
                .build();
        PLATINUM = createSubstance("platinum")
                .element(78, "Pt", "platinum", Element.EGroup.TRANSITION_METAL, 195.0849)
                .temperature(2041.4f, 4098f)
                .color(0xFFd0d0e0)
                .packageTransitionMetal()
                .packageOre()
                .build();
        ZINC = createSubstance("zinc")
                .element(30, "Zn", "zinc", Element.EGroup.TRANSITION_METAL, 65.382)
                .temperature(692.68f, 1180f)
                .color(0xFF7d80b0)
                .packageTransitionMetal()
                .packageOre()
                .build();
        SELENIUM = createSubstance("selenium")
                .element(34, "Se", "selenium", Element.EGroup.NON_METAL, 78.9718)
                .temperature(494f, 958f)
                .color(0xFFffa100)
                .packageNonMetal()
                .packageOre()
                .build();
        LEAD = createSubstance("lead")
                .element(82, "Pb", "lead", Element.EGroup.POST_TRANSITION_METAL, 207.21)
                .temperature(600.61f, 2022f)
                .color(0xFF575961)
                .packagePostTransitionMetal()
                .packageOre()
                .build();
        CARBON = createSubstance("carbon")
                .element(6, "C", "carbon", Element.EGroup.NON_METAL, 12.011)
                .temperature(Float.NaN, Float.NaN)
                .color(0xFF909090)
                .packageNonMetal()
                .build();
        SODIUM = createSubstance("sodium")
                .element(11, "Na", "sodium", Element.EGroup.ALKALI_METAL, 22.989769282)
                .temperature(370.944f, 1156.09f)
                .packageAlkaliMetal()
                .color(0xFFab5cf2)
                .packageOre()
                .build();
        CALCIUM = createSubstance("calcium")
                .element(20, "Ca", "calcium", Element.EGroup.ALKALINE_EARTH_METAL, 40.0784)
                .temperature(1115f, 1757f)
                .packageAlkaliMetal()
                .packageOre()
                .color(0xFF3dff00)
                .build();
        POTASSIUM = createSubstance("potassium")
                .element(19, "K", "potassium", Element.EGroup.ALKALI_METAL, 39.09831)
                .temperature(336.7f, 1032f)
                .color(0xFF8f40d4)
                .packageAlkaliMetal()
                .packageOre()
                .build();
        SULFUR = createSubstance("sulfur")
                .element(16, "S", "sulfur", Element.EGroup.NON_METAL, 32.06)
                .temperature(388.36f, 717.8f)
                .color(0xFFffff30)
                .packageNonMetal()
                .packageOre()
                .build();
        RHODIUM = createSubstance("rhodium")
                .element(45, "Rh", "rhodium", Element.EGroup.TRANSITION_METAL, 102.905502)
                .temperature(2237f, 3968f)
                .color(0xFF0a7d8c)
                .packageTransitionMetal()
                .packageOre()
                .build();
        URANIUM = createSubstance("uranium")
                .element(92, "U", "uranium", Element.EGroup.ACTINIDE, 238.028913)
                .temperature(1405.3f, 4404f)
                .color(0xFF008fff)
                .packageActinide()
                .packageOre()
                .build();
        BISMUTH = createSubstance("bismuth")
                .element(83, "Bi", "bismuth", Element.EGroup.POST_TRANSITION_METAL, 208.980401)
                .temperature(544.7f, 1837f)
                .color(0xFF9e4fb5)
                .packagePostTransitionMetal()
                .build();
        CESIUM = createSubstance("cesium")
                .element(55, "Cs", "cesium", Element.EGroup.ALKALI_METAL, 132.905451966)
                .temperature(301.7f, 944f)
                .color(0xFF57178f)
                .packageAlkaliMetal()
                .build();
        HYDROGEN = createSubstance("hydrogen")
                .element(1, "H", "hydrogen", Element.EGroup.HALOGEN, 1.008)
                .temperature(13.99f, 20.271f)
                .color(0xFFffffff)
                .packageHalogen()
                .build();
    }

    private static SubstanceBuilder createSubstance(String name) {
        return new SubstanceBuilder(name);
    }

    @ZenMethod
    public static Substance get(String name) {
        return SUBSTANCES_REGISTRY.get(name);
    }

    @ZenMethod
    public static boolean remove(String name) {
        return SUBSTANCES_REGISTRY.remove(name);
    }

    public static void close() {
        SUBSTANCES_REGISTRY.close();
    }
}
