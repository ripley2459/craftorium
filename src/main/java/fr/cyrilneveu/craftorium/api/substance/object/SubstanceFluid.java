package fr.cyrilneveu.craftorium.api.substance.object;

import fr.cyrilneveu.craftorium.api.render.FaceProvider;
import fr.cyrilneveu.craftorium.api.substance.Substance;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

import static fr.cyrilneveu.craftorium.CraftoriumTags.MODID;

public final class SubstanceFluid extends ASubstanceObject {
    public SubstanceFluid(String name, boolean self, String prefix, String suffix, ICreateObject provider, IGetFaces faces, IGetModelTemplate model) {
        super(name, self, prefix, suffix, provider, faces, model);
    }

    public static FaceProvider[] defaultFaces(ASubstanceObject reference, Substance substance) {
        FaceProvider[] faces = new FaceProvider[2];
        faces[0] = new FaceProvider(new ResourceLocation(MODID, String.join("/", "fluids", "substances", "fluids", substance.getAestheticism().getStyle(), "still")), substance.getAestheticism().getFluidColor());
        faces[1] = new FaceProvider(new ResourceLocation(MODID, String.join("/", "fluids", "substances", "fluids", substance.getAestheticism().getStyle(), "flow")), substance.getAestheticism().getFluidColor());
        return faces;
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
