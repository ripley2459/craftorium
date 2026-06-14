package fr.cyrilneveu.craftorium.common.integration.transverse;

import fr.cyrilneveu.craftorium.api.substance.RegisterSubstancesEvent;
import fr.cyrilneveu.craftorium.api.substance.Substance;
import fr.cyrilneveu.craftorium.api.substance.SubstanceBuilder;
import fr.cyrilneveu.craftorium.api.utils.Utils;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static fr.cyrilneveu.craftorium.common.substance.Substances.*;

@Mod.EventBusSubscriber
public final class MoreSubstances {
    public static Substance ENERGETIC_BLEND;
    public static Substance DIMENSIONAL_BLEND;
    public static Substance AMETHYST;
    public static Substance RUBY;
    public static Substance PERIDOT;
    public static Substance SAPPHIRE;

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onRegisterSubstances(RegisterSubstancesEvent event) {
        if (shouldRegister("biomesoplenty", "projectred-core")) {
            RUBY = new SubstanceBuilder("ruby")
                    .packageGem()
                    .veinMember()
                    .composition(ALUMINUM, 2, CHROMIUM, 1, OXYGEN, 3)
                    .style("gem")
                    .shiny()
                    .build();
            PERIDOT = new SubstanceBuilder("peridot")
                    .packageGem()
                    .veinMember()
                    .composition(MAGNESIUM, 2, IRON, 2, SILICON, 1, OXYGEN, 4)
                    .style("gem")
                    .shiny()
                    .build();
        }

        if (shouldRegister("biomesoplenty", "projectred-core", "iceandfire")) {
            SAPPHIRE = new SubstanceBuilder("sapphire")
                    .packageGem()
                    .veinMember()
                    .composition(ALUMINUM, 2, OXYGEN, 3, TITANIUM, 1, IRON, 1)
                    .style("gem")
                    .shiny()
                    .build();
        }

        if (shouldRegister("biomesoplenty", "iceandfire")) {
            AMETHYST = new SubstanceBuilder("amethyst")
                    .packageGem()
                    .veinMember()
                    .composition(SILICON, 1, OXYGEN, 2, IRON, 1)
                    .style("gem")
                    .shiny()
                    .build();
        }

        if (shouldRegister("nuclearcraft", "enderioendergy")) {
            ENERGETIC_BLEND = new SubstanceBuilder("energetic_blend")
                    .packageMineral()
                    .composition(REDSTONE, 1, GLOWSTONE, 1)
                    .shiny()
                    .build();
            DIMENSIONAL_BLEND = new SubstanceBuilder("dimensional_blend")
                    .packageMineral()
                    .composition(END, 1, OBSIDIAN, 4)
                    .shiny()
                    .build();
        }

        if (shouldRegister("nuclearcraft", "thermalfoundation")) {
            new SubstanceBuilder("enderium")
                    .packageMetalExtended()
                    .composition(LEAD, 3, PLATINUM, 1, ENDER, 4)
                    .build();
        }
    }

    private static boolean shouldRegister(String... modIds) {
        return Utils.atLeastOne(modIds, Loader::isModLoaded);
    }
}
