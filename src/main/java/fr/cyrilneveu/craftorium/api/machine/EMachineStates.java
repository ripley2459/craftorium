package fr.cyrilneveu.craftorium.api.machine;

import net.minecraft.util.IStringSerializable;

public enum EMachineStates implements IStringSerializable {
    IDLE("idle"),
    WORKING("working"),
    NOPOWER("nopower");

    private final String name;

    EMachineStates(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
