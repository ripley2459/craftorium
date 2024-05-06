package fr.cyrilneveu.craftorium.api.substance;

import org.jetbrains.annotations.NotNull;

public final class Element implements Comparable<Element> {
    private final int atomicNumber;
    private final String symbol, name;
    private final EGroup group;
    private final double atomicMass;

    public Element(int atomicNumber, String symbol, String name, EGroup group, double atomicMass) {
        this.atomicNumber = atomicNumber;
        this.symbol = symbol;
        this.name = name;
        this.group = group;
        this.atomicMass = atomicMass;
    }

    public int getAtomicNumber() {
        return atomicNumber;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }

    public EGroup getGroup() {
        return group;
    }

    public double getAtomicMass() {
        return atomicMass;
    }

    public int getNeutrons() {
        return (int) Math.round(atomicMass) - atomicNumber;
    }

    public int getProtons() {
        return atomicNumber;
    }

    public int getElectrons() {
        return atomicNumber;
    }

    @Override
    public int compareTo(@NotNull Element other) {
        return name.compareTo(other.getName());
    }

    public enum EGroup {
        ACTINIDE,
        ALKALINE_EARTH_METAL,
        ALKALI_METAL,
        HALOGEN,
        LANTHANIDE,
        METALLOID,
        NOBLE_GAS,
        NON_METAL,
        POST_TRANSITION_METAL,
        TRANSITION_METAL,
        UNKNOWN
    }
}
