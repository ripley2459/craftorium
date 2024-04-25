package fr.cyrilneveu.craftorium.api.substance.object;

import fr.cyrilneveu.craftorium.api.render.FaceProvider;
import fr.cyrilneveu.craftorium.api.substance.Substance;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;

import static fr.cyrilneveu.craftorium.CraftoriumTags.MODID;
import static fr.cyrilneveu.craftorium.api.utils.Utils.WHITE_COLOR;

public final class SubstanceTool extends ASubstanceObject {
    public SubstanceTool(String name, boolean self, String prefix, String suffix, ICreateObject provider, IGetFaces faces, IGetModelTemplate model, @Nullable IGetTooltips tooltips) {
        super(name, self, prefix, suffix, provider, faces, model, tooltips);
    }

    public static FaceProvider[] defaultFaces(ASubstanceObject reference, Substance substance) {
        FaceProvider[] faces = new FaceProvider[2];
        faces[0] = new FaceProvider(new ResourceLocation(MODID, String.join("/", "items", "substances", "tools", reference.self ? substance.getName() : reference.name)), substance.getAestheticism().getBaseColor());
        faces[1] = new FaceProvider(new ResourceLocation(MODID, String.join("/", "items", "substances", "tools", (reference.self ? substance.getName() : reference.name).concat("_base"))), WHITE_COLOR);
        return faces;
    }

    public static List<String> toolTooltips(ASubstanceObject reference, Substance substance) {
        /*List<String> lines;
        return lines;*/
        return Collections.emptyList();
    }

    @Override
    public String getOre(Substance substance) {
        return name;
    }
}
