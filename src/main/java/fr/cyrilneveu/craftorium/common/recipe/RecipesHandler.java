package fr.cyrilneveu.craftorium.common.recipe;

import fr.cyrilneveu.craftorium.api.inventory.OreStack;
import fr.cyrilneveu.craftorium.api.recipe.machine.MachineRecipeBuilder;
import fr.cyrilneveu.craftorium.api.recipe.vanilla.RecipeManager;
import fr.cyrilneveu.craftorium.api.substance.Substance;
import fr.cyrilneveu.craftorium.api.substance.SubstanceStack;
import fr.cyrilneveu.craftorium.api.substance.Tier;
import fr.cyrilneveu.craftorium.api.substance.property.Composition;
import fr.cyrilneveu.craftorium.api.substance.property.SubstanceProperties;
import fr.cyrilneveu.craftorium.api.utils.EColors;
import fr.cyrilneveu.craftorium.api.utils.Utils;
import fr.cyrilneveu.craftorium.common.ACommonProxy;
import fr.cyrilneveu.craftorium.common.machine.Machines;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.oredict.OreDictionary;

import static fr.cyrilneveu.craftorium.api.Registries.SUBSTANCES_REGISTRY;
import static fr.cyrilneveu.craftorium.common.machine.Machines.*;
import static fr.cyrilneveu.craftorium.common.recipe.Maps.*;
import static fr.cyrilneveu.craftorium.common.substance.Substances.*;
import static fr.cyrilneveu.craftorium.common.substance.SubstancesObjects.*;
import static fr.cyrilneveu.craftorium.common.substance.Tiers.*;

public final class RecipesHandler {
    public static void registerRecipes(RegistryEvent.Register<IRecipe> event) {
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

        registerMotorRecipe(ONE);
        registerPistonRecipe(ONE);
        registerHeatExchangerRecipe(ONE, RUBBER);
        registerPumpRecipe(ONE, RUBBER);
        registerEmitterRecipe(ONE, DIAMOND, "circuitTier1");
        registerSensorRecipe(ONE, ENDER, "circuitTier1");
        registerBatteryRecipe(ONE);
        registerRobotArmRecipe(ONE, "circuitTier1");

        registerCircuitAssemblerRecipe(ONE, "circuitTier1");
        registerMixerRecipe(ONE, "circuitTier1");
        registerFoundryRecipe(ONE, "circuitTier1");
        registerCompressorRecipe(ONE, "circuitTier1");
        registerElectrolyzerRecipe(ONE, "circuitTier1");
        registerMaceratorRecipe(ONE, "circuitTier1");
        registerBenderRecipe(ONE, "circuitTier1");
        registerCutterRecipe(ONE, "circuitTier1");
        registerLatheRecipe(ONE, "circuitTier1");

        registerMotorRecipe(TWO);
        registerPistonRecipe(TWO);
        registerHeatExchangerRecipe(TWO, RUBBER);
        registerPumpRecipe(TWO, RUBBER);
        registerEmitterRecipe(TWO, DIAMOND, "circuitTier2");
        registerSensorRecipe(TWO, ENDER, "circuitTier2");
        registerBatteryRecipe(TWO);
        registerRobotArmRecipe(TWO, "circuitTier2");

        registerCircuitAssemblerRecipe(TWO, "circuitTier2");
        registerMixerRecipe(TWO, "circuitTier2");
        registerFoundryRecipe(TWO, "circuitTier2");
        registerCompressorRecipe(TWO, "circuitTier2");
        registerElectrolyzerRecipe(TWO, "circuitTier2");
        registerMaceratorRecipe(TWO, "circuitTier2");
        registerBenderRecipe(TWO, "circuitTier2");
        registerCutterRecipe(TWO, "circuitTier2");
        registerLatheRecipe(TWO, "circuitTier2");

        registerMotorRecipe(THREE);
        registerPistonRecipe(THREE);
        registerHeatExchangerRecipe(THREE, RUBBER);
        registerPumpRecipe(THREE, RUBBER);
        registerEmitterRecipe(THREE, DIAMOND, "circuitTier3");
        registerSensorRecipe(THREE, ENDER, "circuitTier3");
        registerBatteryRecipe(THREE);
        registerRobotArmRecipe(THREE, "circuitTier3");

        registerCircuitAssemblerRecipe(THREE, "circuitTier3");
        registerMixerRecipe(THREE, "circuitTier3");
        registerFoundryRecipe(THREE, "circuitTier3");
        registerCompressorRecipe(THREE, "circuitTier3");
        registerElectrolyzerRecipe(THREE, "circuitTier3");
        registerMaceratorRecipe(THREE, "circuitTier3");
        registerBenderRecipe(THREE, "circuitTier3");
        registerCutterRecipe(THREE, "circuitTier3");
        registerLatheRecipe(THREE, "circuitTier3");

        registerMotorRecipe(FOUR);
        registerPistonRecipe(FOUR);
        registerHeatExchangerRecipe(FOUR, RUBBER);
        registerPumpRecipe(FOUR, RUBBER);
        registerEmitterRecipe(FOUR, DIAMOND, "circuitTier4");
        registerSensorRecipe(FOUR, ENDER, "circuitTier4");
        registerBatteryRecipe(FOUR);
        registerRobotArmRecipe(FOUR, "circuitTier4");

        registerCircuitAssemblerRecipe(FOUR, "circuitTier4");
        registerMixerRecipe(FOUR, "circuitTier4");
        registerFoundryRecipe(FOUR, "circuitTier5");
        registerCompressorRecipe(FOUR, "circuitTier4");
        registerElectrolyzerRecipe(FOUR, "circuitTier4");
        registerMaceratorRecipe(FOUR, "circuitTier4");
        registerBenderRecipe(FOUR, "circuitTier4");
        registerCutterRecipe(FOUR, "circuitTier4");
        registerLatheRecipe(FOUR, "circuitTier4");

        registerMotorRecipe(FIVE);
        registerPistonRecipe(FIVE);
        registerHeatExchangerRecipe(FIVE, RUBBER);
        registerPumpRecipe(FIVE, RUBBER);
        registerEmitterRecipe(FIVE, DIAMOND, "circuitTier5");
        registerSensorRecipe(FIVE, ENDER, "circuitTier5");
        registerBatteryRecipe(FIVE);
        registerRobotArmRecipe(FIVE, "circuitTier5");

        registerCircuitAssemblerRecipe(FIVE, "circuitTier5");
        registerMixerRecipe(FIVE, "circuitTier5");
        registerFoundryRecipe(FIVE, "circuitTier5");
        registerCompressorRecipe(FIVE, "circuitTier5");
        registerElectrolyzerRecipe(FIVE, "circuitTier5");
        registerMaceratorRecipe(FIVE, "circuitTier5");
        registerBenderRecipe(FIVE, "circuitTier5");
        registerCutterRecipe(FIVE, "circuitTier5");
        registerLatheRecipe(FIVE, "circuitTier5");

        CUTTING.addRecipe(new MachineRecipeBuilder("oak_planks")
                .consumeItem(new ItemStack(Blocks.LOG))
                .produceItem(new ItemStack(Blocks.PLANKS, 6))
                .consumeEnergy(3000)
                .duration(200)
                .configuration(CONFIGURATION_CUTTER_LARGE)
                .build());
        CUTTING.addRecipe(new MachineRecipeBuilder("oak_spruce")
                .consumeItem(new ItemStack(Blocks.LOG, 1, 1))
                .produceItem(new ItemStack(Blocks.PLANKS, 6, 1))
                .consumeEnergy(3000)
                .duration(200)
                .configuration(CONFIGURATION_CUTTER_LARGE)
                .build());
        CUTTING.addRecipe(new MachineRecipeBuilder("oak_birch")
                .consumeItem(new ItemStack(Blocks.LOG, 1, 2))
                .produceItem(new ItemStack(Blocks.PLANKS, 6, 2))
                .consumeEnergy(3000)
                .duration(200)
                .configuration(CONFIGURATION_CUTTER_LARGE)
                .build());
        CUTTING.addRecipe(new MachineRecipeBuilder("oak_jungle")
                .consumeItem(new ItemStack(Blocks.LOG, 1, 3))
                .produceItem(new ItemStack(Blocks.PLANKS, 6, 3))
                .consumeEnergy(3000)
                .duration(200)
                .configuration(CONFIGURATION_CUTTER_LARGE)
                .build());
        CUTTING.addRecipe(new MachineRecipeBuilder("acacia_birch")
                .consumeItem(new ItemStack(Blocks.LOG2))
                .produceItem(new ItemStack(Blocks.PLANKS, 6, 4))
                .consumeEnergy(3000)
                .duration(200)
                .configuration(CONFIGURATION_CUTTER_LARGE)
                .build());
        CUTTING.addRecipe(new MachineRecipeBuilder("dark_oak_jungle")
                .consumeItem(new ItemStack(Blocks.LOG2, 1, 1))
                .produceItem(new ItemStack(Blocks.PLANKS, 6, 5))
                .consumeEnergy(3000)
                .duration(200)
                .configuration(CONFIGURATION_CUTTER_LARGE)
                .build());

        COMPRESSING.addRecipe(new MachineRecipeBuilder("brick_block_from_brick")
                .consumeItem("ingotBrick", 4)
                .produceItem(new ItemStack(Blocks.BRICK_BLOCK))
                .consumeEnergy(1000)
                .duration(100)
                .configuration(CONFIGURATION_COMPRESSOR_ZIP)
                .build());
        COMPRESSING.addRecipe(new MachineRecipeBuilder("nether_brick_from_brick_nether")
                .consumeItem("ingotBrickNether", 4)
                .produceItem(new ItemStack(Blocks.NETHER_BRICK))
                .consumeEnergy(1000)
                .duration(100)
                .configuration(CONFIGURATION_COMPRESSOR_ZIP)
                .build());
        COMPRESSING.addRecipe(new MachineRecipeBuilder("sandstone_from_sand")
                .consumeItem(new ItemStack(Blocks.SAND, 4))
                .produceItem(new ItemStack(Blocks.SANDSTONE))
                .consumeEnergy(1000)
                .duration(100)
                .configuration(CONFIGURATION_COMPRESSOR_ZIP)
                .build());
        COMPRESSING.addRecipe(new MachineRecipeBuilder("red_sandstone_from_red_sand")
                .consumeItem(new ItemStack(Blocks.SAND, 4, 1))
                .produceItem(new ItemStack(Blocks.RED_SANDSTONE))
                .consumeEnergy(1000)
                .duration(100)
                .configuration(CONFIGURATION_COMPRESSOR_ZIP)
                .build());
        COMPRESSING.addRecipe(new MachineRecipeBuilder("white_wool_from_string")
                .consumeItem("string", 4)
                .produceItem(new ItemStack(Blocks.WOOL))
                .consumeEnergy(1000)
                .duration(100)
                .configuration(CONFIGURATION_COMPRESSOR_ZIP)
                .build());
        COMPRESSING.addRecipe(new MachineRecipeBuilder("snow_block_from_snowball")
                .consumeItem(new ItemStack(Items.SNOWBALL, 4))
                .produceItem(new ItemStack(Blocks.SNOW))
                .consumeEnergy(1000)
                .duration(100)
                .configuration(CONFIGURATION_COMPRESSOR_ZIP)
                .build());
        COMPRESSING.addRecipe(new MachineRecipeBuilder("hay_block_from_wheat")
                .consumeItem("cropWheat", 9)
                .produceItem(new ItemStack(Blocks.HAY_BLOCK))
                .consumeEnergy(1000)
                .duration(100)
                .configuration(CONFIGURATION_COMPRESSOR_ZIP)
                .build());

        MACERATING.addRecipe(new MachineRecipeBuilder("cobblestone_from_stone")
                .consumeItem(new ItemStack(Blocks.STONE))
                .produceItem(new ItemStack(Blocks.COBBLESTONE))
                .consumeEnergy(3000)
                .duration(200)
                .configuration(CONFIGURATION_MACERATOR_BREAKING)
                .build());
        MACERATING.addRecipe(new MachineRecipeBuilder("gravel_from_cobblestone")
                .consumeItem(new ItemStack(Blocks.COBBLESTONE))
                .produceItem(new ItemStack(Blocks.GRAVEL))
                .consumeEnergy(2000)
                .duration(200)
                .configuration(CONFIGURATION_MACERATOR_PULVERIZING)
                .build());
        MACERATING.addRecipe(new MachineRecipeBuilder("sand_from_gravel")
                .consumeItem(new ItemStack(Blocks.GRAVEL))
                .produceItem(new ItemStack(Blocks.SAND))
                .consumeEnergy(1000)
                .duration(200)
                .configuration(CONFIGURATION_MACERATOR_PULVERIZING)
                .build());
        MACERATING.addRecipe(new MachineRecipeBuilder("cracked_stonebrick_from_stonebrick")
                .consumeItem(new ItemStack(Blocks.STONEBRICK))
                .produceItem(new ItemStack(Blocks.STONEBRICK, 1, 2))
                .consumeEnergy(4000)
                .duration(200)
                .configuration(CONFIGURATION_MACERATOR_BREAKING)
                .build());
        MACERATING.addRecipe(new MachineRecipeBuilder("sand_from_sandstone")
                .consumeItem(new ItemStack(Blocks.SANDSTONE, 1, OreDictionary.WILDCARD_VALUE))
                .produceItem(new ItemStack(Blocks.SAND, 4))
                .consumeEnergy(2000)
                .duration(200)
                .configuration(CONFIGURATION_MACERATOR_PULVERIZING)
                .build());
        MACERATING.addRecipe(new MachineRecipeBuilder("red_sand_from_red_sandstone")
                .consumeItem(new ItemStack(Blocks.RED_SANDSTONE, 1, OreDictionary.WILDCARD_VALUE))
                .produceItem(new ItemStack(Blocks.SAND, 4, 1))
                .consumeEnergy(2000)
                .duration(200)
                .configuration(CONFIGURATION_MACERATOR_PULVERIZING)
                .build());
        MACERATING.addRecipe(new MachineRecipeBuilder("string_from_wool")
                .consumeItem(new ItemStack(Blocks.WOOL))
                .produceItem(new ItemStack(Items.STRING, 4))
                .consumeEnergy(1000)
                .duration(200)
                .configuration(CONFIGURATION_MACERATOR_PULVERIZING)
                .build());

        CUTTING.addRecipe(new MachineRecipeBuilder("ram_die")
                .consumeItem(ACommonProxy.getItemStack("ram_wafer"))
                .produceItem(ACommonProxy.getItemStack("ram_die", 16))
                .consumeEnergy(50000)
                .duration(1000)
                .configuration(CONFIGURATION_CUTTER_CIRCUIT)
                .build());
        CUTTING.addRecipe(new MachineRecipeBuilder("power_die")
                .consumeItem(ACommonProxy.getItemStack("power_wafer"))
                .produceItem(ACommonProxy.getItemStack("power_die", 16))
                .consumeEnergy(50000)
                .duration(1000)
                .configuration(CONFIGURATION_CUTTER_CIRCUIT)
                .build());
        CUTTING.addRecipe(new MachineRecipeBuilder("calculus_die")
                .consumeItem(ACommonProxy.getItemStack("calculus_wafer"))
                .produceItem(ACommonProxy.getItemStack("calculus_die", 16))
                .consumeEnergy(50000)
                .duration(1000)
                .configuration(CONFIGURATION_CUTTER_CIRCUIT)
                .build());
        CUTTING.addRecipe(new MachineRecipeBuilder("quantum_calculus_die")
                .consumeItem(ACommonProxy.getItemStack("quantum_calculus_wafer"))
                .produceItem(ACommonProxy.getItemStack("quantum_calculus_die", 16))
                .consumeEnergy(50000)
                .duration(1000)
                .configuration(CONFIGURATION_CUTTER_CIRCUIT)
                .build());

        MIXING.addRecipe(new MachineRecipeBuilder("mixing_dirt_and_gravel")
                .consumeItem("dirt", 2)
                .consumeItem("gravel", 2)
                .produceItem(new ItemStack(Blocks.DIRT, 4, 1))
                .consumeEnergy(1000)
                .duration(20)
                .configuration(CONFIGURATION_MIXING_MIX)
                .build());

        for (EColors color : EColors.values()) {
            MIXING.addRecipe(new MachineRecipeBuilder("solidifying_conrete_".concat(color.getName()))
                    .consumeItem(new ItemStack(Blocks.CONCRETE_POWDER, 1, color.getMeta()))
                    .consumeFluid("water", 1000)
                    .produceItem(new ItemStack(Blocks.CONCRETE, 1, color.getMeta()))
                    .consumeEnergy(3000)
                    .duration(10)
                    .configuration(CONFIGURATION_MIXING_MIX)
                    .build());
            MIXING.addRecipe(new MachineRecipeBuilder("mixing_conrete_powder_".concat(color.getName()))
                    .consumeItem("gravel", 4)
                    .consumeItem("sand", 4)
                    .consumeItem(OreStack.createOre("dye", color.getName()), 1)
                    .produceItem(new ItemStack(Blocks.CONCRETE_POWDER, 8, color.getMeta()))
                    .consumeEnergy(4000)
                    .duration(20)
                    .configuration(CONFIGURATION_MIXING_MIX)
                    .build());
        }

        CIRCUIT_ASSEMBLING.addRecipe(new MachineRecipeBuilder("microprocessor")
                .consumeItem(WIRE.getOre(TWO.getEnergy()), 1)
                .consumeItem(ACommonProxy.getItemStack("ram_die", 1))
                .consumeItem(ACommonProxy.getItemStack("calculus_die", 1))
                .produceItem(ACommonProxy.getItemStack("microprocessor"))
                .consumeEnergy(100000)
                .duration(300)
                .configuration(CONFIGURATION_CIRCUIT_ASSEMBLER_BASE)
                .build());
        CIRCUIT_ASSEMBLING.addRecipe(new MachineRecipeBuilder("processor")
                .consumeItem(WIRE.getOre(THREE.getEnergy()), 4)
                .consumeItem(ACommonProxy.getItemStack("microprocessor", 4))
                .consumeItem(ACommonProxy.getItemStack("ram_die", 4))
                .consumeItem(ACommonProxy.getItemStack("calculus_die", 4))
                .consumeItem(ACommonProxy.getItemStack("power_die", 1))
                .produceItem(ACommonProxy.getItemStack("processor"))
                .consumeEnergy(400000)
                .duration(1200)
                .configuration(CONFIGURATION_CIRCUIT_ASSEMBLER_BASE)
                .build());
        CIRCUIT_ASSEMBLING.addRecipe(new MachineRecipeBuilder("advanced_processor")
                .consumeItem(WIRE.getOre(FOUR.getEnergy()), 16)
                .consumeItem(ACommonProxy.getItemStack("processor", 4))
                .consumeItem(ACommonProxy.getItemStack("ram_die", 16))
                .consumeItem(ACommonProxy.getItemStack("calculus_die", 16))
                .consumeItem(ACommonProxy.getItemStack("power_die", 4))
                .produceItem(ACommonProxy.getItemStack("advanced_processor"))
                .consumeEnergy(1600000)
                .duration(4800)
                .configuration(CONFIGURATION_CIRCUIT_ASSEMBLER_BASE)
                .build());
        CIRCUIT_ASSEMBLING.addRecipe(new MachineRecipeBuilder("mainframe")
                .consumeItem(WIRE.getOre(FIVE.getEnergy()), 64)
                .consumeItem(ACommonProxy.getItemStack("advanced_processor", 4))
                .consumeItem(ACommonProxy.getItemStack("ram_die", 64))
                .consumeItem(ACommonProxy.getItemStack("calculus_die", 64))
                .consumeItem(ACommonProxy.getItemStack("power_die", 16))
                .produceItem(ACommonProxy.getItemStack("mainframe"))
                .consumeEnergy(6400000)
                .duration(19200)
                .configuration(CONFIGURATION_CIRCUIT_ASSEMBLER_BASE)
                .build());

        carbonFiber:
        {
            ELECTROLYZING.addRecipe(new MachineRecipeBuilder("mainframe")
                    .consumeFluid(LIQUID.getName(POLYACRYLONITRILE), MESH.getAmount())
                    .produceItem(MESH.asItemStack(CARBON_FIBER))
                    .consumeEnergy(35000)
                    .duration(200)
                    .configuration(CONFIGURATION_ELECTROLYZING_REFORMING)
                    .build());
            COMPRESSING.addRecipe(new MachineRecipeBuilder("carbon_fiber_plate")
                    .consumeItem(MESH.getOre(CARBON_FIBER), 1)
                    .produceItem(PLATE.asItemStack(CARBON_FIBER))
                    .consumeEnergy(60000)
                    .duration(500)
                    .configuration(CONFIGURATION_COMPRESSOR_PRESSING)
                    .build());
            COMPRESSING.addRecipe(new MachineRecipeBuilder("carbon_fiber_foil")
                    .consumeItem(MESH.getOre(CARBON_FIBER), 1)
                    .produceItem(FOIL.asItemStack(CARBON_FIBER))
                    .consumeEnergy(80000)
                    .duration(500)
                    .configuration(CONFIGURATION_COMPRESSOR_PRESSING_ALT)
                    .build());
            CUTTING.addRecipe(new MachineRecipeBuilder("carbon_fiber_wire")
                    .consumeItem(FOIL.getOre(CARBON_FIBER), 1)
                    .produceItem(WIRE.asItemStack(CARBON_FIBER, 4))
                    .consumeEnergy(100000)
                    .duration(800)
                    .configuration(CONFIGURATION_CUTTER_FINE)
                    .build());
        }

        plastic:
        {
            ELECTROLYZING.addRecipe(new MachineRecipeBuilder("plastic_distillation")
                    .consumeFluid(OIL_SAND.getName(), 144)
                    .produceFluid(PLASTIC.getName(), 144)
                    .consumeEnergy(3000)
                    .duration(80)
                    .configuration(CONFIGURATION_ELECTROLYZING_REFORMING)
                    .build());
        }

        for (Substance substance : SUBSTANCES_REGISTRY.getAll().values()) {
            Composition composition = substance.getComposition();

            if (!composition.isComposite())
                continue;

            electrolyzing:
            {
                if (substance.getProperties().containsKey(SubstanceProperties.KeyProperties.VEIN_MEMBER) || composition.getComposition().size() + composition.getPossible().size() > ELECTROLYZING.getItemsOut())
                    break electrolyzing;

                boolean flag = Utils.all(composition.getComposition(), s -> OreStack.oresExist(DUST.getOre(s.getSubstance())));
                flag &= Utils.all(composition.getPossible(), s -> OreStack.oresExist(DUST.getOre(s.getSubstance())));
                if (!flag)
                    break electrolyzing;

                MachineRecipeBuilder recipe = new MachineRecipeBuilder("electrolyzing_" + DUST.getName(substance));

                int a = 0;

                for (SubstanceStack stack : composition.getComposition()) {
                    recipe.produceItem(DUST.asItemStack(stack.getSubstance(), stack.getAmount()));
                    a += stack.getAmount();
                }

                for (SubstanceStack stack : composition.getPossible()) {
                    recipe.produceItem(DUST.asItemStack(stack.getSubstance(), stack.getAmount()), stack.getChance());
                    a += stack.getAmount();
                }

                ELECTROLYZING.addRecipe(recipe.consumeItem(DUST.asItemStack(substance, a)).duration(80 * a).consumeEnergy(10000 * a).configuration(CONFIGURATION_MIXING_MIX).build());
            }
        }
    }

    public static void unregisterRecipes(RegistryEvent.Register<IRecipe> event) {

    }

    private static void registerMotorRecipe(Tier tier) {
        if (!tier.getItems().contains(MOTOR))
            return;

        RecipeManager.addShapedRecipe(MOTOR.getName(tier), MOTOR.asItemStack(tier),
                "WC", "FR", " C",
                'W', WRENCH.asIngredient(null),
                'C', WIRE.asIngredient(tier.getEnergy()),
                'F', FOIL.asIngredient(tier.getMechanical()),
                'R', ROD.asIngredient(tier.getMechanical())
        );
    }

    private static void registerPistonRecipe(Tier tier) {
        if (!tier.getItems().contains(PISTON))
            return;

        RecipeManager.addShapedRecipe(PISTON.getName(tier), PISTON.asItemStack(tier),
                "PW", "RG", "MC",
                'P', PLATE.asIngredient(tier.getMechanical()),
                'W', WRENCH.asIngredient(null),
                'R', ROD.asIngredient(tier.getMechanical()),
                'G', GEAR.asIngredient(tier.getMechanical()),
                'M', MOTOR.asItemStack(tier),
                'C', WIRE.asIngredient(tier.getEnergy())
        );
    }

    private static void registerHeatExchangerRecipe(Tier tier, Substance fluidIsolator) {
        if (!tier.getItems().contains(HEAT_EXCHANGER))
            return;

        RecipeManager.addShapedRecipe(HEAT_EXCHANGER.getName(tier), HEAT_EXCHANGER.asItemStack(tier),
                "FN", "RS", "VC",
                'F', FOIL.asIngredient(tier.getFluid()),
                'N', SCREWDRIVER.asIngredient(null),
                'R', RING.asIngredient(fluidIsolator),
                'S', SCREW.asIngredient(tier.getMechanical()),
                'V', FOIL.asIngredient(tier.getHeat()),
                'C', WRENCH.asIngredient(null)
        );
    }

    private static void registerPumpRecipe(Tier tier, Substance fluidIsolator) {
        if (!tier.getItems().contains(PUMP))
            return;

        RecipeManager.addShapedRecipe(PUMP.getName(tier), PUMP.asItemStack(tier),
                "SMP", "WFL", "PKC",
                'S', SCREW.asIngredient(tier.getMechanical()),
                'M', MOTOR.asItemStack(tier),
                'P', RING.asIngredient(fluidIsolator),
                'W', WRENCH.asIngredient(null),
                'F', FOIL.asIngredient(tier.getFluid()),
                'L', SCREWDRIVER.asIngredient(null),
                'K', FOIL.asIngredient(tier.getCarcass()),
                'C', WIRE.asIngredient(tier.getEnergy())
        );
    }

    private static void registerEmitterRecipe(Tier tier, Substance emitter, String circuit) {
        if (!tier.getItems().contains(EMITTER))
            return;

        RecipeManager.addShapedRecipe(EMITTER.getName(tier), EMITTER.asItemStack(tier),
                "NSD", "PRW", "JPC",
                'N', SCREWDRIVER.asIngredient(null),
                'S', SCREW.asIngredient(tier.getMechanical()),
                'D', GEM.asIngredient(emitter),
                'P', PLATE.asIngredient(tier.getCarcass()),
                'R', ROD.asIngredient(tier.getMechanical()),
                'W', WIRE.asIngredient(tier.getEnergy()),
                'J', OreStack.getIngredient(circuit),
                'C', WRENCH.asIngredient(null)
        );
    }

    private static void registerSensorRecipe(Tier tier, Substance sensor, String circuit) {
        if (!tier.getItems().contains(SENSOR))
            return;

        RecipeManager.addShapedRecipe(SENSOR.getName(tier), SENSOR.asItemStack(tier),
                "CFW", "KDS", "NFW",
                'C', WRENCH.asIngredient(null),
                'F', FOIL.asIngredient(tier.getCarcass()),
                'W', WIRE.asIngredient(tier.getEnergy()),
                'K', OreStack.getIngredient(circuit),
                'D', PEARL.asIngredient(sensor),
                'S', SCREW.asIngredient(tier.getMechanical()),
                'N', SCREWDRIVER.asIngredient(null)
        );
    }

    private static void registerBatteryRecipe(Tier tier) {
        if (!tier.getItems().contains(BATTERY))
            return;

        RecipeManager.addShapedRecipe(BATTERY.getName(tier), BATTERY.asItemStack(tier),
                "C ", "FW", "F ",
                'C', WIRE.asIngredient(tier.getEnergy()),
                'F', PLATE.asIngredient(PLASTIC),
                'W', WRENCH.asIngredient(null)
        );
    }

    private static void registerRobotArmRecipe(Tier tier, String circuit) {
        if (!tier.getItems().contains(ROBOT_ARM))
            return;

        RecipeManager.addShapedRecipe(ROBOT_ARM.getName(tier), ROBOT_ARM.asItemStack(tier),
                "GWG", "RMR", "PSC",
                'G', GEAR.asIngredient(tier.getMechanical()),
                'M', MOTOR.asItemStack(tier),
                'R', ROD.asIngredient(tier.getMechanical()),
                'S', OreStack.getIngredient(circuit),
                'P', PLATE.asIngredient(tier.getEnergy()),
                'W', WRENCH.asIngredient(null),
                'C', WIRE.asIngredient(tier.getEnergy())
        );
    }

    private static void registerCircuitAssemblerRecipe(Tier tier, String circuit) {
        if (!tier.getMachines().contains(CIRCUIT_ASSEMBLER))
            return;

        RecipeManager.addShapedRecipe(CIRCUIT_ASSEMBLER.getName(tier), CIRCUIT_ASSEMBLER.asItemStack(tier),
                "GEG", "SFS", "ACA",
                'S', OreStack.getIngredient(circuit),
                'F', MACHINE_FRAME.asIngredient(tier),
                'E', EMITTER.asIngredient(tier),
                'A', ROBOT_ARM.asIngredient(tier),
                'C', SENSOR.asIngredient(tier),
                'G', GEAR.asIngredient(tier.getMechanical())
        );
    }

    private static void registerMixerRecipe(Tier tier, String circuit) {
        if (!tier.getMachines().contains(MIXER))
            return;

        RecipeManager.addShapedRecipe(MIXER.getName(tier), MIXER.asItemStack(tier),
                "RCW", "MHM", "WCR",
                'H', MACHINE_FRAME.asIngredient(tier),
                'C', OreStack.getIngredient(circuit),
                'M', MOTOR.asIngredient(tier),
                'R', ROTOR.getOre(tier.getMechanical()),
                'W', WIRE.getOre(tier.getEnergy())
        );
    }

    private static void registerFoundryRecipe(Tier tier, String circuit) {
        if (!tier.getMachines().contains(FOUNDRY))
            return;

        RecipeManager.addShapedRecipe(FOUNDRY.getName(tier), FOUNDRY.asItemStack(tier),
                "CWC", "WHW", "SWS",
                'H', MACHINE_FRAME.asIngredient(tier),
                'C', OreStack.getIngredient(circuit),
                'W', PLATE.getOre(tier.getHeat()),
                'S', HEAT_EXCHANGER.asIngredient(tier)
        );
    }

    private static void registerCompressorRecipe(Tier tier, String circuit) {
        if (!tier.getMachines().contains(COMPRESSOR))
            return;

        RecipeManager.addShapedRecipe(COMPRESSOR.getName(tier), COMPRESSOR.asItemStack(tier),
                "CPC", "AHA", "WPW",
                'H', MACHINE_FRAME.asIngredient(tier),
                'C', OreStack.getIngredient(circuit),
                'P', PISTON.asIngredient(tier),
                'A', ROBOT_ARM.asIngredient(tier),
                'W', WIRE.getOre(tier.getEnergy())
        );
    }

    private static void registerElectrolyzerRecipe(Tier tier, String circuit) {
        if (!tier.getMachines().contains(ELECTROLYZER))
            return;

        RecipeManager.addShapedRecipe(ELECTROLYZER.getName(tier), ELECTROLYZER.asItemStack(tier),
                "WGW", "WHW", "CSC",
                'H', MACHINE_FRAME.asIngredient(tier),
                'C', OreStack.getIngredient(circuit),
                'S', EMITTER.asIngredient(tier),
                'G', OreStack.getIngredient("blockGlass"),
                'W', WIRE.getOre(tier.getEnergy())
        );
    }

    private static void registerMaceratorRecipe(Tier tier, String circuit) {
        if (!tier.getMachines().contains(MACERATOR))
            return;

        RecipeManager.addShapedRecipe(MACERATOR.getName(tier), MACERATOR.asItemStack(tier),
                "MGM", "WHW", "CWC",
                'H', MACHINE_FRAME.asIngredient(tier),
                'C', OreStack.getIngredient(circuit),
                'M', MOTOR.asIngredient(tier),
                'G', GRINDER.asIngredient(tier),
                'W', WIRE.getOre(tier.getEnergy())
        );
    }

    private static void registerBenderRecipe(Tier tier, String circuit) {
        if (!tier.getMachines().contains(BENDER))
            return;

        RecipeManager.addShapedRecipe(BENDER.getName(tier), BENDER.asItemStack(tier),
                "CPP", "WHW", "PPC",
                'H', MACHINE_FRAME.asIngredient(tier),
                'C', OreStack.getIngredient(circuit),
                'P', PISTON.asIngredient(tier),
                'W', WIRE.asIngredient(tier.getEnergy())
        );
    }

    private static void registerCutterRecipe(Tier tier, String circuit) {
        if (!tier.getMachines().contains(Machines.M_CUTTER))
            return;

        RecipeManager.addShapedRecipe(Machines.M_CUTTER.getName(tier), Machines.M_CUTTER.asItemStack(tier),
                "MBM", "WHW", "CWC",
                'H', MACHINE_FRAME.asIngredient(tier),
                'C', OreStack.getIngredient(circuit),
                'M', MOTOR.asIngredient(tier),
                'B', BUZZSAW.asIngredient(tier),
                'W', WIRE.asIngredient(tier.getEnergy())
        );
    }

    private static void registerLatheRecipe(Tier tier, String circuit) {
        if (!tier.getMachines().contains(LATHE))
            return;

        RecipeManager.addShapedRecipe(LATHE.getName(tier), LATHE.asItemStack(tier),
                "WRM", "CHC", "WBM",
                'B', BUZZSAW.asIngredient(tier),
                'R', ROD.asIngredient(DIAMOND),
                'M', MOTOR.asIngredient(tier),
                'H', MACHINE_FRAME.asIngredient(tier),
                'C', OreStack.getIngredient(circuit),
                'W', WIRE.asIngredient(tier.getEnergy())
        );
    }
}
