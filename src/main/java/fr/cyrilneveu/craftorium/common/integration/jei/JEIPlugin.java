package fr.cyrilneveu.craftorium.common.integration.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;

import java.util.ArrayList;
import java.util.List;

import static fr.cyrilneveu.craftorium.CraftoriumTags.MODID;
import static fr.cyrilneveu.craftorium.api.Registries.TIERS_REGISTRY;
import static fr.cyrilneveu.craftorium.api.Registries.VEINS_REGISTRY;
import static fr.cyrilneveu.craftorium.common.tier.TiersObjects.SCANNER;

@mezz.jei.api.JEIPlugin
public final class JEIPlugin implements IModPlugin {
    @Override
    public void registerCategories(IRecipeCategoryRegistration registry) {
        registry.addRecipeCategories(new VeinCategory(registry.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void register(IModRegistry registry) {
        List<VeinWrapper> veins = new ArrayList<>();
        String id = String.join(":", MODID, "vein_generation");
        TIERS_REGISTRY.getAll().values().stream().filter(tier -> tier.getItems().contains(SCANNER)).forEach(tier -> registry.addRecipeCatalyst(SCANNER.asItemStack(tier), id));
        VEINS_REGISTRY.getAll().values().forEach(v -> veins.add(new VeinWrapper(v)));
        registry.addRecipes(veins, id);
    }
}
