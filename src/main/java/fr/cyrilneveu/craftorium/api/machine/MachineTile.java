package fr.cyrilneveu.craftorium.api.machine;

import fr.cyrilneveu.craftorium.api.tier.Tier;
import net.minecraft.tileentity.TileEntity;

import static fr.cyrilneveu.craftorium.common.machine.Machines.ELECTROLYZER;
import static fr.cyrilneveu.craftorium.common.tier.Tiers.ONE;

public final class MachineTile extends TileEntity {
    private Machine machine;
    private Tier tier;

    public MachineTile() {
        this(ELECTROLYZER, ONE);
    }

    public MachineTile(Machine machine, Tier tier) {
        this.machine = machine;
        this.tier = tier;
    }

    public EMachineStates getState() {
        return EMachineStates.IDLE;
    }
}
