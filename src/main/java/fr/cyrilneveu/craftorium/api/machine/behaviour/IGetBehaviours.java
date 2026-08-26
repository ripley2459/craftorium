package fr.cyrilneveu.craftorium.api.machine.behaviour;

import fr.cyrilneveu.craftorium.api.machine.MachineTile;
import fr.cyrilneveu.craftorium.api.substance.Tier;

@FunctionalInterface
public interface IGetBehaviours {
    IMachineBehaviour get(MachineTile owner, Tier tier);
}
