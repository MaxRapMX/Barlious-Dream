package com.mrmx.Barlious_Dream.event;

import com.mrmx.Barlious_Dream.config.GamePauseConfig;
import com.mrmx.Barlious_Dream.config.KillConfirmConfig;
import com.mrmx.Barlious_Dream.sound.GamePause;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.PauseScreen;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ScreenEvent;
import net.neoforged.neoforge.client.event.ClientTickEvent;

@EventBusSubscriber(modid = com.mrmx.Barlious_Dream.registry.Barlious_Dream.MODID, value = Dist.CLIENT)
public class GamePaused {

    // All the delay are in Ticks, 20 Ticks = 1 Second
    private static int DelayAccount = -1;
    private static final int DelayStart = 1;
    private static boolean InPauseMenu = false;

    @SubscribeEvent
    public static void ScreenOpening(ScreenEvent.Opening event) {
        if (!GamePauseConfig.ENABLED.get()) { return; }
        if (event.getScreen() instanceof PauseScreen && !InPauseMenu) {
            DelayAccount = DelayStart;
            InPauseMenu = true;
        }
    }

    @SubscribeEvent
    public static void onClientTick(ClientTickEvent.Post event) {
        if (!GamePauseConfig.ENABLED.get()) { return; }
        if (Minecraft.getInstance().screen == null) {
            InPauseMenu = false;
            DelayAccount = -1;
            return;
        } if (DelayAccount > 0) {
            DelayAccount--; if (DelayAccount == 0) {
                var player = Minecraft.getInstance().player;
                if (player != null) {
                    player.playSound(
                            GamePauseConfig.SOUND.get().getSound(),
                            (float) GamePauseConfig.VOLUME.get().doubleValue(),
                            (float) GamePauseConfig.PITCH.get().doubleValue()
                    );
                }
            }
        }
    }
}
