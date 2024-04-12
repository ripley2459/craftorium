package fr.cyrilneveu.craftorium.api.substance.object;

import fr.cyrilneveu.craftorium.api.render.FaceProvider;
import fr.cyrilneveu.craftorium.api.render.ModelTemplate;
import fr.cyrilneveu.craftorium.api.substance.Substance;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;

import static fr.cyrilneveu.craftorium.CraftoriumTags.MODID;
import static fr.cyrilneveu.craftorium.api.utils.Utils.WHITE_COLOR;

public final class SubstanceBlock extends ASubstanceObject {
    public SubstanceBlock(String name, boolean self, String prefix, String suffix, ICreateObject provider, IGetFaces faces, IGetModelTemplate model) {
        super(name, self, prefix, suffix, provider, faces, model);
    }

    public static FaceProvider[] defaultFaces(ASubstanceObject reference, Substance substance) {
        FaceProvider[] faces = new FaceProvider[substance.getAestheticism().isShiny() ? 2 : 1];
        faces[0] = new FaceProvider(new ResourceLocation(MODID, String.join("/", "blocks", "substances", "blocks", substance.getAestheticism().getStyle(), reference.self ? substance.getName() : reference.name)), substance.getAestheticism().getBaseColor());
        if (substance.getAestheticism().isShiny())
            faces[1] = new FaceProvider(new ResourceLocation(MODID, String.join("/", "blocks", "substances", "blocks", substance.getAestheticism().getStyle(), (reference.self ? substance.getName() : reference.name).concat("_overlay"))), WHITE_COLOR);
        return faces;
    }

    public static FaceProvider[] oreFaces(ASubstanceObject reference, Substance substance) {
        FaceProvider[] faces = new FaceProvider[1];
        // faces[0] = new FaceProvider(new ResourceLocation("minecraft", String.join("/", "blocks", "stone")), WHITE_COLOR);
        faces[0] = new FaceProvider(new ResourceLocation(MODID, String.join("/", "blocks", "substances", "blocks", substance.getAestheticism().getStyle(), reference.self ? substance.getName() : reference.name)), substance.getAestheticism().getOreColor());
        return faces;
    }

    public static ModelTemplate defaultModel(ASubstanceObject reference, Substance substance) {
        return substance.getAestheticism().isShiny() ? ModelTemplate.BLOCK_TINTED_OVERLAY : ModelTemplate.BLOCK_TINTED;
    }

    public static ModelTemplate oreModel(ASubstanceObject reference, Substance substance) {
        return ModelTemplate.BLOCK_OVERLAY_TINTED;
    }

    public Block getBlock(Substance substance) {
        return Block.getBlockFromItem(asItemStack(substance).getItem());
    }
}
