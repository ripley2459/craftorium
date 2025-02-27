package fr.cyrilneveu.craftorium.api.substance.object;

import fr.cyrilneveu.craftorium.api.inventory.OreStack;
import fr.cyrilneveu.craftorium.api.property.Aestheticism;
import fr.cyrilneveu.craftorium.api.render.FaceProvider;
import fr.cyrilneveu.craftorium.api.render.ICustomModel;
import fr.cyrilneveu.craftorium.api.render.ModelTemplates;
import fr.cyrilneveu.craftorium.api.substance.Substance;
import fr.cyrilneveu.craftorium.api.utils.RenderUtils;
import fr.cyrilneveu.craftorium.api.utils.Utils;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.*;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Set;

import static fr.cyrilneveu.craftorium.api.utils.RenderUtils.ITEM_MODEL_BUILDER;
import static fr.cyrilneveu.craftorium.common.substance.SubstancesObjects.INGOT;

public class SubstanceTool {
    public static final class SubstanceAxe extends ItemAxe implements ICustomModel {
        private final Substance substance;
        private final Aestheticism.ObjectAestheticism aestheticism;

        public SubstanceAxe(ASubstanceObject reference, Substance substance) {
            super(ToolMaterial.valueOf(substance.getName()), substance.getEfficiency().getDamage(), substance.getEfficiency().getSpeed());
            this.aestheticism = new Aestheticism.ObjectAestheticism(reference.getFaces(substance), () -> reference.getTooltips(substance), substance.getAestheticism().isGlint(), null);
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
        @SideOnly(Side.CLIENT)
        public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
            if (aestheticism.getToolTips() != null)
                tooltip.addAll(aestheticism.getToolTips().get());
        }

        @Override
        @SideOnly(Side.CLIENT)
        public boolean hasEffect(ItemStack stack) {
            return aestheticism.isGlint();
        }

        @Override
        @SideOnly(Side.CLIENT)
        public int getItemStackColor(ItemStack stack, int layer) {
            return aestheticism.getFaceProviders()[layer].getColor();
        }

        @Override
        public void addTextures(Set<ResourceLocation> textures) {
            for (FaceProvider faceProvider : aestheticism.getFaceProviders())
                textures.add(faceProvider.getTexture());
        }

        @Override
        public void onModelRegister() {
            ModelResourceLocation location = RenderUtils.getSimpleModelLocation(this);
            ModelBakery.registerItemVariants(this, location);
            ModelLoader.setCustomMeshDefinition(this, stack -> location);
        }

        @Override
        public void onModelBake(ModelBakeEvent event) {
            ITEM_MODEL_BUILDER.newOperation(ModelTemplates.ITEM_HANDHELD);

            for (FaceProvider face : aestheticism.getFaceProviders())
                ITEM_MODEL_BUILDER.addLayer(face.getTexture());

            event.getModelRegistry().putObject(RenderUtils.getSimpleModelLocation(this), ITEM_MODEL_BUILDER.build().getModel());
        }
    }

    public static final class SubstanceHoe extends ItemHoe implements ICustomModel {
        private final Substance substance;
        private final Aestheticism.ObjectAestheticism aestheticism;

        public SubstanceHoe(ASubstanceObject reference, Substance substance) {
            super(ToolMaterial.valueOf(substance.getName()));
            this.aestheticism = new Aestheticism.ObjectAestheticism(reference.getFaces(substance), () -> reference.getTooltips(substance), substance.getAestheticism().isGlint(), null);
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
        @SideOnly(Side.CLIENT)
        public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
            if (aestheticism.getToolTips() != null)
                tooltip.addAll(aestheticism.getToolTips().get());
        }

        @Override
        @SideOnly(Side.CLIENT)
        public boolean hasEffect(ItemStack stack) {
            return aestheticism.isGlint();
        }

        @Override
        @SideOnly(Side.CLIENT)
        public int getItemStackColor(ItemStack stack, int layer) {
            return aestheticism.getFaceProviders()[layer].getColor();
        }

        @Override
        public void addTextures(Set<ResourceLocation> textures) {
            for (FaceProvider faceProvider : aestheticism.getFaceProviders())
                textures.add(faceProvider.getTexture());
        }

        @Override
        public void onModelRegister() {
            ModelResourceLocation location = RenderUtils.getSimpleModelLocation(this);
            ModelBakery.registerItemVariants(this, location);
            ModelLoader.setCustomMeshDefinition(this, stack -> location);
        }

        @Override
        public void onModelBake(ModelBakeEvent event) {
            ITEM_MODEL_BUILDER.newOperation(ModelTemplates.ITEM_HANDHELD);

            for (FaceProvider face : aestheticism.getFaceProviders())
                ITEM_MODEL_BUILDER.addLayer(face.getTexture());

            event.getModelRegistry().putObject(RenderUtils.getSimpleModelLocation(this), ITEM_MODEL_BUILDER.build().getModel());
        }
    }

    public static final class SubstancePickaxe extends ItemPickaxe implements ICustomModel {
        private final Substance substance;
        private final Aestheticism.ObjectAestheticism aestheticism;

        public SubstancePickaxe(ASubstanceObject reference, Substance substance) {
            super(ToolMaterial.valueOf(substance.getName()));
            this.aestheticism = new Aestheticism.ObjectAestheticism(reference.getFaces(substance), () -> reference.getTooltips(substance), substance.getAestheticism().isGlint(), null);
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
        @SideOnly(Side.CLIENT)
        public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
            if (aestheticism.getToolTips() != null)
                tooltip.addAll(aestheticism.getToolTips().get());
        }

        @Override
        @SideOnly(Side.CLIENT)
        public boolean hasEffect(ItemStack stack) {
            return aestheticism.isGlint();
        }

        @Override
        @SideOnly(Side.CLIENT)
        public int getItemStackColor(ItemStack stack, int layer) {
            return aestheticism.getFaceProviders()[layer].getColor();
        }

        @Override
        public void addTextures(Set<ResourceLocation> textures) {
            for (FaceProvider faceProvider : aestheticism.getFaceProviders())
                textures.add(faceProvider.getTexture());
        }

        @Override
        public void onModelRegister() {
            ModelResourceLocation location = RenderUtils.getSimpleModelLocation(this);
            ModelBakery.registerItemVariants(this, location);
            ModelLoader.setCustomMeshDefinition(this, stack -> location);
        }

        @Override
        public void onModelBake(ModelBakeEvent event) {
            ITEM_MODEL_BUILDER.newOperation(ModelTemplates.ITEM_HANDHELD);

            for (FaceProvider face : aestheticism.getFaceProviders())
                ITEM_MODEL_BUILDER.addLayer(face.getTexture());

            event.getModelRegistry().putObject(RenderUtils.getSimpleModelLocation(this), ITEM_MODEL_BUILDER.build().getModel());
        }
    }

    public static final class SubstanceShovel extends ItemSpade implements ICustomModel {
        private final Substance substance;
        private final Aestheticism.ObjectAestheticism aestheticism;

        public SubstanceShovel(ASubstanceObject reference, Substance substance) {
            super(ToolMaterial.valueOf(substance.getName()));
            this.aestheticism = new Aestheticism.ObjectAestheticism(reference.getFaces(substance), () -> reference.getTooltips(substance), substance.getAestheticism().isGlint(), null);
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
        @SideOnly(Side.CLIENT)
        public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
            if (aestheticism.getToolTips() != null)
                tooltip.addAll(aestheticism.getToolTips().get());
        }

        @Override
        @SideOnly(Side.CLIENT)
        public boolean hasEffect(ItemStack stack) {
            return aestheticism.isGlint();
        }

        @Override
        @SideOnly(Side.CLIENT)
        public int getItemStackColor(ItemStack stack, int layer) {
            return aestheticism.getFaceProviders()[layer].getColor();
        }

        @Override
        public void addTextures(Set<ResourceLocation> textures) {
            for (FaceProvider faceProvider : aestheticism.getFaceProviders())
                textures.add(faceProvider.getTexture());
        }

        @Override
        public void onModelRegister() {
            ModelResourceLocation location = RenderUtils.getSimpleModelLocation(this);
            ModelBakery.registerItemVariants(this, location);
            ModelLoader.setCustomMeshDefinition(this, stack -> location);
        }

        @Override
        public void onModelBake(ModelBakeEvent event) {
            ITEM_MODEL_BUILDER.newOperation(ModelTemplates.ITEM_HANDHELD);

            for (FaceProvider face : aestheticism.getFaceProviders())
                ITEM_MODEL_BUILDER.addLayer(face.getTexture());

            event.getModelRegistry().putObject(RenderUtils.getSimpleModelLocation(this), ITEM_MODEL_BUILDER.build().getModel());
        }
    }

    public static final class SubstanceSword extends ItemSword implements ICustomModel {
        private final Substance substance;
        private final Aestheticism.ObjectAestheticism aestheticism;

        public SubstanceSword(ASubstanceObject reference, Substance substance) {
            super(ToolMaterial.valueOf(substance.getName()));
            this.aestheticism = new Aestheticism.ObjectAestheticism(reference.getFaces(substance), () -> reference.getTooltips(substance), substance.getAestheticism().isGlint(), null);
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
        @SideOnly(Side.CLIENT)
        public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
            if (aestheticism.getToolTips() != null)
                tooltip.addAll(aestheticism.getToolTips().get());
        }

        @Override
        @SideOnly(Side.CLIENT)
        public boolean hasEffect(ItemStack stack) {
            return aestheticism.isGlint();
        }

        @Override
        @SideOnly(Side.CLIENT)
        public int getItemStackColor(ItemStack stack, int layer) {
            return aestheticism.getFaceProviders()[layer].getColor();
        }

        @Override
        @SideOnly(Side.CLIENT)
        public void addTextures(Set<ResourceLocation> textures) {
            for (FaceProvider faceProvider : aestheticism.getFaceProviders())
                textures.add(faceProvider.getTexture());
        }

        @Override
        @SideOnly(Side.CLIENT)
        public void onModelRegister() {
            ModelResourceLocation location = RenderUtils.getSimpleModelLocation(this);
            ModelBakery.registerItemVariants(this, location);
            ModelLoader.setCustomMeshDefinition(this, stack -> location);
        }

        @Override
        @SideOnly(Side.CLIENT)
        public void onModelBake(ModelBakeEvent event) {
            ITEM_MODEL_BUILDER.newOperation(ModelTemplates.ITEM_HANDHELD);

            for (FaceProvider face : aestheticism.getFaceProviders())
                ITEM_MODEL_BUILDER.addLayer(face.getTexture());

            event.getModelRegistry().putObject(RenderUtils.getSimpleModelLocation(this), ITEM_MODEL_BUILDER.build().getModel());
        }
    }
}
