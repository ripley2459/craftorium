package fr.cyrilneveu.craftorium.api.machine;

import com.google.common.collect.ImmutableList;
import fr.cyrilneveu.craftorium.api.machine.behaviour.IMachineBehaviour;
import fr.cyrilneveu.craftorium.api.mui.AWidget;
import fr.cyrilneveu.craftorium.api.mui.Background;
import fr.cyrilneveu.craftorium.api.mui.Screen;
import fr.cyrilneveu.craftorium.api.tier.Tier;
import fr.cyrilneveu.craftorium.api.utils.Size;
import fr.cyrilneveu.craftorium.api.utils.Utils;

import javax.annotation.Nonnull;
import java.util.LinkedList;
import java.util.List;

import static fr.cyrilneveu.craftorium.CraftoriumTags.MODID;

public final class Machine implements Comparable<Machine> {
    private final String name;
    private final List<IGetBehaviours> providers;
    private final Size screenSize;
    private final List<AWidget> additionalWidgets;

    public Machine(String name, ImmutableList<IGetBehaviours> providers, Size screenSize, List<AWidget> additionalWidgets) {
        this.name = name;
        this.providers = providers;
        this.screenSize = screenSize;
        this.additionalWidgets = additionalWidgets;
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

    public Screen getScreen(MachineTile tile, Tier tier) {
        List<AWidget> widgets = new LinkedList<>(additionalWidgets);

        widgets.add(new Background(screenSize));
        for (IMachineBehaviour behaviour : tile.getBehaviours())
            widgets.addAll(behaviour.getWidgets());

        return new Screen(widgets.toArray(widgets.toArray(new AWidget[0])), screenSize);
    }

    @FunctionalInterface
    public interface IGetBehaviours {
        IMachineBehaviour get(MachineTile owner, Tier tier);
    }
}
