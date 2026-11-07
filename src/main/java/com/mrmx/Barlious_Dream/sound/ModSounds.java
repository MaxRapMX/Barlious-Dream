package com.mrmx.Barlious_Dream.sound;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND =
            DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, "mrmx_dream");

    public static final DeferredHolder<SoundEvent, SoundEvent> SPONGEBOB_DISAPPOINTED =
            SOUND.register("spongebob_disappointed", () -> SoundEvent.createVariableRangeEvent(
                            ResourceLocation.fromNamespaceAndPath("mrmx_dream", "spongebob_disappointed")
                    )
            );

    public static final DeferredHolder<SoundEvent, SoundEvent> PVZ_EWW =
            SOUND.register("pvz_eww", () -> SoundEvent.createVariableRangeEvent(
                            ResourceLocation.fromNamespaceAndPath("mrmx_dream", "pvz_eww")
                    )
            );

    public static final DeferredHolder<SoundEvent, SoundEvent> WHISTLE_ONLY_PLANT_TARGET =
            SOUND.register("whistle_only_plant_target_defense", () -> SoundEvent.createVariableRangeEvent(
                            ResourceLocation.fromNamespaceAndPath("mrmx_dream", "whistle_only_plant_target_defense")
                    )
            );

    public static final DeferredHolder<SoundEvent, SoundEvent> WHISTLE_NO_MERCY =
            SOUND.register("whistle_total_defense", () -> SoundEvent.createVariableRangeEvent(
                            ResourceLocation.fromNamespaceAndPath("mrmx_dream", "whistle_total_defense")
                    )
            );

    public static final DeferredHolder<SoundEvent, SoundEvent> WHISTLE_DEFAULT =
            SOUND.register("whistle_default_defense", () -> SoundEvent.createVariableRangeEvent(
                            ResourceLocation.fromNamespaceAndPath("mrmx_dream", "whistle_default_defense")
                    )
            );
}
