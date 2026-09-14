package com.mrmx.Barlious_Dream.sound;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ClientModulesSounds {
    public static final DeferredRegister<SoundEvent> PVP_SOUND =
            DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, "mrmx_dream");
    public static final DeferredRegister<SoundEvent> GAME_PAUSE =
            DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, "mrmx_dream");

    public static final DeferredHolder<SoundEvent, SoundEvent> MC_BELL =
            PVP_SOUND.register("mc_bell", () -> SoundEvent.createVariableRangeEvent(
                            ResourceLocation.fromNamespaceAndPath("mrmx_dream","mc_bell")
                    )
            );
    public static final DeferredHolder<SoundEvent, SoundEvent> MC_DING =
            PVP_SOUND.register("mc_ding", () -> SoundEvent.createVariableRangeEvent(
                            ResourceLocation.fromNamespaceAndPath("mrmx_dream","mc_ding")
                    )
            );
    public static final DeferredHolder<SoundEvent, SoundEvent> MC_CHANNELING =
            PVP_SOUND.register("mc_trident_channeling", () -> SoundEvent.createVariableRangeEvent(
                            ResourceLocation.fromNamespaceAndPath("mrmx_dream","mc_trident_channeling")
                    )
            );
    public static final DeferredHolder<SoundEvent, SoundEvent> MEME_BONK =
            PVP_SOUND.register("meme_bonk", () -> SoundEvent.createVariableRangeEvent(
                            ResourceLocation.fromNamespaceAndPath("mrmx_dream", "meme_bonk")
                    )
            );
    public static final DeferredHolder<SoundEvent, SoundEvent> MEME_FART_REVERB =
            PVP_SOUND.register("meme_fart_reverb", () -> SoundEvent.createVariableRangeEvent(
                            ResourceLocation.fromNamespaceAndPath("mrmx_dream", "meme_fart_reverb")
                    )
            );
    public static final DeferredHolder<SoundEvent, SoundEvent> METAL_PIPE =
            PVP_SOUND.register("metal_pipe", () -> SoundEvent.createVariableRangeEvent(
                            ResourceLocation.fromNamespaceAndPath("mrmx_dream", "metal_pipe")
                    )
            );
    public static final DeferredHolder<SoundEvent, SoundEvent> BREATH_1 =
            PVP_SOUND.register("breath_1", () -> SoundEvent.createVariableRangeEvent(
                            ResourceLocation.fromNamespaceAndPath("mrmx_dream", "breath_1")
                    )
            );
    public static final DeferredHolder<SoundEvent, SoundEvent> MK_FATALITY =
            PVP_SOUND.register("mk_fatality", () -> SoundEvent.createVariableRangeEvent(
                            ResourceLocation.fromNamespaceAndPath("mrmx_dream", "mk_fatality")
                    )
            );
    public static final DeferredHolder<SoundEvent, SoundEvent> PVZ_GW_KC =
            PVP_SOUND.register("pvz_gw_kc", () -> SoundEvent.createVariableRangeEvent(
                            ResourceLocation.fromNamespaceAndPath("mrmx_dream", "pvz_gw_kc")
                    )
            );
    public static final DeferredHolder<SoundEvent, SoundEvent> CUSTOM_PVP_1 =
            PVP_SOUND.register("custom_pvp_1", () -> SoundEvent.createVariableRangeEvent(
                            ResourceLocation.fromNamespaceAndPath("mrmx_dream", "custom_pvp_1")
                    )
            );
    public static final DeferredHolder<SoundEvent, SoundEvent> CUSTOM_PVP_2 =
            PVP_SOUND.register("custom_pvp_2", () -> SoundEvent.createVariableRangeEvent(
                            ResourceLocation.fromNamespaceAndPath("mrmx_dream", "custom_pvp_2")
                    )
            );
    public static final DeferredHolder<SoundEvent, SoundEvent> CUSTOM_PVP_3 =
            PVP_SOUND.register("custom_pvp_3", () -> SoundEvent.createVariableRangeEvent(
                            ResourceLocation.fromNamespaceAndPath("mrmx_dream", "custom_pvp_3")
                    )
            );
    public static final DeferredHolder<SoundEvent, SoundEvent> CUSTOM_PVP_4 =
            PVP_SOUND.register("custom_pvp_4", () -> SoundEvent.createVariableRangeEvent(
                            ResourceLocation.fromNamespaceAndPath("mrmx_dream", "custom_pvp_4")
                    )
            );
    public static final DeferredHolder<SoundEvent, SoundEvent> CUSTOM_PVP_5 =
            PVP_SOUND.register("custom_pvp_5", () -> SoundEvent.createVariableRangeEvent(
                            ResourceLocation.fromNamespaceAndPath("mrmx_dream", "custom_pvp_5")
                    )
            );



    public static final DeferredHolder<SoundEvent, SoundEvent> PVZ_PAUSE =
            GAME_PAUSE.register("pvz_pause", () -> SoundEvent.createVariableRangeEvent(
                    ResourceLocation.fromNamespaceAndPath("mrmx_dream","pvz_pause")
                    )
            );
    public static final DeferredHolder<SoundEvent, SoundEvent> SKULLGIRLS_JUSTAMOMENT =
            GAME_PAUSE.register("skullgirls_pause_1", () -> SoundEvent.createVariableRangeEvent(
                            ResourceLocation.fromNamespaceAndPath("mrmx_dream","skullgirls_pause_1")
                    )
            );
    public static final DeferredHolder<SoundEvent, SoundEvent> CUSTOM_PAUSE_1 =
            GAME_PAUSE.register("custom_pause_1", () -> SoundEvent.createVariableRangeEvent(
                            ResourceLocation.fromNamespaceAndPath("mrmx_dream","custom_pause_1")
                    )
            );
    public static final DeferredHolder<SoundEvent, SoundEvent> CUSTOM_PAUSE_2 =
            GAME_PAUSE.register("custom_pause_2", () -> SoundEvent.createVariableRangeEvent(
                            ResourceLocation.fromNamespaceAndPath("mrmx_dream","custom_pause_2")
                    )
            );
    public static final DeferredHolder<SoundEvent, SoundEvent> CUSTOM_PAUSE_3 =
            GAME_PAUSE.register("custom_pause_3", () -> SoundEvent.createVariableRangeEvent(
                            ResourceLocation.fromNamespaceAndPath("mrmx_dream","custom_pause_3")
                    )
            );
}
