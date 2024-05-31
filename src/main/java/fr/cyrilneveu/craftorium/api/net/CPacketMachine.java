package fr.cyrilneveu.craftorium.api.net;

import fr.cyrilneveu.craftorium.api.machine.MachineContainer;
import fr.cyrilneveu.craftorium.api.machine.MachineTile;
import fr.cyrilneveu.craftorium.api.machine.behaviour.IMachineBehaviour;
import fr.cyrilneveu.craftorium.api.mui.AWidget;
import io.netty.buffer.ByteBuf;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;

public class CPacketMachine extends AMessage.ClientToServerMessage<CPacketMachine> {
    private MachineTile instigator;
    private ByteBuf data;

    public CPacketMachine() {

    }

    public CPacketMachine(MachineTile instigator) {
        this.instigator = instigator;
    }

    @Override
    public void process(EntityPlayer player, Side side) {
        if (player.openContainer instanceof MachineContainer container) {
            MachineTile tile = container.getOwner();

            for (IMachineBehaviour behaviour : tile.getBehaviours())
                behaviour.fromBytes(data);

            World world = tile.getWorld();
            IBlockState state = world.getBlockState(tile.getPos());
            world.notifyBlockUpdate(tile.getPos(), state, state, 3);
            world.notifyNeighborsOfStateChange(tile.getPos(), tile.getBlockType(), false);
            tile.markDirty();
        }
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        data = buf.copy();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        for (AWidget widget : instigator.getScreen().getWidgets())
            widget.toBytes(buf);
    }
}
