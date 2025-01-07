package fr.cyrilneveu.craftorium.api.substance.property;

import com.google.common.base.Preconditions;
import fr.cyrilneveu.craftorium.api.substance.Substance;

import static fr.cyrilneveu.craftorium.common.substance.SubstancesObjects.DUST;
import static fr.cyrilneveu.craftorium.common.substance.SubstancesObjects.ORE;

public final class SubstanceProperties {
    public static final VeinMemberProperty VEIN_MEMBER_PROPERTY = new VeinMemberProperty();

    public enum KeyProperties {
        EMPTY,
        VEIN_MEMBER,
        FUEL
    }

    public static final class EmptyProperty implements ISubstanceProperty {
        @Override
        public void verify(Substance substance) {

        }
    }

    public static final class VeinMemberProperty implements ISubstanceProperty {
        @Override
        public void verify(Substance substance) {
            Preconditions.checkArgument(substance.getItems().contains(DUST));
            Preconditions.checkArgument(substance.getBlocks().contains(ORE));
        }
    }

    public static final class FuelProperty implements ISubstanceProperty {
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
}
