package fr.cyrilneveu.craftorium.api.block;

import fr.cyrilneveu.craftorium.api.property.Aestheticism;
import fr.cyrilneveu.craftorium.api.render.FaceProvider;
import fr.cyrilneveu.craftorium.common.inventory.CreativeTabs;
import net.minecraft.block.material.Material;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import static fr.cyrilneveu.craftorium.CraftoriumTags.MODID;
import static fr.cyrilneveu.craftorium.api.Registries.BLOCKS_REGISTRY;
import static fr.cyrilneveu.craftorium.api.Registries.ITEMS_REGISTRY;
import static fr.cyrilneveu.craftorium.api.utils.RenderUtils.ERROR_COLOR;

public class BlockBuilder {
    private String name;
    private ResourceLocation registryName;
    private String translation;
    private List<FaceProvider> faceProviderList;
    @Nullable
    private Supplier<List<String>> toolTips;
    private boolean glint;
    private net.minecraft.creativetab.CreativeTabs creativeTab = CreativeTabs.COMMON;
    private Material material = Material.IRON;

    public BlockBuilder(String name) {
        this.name = name;
        this.faceProviderList = new ArrayList<>();
        setRegistryName(name);
        setTranslation(name);
    }

    public BlockBuilder setRegistryName(String name) {
        this.registryName = new ResourceLocation(MODID, name);
        return this;
    }

    public BlockBuilder setTranslation(String name) {
        this.translation = String.join(".", MODID, name);
        return this;
    }

    public BlockBuilder setCreativeTab(net.minecraft.creativetab.CreativeTabs creativeTab) {
        this.creativeTab = creativeTab;
        return this;
    }

    public BlockBuilder setMaterial(Material material) {
        this.material = material;
        return this;
    }

    public BlockBuilder addTexture(String path) {
        return addTexture(path, ERROR_COLOR);
    }

    public BlockBuilder addTexture(String path, int color) {
        this.faceProviderList.add(new FaceProvider(new ResourceLocation(MODID, String.join("/", "blocks", path)), color));
        return this;
    }

    public BlockBuilder tooltips(@Nullable Supplier<List<String>> toolTips) {
        this.toolTips = toolTips;
        return this;
    }

    public BlockBuilder glint() {
        this.glint = !this.glint;
        return this;
    }

    public CustomBlock build() {
        if (faceProviderList.isEmpty())
            addTexture(name);

        CustomBlock block = new CustomBlock(material, new Aestheticism.ObjectAestheticism(faceProviderList.toArray(new FaceProvider[0]), toolTips, glint));
        block.setRegistryName(registryName);
        block.setTranslationKey(translation);
        block.setCreativeTab(creativeTab);

        CustomBlock.CustomItemBlock item = new CustomBlock.CustomItemBlock(block);
        item.setRegistryName(registryName);
        item.setTranslationKey(translation);
        item.setCreativeTab(creativeTab);

        BLOCKS_REGISTRY.put(name, block);
        ITEMS_REGISTRY.put(name, item);

        return block;
    }
}
