package fr.cyrilneveu.craftorium.api.tier;

import crafttweaker.annotations.ZenRegister;
import fr.cyrilneveu.craftorium.api.machine.Machine;
import fr.cyrilneveu.craftorium.api.property.Aestheticism;
import fr.cyrilneveu.craftorium.api.tier.object.ATierObject;
import fr.cyrilneveu.craftorium.api.tier.property.Pack;
import fr.cyrilneveu.craftorium.api.tier.property.Process;
import fr.cyrilneveu.craftorium.api.tier.property.Storage;
import fr.cyrilneveu.craftorium.api.utils.Utils;
import stanhebben.zenscript.annotations.ZenClass;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Set;

import static fr.cyrilneveu.craftorium.CraftoriumTags.MODID;

@ZenClass("mods." + MODID + ".tier.Tier")
@ZenRegister
public class Tier implements Comparable<Tier> {
    private final String name;
    private final Aestheticism.SubstanceAestheticism aestheticism;
    @Nullable
    private final Pack substances;
    private final Process process;
    private final Storage storage;
    private final Set<ATierObject.TierItem> items;
    private final Set<Machine> machines;

    public Tier(String name, Aestheticism.SubstanceAestheticism aestheticism, @Nullable Pack substances, Process process, Storage storage, Set<ATierObject.TierItem> items, Set<Machine> machines) {
        this.name = name;
        this.aestheticism = aestheticism;
        this.substances = substances;
        this.process = process;
        this.storage = storage;
        this.items = items;
        this.machines = machines;
    }

    public String getName() {
        return name;
    }

    public Aestheticism.SubstanceAestheticism getAestheticism() {
        return aestheticism;
    }

    @Nullable
    public Pack getPack() {
        return substances;
    }

    public Process getProcess() {
        return process;
    }

    public Storage getStorage() {
        return storage;
    }

    public Set<ATierObject.TierItem> getItems() {
        return items;
    }

    public Set<Machine> getMachines() {
        return machines;
    }

    public String getDisplayName() {
        return Utils.localise(getTranslationKey());
    }

    public String getTranslationKey() {
        return String.join(".", "tier", MODID, name, "name");
    }

    @Override
    public int compareTo(@Nonnull Tier other) {
        return name.compareTo(other.getName());
    }
}
