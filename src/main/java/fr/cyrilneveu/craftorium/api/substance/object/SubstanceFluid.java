package fr.cyrilneveu.craftorium.api.substance.object;

import fr.cyrilneveu.craftorium.api.substance.Substance;

import javax.annotation.Nullable;

public final class SubstanceFluid extends ASubstanceObject {
    public SubstanceFluid(String name, boolean self, String prefix, String suffix, ICreateObject provider, IGetFaces faces, IGetModel model) {
        super(name, self, prefix, suffix, provider, faces, model);
    }

    @Override
    public String getOre(Substance substance) {
        return getName(substance);
    }

    @Override
    public String getName(@Nullable Substance substance) {
        return substance == null ? name : substance.getName();
    }
}
