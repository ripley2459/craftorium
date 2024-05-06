package fr.cyrilneveu.craftorium.api.substance.object;

import fr.cyrilneveu.craftorium.api.inventory.OreStack;
import fr.cyrilneveu.craftorium.api.property.Aestheticism;
import fr.cyrilneveu.craftorium.api.render.FaceProvider;
import fr.cyrilneveu.craftorium.api.render.ICustomModel;
import fr.cyrilneveu.craftorium.api.render.ModelTemplates;
import fr.cyrilneveu.craftorium.api.substance.Substance;
import fr.cyrilneveu.craftorium.api.utils.Utils;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.color.IItemColor;
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

import static fr.cyrilneveu.craftorium.api.utils.Utils.ITEM_MODEL_BUILDER;
import static fr.cyrilneveu.craftorium.common.substance.SubstancesObjects.INGOT;

public class SubstanceTool extends SubstanceItem {
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

    public static final class SubstanceAxe extends ItemAxe implements ICustomModel, IItemColor {
        private final Substance substance;
        private final Aestheticism.ObjectAestheticism aestheticism;

        public SubstanceAxe(ASubstanceObject reference, Substance substance) {
            super(ToolMaterial.valueOf(substance.getName()), substance.getEfficiency().getDamage(), substance.getEfficiency().getSpeed());
            this.aestheticism = new Aestheticism.ObjectAestheticism(reference.getFaces(substance), () -> reference.getTooltips(substance), substance.getAestheticism().isGlint());
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
        public int colorMultiplier(ItemStack stack, int tintIndex) {
            return aestheticism.getFaceProviders()[tintIndex].getColor();
        }

        @Override
        public void addTextures(Set<ResourceLocation> textures) {
            for (FaceProvider faceProvider : aestheticism.getFaceProviders())
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
            ITEM_MODEL_BUILDER.newOperation(ModelTemplates.ITEM_HANDHELD);

            for (FaceProvider face : aestheticism.getFaceProviders())
                ITEM_MODEL_BUILDER.addLayer(face.getTexture());

            event.getModelRegistry().putObject(Utils.getSimpleModelLocation(this), ITEM_MODEL_BUILDER.build().getModel());
        }
    }

    public static final class SubstanceHoe extends ItemHoe implements ICustomModel, IItemColor {
        private final Substance substance;
        private final Aestheticism.ObjectAestheticism aestheticism;

        public SubstanceHoe(ASubstanceObject reference, Substance substance) {
            super(ToolMaterial.valueOf(substance.getName()));
            this.aestheticism = new Aestheticism.ObjectAestheticism(reference.getFaces(substance), () -> reference.getTooltips(substance), substance.getAestheticism().isGlint());
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
        public int colorMultiplier(ItemStack stack, int tintIndex) {
            return aestheticism.getFaceProviders()[tintIndex].getColor();
        }

        @Override
        public void addTextures(Set<ResourceLocation> textures) {
            for (FaceProvider faceProvider : aestheticism.getFaceProviders())
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
            ITEM_MODEL_BUILDER.newOperation(ModelTemplates.ITEM_HANDHELD);

            for (FaceProvider face : aestheticism.getFaceProviders())
                ITEM_MODEL_BUILDER.addLayer(face.getTexture());

            event.getModelRegistry().putObject(Utils.getSimpleModelLocation(this), ITEM_MODEL_BUILDER.build().getModel());
        }
    }

    public static final class SubstancePickaxe extends ItemPickaxe implements ICustomModel, IItemColor {
        private final Substance substance;
        private final Aestheticism.ObjectAestheticism aestheticism;

        public SubstancePickaxe(ASubstanceObject reference, Substance substance) {
            super(ToolMaterial.valueOf(substance.getName()));
            this.aestheticism = new Aestheticism.ObjectAestheticism(reference.getFaces(substance), () -> reference.getTooltips(substance), substance.getAestheticism().isGlint());
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
        public int colorMultiplier(ItemStack stack, int tintIndex) {
            return aestheticism.getFaceProviders()[tintIndex].getColor();
        }

        @Override
        public void addTextures(Set<ResourceLocation> textures) {
            for (FaceProvider faceProvider : aestheticism.getFaceProviders())
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
            ITEM_MODEL_BUILDER.newOperation(ModelTemplates.ITEM_HANDHELD);

            for (FaceProvider face : aestheticism.getFaceProviders())
                ITEM_MODEL_BUILDER.addLayer(face.getTexture());

            event.getModelRegistry().putObject(Utils.getSimpleModelLocation(this), ITEM_MODEL_BUILDER.build().getModel());
        }
    }

    public static final class SubstanceShovel extends ItemSpade implements ICustomModel, IItemColor {
        private final Substance substance;
        private final Aestheticism.ObjectAestheticism aestheticism;

        public SubstanceShovel(ASubstanceObject reference, Substance substance) {
            super(ToolMaterial.valueOf(substance.getName()));
            this.aestheticism = new Aestheticism.ObjectAestheticism(reference.getFaces(substance), () -> reference.getTooltips(substance), substance.getAestheticism().isGlint());
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
        public int colorMultiplier(ItemStack stack, int tintIndex) {
            return aestheticism.getFaceProviders()[tintIndex].getColor();
        }

        @Override
        public void addTextures(Set<ResourceLocation> textures) {
            for (FaceProvider faceProvider : aestheticism.getFaceProviders())
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
            ITEM_MODEL_BUILDER.newOperation(ModelTemplates.ITEM_HANDHELD);

            for (FaceProvider face : aestheticism.getFaceProviders())
                ITEM_MODEL_BUILDER.addLayer(face.getTexture());

            event.getModelRegistry().putObject(Utils.getSimpleModelLocation(this), ITEM_MODEL_BUILDER.build().getModel());
        }
    }

    public static final class SubstanceSword extends ItemSword implements ICustomModel, IItemColor {
        private final Substance substance;
        private final Aestheticism.ObjectAestheticism aestheticism;

        public SubstanceSword(ASubstanceObject reference, Substance substance) {
            super(ToolMaterial.valueOf(substance.getName()));
            this.aestheticism = new Aestheticism.ObjectAestheticism(reference.getFaces(substance), () -> reference.getTooltips(substance), substance.getAestheticism().isGlint());
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
        public int colorMultiplier(ItemStack stack, int tintIndex) {
            return aestheticism.getFaceProviders()[tintIndex].getColor();
        }

        @Override
        public void addTextures(Set<ResourceLocation> textures) {
            for (FaceProvider faceProvider : aestheticism.getFaceProviders())
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
            ITEM_MODEL_BUILDER.newOperation(ModelTemplates.ITEM_HANDHELD);

            for (FaceProvider face : aestheticism.getFaceProviders())
                ITEM_MODEL_BUILDER.addLayer(face.getTexture());

            event.getModelRegistry().putObject(Utils.getSimpleModelLocation(this), ITEM_MODEL_BUILDER.build().getModel());
        }
    }
}
