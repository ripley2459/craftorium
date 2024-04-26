package fr.cyrilneveu.craftorium.api.item;

import fr.cyrilneveu.craftorium.api.inventory.OreStack;
import fr.cyrilneveu.craftorium.api.render.FaceProvider;
import fr.cyrilneveu.craftorium.api.render.ICustomModel;
import fr.cyrilneveu.craftorium.api.render.ModelTemplate;
import fr.cyrilneveu.craftorium.api.substance.Substance;
import fr.cyrilneveu.craftorium.api.substance.object.ASubstanceObject;
import fr.cyrilneveu.craftorium.api.utils.Utils;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.*;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.model.ModelLoader;

import java.util.Set;

import static fr.cyrilneveu.craftorium.api.utils.Utils.ITEM_MODEL_BUILDER;
import static fr.cyrilneveu.craftorium.common.substance.SubstancesObjects.INGOT;

public class SubstanceItem extends CustomItem {
    protected final ASubstanceObject reference;
    protected final Substance substance;

    public SubstanceItem(ASubstanceObject reference, Substance substance) {
        super(reference.getFaces(substance), () -> reference.getTooltips(substance));
        this.reference = reference;
        this.substance = substance;
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        return Utils.localise(String.join(".", getTranslationKey(), "name"), substance.getDisplayName());
    }

    public static class SubstanceTool extends SubstanceItem {
        public SubstanceTool(ASubstanceObject reference, Substance substance) {
            super(reference, substance);
            this.maxStackSize = 1;
            if (substance.getEfficiency() != null)
                this.setMaxDamage(substance.getEfficiency().getDurability());
        }

        @Override
        public boolean isDamageable() {
            return true;
        }

        @Override
        public ItemStack getContainerItem(ItemStack stack) {
            ItemStack container = stack.copy();
            container.setItemDamage(container.getItemDamage() + 1);
            return container;
        }

        @Override
        public boolean hasContainerItem(ItemStack stack) {
            return true;
        }

        @Override
        public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
            return new OreStack(INGOT.getOre(substance)).matches(repair);
        }
    }

    public static final class SubstanceAxe extends ItemAxe implements ICustomModel, IItemColor {
        private final Substance substance;
        private final FaceProvider[] faceProviders;

        public SubstanceAxe(ASubstanceObject reference, Substance substance) {
            super(ToolMaterial.valueOf(substance.getName()), substance.getEfficiency().getDamage(), substance.getEfficiency().getSpeed());
            this.faceProviders = reference.getFaces(substance);
            this.substance = substance;
        }

        @Override
        public String getItemStackDisplayName(ItemStack stack) {
            return Utils.localise(String.join(".", getTranslationKey(), "name"), substance.getDisplayName());
        }

        @Override
        public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
            return new OreStack(INGOT.getOre(substance)).matches(repair);
        }

        @Override
        public int colorMultiplier(ItemStack stack, int tintIndex) {
            return faceProviders[tintIndex].getColor();
        }

        @Override
        public void addTextures(Set<ResourceLocation> textures) {
            for (FaceProvider faceProvider : faceProviders)
                textures.add(faceProvider.getTexture());
        }

        @Override
        public void onModelRegister() {
            ModelResourceLocation location = Utils.getSimpleModelLocation(this);
            ModelBakery.registerItemVariants(this, location);
            ModelLoader.setCustomMeshDefinition(this, stack -> location);
        }

        @Override
        public void onModelBake(ModelBakeEvent event) {
            ITEM_MODEL_BUILDER.newOperation(ModelTemplate.ITEM_HANDHELD);

            for (FaceProvider face : faceProviders)
                ITEM_MODEL_BUILDER.addLayer(face.getTexture());

            event.getModelRegistry().putObject(Utils.getSimpleModelLocation(this), ITEM_MODEL_BUILDER.build().getModel());
        }
    }

    public static final class SubstanceHoe extends ItemHoe implements ICustomModel, IItemColor {
        private final Substance substance;
        private final FaceProvider[] faceProviders;

        public SubstanceHoe(ASubstanceObject reference, Substance substance) {
            super(ToolMaterial.valueOf(substance.getName()));
            this.faceProviders = reference.getFaces(substance);
            this.substance = substance;
        }

        @Override
        public String getItemStackDisplayName(ItemStack stack) {
            return Utils.localise(String.join(".", getTranslationKey(), "name"), substance.getDisplayName());
        }

        @Override
        public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
            return new OreStack(INGOT.getOre(substance)).matches(repair);
        }

        @Override
        public int colorMultiplier(ItemStack stack, int tintIndex) {
            return faceProviders[tintIndex].getColor();
        }

        @Override
        public void addTextures(Set<ResourceLocation> textures) {
            for (FaceProvider faceProvider : faceProviders)
                textures.add(faceProvider.getTexture());
        }

        @Override
        public void onModelRegister() {
            ModelResourceLocation location = Utils.getSimpleModelLocation(this);
            ModelBakery.registerItemVariants(this, location);
            ModelLoader.setCustomMeshDefinition(this, stack -> location);
        }

        @Override
        public void onModelBake(ModelBakeEvent event) {
            ITEM_MODEL_BUILDER.newOperation(ModelTemplate.ITEM_HANDHELD);

            for (FaceProvider face : faceProviders)
                ITEM_MODEL_BUILDER.addLayer(face.getTexture());

            event.getModelRegistry().putObject(Utils.getSimpleModelLocation(this), ITEM_MODEL_BUILDER.build().getModel());
        }
    }

    public static final class SubstancePickaxe extends ItemPickaxe implements ICustomModel, IItemColor {
        private final Substance substance;
        private final FaceProvider[] faceProviders;

        public SubstancePickaxe(ASubstanceObject reference, Substance substance) {
            super(ToolMaterial.valueOf(substance.getName()));
            this.faceProviders = reference.getFaces(substance);
            this.substance = substance;
        }

        @Override
        public String getItemStackDisplayName(ItemStack stack) {
            return Utils.localise(String.join(".", getTranslationKey(), "name"), substance.getDisplayName());
        }

        @Override
        public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
            return new OreStack(INGOT.getOre(substance)).matches(repair);
        }

        @Override
        public int colorMultiplier(ItemStack stack, int tintIndex) {
            return faceProviders[tintIndex].getColor();
        }

        @Override
        public void addTextures(Set<ResourceLocation> textures) {
            for (FaceProvider faceProvider : faceProviders)
                textures.add(faceProvider.getTexture());
        }

        @Override
        public void onModelRegister() {
            ModelResourceLocation location = Utils.getSimpleModelLocation(this);
            ModelBakery.registerItemVariants(this, location);
            ModelLoader.setCustomMeshDefinition(this, stack -> location);
        }

        @Override
        public void onModelBake(ModelBakeEvent event) {
            ITEM_MODEL_BUILDER.newOperation(ModelTemplate.ITEM_HANDHELD);

            for (FaceProvider face : faceProviders)
                ITEM_MODEL_BUILDER.addLayer(face.getTexture());

            event.getModelRegistry().putObject(Utils.getSimpleModelLocation(this), ITEM_MODEL_BUILDER.build().getModel());
        }
    }

    public static final class SubstanceShovel extends ItemSpade implements ICustomModel, IItemColor {
        private final Substance substance;
        private final FaceProvider[] faceProviders;

        public SubstanceShovel(ASubstanceObject reference, Substance substance) {
            super(ToolMaterial.valueOf(substance.getName()));
            this.faceProviders = reference.getFaces(substance);
            this.substance = substance;
        }

        @Override
        public String getItemStackDisplayName(ItemStack stack) {
            return Utils.localise(String.join(".", getTranslationKey(), "name"), substance.getDisplayName());
        }

        @Override
        public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
            return new OreStack(INGOT.getOre(substance)).matches(repair);
        }

        @Override
        public int colorMultiplier(ItemStack stack, int tintIndex) {
            return faceProviders[tintIndex].getColor();
        }

        @Override
        public void addTextures(Set<ResourceLocation> textures) {
            for (FaceProvider faceProvider : faceProviders)
                textures.add(faceProvider.getTexture());
        }

        @Override
        public void onModelRegister() {
            ModelResourceLocation location = Utils.getSimpleModelLocation(this);
            ModelBakery.registerItemVariants(this, location);
            ModelLoader.setCustomMeshDefinition(this, stack -> location);
        }

        @Override
        public void onModelBake(ModelBakeEvent event) {
            ITEM_MODEL_BUILDER.newOperation(ModelTemplate.ITEM_HANDHELD);

            for (FaceProvider face : faceProviders)
                ITEM_MODEL_BUILDER.addLayer(face.getTexture());

            event.getModelRegistry().putObject(Utils.getSimpleModelLocation(this), ITEM_MODEL_BUILDER.build().getModel());
        }
    }

    public static final class SubstanceSword extends ItemSword implements ICustomModel, IItemColor {
        private final Substance substance;
        private final FaceProvider[] faceProviders;

        public SubstanceSword(ASubstanceObject reference, Substance substance) {
            super(ToolMaterial.valueOf(substance.getName()));
            this.faceProviders = reference.getFaces(substance);
            this.substance = substance;
        }

        @Override
        public String getItemStackDisplayName(ItemStack stack) {
            return Utils.localise(String.join(".", getTranslationKey(), "name"), substance.getDisplayName());
        }

        @Override
        public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
            return new OreStack(INGOT.getOre(substance)).matches(repair);
        }

        @Override
        public int colorMultiplier(ItemStack stack, int tintIndex) {
            return faceProviders[tintIndex].getColor();
        }

        @Override
        public void addTextures(Set<ResourceLocation> textures) {
            for (FaceProvider faceProvider : faceProviders)
                textures.add(faceProvider.getTexture());
        }

        @Override
        public void onModelRegister() {
            ModelResourceLocation location = Utils.getSimpleModelLocation(this);
            ModelBakery.registerItemVariants(this, location);
            ModelLoader.setCustomMeshDefinition(this, stack -> location);
        }

        @Override
        public void onModelBake(ModelBakeEvent event) {
            ITEM_MODEL_BUILDER.newOperation(ModelTemplate.ITEM_HANDHELD);

            for (FaceProvider face : faceProviders)
                ITEM_MODEL_BUILDER.addLayer(face.getTexture());

            event.getModelRegistry().putObject(Utils.getSimpleModelLocation(this), ITEM_MODEL_BUILDER.build().getModel());
        }
    }
}
