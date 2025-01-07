package fr.cyrilneveu.craftorium.api.machine;

import com.google.common.collect.ImmutableList;
import fr.cyrilneveu.craftorium.api.machine.behaviour.IMachineBehaviour;
import fr.cyrilneveu.craftorium.api.mui.ATabGroup;
import fr.cyrilneveu.craftorium.api.mui.AWidget;
import fr.cyrilneveu.craftorium.api.mui.Background;
import fr.cyrilneveu.craftorium.api.mui.Screen;
import fr.cyrilneveu.craftorium.api.tier.Tier;
import fr.cyrilneveu.craftorium.api.utils.Size;
import fr.cyrilneveu.craftorium.api.utils.Utils;
import fr.cyrilneveu.craftorium.common.ACommonProxy;
import fr.cyrilneveu.craftorium.common.integration.jei.recipe.machine.MachineJeiData;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;
import java.util.LinkedList;
import java.util.List;

import static fr.cyrilneveu.craftorium.CraftoriumTags.MODID;

public class Machine implements Comparable<Machine> {
    private final String name;
    private final List<IGetBehaviours> providers;
    private final Size screenSize;
    private final List<AWidget> widgets;
    private final List<ATabGroup.Tab> leftTabs;
    private final List<ATabGroup.Tab> rightTabs;
    private final MachineJeiData jeiData;

    public Machine(String name, ImmutableList<IGetBehaviours> providers, Size screenSize, List<AWidget> widgets, List<ATabGroup.Tab> leftTabs, List<ATabGroup.Tab> rightTabs, MachineJeiData jeiData) {
        this.name = name;
        this.providers = providers;
        this.screenSize = screenSize;
        this.widgets = widgets;
        this.leftTabs = leftTabs;
        this.rightTabs = rightTabs;
        this.jeiData = jeiData;
    }

    public String getName() {
        return name;
    }

    public String getName(Tier tier) {
        return String.join("_", getName(), "tier", tier.getName());
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

    public MachineJeiData getJeiData() {
        return jeiData;
    }

    @Override
    public int compareTo(@Nonnull Machine other) {
        return name.compareTo(other.getName());
    }

    public ItemStack asItemStack(Tier tier) {
        return ACommonProxy.getItemStack(getName(tier));
    }

    public Screen getScreen(MachineTile tile, Tier tier) {
        LinkedList<AWidget> widgetsT = new LinkedList<>(widgets);
        List<ATabGroup.Tab> leftTabsT = new LinkedList<>(leftTabs);
        List<ATabGroup.Tab> rightTabsT = new LinkedList<>(rightTabs);

        for (IMachineBehaviour behaviour : tile.getBehaviours())
            behaviour.pushWidgets(widgetsT, leftTabsT, rightTabsT);

        widgetsT.addFirst(new ATabGroup.LeftTabGroup(leftTabsT.toArray(new ATabGroup.Tab[0])));
        widgetsT.addFirst(new ATabGroup.RightTabGroup(rightTabsT.toArray(new ATabGroup.Tab[0])));
        widgetsT.addFirst(new Background(screenSize));

        return new Screen(widgetsT.toArray(widgetsT.toArray(new AWidget[0])), screenSize);
    }

    @FunctionalInterface
    public interface IGetBehaviours {
        IMachineBehaviour get(MachineTile owner, Tier tier);
    }
}
