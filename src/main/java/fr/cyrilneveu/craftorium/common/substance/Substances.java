package fr.cyrilneveu.craftorium.common.substance;

import crafttweaker.CraftTweakerAPI;
import crafttweaker.annotations.ZenRegister;
import fr.cyrilneveu.craftorium.api.substance.Element;
import fr.cyrilneveu.craftorium.api.substance.Substance;
import fr.cyrilneveu.craftorium.api.substance.SubstanceBuilder;
import fr.cyrilneveu.craftorium.api.utils.Registry;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import static fr.cyrilneveu.craftorium.CraftoriumTags.MODID;

@ZenClass("mods." + MODID + ".substance.Substances")
@ZenRegister
public final class Substances {
    public static final Registry<String, Substance> SUBSTANCES_REGISTRY = new Registry<>();

    public static final Substance TIN = createSubstance("tin")
            .element(50, "Sn", "tin", Element.EGroup.POST_TRANSITION_METAL, 118.7107)
            .temperature(505.08f, 2875f)
            .color(0xFF668080)
            .setPostTransitionMetal()
            .build();
    public static final Substance COPPER = createSubstance("copper")
            .element(29, "Cu", "copper", Element.EGroup.TRANSITION_METAL, 63.5463)
            .temperature(1357.77f, 2835f)
            .color(0xFFc88033)
            .setTransitionMetal()
            .build();
    public static final Substance BRONZE = createSubstance("bronze")
            .composition(COPPER, 3, TIN, 1)
            .tools(4.0f, 6.0f, 325, 3, 3)
            .color(0xFFf5a945)
            .shiny()
            .build();
    public static final Substance ALUMINIUM = createSubstance("aluminium")
            .element(13, "Al", "aluminium", Element.EGroup.POST_TRANSITION_METAL, 26.98153857)
            .temperature(933.47f, 2743f)
            .color(0xFFbfa6a6)
            .setPostTransitionMetal()
            .build();
    public static final Substance IRON = createSubstance("iron")
            .element(26, "Fe", "iron", Element.EGroup.TRANSITION_METAL, 55.8452)
            .temperature(1811f, 3134f)
            .setTransitionMetal()
            .tools(4.0f, 2.0f, 250, 3, 3)
            .color(0xFFd4d4d4, 0xFFd4d4d4, 0xFFa31000)
            .shiny()
            .build();
    public static final Substance CARBON = createSubstance("carbon")
            .element(6, "C", "carbon", Element.EGroup.NON_METAL, 12.011)
            .temperature(Float.NaN, Float.NaN)
            .color(0xFF909090)
            .setNonMetal()
            .build();
    public static final Substance STEEL = createSubstance("steel")
            .composition(IRON, 1, CARBON, 3)
            .setMetalExtended()
            .build();
    public static final Substance GRAPHITE = createSubstance("graphite")
            .composition(CARBON, 1)
            .setMetalExtended()
            .build();
    public static final Substance MANGANESE = createSubstance("manganese")
            .element(25, "Mn", "manganese", Element.EGroup.TRANSITION_METAL, 54.9380443)
            .temperature(1519f, 2334f)
            .color(0xFF9c7ac7)
            .setTransitionMetal()
            .build();
    public static final Substance HSLA_STEEL = createSubstance("hsla_steel")
            .composition(IRON, 4, GRAPHITE, 1, MANGANESE, 1)
            .setMetalExtended()
            .build();
    public static final Substance GOLD = createSubstance("gold")
            .element(79, "Au", "gold", Element.EGroup.TRANSITION_METAL, 196.9665695)
            .temperature(1337.33f, 3243f)
            .setTransitionMetal()
            .tools(4.0f, 2.0f, 32, 3, 3)
            .color(0xFFffd123)
            .build();
    public static final Substance SILVER = createSubstance("silver")
            .element(47, "Ag", "silver", Element.EGroup.TRANSITION_METAL, 107.86822)
            .temperature(1234.93f, 2435f)
            .setTransitionMetal()
            .color(0xFFc0c0c0)
            .build();
    public static final Substance ELECTRUM = createSubstance("electrum")
            .composition(GOLD, 1, SILVER, 1)
            .setMetalExtended()
            .shiny()
            .build();
    public static final Substance NICKEL = createSubstance("nickel")
            .element(28, "Ni", "nickel", Element.EGroup.TRANSITION_METAL, 58.69344)
            .temperature(1728f, 3003f)
            .color(0xFF50d050)
            .setTransitionMetal()
            .build();
    public static final Substance CUPRONICKEL = createSubstance("cupronickel")
            .composition(COPPER, 1, NICKEL, 1)
            .setMetalExtended()
            .build();
    public static final Substance TUNGSTEN = createSubstance("tungsten")
            .element(74, "W", "tungsten", Element.EGroup.TRANSITION_METAL, 183.841)
            .temperature(3695f, 6203f)
            .color(0xFF2194d6)
            .setTransitionMetal()
            .build();
    public static final Substance TUNGSTEN_STEEL = createSubstance("tungsten_steel")
            .composition(TUNGSTEN, 1, STEEL, 1)
            .setMetalExtended()
            .tools(5.0f, 4.0f, 5300, 3, 2)
            .build();
    public static final Substance TUNGSTEN_CARBIDE_ALLOY = createSubstance("tungsten_carbide_alloy")
            .composition(TUNGSTEN, 1, CARBON, 1)
            .setMetalExtended()
            .build();
    public static final Substance MAGNESIUM = createSubstance("magnesium")
            .element(12, "Mg", "magnesium", Element.EGroup.ALKALINE_EARTH_METAL, 24.305)
            .temperature(923f, 1363f)
            .color(0xFF8aff00)
            .setAlkalineEarthMetal()
            .build();
    public static final Substance CHROMIUM = createSubstance("chromium")
            .element(24, "Cr", "chromium", Element.EGroup.TRANSITION_METAL, 51.99616)
            .temperature(2180f, 2944f)
            .color(0xFF8a99c7)
            .setTransitionMetal()
            .build();
    public static final Substance STAINLESS_STEEL = createSubstance("stainless_steel")
            .composition(IRON, 4, MAGNESIUM, 3, MANGANESE, 1, CHROMIUM, 1)
            .setMetalExtended()
            .build();
    public static final Substance NICHROME = createSubstance("nichrome")
            .composition(NICKEL, 2, CHROMIUM, 8)
            .setMetalExtended()
            .build();
    public static final Substance RUTHENIUM = createSubstance("ruthenium")
            .element(44, "Ru", "ruthenium", Element.EGroup.TRANSITION_METAL, 101.072)
            .temperature(2607f, 4423f)
            .color(0xFF248f8f)
            .setTransitionMetal()
            .build();
    public static final Substance MOLYBDENUM = createSubstance("molybdenum")
            .element(42, "Mo", "molybdenum", Element.EGroup.TRANSITION_METAL, 95.951)
            .temperature(2896f, 4912f)
            .color(0xFF54b5b5)
            .setTransitionMetal()
            .build();
    public static final Substance RTM_ALLOY = createSubstance("rtm_alloy")
            .composition(RUTHENIUM, 4, TUNGSTEN, 2, MOLYBDENUM, 1)
            .setMetalExtended()
            .shiny()
            .build();
    public static final Substance COBALT = createSubstance("cobalt")
            .element(27, "Co", "cobalt", Element.EGroup.TRANSITION_METAL, 58.9331944)
            .temperature(1768f, 3200f)
            .color(0xFFf090a0)
            .setTransitionMetal()
            .build();
    public static final Substance TITANIUM = createSubstance("titanium")
            .element(22, "Ti", "titanium", Element.EGroup.TRANSITION_METAL, 47.8671)
            .temperature(1941f, 3560f)
            .color(0xFFbfc2c7)
            .setTransitionMetal()
            .build();
    public static final Substance HASTE_ALLOY = createSubstance("haste_alloy")
            .composition(NICKEL, 2, COBALT, 2, IRON, 2, TITANIUM, 1, ALUMINIUM, 1)
            .setMetalExtended()
            .build();
    public static final Substance BORON = createSubstance("boron")
            .element(5, "B", "boron", Element.EGroup.METALLOID, 10.81)
            .temperature(2349f, 4200f)
            .color(0xFFffb5b5)
            .setMetalloid()
            .build();
    public static final Substance MAGNESIUM_DIBORIDE_ALLOY = createSubstance("magnesium_diboride_alloy")
            .composition(MAGNESIUM, 1, BORON, 2)
            .setMetalExtended()
            .build();
    public static final Substance ARSENIC = createSubstance("arsenic")
            .element(33, "As", "arsenic", Element.EGroup.METALLOID, 74.9215956)
            .temperature(Float.NaN, Float.NaN)
            .color(0xFFbd80e3)
            .setMetalloid()
            .build();
    public static final Substance BORON_ARSENIDE_ALLOY = createSubstance("boron_arsenide_alloy")
            .composition(BORON, 1, ARSENIC, 1)
            .setMetalExtended()
            .build();
    public static final Substance NIOBIUM = createSubstance("niobium")
            .element(41, "Nb", "niobium", Element.EGroup.TRANSITION_METAL, 92.906372)
            .temperature(2750f, 5017f)
            .color(0xFF73c2c9)
            .setTransitionMetal()
            .build();
    public static final Substance RHENIUM = createSubstance("rhenium")
            .element(75, "Re", "rhenium", Element.EGroup.TRANSITION_METAL, 186.2071)
            .temperature(3459f, 5869f)
            .color(0xFF267dab)
            .setTransitionMetal()
            .build();
    public static final Substance SUPER_ALLOY = createSubstance("super_alloy")
            .composition(HASTE_ALLOY, 2, CARBON, 2, NIOBIUM, 1, RHENIUM, 1, TUNGSTEN, 1)
            .setMetalExtended()
            .build();
    public static final Substance OSMIUM = createSubstance("osmium")
            .element(76, "Os", "osmium", Element.EGroup.TRANSITION_METAL, 190.233)
            .temperature(3306f, 5285f)
            .color(0xFF266696)
            .setTransitionMetal()
            .build();
    public static final Substance IRIDIUM = createSubstance("iridium")
            .element(77, "Ir", "iridium", Element.EGroup.TRANSITION_METAL, 192.2173)
            .temperature(2719f, 4403f)
            .color(0xFF175487)
            .setTransitionMetal()
            .build();
    public static final Substance OSMIRIDIUM = createSubstance("osmiridium")
            .composition(OSMIUM, 6, IRIDIUM, 3)
            .setMetalExtended()
            .build();
    public static final Substance YTTRIUM = createSubstance("yttrium")
            .element(39, "Y", "yttrium", Element.EGroup.TRANSITION_METAL, 88.905842)
            .temperature(1799f, 3203f)
            .color(0xFF94ffff)
            .setTransitionMetal()
            .build();
    public static final Substance BARIUM = createSubstance("barium")
            .element(56, "Ba", "barium", Element.EGroup.ALKALINE_EARTH_METAL, 137.3277)
            .temperature(1000f, 2118f)
            .color(0xFF00c900)
            .setAlkalineEarthMetal()
            .build();
    public static final Substance YBCO = createSubstance("ybco")
            .composition(YTTRIUM, 1, BARIUM, 2, COPPER, 3)
            .setMetalExtended()
            .build();
    public static final Substance SILICON = createSubstance("silicon")
            .element(14, "Si", "silicon", Element.EGroup.METALLOID, 28.085)
            .temperature(1687f, 3538f)
            .color(0xFFf0c8a0)
            .setMetalloid()
            .build();
    public static final Substance GRAPHENE = createSubstance("graphene")
            .composition(GRAPHITE, 2, SILICON, 1, CARBON, 3)
            .setMetalExtended()
            .build();

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

    @ZenMethod
    public static void list() {
        CraftTweakerAPI.logInfo("Registered substances:");
        SUBSTANCES_REGISTRY.getAll().forEach((n, s) -> CraftTweakerAPI.logInfo(" - " + s.getName()));
    }
}
