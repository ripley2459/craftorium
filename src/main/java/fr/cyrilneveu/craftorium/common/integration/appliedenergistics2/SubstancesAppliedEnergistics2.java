package fr.cyrilneveu.craftorium.common.integration.appliedenergistics2;

import fr.cyrilneveu.craftorium.api.substance.RegisterSubstancesEvent;
import fr.cyrilneveu.craftorium.api.substance.SubstanceBuilder;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static fr.cyrilneveu.craftorium.common.substance.SubstancesObjects.*;

@Mod.EventBusSubscriber
public final class SubstancesAppliedEnergistics2 {
    @SubscribeEvent
    public static void onRegisterSubstances(RegisterSubstancesEvent event) {
        if (!Loader.isModLoaded("appliedenergistics2")) return;
        new SubstanceBuilder("certus_quartz")
                .packageGem()
                .veinMember()
                .overrides(GEM, "appliedenergistics2:material")
                .tools(3.0f, 1.0f, 250, 2, 15)
                .color(0xFFafd4ea)
                .style("gem")
                .shiny()
                .build();
        new SubstanceBuilder("charged_certus_quartz")
                .packageGem()
                .veinMember()
                .overrides(GEM, "appliedenergistics2:material:1")
                .color(0xFFb7dfe2)
                .style("gem")
                .build();
        new SubstanceBuilder("fluix")
                .packageGem()
                .overrides(GEM, "appliedenergistics2:material:7")
                .color(0xFF614eab)
                .style("gem")
                .build();

        new SubstanceBuilder("pure_certus_quartz")
                .packageGem()
                .overrides(GEM, "appliedenergistics2:material:10")
                .color(0xFFa5cce2)
                .style("gem")
                .build();
        new SubstanceBuilder("pure_quartz")
                .packageGem()
                .overrides(GEM, "appliedenergistics2:material:11")
                .color(0xFFc9c5b9)
                .style("gem")
                .build();
        new SubstanceBuilder("pure_fluix")
                .packageGem()
                .overrides(GEM, "appliedenergistics2:material:12")
                .color(0xFF614eab)
                .style("gem")
                .shiny()
                .build();
    }
}
