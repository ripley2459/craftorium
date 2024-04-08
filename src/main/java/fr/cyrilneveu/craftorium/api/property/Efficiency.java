package fr.cyrilneveu.craftorium.api.property;

public final class Efficiency {
    private final float speed, damage;
    private final int durability, harvestLevel, enchantability;

    public Efficiency(float speed, float damage, int durability, int harvestLevel, int enchantability) {
        this.speed = speed;
        this.damage = damage;
        this.durability = durability;
        this.harvestLevel = harvestLevel;
        this.enchantability = enchantability;
    }

    public float getSpeed() {
        return speed;
    }

    public float getDamage() {
        return damage;
    }

    public int getDurability() {
        return durability;
    }

    public int getHarvestLevel() {
        return harvestLevel;
    }

    public int getEnchantability() {
        return enchantability;
    }
}
