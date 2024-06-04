package fr.cyrilneveu.craftorium.common.machine;

import fr.cyrilneveu.craftorium.api.block.CustomBlock;
import fr.cyrilneveu.craftorium.api.config.Settings;
import fr.cyrilneveu.craftorium.api.machine.Machine;
import fr.cyrilneveu.craftorium.api.machine.MachineBlock;
import fr.cyrilneveu.craftorium.api.machine.MachineBuilder;
import fr.cyrilneveu.craftorium.api.property.Aestheticism;
import fr.cyrilneveu.craftorium.api.render.FaceProvider;
import fr.cyrilneveu.craftorium.api.tier.Tier;
import fr.cyrilneveu.craftorium.api.utils.RenderUtils;
import fr.cyrilneveu.craftorium.api.utils.Utils;
import net.minecraft.util.ResourceLocation;

import java.util.Collections;

import static fr.cyrilneveu.craftorium.CraftoriumTags.MODID;
import static fr.cyrilneveu.craftorium.api.Registries.*;
import static fr.cyrilneveu.craftorium.common.inventory.CreativeTabs.MACHINES;
import static fr.cyrilneveu.craftorium.common.recipe.Maps.ELECTROLYZING;

public final class Machines {
    public static Machine ELECTROLYZER;

    public static void init() {
        if (MACHINES_REGISTRY.isInitialized())
            return;

        ELECTROLYZER = new MachineBuilder("electrolyzer")
                .size(176, 200)
                .itemInput(38, 46)
                .fluidInput(56, 46)
                .itemOutput(103, 17).itemOutput(121, 17).itemOutput(139, 17).itemOutput(103, 35).itemOutput(121, 35).itemOutput(139, 35)
                .fluidOutput(103, 57).fluidOutput(121, 57).fluidOutput(139, 57).fluidOutput(103, 75).fluidOutput(121, 75).fluidOutput(139, 75)
                .processor(ELECTROLYZING, 77, 47, 134, 96)
                .energy(153, 97)
                .flowControlled()
                .text(176 / 2, 6, String.join(".", "machine", MODID, "electrolyzer", "name"), true)
                .text(8, 106, "container.inventory", false)
                .playerInventory(7, 117)
                .build();
    }

    public static void close() {
        MACHINES_REGISTRY.close();
    }

    public static void createMachine(Machine machine, Tier tier) {
        MachineBlock block = new MachineBlock(machine, tier);

        String name = String.join("_", machine.getName(), "tier", tier.getName());
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

    public static Aestheticism.ObjectAestheticism getAestheticism(Machine machine, Tier tier) {
        FaceProvider[] faces = new FaceProvider[4];
        faces[0] = new FaceProvider(new ResourceLocation(MODID, String.join("/", "blocks", "machines", "machine_side")), tier.getAestheticism().getBaseColor());
        faces[1] = new FaceProvider(new ResourceLocation(MODID, String.join("/", "blocks", "machines", "machine_side_overlay")), RenderUtils.WHITE_COLOR);
        faces[2] = new FaceProvider(new ResourceLocation(MODID, String.join("/", "blocks", "machines", machine.getName())), RenderUtils.WHITE_COLOR);
        faces[3] = new FaceProvider(new ResourceLocation(MODID, String.join("/", "blocks", "machines", machine.getName() + "_on")), RenderUtils.WHITE_COLOR);

        return new Aestheticism.ObjectAestheticism(faces, () -> Settings.globalSettings.showAdvancedTooltips ? Collections.singletonList(Utils.localise("tooltip.craftorium.tier.name", tier.getDisplayName())) : Collections.emptyList(), false);
    }
}
