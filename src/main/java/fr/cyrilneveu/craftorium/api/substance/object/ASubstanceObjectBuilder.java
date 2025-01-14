package fr.cyrilneveu.craftorium.api.substance.object;

import javax.annotation.Nullable;

import static fr.cyrilneveu.craftorium.api.Registries.*;

public abstract class ASubstanceObjectBuilder<T> {
    protected String name;
    protected boolean self;
    protected String prefix;
    protected String suffix;
    protected int amount = 0;
    protected ASubstanceObject.ICreateObject provider;
    protected ASubstanceObject.IGetFaces faces;
    protected ASubstanceObject.IGetModelTemplate model;
    protected ASubstanceObject.IGetBehaviours behaviours;
    @Nullable
    protected ASubstanceObject.IGetTooltips tooltips;

    public ASubstanceObjectBuilder(String name) {
        this.name = name;
    }

    public ASubstanceObjectBuilder<T> self() {
        self = !self;
        return this;
    }

    public ASubstanceObjectBuilder<T> prefix(String prefix) {
        this.prefix = prefix;
        return this;
    }

    public ASubstanceObjectBuilder<T> suffix(String suffix) {
        this.suffix = suffix;
        return this;
    }

    public ASubstanceObjectBuilder<T> amount(int amount) {
        this.amount = amount;
        return this;
    }

    public ASubstanceObjectBuilder<T> provider(ASubstanceObject.ICreateObject provider) {
        this.provider = provider;
        return this;
    }

    public ASubstanceObjectBuilder<T> faces(ASubstanceObject.IGetFaces facesProvider) {
        this.faces = facesProvider;
        return this;
    }

    public ASubstanceObjectBuilder<T> model(ASubstanceObject.IGetModelTemplate modelProvider) {
        this.model = modelProvider;
        return this;
    }

    public ASubstanceObjectBuilder<T> behaviours(ASubstanceObject.IGetBehaviours behaviours) {
        this.behaviours = behaviours;
        return this;
    }

    public ASubstanceObjectBuilder<T> tooltips(@Nullable ASubstanceObject.IGetTooltips tooltips) {
        this.tooltips = tooltips;
        return this;
    }

    public abstract T build();

    public static final class SubstanceItemBuilder extends ASubstanceObjectBuilder<ASubstanceObject.SubstanceItemDefinition> {
        public SubstanceItemBuilder(String name) {
            super(name);
        }

        @Override
        public ASubstanceObject.SubstanceItemDefinition build() {
            ASubstanceObject.SubstanceItemDefinition item = new ASubstanceObject.SubstanceItemDefinition(name, self, prefix, suffix, amount, provider, faces, model, behaviours, tooltips);
            SUBSTANCE_ITEMS_REGISTRY.put(name, item);
            return item;
        }
    }

    public static final class SubstanceToolBuilder extends ASubstanceObjectBuilder<ASubstanceObject.SubstanceToolDefinition> {
        public SubstanceToolBuilder(String name) {
            super(name);
        }

        @Override
        public ASubstanceObject.SubstanceToolDefinition build() {
            ASubstanceObject.SubstanceToolDefinition tool = new ASubstanceObject.SubstanceToolDefinition(name, self, prefix, suffix, amount, provider, faces, model, behaviours, tooltips);
            SUBSTANCE_TOOLS_REGISTRY.put(name, tool);
            return tool;
        }
    }

    public static final class SubstanceBlockBuilder extends ASubstanceObjectBuilder<ASubstanceObject.SubstanceBlockDefinition> {
        public SubstanceBlockBuilder(String name, ASubstanceObject.ICreateObject provider) {
            super(name);
            this.provider = provider;
        }

        @Override
        public ASubstanceObject.SubstanceBlockDefinition build() {
            ASubstanceObject.SubstanceBlockDefinition block = new ASubstanceObject.SubstanceBlockDefinition(name, self, prefix, suffix, amount, provider, faces, model, tooltips);
            SUBSTANCE_BLOCKS_REGISTRY.put(name, block);
            return block;
        }
    }

    public static final class SubstanceFluidBuilder extends ASubstanceObjectBuilder<ASubstanceObject.SubstanceFluidDefinition> {
        public SubstanceFluidBuilder(String name, ASubstanceObject.ICreateObject provider) {
            super(name);
            this.provider = provider;
        }

        @Override
        public ASubstanceObject.SubstanceFluidDefinition build() {
            ASubstanceObject.SubstanceFluidDefinition fluid = new ASubstanceObject.SubstanceFluidDefinition(name, self, prefix, suffix, amount, provider, faces, model, tooltips);
            SUBSTANCE_FLUIDS_REGISTRY.put(name, fluid);
            return fluid;
        }
    }
}
