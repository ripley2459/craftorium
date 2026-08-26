package fr.cyrilneveu.craftorium.common.integration.bigreactor;

import fr.cyrilneveu.craftorium.api.substance.RegisterSubstancesEvent;
import fr.cyrilneveu.craftorium.api.substance.Substance;
import fr.cyrilneveu.craftorium.api.substance.SubstanceBuilder;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static fr.cyrilneveu.craftorium.common.substance.Substances.*;
import static fr.cyrilneveu.craftorium.common.substance.SubstancesObjects.*;

@Mod.EventBusSubscriber
public class SubstancesBigReactors {
    @SubscribeEvent
    public static void onRegisterSubstances(RegisterSubstancesEvent event) {
        if (!Loader.isModLoaded("bigreactors")) return;

        Substance YELLOWRIUM = new SubstanceBuilder("yellowrium")
                .overrides(INGOT, "bigreactors:ingotyellorium", DUST, "bigreactors:dustyellorium", BLOCK, "bigreactors:blockyellorium")
                .color(0xFFe8fc0c)
                .packageActinide()
                .veinMember()
                .build();
        Substance CYANITE = new SubstanceBuilder("cyanite")
                .overrides(INGOT, "bigreactors:ingotcyanite", DUST, "bigreactors:dustcyanite", BLOCK, "bigreactors:blockcyanite")
                .color(0xFF0ce8fc)
                .packageActinide()
                .veinMember()
                .build();

        Substance BLUTONIUM = new SubstanceBuilder("blutonium")
                .overrides(INGOT, "bigreactors:ingotblutonium", DUST, "bigreactors:dustblutonium", BLOCK, "bigreactors:blockblutonium")
                .color(0xFF240cfc)
                .packageActinide()
                .build();
        Substance LUDICRITE = new SubstanceBuilder("ludicrite")
                .overrides(INGOT, "bigreactors:ingotludicrite", DUST, "bigreactors:dustludicrite", BLOCK, "bigreactors:blockludicrite")
                .color(0xFFfc0cfc)
                .packageActinide()
                .build();

        Substance ANGLESITE = new SubstanceBuilder("anglesite")
                .composition(LEAD, 1, SULFUR, 1, OXYGEN, 4)
                .overrides(GEM, "bigreactors:mineralanglesite")
                .color(0xFFfccc0c)
                .packageGem()
                .build();
        Substance BENITOITE = new SubstanceBuilder("benitoite")
                .composition(BARIUM, 1, TITANIUM, 1, SILICON, 3, OXYGEN, 9)
                .overrides(GEM, "bigreactors:mineralbenitoite")
                .color(0xFF0cf4fc)
                .packageGem()
                .build();
    }
}
