package fr.cyrilneveu.craftorium.api.item.behaviour;

public final class FuelBehaviour implements IItemBehaviour {
    private final int duration;

    public FuelBehaviour(int duration) {
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }
}
