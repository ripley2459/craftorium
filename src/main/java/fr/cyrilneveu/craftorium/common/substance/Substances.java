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
import static fr.cyrilneveu.craftorium.api.utils.Utils.ERROR_COLOR;

@ZenClass("mods." + MODID + ".substance.Substances")
@ZenRegister
public final class Substances {
    public static final Registry<String, Substance> SUBSTANCES_REGISTRY = new Registry<>();

    public static final Substance HYDROGEN = createSubstance("hydrogen")
            .element(1, "H", "hydrogen", Element.EGroup.HALOGEN, 1.008)
            .temperature(13.99f, 20.271f)
            .color(0xFFffffff)
            .setHalogen()
            .build();
    public static final Substance HELIUM = createSubstance("helium")
            .element(2, "He", "helium", Element.EGroup.NOBLE_GAS, 4.0026022)
            .temperature(0.95f, 4.222f)
            .color(0xFFd9ffff)
            .setNobleGas()
            .build();
    public static final Substance LITHIUM = createSubstance("lithium")
            .element(3, "Li", "lithium", Element.EGroup.ALKALI_METAL, 6.94)
            .temperature(453.65f, 1603f)
            .color(0xFFcc80ff)
            .setAlkaliMetal()
            .build();
    public static final Substance BERYLLIUM = createSubstance("beryllium")
            .element(4, "Be", "beryllium", Element.EGroup.ALKALINE_EARTH_METAL, 9.01218315)
            .temperature(1560f, 2742f)
            .color(0xFFc2ff00)
            .setAlkalineEarthMetal()
            .build();
    public static final Substance BORON = createSubstance("boron")
            .element(5, "B", "boron", Element.EGroup.METALLOID, 10.81)
            .temperature(2349f, 4200f)
            .color(0xFFffb5b5)
            .setMetalloid()
            .build();
    public static final Substance CARBON = createSubstance("carbon")
            .element(6, "C", "carbon", Element.EGroup.NON_METAL, 12.011)
            .temperature(Float.NaN, Float.NaN)
            .color(0xFF909090)
            .setNonMetal()
            .build();
    public static final Substance NITROGEN = createSubstance("nitrogen")
            .element(7, "N", "nitrogen", Element.EGroup.HALOGEN, 14.007)
            .temperature(63.15f, 77.355f)
            .color(0xFF3050f8)
            .setHalogen()
            .build();
    public static final Substance OXYGEN = createSubstance("oxygen")
            .element(8, "O", "oxygen", Element.EGroup.HALOGEN, 15.999)
            .temperature(54.36f, 90.188f)
            .color(0xFFff0d0d)
            .setHalogen()
            .build();
    public static final Substance FLUORINE = createSubstance("fluorine")
            .element(9, "F", "fluorine", Element.EGroup.HALOGEN, 18.9984031636)
            .temperature(53.48f, 85.03f)
            .color(0xFF90e050)
            .setHalogen()
            .build();
    public static final Substance NEON = createSubstance("neon")
            .element(10, "Ne", "neon", Element.EGroup.NOBLE_GAS, 20.17976)
            .temperature(24.56f, 27.104f)
            .color(0xFFb3e3f5)
            .setNobleGas()
            .build();
    public static final Substance SODIUM = createSubstance("sodium")
            .element(11, "Na", "sodium", Element.EGroup.ALKALI_METAL, 22.989769282)
            .temperature(370.944f, 1156.09f)
            .color(0xFFab5cf2)
            .setAlkaliMetal()
            .build();
    public static final Substance MAGNESIUM = createSubstance("magnesium")
            .element(12, "Mg", "magnesium", Element.EGroup.ALKALINE_EARTH_METAL, 24.305)
            .temperature(923f, 1363f)
            .color(0xFF8aff00)
            .setAlkalineEarthMetal()
            .build();
    public static final Substance ALUMINIUM = createSubstance("aluminium")
            .element(13, "Al", "aluminium", Element.EGroup.POST_TRANSITION_METAL, 26.98153857)
            .temperature(933.47f, 2743f)
            .color(0xFFbfa6a6)
            .setPostTransitionMetal()
            .build();
    public static final Substance SILICON = createSubstance("silicon")
            .element(14, "Si", "silicon", Element.EGroup.METALLOID, 28.085)
            .temperature(1687f, 3538f)
            .color(0xFFf0c8a0)
            .setMetalloid()
            .build();
    public static final Substance PHOSPHORUS = createSubstance("phosphorus")
            .element(15, "P", "phosphorus", Element.EGroup.NON_METAL, 30.9737619985)
            .temperature(Float.NaN, Float.NaN)
            .color(0xFFff8000)
            .setNonMetal()
            .build();
    public static final Substance SULFUR = createSubstance("sulfur")
            .element(16, "S", "sulfur", Element.EGroup.NON_METAL, 32.06)
            .temperature(388.36f, 717.8f)
            .color(0xFFffff30)
            .setNonMetal()
            .build();
    public static final Substance CHLORINE = createSubstance("chlorine")
            .element(17, "Cl", "chlorine", Element.EGroup.HALOGEN, 35.45)
            .temperature(171.6f, 239.11f)
            .color(0xFF1ff01f)
            .setHalogen()
            .build();
    public static final Substance ARGON = createSubstance("argon")
            .element(18, "Ar", "argon", Element.EGroup.NOBLE_GAS, 39.9481)
            .temperature(83.81f, 87.302f)
            .color(0xFF80d1e3)
            .setNobleGas()
            .build();
    public static final Substance POTASSIUM = createSubstance("potassium")
            .element(19, "K", "potassium", Element.EGroup.ALKALI_METAL, 39.09831)
            .temperature(336.7f, 1032f)
            .color(0xFF8f40d4)
            .setAlkaliMetal()
            .build();
    public static final Substance CALCIUM = createSubstance("calcium")
            .element(20, "Ca", "calcium", Element.EGroup.ALKALINE_EARTH_METAL, 40.0784)
            .temperature(1115f, 1757f)
            .color(0xFF3dff00)
            .setAlkalineEarthMetal()
            .build();
    public static final Substance SCANDIUM = createSubstance("scandium")
            .element(21, "Sc", "scandium", Element.EGroup.TRANSITION_METAL, 44.9559085)
            .temperature(1814f, 3109f)
            .color(0xFFe6e6e6)
            .setTransitionMetal()
            .build();
    public static final Substance TITANIUM = createSubstance("titanium")
            .element(22, "Ti", "titanium", Element.EGroup.TRANSITION_METAL, 47.8671)
            .temperature(1941f, 3560f)
            .color(0xFFbfc2c7)
            .setTransitionMetal()
            .build();
    public static final Substance VANADIUM = createSubstance("vanadium")
            .element(23, "V", "vanadium", Element.EGroup.TRANSITION_METAL, 50.94151)
            .temperature(2183f, 3680f)
            .color(0xFFa6a6ab)
            .setTransitionMetal()
            .build();
    public static final Substance CHROMIUM = createSubstance("chromium")
            .element(24, "Cr", "chromium", Element.EGroup.TRANSITION_METAL, 51.99616)
            .temperature(2180f, 2944f)
            .color(0xFF8a99c7)
            .setTransitionMetal()
            .build();
    public static final Substance MANGANESE = createSubstance("manganese")
            .element(25, "Mn", "manganese", Element.EGroup.TRANSITION_METAL, 54.9380443)
            .temperature(1519f, 2334f)
            .color(0xFF9c7ac7)
            .setTransitionMetal()
            .build();
    public static final Substance IRON = createSubstance("iron")
            .element(26, "Fe", "iron", Element.EGroup.TRANSITION_METAL, 55.8452)
            .temperature(1811f, 3134f)
            .color(0xFFe06633)
            .setTransitionMetal()
            .shiny()
            .build();
    public static final Substance COBALT = createSubstance("cobalt")
            .element(27, "Co", "cobalt", Element.EGroup.TRANSITION_METAL, 58.9331944)
            .temperature(1768f, 3200f)
            .color(0xFFf090a0)
            .setTransitionMetal()
            .build();
    public static final Substance NICKEL = createSubstance("nickel")
            .element(28, "Ni", "nickel", Element.EGroup.TRANSITION_METAL, 58.69344)
            .temperature(1728f, 3003f)
            .color(0xFF50d050)
            .setTransitionMetal()
            .build();
    public static final Substance COPPER = createSubstance("copper")
            .element(29, "Cu", "copper", Element.EGroup.TRANSITION_METAL, 63.5463)
            .temperature(1357.77f, 2835f)
            .color(0xFFc88033)
            .setTransitionMetal()
            .build();
    public static final Substance ZINC = createSubstance("zinc")
            .element(30, "Zn", "zinc", Element.EGroup.TRANSITION_METAL, 65.382)
            .temperature(692.68f, 1180f)
            .color(0xFF7d80b0)
            .setTransitionMetal()
            .build();
    public static final Substance GALLIUM = createSubstance("gallium")
            .element(31, "Ga", "gallium", Element.EGroup.POST_TRANSITION_METAL, 69.7231)
            .temperature(302.9146f, 2673f)
            .color(0xFFc28f8f)
            .setPostTransitionMetal()
            .build();
    public static final Substance GERMANIUM = createSubstance("germanium")
            .element(32, "Ge", "germanium", Element.EGroup.METALLOID, 72.6308)
            .temperature(1211.4f, 3106f)
            .color(0xFF668f8f)
            .setMetalloid()
            .build();
    public static final Substance ARSENIC = createSubstance("arsenic")
            .element(33, "As", "arsenic", Element.EGroup.METALLOID, 74.9215956)
            .temperature(Float.NaN, Float.NaN)
            .color(0xFFbd80e3)
            .setMetalloid()
            .build();
    public static final Substance SELENIUM = createSubstance("selenium")
            .element(34, "Se", "selenium", Element.EGroup.NON_METAL, 78.9718)
            .temperature(494f, 958f)
            .color(0xFFffa100)
            .setNonMetal()
            .build();
    public static final Substance BROMINE = createSubstance("bromine")
            .element(35, "Br", "bromine", Element.EGroup.HALOGEN, 79.904)
            .temperature(265.8f, 332f)
            .color(0xFFa62929)
            .setHalogen()
            .build();
    public static final Substance KRYPTON = createSubstance("krypton")
            .element(36, "Kr", "krypton", Element.EGroup.NOBLE_GAS, 83.7982)
            .temperature(115.78f, 119.93f)
            .color(0xFF5cb8d1)
            .setNobleGas()
            .build();
    public static final Substance RUBIDIUM = createSubstance("rubidium")
            .element(37, "Rb", "rubidium", Element.EGroup.ALKALI_METAL, 85.46783)
            .temperature(312.45f, 961f)
            .color(0xFF702eb0)
            .setAlkaliMetal()
            .build();
    public static final Substance STRONTIUM = createSubstance("strontium")
            .element(38, "Sr", "strontium", Element.EGroup.ALKALINE_EARTH_METAL, 87.621)
            .temperature(1050f, 1650f)
            .color(0xFF00ff00)
            .setAlkalineEarthMetal()
            .build();
    public static final Substance YTTRIUM = createSubstance("yttrium")
            .element(39, "Y", "yttrium", Element.EGroup.TRANSITION_METAL, 88.905842)
            .temperature(1799f, 3203f)
            .color(0xFF94ffff)
            .setTransitionMetal()
            .build();
    public static final Substance ZIRCONIUM = createSubstance("zirconium")
            .element(40, "Zr", "zirconium", Element.EGroup.TRANSITION_METAL, 91.2242)
            .temperature(2128f, 4650f)
            .color(0xFF94e0e0)
            .setTransitionMetal()
            .build();
    public static final Substance NIOBIUM = createSubstance("niobium")
            .element(41, "Nb", "niobium", Element.EGroup.TRANSITION_METAL, 92.906372)
            .temperature(2750f, 5017f)
            .color(0xFF73c2c9)
            .setTransitionMetal()
            .build();
    public static final Substance MOLYBDENUM = createSubstance("molybdenum")
            .element(42, "Mo", "molybdenum", Element.EGroup.TRANSITION_METAL, 95.951)
            .temperature(2896f, 4912f)
            .color(0xFF54b5b5)
            .setTransitionMetal()
            .build();
    public static final Substance TECHNETIUM = createSubstance("technetium")
            .element(43, "Tc", "technetium", Element.EGroup.TRANSITION_METAL, 98)
            .temperature(2430f, 4538f)
            .color(0xFF3b9e9e)
            .setTransitionMetal()
            .build();
    public static final Substance RUTHENIUM = createSubstance("ruthenium")
            .element(44, "Ru", "ruthenium", Element.EGroup.TRANSITION_METAL, 101.072)
            .temperature(2607f, 4423f)
            .color(0xFF248f8f)
            .setTransitionMetal()
            .build();
    public static final Substance RHODIUM = createSubstance("rhodium")
            .element(45, "Rh", "rhodium", Element.EGroup.TRANSITION_METAL, 102.905502)
            .temperature(2237f, 3968f)
            .color(0xFF0a7d8c)
            .setTransitionMetal()
            .build();
    public static final Substance PALLADIUM = createSubstance("palladium")
            .element(46, "Pd", "palladium", Element.EGroup.TRANSITION_METAL, 106.421)
            .temperature(1828.05f, 3236f)
            .color(0xFF006985)
            .setTransitionMetal()
            .build();
    public static final Substance SILVER = createSubstance("silver")
            .element(47, "Ag", "silver", Element.EGroup.TRANSITION_METAL, 107.86822)
            .temperature(1234.93f, 2435f)
            .color(0xFFc0c0c0)
            .setTransitionMetal()
            .build();
    public static final Substance CADMIUM = createSubstance("cadmium")
            .element(48, "Cd", "cadmium", Element.EGroup.TRANSITION_METAL, 112.4144)
            .temperature(594.22f, 1040f)
            .color(0xFFffd98f)
            .setTransitionMetal()
            .build();
    public static final Substance INDIUM = createSubstance("indium")
            .element(49, "In", "indium", Element.EGroup.POST_TRANSITION_METAL, 114.8181)
            .temperature(429.7485f, 2345f)
            .color(0xFFa67573)
            .setPostTransitionMetal()
            .build();
    public static final Substance TIN = createSubstance("tin")
            .element(50, "Sn", "tin", Element.EGroup.POST_TRANSITION_METAL, 118.7107)
            .temperature(505.08f, 2875f)
            .color(0xFF668080)
            .setPostTransitionMetal()
            .build();
    public static final Substance ANTIMONY = createSubstance("antimony")
            .element(51, "Sb", "antimony", Element.EGroup.METALLOID, 121.7601)
            .temperature(903.78f, 1908f)
            .color(0xFF9e63b5)
            .setMetalloid()
            .build();
    public static final Substance TELLURIUM = createSubstance("tellurium")
            .element(52, "Te", "tellurium", Element.EGroup.METALLOID, 127.603)
            .temperature(722.66f, 1261f)
            .color(0xFFd47a00)
            .setMetalloid()
            .build();
    public static final Substance IODINE = createSubstance("iodine")
            .element(53, "I", "iodine", Element.EGroup.HALOGEN, 126.904473)
            .temperature(386.85f, 457.4f)
            .color(0xFF940094)
            .setHalogen()
            .build();
    public static final Substance XENON = createSubstance("xenon")
            .element(54, "Xe", "xenon", Element.EGroup.NOBLE_GAS, 131.2936)
            .temperature(161.4f, 165.051f)
            .color(0xFF429eb0)
            .setNobleGas()
            .build();
    public static final Substance CESIUM = createSubstance("cesium")
            .element(55, "Cs", "cesium", Element.EGroup.ALKALI_METAL, 132.905451966)
            .temperature(301.7f, 944f)
            .color(0xFF57178f)
            .setAlkaliMetal()
            .build();
    public static final Substance BARIUM = createSubstance("barium")
            .element(56, "Ba", "barium", Element.EGroup.ALKALINE_EARTH_METAL, 137.3277)
            .temperature(1000f, 2118f)
            .color(0xFF00c900)
            .setAlkalineEarthMetal()
            .build();
    public static final Substance LANTHANUM = createSubstance("lanthanum")
            .element(57, "La", "lanthanum", Element.EGroup.LANTHANIDE, 138.905477)
            .temperature(1193f, 3737f)
            .color(0xFF70d4ff)
            .setLanthanide()
            .build();
    public static final Substance CERIUM = createSubstance("cerium")
            .element(58, "Ce", "cerium", Element.EGroup.LANTHANIDE, 140.1161)
            .temperature(1068f, 3716f)
            .color(0xFFffffc7)
            .setLanthanide()
            .build();
    public static final Substance PRASEODYMIUM = createSubstance("praseodymium")
            .element(59, "Pr", "praseodymium", Element.EGroup.LANTHANIDE, 140.907662)
            .temperature(1208f, 3403f)
            .color(0xFFd9ffc7)
            .setLanthanide()
            .build();
    public static final Substance NEODYMIUM = createSubstance("neodymium")
            .element(60, "Nd", "neodymium", Element.EGroup.LANTHANIDE, 144.2423)
            .temperature(1297f, 3347f)
            .color(0xFFc7ffc7)
            .setLanthanide()
            .build();
    public static final Substance PROMETHIUM = createSubstance("promethium")
            .element(61, "Pm", "promethium", Element.EGroup.LANTHANIDE, 145)
            .temperature(1315f, 3273f)
            .color(0xFFa3ffc7)
            .setLanthanide()
            .build();
    public static final Substance SAMARIUM = createSubstance("samarium")
            .element(62, "Sm", "samarium", Element.EGroup.LANTHANIDE, 150.362)
            .temperature(1345f, 2173f)
            .color(0xFF8fffc7)
            .setLanthanide()
            .build();
    public static final Substance EUROPIUM = createSubstance("europium")
            .element(63, "Eu", "europium", Element.EGroup.LANTHANIDE, 151.9641)
            .temperature(1099f, 1802f)
            .color(0xFF61ffc7)
            .setLanthanide()
            .build();
    public static final Substance GADOLINIUM = createSubstance("gadolinium")
            .element(64, "Gd", "gadolinium", Element.EGroup.LANTHANIDE, 157.253)
            .temperature(1585f, 3273f)
            .color(0xFF45ffc7)
            .setLanthanide()
            .build();
    public static final Substance TERBIUM = createSubstance("terbium")
            .element(65, "Tb", "terbium", Element.EGroup.LANTHANIDE, 158.925352)
            .temperature(1629f, 3396f)
            .color(0xFF30ffc7)
            .setLanthanide()
            .build();
    public static final Substance DYSPROSIUM = createSubstance("dysprosium")
            .element(66, "Dy", "dysprosium", Element.EGroup.LANTHANIDE, 162.5001)
            .temperature(1680f, 2840f)
            .color(0xFF1fffc7)
            .setLanthanide()
            .build();
    public static final Substance HOLMIUM = createSubstance("holmium")
            .element(67, "Ho", "holmium", Element.EGroup.LANTHANIDE, 164.930332)
            .temperature(1734f, 2873f)
            .color(0xFF00ff9c)
            .setLanthanide()
            .build();
    public static final Substance ERBIUM = createSubstance("erbium")
            .element(68, "Er", "erbium", Element.EGroup.LANTHANIDE, 167.2593)
            .temperature(1802f, 3141f)
            .color(0xFF00e675)
            .setLanthanide()
            .build();
    public static final Substance THULIUM = createSubstance("thulium")
            .element(69, "Tm", "thulium", Element.EGroup.LANTHANIDE, 168.934222)
            .temperature(1818f, 2223f)
            .color(0xFF00d452)
            .setLanthanide()
            .build();
    public static final Substance YTTERBIUM = createSubstance("ytterbium")
            .element(70, "Yb", "ytterbium", Element.EGroup.LANTHANIDE, 173.0451)
            .temperature(1097f, 1469f)
            .color(0xFF00bf38)
            .setLanthanide()
            .build();
    public static final Substance LUTETIUM = createSubstance("lutetium")
            .element(71, "Lu", "lutetium", Element.EGroup.LANTHANIDE, 174.96681)
            .temperature(1925f, 3675f)
            .color(0xFF00ab24)
            .setLanthanide()
            .build();
    public static final Substance HAFNIUM = createSubstance("hafnium")
            .element(72, "Hf", "hafnium", Element.EGroup.TRANSITION_METAL, 178.492)
            .temperature(2506f, 4876f)
            .color(0xFF4dc2ff)
            .setTransitionMetal()
            .build();
    public static final Substance TANTALUM = createSubstance("tantalum")
            .element(73, "Ta", "tantalum", Element.EGroup.TRANSITION_METAL, 180.947882)
            .temperature(3290f, 5731f)
            .color(0xFF4da6ff)
            .setTransitionMetal()
            .build();
    public static final Substance TUNGSTEN = createSubstance("tungsten")
            .element(74, "W", "tungsten", Element.EGroup.TRANSITION_METAL, 183.841)
            .temperature(3695f, 6203f)
            .color(0xFF2194d6)
            .setTransitionMetal()
            .build();
    public static final Substance RHENIUM = createSubstance("rhenium")
            .element(75, "Re", "rhenium", Element.EGroup.TRANSITION_METAL, 186.2071)
            .temperature(3459f, 5869f)
            .color(0xFF267dab)
            .setTransitionMetal()
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
    public static final Substance PLATINUM = createSubstance("platinum")
            .element(78, "Pt", "platinum", Element.EGroup.TRANSITION_METAL, 195.0849)
            .temperature(2041.4f, 4098f)
            .color(0xFFd0d0e0)
            .setTransitionMetal()
            .build();
    public static final Substance GOLD = createSubstance("gold")
            .element(79, "Au", "gold", Element.EGroup.TRANSITION_METAL, 196.9665695)
            .temperature(1337.33f, 3243f)
            .color(0xFFffd123)
            .setTransitionMetal()
            .build();
    public static final Substance MERCURY = createSubstance("mercury")
            .element(80, "Hg", "mercury", Element.EGroup.TRANSITION_METAL, 200.5923)
            .temperature(234.321f, 629.88f)
            .color(0xFFb8b8d0)
            .setTransitionMetal()
            .build();
    public static final Substance THALLIUM = createSubstance("thallium")
            .element(81, "Tl", "thallium", Element.EGroup.POST_TRANSITION_METAL, 204.38)
            .temperature(577f, 1746f)
            .color(0xFFa6544d)
            .setPostTransitionMetal()
            .build();
    public static final Substance LEAD = createSubstance("lead")
            .element(82, "Pb", "lead", Element.EGroup.POST_TRANSITION_METAL, 207.21)
            .temperature(600.61f, 2022f)
            .color(0xFF575961)
            .setPostTransitionMetal()
            .build();
    public static final Substance BISMUTH = createSubstance("bismuth")
            .element(83, "Bi", "bismuth", Element.EGroup.POST_TRANSITION_METAL, 208.980401)
            .temperature(544.7f, 1837f)
            .color(0xFF9e4fb5)
            .setPostTransitionMetal()
            .build();
    public static final Substance POLONIUM = createSubstance("polonium")
            .element(84, "Po", "polonium", Element.EGroup.POST_TRANSITION_METAL, 209)
            .temperature(527f, 1235f)
            .color(0xFFab5c00)
            .setPostTransitionMetal()
            .build();
    public static final Substance ASTATINE = createSubstance("astatine")
            .element(85, "At", "astatine", Element.EGroup.METALLOID, 210)
            .temperature(575f, 610f)
            .color(0xFF754f45)
            .setMetalloid()
            .build();
    public static final Substance RADON = createSubstance("radon")
            .element(86, "Rn", "radon", Element.EGroup.NOBLE_GAS, 222)
            .temperature(202f, 211.5f)
            .color(0xFF428296)
            .setNobleGas()
            .build();
    public static final Substance FRANCIUM = createSubstance("francium")
            .element(87, "Fr", "francium", Element.EGroup.ALKALI_METAL, 223)
            .temperature(300f, 950f)
            .color(0xFF420066)
            .setAlkaliMetal()
            .build();
    public static final Substance RADIUM = createSubstance("radium")
            .element(88, "Ra", "radium", Element.EGroup.ALKALINE_EARTH_METAL, 226)
            .temperature(1233f, 2010f)
            .color(0xFF007d00)
            .setAlkalineEarthMetal()
            .build();
    public static final Substance ACTINIUM = createSubstance("actinium")
            .element(89, "Ac", "actinium", Element.EGroup.ACTINIDE, 227)
            .temperature(1500f, 3500f)
            .color(0xFF70abfa)
            .setActinide()
            .build();
    public static final Substance THORIUM = createSubstance("thorium")
            .element(90, "Th", "thorium", Element.EGroup.ACTINIDE, 232.03774)
            .temperature(2023f, 5061f)
            .color(0xFF00baff)
            .setActinide()
            .build();
    public static final Substance PROTACTINIUM = createSubstance("protactinium")
            .element(91, "Pa", "protactinium", Element.EGroup.ACTINIDE, 231.035882)
            .temperature(1841f, 4300f)
            .color(0xFF00a1ff)
            .setActinide()
            .build();
    public static final Substance URANIUM = createSubstance("uranium")
            .element(92, "U", "uranium", Element.EGroup.ACTINIDE, 238.028913)
            .temperature(1405.3f, 4404f)
            .color(0xFF008fff)
            .setActinide()
            .build();
    public static final Substance NEPTUNIUM = createSubstance("neptunium")
            .element(93, "Np", "neptunium", Element.EGroup.ACTINIDE, 237)
            .temperature(912f, 4447f)
            .color(0xFF0080ff)
            .setActinide()
            .build();
    public static final Substance PLUTONIUM = createSubstance("plutonium")
            .element(94, "Pu", "plutonium", Element.EGroup.ACTINIDE, 244)
            .temperature(912.5f, 3505f)
            .color(0xFF006bff)
            .setActinide()
            .build();
    public static final Substance AMERICIUM = createSubstance("americium")
            .element(95, "Am", "americium", Element.EGroup.ACTINIDE, 243)
            .temperature(1449f, 2880f)
            .color(0xFF545cf2)
            .setActinide()
            .build();
    public static final Substance CURIUM = createSubstance("curium")
            .element(96, "Cm", "curium", Element.EGroup.ACTINIDE, 247)
            .temperature(1613f, 3383f)
            .color(0xFF785ce3)
            .setActinide()
            .build();
    public static final Substance BERKELIUM = createSubstance("berkelium")
            .element(97, "Bk", "berkelium", Element.EGroup.ACTINIDE, 247)
            .temperature(1259f, 2900f)
            .color(0xFF8a4fe3)
            .setActinide()
            .build();
    public static final Substance CALIFORNIUM = createSubstance("californium")
            .element(98, "Cf", "californium", Element.EGroup.ACTINIDE, 251)
            .temperature(1173f, 1743f)
            .color(0xFFa136d4)
            .setActinide()
            .build();
    public static final Substance EINSTEINIUM = createSubstance("einsteinium")
            .element(99, "Es", "einsteinium", Element.EGroup.ACTINIDE, 252)
            .temperature(1133f, 1269f)
            .color(0xFFb31fd4)
            .setActinide()
            .build();
    public static final Substance FERMIUM = createSubstance("fermium")
            .element(100, "Fm", "fermium", Element.EGroup.ACTINIDE, 257)
            .temperature(1800f, Float.NaN)
            .color(0xFFb31fba)
            .setActinide()
            .build();
    public static final Substance MENDELEVIUM = createSubstance("mendelevium")
            .element(101, "Md", "mendelevium", Element.EGroup.ACTINIDE, 258)
            .temperature(1100f, Float.NaN)
            .color(0xFFb30da6)
            .setActinide()
            .build();
    public static final Substance NOBELIUM = createSubstance("nobelium")
            .element(102, "No", "nobelium", Element.EGroup.ACTINIDE, 259)
            .temperature(1100f, Float.NaN)
            .color(0xFFbd0d87)
            .setActinide()
            .build();
    public static final Substance LAWRENCIUM = createSubstance("lawrencium")
            .element(103, "Lr", "lawrencium", Element.EGroup.ACTINIDE, 266)
            .temperature(1900f, Float.NaN)
            .color(0xFFc70066)
            .setActinide()
            .build();
    public static final Substance RUTHERFORDIUM = createSubstance("rutherfordium")
            .element(104, "Rf", "rutherfordium", Element.EGroup.TRANSITION_METAL, 267)
            .temperature(2400f, 5800f)
            .color(0xFFcc0059)
            .setTransitionMetal()
            .build();
    public static final Substance DUBNIUM = createSubstance("dubnium")
            .element(105, "Db", "dubnium", Element.EGroup.TRANSITION_METAL, 268)
            .temperature(Float.NaN, Float.NaN)
            .color(0xFFd1004f)
            .setTransitionMetal()
            .build();
    public static final Substance SEABORGIUM = createSubstance("seaborgium")
            .element(106, "Sg", "seaborgium", Element.EGroup.TRANSITION_METAL, 269)
            .temperature(Float.NaN, Float.NaN)
            .color(0xFFd90045)
            .setTransitionMetal()
            .build();
    public static final Substance BOHRIUM = createSubstance("bohrium")
            .element(107, "Bh", "bohrium", Element.EGroup.TRANSITION_METAL, 270)
            .temperature(Float.NaN, Float.NaN)
            .color(0xFFe00038)
            .setTransitionMetal()
            .build();
    public static final Substance HASSIUM = createSubstance("hassium")
            .element(108, "Hs", "hassium", Element.EGroup.TRANSITION_METAL, 269)
            .temperature(126f, Float.NaN)
            .color(0xFFe6002e)
            .setTransitionMetal()
            .build();
    public static final Substance MEITNERIUM = createSubstance("meitnerium")
            .element(109, "Mt", "meitnerium", Element.EGroup.UNKNOWN, 278)
            .temperature(Float.NaN, Float.NaN)
            .color(0xFFeb0026)
            .setUnknown()
            .build();
    public static final Substance DARMSTADTIUM = createSubstance("darmstadtium")
            .element(110, "Ds", "darmstadtium", Element.EGroup.UNKNOWN, 281)
            .temperature(Float.NaN, Float.NaN)
            .color(ERROR_COLOR)
            .setUnknown()
            .build();
    public static final Substance ROENTGENIUM = createSubstance("roentgenium")
            .element(111, "Rg", "roentgenium", Element.EGroup.UNKNOWN, 282)
            .temperature(Float.NaN, Float.NaN)
            .color(ERROR_COLOR)
            .setUnknown()
            .build();
    public static final Substance COPERNICIUM = createSubstance("copernicium")
            .element(112, "Cn", "copernicium", Element.EGroup.TRANSITION_METAL, 285)
            .temperature(Float.NaN, 3570f)
            .color(ERROR_COLOR)
            .setTransitionMetal()
            .build();
    public static final Substance NIHONIUM = createSubstance("nihonium")
            .element(113, "Nh", "nihonium", Element.EGroup.UNKNOWN, 286)
            .temperature(700f, 1430f)
            .color(ERROR_COLOR)
            .setUnknown()
            .build();
    public static final Substance FLEROVIUM = createSubstance("flerovium")
            .element(114, "Fl", "flerovium", Element.EGroup.POST_TRANSITION_METAL, 289)
            .temperature(340f, 420f)
            .color(ERROR_COLOR)
            .setPostTransitionMetal()
            .build();
    public static final Substance MOSCOVIUM = createSubstance("moscovium")
            .element(115, "Mc", "moscovium", Element.EGroup.UNKNOWN, 289)
            .temperature(670f, 1400f)
            .color(ERROR_COLOR)
            .setUnknown()
            .build();
    public static final Substance LIVERMORIUM = createSubstance("livermorium")
            .element(116, "Lv", "livermorium", Element.EGroup.UNKNOWN, 293)
            .temperature(709f, 1085f)
            .color(ERROR_COLOR)
            .setUnknown()
            .build();
    public static final Substance TENNESSINE = createSubstance("tennessine")
            .element(117, "Ts", "tennessine", Element.EGroup.UNKNOWN, 294)
            .temperature(723f, 883f)
            .color(ERROR_COLOR)
            .setUnknown()
            .build();
    public static final Substance OGANESSON = createSubstance("oganesson")
            .element(118, "Og", "oganesson", Element.EGroup.UNKNOWN, 294)
            .temperature(Float.NaN, 350f)
            .color(ERROR_COLOR)
            .setUnknown()
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
