package fr.cyrilneveu.craftorium.api.world.stone;

import com.google.common.base.Optional;
import fr.cyrilneveu.craftorium.api.utils.ADynamicProperty;

import static fr.cyrilneveu.craftorium.common.world.StoneTypes.STONE;
import static fr.cyrilneveu.craftorium.common.world.StoneTypes.STONES_REGISTRY;

public final class StoneProperty extends ADynamicProperty<StoneType> {
    public StoneProperty() {
        super("stone_variant", StoneType.class, STONES_REGISTRY.getAll().values().toArray(new StoneType[0]));
    }

    public static StoneType getByMeta(int meta) {
        for (StoneType value : STONES_REGISTRY.getAll().values()) {
            if (value.getMeta() == meta)
                return value;
        }

        return STONE;
    }

    @Override
    public Optional<StoneType> parseValue(String value) {
        try {
            return Optional.of(STONES_REGISTRY.get(value));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        return Optional.absent();
    }

    @Override
    public String getName(StoneType value) {
        return value.getName();
    }
}
