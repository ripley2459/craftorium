package fr.cyrilneveu.craftorium.api;

import fr.cyrilneveu.craftorium.api.machine.Machine;
import fr.cyrilneveu.craftorium.api.recipe.machine.RecipeMap;
import fr.cyrilneveu.craftorium.api.substance.Substance;
import fr.cyrilneveu.craftorium.api.substance.Tier;
import fr.cyrilneveu.craftorium.api.substance.object.ASubstanceObject;
import fr.cyrilneveu.craftorium.api.utils.Registry;
import fr.cyrilneveu.craftorium.api.world.vein.Vein;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fluids.Fluid;

public final class Registries {
    public static final Registry<String, Item> ITEMS_REGISTRY = new Registry<>();
    public static final Registry<String, Block> BLOCKS_REGISTRY = new Registry<>();
    public static final Registry<String, Fluid> FLUIDS_REGISTRY = new Registry<>();
    public static final Registry<String, ASubstanceObject.SubstanceItem> SUBSTANCE_ITEMS_REGISTRY = new Registry<>();
    public static final Registry<String, ASubstanceObject.SubstanceTool> SUBSTANCE_TOOLS_REGISTRY = new Registry<>();
    public static final Registry<String, ASubstanceObject.SubstanceBlock> SUBSTANCE_BLOCKS_REGISTRY = new Registry<>();
    public static final Registry<String, ASubstanceObject.SubstanceFluid> SUBSTANCE_FLUIDS_REGISTRY = new Registry<>();
    public static final Registry<String, Substance> SUBSTANCES_REGISTRY = new Registry<>();
    public static final Registry<String, Tier> TIERS_REGISTRY = new Registry<>();
    public static final Registry<String, Vein> VEINS_REGISTRY = new Registry<>();
    public static final Registry<String, Machine> MACHINES_REGISTRY = new Registry<>();
    public static final Registry<String, RecipeMap> MACHINE_MAPS_REGISTRY = new Registry<>();
}
