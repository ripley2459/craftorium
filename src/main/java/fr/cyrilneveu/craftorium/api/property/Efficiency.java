package fr.cyrilneveu.craftorium.api.property;

public final class Efficiency {
    private final int harvestLevel, durability, enchantability;
    private final float speed, damage;

    @Deprecated
    public Efficiency(float speed, float damage, int durability, int harvestLevel, int enchantability) {
        this.speed = speed;
        this.damage = damage;
        this.durability = durability;
        this.harvestLevel = harvestLevel;
        this.enchantability = enchantability;
    }

    /**
     * | Material | Harvest Level  | Durability | Speed | Damage | Enchantability |
     * |----------|----------------|------------|-------|--------|----------------|
     * | WOOD     | 0              | 59         | 2.0   | 0.0    | 15             |
     * | STONE    | 1              | 131        | 4.0   | 1.0    | 5              |
     * | IRON     | 2              | 250        | 6.0   | 2.0    | 14             |
     * | DIAMOND  | 3              | 1561       | 8.0   | 3.0    | 10             |
     * | GOLD     | 0              | 32         | 12.0  | 0.0    | 22             |
     */
    public Efficiency(int harvestLevel, int durability, float speed, float damage, int enchantability) {
        this.harvestLevel = harvestLevel;
        this.durability = durability;
        this.speed = speed;
        this.damage = damage;
        this.enchantability = enchantability;
    }

    public int getHarvestLevel() {
        return harvestLevel;
    }

    public int getDurability() {
        return durability;
    }

    public float getSpeed() {
        return speed;
    }

    public float getDamage() {
        return damage;
    }

    public int getEnchantability() {
        return enchantability;
    }
}
