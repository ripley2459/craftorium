package fr.cyrilneveu.craftorium.api.machine.behaviour;

import fr.cyrilneveu.craftorium.api.machine.MachineTile;
import net.minecraft.util.ITickable;

public final class RecipeProcessor implements IMachineBehaviour, ITickable {
    private final MachineTile owner;

    public RecipeProcessor(MachineTile owner) {
        this.owner = owner;
    }

    @Override
    public void update() {

    }
}
