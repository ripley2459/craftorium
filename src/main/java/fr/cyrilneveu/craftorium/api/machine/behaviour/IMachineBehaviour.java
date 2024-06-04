package fr.cyrilneveu.craftorium.api.machine.behaviour;

import fr.cyrilneveu.craftorium.api.mui.AWidget;
import fr.cyrilneveu.craftorium.api.mui.Tab;
import io.netty.buffer.ByteBuf;

import java.util.List;

public interface IMachineBehaviour {
    default void init() {

    }

    default void fromBytes(ByteBuf buf) {

    }

    default void toBytes(ByteBuf buf) {

    }

    default void pushWidgets(List<AWidget> widgets, List<Tab> leftTabs, List<Tab> rightTabs) {

    }
}
