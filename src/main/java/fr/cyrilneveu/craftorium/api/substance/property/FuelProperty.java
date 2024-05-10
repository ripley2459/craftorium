package fr.cyrilneveu.craftorium.api.substance.property;

import fr.cyrilneveu.craftorium.api.substance.Substance;

public final class FuelProperty implements ISubstanceProperty {
    private final int burnDuration;

    public FuelProperty(int burnDuration) {
        this.burnDuration = burnDuration;
    }

    public int getBurnDuration() {
        return burnDuration;
    }

    @Override
    public void verify(Substance substance) {

    }
}
