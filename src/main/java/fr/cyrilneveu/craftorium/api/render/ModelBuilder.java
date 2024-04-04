package fr.cyrilneveu.craftorium.api.render;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.ModelLoader;

import java.util.Map;
import java.util.function.UnaryOperator;

public final class ModelBuilder {
    private final VertexFormat vertexFormats;
    private final Map<String, String> sprites = new Object2ObjectOpenHashMap<>();
    private boolean currentlyBaking = false;
    private ModelTemplate template;
    private UnaryOperator<IModel> mutator;
    private IBakedModel model;
    private int layer = 0;

    public ModelBuilder(VertexFormat vertexFormats) {
        this.vertexFormats = vertexFormats;
    }

    private static IBakedModel bakeModel(IModel model, UnaryOperator<IModel> mutator, Map<String, String> sprites, VertexFormat format) {
        if (!sprites.containsKey("particle"))
            sprites.put("particle", (String) sprites.values().toArray()[0]);

        IModel retextured = model.retexture(ImmutableMap.copyOf(sprites));
        if (mutator != null)
            retextured = mutator.apply(retextured);

        return retextured.bake(retextured.getDefaultState(), format, ModelLoader.defaultTextureGetter());
    }

    public ModelBuilder newOperation(ModelTemplate template) {
        currentlyBaking = true;
        this.template = template;
        sprites.clear();
        mutator = null;
        layer = 0;
        return this;
    }

    public ModelBuilder addLayer(ResourceLocation texture) {
        return addTexture("layer" + layer++, texture);
    }

    public ModelBuilder addTexture(String element, ResourceLocation texture) {
        sprites.put(element, texture.toString());
        return this;
    }

    public ModelBuilder mutate(UnaryOperator<IModel> mutator) {
        this.mutator = mutator;
        return this;
    }

    public ModelBuilder build() {
        Preconditions.checkArgument(currentlyBaking);
        model = bakeModel(template.getModel(), mutator, sprites, vertexFormats);
        sprites.clear();
        currentlyBaking = false;
        template = null;
        mutator = null;
        layer = 0;
        return this;
    }

    public IBakedModel getModel() {
        Preconditions.checkArgument(!currentlyBaking);
        IBakedModel take = model;
        model = null;
        return take;
    }
}
