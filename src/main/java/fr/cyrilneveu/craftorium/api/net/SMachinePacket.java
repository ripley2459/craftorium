package fr.cyrilneveu.craftorium.api.net;

import fr.cyrilneveu.craftorium.api.machine.MachineContainer;
import fr.cyrilneveu.craftorium.api.machine.MachineTile;
import fr.cyrilneveu.craftorium.api.machine.behaviour.IMachineBehaviour;
import fr.cyrilneveu.craftorium.api.mui.AWidget;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.relauncher.Side;

public final class SMachinePacket extends AMessage.ServerToClientMessage<SMachinePacket> {
    private MachineTile instigator;
    private ByteBuf data;

    public SMachinePacket() {

    }

    public SMachinePacket(MachineTile instigator) {
        this.instigator = instigator;
    }

    @Override
    public void process(EntityPlayer player, Side side) {
        if (player.openContainer instanceof MachineContainer container) {
            for (AWidget widget : container.getOwner().getScreen().getWidgets())
                widget.fromBytes(data);
        }
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        data = buf.copy();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        for (IMachineBehaviour behaviour : instigator.getBehaviours())
            behaviour.toBytes(buf);
    }
}
