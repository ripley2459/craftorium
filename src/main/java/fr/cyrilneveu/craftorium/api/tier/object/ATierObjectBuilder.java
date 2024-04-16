package fr.cyrilneveu.craftorium.api.tier.object;

import fr.cyrilneveu.craftorium.api.substance.object.SubstanceItem;

import static fr.cyrilneveu.craftorium.common.tier.TiersObjects.TIER_ITEMS_REGISTRY;

public abstract class ATierObjectBuilder<T> {
    protected String name;
    protected ATierObject.ICreateObject provider;
    protected ATierObject.IGetFaces faces;
    protected ATierObject.IGetModelTemplate model;

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

    public abstract T build();

    public static final class TierItemBuilder extends ATierObjectBuilder<TierItem> {
        public TierItemBuilder(String name) {
            super(name);
        }

        @Override
        public TierItem build() {
            TierItem item = new TierItem(name, provider, faces, model);
            TIER_ITEMS_REGISTRY.put(name, item);
            return item;
        }
    }
}
