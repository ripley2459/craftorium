package fr.cyrilneveu.craftorium.common.world;

import fr.cyrilneveu.craftorium.api.utils.Registry;
import fr.cyrilneveu.craftorium.api.utils.WeightedList;
import fr.cyrilneveu.craftorium.api.world.vein.Vein;
import fr.cyrilneveu.craftorium.api.world.vein.VeinBuilder;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import static fr.cyrilneveu.craftorium.common.substance.Substances.*;

public final class Veins {
    public static final Registry<String, Vein> VEINS_REGISTRY = new Registry<>();

    public static final Vein VEIN_BAUXITE = createVein("bauxite", 10, 80, 5, 5, 80, 0, BAUXITE, 12, ALUMINUM, 2, GALLIUM, 1).build();
    public static final Vein VEIN_BARITE = createVein("barite", 80, 120, 5, 5, 30, 0, BARITE, 12, BARIUM, 2, SULFUR, 2).build();
    public static final Vein VEIN_STIBNITE = createVein("stibnite", 80, 120, 5, 5, 70, 0, STIBNITE, 12, ANTIMONY, 2, SULFUR, 2).build();
    public static final Vein VEIN_BERYL = createVein("beryl", 5, 30, 5, 5, 15, 0, BERYL, 12, EMERALD, 2, THORIUM, 2, BERYLLIUM, 1, BORON, 2).build();
    public static final Vein VEIN_BISMUTHINITE = createVein("bismuthinite", 40, 60, 5, 5, 60, 0, BISMUTHINITE, 6, SULFUR, 2).build();
    public static final Vein VEIN_CHROMITE = createVein("chromite", 10, 70, 5, 5, 16, 0, CHROMITE, 6, IRON, 2, CHROMIUM, 2).build();
    public static final Vein VEIN_SMALTITE = createVein("smaltite", 40, 60, 5, 5, 60, 0, SMALTITE, 6, COBALT, 1).build();
    public static final Vein VEIN_CARROLLITE = createVein("carrollite", 40, 60, 5, 5, 60, 0, CARROLLITE, 8, COPPER, 1, COBALT, 1, SULFUR, 1).build();
    public static final Vein VEIN_CHALCOPYRITE = createVein("chalcopyrite", 80, 200, 5, 5, 80, 0, CHALCOPYRITE, 15, COPPER, 3, IRON, 1, SULFUR, 1).build();
    public static final Vein VEIN_NATIVE_COPPER = createVein("native_copper", 80, 120, 5, 5, 70, 0, COPPER, 1).build();
    public static final Vein VEIN_MALACHITE = createVein("malachite", 10, 40, 5, 5, 15, 0, MALACHITE, 8, IRON, 1, COPPER, 1).build();
    public static final Vein VEIN_AZURITE = createVein("azurite", 20, 50, 5, 5, 15, 0, AZURITE, 3, LAPIS_LAZULI, 1).build();
    public static final Vein VEIN_LAZURITE = createVein("lazurite", 20, 50, 5, 5, 15, 0, LAZURITE, 1, LAPIS_LAZULI, 3).build();
    public static final Vein VEIN_SODALITE = createVein("sodalite", 30, 60, 5, 5, 10, 0, SODALITE, 1).build();
    public static final Vein VEIN_NATIVE_GOLD = createVein("native_gold", 15, 40, 4, 4, 30, 0, GOLD, 20, PLATINUM, 1, IRIDIUM, 1, OSMIUM, 1).build();
    public static final Vein VEIN_NATIVE_PLATINUM = createVein("native_platinum", 15, 40, 2, 2, 30, 0, PLATINUM, 10, IRIDIUM, 1, OSMIUM, 1).build();
    public static final Vein VEIN_NATIVE_OSMIUM = createVein("native_osmium", 15, 40, 2, 2, 30, 0, PLATINUM, 1, IRIDIUM, 1, OSMIUM, 10).build();
    public static final Vein VEIN_NATIVE_IRIDIUM = createVein("native_iridium", 15, 40, 2, 2, 30, 0, PLATINUM, 1, IRIDIUM, 10, OSMIUM, 1).build();
    public static final Vein VEIN_HEMATITE = createVein("hematite", 10, 50, 5, 5, 60, 0, HEMATITE, 6, IRON, 1).build();
    public static final Vein VEIN_MAGNETITE = createVein("magnetite", 10, 50, 5, 5, 70, 0, MAGNETITE, 14, CHROMITE, 1, GOLD, 1, IRON, 1).build();
    public static final Vein VEIN_GALENA = createVein("galena", 5, 35, 5, 5, 40, 0, GALENA, 6, SILVER, 1, SULFUR, 1).build();
    public static final Vein VEIN_CERUSSITE = createVein("cerussite", 10, 30, 5, 5, 40, 0, CERUSSITE, 8, LEAD, 1, COPPER, 1, ZINC, 1).build();
    public static final Vein VEIN_SPDODUMENE = createVein("spdodumene", 5, 40, 5, 5, 60, 0, SPDODUMENE, 14, IRON, 1, MANGANESE, 1, MAGNESIUM, 1, CALCIUM, 1, SODIUM, 1, POTASSIUM, 1).build();
    public static final Vein VEIN_PYROLUSITE = createVein("pyrolusite", 20, 30, 5, 5, 20, 0, PYROLUSITE, 6, MANGANESE, 4).build();
    public static final Vein VEIN_CINNABAR = createVein("cinnabar", 5, 35, 5, 5, 16, 0, CINNABAR, 2, REDSTONE, 2).build();
    public static final Vein VEIN_MOLYBDENITE = createVein("molybdenite", 20, 50, 5, 5, 5, 0, MOLYBDENITE, 15, RHENIUM, 1, RHODIUM, 1, SELENIUM, 1, SILVER, 2, MOLYBDENUM, 3).build();
    public static final Vein VEIN_PENTLANDITE = createVein("pentlandite", 10, 40, 5, 5, 40, 0, PENTLANDITE, 20, PLATINUM, 1, IRIDIUM, 1, OSMIUM, 1).build();
    public static final Vein VEIN_GARNIERITE = createVein("garnierite", 10, 40, 5, 5, 40, 0, GARNIERITE, 40, PLATINUM, 1, IRIDIUM, 1, OSMIUM, 1).build();
    public static final Vein VEIN_PYROCHLORE = createVein("pyrochlore", 20, 30, 5, 5, 10, 0, PYROCHLORE, 8, SODIUM, 1, CALCIUM, 1, NIOBIUM, 1).build();
    public static final Vein VEIN_ARGENTITE = createVein("argentite", 5, 35, 4, 4, 40, 0, ARGENTITE, 10, SILVER, 5, SELENIUM, 1).build();
    public static final Vein VEIN_CASSITERITE = createVein("cassiterite", 80, 200, 5, 5, 80, 0, CASSITERITE, 10, TIN, 3, ZINC, 1, GALLIUM, 1).build();
    public static final Vein VEIN_COPPER_TIN = createVein("copper_tin", 40, 160, 5, 5, 50, 0, CASSITERITE, 6, CHALCOPYRITE, 2, TIN, 1, COPPER, 1).build();
    public static final Vein VEIN_COPPER = createVein("copper", 5, 60, 5, 5, 80, 0, CHALCOPYRITE, 4, IRON, 1).build();
    public static final Vein VEIN_ILMENITE = createVein("ilmenite", 10, 70, 5, 5, 20, 0, ILMENITE, 16, MANGANESE, 3, MAGNESIUM, 3, VANADIUM, 1).build();
    public static final Vein VEIN_RUTILE = createVein("rutile", 5, 20, 5, 5, 10, 0, RUTILE, 14, IRON, 3, TANTALUM, 1, NIOBIUM, 1, CHROMIUM, 1, VANADIUM, 1, TIN, 1).build();
    public static final Vein VEIN_SCHEELITE = createVein("scheelite", 20, 60, 5, 5, 10, 0, SCHEELITE, 6, TUNGSTEN, 1).build();
    public static final Vein VEIN_CARNOTITE = createVein("carnotite", 10, 30, 5, 5, 60, 0, CARNOTITE, 6, URANIUM, 1, VANADIUM, 1).build();
    public static final Vein VEIN_SMITHSONITE = createVein("smithsonite", 5, 20, 5, 5, 100, 0, SMITHSONITE, 10, IRON, 1, COBALT, 1, COPPER, 1, MANGANESE, 1).build();
    public static final Vein VEIN_GRAPHITE = createVein("graphite", 5, 25, 5, 5, 40, 0, GRAPHITE, 2, COAL, 2).build();
    public static final Vein VEIN_COAL = createVein("coal", 30, 80, 5, 5, 80, 0, COAL, 1).build();
    public static final Vein VEIN_DIAMOND = createVein("diamond", 5, 20, 5, 5, 40, 0, DIAMOND, 3, COAL, 1, GRAPHITE, 2).build();
    public static final Vein VEIN_ORTHOCLASE = createVein("orthoclase", 5, 60, 5, 5, 25, 0, ORTHOCLASE, 6, IRON, 1, ALUMINUM, 1, BARIUM, 1).build();
    public static final Vein VEIN_PYRITE = createVein("pyrite", 20, 30, 5, 5, 10, 0, PYRITE, 1).build();
    public static final Vein VEIN_REDSTONE = createVein("redstone", 5, 40, 5, 5, 60, 0, REDSTONE, 3, CINNABAR, 1).build();
    public static final Vein VEIN_QUARTZITE = createVein("quartzite", 80, 120, 5, 5, 30, -1, QUARTZ, 1).build();
    public static final Vein VEIN_EMERALD = createVein("emerald", 5, 25, 1, 1, 10, 0, EMERALD, 1, BERYLLIUM, 1, THORIUM, 1).build();

    public static final Vein VEIN_EMERALD_NETHER = createVein("emerald_nether", 5, 30, 3, 3, 10, -1, EMERALD, 1, BERYLLIUM, 1, THORIUM, 1).build();
    public static final Vein VEIN_CARROLLITE_NETHER = createVein("carrollite_nether", 40, 60, 5, 5, 60, -1, CARROLLITE, 8, COPPER, 1, COBALT, 1, SULFUR, 1).build();
    public static final Vein VEIN_QUARTZITE_NETHER = createVein("quartzite_nether", 80, 120, 5, 5, 30, -1, QUARTZ, 1).build();
    public static final Vein VEIN_STIBNITE_NETHER = createVein("stibnite_nether", 80, 120, 5, 5, 70, -1, STIBNITE, 12, ANTIMONY, 2, SULFUR, 2).build();
    public static final Vein VEIN_BAUXITE_END = createVein("bauxite_end", 30, 60, 5, 5, 20, 1, BAUXITE, 4, ALUMINUM, 1, ILMENITE, 1).build();
    public static final Vein VEIN_MAGNETITE_END = createVein("magnetite_end", 10, 50, 5, 5, 70, 1, MAGNETITE, 8, CHROMITE, 1, GOLD, 1, IRON, 1).build();

    private static VeinBuilder createVein(String name, int minY, int maxY, int sizeH, int sizeV, int chance, int dimension, Object... substances) {
        return new VeinBuilder(name, minY, maxY, sizeH, sizeV, chance, dimension, substances);
    }

    @Nullable
    public static Vein getVein(Random random, int dimension) {
        WeightedList<Vein> veins = new WeightedList<>();
        VEINS_REGISTRY.getAll().entrySet().stream().filter(v -> v.getValue().getDimension() == dimension).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)).forEach((s, v) -> veins.put(v, v.getChance()));

        return veins.isEmpty() ? null : veins.get(random);
    }
}
