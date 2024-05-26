package fr.cyrilneveu.craftorium.api.machine;

import com.google.common.collect.ImmutableList;
import fr.cyrilneveu.craftorium.api.config.Settings;
import fr.cyrilneveu.craftorium.api.inventory.EnergySlotData;
import fr.cyrilneveu.craftorium.api.inventory.FluidSlotData;
import fr.cyrilneveu.craftorium.api.inventory.ItemSlotData;
import fr.cyrilneveu.craftorium.api.machine.behaviour.*;
import fr.cyrilneveu.craftorium.api.mui.AWidget;
import fr.cyrilneveu.craftorium.api.mui.Text;
import fr.cyrilneveu.craftorium.api.utils.Position;
import fr.cyrilneveu.craftorium.api.utils.Size;

import javax.annotation.Nullable;
import java.util.LinkedList;
import java.util.List;

import static fr.cyrilneveu.craftorium.api.Registries.MACHINES_REGISTRY;

public final class MachineBuilder {
    private String name;
    private List<ItemSlotData> items = new LinkedList<>();
    private List<FluidSlotData> fluids = new LinkedList<>();
    private EnergySlotData energy;
    @Nullable
    private Position playerInventoryPosition;
    private boolean flowControlled;
    private List<AWidget> additionalWidgets = new LinkedList<>();
    private Size screenSize = new Size(176, 166);

    public MachineBuilder(String name) {
        this.name = name;
    }

    public MachineBuilder itemInput(int posX, int posY) {
        items.add(new ItemSlotData(new Position(posX, posY), items.size(), ESlotFlow.INPUT));
        return this;
    }

    public MachineBuilder itemOutput(int posX, int posY) {
        items.add(new ItemSlotData(new Position(posX, posY), items.size(), ESlotFlow.OUTPUT));
        return this;
    }

    public MachineBuilder fluidInput(int posX, int posY) {
        fluids.add(new FluidSlotData(new Position(posX, posY), fluids.size(), ESlotFlow.INPUT, 64000));
        return this;
    }

    public MachineBuilder fluidOutput(int posX, int posY) {
        fluids.add(new FluidSlotData(new Position(posX, posY), fluids.size(), ESlotFlow.OUTPUT, 64000));
        return this;
    }

    public MachineBuilder energy(int posX, int posY) {
        energy = new EnergySlotData(new Position(posX, posY), Settings.machinesSettings.machineBaseStorage, Settings.machinesSettings.machineBaseTransfer);
        return this;
    }

    public MachineBuilder playerInventory(int posX, int posY) {
        playerInventoryPosition = new Position(posX, posY);
        return this;
    }

    public MachineBuilder size(int sizeX, int sizeY) {
        this.screenSize = new Size(sizeX, sizeY);
        return this;
    }

    public MachineBuilder flowControlled() {
        flowControlled = true;
        return this;
    }

    public MachineBuilder text(int posX, int posY, String unlocalizedText, boolean centered) {
        additionalWidgets.add(new Text(new Position(posX, posY), () -> unlocalizedText, centered, 4210752));
        return this;
    }

    public Machine build() {
        List<Machine.IGetBehaviours> providers = new LinkedList<>();

        if (!items.isEmpty())
            providers.add((o, t) -> new ItemInventory(o, ImmutableList.copyOf(items)));
        if (!fluids.isEmpty())
            providers.add((o, t) -> new FluidInventory(o, ImmutableList.copyOf(fluids)));
        if (energy != null)
            providers.add((o, t) -> new EnergyInventory(o, energy));
        if (flowControlled)
            providers.add((o, t) -> new FlowController(o));
        if (playerInventoryPosition != null)
            providers.add((o, t) -> new PlayerInventory(playerInventoryPosition));

        Machine m = new Machine(name, ImmutableList.copyOf(providers), screenSize, additionalWidgets);
        MACHINES_REGISTRY.put(name, m);
        return m;
    }
}
