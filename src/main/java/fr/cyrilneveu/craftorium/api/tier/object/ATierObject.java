package fr.cyrilneveu.craftorium.api.tier.object;

import fr.cyrilneveu.craftorium.api.inventory.OreStack;
import fr.cyrilneveu.craftorium.api.item.behaviour.IItemBehaviour;
import fr.cyrilneveu.craftorium.api.render.FaceProvider;
import fr.cyrilneveu.craftorium.api.render.ModelTemplate;
import fr.cyrilneveu.craftorium.api.tier.Tier;
import fr.cyrilneveu.craftorium.common.ACommonProxy;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;

public abstract class ATierObject implements Comparable<ATierObject> {
    protected final String name;
    protected final ICreateObject provider;
    protected final IGetFaces faces;
    protected final IGetModelTemplate model;
    protected final IGetBehaviours behaviours;
    @Nullable
    protected final IGetTooltips tooltips;

    public ATierObject(String name, ICreateObject provider, IGetFaces faces, IGetModelTemplate model, IGetBehaviours behaviours, @Nullable IGetTooltips tooltips) {
        this.name = name;
        this.provider = provider;
        this.faces = faces;
        this.model = model;
        this.behaviours = behaviours;
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
        return faces.get(this, tier);
    }

    public final ModelTemplate getModelTemplate(Tier tier) {
        return model.get(this, tier);
    }

    public final IItemBehaviour[] getBehaviours(Tier tier) {
        return behaviours.get(this, tier);
    }

    public final List<String> getTooltips(Tier tier) {
        return tooltips == null ? Collections.emptyList() : tooltips.get(this, tier);
    }

    public Ingredient asIngredient(Tier tier) {
        return OreStack.getIngredient(getOre(tier));
    }

    public ItemStack asItemStack(Tier tier) {
        return asItemStack(tier, 1);
    }

    public ItemStack asItemStack(Tier tier, int amount) {
        return ACommonProxy.getItemStack(getName(tier), amount);
    }

    @Override
    public int compareTo(@Nonnull ATierObject other) {
        return name.compareTo(other.getName(null));
    }

    @FunctionalInterface
    public interface ICreateObject {
        void createObject(ATierObject reference, Tier substance);
    }

    @FunctionalInterface
    public interface IGetFaces {
        FaceProvider[] get(ATierObject reference, Tier tier);
    }

    @FunctionalInterface
    public interface IGetModelTemplate {
        ModelTemplate get(ATierObject reference, Tier tier);
    }

    @FunctionalInterface
    public interface IGetBehaviours {
        IItemBehaviour[] get(ATierObject reference, Tier tier);
    }

    @FunctionalInterface
    public interface IGetTooltips {
        List<String> get(ATierObject reference, Tier tier);
    }

    public static final class TierItem extends ATierObject {
        public TierItem(String name, ICreateObject provider, IGetFaces faces, IGetModelTemplate model, IGetBehaviours behaviours, IGetTooltips tooltips) {
            super(name, provider, faces, model, behaviours, tooltips);
        }
    }
}
