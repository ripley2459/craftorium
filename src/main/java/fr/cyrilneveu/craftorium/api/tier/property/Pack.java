package fr.cyrilneveu.craftorium.api.tier.property;

import fr.cyrilneveu.craftorium.api.substance.Substance;

public final class Pack {
    private final Substance carcass, mechanical, energy, fluid, heat;

    public Pack(Substance carcass, Substance mechanical, Substance energy, Substance fluid, Substance heat) {
        this.carcass = carcass;
        this.mechanical = mechanical;
        this.energy = energy;
        this.fluid = fluid;
        this.heat = heat;
    }

    public Substance getCarcass() {
        return carcass;
    }

    public Substance getMechanical() {
        return mechanical;
    }

    public Substance getEnergy() {
        return energy;
    }

    public Substance getFluid() {
        return fluid;
    }

    public Substance getHeat() {
        return heat;
    }
}
