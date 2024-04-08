package fr.cyrilneveu.craftorium.api.block;

import fr.cyrilneveu.craftorium.api.render.FaceProvider;
import fr.cyrilneveu.craftorium.common.inventory.CreativeTabs;
import net.minecraft.block.material.Material;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

import static fr.cyrilneveu.craftorium.CraftoriumTags.MODID;
import static fr.cyrilneveu.craftorium.api.utils.Utils.ERROR_COLOR;
import static fr.cyrilneveu.craftorium.common.ACommonProxy.BLOCKS_REGISTRY;
import static fr.cyrilneveu.craftorium.common.ACommonProxy.ITEMS_REGISTRY;

public class BlockBuilder {
    private String name;
    private ResourceLocation registryName;
    private String translation;
    private List<FaceProvider> faceProviderList;
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

    public CustomBlock build() {
        if (faceProviderList.isEmpty())
            addTexture(name);

        CustomBlock block = new CustomBlock(material, faceProviderList.toArray(new FaceProvider[0]));
        block.setRegistryName(registryName);
        block.setTranslationKey(translation);
        block.setCreativeTab(creativeTab);

        CustomItemBlock item = new CustomItemBlock(block);
        item.setRegistryName(registryName);
        item.setTranslationKey(translation);
        item.setCreativeTab(creativeTab);

        BLOCKS_REGISTRY.put(name, block);
        ITEMS_REGISTRY.put(name, item);

        return block;
    }
}
