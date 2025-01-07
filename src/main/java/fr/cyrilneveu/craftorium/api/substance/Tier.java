package fr.cyrilneveu.craftorium.api.substance;

import fr.cyrilneveu.craftorium.api.machine.Machine;
import fr.cyrilneveu.craftorium.api.property.Aestheticism;
import fr.cyrilneveu.craftorium.api.property.Efficiency;
import fr.cyrilneveu.craftorium.api.property.Temperature;
import fr.cyrilneveu.craftorium.api.property.Toughness;
import fr.cyrilneveu.craftorium.api.recipe.process.AProcess;
import fr.cyrilneveu.craftorium.api.substance.object.ASubstanceObject;
import fr.cyrilneveu.craftorium.api.substance.property.Composition;
import fr.cyrilneveu.craftorium.api.substance.property.ISubstanceProperty;
import fr.cyrilneveu.craftorium.api.substance.property.SubstanceProperties;

import java.util.Map;
import java.util.Set;

public class Tier extends Substance {
    private final int simultaneous, chance;
    private final float speed, tankSize, energyBuffer, energyIO;
    private final Set<Machine> machines;
    private final Substance carcass, mechanical, energy, fluid, heat;

    public Tier(String name, Composition composition, Efficiency efficiency, Toughness toughness, Temperature temperature, Aestheticism.SubstanceAestheticism aestheticism, AProcess process, Map<SubstanceProperties.KeyProperties, ISubstanceProperty> properties, Set<ASubstanceObject.SubstanceItem> items, Set<ASubstanceObject.SubstanceTool> tools, Set<ASubstanceObject.SubstanceBlock> blocks, Set<ASubstanceObject.SubstanceFluid> fluids, Map<ASubstanceObject, String> overrides, int simultaneous, int chance, float speed, float tankSize, float energyBuffer, float energyIO, Set<Machine> machines, Substance carcass, Substance mechanical, Substance energy, Substance fluid, Substance heat) {
        super(name, composition, efficiency, toughness, temperature, aestheticism, process, properties, items, tools, blocks, fluids, overrides);
        this.simultaneous = simultaneous;
        this.chance = chance;
        this.speed = speed;
        this.tankSize = tankSize;
        this.energyBuffer = energyBuffer;
        this.energyIO = energyIO;
        this.machines = machines;
        this.carcass = carcass;
        this.mechanical = mechanical;
        this.energy = energy;
        this.fluid = fluid;
        this.heat = heat;
    }

    public int getSimultaneous() {
        return simultaneous;
    }

    public int getChance() {
        return chance;
    }

    public float getSpeed() {
        return speed;
    }

    public float getTankSize() {
        return tankSize;
    }

    public float getEnergyBuffer() {
        return energyBuffer;
    }

    public float getEnergyIO() {
        return energyIO;
    }

    public Set<Machine> getMachines() {
        return machines;
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
