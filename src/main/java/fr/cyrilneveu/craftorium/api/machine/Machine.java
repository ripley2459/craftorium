package fr.cyrilneveu.craftorium.api.machine;

import fr.cyrilneveu.craftorium.api.machine.behaviour.IMachineBehaviour;
import fr.cyrilneveu.craftorium.api.tier.Tier;
import fr.cyrilneveu.craftorium.api.utils.Utils;

import javax.annotation.Nonnull;
import java.util.List;

import static fr.cyrilneveu.craftorium.CraftoriumTags.MODID;

public final class Machine implements Comparable<Machine> {
    private final String name;
    private final List<IGetBehaviours> providers;

    public Machine(String name, List<IGetBehaviours> providers) {
        this.name = name;
        this.providers = providers;
    }

    public String getName() {
        return name;
    }

    public String getDisplayName() {
        return Utils.localise(getTranslationKey());
    }

    public String getTranslationKey() {
        return String.join(".", "machine", MODID, name, "name");
    }

    public IMachineBehaviour[] getBehaviours(MachineTile tile, Tier tier) {
        IMachineBehaviour[] behaviours = new IMachineBehaviour[providers.size()];

        for (int i = 0; i < providers.size(); i++)
            behaviours[i] = providers.get(i).get(tile, tier);

        return behaviours;
    }

    @Override
    public int compareTo(@Nonnull Machine other) {
        return name.compareTo(other.getName());
    }

    @FunctionalInterface
    public interface IGetBehaviours {
        IMachineBehaviour get(MachineTile owner, Tier tier);
    }
}
