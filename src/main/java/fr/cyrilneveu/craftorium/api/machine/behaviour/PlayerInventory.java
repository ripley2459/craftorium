package fr.cyrilneveu.craftorium.api.machine.behaviour;

import fr.cyrilneveu.craftorium.api.mui.AWidget;
import fr.cyrilneveu.craftorium.api.utils.Position;
import fr.cyrilneveu.craftorium.api.utils.Size;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

import java.util.Collections;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public final class PlayerInventory implements IMachineBehaviour, IContainable {
    public static final Size SIZE = new Size(162, 76);
    public static final int BR = 4;
    public final Position position;

    public PlayerInventory(Position position) {
        this.position = position;
    }

    public static void forPlayerBagSlots(BiConsumer<Integer, Integer> consumer) {
        for (int row = 0; row < 3; ++row) {
            for (int column = 0; column < 9; ++column)
                consumer.accept(row, column);
        }
    }

    public static void forPlayerBarSlots(Consumer<Integer> consumer) {
        for (int k = 0; k < 9; ++k)
            consumer.accept(k);
    }

    public Position getPosition() {
        return position;
    }

    @Override
    public List<AWidget> getWidgets() {
        return Collections.singletonList(new fr.cyrilneveu.craftorium.api.mui.PlayerInventory(position));
    }

    @Override
    public void initContainer(Container container, EntityPlayer playerIn) {
        PlayerInventory.forPlayerBagSlots((r, c) -> container.addSlotToContainer(new Slot(playerIn.inventory, c + r * 9 + 9, this.position.getPosX() + c * 18 + 1, this.position.getPosY() + r * 18 + 1)));
        Position position = new Position(this.position.getPosX() + 1, this.position.getPosY() + 3 * 18 + PlayerInventory.BR + 1);
        PlayerInventory.forPlayerBarSlots(s -> container.addSlotToContainer(new Slot(playerIn.inventory, s, position.getPosX() + s * 18, position.getPosY())));
    }
}
