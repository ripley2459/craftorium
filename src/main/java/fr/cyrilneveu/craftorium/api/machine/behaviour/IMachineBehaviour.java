package fr.cyrilneveu.craftorium.api.machine.behaviour;

import fr.cyrilneveu.craftorium.api.mui.AWidget;
import io.netty.buffer.ByteBuf;

import java.util.Collections;
import java.util.List;

public interface IMachineBehaviour {
    default void verify() {

    }

    default void fromBytes(ByteBuf buf) {

    }

    default void toBytes(ByteBuf buf) {

    }

    default List<AWidget> getWidgets() {
        return Collections.emptyList();
    }
}
