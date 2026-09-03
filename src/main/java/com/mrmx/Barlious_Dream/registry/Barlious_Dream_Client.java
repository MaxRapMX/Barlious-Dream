package com.mrmx.Barlious_Dream.registry;

import com.mrmx.Barlious_Dream.config.GamePauseConfig;
import com.mrmx.Barlious_Dream.config.KillConfirmConfig;
import com.mrmx.Barlious_Dream.config.config_screen.GeneralConfigScreen;
import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

// This class will not load on dedicated servers. Accessing client side code from here is safe.
@Mod(value = Barlious_Dream.MODID, dist = Dist.CLIENT)
// You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
@EventBusSubscriber(modid = Barlious_Dream.MODID, value = Dist.CLIENT)
public class Barlious_Dream_Client {
    public Barlious_Dream_Client(ModContainer container) {

        container.registerConfig(ModConfig.Type.CLIENT, KillConfirmConfig.SPEC, "mrmx_dream/kill_confirm-client.toml");
        container.registerConfig(ModConfig.Type.CLIENT, GamePauseConfig.SPEC, "mrmx_dream/game_pause-client.toml");

        container.registerExtensionPoint(IConfigScreenFactory.class,
                (modContainer, screen) -> new GeneralConfigScreen(screen));



    }

    @SubscribeEvent
    static void onClientSetup(FMLClientSetupEvent event) {
        // Some client setup code
        Barlious_Dream.LOGGER.info("HELLO FROM CLIENT SETUP");
        Barlious_Dream.LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
    }
}
