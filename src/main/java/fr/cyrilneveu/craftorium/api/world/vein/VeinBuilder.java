package fr.cyrilneveu.craftorium.api.world.vein;

import com.google.common.base.Preconditions;
import fr.cyrilneveu.craftorium.api.substance.Substance;
import fr.cyrilneveu.craftorium.api.substance.property.SubstanceProperties;
import fr.cyrilneveu.craftorium.api.utils.WeightedList;
import net.minecraft.util.math.MathHelper;

import static fr.cyrilneveu.craftorium.api.Registries.SUBSTANCES_REGISTRY;
import static fr.cyrilneveu.craftorium.api.Registries.VEINS_REGISTRY;

public class VeinBuilder {
    private String name;
    private int minY;
    private int maxY;
    private int sizeH;
    private int sizeV;
    private int chance;
    private int dimension;
    private WeightedList<Substance> composition;

    public VeinBuilder(String name, int minY, int maxY, int sizeH, int sizeV, int chance, int dimension, Object... composition) {
        this.name = name;
        y(minY, maxY);
        size(sizeH, sizeV);
        chance(chance);
        dimension(dimension);
        composition(composition);
    }

    public VeinBuilder y(int minY, int maxY) {
        minY = MathHelper.clamp(minY, 0, 255);
        maxY = MathHelper.clamp(maxY, 0, 255);
        this.minY = minY >= maxY ? maxY - 1 : minY;
        this.maxY = maxY;
        return this;
    }

    public VeinBuilder size(int sizeH, int sizeV) {
        this.sizeH = MathHelper.clamp(sizeH, 1, 8);
        this.sizeV = MathHelper.clamp(sizeV, 1, 8);
        return this;
    }

    public VeinBuilder chance(int chance) {
        this.chance = MathHelper.clamp(chance, 0, Integer.MAX_VALUE);
        return this;
    }

    public VeinBuilder dimension(int dimension) {
        this.dimension = dimension;
        return this;
    }

    public VeinBuilder composition(Object... composition) {
        Preconditions.checkArgument(composition.length % 2 == 0);

        this.composition = new WeightedList<>();
        for (int i = 0; i < composition.length; i += 2) {
            Preconditions.checkArgument((composition[i] instanceof Substance || composition[i] instanceof String) && (composition[i + 1] instanceof Integer));

            Substance substance;
            if (composition[i] instanceof String) {
                Preconditions.checkArgument(SUBSTANCES_REGISTRY.contains((String) composition[i]));
                substance = SUBSTANCES_REGISTRY.get((String) composition[i]);
            } else substance = (Substance) composition[i];

            this.composition.put(substance, (Integer) composition[i + 1]);
        }

        return this;
    }

    public Vein build() {
        for (Substance substance : composition) {
            Preconditions.checkArgument(SUBSTANCES_REGISTRY.contains(substance.getName()));
            substance.getProperties().get(SubstanceProperties.KeyProperties.VEIN_MEMBER).verify(substance);
        }

        Vein vein = new Vein(name, minY, maxY, sizeH, sizeV, chance, dimension, composition);

        VEINS_REGISTRY.put(name, vein);

        return vein;
    }
}
