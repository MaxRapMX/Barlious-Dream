package com.mrmx.Barlious_Dream.sound;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class GamePause {
    public static final DeferredRegister<SoundEvent> GAME_PAUSE =
            DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, "mrmx_dream");

    public static final DeferredHolder<SoundEvent, SoundEvent> MC_CLICK_PAUSE =
            GAME_PAUSE.register("mc_click_pause",
                    () -> SoundEvent.createVariableRangeEvent(
                            ResourceLocation.fromNamespaceAndPath("mrmx_dream","mc_click_pause")
                    )
            );
    public static final DeferredHolder<SoundEvent, SoundEvent> PVZ_PAUSE =
            GAME_PAUSE.register("pvz_pause",
                    () -> SoundEvent.createVariableRangeEvent(
                            ResourceLocation.fromNamespaceAndPath("mrmx_dream","pvz_pause")
                    )
            );
    public static final DeferredHolder<SoundEvent, SoundEvent> SKULLGIRLS_PAUSE =
            GAME_PAUSE.register("skullgirls_pause",
                    () -> SoundEvent.createVariableRangeEvent(
                            ResourceLocation.fromNamespaceAndPath("mrmx_dream","skullgirls_pause")
                    )
            );
    public static final DeferredHolder<SoundEvent, SoundEvent> CUSTOM_PAUSE_1 =
            GAME_PAUSE.register("custom_pause_1",
                    () -> SoundEvent.createVariableRangeEvent(
                            ResourceLocation.fromNamespaceAndPath("mrmx_dream","custom_pause_1")
                    )
            );
    public static final DeferredHolder<SoundEvent, SoundEvent> CUSTOM_PAUSE_2 =
            GAME_PAUSE.register("custom_pause_2",
                    () -> SoundEvent.createVariableRangeEvent(
                            ResourceLocation.fromNamespaceAndPath("mrmx_dream","custom_pause_2")
                    )
            );
    public static final DeferredHolder<SoundEvent, SoundEvent> CUSTOM_PAUSE_3 =
            GAME_PAUSE.register("custom_pause_3",
                    () -> SoundEvent.createVariableRangeEvent(
                            ResourceLocation.fromNamespaceAndPath("mrmx_dream","custom_pause_3")
                    )
            );
}
