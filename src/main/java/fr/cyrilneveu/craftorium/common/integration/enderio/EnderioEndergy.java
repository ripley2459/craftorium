package fr.cyrilneveu.craftorium.common.integration.enderio;

import fr.cyrilneveu.craftorium.api.substance.RegisterSubstancesEvent;
import fr.cyrilneveu.craftorium.api.substance.Substance;
import fr.cyrilneveu.craftorium.api.substance.SubstanceBuilder;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static fr.cyrilneveu.craftorium.common.integration.transverse.MoreSubstances.ENERGETIC_BLEND;
import static fr.cyrilneveu.craftorium.common.substance.Substances.ENDER;
import static fr.cyrilneveu.craftorium.common.substance.Substances.SILVER;

@Mod.EventBusSubscriber
public class EnderioEndergy {
    public static Substance CRUDE_STEEL;
    public static Substance CRYSTALLINE_ALLOY;
    public static Substance MELODIC_ALLOY;
    public static Substance STELLAR_ALLOY;
    public static Substance CRYSTALLINE_PINK_SLIME;
    public static Substance ENERGETIC_SILVER;
    public static Substance VIVID_ALLOY;

    @SubscribeEvent
    public static void register(RegisterSubstancesEvent event) {
        if (!Loader.isModLoaded("enderioendergy"))
            return;

        CRUDE_STEEL = new SubstanceBuilder("crude_steel")
                .packageMetalloid()
                .color(0xFFcca0f2)
                .build();
        CRYSTALLINE_ALLOY = new SubstanceBuilder("crystalline_alloy")
                .packageMetalloid()
                .color(0xFFcca0f2)
                .build();
        MELODIC_ALLOY = new SubstanceBuilder("melodic_alloy")
                .packageMetalloid()
                .color(0xFFcca0f2)
                .build();
        STELLAR_ALLOY = new SubstanceBuilder("stellar_alloy")
                .packageMetalloid()
                .color(0xFFcca0f2)
                .build();
        CRYSTALLINE_PINK_SLIME = new SubstanceBuilder("crystalline_pink_slime")
                .packageMetalloid()
                .color(0xFFcca0f2)
                .build();
        ENERGETIC_SILVER = new SubstanceBuilder("energetic_silver")
                .packageMetalloid()
                .composition(SILVER, 1, ENERGETIC_BLEND, 1)
                .color(0xFFcca0f2)
                .build();
        VIVID_ALLOY = new SubstanceBuilder("vivid_alloy")
                .packageMetalloid()
                .composition(ENERGETIC_SILVER, 1, ENDER, 1)
                .color(0xFFcca0f2)
                .build();
    }
}
