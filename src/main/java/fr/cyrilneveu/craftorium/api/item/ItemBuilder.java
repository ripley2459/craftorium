package fr.cyrilneveu.craftorium.api.item;

import fr.cyrilneveu.craftorium.api.render.FaceProvider;
import fr.cyrilneveu.craftorium.common.inventory.CreativeTabs;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

import static fr.cyrilneveu.craftorium.CraftoriumTags.MODID;
import static fr.cyrilneveu.craftorium.api.utils.Utils.ERROR_COLOR;
import static fr.cyrilneveu.craftorium.common.ACommonProxy.ITEMS_REGISTRY;

public class ItemBuilder {
    private String name;
    private ResourceLocation registryName;
    private String translation;
    private List<FaceProvider> faceProviderList;
    private net.minecraft.creativetab.CreativeTabs creativeTab = CreativeTabs.COMMON;

    public ItemBuilder(String name) {
        this.name = name;
        this.faceProviderList = new ArrayList<>();
        setRegistryName(name);
        setTranslation(name);
    }

    public ItemBuilder setRegistryName(String name) {
        this.registryName = new ResourceLocation(MODID, name);
        return this;
    }

    public ItemBuilder setTranslation(String name) {
        this.translation = String.join(".", MODID, name);
        return this;
    }

    public ItemBuilder setCreativeTab(net.minecraft.creativetab.CreativeTabs creativeTab) {
        this.creativeTab = creativeTab;
        return this;
    }

    public ItemBuilder addTexture(String path) {
        return addTexture(path, ERROR_COLOR);
    }

    public ItemBuilder addTexture(String path, int color) {
        this.faceProviderList.add(new FaceProvider(new ResourceLocation(MODID, String.join("/", "items", path)), color));
        return this;
    }

    public CustomItem build() {
        if (faceProviderList.isEmpty())
            addTexture(name);

        CustomItem item = new CustomItem(faceProviderList.toArray(new FaceProvider[0]));
        item.setRegistryName(registryName);
        item.setTranslationKey(translation);
        item.setCreativeTab(creativeTab);

        ITEMS_REGISTRY.put(name, item);

        return item;
    }
}
