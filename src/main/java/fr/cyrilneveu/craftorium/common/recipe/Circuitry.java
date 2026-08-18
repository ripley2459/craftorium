package fr.cyrilneveu.craftorium.common.recipe;

import fr.cyrilneveu.craftorium.api.inventory.OreStack;
import fr.cyrilneveu.craftorium.api.recipe.machine.MachineRecipeBuilder;
import fr.cyrilneveu.craftorium.api.recipe.vanilla.RecipeManager;
import fr.cyrilneveu.craftorium.common.ACommonProxy;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

import static fr.cyrilneveu.craftorium.common.recipe.Maps.*;
import static fr.cyrilneveu.craftorium.common.substance.Substances.*;
import static fr.cyrilneveu.craftorium.common.substance.SubstancesObjects.*;

public final class Circuitry {
    public static void registerRecipes() {
        RecipeManager.addShapedRecipe("circuit_board_redstone", ACommonProxy.getItemStack("circuit_board_redstone", 6),
                "TSR",
                "PPP",
                "RSG",
                'T', SAW.asIngredient(null),
                'S', new ItemStack(Blocks.STONE_SLAB, 1, 0) /* OreStack.getIngredient("slabStone"), TODO: WHY THE ORE DOESN'T WORK? */,
                'P', PLATE.asIngredient(REDSTONE_ALLOY),
                'G', OreStack.getIngredient("glue"),
                'R', DUST.asIngredient(REDSTONE)
        );
        RecipeManager.addShapedRecipe("redstone_circuit", ACommonProxy.getItemStack("redstone_circuit"),
                " C ",
                "RPR",
                "FDF",
                'C', ACommonProxy.getItemStack("redstone_chip"),
                'R', WIRE.asIngredient(REDSTONE_ALLOY),
                'P', ACommonProxy.getItemStack("circuit_board_redstone"),
                'F', ACommonProxy.getItemStack("redstone_resistor"),
                'D', ACommonProxy.getItemStack("redstone_capacitor")
        );
        RecipeManager.addShapedRecipe("advanced_redstone_circuit", ACommonProxy.getItemStack("advanced_redstone_circuit"),
                "RCD",
                "FPF",
                "HCR",
                'R', WIRE.asIngredient(REDSTONE_ALLOY),
                'C', ACommonProxy.getItemStack("redstone_chip"),
                'F', ACommonProxy.getItemStack("redstone_resistor"),
                'P', ACommonProxy.getItemStack("circuit_board_redstone"),
                'D', ACommonProxy.getItemStack("redstone_capacitor"),
                'H', ACommonProxy.getItemStack("redstone_diode")
        );

        RecipeManager.addShapedRecipe("primitive_circuit", ACommonProxy.getItemStack("primitive_circuit"),
                "RCR",
                "FPD",
                'C', ACommonProxy.getItemStack("chip"),
                'R', WIRE.asIngredient(COPPER),
                'P', ACommonProxy.getItemStack("circuit_board_1"),
                'F', ACommonProxy.getItemStack("resistor"),
                'D', ACommonProxy.getItemStack("diode")
        );
        RecipeManager.addShapedRecipe("advanced_circuit", ACommonProxy.getItemStack("advanced_circuit"),
                "RCR",
                "FPD",
                "FGW",
                'C', ACommonProxy.getItemStack("chip"),
                'R', WIRE.asIngredient(GOLD),
                'P', ACommonProxy.getItemStack("circuit_board_2"),
                'F', ACommonProxy.getItemStack("resistor"),
                'D', ACommonProxy.getItemStack("diode"),
                'W', ACommonProxy.getItemStack("capacitor"),
                'G', WIRE.asIngredient(COPPER)
        );
        RecipeManager.addShapedRecipe("maxed_circuit", ACommonProxy.getItemStack("maxed_circuit"),
                "RCR",
                "FPD",
                "FCW",
                'C', ACommonProxy.getItemStack("chip"),
                'R', WIRE.asIngredient(ELECTRUM),
                'P', ACommonProxy.getItemStack("circuit_board_3"),
                'F', ACommonProxy.getItemStack("resistor"),
                'D', ACommonProxy.getItemStack("diode"),
                'W', ACommonProxy.getItemStack("capacitor")
        );

        RecipeManager.addShapedRecipe("redstone_capacitor", ACommonProxy.getItemStack("redstone_capacitor", 3),
                "FFF",
                "MST",
                "WCW",
                'F', OreStack.getIngredient("paper"),
                'M', FOIL.asIngredient(MANGANESE),
                'S', FOIL.asIngredient(REDSTONE_ALLOY),
                'T', FOIL.asIngredient(TANTALUM),
                'C', CUTTER.asIngredient(null),
                'W', WIRE.asIngredient(REDSTONE_ALLOY)
        );
        RecipeManager.addShapedRecipe("redstone_resistor", ACommonProxy.getItemStack("redstone_resistor", 3),
                " W ",
                "FDC",
                " W ",
                'F', OreStack.getIngredient("paper"),
                'D', DUST.asIngredient(COAL),
                'C', CUTTER.asIngredient(null),
                'W', WIRE.asIngredient(REDSTONE_ALLOY)
        );
        RecipeManager.addShapedRecipe("redstone_chip", ACommonProxy.getItemStack("redstone_chip"),
                "PCP",
                "VVV",
                "PWP",
                'P', OreStack.getIngredient("paper"),
                'C', CUTTER.asIngredient(null),
                'V', ACommonProxy.getItemStack("redstone_diode"),
                'W', WIRE.asIngredient(REDSTONE_ALLOY)
        );
        RecipeManager.addShapedRecipe("redstone_diode", ACommonProxy.getItemStack("redstone_diode", 3),
                " G ",
                "TDR",
                "JCW",
                'T', FOIL.asIngredient(TIN),
                'R', FOIL.asIngredient(REDSTONE_ALLOY),
                'J', WIRE.asIngredient(TIN),
                'D', DUST.asIngredient(QUARTZ),
                'G', OreStack.getIngredient("blockGlass"),
                'C', CUTTER.asIngredient(null),
                'W', WIRE.asIngredient(REDSTONE_ALLOY)
        );

        CUTTING.addRecipe(new MachineRecipeBuilder("ram_die")
                .consumeItem(ACommonProxy.getItemStack("ram_wafer"))
                .produceItem(ACommonProxy.getItemStack("ram_die", 8))
                .consumeEnergy(50000)
                .duration(1000)
                .configuration(CONFIGURATION_CUTTER_CIRCUIT)
                .build());
        CUTTING.addRecipe(new MachineRecipeBuilder("power_die")
                .consumeItem(ACommonProxy.getItemStack("power_wafer"))
                .produceItem(ACommonProxy.getItemStack("power_die", 8))
                .consumeEnergy(50000)
                .duration(1000)
                .configuration(CONFIGURATION_CUTTER_CIRCUIT)
                .build());
        CUTTING.addRecipe(new MachineRecipeBuilder("calculus_die")
                .consumeItem(ACommonProxy.getItemStack("calculus_wafer"))
                .produceItem(ACommonProxy.getItemStack("calculus_die", 8))
                .consumeEnergy(50000)
                .duration(1000)
                .configuration(CONFIGURATION_CUTTER_CIRCUIT)
                .build());
        CUTTING.addRecipe(new MachineRecipeBuilder("quantum_calculus_die")
                .consumeItem(ACommonProxy.getItemStack("quantum_calculus_wafer"))
                .produceItem(ACommonProxy.getItemStack("quantum_calculus_die", 8))
                .consumeEnergy(50000)
                .duration(1000)
                .configuration(CONFIGURATION_CUTTER_CIRCUIT)
                .build());

        CASTING.addRecipe(new MachineRecipeBuilder("ram_wafer")
                .consumeItem(WAFER.getOre(SILICON), 1)
                .produceItem(ACommonProxy.getItemStack("ram_wafer", 1))
                .consumeEnergy(100000)
                .duration(350)
                .configuration(CONFIGURATION_CASTING_ANNEAL)
                .build());
        CASTING.addRecipe(new MachineRecipeBuilder("power_wafer")
                .consumeItem(WAFER.getOre(DOPED_GAAS_SILICON), 1)
                .produceItem(ACommonProxy.getItemStack("power_wafer", 1))
                .consumeEnergy(100000)
                .duration(350)
                .configuration(CONFIGURATION_CASTING_ANNEAL)
                .build());
        CASTING.addRecipe(new MachineRecipeBuilder("calculus_wafer")
                .consumeItem(WAFER.getOre(DOPED_PN_SILICON), 1)
                .produceItem(ACommonProxy.getItemStack("calculus_wafer", 1))
                .consumeEnergy(100000)
                .duration(350)
                .configuration(CONFIGURATION_CASTING_ANNEAL)
                .build());
        CASTING.addRecipe(new MachineRecipeBuilder("quantum_calculus_wafer")
                .consumeItem(WAFER.getOre(DOPED_GRAPHENE_SILICON), 1)
                .produceItem(ACommonProxy.getItemStack("quantum_calculus_wafer", 1))
                .consumeEnergy(100000)
                .duration(350)
                .configuration(CONFIGURATION_CASTING_ANNEAL)
                .build());
    }
}
