package fr.cyrilneveu.craftorium.common.integration.crafttweaker;

import com.google.common.base.Preconditions;
import crafttweaker.CraftTweakerAPI;
import crafttweaker.annotations.ZenRegister;
import fr.cyrilneveu.craftorium.api.substance.Element;
import fr.cyrilneveu.craftorium.api.substance.Substance;
import fr.cyrilneveu.craftorium.api.substance.SubstanceBuilder;
import fr.cyrilneveu.craftorium.api.substance.object.ASubstanceObject;
import fr.cyrilneveu.craftorium.api.substance.property.ISubstanceProperty;
import fr.cyrilneveu.craftorium.api.substance.property.SubstanceProperties;
import net.minecraft.block.SoundType;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import static fr.cyrilneveu.craftorium.CraftoriumTags.MODID;
import static fr.cyrilneveu.craftorium.api.Registries.*;

@ZenClass("mods." + MODID + ".substance.Builder")
@ZenRegister
public final class SubstanceBuilderCT {
    SubstanceBuilder builder;

    public SubstanceBuilderCT(String name) {
        this.builder = new SubstanceBuilder(name);
    }

    @ZenMethod
    public SubstanceBuilderCT composition(Object... composition) {
        builder.composition(composition);
        return this;
    }

    @ZenMethod
    public SubstanceBuilderCT possible(Object... possible) {
        builder.possible(possible);
        return this;
    }

    @ZenMethod
    public SubstanceBuilderCT element(int atomicNumber, String symbol, String name, String group, double atomicMass) {
        builder.element(atomicNumber, symbol, name, Element.EGroup.valueOf(group.toUpperCase()), atomicMass);
        return this;
    }

    public SubstanceBuilderCT property(SubstanceProperties.KeyProperties key, ISubstanceProperty value) {
        builder.property(key, value);
        return this;
    }

    @ZenMethod
    public SubstanceBuilderCT tools(float speed, float damage, int durability, int harvestLevel, int enchantability) {
        builder.tools(speed, damage, durability, harvestLevel, enchantability);
        return this;
    }

    @ZenMethod
    public SubstanceBuilderCT toughness(float hardness, float resistance, String toolClass, int harvestLevel) {
        builder.toughness(hardness, resistance, toolClass, harvestLevel);
        return this;
    }

    @ZenMethod
    public SubstanceBuilderCT temperature(float meltingPoint, float boilingPoint) {
        builder.temperature(meltingPoint, boilingPoint);
        return this;
    }

    @ZenMethod
    public SubstanceBuilderCT temperatureAverage() {
        builder.temperatureAverage();
        return this;
    }

    @ZenMethod
    public SubstanceBuilderCT veinMember() {
        builder.veinMember();
        return this;
    }

    @ZenMethod
    public SubstanceBuilderCT packageHalogen() {
        builder.packageHalogen();
        return this;
    }

    @ZenMethod
    public SubstanceBuilderCT packageNobleGas() {
        builder.packageNobleGas();
        return this;
    }

    @ZenMethod
    public SubstanceBuilderCT packageAlkaliMetal() {
        builder.packageAlkaliMetal();
        return this;
    }

    @ZenMethod
    public SubstanceBuilderCT packageAlkalineEarthMetal() {
        builder.packageAlkalineEarthMetal();
        return this;
    }

    @ZenMethod
    public SubstanceBuilderCT packageMetalloid() {
        builder.packageMetalloid();
        return this;
    }

    @ZenMethod
    public SubstanceBuilderCT packageNonMetal() {
        builder.packageNonMetal();
        return this;
    }

    @ZenMethod
    public SubstanceBuilderCT packagePostTransitionMetal() {
        builder.packagePostTransitionMetal();
        return this;
    }

    @ZenMethod
    public SubstanceBuilderCT packageTransitionMetal() {
        builder.packageTransitionMetal();
        return this;
    }

    @ZenMethod
    public SubstanceBuilderCT packageLanthanide() {
        builder.packageLanthanide();
        return this;
    }

    @ZenMethod
    public SubstanceBuilderCT packageActinide() {
        builder.packageActinide();
        return this;
    }

    @ZenMethod
    public SubstanceBuilderCT packageMetalExtended() {
        builder.packageMetalExtended();
        return this;
    }

    @ZenMethod
    public SubstanceBuilderCT packageGem() {
        builder.packageGem();
        return this;
    }

    @ZenMethod
    public SubstanceBuilderCT packageMineral() {
        builder.packageMineral();
        return this;
    }

    @ZenMethod
    public SubstanceBuilderCT items(String... items) {
        ASubstanceObject.SubstanceItemDefinition[] items1;
        if (items.length != 0) {
            items1 = new ASubstanceObject.SubstanceItemDefinition[items.length];
            for (int i = 0; i < items.length; i++) {
                Preconditions.checkArgument(SUBSTANCE_ITEMS_REGISTRY.contains(items[i]));
                items1[i] = SUBSTANCE_ITEMS_REGISTRY.get(items[i]);
            }
        } else items1 = new ASubstanceObject.SubstanceItemDefinition[0];

        builder.items(items1);
        return this;
    }

    @ZenMethod
    public SubstanceBuilderCT tools(String... tools) {
        ASubstanceObject.SubstanceToolDefinition[] tools1;
        if (tools.length != 0) {
            tools1 = new ASubstanceObject.SubstanceToolDefinition[tools.length];
            for (int i = 0; i < tools.length; i++) {
                Preconditions.checkArgument(SUBSTANCE_TOOLS_REGISTRY.contains(tools[i]));
                tools1[i] = SUBSTANCE_TOOLS_REGISTRY.get(tools[i]);
            }
        } else tools1 = new ASubstanceObject.SubstanceToolDefinition[0];

        builder.tools(tools1);
        return this;
    }

    @ZenMethod
    public SubstanceBuilderCT blocks(String... blocks) {
        ASubstanceObject.SubstanceBlockDefinition[] blocks1;
        if (blocks.length != 0) {
            blocks1 = new ASubstanceObject.SubstanceBlockDefinition[blocks.length];
            for (int i = 0; i < blocks.length; i++) {
                Preconditions.checkArgument(SUBSTANCE_BLOCKS_REGISTRY.contains(blocks[i]));
                blocks1[i] = SUBSTANCE_BLOCKS_REGISTRY.get(blocks[i]);
            }
        } else blocks1 = new ASubstanceObject.SubstanceBlockDefinition[0];

        builder.blocks(blocks1);
        return this;
    }

    @ZenMethod
    public SubstanceBuilderCT fluids(String... fluids) {
        ASubstanceObject.SubstanceFluidDefinition[] fluids1;
        if (fluids.length != 0) {
            fluids1 = new ASubstanceObject.SubstanceFluidDefinition[fluids.length];
            for (int i = 0; i < fluids.length; i++) {
                Preconditions.checkArgument(SUBSTANCE_FLUIDS_REGISTRY.contains(fluids[i]));
                fluids1[i] = SUBSTANCE_FLUIDS_REGISTRY.get(fluids[i]);
            }
        } else fluids1 = new ASubstanceObject.SubstanceFluidDefinition[0];

        builder.fluids(fluids1);
        return this;
    }

    @ZenMethod
    public SubstanceBuilderCT fuel(int duration) {
        builder.fuel(duration);
        return this;
    }

    @ZenMethod
    public SubstanceBuilderCT color(int color) {
        return this.color(color, color, color);
    }

    @ZenMethod
    public SubstanceBuilderCT color(int baseColor, int oreColor, int fluidColor) {
        builder.color(baseColor, oreColor, fluidColor);
        return this;
    }

    @ZenMethod
    public SubstanceBuilderCT colorAverage() {
        builder.colorAverage();
        return this;
    }

    @ZenMethod
    public SubstanceBuilderCT shiny() {
        builder.shiny();
        return this;
    }

    @ZenMethod
    public SubstanceBuilderCT glow() {
        builder.glint();
        return this;
    }

    @ZenMethod
    public SubstanceBuilderCT style(String style) {
        builder.style(style);
        return this;
    }

    @ZenMethod
    public SubstanceBuilderCT sound(String soundName) {
        switch (soundName) {
            case "metal" -> builder.sound(SoundType.METAL);
            case "wood" -> builder.sound(SoundType.WOOD);
            case "stone" -> builder.sound(SoundType.STONE);
            case "sand" -> builder.sound(SoundType.SAND);
            default -> CraftTweakerAPI.logError("This type of sound does not exists: " + soundName);
        }

        return this;
    }

    @ZenMethod
    public Substance build() {
        return builder.build();
    }
}
