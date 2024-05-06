package fr.cyrilneveu.craftorium;

import fr.cyrilneveu.craftorium.api.net.NetManager;
import fr.cyrilneveu.craftorium.common.ACommonProxy;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = CraftoriumTags.MODID, version = CraftoriumTags.VERSION, name = CraftoriumTags.MODNAME, acceptedMinecraftVersions = "[1.12.2]", dependencies = "after:crafttweaker")
public final class Craftorium {
    public static final Logger LOGGER = LogManager.getLogger(CraftoriumTags.MODID);
    @Mod.Instance
    public static Craftorium instance;
    @SidedProxy(clientSide = "fr.cyrilneveu.craftorium.client.ClientProxy", serverSide = "fr.cyrilneveu.craftorium.server.ServerProxy")
    public static ACommonProxy proxy;

    public Craftorium() {
        instance = this;
        FluidRegistry.enableUniversalBucket();
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
        NetManager.registerPackets();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }
}