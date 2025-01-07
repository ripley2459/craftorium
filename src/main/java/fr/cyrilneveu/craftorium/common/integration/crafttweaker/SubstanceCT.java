package fr.cyrilneveu.craftorium.common.integration.crafttweaker;

import crafttweaker.annotations.ZenRegister;
import fr.cyrilneveu.craftorium.api.substance.Substance;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import static fr.cyrilneveu.craftorium.CraftoriumTags.MODID;

@ZenClass("mods." + MODID + ".substance.Substance")
@ZenRegister
public class SubstanceCT {
    private final Substance substance;

    public SubstanceCT(Substance substance) {
        this.substance = substance;
    }

    @ZenMethod
    public String getName() {
        return substance.getName();
    }
}
