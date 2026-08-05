package fr.cyrilneveu.craftorium.common.integration;

import fr.cyrilneveu.craftorium.api.substance.RegisterSubstancesEvent;
import fr.cyrilneveu.craftorium.api.substance.SubstanceBuilder;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static fr.cyrilneveu.craftorium.common.substance.SubstancesObjects.*;

@Mod.EventBusSubscriber
public final class SubstancesProjectE {
    @SubscribeEvent
    public static void onRegisterSubstances(RegisterSubstancesEvent event) {
        if (!Loader.isModLoaded("projecte"))
            return;

        new SubstanceBuilder("dark_matter")
                .packageNonMetal()
                .items(BOULE)
                .blocks(BLOCK)
                .overrides(BOULE, "projecte:item.pe_matter", BLOCK, "projecte:matter_block")
                .color(0xFF000000)
                .build();
        new SubstanceBuilder("red_matter")
                .packageNonMetal()
                .items(BOULE)
                .blocks(BLOCK)
                .overrides(BOULE, "projecte:item.pe_matter:1", BLOCK, "projecte:matter_block:1")
                .color(0xFFff0000)
                .build();

        new SubstanceBuilder("low_covalence")
                .packageNonMetal()
                .overrides(DUST, "projecte:item.pe_covalence_dust")
                .color(0xFF00ff00)
                .build();
        new SubstanceBuilder("medium_covalence")
                .packageNonMetal()
                .overrides(DUST, "projecte:item.pe_covalence_dust:1")
                .color(0xFF00ffff)
                .build();
        new SubstanceBuilder("high_covalence")
                .packageNonMetal()
                .overrides(DUST, "projecte:item.pe_covalence_dust:2")
                .color(0xFF0000ff)
                .build();
    }
}
