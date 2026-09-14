package com.mrmx.Barlious_Dream.config;

import com.mrmx.Barlious_Dream.sound.ClientModulesSounds;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.registries.DeferredHolder;

public class ArrowHitConfig {

    public enum ArrowHitSoundOption {
        MC_Ding(ClientModulesSounds.MC_DING),
        MC_Bell(ClientModulesSounds.MC_BELL),
        Bonk(ClientModulesSounds.MEME_BONK),
        Metal_Pipe(ClientModulesSounds.METAL_PIPE),
        CUSTOMIZABLE_1(ClientModulesSounds.CUSTOM_PVP_1),
        CUSTOMIZABLE_2(ClientModulesSounds.CUSTOM_PVP_2),
        CUSTOMIZABLE_3(ClientModulesSounds.CUSTOM_PVP_3),
        CUSTOMIZABLE_4(ClientModulesSounds.CUSTOM_PVP_4),
        CUSTOMIZABLE_5(ClientModulesSounds.CUSTOM_PVP_5);

        private final DeferredHolder<SoundEvent, SoundEvent> ArrowHit_Selected_Sound;
        ArrowHitSoundOption(DeferredHolder<SoundEvent, SoundEvent> soundHolder) {
            this.ArrowHit_Selected_Sound = soundHolder;
        } public SoundEvent getSound() {
            return ArrowHit_Selected_Sound.get();
        }
    }

    public static final ModConfigSpec SPEC;

    public static final ModConfigSpec.BooleanValue ENABLED;
    public static final ModConfigSpec.BooleanValue PLAYER_ENABLED;
    public static final ModConfigSpec.BooleanValue ENEMY_ENABLED;
    public static final ModConfigSpec.BooleanValue NEUTRAL_ENABLED;
    public static final ModConfigSpec.BooleanValue PACIFIC_ENABLED;

    public static final ModConfigSpec.EnumValue<ArrowHitConfig.ArrowHitSoundOption> SOUND;
    public static final ModConfigSpec.DoubleValue VOLUME;
    public static final ModConfigSpec.DoubleValue PITCH;
    public static final ModConfigSpec.BooleanValue SHAME_ENABLED;

    static {
        ModConfigSpec.Builder builder = new ModConfigSpec.Builder();
        builder.push("arrow_hit");

        ENABLED = builder
                .comment("Turn On, or Off the Arrow-Hit Module")
                .translation("enabled.arrow_hit").define("enabled", false);
        PLAYER_ENABLED = builder
                .comment("Turn On, or Off the Arrow-Hit when you defeat a Player (PvP)")
                .translation("player.arrow_hit").define("player_enabled", true);
        ENEMY_ENABLED = builder
                .comment("Turn On, or Off the Arrow-Hit when you defeat a Enemy Mob")
                .translation("enemy_mob.arrow_hit").define("enemy_enabled", true);
        NEUTRAL_ENABLED = builder
                .comment("Turn On, or Off the Arrow-Hit when you defeat a Neutral Mob")
                .translation("neutral_mob.arrow_hit").define("neutral_enabled", true);
        PACIFIC_ENABLED = builder
                .comment("Turn On, or Off the Arrow-Hit when you defeat a Pacific Mob")
                .translation("pacific_mob.arrow_hit").define("pacific_enabled", true);
        SOUND = builder
                .comment("Choose the sound that plays the Arrow-Hit")
                .translation("mrmx_dream.config.arrow_hit.sound").defineEnum("sound", ArrowHitSoundOption.MC_Ding);
        VOLUME = builder
                .comment("Define the Volume of the Arrow-Hit")
                .translation("dreams_mrmx.config.arrow_hit.volume").defineInRange("volume", 0.25, 0.0, 2.0);
        PITCH = builder
                .comment("Define the Pitch of the Arrow-Hit")
                .translation("dreams_mrmx.config.arrow_hit.pitch").defineInRange("pitch", 1.0, 0.5, 2.0);
        SHAME_ENABLED = builder
                .comment("Turn On, or Off the Arrow-Block-Hit")
                .translation("enabled.arrow_block_hit").define("enabled", false);

        builder.pop(); SPEC = builder.build();
    }
}
