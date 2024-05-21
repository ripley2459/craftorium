package fr.cyrilneveu.craftorium.api.machine;

import fr.cyrilneveu.craftorium.api.block.CustomBlock;
import fr.cyrilneveu.craftorium.api.property.Aestheticism;
import fr.cyrilneveu.craftorium.api.render.FaceProvider;
import fr.cyrilneveu.craftorium.api.tier.Tier;
import fr.cyrilneveu.craftorium.api.utils.RenderUtils;
import fr.cyrilneveu.craftorium.api.utils.Utils;
import net.minecraft.util.ResourceLocation;

import static fr.cyrilneveu.craftorium.CraftoriumTags.MODID;
import static fr.cyrilneveu.craftorium.api.Registries.BLOCKS_REGISTRY;
import static fr.cyrilneveu.craftorium.api.Registries.ITEMS_REGISTRY;
import static fr.cyrilneveu.craftorium.common.inventory.CreativeTabs.MACHINES;

public final class Machine {
    public final String name;

    public Machine(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return Utils.localise(getTranslationKey());
    }

    public String getTranslationKey() {
        return String.join(".", "machine", MODID, name, "name");
    }

    public Aestheticism.ObjectAestheticism getAestheticism(Tier tier) {
        FaceProvider[] faces = new FaceProvider[4];
        faces[0] = new FaceProvider(new ResourceLocation(MODID, String.join("/", "blocks", "machines", "machine_side")), tier.getAestheticism().getBaseColor());
        faces[1] = new FaceProvider(new ResourceLocation(MODID, String.join("/", "blocks", "machines", "machine_side_overlay")), RenderUtils.WHITE_COLOR);
        faces[2] = new FaceProvider(new ResourceLocation(MODID, String.join("/", "blocks", "machines", name)), RenderUtils.WHITE_COLOR);
        faces[3] = new FaceProvider(new ResourceLocation(MODID, String.join("/", "blocks", "machines", name + "_on")), RenderUtils.WHITE_COLOR);

        return new Aestheticism.ObjectAestheticism(faces, null, false);
    }

    public void createObject(Tier tier) {
        MachineBlock block = new MachineBlock(this, tier);

        String name = String.join("_", this.name, "tier", tier.getName());
        ResourceLocation registryName = new ResourceLocation(MODID, name);
        String translation = String.join(".", MODID, name);

        block.setRegistryName(registryName);
        block.setTranslationKey(translation);
        block.setCreativeTab(MACHINES);

        CustomBlock.CustomItemBlock item = new MachineBlock.MachineItemBlock(block);
        item.setRegistryName(registryName);
        item.setTranslationKey(translation);
        item.setCreativeTab(MACHINES);

        BLOCKS_REGISTRY.put(name, block);
        ITEMS_REGISTRY.put(name, item);
    }
}
