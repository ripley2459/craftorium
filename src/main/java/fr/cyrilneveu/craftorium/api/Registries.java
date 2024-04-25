package fr.cyrilneveu.craftorium.api;

import fr.cyrilneveu.craftorium.api.substance.Substance;
import fr.cyrilneveu.craftorium.api.substance.object.SubstanceBlock;
import fr.cyrilneveu.craftorium.api.substance.object.SubstanceFluid;
import fr.cyrilneveu.craftorium.api.substance.object.SubstanceItem;
import fr.cyrilneveu.craftorium.api.substance.object.SubstanceTool;
import fr.cyrilneveu.craftorium.api.tier.Tier;
import fr.cyrilneveu.craftorium.api.utils.Registry;
import fr.cyrilneveu.craftorium.api.world.vein.Vein;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fluids.Fluid;

public final class Registries {
    public static final Registry<String, Item> ITEMS_REGISTRY = new Registry<>();
    public static final Registry<String, Block> BLOCKS_REGISTRY = new Registry<>();
    public static final Registry<String, Fluid> FLUIDS_REGISTRY = new Registry<>();
    public static final Registry<String, SubstanceItem> SUBSTANCE_ITEMS_REGISTRY = new Registry<>();
    public static final Registry<String, SubstanceTool> SUBSTANCE_TOOLS_REGISTRY = new Registry<>();
    public static final Registry<String, SubstanceBlock> SUBSTANCE_BLOCKS_REGISTRY = new Registry<>();
    public static final Registry<String, SubstanceFluid> SUBSTANCE_FLUIDS_REGISTRY = new Registry<>();
    public static final Registry<String, Substance> SUBSTANCES_REGISTRY = new Registry<>();
    public static final Registry<String, Tier> TIERS_REGISTRY = new Registry<>();
    public static final Registry<String, Vein> VEINS_REGISTRY = new Registry<>();
}
