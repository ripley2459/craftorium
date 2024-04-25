package fr.cyrilneveu.craftorium.common.tier;

import fr.cyrilneveu.craftorium.api.tier.Tier;
import fr.cyrilneveu.craftorium.api.tier.object.ATierObject;
import fr.cyrilneveu.craftorium.api.tier.object.ATierObjectBuilder;
import fr.cyrilneveu.craftorium.api.tier.object.TierItem;
import fr.cyrilneveu.craftorium.api.utils.Registry;

import static fr.cyrilneveu.craftorium.CraftoriumTags.MODID;
import static fr.cyrilneveu.craftorium.common.ACommonProxy.ITEMS_REGISTRY;
import static fr.cyrilneveu.craftorium.common.inventory.CreativeTabs.COMMON;

public final class TiersObjects {
    public static final Registry<String, TierItem> TIER_ITEMS_REGISTRY = new Registry<>();

    public static final TierItem BATTERY = createItem("battery").build();
    public static final TierItem EMITTER = createItem("emitter").build();
    public static final TierItem HEAT_EXCHANGER = createItem("heat_exchanger").build();
    public static final TierItem LARGE_BATTERY = createItem("large_battery").build();
    public static final TierItem MOTOR = createItem("motor").build();
    public static final TierItem PISTON = createItem("piston").build();
    public static final TierItem PUMP = createItem("pump").build();
    public static final TierItem ROBOT_ARM = createItem("robot_arm").build();
    public static final TierItem SCANNER = createItem("scanner").build();
    public static final TierItem SENSOR = createItem("sensor").build();

    private static ATierObjectBuilder.TierItemBuilder createItem(String name) {
        ATierObjectBuilder.TierItemBuilder builder = new ATierObjectBuilder.TierItemBuilder(name);
        builder.provider(TiersObjects::createItem);
        builder.faces(TierItem::defaultFaces);
        builder.tooltips(TierItem::defaultTooltips);

        return builder;
    }

    private static void createItem(ATierObject reference, Tier tier) {
        fr.cyrilneveu.craftorium.api.item.TierItem item = new fr.cyrilneveu.craftorium.api.item.TierItem(reference, tier);
        item.setRegistryName(reference.getName(tier));
        item.setTranslationKey(String.join(".", MODID, reference.getName(null)));
        item.setCreativeTab(COMMON);

        ITEMS_REGISTRY.put(reference.getName(tier), item);
    }
}
