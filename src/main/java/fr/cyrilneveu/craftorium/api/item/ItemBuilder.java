package fr.cyrilneveu.craftorium.api.item;

import fr.cyrilneveu.craftorium.api.item.behaviour.FuelBehaviour;
import fr.cyrilneveu.craftorium.api.item.behaviour.IItemBehaviour;
import fr.cyrilneveu.craftorium.api.property.Aestheticism;
import fr.cyrilneveu.craftorium.api.render.FaceProvider;
import fr.cyrilneveu.craftorium.api.utils.Utils;
import fr.cyrilneveu.craftorium.common.inventory.CreativeTabs;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import static fr.cyrilneveu.craftorium.CraftoriumTags.MODID;
import static fr.cyrilneveu.craftorium.api.Registries.ITEMS_REGISTRY;
import static fr.cyrilneveu.craftorium.api.utils.RenderUtils.WHITE_COLOR;

public class ItemBuilder {
    private String name;
    private ResourceLocation registryName;
    private String translation;
    private List<FaceProvider> faceProviderList;
    private List<IItemBehaviour> behaviours = new ArrayList<>();
    @Nullable
    private Supplier<List<String>> toolTips;
    private boolean glint;
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

    public ItemBuilder fuel(int duration) {
        return this.behaviour(new FuelBehaviour(duration));
    }

    public ItemBuilder addTexture(String path) {
        return addTexture(path, WHITE_COLOR);
    }

    public ItemBuilder addTexture(String path, int color) {
        this.faceProviderList.add(new FaceProvider(new ResourceLocation(MODID, String.join("/", "items", path)), color));
        return this;
    }

    public ItemBuilder tooltips(String... toolTipLocalisationKeys) {
        this.toolTips = Utils.generateTooltipProvider(toolTipLocalisationKeys);
        return this;
    }

    public ItemBuilder behaviour(IItemBehaviour behaviour) {
        this.behaviours.add(behaviour);
        return this;
    }

    public ItemBuilder glint() {
        this.glint = !this.glint;
        return this;
    }

    public CustomItem build() {
        if (faceProviderList.isEmpty())
            addTexture(name);

        CustomItem item = new CustomItem(behaviours.toArray(new IItemBehaviour[0]), new Aestheticism.ObjectAestheticism(faceProviderList.toArray(new FaceProvider[0]), toolTips, glint));
        item.setRegistryName(registryName);
        item.setTranslationKey(translation);
        item.setCreativeTab(creativeTab);

        ITEMS_REGISTRY.put(name, item);

        return item;
    }
}
