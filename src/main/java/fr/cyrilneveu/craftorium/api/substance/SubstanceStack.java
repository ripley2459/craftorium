package fr.cyrilneveu.craftorium.api.substance;

import com.google.common.base.Preconditions;
import org.jetbrains.annotations.NotNull;

public final class SubstanceStack implements Comparable<SubstanceStack> {
    private final Substance substance;
    private final int amount;
    private final int chance;

    public SubstanceStack(Substance substance, int amount) {
        this(substance, amount, 100);
    }

    public SubstanceStack(Substance substance, int amount, int chance) {
        Preconditions.checkArgument(amount > 0);
        Preconditions.checkArgument(chance > 0 && chance <= 100);

        this.substance = substance;
        this.amount = amount;
        this.chance = chance;
    }

    public Substance getSubstance() {
        return substance;
    }

    public int getAmount() {
        return amount;
    }

    public int getChance() {
        return chance;
    }

    @Override
    public int compareTo(@NotNull SubstanceStack other) {
        return substance.compareTo(other.substance);
    }
}
