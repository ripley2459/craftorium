package fr.cyrilneveu.craftorium.common.recipe;

import fr.cyrilneveu.craftorium.api.inventory.OreStack;
import fr.cyrilneveu.craftorium.api.recipe.RecipeManager;
import fr.cyrilneveu.craftorium.api.substance.Substance;
import fr.cyrilneveu.craftorium.api.tier.Tier;
import fr.cyrilneveu.craftorium.common.ACommonProxy;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;

import static fr.cyrilneveu.craftorium.common.substance.Substances.*;
import static fr.cyrilneveu.craftorium.common.substance.SubstancesObjects.*;
import static fr.cyrilneveu.craftorium.common.tier.Tiers.*;
import static fr.cyrilneveu.craftorium.common.tier.TiersObjects.*;

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
