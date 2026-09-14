package com.mrmx.Barlious_Dream.registry;

import com.mojang.logging.LogUtils;
import com.mrmx.Barlious_Dream.item.ModCreativeModeTabs;
import com.mrmx.Barlious_Dream.item.ModItems;
import com.mrmx.Barlious_Dream.sound.ClientModulesSounds;
import com.mrmx.Barlious_Dream.sound.ModSounds;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;

@Mod(Barlious_Dream.MODID)
public class Barlious_Dream {
    public static final String MODID = "mrmx_dream"; // Define mod id in a common place for everything to reference
    public static final Logger LOGGER = LogUtils.getLogger(); // Directly reference a slf4j logger

    public Barlious_Dream(IEventBus modEventBus) {
        ModItems.register(modEventBus);
        ModCreativeModeTabs.register(modEventBus);
        ModSounds.SOUND.register(modEventBus);
        ModAttachments.register(modEventBus);

        ClientModulesSounds.PVP_SOUND.register(modEventBus);
        ClientModulesSounds.GAME_PAUSE.register(modEventBus);
    }
}
