package fr.cyrilneveu.craftorium.common.integration.draconicevolution;

import fr.cyrilneveu.craftorium.api.substance.RegisterSubstancesEvent;
import fr.cyrilneveu.craftorium.api.substance.SubstanceBuilder;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public final class SubstancesDraconicEvolution {
    @SubscribeEvent
    public static void onRegisterSubstances(RegisterSubstancesEvent event) {
        if (!Loader.isModLoaded("draconicevolution")) return;
        new SubstanceBuilder("draconium")
                .packageMetalExtended()
                .veinMember()
                .color(0xFFcca0f2)
                .style("metal")
                .build();
        new SubstanceBuilder("awakened_draconium")
                .packageMetalExtended()
                .color(0xFFffd200)
                .style("metal")
                .build();
    }
}
