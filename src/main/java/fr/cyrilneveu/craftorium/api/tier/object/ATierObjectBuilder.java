package fr.cyrilneveu.craftorium.api.tier.object;

import javax.annotation.Nullable;

import static fr.cyrilneveu.craftorium.api.Registries.TIER_ITEMS_REGISTRY;

public abstract class ATierObjectBuilder<T> {
    protected String name;
    protected ATierObject.ICreateObject provider;
    protected ATierObject.IGetFaces faces;
    protected ATierObject.IGetModelTemplate model;
    @Nullable
    protected ATierObject.IGetTooltips tooltips;

    public ATierObjectBuilder(String name) {
        this.name = name;
    }

    public ATierObjectBuilder<T> provider(ATierObject.ICreateObject provider) {
        this.provider = provider;
        return this;
    }

    public ATierObjectBuilder<T> faces(ATierObject.IGetFaces facesProvider) {
        this.faces = facesProvider;
        return this;
    }

    public ATierObjectBuilder<T> model(ATierObject.IGetModelTemplate modelProvider) {
        this.model = modelProvider;
        return this;
    }

    public ATierObjectBuilder<T> tooltips(@Nullable ATierObject.IGetTooltips tooltips) {
        this.tooltips = tooltips;
        return this;
    }

    public abstract T build();

    public static final class TierItemBuilder extends ATierObjectBuilder<ATierObject.TierItem> {
        public TierItemBuilder(String name) {
            super(name);
        }

        @Override
        public ATierObject.TierItem build() {
            ATierObject.TierItem item = new ATierObject.TierItem(name, provider, faces, model, tooltips);
            TIER_ITEMS_REGISTRY.put(name, item);
            return item;
        }
    }
}
