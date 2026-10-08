package com.mrmx.Barlious_Dream.config;

import com.mrmx.Barlious_Dream.sound.ClientModulesSounds;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.registries.DeferredHolder;

public class ThrownTridentHitConfig {

    public enum ThrownTridentHitSoundOption {
        MC_Ding(ClientModulesSounds.MC_DING),
        MC_Bell(ClientModulesSounds.MC_BELL),
        Bonk(ClientModulesSounds.MEME_BONK),
        Metal_Pipe(ClientModulesSounds.METAL_PIPE),
        CUSTOMIZABLE_1(ClientModulesSounds.CUSTOM_PVP_1),
        CUSTOMIZABLE_2(ClientModulesSounds.CUSTOM_PVP_2),
        CUSTOMIZABLE_3(ClientModulesSounds.CUSTOM_PVP_3),
        CUSTOMIZABLE_4(ClientModulesSounds.CUSTOM_PVP_4),
        CUSTOMIZABLE_5(ClientModulesSounds.CUSTOM_PVP_5);

        private final DeferredHolder<SoundEvent, SoundEvent> ThrownTridentHit_Selected_Sound;
        ThrownTridentHitSoundOption(DeferredHolder<SoundEvent, SoundEvent> soundHolder) {
            this.ThrownTridentHit_Selected_Sound = soundHolder;
        } public SoundEvent getSound() {
            return ThrownTridentHit_Selected_Sound.get();
        }
    }

    public static final ModConfigSpec SPEC;

    public static final ModConfigSpec.BooleanValue ENABLED;
    public static final ModConfigSpec.BooleanValue PLAYER_ENABLED;
    public static final ModConfigSpec.BooleanValue ENEMY_ENABLED;
    public static final ModConfigSpec.BooleanValue NEUTRAL_ENABLED;
    public static final ModConfigSpec.BooleanValue PACIFIC_ENABLED;

    public static final ModConfigSpec.EnumValue<ThrownTridentHitSoundOption> SOUND;
    public static final ModConfigSpec.DoubleValue VOLUME;
    public static final ModConfigSpec.DoubleValue PITCH;
    public static final ModConfigSpec.BooleanValue SHAME_ENABLED;

    static {
        ModConfigSpec.Builder builder = new ModConfigSpec.Builder();
        builder.push("thrown_trident_hit");

        ENABLED = builder
                .comment("Turn On, or Off the Trident-Hit Module")
                .translation("enabled.thrown_trident_hit").define("enabled", false);
        PLAYER_ENABLED = builder
                .comment("Turn On, or Off the Trident-Hit when you defeat a Player (PvP)")
                .translation("player.thrown_trident_hit").define("player_enabled", true);
        ENEMY_ENABLED = builder
                .comment("Turn On, or Off the Trident-Hit when you defeat a Enemy Mob")
                .translation("enemy_mob.thrown_trident_hit").define("enemy_enabled", true);
        NEUTRAL_ENABLED = builder
                .comment("Turn On, or Off the Trident-Hit when you defeat a Neutral Mob")
                .translation("neutral_mob.thrown_trident_hit").define("neutral_enabled", true);
        PACIFIC_ENABLED = builder
                .comment("Turn On, or Off the Trident-Hit when you defeat a Pacific Mob")
                .translation("pacific_mob.thrown_trident_hit").define("pacific_enabled", true);
        SOUND = builder
                .comment("Choose the sound that plays the Trident-Hit")
                .translation("mrmx_dream.config.thrown_trident_hit.sound").defineEnum("sound", ThrownTridentHitSoundOption.MC_Ding);
        VOLUME = builder
                .comment("Define the Volume of the Trident-Hit")
                .translation("dreams_mrmx.config.thrown_trident_hit.volume").defineInRange("volume", 0.25, 0.0, 2.0);
        PITCH = builder
                .comment("Define the Pitch of the Trident-Hit")
                .translation("dreams_mrmx.config.thrown_trident_hit.pitch").defineInRange("pitch", 1.0, 0.5, 2.0);
        SHAME_ENABLED = builder
                .comment("Turn On, or Off the Trident-Block-Hit (Shame)")
                .translation("enabled.thrown_trident_block_hit").define("block_hit_enabled", false);

        builder.pop(); SPEC = builder.build();
    }
}