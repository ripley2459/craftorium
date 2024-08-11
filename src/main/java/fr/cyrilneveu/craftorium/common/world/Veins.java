package fr.cyrilneveu.craftorium.common.world;

import fr.cyrilneveu.craftorium.api.utils.WeightedList;
import fr.cyrilneveu.craftorium.api.world.vein.Vein;
import fr.cyrilneveu.craftorium.api.world.vein.VeinBuilder;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import static fr.cyrilneveu.craftorium.api.Registries.VEINS_REGISTRY;
import static fr.cyrilneveu.craftorium.common.substance.Substances.*;

public final class Veins {
    public static Vein VEIN_BAUXITE;
    public static Vein VEIN_BARITE;
    public static Vein VEIN_STIBNITE;
    public static Vein VEIN_BERYL;
    public static Vein VEIN_BISMUTHINITE;
    public static Vein VEIN_CHROMITE;
    public static Vein VEIN_SMALTITE;
    public static Vein VEIN_CARROLLITE;
    public static Vein VEIN_CHALCOPYRITE;
    public static Vein VEIN_NATIVE_COPPER;
    public static Vein VEIN_MALACHITE;
    public static Vein VEIN_AZURITE;
    public static Vein VEIN_LAZURITE;
    public static Vein VEIN_SODALITE;
    public static Vein VEIN_NATIVE_GOLD;
    public static Vein VEIN_NATIVE_PLATINUM;
    public static Vein VEIN_NATIVE_OSMIUM;
    public static Vein VEIN_NATIVE_IRIDIUM;
    public static Vein VEIN_HEMATITE;
    public static Vein VEIN_MAGNETITE;
    public static Vein VEIN_GALENA;
    public static Vein VEIN_CERUSSITE;
    public static Vein VEIN_SPDODUMENE;
    public static Vein VEIN_PYROLUSITE;
    public static Vein VEIN_CINNABAR;
    public static Vein VEIN_MOLYBDENITE;
    public static Vein VEIN_PENTLANDITE;
    public static Vein VEIN_GARNIERITE;
    public static Vein VEIN_PYROCHLORE;
    public static Vein VEIN_ARGENTITE;
    public static Vein VEIN_CASSITERITE;
    public static Vein VEIN_COPPER_TIN;
    public static Vein VEIN_COPPER;
    public static Vein VEIN_ILMENITE;
    public static Vein VEIN_RUTILE;
    public static Vein VEIN_SCHEELITE;
    public static Vein VEIN_CARNOTITE;
    public static Vein VEIN_SMITHSONITE;
    public static Vein VEIN_GRAPHITE;
    public static Vein VEIN_COAL;
    public static Vein VEIN_DIAMOND;
    public static Vein VEIN_ORTHOCLASE;
    public static Vein VEIN_PYRITE;
    public static Vein VEIN_REDSTONE;
    public static Vein VEIN_QUARTZITE;
    public static Vein VEIN_EMERALD;
    public static Vein VEIN_OIL_SAND;
    public static Vein VEIN_EMERALD_NETHER;
    public static Vein VEIN_CARROLLITE_NETHER;
    public static Vein VEIN_QUARTZITE_NETHER;
    public static Vein VEIN_STIBNITE_NETHER;
    public static Vein VEIN_BAUXITE_END;
    public static Vein VEIN_MAGNETITE_END;

    public static void init() {
        if (VEINS_REGISTRY.isInitialized())
            return;

        VEINS_REGISTRY.initialize();

        VEIN_BAUXITE = new VeinBuilder("bauxite", 10, 80, 7, 7, 80, 0, BAUXITE, 12, ALUMINUM, 2, GALLIUM, 1).build();
        VEIN_BARITE = new VeinBuilder("barite", 80, 120, 7, 7, 30, 0, BARITE, 12, BARIUM, 2, SULFUR, 2).build();
        VEIN_STIBNITE = new VeinBuilder("stibnite", 80, 120, 7, 7, 70, 0, STIBNITE, 12, ANTIMONY, 2, SULFUR, 2).build();
        VEIN_BERYL = new VeinBuilder("beryl", 5, 30, 7, 7, 15, 0, BERYL, 12, EMERALD, 2, THORIUM, 2, BERYLLIUM, 1, BORON, 2).build();
        VEIN_BISMUTHINITE = new VeinBuilder("bismuthinite", 40, 60, 7, 7, 60, 0, BISMUTHINITE, 6, SULFUR, 2).build();
        VEIN_CHROMITE = new VeinBuilder("chromite", 10, 70, 7, 7, 16, 0, CHROMITE, 6, IRON, 2, CHROMIUM, 2).build();
        VEIN_SMALTITE = new VeinBuilder("smaltite", 40, 60, 7, 7, 60, 0, SMALTITE, 6, COBALT, 1).build();
        VEIN_CARROLLITE = new VeinBuilder("carrollite", 40, 60, 7, 7, 60, 0, CARROLLITE, 8, COPPER, 1, COBALT, 1, SULFUR, 1).build();
        VEIN_CHALCOPYRITE = new VeinBuilder("chalcopyrite", 80, 200, 7, 7, 80, 0, CHALCOPYRITE, 15, COPPER, 3, IRON, 1, SULFUR, 1).build();
        VEIN_NATIVE_COPPER = new VeinBuilder("native_copper", 80, 120, 7, 7, 70, 0, COPPER, 1).build();
        VEIN_MALACHITE = new VeinBuilder("malachite", 10, 40, 7, 7, 15, 0, MALACHITE, 8, IRON, 1, COPPER, 1).build();
        VEIN_AZURITE = new VeinBuilder("azurite", 20, 50, 7, 7, 15, 0, AZURITE, 3, LAPIS, 1).build();
        VEIN_LAZURITE = new VeinBuilder("lazurite", 20, 50, 7, 7, 15, 0, LAZURITE, 1, LAPIS, 3).build();
        VEIN_SODALITE = new VeinBuilder("sodalite", 30, 60, 7, 7, 10, 0, SODALITE, 1).build();
        VEIN_NATIVE_GOLD = new VeinBuilder("native_gold", 15, 40, 5, 5, 30, 0, GOLD, 20, PLATINUM, 1, IRIDIUM, 1, OSMIUM, 1).build();
        VEIN_NATIVE_PLATINUM = new VeinBuilder("native_platinum", 15, 40, 3, 3, 30, 0, PLATINUM, 10, IRIDIUM, 1, OSMIUM, 1).build();
        VEIN_NATIVE_OSMIUM = new VeinBuilder("native_osmium", 15, 40, 3, 3, 30, 0, PLATINUM, 1, IRIDIUM, 1, OSMIUM, 10).build();
        VEIN_NATIVE_IRIDIUM = new VeinBuilder("native_iridium", 15, 40, 3, 3, 30, 0, PLATINUM, 1, IRIDIUM, 10, OSMIUM, 1).build();
        VEIN_HEMATITE = new VeinBuilder("hematite", 10, 50, 7, 7, 60, 0, HEMATITE, 6, IRON, 1).build();
        VEIN_MAGNETITE = new VeinBuilder("magnetite", 10, 50, 7, 7, 70, 0, MAGNETITE, 14, CHROMITE, 1, GOLD, 1, IRON, 1).build();
        VEIN_GALENA = new VeinBuilder("galena", 5, 37, 7, 5, 40, 0, GALENA, 6, SILVER, 1, SULFUR, 1).build();
        VEIN_CERUSSITE = new VeinBuilder("cerussite", 10, 30, 7, 7, 40, 0, CERUSSITE, 8, LEAD, 1, COPPER, 1, ZINC, 1).build();
        VEIN_SPDODUMENE = new VeinBuilder("spdodumene", 5, 40, 7, 7, 60, 0, SPDODUMENE, 14, IRON, 1, MANGANESE, 1, MAGNESIUM, 1, CALCIUM, 1, SODIUM, 1, POTASSIUM, 1).build();
        VEIN_PYROLUSITE = new VeinBuilder("pyrolusite", 20, 30, 7, 7, 20, 0, PYROLUSITE, 6, MANGANESE, 4).build();
        VEIN_CINNABAR = new VeinBuilder("cinnabar", 5, 37, 7, 5, 16, 0, CINNABAR, 2, REDSTONE, 2).build();
        VEIN_MOLYBDENITE = new VeinBuilder("molybdenite", 20, 50, 7, 7, 5, 0, MOLYBDENITE, 15, RHENIUM, 1, RHODIUM, 1, SELENIUM, 1, SILVER, 2, MOLYBDENUM, 3).build();
        VEIN_PENTLANDITE = new VeinBuilder("pentlandite", 10, 40, 7, 7, 40, 0, PENTLANDITE, 20, PLATINUM, 1, IRIDIUM, 1, OSMIUM, 1).build();
        VEIN_GARNIERITE = new VeinBuilder("garnierite", 10, 40, 7, 7, 40, 0, GARNIERITE, 40, PLATINUM, 1, IRIDIUM, 1, OSMIUM, 1).build();
        VEIN_PYROCHLORE = new VeinBuilder("pyrochlore", 20, 30, 7, 7, 10, 0, PYROCHLORE, 8, SODIUM, 1, CALCIUM, 1, NIOBIUM, 1).build();
        VEIN_ARGENTITE = new VeinBuilder("argentite", 5, 35, 5, 5, 40, 0, ARGENTITE, 10, SILVER, 5, SELENIUM, 1).build();
        VEIN_CASSITERITE = new VeinBuilder("cassiterite", 80, 200, 7, 7, 80, 0, CASSITERITE, 10, TIN, 3, ZINC, 1, GALLIUM, 1).build();
        VEIN_COPPER_TIN = new VeinBuilder("copper_tin", 40, 160, 7, 7, 50, 0, CASSITERITE, 6, CHALCOPYRITE, 2, TIN, 1, COPPER, 1).build();
        VEIN_COPPER = new VeinBuilder("copper", 5, 60, 7, 7, 80, 0, CHALCOPYRITE, 4, IRON, 1).build();
        VEIN_ILMENITE = new VeinBuilder("ilmenite", 10, 70, 7, 7, 20, 0, ILMENITE, 16, MANGANESE, 3, MAGNESIUM, 3, VANADIUM, 1).build();
        VEIN_RUTILE = new VeinBuilder("rutile", 5, 20, 7, 7, 10, 0, RUTILE, 14, IRON, 3, TANTALUM, 1, NIOBIUM, 1, CHROMIUM, 1, VANADIUM, 1, TIN, 1).build();
        VEIN_SCHEELITE = new VeinBuilder("scheelite", 20, 60, 7, 7, 10, 0, SCHEELITE, 6, TUNGSTEN, 1).build();
        VEIN_CARNOTITE = new VeinBuilder("carnotite", 10, 30, 7, 7, 60, 0, CARNOTITE, 6, URANIUM, 1, VANADIUM, 1).build();
        VEIN_SMITHSONITE = new VeinBuilder("smithsonite", 5, 20, 7, 7, 100, 0, SMITHSONITE, 10, IRON, 1, COBALT, 1, COPPER, 1, MANGANESE, 1).build();
        VEIN_GRAPHITE = new VeinBuilder("graphite", 5, 27, 7, 5, 40, 0, GRAPHITE, 2, COAL, 2).build();
        VEIN_COAL = new VeinBuilder("coal", 30, 80, 7, 7, 80, 0, COAL, 1).build();
        VEIN_DIAMOND = new VeinBuilder("diamond", 5, 20, 7, 7, 40, 0, DIAMOND, 3, COAL, 1, GRAPHITE, 2).build();
        VEIN_ORTHOCLASE = new VeinBuilder("orthoclase", 5, 60, 7, 7, 25, 0, ORTHOCLASE, 6, IRON, 1, ALUMINUM, 1, BARIUM, 1).build();
        VEIN_PYRITE = new VeinBuilder("pyrite", 20, 30, 7, 7, 10, 0, PYRITE, 1).build();
        VEIN_REDSTONE = new VeinBuilder("redstone", 5, 40, 7, 7, 60, 0, REDSTONE, 3, CINNABAR, 1).build();
        VEIN_QUARTZITE = new VeinBuilder("quartzite", 80, 120, 7, 7, 30, 0, QUARTZ, 1).build();
        VEIN_EMERALD = new VeinBuilder("emerald", 5, 25, 1, 1, 10, 0, EMERALD, 1, BERYLLIUM, 1, THORIUM, 1).build();
        VEIN_OIL_SAND = new VeinBuilder("oil_sand", 30, 80, 9, 2, 40, 0, OIL_SAND, 1).build();

        VEIN_EMERALD_NETHER = new VeinBuilder("emerald_nether", 5, 30, 3, 3, 10, -1, EMERALD, 1, BERYLLIUM, 1, THORIUM, 1).build();
        VEIN_CARROLLITE_NETHER = new VeinBuilder("carrollite_nether", 40, 60, 7, 7, 60, -1, CARROLLITE, 8, COPPER, 1, COBALT, 1, SULFUR, 1).build();
        VEIN_QUARTZITE_NETHER = new VeinBuilder("quartzite_nether", 80, 120, 7, 7, 30, -1, QUARTZ, 1).build();
        VEIN_STIBNITE_NETHER = new VeinBuilder("stibnite_nether", 80, 120, 7, 7, 70, -1, STIBNITE, 12, ANTIMONY, 2, SULFUR, 2).build();

        VEIN_BAUXITE_END = new VeinBuilder("bauxite_end", 30, 60, 7, 7, 20, 1, BAUXITE, 4, ALUMINUM, 1, ILMENITE, 1).build();
        VEIN_MAGNETITE_END = new VeinBuilder("magnetite_end", 10, 50, 7, 7, 70, 1, MAGNETITE, 8, CHROMITE, 1, GOLD, 1, IRON, 1).build();
    }

    @Nullable
    public static Vein getVein(Random random, int dimension) {
        WeightedList<Vein> veins = new WeightedList<>();
        VEINS_REGISTRY.getAll().entrySet().stream().filter(v -> v.getValue().getDimension() == dimension).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)).forEach((s, v) -> veins.put(v, v.getChance()));

        return veins.isEmpty() ? null : veins.get(random);
    }

    public static void close() {
        VEINS_REGISTRY.order().close();
    }
}
