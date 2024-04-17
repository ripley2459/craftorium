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
