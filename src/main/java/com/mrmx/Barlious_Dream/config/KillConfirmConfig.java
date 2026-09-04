package com.mrmx.Barlious_Dream.config;

import net.neoforged.neoforge.common.ModConfigSpec;
import com.mrmx.Barlious_Dream.sound.Kill_Confirm;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredHolder;

public class KillConfirmConfig {

    public enum KillConfirmSoundOption {
        MC_Belt(Kill_Confirm.MC_BELL_KC),
        Breath(Kill_Confirm.BREATH_KC),
        PVZ_GW(Kill_Confirm.PVZ_GW_KC),
        Meme_Dissapointed(Kill_Confirm.MEME_DISAPPOINTED_KC),
        Meme_Metal_Pipe(Kill_Confirm.MEME_METAL_PIPE_KC),
        CUSTOMIZABLE_1(Kill_Confirm.CUSTOM_KC_1),
        CUSTOMIZABLE_2(Kill_Confirm.CUSTOM_KC_2),
        CUSTOMIZABLE_3(Kill_Confirm.CUSTOM_KC_3);

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
                .translation("dreams_mrmx.config.kill_confirm.sound").defineEnum("sound", KillConfirmSoundOption.MC_Belt);
        VOLUME = builder
                .comment("Define the Volume of the Kill-Confirm")
                .translation("dreams_mrmx.config.kill_confirm.volume").defineInRange("volume", 1.0, 0.0, 3.0);
        PITCH = builder
                .comment("Define the Pitch of the Kill-Confirm")
                .translation("dreams_mrmx.config.kill_confirm.pitch").defineInRange("pitch", 1.0, 0.5, 2.0);

        builder.pop(); SPEC = builder.build();
    }
}