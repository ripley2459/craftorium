package fr.cyrilneveu.craftorium.api.substance.object;

import com.google.common.base.Preconditions;
import fr.cyrilneveu.craftorium.api.inventory.OreStack;
import fr.cyrilneveu.craftorium.api.item.behaviour.IItemBehaviour;
import fr.cyrilneveu.craftorium.api.render.FaceProvider;
import fr.cyrilneveu.craftorium.api.render.ModelTemplate;
import fr.cyrilneveu.craftorium.api.substance.Substance;
import fr.cyrilneveu.craftorium.common.ACommonProxy;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraftforge.fml.common.Loader;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public abstract class ASubstanceObject implements Comparable<ASubstanceObject> {
    protected final String name;
    protected final boolean self;
    protected final String prefix;
    protected final String suffix;
    protected final int amount;
    protected final ICreateObject provider;
    protected final IGetFaces faces;
    protected final IGetModelTemplate model;
    @Nullable
    protected final IGetTooltips tooltips;

    public ASubstanceObject(String name, boolean self, String prefix, String suffix, int amount, ICreateObject provider, IGetFaces faces, IGetModelTemplate model, @Nullable IGetTooltips tooltips) {
        this.name = name;
        this.self = self;
        this.prefix = prefix;
        this.suffix = suffix;
        this.amount = amount;
        this.provider = provider;
        this.faces = faces;
        this.model = model;
        this.tooltips = tooltips;
    }

    public String getName(@Nullable Substance substance) {
        return self ? substance != null ? substance.getName() : name : substance == null ? name : String.join("_", substance.getName(), name);
    }

    public String getOre(Substance substance) {
        return OreStack.createOre(prefix, name, substance.getName(), suffix);
    }

    public final void createObject(Substance substance) {
        provider.create(this, substance);
    }

    public final FaceProvider[] getFaces(Substance substance) {
        return faces.get(this, substance);
    }

    public final ModelTemplate getModelTemplate(Substance substance) {
        return model.get(this, substance);
    }

    public final List<String> getTooltips(Substance substance) {
        return tooltips == null ? Collections.emptyList() : tooltips.getTooltips(this, substance);
    }

    public Ingredient asIngredient(Substance substance) {
        return OreStack.getIngredient(getOre(substance));
    }

    public ItemStack asItemStack(Substance substance) {
        return asItemStack(substance, 1);
    }

    public ItemStack asItemStack(Substance substance, int amount) {
        if (substance.shouldUse(this)) {
            String[] parts = substance.getOverrides().get(this).split(":");
            Preconditions.checkArgument(parts.length >= 2 && parts.length <= 3);

            if (Objects.equals(parts[0], "ore") && OreStack.oresExist(parts[1])) {
                ItemStack itemStack = OreStack.getStacks(parts[1])[0];
                itemStack.setCount(amount);
                return itemStack;
            }

            if (Loader.isModLoaded(parts[0])) {
                String id = String.format("%s:%s", parts[0], parts[1]);
                Item item = Item.getByNameOrId(id);
                if (item != null) {
                    int meta = parts.length == 3 ? Integer.parseInt(parts[2]) : 0;
                    return new ItemStack(item, amount, meta);
                }
            }
        }

        ItemStack stack = ACommonProxy.getItemStack(getName(substance), amount);
        if (stack == null) {
            String ore = getOre(substance);
            Preconditions.checkArgument(OreStack.oresExist(ore), "Can't return the asked ItemStack (" + getName(substance) + ") because because it does not exist either with this substance (" + substance.getName() + ") or in the form of oredict (" + ore + ").");
            ItemStack[] stacks = OreStack.getStacks(ore);
            Preconditions.checkArgument(stacks.length > 0, "Can't return the asked ItemStack (" + getName(substance) + ") because because it does not exist either with this substance (" + substance.getName() + ") or the associated oredict (" + ore + ") is empty.");
            ItemStack itemStack = OreStack.getStacks(ore)[0];
            itemStack.setCount(amount);
            return itemStack;
        }

        return stack;
    }

    public boolean getSelf() {
        return self;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public int compareTo(@Nonnull ASubstanceObject other) {
        return name.compareTo(other.getName(null));
    }

    @FunctionalInterface
    public interface ICreateObject {
        void create(ASubstanceObject reference, Substance substance);
    }

    @FunctionalInterface
    public interface IGetFaces {
        FaceProvider[] get(ASubstanceObject reference, Substance substance);
    }

    @FunctionalInterface
    public interface IGetModelTemplate {
        ModelTemplate get(ASubstanceObject reference, Substance substance);
    }

    @FunctionalInterface
    public interface IGetBehaviours {
        IItemBehaviour[] get(ASubstanceObject reference, Substance substance);
    }

    @FunctionalInterface
    public interface IGetTooltips {
        List<String> getTooltips(ASubstanceObject reference, Substance substance);
    }

    public static class SubstanceBlockDefinition extends ASubstanceObject {
        public SubstanceBlockDefinition(String name, boolean self, String prefix, String suffix, int amount, ICreateObject provider, IGetFaces faces, IGetModelTemplate model, @Nullable IGetTooltips tooltips) {
            super(name, self, prefix, suffix, amount, provider, faces, model, tooltips);
        }

        public Block asBlock(Substance substance) {
            return Block.getBlockFromItem(asItemStack(substance).getItem());
        }
    }

    public static class SubstanceFluidDefinition extends ASubstanceObject {
        public SubstanceFluidDefinition(String name, boolean self, String prefix, String suffix, int amount, ICreateObject provider, IGetFaces faces, IGetModelTemplate model, @Nullable IGetTooltips tooltips) {
            super(name, self, prefix, suffix, amount, provider, faces, model, tooltips);
        }

        @Override
        public String getOre(Substance substance) {
            return getName(substance);
        }

        @Override
        public String getName(@Nullable Substance substance) {
            return substance == null ? name : substance.getName();
        }
    }

    public static class SubstanceToolDefinition extends SubstanceItemDefinition {
        public SubstanceToolDefinition(String name, boolean self, String prefix, String suffix, int amount, ICreateObject provider, IGetFaces faces, IGetModelTemplate model, IGetBehaviours behaviours, @Nullable IGetTooltips tooltips) {
            super(name, self, prefix, suffix, amount, provider, faces, model, behaviours, tooltips);
        }

        @Override
        public String getOre(Substance substance) {
            return name;
        }
    }

    public static class SubstanceItemDefinition extends ASubstanceObject {
        protected final IGetBehaviours behaviours;

        public SubstanceItemDefinition(String name, boolean self, String prefix, String suffix, int amount, ICreateObject provider, IGetFaces faces, IGetModelTemplate model, IGetBehaviours behaviours, @Nullable IGetTooltips tooltips) {
            super(name, self, prefix, suffix, amount, provider, faces, model, tooltips);
            this.behaviours = behaviours;
        }

        public final IItemBehaviour[] getBehaviours(Substance substance) {
            return behaviours.get(this, substance);
        }
    }
}
