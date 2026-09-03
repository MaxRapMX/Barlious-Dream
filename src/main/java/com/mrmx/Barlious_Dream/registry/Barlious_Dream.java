package com.mrmx.Barlious_Dream.registry;

import com.mojang.logging.LogUtils;
import com.mrmx.Barlious_Dream.sound.GamePause;
import com.mrmx.Barlious_Dream.sound.Kill_Confirm;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;

@Mod(Barlious_Dream.MODID)
public class Barlious_Dream {
    public static final String MODID = "mrmx_dream"; // Define mod id in a common place for everything to reference
    public static final Logger LOGGER = LogUtils.getLogger(); // Directly reference a slf4j logger

    public Barlious_Dream(IEventBus modEventBus) {
        Kill_Confirm.KILL_CONFIRM_SOUND.register(modEventBus);
        GamePause.GAME_PAUSE.register(modEventBus);
    }
}
