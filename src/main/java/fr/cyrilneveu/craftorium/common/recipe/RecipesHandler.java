package fr.cyrilneveu.craftorium.common.recipe;

import fr.cyrilneveu.craftorium.api.inventory.OreStack;
import fr.cyrilneveu.craftorium.api.recipe.RecipeManager;
import fr.cyrilneveu.craftorium.api.substance.Substance;
import fr.cyrilneveu.craftorium.api.tier.Tier;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;

import static fr.cyrilneveu.craftorium.common.substance.Substances.*;
import static fr.cyrilneveu.craftorium.common.substance.SubstancesObjects.*;
import static fr.cyrilneveu.craftorium.common.tier.Tiers.*;
import static fr.cyrilneveu.craftorium.common.tier.TiersObjects.*;

public final class RecipesHandler {
    public static void registerRecipes(RegistryEvent.Register<IRecipe> event) {

        if (ONE.getPack() != null) {
            registerMotorRecipe(ONE);
            registerPistonRecipe(ONE);
            registerHeatExchangerRecipe(ONE, RUBBER);
            registerPumpRecipe(ONE, RUBBER);
            registerEmitterRecipe(ONE, DIAMOND, "circuitTier1");
            registerSensorRecipe(ONE, ENDER, "circuitTier1");
            registerBatteryRecipe(ONE);
            registerLargeBatteryRecipe(ONE);
            registerRobotArmRecipe(ONE, "circuitTier1");
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

    private static void registerLargeBatteryRecipe(Tier tier) {
        if (!tier.getItems().contains(LARGE_BATTERY))
            return;

        RecipeManager.addShapedRecipe(LARGE_BATTERY.getName(tier), LARGE_BATTERY.asItemStack(tier),
                "CC ", "FCW", "FF ",
                'C', WIRE.asIngredient(tier.getPack().getEnergy()),
                'F', PLATE.asIngredient(PLASTIC),
                'W', WRENCH.asIngredient(null)
        );
    }

    private static void registerRobotArmRecipe(Tier tier, String circuit) {
        if (!tier.getItems().contains(ROBOT_ARM))
            return;

        RecipeManager.addShapedRecipe(ROBOT_ARM.getName(tier), ROBOT_ARM.asItemStack(tier),
                "CWC", "GMG", "PSR",
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
