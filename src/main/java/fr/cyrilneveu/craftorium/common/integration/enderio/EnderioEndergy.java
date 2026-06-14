package fr.cyrilneveu.craftorium.common.integration.enderio;

import fr.cyrilneveu.craftorium.api.substance.RegisterSubstancesEvent;
import fr.cyrilneveu.craftorium.api.substance.Substance;
import fr.cyrilneveu.craftorium.api.substance.SubstanceBuilder;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static fr.cyrilneveu.craftorium.common.integration.enderio.Enderio.END_STEEL;
import static fr.cyrilneveu.craftorium.common.integration.enderio.Enderio.PIEZALLITY;
import static fr.cyrilneveu.craftorium.common.integration.transverse.MoreSubstances.ENERGETIC_BLEND;
import static fr.cyrilneveu.craftorium.common.substance.Substances.*;

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
                .composition(GRAVEL, 1, STONE, 1, CLAY, 1)
                .build();
        CRYSTALLINE_ALLOY = new SubstanceBuilder("crystalline_alloy")
                .packageMetalloid()
                .composition(PIEZALLITY, 1, GOLD, 1)
                .build();
        MELODIC_ALLOY = new SubstanceBuilder("melodic_alloy")
                .packageMetalloid()
                //.composition(END_STEEL, 1, POPPED_CHORUS, 1)
                .build();
        STELLAR_ALLOY = new SubstanceBuilder("stellar_alloy")
                .packageMetalloid()
                .composition(CLAY, 2, MELODIC_ALLOY, 1, NETHER_STAR, 1)
                .build();
        CRYSTALLINE_PINK_SLIME = new SubstanceBuilder("crystalline_pink_slime")
                .packageMetalloid()
                //.composition(e)
                .build();
        ENERGETIC_SILVER = new SubstanceBuilder("energetic_silver")
                .packageMetalloid()
                .composition(SILVER, 1, ENERGETIC_BLEND, 1)
                .build();
        VIVID_ALLOY = new SubstanceBuilder("vivid_alloy")
                .packageMetalloid()
                .composition(ENERGETIC_SILVER, 1, ENDER, 1)
                .build();
    }
}
