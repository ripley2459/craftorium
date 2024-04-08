package fr.cyrilneveu.craftorium.api.property;

public final class Temperature {
    public static final Temperature EMPTY = new Temperature(Float.NaN, Float.NaN);
    private final float meltingPoint, boilingPoint;

    public Temperature(float meltingPoint, float boilingPoint) {
        this.meltingPoint = meltingPoint;
        this.boilingPoint = boilingPoint;
    }

    public float getMeltingPoint() {
        return meltingPoint;
    }

    public float getBoilingPoint() {
        return boilingPoint;
    }
}
