package com.mrmx.Barlious_Dream.config;

import com.mrmx.Barlious_Dream.sound.ClientModulesSounds;
import com.mrmx.Barlious_Dream.sound.ModSounds;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredHolder;

public class KillConfirmConfig {

    public enum KillConfirmSoundOption {
        MC_Bell(ClientModulesSounds.MC_BELL),
        MC_Channeling(ClientModulesSounds.MC_CHANNELING),
        Breath(ClientModulesSounds.BREATH_1),
        MK_Fatality(ClientModulesSounds.MK_FATALITY),
        PVZ_GW(ClientModulesSounds.PVZ_GW_KC),
        Spongebob_Dissapointed(ModSounds.SPONGEBOB_DISAPPOINTED),
        Fart_Reverb(ClientModulesSounds.MEME_FART_REVERB),
        CUSTOMIZABLE_1(ClientModulesSounds.CUSTOM_PVP_1),
        CUSTOMIZABLE_2(ClientModulesSounds.CUSTOM_PVP_2),
        CUSTOMIZABLE_3(ClientModulesSounds.CUSTOM_PVP_3),
        CUSTOMIZABLE_4(ClientModulesSounds.CUSTOM_PVP_4),
        CUSTOMIZABLE_5(ClientModulesSounds.CUSTOM_PVP_5);

        private final DeferredHolder<SoundEvent, SoundEvent> KillConfirm_Selected_Sound;
        KillConfirmSoundOption(DeferredHolder<SoundEvent, SoundEvent> soundHolder) {
            this.KillConfirm_Selected_Sound = soundHolder;
        } public SoundEvent getSound() {
            return KillConfirm_Selected_Sound.get();
        }
    }

    public static final ModConfigSpec SPEC;

    public static final ModConfigSpec.BooleanValue ENABLED;
    public static final ModConfigSpec.BooleanValue PLAYER_ENABLED;
    public static final ModConfigSpec.BooleanValue ENEMY_ENABLED;
    public static final ModConfigSpec.BooleanValue NEUTRAL_ENABLED;
    public static final ModConfigSpec.BooleanValue PACIFIC_ENABLED;

    public static final ModConfigSpec.EnumValue<KillConfirmSoundOption> SOUND;
    public static final ModConfigSpec.DoubleValue VOLUME;
    public static final ModConfigSpec.DoubleValue PITCH;

    static {
        ModConfigSpec.Builder builder = new ModConfigSpec.Builder();
        builder.push("kill_confirm");

        ENABLED = builder
                .comment("Turn On, or Off the Kill-Confirm Module")
                .translation("enabled.kill_confirm").define("enabled", true);
        PLAYER_ENABLED = builder
                .comment("Turn On, or Off the Kill-Confirm when you defeat a Player (PvP)")
                .translation("player.kill_confirm").define("player_enabled", true);
        ENEMY_ENABLED = builder
                .comment("Turn On, or Off the Kill-Confirm when you defeat a Enemy Mob")
                .translation("enemy_mob.kill_confirm").define("enemy_enabled", true);
        NEUTRAL_ENABLED = builder
                .comment("Turn On, or Off the Kill-Confirm when you defeat a Neutral Mob")
                .translation("neutral_mob.kill_confirm").define("neutral_enabled", false);
        PACIFIC_ENABLED = builder
                .comment("Turn On, or Off the Kill-Confirm when you defeat a Pacific Mob")
                .translation("pacific_mob.kill_confirm").define("pacific_enabled", false);
        SOUND = builder
                .comment("Choose the sound that plays the Kill-Confirm")
                .translation("dreams_mrmx.config.kill_confirm.sound").defineEnum("sound", KillConfirmSoundOption.MC_Bell);
        VOLUME = builder
                .comment("Define the Volume of the Kill-Confirm")
                .translation("dreams_mrmx.config.kill_confirm.volume").defineInRange("volume", 1.0, 0.0, 3.0);
        PITCH = builder
                .comment("Define the Pitch of the Kill-Confirm")
                .translation("dreams_mrmx.config.kill_confirm.pitch").defineInRange("pitch", 1.0, 0.5, 2.0);

        builder.pop(); SPEC = builder.build();
    }
}