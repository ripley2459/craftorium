package fr.cyrilneveu.craftorium.api.fluid;

import fr.cyrilneveu.craftorium.api.render.FaceProvider;
import fr.cyrilneveu.craftorium.common.inventory.CreativeTabs;
import net.minecraft.block.material.Material;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidRegistry;

import static fr.cyrilneveu.craftorium.CraftoriumTags.MODID;
import static fr.cyrilneveu.craftorium.api.Registries.BLOCKS_REGISTRY;
import static fr.cyrilneveu.craftorium.api.Registries.FLUIDS_REGISTRY;
import static fr.cyrilneveu.craftorium.api.utils.RenderUtils.ERROR_COLOR;

public class FluidBuilder {
    private String name;
    private ResourceLocation registryName;
    private String translation;
    private FaceProvider still;
    private FaceProvider flowing;
    private net.minecraft.creativetab.CreativeTabs creativeTab = CreativeTabs.COMMON;
    private boolean isGaseous = false;
    private int color = ERROR_COLOR;
    private int luminosity = 0;
    private int density = 1000;
    private int viscosity = 1000;
    private int temperature = 300;
    private Material material = Material.LAVA;

    public FluidBuilder(String name) {
        this.name = name;
        setRegistryName(name);
        setTranslation(name);
        setStill(name);
        setFlowing(name);
    }

    public FluidBuilder setRegistryName(String name) {
        this.registryName = new ResourceLocation(MODID, name);
        return this;
    }

    public FluidBuilder setTranslation(String name) {
        this.translation = String.join(".", MODID, name);
        return this;
    }

    public FluidBuilder setCreativeTab(net.minecraft.creativetab.CreativeTabs creativeTab) {
        this.creativeTab = creativeTab;
        return this;
    }

    public FluidBuilder setStill(String path) {
        return setStill(path, ERROR_COLOR);
    }

    public FluidBuilder setStill(String path, int color) {
        still = new FaceProvider(new ResourceLocation(MODID, String.join("/", "fluids", path)), color);
        this.color = color;
        return this;
    }

    public FluidBuilder setFlowing(String path) {
        return setFlowing(path, ERROR_COLOR);
    }

    public FluidBuilder setFlowing(String path, int color) {
        flowing = new FaceProvider(new ResourceLocation(MODID, String.join("/", "fluids", path)), color);
        this.color = color;
        return this;
    }

    public FluidBuilder setGaseous() {
        isGaseous = true;
        return this;
    }

    public FluidBuilder setLuminosity(int luminosity) {
        this.luminosity = luminosity;
        return this;
    }

    public FluidBuilder setDensity(int density) {
        this.density = density;
        return this;
    }

    public FluidBuilder setViscosity(int viscosity) {
        this.viscosity = viscosity;
        return this;
    }

    public FluidBuilder setTemperature(int temperature) {
        this.temperature = temperature;
        return this;
    }

    public FluidBuilder setMaterial(Material material) {
        this.material = material;
        return this;
    }

    public CustomFluid build() {
        CustomFluid fluid = new CustomFluid(name, still, flowing, color);
        fluid.setGaseous(isGaseous).setDensity(density).setViscosity(viscosity).setLuminosity(luminosity).setTemperature(temperature);
        fluid.setUnlocalizedName(String.join(".", translation, "name"));

        FLUIDS_REGISTRY.put(name, fluid);
        FluidRegistry.registerFluid(fluid);
        FluidRegistry.addBucketForFluid(fluid);

        CustomFluid.CustomFluidBlock block = new CustomFluid.CustomFluidBlock(fluid, material);
        block.setRegistryName(registryName);
        block.setTranslationKey(translation);
        block.setCreativeTab(creativeTab);

        BLOCKS_REGISTRY.put(name, block);

        return fluid;
    }
}
