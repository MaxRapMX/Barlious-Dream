package com.mrmx.Barlious_Dream.sound;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

public class Kill_Confirm {
    public static final DeferredRegister<SoundEvent> KILL_CONFIRM_SOUND =
            DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, "mrmx_dream");

    public static final DeferredHolder<SoundEvent, SoundEvent> MC_BELL_KC =
            KILL_CONFIRM_SOUND.register("mc_bell_kc",
                    () -> SoundEvent.createVariableRangeEvent(
                            ResourceLocation.fromNamespaceAndPath("mrmx_dream","mc_bell_kc")
                    )
            );
    public static final DeferredHolder<SoundEvent, SoundEvent> BREATH_KC =
            KILL_CONFIRM_SOUND.register("breath_kc",
                    () -> SoundEvent.createVariableRangeEvent(
                            ResourceLocation.fromNamespaceAndPath("mrmx_dream", "breath_kc")
                    )
            );
    public static final DeferredHolder<SoundEvent, SoundEvent> PVZ_GW_KC =
            KILL_CONFIRM_SOUND.register("pvz_gw_kc",
                    () -> SoundEvent.createVariableRangeEvent(
                            ResourceLocation.fromNamespaceAndPath("mrmx_dream", "pvz_gw_kc")
                    )
            );
}
