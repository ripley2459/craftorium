package fr.cyrilneveu.craftorium.common.tier;

import fr.cyrilneveu.craftorium.api.render.FaceProvider;
import fr.cyrilneveu.craftorium.api.tier.Tier;
import fr.cyrilneveu.craftorium.api.tier.object.ATierObject;
import fr.cyrilneveu.craftorium.api.tier.object.ATierObjectBuilder;
import fr.cyrilneveu.craftorium.api.tier.object.TierItem;
import fr.cyrilneveu.craftorium.api.utils.Registry;
import fr.cyrilneveu.craftorium.api.utils.Utils;
import fr.cyrilneveu.craftorium.common.config.Settings;
import net.minecraft.util.ResourceLocation;

import java.util.Collections;
import java.util.List;

import static fr.cyrilneveu.craftorium.CraftoriumTags.MODID;
import static fr.cyrilneveu.craftorium.api.Registries.ITEMS_REGISTRY;
import static fr.cyrilneveu.craftorium.api.utils.Utils.WHITE_COLOR;
import static fr.cyrilneveu.craftorium.common.inventory.CreativeTabs.COMMON;

public final class TiersObjects {
    public static final Registry<String, ATierObject.TierItem> TIER_ITEMS_REGISTRY = new Registry<>();

    public static final ATierObject.TierItem BATTERY = createItem("battery").build();
    public static final ATierObject.TierItem EMITTER = createItem("emitter").build();
    public static final ATierObject.TierItem HEAT_EXCHANGER = createItem("heat_exchanger").build();
    public static final ATierObject.TierItem LARGE_BATTERY = createItem("large_battery").build();
    public static final ATierObject.TierItem MOTOR = createItem("motor").build();
    public static final ATierObject.TierItem PISTON = createItem("piston").build();
    public static final ATierObject.TierItem PUMP = createItem("pump").build();
    public static final ATierObject.TierItem ROBOT_ARM = createItem("robot_arm").build();
    public static final ATierObject.TierItem SCANNER = createItem("scanner").build();
    public static final ATierObject.TierItem SENSOR = createItem("sensor").build();

    private static ATierObjectBuilder.TierItemBuilder createItem(String name) {
        ATierObjectBuilder.TierItemBuilder builder = new ATierObjectBuilder.TierItemBuilder(name);
        builder.provider(TiersObjects::createItem);
        builder.faces(TiersObjects::itemFaces);
        builder.tooltips(TiersObjects::defaultTooltips);

        return builder;
    }

    private static void createItem(ATierObject reference, Tier tier) {
        TierItem item = new TierItem(reference, tier);
        item.setRegistryName(reference.getName(tier));
        item.setTranslationKey(String.join(".", MODID, reference.getName(null)));
        item.setCreativeTab(COMMON);

        ITEMS_REGISTRY.put(reference.getName(tier), item);
    }

    public static FaceProvider[] itemFaces(ATierObject reference, Tier tier) {
        FaceProvider[] faces = new FaceProvider[2];
        faces[0] = new FaceProvider(new ResourceLocation(MODID, String.join("/", "items", "tiers", "items", reference.getName(null))), tier.getAestheticism().getBaseColor());
        faces[1] = new FaceProvider(new ResourceLocation(MODID, String.join("/", "items", "tiers", "items", reference.getName(null)).concat("_overlay")), WHITE_COLOR);
        return faces;
    }

    public static List<String> defaultTooltips(ATierObject reference, Tier tier) {
        return Settings.substancesSettings.showAdvancedTooltips ? Collections.singletonList(Utils.localise("tooltip.craftorium.tier.name", tier.getDisplayName())) : Collections.emptyList();
    }
}
