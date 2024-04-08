package fr.cyrilneveu.craftorium.api.block;

import fr.cyrilneveu.craftorium.api.render.FaceProvider;
import fr.cyrilneveu.craftorium.api.substance.Substance;
import fr.cyrilneveu.craftorium.api.substance.object.ASubstanceObject;
import fr.cyrilneveu.craftorium.api.utils.Utils;
import net.minecraft.block.material.Material;
import net.minecraftforge.client.event.ModelBakeEvent;

import static fr.cyrilneveu.craftorium.api.utils.Utils.BLOCK_MODEL_BUILDER;

public class SubstanceBlock extends CustomBlock {
    protected final ASubstanceObject reference;
    protected final Substance substance;

    public SubstanceBlock(ASubstanceObject reference, Substance substance) {
        super(Material.IRON, reference.getFaces(substance));
        this.reference = reference;
        this.substance = substance;
    }



    @Override
    public void onModelBake(ModelBakeEvent event) {
        BLOCK_MODEL_BUILDER.newOperation(reference.getModel(substance));

        for (FaceProvider face : faceProviders)
            BLOCK_MODEL_BUILDER.addLayer(face.getTexture());

        event.getModelRegistry().putObject(Utils.getSimpleModelLocation(this), BLOCK_MODEL_BUILDER.build().getModel());
    }
}
