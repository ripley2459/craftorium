package fr.cyrilneveu.craftorium.api.inventory;

import fr.cyrilneveu.craftorium.api.utils.Utils;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;

public final class OreStack {
    private final String oreDictName;
    private final ItemStack itemStack;
    private final int amount;
    private boolean strict = false;
    private boolean consumable = true;

    public OreStack(String oreDictName) {
        this(oreDictName, 1);
    }

    public OreStack(String oreDictName, int amount) {
        this.oreDictName = oreDictName;
        this.itemStack = ItemStack.EMPTY;
        this.amount = amount;
    }

    public OreStack(ItemStack itemStack, int amount) {
        this.oreDictName = null;
        this.itemStack = itemStack;
        this.amount = amount;
    }

    public OreStack(ItemStack itemStack) {
        if (itemStack != null) {
            this.itemStack = itemStack;
            this.amount = itemStack.getCount();
        } else {
            this.itemStack = ItemStack.EMPTY;
            this.amount = 0;
        }

        this.oreDictName = null;
    }

    public static String createOre(String... parts) {
        return Utils.toCamelCase(String.join("_", Utils.cleanArray(parts)));
    }

    public static boolean oresExist(String... ores) {
        return Utils.all(ores, OreDictionary::doesOreNameExist);
    }

    public static ItemStack[] getStacks(String ore) {
        // TODO : Order by ore priority
        ArrayList<ItemStack> itemStacks = new OreStack(ore).getStacks();
        return itemStacks.toArray(new ItemStack[0]);
    }

    public static Ingredient getIngredient(String ore) {
        return Ingredient.fromStacks(getStacks(ore));
    }

    public boolean isOreDict() {
        return oreDictName != null;
    }

    public boolean isEmpty() {
        return oreDictName == null && itemStack.isEmpty();
    }

    public OreStack setNoStrictMode() {
        strict = false;
        return this;
    }

    public OreStack setStrictMode() {
        strict = true;
        return this;
    }

    public OreStack setConsumable() {
        consumable = true;
        return this;
    }

    public OreStack setNotConsumable() {
        consumable = false;
        return this;
    }

    public boolean hasItems() {
        if (isEmpty())
            return false;
        return oreDictName != null ? !OreDictionary.getOres(oreDictName, true).isEmpty() : !itemStack.isEmpty();
    }

    public boolean matches(OreStack other) {
        if (oreDictName != null && other.oreDictName != null)
            return oreDictName.equals(other.oreDictName);
        else if (oreDictName != null)
            return isEqualWithOreDict(other.itemStack);
        else if (other.oreDictName != null)
            return other.isEqualWithOreDict(itemStack);
        else return OreDictionary.itemMatches(itemStack, other.itemStack, false);
    }

    public boolean matches(ItemStack other) {
        return matches(new OreStack(other, other.getCount()));
    }

    public boolean isEqualWithOreDict(ItemStack other) {
        if (oreDictName == null)
            return OreDictionary.itemMatches(itemStack, other, strict);
        else return Utils.atLeastOne(getStacks(), i -> OreDictionary.itemMatches(i, other, strict));
    }

    public ArrayList<ItemStack> getStacks() {
        ArrayList<ItemStack> list = new ArrayList<>();
        if (oreDictName == null) {
            if (!itemStack.isEmpty())
                list.add(new ItemStack(itemStack.getItem(), amount, itemStack.getItemDamage()));
        } else {
            for (ItemStack s : OreDictionary.getOres(oreDictName)) {
                ItemStack stack = s.copy();
                stack.setCount(amount);
                list.add(stack);
            }
        }

        return list;
    }

    public ArrayList<ItemStack> getStacks(int amountOverride) {
        ArrayList<ItemStack> list = getStacks();
        list.forEach(s -> s.setCount(amountOverride));
        return list;
    }

    public Ingredient getIngredient() {
        return Ingredient.fromStacks(getStacks().toArray(new ItemStack[0]));
    }

    public String getOreDictName() {
        return oreDictName;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public int getAmount() {
        return amount;
    }

    public boolean isStrict() {
        return strict;
    }

    public boolean isConsumable() {
        return consumable;
    }
}
