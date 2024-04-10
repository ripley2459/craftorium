package fr.cyrilneveu.craftorium.api.substance.object;

import fr.cyrilneveu.craftorium.api.render.FaceProvider;
import fr.cyrilneveu.craftorium.api.substance.Substance;
import net.minecraft.util.ResourceLocation;

import static fr.cyrilneveu.craftorium.CraftoriumTags.MODID;
import static fr.cyrilneveu.craftorium.api.utils.Utils.WHITE_COLOR;

public final class SubstanceItem extends ASubstanceObject {
    public SubstanceItem(String name, boolean self, String prefix, String suffix, ICreateObject provider, IGetFaces faces, IGetModelTemplate model) {
        super(name, self, prefix, suffix, provider, faces, model);
    }

    public static FaceProvider[] defaultFaces(ASubstanceObject reference, Substance substance) {
        FaceProvider[] faces = new FaceProvider[substance.getAestheticism().isShiny() ? 2 : 1];
        faces[0] = new FaceProvider(new ResourceLocation(MODID, String.join("/", "items", "substances", "items", substance.getAestheticism().getStyle(), reference.self ? substance.getName() : reference.name)), substance.getAestheticism().getBaseColor());
        if (substance.getAestheticism().isShiny())
            faces[1] = new FaceProvider(new ResourceLocation(MODID, String.join("/", "items", "substances", "items", substance.getAestheticism().getStyle(), (reference.self ? substance.getName() : reference.name).concat("_overlay"))), WHITE_COLOR);
        return faces;
    }
}
