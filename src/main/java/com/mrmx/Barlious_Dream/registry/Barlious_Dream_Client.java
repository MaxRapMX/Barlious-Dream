package com.mrmx.Barlious_Dream.registry;

import com.mrmx.Barlious_Dream.config.GamePauseConfig;
import com.mrmx.Barlious_Dream.config.KillConfirmConfig;
import com.mrmx.Barlious_Dream.config.config_screen.GeneralConfigScreen;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

@Mod(value = Barlious_Dream.MODID, dist = Dist.CLIENT) // This class will not load on dedicated servers. Accessing client side code from here is safe.
public class Barlious_Dream_Client {
    public Barlious_Dream_Client(ModContainer container) {
        container.registerConfig(ModConfig.Type.CLIENT, KillConfirmConfig.SPEC, "mrmx_dream/kill_confirm-client.toml");
        container.registerConfig(ModConfig.Type.CLIENT, GamePauseConfig.SPEC, "mrmx_dream/game_pause-client.toml");

        container.registerExtensionPoint(IConfigScreenFactory.class,
                (modContainer, screen) -> new GeneralConfigScreen(screen));
    }
}
