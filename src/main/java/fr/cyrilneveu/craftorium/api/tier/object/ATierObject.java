package fr.cyrilneveu.craftorium.api.tier.object;

import fr.cyrilneveu.craftorium.api.inventory.OreStack;
import fr.cyrilneveu.craftorium.api.render.FaceProvider;
import fr.cyrilneveu.craftorium.api.render.ModelTemplate;
import fr.cyrilneveu.craftorium.api.tier.Tier;
import net.minecraft.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

public abstract class ATierObject implements Comparable<ATierObject> {
    protected final String name;
    protected final ICreateObject provider;
    protected final IGetFaces faces;
    protected final IGetModelTemplate model;
    @Nullable
    protected final IGetTooltips tooltips;

    public ATierObject(String name, ICreateObject provider, IGetFaces faces, IGetModelTemplate model, @Nullable IGetTooltips tooltips) {
        this.name = name;
        this.provider = provider;
        this.faces = faces;
        this.model = model;
        this.tooltips = tooltips;
    }

    public String getName(@Nullable Tier tier) {
        return tier == null ? name : String.join("_", name, "tier", tier.getName());
    }

    public String getOre(Tier tier) {
        return OreStack.createOre(name, tier.getName());
    }

    public final void createObject(Tier tier) {
        provider.createObject(this, tier);
    }

    public final FaceProvider[] getFaces(Tier tier) {
        return faces.getFaces(this, tier);
    }

    public final ModelTemplate getModelTemplate(Tier tier) {
        return model.getModelTemplate(this, tier);
    }

    public final List<String> getTooltips(Tier tier) {
        return tooltips.getTooltips(this, tier);
    }

    public Ingredient asIngredient(Tier tier) {
        return OreStack.getIngredient(getOre(tier));
    }

    @Override
    public int compareTo(@NotNull ATierObject other) {
        return name.compareTo(other.getName(null));
    }

    @FunctionalInterface
    public interface ICreateObject {
        void createObject(ATierObject reference, Tier substance);
    }

    @FunctionalInterface
    public interface IGetFaces {
        FaceProvider[] getFaces(ATierObject reference, Tier tier);
    }

    @FunctionalInterface
    public interface IGetModelTemplate {
        ModelTemplate getModelTemplate(ATierObject reference, Tier tier);
    }

    @FunctionalInterface
    public interface IGetTooltips {
        List<String> getTooltips(ATierObject reference, Tier tier);
    }
}
