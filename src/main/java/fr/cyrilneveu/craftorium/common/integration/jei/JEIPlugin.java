package fr.cyrilneveu.craftorium.common.integration.jei;

import fr.cyrilneveu.craftorium.api.machine.Machine;
import fr.cyrilneveu.craftorium.api.substance.Tier;
import fr.cyrilneveu.craftorium.common.ACommonProxy;
import fr.cyrilneveu.craftorium.common.integration.jei.recipe.machine.MachineRecipeCategory;
import fr.cyrilneveu.craftorium.common.integration.jei.recipe.machine.MachineRecipeWrapper;
import fr.cyrilneveu.craftorium.common.integration.jei.vein.VeinCategory;
import fr.cyrilneveu.craftorium.common.integration.jei.vein.VeinWrapper;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;

import java.util.ArrayList;
import java.util.List;

import static fr.cyrilneveu.craftorium.CraftoriumTags.MODID;
import static fr.cyrilneveu.craftorium.api.Registries.*;
import static fr.cyrilneveu.craftorium.common.substance.SubstancesObjects.SCANNER;

@mezz.jei.api.JEIPlugin
public final class JEIPlugin implements IModPlugin {
    @Override
    public void registerCategories(IRecipeCategoryRegistration registry) {
        registry.addRecipeCategories(new VeinCategory(registry.getJeiHelpers().getGuiHelper()));

        MACHINES_REGISTRY.getAll().forEach((s, m) -> registry.addRecipeCategories(new MachineRecipeCategory(m, registry.getJeiHelpers().getGuiHelper())));
    }

    @Override
    public void register(IModRegistry registry) {
        registry.addAdvancedGuiHandlers(new TabMover());

        for (Machine machine : MACHINES_REGISTRY.getAll().values()) {
            String id = String.join(":", MODID, machine.getName());

            for (Tier tier : TIERS_REGISTRY.getAll().values()) {
                if (tier.getMachines().contains(machine)) {
                    String name = String.join("_", machine.getName(), tier.getName());
                    registry.addRecipeCatalyst(ACommonProxy.getItemStack(name), id);
                }
            }

            List<MachineRecipeWrapper> recipes = new ArrayList<>();
            MACHINE_MAPS_REGISTRY.get(machine.getJeiData().getMap()).getRecipes().getAll().forEach((n, r) -> recipes.add(new MachineRecipeWrapper(machine, r, registry.getJeiHelpers().getGuiHelper())));
            registry.addRecipes(recipes, id);
        }

        List<VeinWrapper> veins = new ArrayList<>();
        String id = String.join(":", MODID, "vein_generation");
        TIERS_REGISTRY.getAll().values().stream().filter(tier -> tier.getItems().contains(SCANNER)).forEach(tier -> registry.addRecipeCatalyst(SCANNER.asItemStack(tier), id));
        VEINS_REGISTRY.getAll().values().forEach(v -> veins.add(new VeinWrapper(v)));
        registry.addRecipes(veins, id);
    }
}
