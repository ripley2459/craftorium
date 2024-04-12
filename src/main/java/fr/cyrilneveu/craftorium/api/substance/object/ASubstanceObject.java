package fr.cyrilneveu.craftorium.api.substance.object;

import com.google.common.base.Preconditions;
import fr.cyrilneveu.craftorium.api.inventory.OreStack;
import fr.cyrilneveu.craftorium.api.render.FaceProvider;
import fr.cyrilneveu.craftorium.api.render.ModelTemplate;
import fr.cyrilneveu.craftorium.api.substance.Substance;
import fr.cyrilneveu.craftorium.common.ACommonProxy;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public abstract class ASubstanceObject implements Comparable<ASubstanceObject> {
    protected final String name;
    protected final boolean self;
    protected final String prefix;
    protected final String suffix;
    protected final ICreateObject provider;
    protected final IGetFaces faces;
    protected final IGetModelTemplate model;

    public ASubstanceObject(String name, boolean self, String prefix, String suffix, ICreateObject provider, IGetFaces faces, IGetModelTemplate model) {
        this.name = name;
        this.self = self;
        this.prefix = prefix;
        this.suffix = suffix;
        this.provider = provider;
        this.faces = faces;
        this.model = model;
    }

    public String getName(@Nullable Substance substance) {
        return self ? substance != null ? substance.getName() : name : substance == null ? name : String.join("_", substance.getName(), name);
    }

    public String getOre(Substance substance) {
        return self ? OreStack.createOre(prefix, substance.getName(), suffix) : OreStack.createOre(prefix, name, substance.getName(), suffix);
    }

    public final void createObject(Substance substance) {
        provider.createObject(this, substance);
    }

    public final FaceProvider[] getFaces(Substance substance) {
        return faces.getFaces(this, substance);
    }

    public final ModelTemplate getModelTemplate(Substance substance) {
        return model.getModelTemplate(this, substance);
    }

    public Ingredient asIngredient(Substance substance) {
        return OreStack.getIngredient(getOre(substance));
    }

    public ItemStack asItemStack(Substance substance) {
        return asItemStack(substance, 1);
    }

    public ItemStack asItemStack(Substance substance, int amount) {
        /*if (substance.shouldUse(this)) {
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
        }*/

        ItemStack stack = ACommonProxy.getItemStack(getName(substance), amount);
        if (stack == null) {
            String ore = getOre(substance);
            Preconditions.checkArgument(OreStack.oresExist(ore), "Can\'t return the asked ItemStack (" + getName(substance) + ") because because it does not exist either with this substance (" + substance.getName() + ") or in the form of oredict (" + ore + ").");
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

    @Override
    public int compareTo(@NotNull ASubstanceObject other) {
        return name.compareTo(other.getName(null));
    }

    @FunctionalInterface
    public interface ICreateObject {
        void createObject(ASubstanceObject reference, Substance substance);
    }

    @FunctionalInterface
    public interface IGetFaces {
        FaceProvider[] getFaces(ASubstanceObject reference, Substance substance);
    }

    @FunctionalInterface
    public interface IGetModelTemplate {
        ModelTemplate getModelTemplate(ASubstanceObject reference, Substance substance);
    }
}
