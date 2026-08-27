package fr.cyrilneveu.craftorium.common.machine;

import fr.cyrilneveu.craftorium.api.block.CustomBlock;
import fr.cyrilneveu.craftorium.api.machine.Machine;
import fr.cyrilneveu.craftorium.api.machine.MachineBlock;
import fr.cyrilneveu.craftorium.api.machine.MachineBuilder;
import fr.cyrilneveu.craftorium.api.machine.behaviour.EnergyInventoryProvider;
import fr.cyrilneveu.craftorium.api.machine.behaviour.IGetBehaviours;
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
    public static Machine CHARGER;
    public static Machine SOLID_FUEL_GENERATOR;

    public static void init() {
        if (MACHINES_REGISTRY.isInitialized())
            return;

        ELECTROLYZER = new MachineBuilder("electrolyzer")
                .itemInput(37, 63)
                .fluidInput(55, 63)
                .itemOutput(103, 18).itemOutput(121, 18).itemOutput(139, 18)
                .itemOutput(103, 36).itemOutput(121, 36).itemOutput(139, 36)
                .itemOutput(103, 54).itemOutput(121, 54).itemOutput(139, 54)
                .fluidOutput(103, 72).fluidOutput(121, 72).fluidOutput(139, 72)
                .fluidOutput(103, 90).fluidOutput(121, 90).fluidOutput(139, 90)
                .fluidOutput(103, 108).fluidOutput(121, 108).fluidOutput(139, 108)
                .processor(ELECTROLYZING, 77, 64, 134, 130)
                .energy(153, 131)
                .flowControlled()
                .text(176 / 2, 6, String.join(".", "machine", MODID, "electrolyzer", "name"), true)
                .text(8, 140, "container.inventory", false)
                .playerInventory(7, 151)
                .size(176, 234)
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
                .itemInput(19, 54).itemInput(37, 54).itemInput(55, 54)
                .fluidInput(19, 72).fluidInput(37, 72).fluidInput(55, 72)
                .fluidInput(19, 90).fluidInput(37, 90).fluidInput(55, 90)
                .fluidInput(19, 108).fluidInput(37, 108).fluidInput(55, 108)
                .itemOutput(103, 63)
                .fluidOutput(121, 63)
                .processor(MIXING, 77, 64, 134, 130)
                .energy(153, 131)
                .flowControlled()
                .text(176 / 2, 6, String.join(".", "machine", MODID, "mixer", "name"), true)
                .text(8, 140, "container.inventory", false)
                .playerInventory(7, 151)
                .size(176, 234)
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
                .size(176, 198)
                .build();
        CHARGER = new MachineBuilder("charger")
                .itemFree(61, 18).itemFree(79, 18).itemFree(97, 18)
                .itemFree(61, 36).itemFree(79, 36).itemFree(97, 36)
                .itemFree(61, 54).itemFree(79, 54).itemFree(97, 54)
                .energyBuffer()
                .energy(153, 77)
                .text(176 / 2, 6, String.join(".", "machine", MODID, "charger", "name"), true)
                .text(8, 86, "container.inventory", false)
                .playerInventory(7, 97)
                .build();
        SOLID_FUEL_GENERATOR = new MachineBuilder("solid_fuel_generator")
                .itemInput(79, 36)
                .solidFuelGenerator(81,56)
                .flowControlled()
                .energy(153, 77)
                .text(176 / 2, 6, String.join(".", "machine", MODID, "solid_fuel_generator", "name"), true)
                .text(8, 86, "container.inventory", false)
                .playerInventory(7, 97)
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
            for (IGetBehaviours provider : machine.getProviders()) {
                if (provider instanceof EnergyInventoryProvider energyInventory)
                    tooltips.add(Utils.localise("tooltip.craftorium.machine.slot.energy", (int) (energyInventory.getCapacity() * tier.getEnergyBuffer()), (int) (energyInventory.getTransfer() * tier.getEnergyBuffer())));
            }
            tooltips.add(Utils.localise("tooltip.craftorium.tier.name", tier.getDisplayName()));
            return tooltips;
        }, false, null);
    }
}
