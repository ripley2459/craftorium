package fr.cyrilneveu.craftorium.common.recipe;

import fr.cyrilneveu.craftorium.api.inventory.OreStack;
import fr.cyrilneveu.craftorium.api.recipe.machine.MachineRecipeBuilder;
import fr.cyrilneveu.craftorium.api.recipe.vanilla.RecipeManager;
import fr.cyrilneveu.craftorium.api.substance.Substance;
import fr.cyrilneveu.craftorium.api.substance.SubstanceStack;
import fr.cyrilneveu.craftorium.api.substance.property.Composition;
import fr.cyrilneveu.craftorium.api.substance.property.SubstanceProperties;
import fr.cyrilneveu.craftorium.api.tier.Tier;
import fr.cyrilneveu.craftorium.api.utils.Utils;
import fr.cyrilneveu.craftorium.common.ACommonProxy;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.oredict.OreDictionary;

import static fr.cyrilneveu.craftorium.api.Registries.SUBSTANCES_REGISTRY;
import static fr.cyrilneveu.craftorium.common.recipe.Maps.*;
import static fr.cyrilneveu.craftorium.common.substance.Substances.*;
import static fr.cyrilneveu.craftorium.common.substance.SubstancesObjects.*;
import static fr.cyrilneveu.craftorium.common.tier.Tiers.*;
import static fr.cyrilneveu.craftorium.common.tier.TiersObjects.*;

public final class RecipesHandler {
    public static void registerRecipes(RegistryEvent.Register<IRecipe> event) {

        ELECTROLYZING.addRecipe(new MachineRecipeBuilder("test_recipe")
                .consumeEnergy(5000)
                .consumeItem(DUST.getOre(REDSTONE), 3)
                .consumeFluid("water", 1000)
                .produceItem(WIRE.asItemStack(REDSTONE_ALLOY, 3))
                .produceFluid("lava", 1000)
                .configuration(1)
                .build());

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

        if (ONE.getPack() != null) {
            registerMotorRecipe(ONE);
            registerPistonRecipe(ONE);
            registerHeatExchangerRecipe(ONE, RUBBER);
            registerPumpRecipe(ONE, RUBBER);
            registerEmitterRecipe(ONE, DIAMOND, "circuitTier1");
            registerSensorRecipe(ONE, ENDER, "circuitTier1");
            registerBatteryRecipe(ONE);
            registerRobotArmRecipe(ONE, "circuitTier1");
        }

        if (TWO.getPack() != null) {
            registerMotorRecipe(TWO);
            registerPistonRecipe(TWO);
            registerHeatExchangerRecipe(TWO, RUBBER);
            registerPumpRecipe(TWO, RUBBER);
            registerEmitterRecipe(TWO, DIAMOND, "circuitTier2");
            registerSensorRecipe(TWO, ENDER, "circuitTier2");
            registerBatteryRecipe(TWO);
            registerRobotArmRecipe(TWO, "circuitTier2");
        }

        if (THREE.getPack() != null) {
            registerMotorRecipe(THREE);
            registerPistonRecipe(THREE);
            registerHeatExchangerRecipe(THREE, RUBBER);
            registerPumpRecipe(THREE, RUBBER);
            registerEmitterRecipe(THREE, DIAMOND, "circuitTier3");
            registerSensorRecipe(THREE, ENDER, "circuitTier3");
            registerBatteryRecipe(THREE);
            registerRobotArmRecipe(THREE, "circuitTier3");
        }

        if (FOUR.getPack() != null) {
            registerMotorRecipe(FOUR);
            registerPistonRecipe(FOUR);
            registerHeatExchangerRecipe(FOUR, RUBBER);
            registerPumpRecipe(FOUR, RUBBER);
            registerEmitterRecipe(FOUR, DIAMOND, "circuitTier4");
            registerSensorRecipe(FOUR, ENDER, "circuitTier4");
            registerBatteryRecipe(FOUR);
            registerRobotArmRecipe(FOUR, "circuitTier4");
        }

        if (FIVE.getPack() != null) {
            registerMotorRecipe(FIVE);
            registerPistonRecipe(FIVE);
            registerHeatExchangerRecipe(FIVE, RUBBER);
            registerPumpRecipe(FIVE, RUBBER);
            registerEmitterRecipe(FIVE, DIAMOND, "circuitTier5");
            registerSensorRecipe(FIVE, ENDER, "circuitTier5");
            registerBatteryRecipe(FIVE);
            registerRobotArmRecipe(FIVE, "circuitTier5");
        }

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
                'C', WIRE.asIngredient(tier.getPack().getEnergy()),
                'F', FOIL.asIngredient(tier.getPack().getMechanical()),
                'R', ROD.asIngredient(tier.getPack().getMechanical())
        );
    }

    private static void registerPistonRecipe(Tier tier) {
        if (!tier.getItems().contains(PISTON))
            return;

        RecipeManager.addShapedRecipe(PISTON.getName(tier), PISTON.asItemStack(tier),
                "PW", "RG", "MC",
                'P', PLATE.asIngredient(tier.getPack().getMechanical()),
                'W', WRENCH.asIngredient(null),
                'R', ROD.asIngredient(tier.getPack().getMechanical()),
                'G', GEAR.asIngredient(tier.getPack().getMechanical()),
                'M', MOTOR.asItemStack(tier),
                'C', WIRE.asIngredient(tier.getPack().getEnergy())
        );
    }

    private static void registerHeatExchangerRecipe(Tier tier, Substance fluidIsolator) {
        if (!tier.getItems().contains(HEAT_EXCHANGER))
            return;

        RecipeManager.addShapedRecipe(HEAT_EXCHANGER.getName(tier), HEAT_EXCHANGER.asItemStack(tier),
                "FN", "RS", "VC",
                'F', FOIL.asIngredient(tier.getPack().getFluid()),
                'N', SCREWDRIVER.asIngredient(null),
                'R', RING.asIngredient(fluidIsolator),
                'S', SCREW.asIngredient(tier.getPack().getMechanical()),
                'V', FOIL.asIngredient(tier.getPack().getHeat()),
                'C', WRENCH.asIngredient(null)
        );
    }

    private static void registerPumpRecipe(Tier tier, Substance fluidIsolator) {
        if (!tier.getItems().contains(PUMP))
            return;

        RecipeManager.addShapedRecipe(PUMP.getName(tier), PUMP.asItemStack(tier),
                "SMP", "WFL", "PKC",
                'S', SCREW.asIngredient(tier.getPack().getMechanical()),
                'M', MOTOR.asItemStack(tier),
                'P', RING.asIngredient(fluidIsolator),
                'W', WRENCH.asIngredient(null),
                'F', FOIL.asIngredient(tier.getPack().getFluid()),
                'L', SCREWDRIVER.asIngredient(null),
                'K', FOIL.asIngredient(tier.getPack().getCarcass()),
                'C', WIRE.asIngredient(tier.getPack().getEnergy())
        );
    }

    private static void registerEmitterRecipe(Tier tier, Substance emitter, String circuit) {
        if (!tier.getItems().contains(EMITTER))
            return;

        RecipeManager.addShapedRecipe(EMITTER.getName(tier), EMITTER.asItemStack(tier),
                "NSD", "PRW", "JPC",
                'N', SCREWDRIVER.asIngredient(null),
                'S', SCREW.asIngredient(tier.getPack().getMechanical()),
                'D', GEM.asIngredient(emitter),
                'P', PLATE.asIngredient(tier.getPack().getCarcass()),
                'R', ROD.asIngredient(tier.getPack().getMechanical()),
                'W', WIRE.asIngredient(tier.getPack().getEnergy()),
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
                'F', FOIL.asIngredient(tier.getPack().getCarcass()),
                'W', WIRE.asIngredient(tier.getPack().getEnergy()),
                'K', OreStack.getIngredient(circuit),
                'D', PEARL.asIngredient(sensor),
                'S', SCREW.asIngredient(tier.getPack().getMechanical()),
                'N', SCREWDRIVER.asIngredient(null)
        );
    }

    private static void registerBatteryRecipe(Tier tier) {
        if (!tier.getItems().contains(BATTERY))
            return;

        RecipeManager.addShapedRecipe(BATTERY.getName(tier), BATTERY.asItemStack(tier),
                "C ", "FW", "F ",
                'C', WIRE.asIngredient(tier.getPack().getEnergy()),
                'F', PLATE.asIngredient(PLASTIC),
                'W', WRENCH.asIngredient(null)
        );
    }

    private static void registerRobotArmRecipe(Tier tier, String circuit) {
        if (!tier.getItems().contains(ROBOT_ARM))
            return;

        RecipeManager.addShapedRecipe(ROBOT_ARM.getName(tier), ROBOT_ARM.asItemStack(tier),
                "GWG", "RMR", "PSC",
                'G', GEAR.asIngredient(tier.getPack().getMechanical()),
                'M', MOTOR.asItemStack(tier),
                'R', ROD.asIngredient(tier.getPack().getMechanical()),
                'S', OreStack.getIngredient(circuit),
                'P', PLATE.asIngredient(tier.getPack().getEnergy()),
                'W', WRENCH.asIngredient(null),
                'C', WIRE.asIngredient(tier.getPack().getEnergy())
        );
    }
}
