package fr.cyrilneveu.craftorium.common.tier;

import fr.cyrilneveu.craftorium.api.item.behaviour.IItemBehaviour;
import fr.cyrilneveu.craftorium.api.item.behaviour.ItemEnergyStorageBehaviour;
import fr.cyrilneveu.craftorium.api.render.FaceProvider;
import fr.cyrilneveu.craftorium.api.tier.Tier;
import fr.cyrilneveu.craftorium.api.tier.object.ATierObject;
import fr.cyrilneveu.craftorium.api.tier.object.ATierObjectBuilder;
import fr.cyrilneveu.craftorium.api.tier.object.TierItem;
import fr.cyrilneveu.craftorium.api.utils.Utils;
import fr.cyrilneveu.craftorium.common.config.Settings;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

import java.util.Collections;
import java.util.List;

import static fr.cyrilneveu.craftorium.CraftoriumTags.MODID;
import static fr.cyrilneveu.craftorium.api.Registries.ITEMS_REGISTRY;
import static fr.cyrilneveu.craftorium.api.Registries.TIER_ITEMS_REGISTRY;
import static fr.cyrilneveu.craftorium.api.utils.RenderUtils.WHITE_COLOR;
import static fr.cyrilneveu.craftorium.api.utils.Utils.NO_BEHAVIOUR;
import static fr.cyrilneveu.craftorium.common.inventory.CreativeTabs.COMMON;

public final class TiersObjects {
    public static ATierObject.TierItem BATTERY;
    public static ATierObject.TierItem EMITTER;
    public static ATierObject.TierItem HEAT_EXCHANGER;
    public static ATierObject.TierItem MOTOR;
    public static ATierObject.TierItem PISTON;
    public static ATierObject.TierItem PUMP;
    public static ATierObject.TierItem ROBOT_ARM;
    public static ATierObject.TierItem SCANNER;
    public static ATierObject.TierItem SENSOR;

    public static void init() {
        if (TIER_ITEMS_REGISTRY.isInitialized())
            return;

        TIER_ITEMS_REGISTRY.initialize();

        BATTERY = createItem("battery").provider(TiersObjects::createStandalone).behaviours(TiersObjects::energyStorage).build();
        EMITTER = createItem("emitter").build();
        HEAT_EXCHANGER = createItem("heat_exchanger").build();
        MOTOR = createItem("motor").build();
        PISTON = createItem("piston").build();
        PUMP = createItem("pump").build();
        ROBOT_ARM = createItem("robot_arm").build();
        SCANNER = createItem("scanner").provider(TiersObjects::createStandalone).behaviours(TiersObjects::energyStorage).build();
        SENSOR = createItem("sensor").build();
    }

    public static void close() {
        TIER_ITEMS_REGISTRY.order().close();
    }

    private static ATierObjectBuilder.TierItemBuilder createItem(String name) {
        ATierObjectBuilder.TierItemBuilder builder = new ATierObjectBuilder.TierItemBuilder(name);
        builder.provider(TiersObjects::createItem);
        builder.faces(TiersObjects::itemFaces);
        builder.tooltips(TiersObjects::defaultTooltips);
        builder.behaviours(TiersObjects::noBehaviours);

        return builder;
    }

    private static void createStandalone(ATierObject reference, Tier tier) {
        TierItem item = new TierItem(reference, tier);
        item.setMaxStackSize(1);

        createItem(reference, tier, item);
    }

    private static void createItem(ATierObject reference, Tier tier) {
        TierItem item = new TierItem(reference, tier);

        createItem(reference, tier, item);
    }

    private static void createItem(ATierObject reference, Tier tier, Item item) {
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

    public static IItemBehaviour[] noBehaviours(ATierObject reference, Tier tier) {
        return NO_BEHAVIOUR;
    }

    public static IItemBehaviour[] energyStorage(ATierObject reference, Tier tier) {
        IItemBehaviour[] behaviours = new IItemBehaviour[1];
        behaviours[0] = new ItemEnergyStorageBehaviour(null, (int) (Settings.balancingSettings.batteryBaseStorage * tier.getStorage().getEnergyBuffer()), (int) (Settings.balancingSettings.batteryBaseTransfer * tier.getStorage().getEnergyIO()));
        return behaviours;
    }
}
