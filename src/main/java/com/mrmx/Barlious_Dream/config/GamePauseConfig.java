package com.mrmx.Barlious_Dream.config;

import com.mrmx.Barlious_Dream.sound.GamePause;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.registries.DeferredHolder;

public class GamePauseConfig {

    public enum GamePauseSoundOption {
        PVZ(GamePause.PVZ_PAUSE),
        SKULLGIRLS(GamePause.SKULLGIRLS_PAUSE);

        private final DeferredHolder<SoundEvent, SoundEvent> GamePause_Selected_Sound;

        GamePauseSoundOption(DeferredHolder<SoundEvent, SoundEvent> soundHolder) {
            this.GamePause_Selected_Sound = soundHolder;
        }
        public SoundEvent getSound() {
            return GamePause_Selected_Sound.get();
        }
    }

    public static final ModConfigSpec SPEC;

    public static final ModConfigSpec.BooleanValue ENABLED;

    public static final ModConfigSpec.EnumValue<GamePauseSoundOption> SOUND;
    public static final ModConfigSpec.DoubleValue VOLUME;
    public static final ModConfigSpec.DoubleValue PITCH;

    static {
        ModConfigSpec.Builder builder = new ModConfigSpec.Builder();
        builder.push("game_pause");

        ENABLED = builder
                .comment("Turn On, or Off the Game-Pause Module")
                .translation("enabled.game_pause")
                .define("enabled", true);

        SOUND = builder
                .comment("Elige qué sonido se reproduce al abrir el menú de pausa")
                .translation("mrmx_dream.config.game_pause.sound")
                .defineEnum("sound", GamePauseSoundOption.PVZ);
        VOLUME = builder
                .comment("Define the Volume of the Game-Pause (0.0 - 2.0)")
                .translation("dreams_mrmx.config.game_pause.volume")
                .defineInRange("volume", 1.0, 0.0, 2.0);
        PITCH = builder
                .comment("Define the Pitch of the Game-Pause (0.5 - 2.0)")
                .translation("dreams_mrmx.config.game_pause.pitch")
                .defineInRange("pitch", 1.0, 0.5, 2.0);

        builder.pop();
        SPEC = builder.build();
    }
}
