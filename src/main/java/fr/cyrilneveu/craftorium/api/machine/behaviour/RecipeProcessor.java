package fr.cyrilneveu.craftorium.api.machine.behaviour;

import fr.cyrilneveu.craftorium.api.machine.MachineTile;
import fr.cyrilneveu.craftorium.api.mui.AWidget;
import fr.cyrilneveu.craftorium.api.mui.ButtonConfiguration;
import fr.cyrilneveu.craftorium.api.mui.Tab;
import fr.cyrilneveu.craftorium.api.recipe.machine.RecipeMap;
import fr.cyrilneveu.craftorium.api.utils.NBTUtils;
import fr.cyrilneveu.craftorium.api.utils.Position;
import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.util.INBTSerializable;

import java.util.List;

public final class RecipeProcessor implements IMachineBehaviour, ITickable, INBTSerializable<NBTTagCompound> {
    public static final int MACHINE_CONFIGURATION_MIN = 1;
    public static final int MACHINE_CONFIGURATION_MAX = 9;
    public static final String MACHINE_CONFIGURATION_NBT = "MachineProcessingConfiguration";
    private final MachineTile owner;
    private final RecipeMap map;
    private final Position progressPosition;
    private final Position configurationButtonPosition;
    private int configuration = MACHINE_CONFIGURATION_MIN;

    public RecipeProcessor(MachineTile owner, RecipeMap map, Position progress, Position configuration) {
        this.owner = owner;
        this.map = map;
        this.progressPosition = progress;
        this.configurationButtonPosition = configuration;
    }

    @Override
    public void update() {

    }

    @Override
    public void pushWidgets(List<AWidget> widgets, List<Tab> leftTabs, List<Tab> rightTabs) {
        widgets.add(new ButtonConfiguration(configurationButtonPosition));
    }


    @Override
    public void fromBytes(ByteBuf buf) {
        configuration = buf.readByte();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeByte(configuration);
    }

    @Override
    public NBTTagCompound serializeNBT() {
        return NBTUtils.setValue(MACHINE_CONFIGURATION_NBT, configuration);
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        if (nbt.hasKey(MACHINE_CONFIGURATION_NBT))
            configuration = nbt.getByte(MACHINE_CONFIGURATION_NBT);
    }
}
