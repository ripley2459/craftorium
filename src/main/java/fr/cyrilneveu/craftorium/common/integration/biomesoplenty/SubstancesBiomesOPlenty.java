package fr.cyrilneveu.craftorium.common.integration.biomesoplenty;

import fr.cyrilneveu.craftorium.api.substance.RegisterSubstancesEvent;
import fr.cyrilneveu.craftorium.api.substance.SubstanceBuilder;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static fr.cyrilneveu.craftorium.common.substance.Substances.*;

@Mod.EventBusSubscriber
public final class SubstancesBiomesOPlenty {
    @SubscribeEvent
    public static void onRegisterSubstances(RegisterSubstancesEvent event) {
        if (!Loader.isModLoaded("biomesoplenty")) return;
        new SubstanceBuilder("topaz")
                .packageGem()
                .veinMember()
                .composition(ALUMINUM, 2, SILICON, 1, OXYGEN, 6, IRON, 2, HYDROGEN, 2)
                .color(0xFF1bd61b)
                .style("gem")
                .shiny()
                .build();
        new SubstanceBuilder("tanzanite")
                .packageGem()
                .veinMember()
                .composition(CALCIUM, 2, ALUMINUM, 3, OXYGEN, 13, SILICON, 3, HYDROGEN, 1, VANADIUM, 3)
                .color(0xFF560c6b)
                .style("gem")
                .shiny()
                .build();
        new SubstanceBuilder("malachite")
                .packageGem()
                .veinMember()
                .composition(COPPER, 2, CARBON, 1, OXYGEN, 5, HYDROGEN, 2)
                .color(0xFF4f9e7e)
                .style("gem")
                .shiny()
                .build();
        new SubstanceBuilder("amber")
                .packageGem()
                .veinMember()
                .composition()
                .color(0xFFe2bd4d)
                .style("gem")
                .shiny()
                .build();
    }
}
