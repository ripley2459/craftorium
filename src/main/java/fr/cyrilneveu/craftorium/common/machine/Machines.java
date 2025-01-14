package fr.cyrilneveu.craftorium.common.machine;

import fr.cyrilneveu.craftorium.api.block.CustomBlock;
import fr.cyrilneveu.craftorium.api.config.Settings;
import fr.cyrilneveu.craftorium.api.machine.Machine;
import fr.cyrilneveu.craftorium.api.machine.MachineBlock;
import fr.cyrilneveu.craftorium.api.machine.MachineBuilder;
import fr.cyrilneveu.craftorium.api.property.Aestheticism;
import fr.cyrilneveu.craftorium.api.render.FaceProvider;
import fr.cyrilneveu.craftorium.api.substance.Tier;
import fr.cyrilneveu.craftorium.api.utils.RenderUtils;
import fr.cyrilneveu.craftorium.api.utils.Utils;
import net.minecraft.util.ResourceLocation;

import java.util.LinkedList;
import java.util.List;

import static fr.cyrilneveu.craftorium.CraftoriumTags.MODID;
import static fr.cyrilneveu.craftorium.api.Registries.*;
import static fr.cyrilneveu.craftorium.common.inventory.CreativeTabs.MACHINES;
import static fr.cyrilneveu.craftorium.common.recipe.Maps.*;

public final class Machines {
    public static Machine ELECTROLYZER;
    public static Machine MACERATOR;
    public static Machine BENDER;
    public static Machine LATHE;
    public static Machine M_CUTTER;
    public static Machine COMPRESSOR;
    public static Machine FOUNDRY;
    public static Machine MIXER;
    public static Machine CIRCUIT_ASSEMBLER;
    public static Machine ASSEMBLER;

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
        MACERATOR = new MachineBuilder("macerator")
                .itemInput(55, 36)
                .itemOutput(103, 36)
                .processor(MACERATING, 77, 37, 134, 76)
                .energy(153, 77)
                .flowControlled()
                .text(176 / 2, 6, String.join(".", "machine", MODID, "macerator", "name"), true)
                .text(8, 86, "container.inventory", false)
                .playerInventory(7, 97)
                .build();
        BENDER = new MachineBuilder("bender")
                .itemInput(55, 36)
                .itemOutput(103, 36)
                .processor(BENDING, 77, 37, 134, 76)
                .energy(153, 77)
                .flowControlled()
                .text(176 / 2, 6, String.join(".", "machine", MODID, "bender", "name"), true)
                .text(8, 86, "container.inventory", false)
                .playerInventory(7, 97)
                .build();
        LATHE = new MachineBuilder("lathe")
                .itemInput(55, 36)
                .itemOutput(103, 36)
                .processor(SPINNING, 77, 37, 134, 76)
                .energy(153, 77)
                .flowControlled()
                .text(176 / 2, 6, String.join(".", "machine", MODID, "lathe", "name"), true)
                .text(8, 86, "container.inventory", false)
                .playerInventory(7, 97)
                .build();
        M_CUTTER = new MachineBuilder("cutter")
                .itemInput(55, 36)
                .itemOutput(103, 36)
                .processor(CUTTING, 77, 37, 134, 76)
                .energy(153, 77)
                .flowControlled()
                .text(176 / 2, 6, String.join(".", "machine", MODID, "cutter", "name"), true)
                .text(8, 86, "container.inventory", false)
                .playerInventory(7, 97)
                .build();
        COMPRESSOR = new MachineBuilder("compressor")
                .itemInput(55, 36)
                .itemOutput(103, 36)
                .processor(COMPRESSING, 77, 37, 134, 76)
                .energy(153, 77)
                .flowControlled()
                .text(176 / 2, 6, String.join(".", "machine", MODID, "compressor", "name"), true)
                .text(8, 86, "container.inventory", false)
                .playerInventory(7, 97)
                .build();
        FOUNDRY = new MachineBuilder("foundry")
                .itemInput(37, 36)
                .fluidInput(55, 36)
                .itemOutput(103, 36)
                .fluidOutput(121, 36)
                .processor(CASTING, 77, 37, 134, 76)
                .energy(153, 77)
                .flowControlled()
                .text(176 / 2, 6, String.join(".", "machine", MODID, "foundry", "name"), true)
                .text(8, 86, "container.inventory", false)
                .playerInventory(7, 97)
                .build();
        MIXER = new MachineBuilder("mixer")
                .itemInput(19, 18).itemInput(37, 18).itemInput(55, 18)
                .itemInput(19, 36).itemInput(37, 36).itemInput(55, 36)
                .fluidInput(19, 54).fluidInput(37, 54).fluidInput(55, 54)
                .fluidInput(19, 72).fluidInput(37, 72).fluidInput(55, 72)
                .itemOutput(103, 45)
                .fluidOutput(121, 45)
                .processor(MIXING, 77, 46, 134, 94)
                .energy(153, 95)
                .flowControlled()
                .text(176 / 2, 6, String.join(".", "machine", MODID, "mixer", "name"), true)
                .text(8, 104, "container.inventory", false)
                .playerInventory(7, 115)
                .size(176, 198)
                .build();
        CIRCUIT_ASSEMBLER = new MachineBuilder("circuit_assembler")
                .itemInput(19, 18).itemInput(37, 18).itemInput(55, 18)
                .itemInput(19, 36).itemInput(37, 36).itemInput(55, 36)
                .fluidInput(55, 54)
                .itemOutput(103, 36)
                .processor(CIRCUIT_ASSEMBLING, 77, 37, 134, 76)
                .energy(153, 77)
                .flowControlled()
                .text(176 / 2, 6, String.join(".", "machine", MODID, "circuit_assembler", "name"), true)
                .text(8, 86, "container.inventory", false)
                .playerInventory(7, 97)
                .build();
        ASSEMBLER = new MachineBuilder("assembler")
                .itemInput(19, 18).itemInput(37, 18).itemInput(55, 18)
                .itemInput(19, 36).itemInput(37, 36).itemInput(55, 36)
                .itemInput(19, 54).itemInput(37, 54).itemInput(55, 54)
                .fluidInput(55, 72)
                .itemOutput(103, 45)
                .processor(ASSEMBLING, 77, 46, 134, 94)
                .energy(153, 95)
                .flowControlled()
                .text(176 / 2, 6, String.join(".", "machine", MODID, "assembler", "name"), true)
                .text(8, 104, "container.inventory", false)
                .playerInventory(7, 115)
                .build();
    }

    public static void close() {
        MACHINES_REGISTRY.close();
    }

    public static void createMachine(Machine machine, Tier tier) {
        MachineBlock block = new MachineBlock(machine, tier);

        String name = machine.getName(tier);
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

        return new Aestheticism.ObjectAestheticism(faces, () -> {
            List<String> tooltips = new LinkedList<>();
            if (Settings.globalSettings.showAdvancedTooltips)
                tooltips.add(Utils.localise("tooltip.craftorium.tier.name", tier.getDisplayName()));
            //tooltips.add(Utils.localise("tooltip.craftorium.map.name", machine.));
            return tooltips;
        }, false, null);
    }
}
