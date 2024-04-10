package fr.cyrilneveu.craftorium.api.substance.object;

import static fr.cyrilneveu.craftorium.common.substance.SubstancesObjects.*;

public abstract class ASubstanceObjectBuilder<T> {
    protected String name;
    protected boolean self;
    protected String prefix;
    protected String suffix;
    protected ASubstanceObject.ICreateObject provider;
    protected ASubstanceObject.IGetFaces faces;
    protected ASubstanceObject.IGetModelTemplate model;

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

    public abstract T build();

    public static final class SubstanceItemBuilder extends ASubstanceObjectBuilder<SubstanceItem> {
        public SubstanceItemBuilder(String name) {
            super(name);
        }

        @Override
        public SubstanceItem build() {
            SubstanceItem item = new SubstanceItem(name, self, prefix, suffix, provider, faces, model);
            SUBSTANCE_ITEMS_REGISTRY.put(name, item);
            return item;
        }
    }

    public static final class SubstanceToolBuilder extends ASubstanceObjectBuilder<SubstanceTool> {
        public SubstanceToolBuilder(String name) {
            super(name);
        }

        @Override
        public SubstanceTool build() {
            SubstanceTool tool = new SubstanceTool(name, self, prefix, suffix, provider, faces, model);
            SUBSTANCE_TOOLS_REGISTRY.put(name, tool);
            return tool;
        }
    }

    public static final class SubstanceBlockBuilder extends ASubstanceObjectBuilder<SubstanceBlock> {
        public SubstanceBlockBuilder(String name, ASubstanceObject.ICreateObject provider) {
            super(name);
            this.provider = provider;
        }

        @Override
        public SubstanceBlock build() {
            SubstanceBlock block = new SubstanceBlock(name, self, prefix, suffix, provider, faces, model);
            SUBSTANCE_BLOCKS_REGISTRY.put(name, block);
            return block;
        }
    }

    public static final class SubstanceFluidBuilder extends ASubstanceObjectBuilder<SubstanceFluid> {
        public SubstanceFluidBuilder(String name, ASubstanceObject.ICreateObject provider) {
            super(name);
            this.provider = provider;
        }

        @Override
        public SubstanceFluid build() {
            SubstanceFluid fluid = new SubstanceFluid(name, self, prefix, suffix, provider, faces, model);
            SUBSTANCE_FLUIDS_REGISTRY.put(name, fluid);
            return fluid;
        }
    }
}
