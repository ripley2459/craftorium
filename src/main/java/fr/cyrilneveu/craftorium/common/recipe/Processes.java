package fr.cyrilneveu.craftorium.common.recipe;

import fr.cyrilneveu.craftorium.api.inventory.OreStack;
import fr.cyrilneveu.craftorium.api.recipe.machine.MachineRecipeBuilder;
import fr.cyrilneveu.craftorium.api.recipe.process.AProcess;
import fr.cyrilneveu.craftorium.api.recipe.vanilla.RecipeManager;
import fr.cyrilneveu.craftorium.api.substance.Substance;
import fr.cyrilneveu.craftorium.api.substance.SubstanceStack;
import fr.cyrilneveu.craftorium.api.substance.object.ASubstanceObject;
import fr.cyrilneveu.craftorium.api.substance.property.SubstanceProperties;
import fr.cyrilneveu.craftorium.api.utils.Registry;
import fr.cyrilneveu.craftorium.api.utils.Utils;
import net.minecraft.init.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraftforge.fluids.FluidRegistry;

import java.util.Set;

import static fr.cyrilneveu.craftorium.api.Registries.SUBSTANCE_BLOCKS_REGISTRY;
import static fr.cyrilneveu.craftorium.api.Registries.SUBSTANCE_ITEMS_REGISTRY;
import static fr.cyrilneveu.craftorium.common.recipe.Maps.*;
import static fr.cyrilneveu.craftorium.common.substance.SubstancesObjects.*;

public final class Processes {
    public static final Registry<String, AProcess> PROCESSES_REGISTRY = new Registry<>();

    public static final DefaultProcess DEFAULT_PROCESS = new DefaultProcess("default_process");

    public static class DefaultProcess extends AProcess {
        public DefaultProcess(String name) {
            super(name);

            PROCESSES_REGISTRY.put(name, this);
        }

        @Override
        protected void addRecipes() {
            recipes.add(this::casing);
            recipes.add(this::dust);
            recipes.add(this::foil);
            recipes.add(this::gear);
            recipes.add(this::gem);
            recipes.add(this::ingot);
            recipes.add(this::nugget);
            recipes.add(this::pearl);
            recipes.add(this::plate);
            recipes.add(this::ring);
            recipes.add(this::rod);
            recipes.add(this::rotor);
            recipes.add(this::screw);
            recipes.add(this::spring);
            recipes.add(this::wire);

            recipes.add(this::axe);
            recipes.add(this::cutter);
            recipes.add(this::file);
            recipes.add(this::hammer);
            recipes.add(this::hoe);
            recipes.add(this::knife);
            recipes.add(this::mortar);
            recipes.add(this::pickaxe);
            recipes.add(this::saw);
            recipes.add(this::screwdriver);
            recipes.add(this::shovel);
            recipes.add(this::sword);
            recipes.add(this::wrench);

            recipes.add(this::block);
            recipes.add(this::frame);
            recipes.add(this::hull);

            recipes.add(this::liquid);
        }

        private void casing(Substance substance) {
            if (!substance.getItems().contains(CASING))
                return;

            if (OreStack.oresExist(WRENCH.getOre(substance), PLATE.getOre(substance)))
                RecipeManager.addShapedRecipe(CASING.getName(substance), CASING.asItemStack(substance), "WP", 'W', WRENCH.asIngredient(substance), 'P', PLATE.asIngredient(substance));

            if (OreStack.oresExist(PLATE.getOre(substance)))
                BENDING.addRecipe(new MachineRecipeBuilder(CASING.getName(substance).concat(PLATE.getName(substance)))
                        .consumeItem(PLATE.getOre(substance), 1)
                        .produceItem(CASING.asItemStack(substance))
                        .consumeEnergy(5000)
                        .duration(200)
                        .configuration(CONFIGURATION_BENDER_REFORM)
                        .build());
        }

        private void dust(Substance substance) {
            if (!substance.getItems().contains(DUST))
                return;

            if (OreStack.oresExist(MORTAR.getOre(substance), INGOT.getOre(substance)))
                RecipeManager.addShapelessRecipe(DUST.getName(substance).concat(INGOT.getName(substance)), DUST.asItemStack(substance), MORTAR.asIngredient(substance), INGOT.asIngredient(substance));
            if (OreStack.oresExist(MORTAR.getOre(substance), GEM.getOre(substance)))
                RecipeManager.addShapelessRecipe(DUST.getName(substance).concat(GEM.getName(substance)), DUST.asItemStack(substance), MORTAR.asIngredient(substance), GEM.asIngredient(substance));
            if (OreStack.oresExist(MORTAR.getOre(substance), PEARL.getOre(substance)))
                RecipeManager.addShapelessRecipe(DUST.getName(substance).concat(PEARL.getName(substance)), DUST.asItemStack(substance), MORTAR.asIngredient(substance), PEARL.asIngredient(substance));
            if (OreStack.oresExist(MORTAR.getOre(substance), ORE.getOre(substance)))
                RecipeManager.addShapelessRecipe(DUST.getName(substance).concat(ORE.getName(substance)), DUST.asItemStack(substance, 2), MORTAR.asIngredient(substance), ORE.asIngredient(substance));

            if (OreStack.oresExist(INGOT.getOre(substance)))
                MACERATING.addRecipe(new MachineRecipeBuilder(DUST.getName(substance).concat(INGOT.getName(substance)))
                        .consumeItem(INGOT.getOre(substance), 1)
                        .produceItem(DUST.asItemStack(substance))
                        .consumeEnergy(5000)
                        .duration(200)
                        .configuration(CONFIGURATION_MACERATOR_PULVERIZING)
                        .build());
            if (OreStack.oresExist(GEM.getOre(substance)))
                MACERATING.addRecipe(new MachineRecipeBuilder(DUST.getName(substance).concat(GEM.getName(substance)))
                        .consumeItem(GEM.getOre(substance), 1)
                        .produceItem(DUST.asItemStack(substance))
                        .consumeEnergy(5000)
                        .duration(200)
                        .configuration(CONFIGURATION_MACERATOR_PULVERIZING)
                        .build());
            if (OreStack.oresExist(PEARL.getOre(substance)))
                MACERATING.addRecipe(new MachineRecipeBuilder(DUST.getName(substance).concat(PEARL.getName(substance)))
                        .consumeItem(PEARL.getOre(substance), 1)
                        .produceItem(DUST.asItemStack(substance))
                        .consumeEnergy(5000)
                        .duration(200)
                        .configuration(CONFIGURATION_MACERATOR_PULVERIZING)
                        .build());
            if (OreStack.oresExist(ORE.getOre(substance)))
                MACERATING.addRecipe(new MachineRecipeBuilder(DUST.getName(substance).concat(ORE.getName(substance)))
                        .consumeItem(ORE.getOre(substance), 1)
                        .produceItem(DUST.asItemStack(substance, 2))
                        .consumeEnergy(10000)
                        .duration(300)
                        .configuration(CONFIGURATION_MACERATOR_PULVERIZING)
                        .build());

            if (substance.getComposition().isNative())
                return;

            Set<SubstanceStack> composition = substance.getComposition().getComposition();
            if (composition.size() < 2)
                return;

            int amount = 0;
            for (SubstanceStack stack : composition)
                amount += stack.getAmount();

            boolean flag = Utils.all(composition, s -> OreStack.oresExist(DUST.getOre(s.getSubstance())));

            blend:
            {
                if (!flag || amount <= 0 || amount > 9)
                    break blend;

                Ingredient[] dusts = new Ingredient[amount];

                int i = 0;
                for (SubstanceStack stack : composition) {
                    for (int j = 0; j < stack.getAmount(); j++)
                        dusts[i++] = DUST.asIngredient(stack.getSubstance());
                }

                RecipeManager.addShapelessRecipe(DUST.getName(substance).concat("blend"), DUST.asItemStack(substance, amount), dusts);
            }

            mixing:
            {
                if (!flag || substance.getProperties().containsKey(SubstanceProperties.KeyProperties.VEIN_MEMBER) || composition.size() > MIXING.getItemsIn())
                    break mixing;

                MachineRecipeBuilder recipeMix = new MachineRecipeBuilder("mixing_" + DUST.getName(substance));

                for (SubstanceStack stack : composition)
                    recipeMix.consumeItem(DUST.getOre(stack.getSubstance()), stack.getAmount());

                MIXING.addRecipe(recipeMix.produceItem(DUST.asItemStack(substance, amount)).duration(100).consumeEnergy(20000).configuration(CONFIGURATION_MIXING_MIX).build());
            }
        }

        private void foil(Substance substance) {
            if (!substance.getItems().contains(FOIL))
                return;

            if (OreStack.oresExist(FILE.getOre(substance), PLATE.getOre(substance)))
                RecipeManager.addShapedRecipe(FOIL.getName(substance), FOIL.asItemStack(substance, 2), "F", "P", 'F', FILE.asIngredient(substance), 'P', PLATE.asIngredient(substance));

            if (OreStack.oresExist(PLATE.getOre(substance)))
                CUTTING.addRecipe(new MachineRecipeBuilder(FOIL.getName(substance).concat(PLATE.getName(substance)))
                        .consumeItem(PLATE.getOre(substance), 1)
                        .produceItem(FOIL.asItemStack(substance, 2))
                        .consumeEnergy(5000).duration(200)
                        .configuration(CONFIGURATION_CUTTER_NORMAL)
                        .build());

            CASTING.addRecipe(new MachineRecipeBuilder(FOIL.getName(substance))
                    .consumeFluid(LIQUID.getName(substance), FOIL.getAmount())
                    .produceItem(FOIL.asItemStack(substance))
                    .consumeEnergy(500)
                    .duration(500)
                    .configuration(CONFIGURATION_CASTING_FORM_FOIL)
                    .build());
        }

        private void gear(Substance substance) {
            if (!substance.getItems().contains(GEAR))
                return;

            if (OreStack.oresExist(KNIFE.getOre(substance), PLATE.getOre(substance), FILE.getOre(substance)))
                RecipeManager.addShapedRecipe(GEAR.getName(substance), GEAR.asItemStack(substance), "C", "P", "F", 'C', KNIFE.asIngredient(substance), 'P', PLATE.asIngredient(substance), 'F', FILE.asIngredient(substance));

            if (OreStack.oresExist(PLATE.getOre(substance)))
                CUTTING.addRecipe(new MachineRecipeBuilder(GEAR.getName(substance).concat(PLATE.getName(substance)))
                        .consumeItem(PLATE.getOre(substance), 1)
                        .produceItem(GEAR.asItemStack(substance, 1))
                        .consumeEnergy(9000)
                        .duration(300)
                        .configuration(CONFIGURATION_CUTTER_LARGE)
                        .build());

            CASTING.addRecipe(new MachineRecipeBuilder(GEAR.getName(substance))
                    .consumeFluid(LIQUID.getName(substance), GEAR.getAmount())
                    .produceItem(GEAR.asItemStack(substance))
                    .consumeEnergy(500)
                    .duration(500)
                    .configuration(CONFIGURATION_CASTING_FORM_GEAR)
                    .build());
        }

        private void gem(Substance substance) {
            if (!substance.getItems().contains(GEM))
                return;

            if (OreStack.oresExist(ORE.getOre(substance)))
                RecipeManager.addFurnaceRecipe(new OreStack(ORE.getOre(substance)), GEM.asItemStack(substance), 0.7f);
            if (OreStack.oresExist(NUGGET.getOre(substance)))
                RecipeManager.zip(substance, NUGGET, GEM);
            if (OreStack.oresExist(BLOCK.getOre(substance)))
                RecipeManager.unzip(substance, BLOCK, GEM);

            CASTING.addRecipe(new MachineRecipeBuilder(GEM.getName(substance))
                    .consumeFluid(LIQUID.getName(substance), GEM.getAmount())
                    .produceItem(GEM.asItemStack(substance))
                    .consumeEnergy(500)
                    .duration(500)
                    .configuration(CONFIGURATION_CASTING_FORM_INGOT)
                    .build());
        }

        private void ingot(Substance substance) {
            if (!substance.getItems().contains(INGOT))
                return;

            if (OreStack.oresExist(ORE.getOre(substance)))
                RecipeManager.addFurnaceRecipe(new OreStack(ORE.getOre(substance)), INGOT.asItemStack(substance), 0.7f);
            if (OreStack.oresExist(DUST.getOre(substance)))
                RecipeManager.addFurnaceRecipe(new OreStack(DUST.getOre(substance)), INGOT.asItemStack(substance));
            if (OreStack.oresExist(NUGGET.getOre(substance)))
                RecipeManager.zip(substance, NUGGET, INGOT);
            if (OreStack.oresExist(BLOCK.getOre(substance)))
                RecipeManager.unzip(substance, BLOCK, INGOT);

            CASTING.addRecipe(new MachineRecipeBuilder(INGOT.getName(substance))
                    .consumeFluid(LIQUID.getName(substance), INGOT.getAmount())
                    .produceItem(INGOT.asItemStack(substance))
                    .consumeEnergy(500)
                    .duration(500)
                    .configuration(CONFIGURATION_CASTING_FORM_INGOT)
                    .build());
        }

        private void nugget(Substance substance) {
            if (!substance.getItems().contains(NUGGET))
                return;

            if (OreStack.oresExist(INGOT.getOre(substance)))
                RecipeManager.unzip(substance, INGOT, NUGGET);
            if (OreStack.oresExist(GEM.getOre(substance)))
                RecipeManager.unzip(substance, GEM, NUGGET);
            if (OreStack.oresExist(PEARL.getOre(substance)))
                RecipeManager.unzip(substance, PEARL, NUGGET);

            CASTING.addRecipe(new MachineRecipeBuilder(NUGGET.getName(substance))
                    .consumeFluid(LIQUID.getName(substance), NUGGET.getAmount())
                    .produceItem(NUGGET.asItemStack(substance))
                    .consumeEnergy(500)
                    .duration(500)
                    .configuration(CONFIGURATION_CASTING_FORM_NUGGET)
                    .build());
        }

        private void pearl(Substance substance) {
            if (!substance.getItems().contains(PEARL))
                return;

            if (OreStack.oresExist(NUGGET.getOre(substance)))
                RecipeManager.zip(substance, NUGGET, PEARL);
            if (OreStack.oresExist(BLOCK.getOre(substance)))
                RecipeManager.unzip(substance, BLOCK, PEARL);

            CASTING.addRecipe(new MachineRecipeBuilder(PEARL.getName(substance))
                    .consumeFluid(LIQUID.getName(substance), PEARL.getAmount())
                    .produceItem(PEARL.asItemStack(substance))
                    .consumeEnergy(500)
                    .duration(500)
                    .configuration(CONFIGURATION_CASTING_FORM_PEARL)
                    .build());
        }

        private void plate(Substance substance) {
            if (!substance.getItems().contains(PLATE))
                return;

            if (OreStack.oresExist(HAMMER.getOre(substance), INGOT.getOre(substance)))
                RecipeManager.addShapedRecipe(PLATE.getName(substance), PLATE.asItemStack(substance), "H", "I", "I", 'H', HAMMER.asIngredient(substance), 'I', INGOT.asIngredient(substance));

            if (OreStack.oresExist(INGOT.getOre(substance)))
                COMPRESSING.addRecipe(new MachineRecipeBuilder(PLATE.getName(substance).concat(INGOT.getName(substance)))
                        .consumeItem(INGOT.getOre(substance), 1)
                        .produceItem(PLATE.asItemStack(substance, 1))
                        .consumeEnergy(5000)
                        .duration(100)
                        .configuration(CONFIGURATION_COMPRESSOR_PRESSING)
                        .build());

            if (OreStack.oresExist(BLOCK.getOre(substance)))
                CUTTING.addRecipe(new MachineRecipeBuilder(PLATE.getName(substance).concat(BLOCK.getName(substance)))
                        .consumeItem(BLOCK.getOre(substance), 1)
                        .produceItem(PLATE.asItemStack(substance, 9))
                        .consumeEnergy(45000)
                        .duration(1800)
                        .configuration(CONFIGURATION_CUTTER_LARGE)
                        .build());

            CASTING.addRecipe(new MachineRecipeBuilder(PLATE.getName(substance))
                    .consumeFluid(LIQUID.getName(substance), PLATE.getAmount())
                    .produceItem(PLATE.asItemStack(substance))
                    .consumeEnergy(500)
                    .duration(500)
                    .configuration(CONFIGURATION_CASTING_FORM_PLATE)
                    .build());
        }

        private void ring(Substance substance) {
            if (!substance.getItems().contains(RING))
                return;

            if (OreStack.oresExist(CUTTER.getOre(substance), ROD.getOre(substance), WRENCH.getOre(substance)))
                RecipeManager.addShapedRecipe(RING.getName(substance), RING.asItemStack(substance), "CRW", 'C', CUTTER.asIngredient(substance), 'R', ROD.asIngredient(substance), 'W', WRENCH.asIngredient(substance));

            if (OreStack.oresExist(ROD.getOre(substance)))
                BENDING.addRecipe(new MachineRecipeBuilder(RING.getName(substance).concat(ROD.getName(substance)))
                        .consumeItem(ROD.getOre(substance), 1)
                        .produceItem(RING.asItemStack(substance))
                        .consumeEnergy(5000)
                        .duration(200)
                        .configuration(CONFIGURATION_BENDER_REFORM)
                        .build());

            CASTING.addRecipe(new MachineRecipeBuilder(RING.getName(substance))
                    .consumeFluid(LIQUID.getName(substance), RING.getAmount())
                    .produceItem(RING.asItemStack(substance))
                    .consumeEnergy(500)
                    .duration(500)
                    .configuration(CONFIGURATION_CASTING_FORM_RING)
                    .build());
        }

        private void rod(Substance substance) {
            if (!substance.getItems().contains(ROD))
                return;

            if (OreStack.oresExist(FILE.getOre(substance), INGOT.getOre(substance)))
                RecipeManager.addShapelessRecipe(ROD.getName(substance), ROD.asItemStack(substance, 2), FILE.asIngredient(substance), INGOT.asIngredient(substance));

            if (OreStack.oresExist(INGOT.getOre(substance)))
                SPINNING.addRecipe(new MachineRecipeBuilder(ROD.getName(substance).concat(INGOT.getName(substance)))
                        .consumeItem(INGOT.getOre(substance), 1)
                        .produceItem(ROD.asItemStack(substance, 4))
                        .consumeEnergy(5000)
                        .duration(200)
                        .configuration(CONFIGURATION_LATHE_SIMPLE)
                        .build());

            CASTING.addRecipe(new MachineRecipeBuilder(ROD.getName(substance))
                    .consumeFluid(LIQUID.getName(substance), ROD.getAmount())
                    .produceItem(ROD.asItemStack(substance))
                    .consumeEnergy(500)
                    .duration(500)
                    .configuration(CONFIGURATION_CASTING_FORM_ROD)
                    .build());
        }

        private void rotor(Substance substance) {
            if (!substance.getItems().contains(ROTOR))
                return;

            if (OreStack.oresExist(CUTTER.getOre(substance), SCREWDRIVER.getOre(substance), PLATE.getOre(substance), SCREW.getOre(substance), RING.getOre(substance)))
                RecipeManager.addShapedRecipe(ROTOR.getName(substance), ROTOR.asItemStack(substance), "CP ", "RDR", " PS", 'C', CUTTER.asIngredient(substance), 'P', PLATE.asIngredient(substance), 'R', SCREW.asIngredient(substance), 'D', RING.asIngredient(substance), 'S', SCREWDRIVER.asIngredient(substance));
        }

        private void screw(Substance substance) {
            if (!substance.getItems().contains(SCREW))
                return;

            if (OreStack.oresExist(ROD.getOre(substance), SAW.getOre(substance), HAMMER.getOre(substance), FILE.getOre(substance)))
                RecipeManager.addShapedRecipe(SCREW.getName(substance), SCREW.asItemStack(substance, 2), " SH", "FR ", 'S', SAW.asIngredient(substance), 'F', FILE.asIngredient(substance), 'R', ROD.asIngredient(substance), 'H', HAMMER.asIngredient(substance));
            if (OreStack.oresExist(ROD.getOre(substance)))
                SPINNING.addRecipe(new MachineRecipeBuilder(SCREW.getName(substance).concat(ROD.getName(substance)))
                        .consumeItem(ROD.getOre(substance), 1)
                        .produceItem(SCREW.asItemStack(substance, 2))
                        .consumeEnergy(5000)
                        .duration(200)
                        .configuration(CONFIGURATION_LATHE_ADVANCED)
                        .build());
        }

        private void spring(Substance substance) {
            if (!substance.getItems().contains(SPRING))
                return;

            if (OreStack.oresExist(CUTTER.getOre(substance), WRENCH.getOre(substance), HAMMER.getOre(substance)))
                RecipeManager.addShapedRecipe(SPRING.getName(substance), SPRING.asItemStack(substance), "CRW", " H ", 'C', CUTTER.asIngredient(substance), 'R', ROD.asIngredient(substance), 'W', WRENCH.asIngredient(substance), 'H', HAMMER.asIngredient(substance));

            if (OreStack.oresExist(ROD.getOre(substance)))
                BENDING.addRecipe(new MachineRecipeBuilder(SPRING.getName(substance).concat(ROD.getName(substance)))
                        .consumeItem(ROD.getOre(substance), 1)
                        .produceItem(SPRING.asItemStack(substance))
                        .consumeEnergy(5000)
                        .duration(200)
                        .configuration(CONFIGURATION_BENDER_REFORM)
                        .build());
        }

        private void wire(Substance substance) {
            if (!substance.getItems().contains(WIRE))
                return;

            if (OreStack.oresExist(CUTTER.getOre(substance), FOIL.getOre(substance)))
                RecipeManager.addShapelessRecipe(WIRE.getName(substance), WIRE.asItemStack(substance, 4), CUTTER.asIngredient(substance), FOIL.asIngredient(substance));

            if (OreStack.oresExist(FOIL.getOre(substance)))
                CUTTING.addRecipe(new MachineRecipeBuilder(WIRE.getName(substance).concat(FOIL.getName(substance)))
                        .consumeItem(FOIL.getOre(substance), 1)
                        .produceItem(WIRE.asItemStack(substance, 4))
                        .consumeEnergy(5000)
                        .duration(200)
                        .configuration(CONFIGURATION_CUTTER_FINE)
                        .build());
        }

        private void axe(Substance substance) {
            if (!substance.getTools().contains(AXE))
                return;

            if (OreStack.oresExist(INGOT.getOre(substance), "stickWood"))
                RecipeManager.addShapedRecipe(AXE.getName(substance), AXE.asItemStack(substance), "II", "IS", " S", 'I', INGOT.asIngredient(substance), 'S', "stickWood");
        }

        private void cutter(Substance substance) {
            if (!substance.getTools().contains(CUTTER))
                return;

            if (OreStack.oresExist(HAMMER.getOre(substance), FILE.getOre(substance), SCREWDRIVER.getOre(substance), PLATE.getOre(substance), GEAR.getOre(substance), SCREW.getOre(substance), "stickWood"))
                RecipeManager.addShapedRecipe(CUTTER.getName(substance), CUTTER.asItemStack(substance), "PSP", "FGW", "RCR", 'P', PLATE.asIngredient(substance), 'S', SCREWDRIVER.asIngredient(substance), 'F', FILE.asIngredient(substance), 'G', GEAR.asIngredient(substance), 'W', WRENCH.asIngredient(substance), 'R', "stickWood", 'C', SCREW.asIngredient(substance));
        }

        private void file(Substance substance) {
            if (!substance.getTools().contains(FILE))
                return;

            if (substance.getTools().contains(FILE) && OreStack.oresExist(HAMMER.getOre(substance), FILE.getOre(substance), PLATE.getOre(substance), "stickWood"))
                RecipeManager.addShapedRecipe(FILE.getName(substance), FILE.asItemStack(substance), "PH", "S ", 'H', HAMMER.asIngredient(substance), 'P', PLATE.asIngredient(substance), 'S', "stickWood");
        }

        private void hammer(Substance substance) {
            if (!substance.getTools().contains(HAMMER))
                return;

            if (OreStack.oresExist(HAMMER.getOre(substance), FILE.getOre(substance), INGOT.getOre(substance), "stickWood"))
                RecipeManager.addShapedRecipe(HAMMER.getName(substance), HAMMER.asItemStack(substance), "IF", "S ", 'I', INGOT.asIngredient(substance), 'S', "stickWood", 'F', FILE.asIngredient(substance));
        }

        private void hoe(Substance substance) {
            if (!substance.getTools().contains(HOE))
                return;

            if (OreStack.oresExist(INGOT.getOre(substance), "stickWood"))
                RecipeManager.addShapedRecipe(HOE.getName(substance), HOE.asItemStack(substance), "II", " S", " S", 'I', INGOT.asIngredient(substance), 'S', "stickWood");
        }

        private void knife(Substance substance) {
            if (!substance.getTools().contains(KNIFE))
                return;

            if (OreStack.oresExist(CUTTER.getOre(substance), FILE.getOre(substance), PLATE.getOre(substance), "stickWood"))
                RecipeManager.addShapedRecipe(KNIFE.getName(substance), KNIFE.asItemStack(substance), "FPC", " R ", 'P', PLATE.asIngredient(substance), 'R', "stickWood", 'C', CUTTER.asIngredient(substance), 'F', FILE.asIngredient(substance));
        }

        private void mortar(Substance substance) {
            if (!substance.getTools().contains(MORTAR))
                return;

            if (OreStack.oresExist(FILE.getOre(substance), INGOT.getOre(substance)))
                RecipeManager.addShapedRecipe(MORTAR.getName(substance), MORTAR.asItemStack(substance), "IF", "B ", 'I', INGOT.getOre(substance), 'B', Ingredient.fromItem(Items.BOWL), 'F', FILE.getOre(substance));
        }

        private void pickaxe(Substance substance) {
            if (!substance.getTools().contains(PICKAXE))
                return;

            if (OreStack.oresExist(INGOT.getOre(substance), "stickWood"))
                RecipeManager.addShapedRecipe(PICKAXE.getName(substance), PICKAXE.asItemStack(substance), "III", " S ", " S ", 'I', INGOT.asIngredient(substance), 'S', "stickWood");
        }

        private void saw(Substance substance) {
            if (!substance.getTools().contains(SAW))
                return;

            if (OreStack.oresExist(HAMMER.getOre(substance), FILE.getOre(substance), PLATE.getOre(substance), "stickWood"))
                RecipeManager.addShapedRecipe(SAW.getName(substance), SAW.asItemStack(substance), "PS", "FH", 'H', HAMMER.asIngredient(substance), 'P', PLATE.asIngredient(substance), 'S', "stickWood", 'F', FILE.asIngredient(substance));
        }

        private void screwdriver(Substance substance) {
            if (!substance.getTools().contains(SCREWDRIVER))
                return;

            if (OreStack.oresExist(HAMMER.getOre(substance), FILE.getOre(substance), ROD.getOre(substance), "stickWood"))
                RecipeManager.addShapedRecipe(SCREWDRIVER.getName(substance), SCREWDRIVER.asItemStack(substance), "HR", "SF", 'H', HAMMER.asIngredient(substance), 'R', ROD.asIngredient(substance), 'S', "stickWood", 'F', FILE.asIngredient(substance));
        }

        private void shovel(Substance substance) {
            if (!substance.getTools().contains(SHOVEL))
                return;

            if (OreStack.oresExist(INGOT.getOre(substance), "stickWood"))
                RecipeManager.addShapedRecipe(SHOVEL.getName(substance), SHOVEL.asItemStack(substance), "I", "S", "S", 'I', INGOT.asIngredient(substance), 'S', "stickWood");
        }

        private void sword(Substance substance) {
            if (!substance.getTools().contains(SWORD))
                return;

            if (OreStack.oresExist(INGOT.getOre(substance), "stickWood"))
                RecipeManager.addShapedRecipe(SWORD.getName(substance), SWORD.asItemStack(substance), "I", "I", "S", 'I', INGOT.asIngredient(substance), 'S', "stickWood");
        }

        private void wrench(Substance substance) {
            if (!substance.getTools().contains(WRENCH))
                return;

            if (OreStack.oresExist(HAMMER.getOre(substance), FILE.getOre(substance), PLATE.getOre(substance), ROD.getOre(substance)))
                RecipeManager.addShapedRecipe(WRENCH.getName(substance), WRENCH.asItemStack(substance), "P P", "FPH", " R ", 'P', PLATE.asIngredient(substance), 'R', ROD.asIngredient(substance), 'H', HAMMER.asIngredient(substance), 'F', FILE.asIngredient(substance));
        }

        private void block(Substance substance) {
            if (!substance.getBlocks().contains(BLOCK))
                return;

            if (OreStack.oresExist(INGOT.getOre(substance)))
                RecipeManager.zip(substance, INGOT, BLOCK);
            if (OreStack.oresExist(GEM.getOre(substance)))
                RecipeManager.zip(substance, GEM, BLOCK);
            if (OreStack.oresExist(PEARL.getOre(substance)))
                RecipeManager.zip(substance, PEARL, BLOCK);

            CASTING.addRecipe(new MachineRecipeBuilder(BLOCK.getName(substance))
                    .consumeFluid(LIQUID.getName(substance), BLOCK.getAmount())
                    .produceItem(BLOCK.asItemStack(substance))
                    .consumeEnergy(500)
                    .duration(500)
                    .configuration(CONFIGURATION_CASTING_FORM_BLOCK)
                    .build());
        }

        private void frame(Substance substance) {
            if (!substance.getBlocks().contains(FRAME))
                return;

            if (OreStack.oresExist(ROD.getOre(substance), WRENCH.getOre(substance)))
                RecipeManager.addShapedRecipe(FRAME.getName(substance), FRAME.asItemStack(substance), "RRR", "RWR", "RRR", 'R', ROD.asIngredient(substance), 'W', WRENCH.asIngredient(substance));
        }

        private void hull(Substance substance) {
            if (!substance.getBlocks().contains(HULL))
                return;

            if (OreStack.oresExist(PLATE.getOre(substance), WRENCH.getOre(substance)))
                RecipeManager.addShapedRecipe(HULL.getName(substance), HULL.asItemStack(substance), "PPP", "PWP", "PPP", 'P', PLATE.asIngredient(substance), 'W', WRENCH.asIngredient(substance));
        }

        private void liquid(Substance substance) {
            if (!substance.getFluids().contains(LIQUID))
                return;

            for (ASubstanceObject.SubstanceItem item : SUBSTANCE_ITEMS_REGISTRY.getAll().values()) {
                if (item.getAmount() <= 0 || !OreStack.oresExist(item.getOre(substance)))
                    continue;

                CASTING.addRecipe(new MachineRecipeBuilder(LIQUID.getName(substance).concat("_from_").concat(item.getName(substance)))
                        .consumeItem(item.getOre(substance), 1)
                        .produceFluid(LIQUID.getName(substance), item.getAmount())
                        .consumeEnergy(25 * item.getAmount())
                        .duration(item.getAmount())
                        .configuration(CONFIGURATION_CASTING_MELTING)
                        .build());
            }

            for (ASubstanceObject.SubstanceBlock block : SUBSTANCE_BLOCKS_REGISTRY.getAll().values()) {
                if (block.getAmount() <= 0 || !OreStack.oresExist(block.getOre(substance)))
                    continue;

                CASTING.addRecipe(new MachineRecipeBuilder(LIQUID.getName(substance).concat("_from_").concat(block.getName(substance)))
                        .consumeItem(block.getOre(substance), 1)
                        .produceFluid(LIQUID.getName(substance), block.getAmount())
                        .consumeEnergy(50 * block.getAmount())
                        .duration(block.getAmount())
                        .configuration(CONFIGURATION_CASTING_MELTING)
                        .build());
            }

            if (substance.getComposition().isNative())
                return;

            Set<SubstanceStack> composition = substance.getComposition().getComposition();
            if (composition.size() < 2)
                return;

            boolean flag = Utils.all(composition, s -> FluidRegistry.isFluidRegistered(LIQUID.getName(s.getSubstance())));

            mixing:
            {
                if (!flag || substance.getProperties().containsKey(SubstanceProperties.KeyProperties.VEIN_MEMBER) || composition.size() > MIXING.getFluidsIn())
                    break mixing;

                MachineRecipeBuilder recipeMix = new MachineRecipeBuilder("mixing_" + LIQUID.getName(substance) + "_liquid");

                int amount = 0;
                for (SubstanceStack stack : composition) {
                    amount += stack.getAmount();
                    recipeMix.consumeFluid(LIQUID.getName(stack.getSubstance()), stack.getAmount());
                }

                MIXING.addRecipe(recipeMix.produceFluid(LIQUID.getName(substance), amount).duration(10).consumeEnergy(500).configuration(CONFIGURATION_MIXING_MIX).build());
            }
        }
    }
}
