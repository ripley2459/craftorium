package fr.cyrilneveu.craftorium.api.machine;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import fr.cyrilneveu.craftorium.api.config.Settings;
import fr.cyrilneveu.craftorium.common.integration.jei.recipe.machine.MachineJeiData;
import fr.cyrilneveu.craftorium.api.inventory.ASlotData;
import fr.cyrilneveu.craftorium.api.inventory.EnergySlotData;
import fr.cyrilneveu.craftorium.api.inventory.FluidSlotData;
import fr.cyrilneveu.craftorium.api.inventory.ItemSlotData;
import fr.cyrilneveu.craftorium.api.machine.behaviour.*;
import fr.cyrilneveu.craftorium.api.mui.AWidget;
import fr.cyrilneveu.craftorium.api.mui.Tab;
import fr.cyrilneveu.craftorium.api.mui.Text;
import fr.cyrilneveu.craftorium.api.recipe.machine.RecipeMap;
import fr.cyrilneveu.craftorium.api.utils.Position;
import fr.cyrilneveu.craftorium.api.utils.Size;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static fr.cyrilneveu.craftorium.api.Registries.MACHINES_REGISTRY;
import static fr.cyrilneveu.craftorium.api.utils.Position.ORIGIN;
import static fr.cyrilneveu.craftorium.api.utils.RenderUtils.TEXT_COLOR;

public final class MachineBuilder {
    private String name;
    private List<Machine.IGetBehaviours> providers = new LinkedList<>();
    private List<ItemSlotData> items = new LinkedList<>();
    private List<FluidSlotData> fluids = new LinkedList<>();
    private List<AWidget> widgets = new LinkedList<>();
    private Size screenSize = new Size(176, 180);
    private List<Tab> leftTabs = new LinkedList<>();
    private List<Tab> rightTabs = new LinkedList<>();
    private List<ASlotData> slotJEI = new ArrayList<>();
    private Position arrowJEI;
    @Nullable
    private RecipeMap map;
    private boolean energy;

    public MachineBuilder(String name) {
        this.name = name;
    }

    public MachineBuilder itemInput(int posX, int posY) {
        ItemSlotData data = new ItemSlotData(new Position(posX, posY), items.size(), ESlotFlow.INPUT);
        this.items.add(data);
        this.slotJEI.add(data);
        return this;
    }

    public MachineBuilder itemOutput(int posX, int posY) {
        ItemSlotData data = new ItemSlotData(new Position(posX, posY), items.size(), ESlotFlow.OUTPUT);
        this.items.add(data);
        this.slotJEI.add(data);
        return this;
    }

    public MachineBuilder fluidInput(int posX, int posY) {
        FluidSlotData data = new FluidSlotData(new Position(posX, posY), fluids.size(), ESlotFlow.INPUT, 64000);
        this.fluids.add(data);
        this.slotJEI.add(data);
        return this;
    }

    public MachineBuilder fluidOutput(int posX, int posY) {
        FluidSlotData data = new FluidSlotData(new Position(posX, posY), fluids.size(), ESlotFlow.OUTPUT, 64000);
        this.fluids.add(data);
        this.slotJEI.add(data);
        return this;
    }

    public MachineBuilder energy(int posX, int posY) {
        this.energy = true;
        this.providers.add((m, t) -> new EnergyInventory(m, new EnergySlotData(new Position(posX, posY), Settings.machinesSettings.machineBaseStorage, Settings.machinesSettings.machineBaseTransfer)));
        return this;
    }

    public MachineBuilder playerInventory(int posX, int posY) {
        this.providers.add((m, t) -> new PlayerInventory(new Position(posX, posY)));
        return this;
    }

    public MachineBuilder size(int sizeX, int sizeY) {
        this.screenSize = new Size(sizeX, sizeY);
        return this;
    }

    public MachineBuilder flowControlled() {
        this.providers.add((m, t) -> new FlowController(m));
        return this;
    }

    public MachineBuilder processor(RecipeMap map, int progressX, int progressY, int configurationX, int ConfigurationY) {
        this.map = map;
        this.providers.add((m, t) -> new RecipeProcessor(m, map, new Position(progressX, progressY), new Position(configurationX, ConfigurationY)));
        this.arrowJEI = new Position(progressX, progressY);
        return this;
    }

    public MachineBuilder text(int posX, int posY, String text, boolean centered) {
        this.widgets.add(new Text(new Position(posX, posY), () -> text, centered, TEXT_COLOR));
        return this;
    }

    public Machine build() {
        verify();

        if (!items.isEmpty())
            providers.add((m, t) -> new ItemInventory(m, ImmutableList.copyOf(items)));
        if (!fluids.isEmpty())
            providers.add((m, t) -> new FluidInventory(m, ImmutableList.copyOf(fluids)));

        Machine m = new Machine(name, ImmutableList.copyOf(providers), screenSize, widgets, leftTabs, rightTabs, new MachineJeiData(map.getName(), arrowJEI, slotJEI));

        MACHINES_REGISTRY.put(name, m);

        return m;
    }

    private void verify() {
        if (map != null) {
            Preconditions.checkArgument(energy);
            Preconditions.checkArgument(arrowJEI != ORIGIN);

            int input = 0;
            int output = 0;
            for (ItemSlotData itemSlotData : items) {
                if (itemSlotData.isInput())
                    input++;
                else output++;
            }

            Preconditions.checkArgument(input >= map.getItemsIn() && output >= map.getItemsOut());

            input = 0;
            output = 0;
            for (FluidSlotData s : fluids) {
                if (s.isInput())
                    input++;
                else output++;
            }

            Preconditions.checkArgument(input >= map.getFluidsIn() && output >= map.getFluidsOut());
        }
    }
}
