package com.mrmx.Barlious_Dream.sound;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND =
            DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, "mrmx_dream");

    public static final DeferredHolder<SoundEvent, SoundEvent> MC_CLICK =
            SOUND.register("mc_click", () -> SoundEvent.createVariableRangeEvent(
                    ResourceLocation.fromNamespaceAndPath("mrmx_dream","mc_click")
                    )
            );
    public static final DeferredHolder<SoundEvent, SoundEvent> SPONGEBOB_DISAPPOINTED =
            SOUND.register("spongebob_disappointed", () -> SoundEvent.createVariableRangeEvent(
                            ResourceLocation.fromNamespaceAndPath("mrmx_dream", "spongebob_disappointed")
                    )
            );
}
