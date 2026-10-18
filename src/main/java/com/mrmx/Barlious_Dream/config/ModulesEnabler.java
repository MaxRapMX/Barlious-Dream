package com.mrmx.Barlious_Dream.config;
import net.neoforged.neoforge.common.ModConfigSpec;

public class ModulesEnabler {

    public static final ModConfigSpec SPEC;

    public static final ModConfigSpec.BooleanValue KILL_CONFIRM_ENABLED;
    public static final ModConfigSpec.BooleanValue GAME_PAUSE_ENABLED;
    public static final ModConfigSpec.BooleanValue ARROW_HIT_ENABLED;
    public static final ModConfigSpec.BooleanValue THROWN_TRIDENT_HIT_ENABLED;
    public static final ModConfigSpec.BooleanValue TOTEM_POPPED_ENABLED;


    static {
        ModConfigSpec.Builder builder = new ModConfigSpec.Builder();
        builder.push("modules_enabler");

        KILL_CONFIRM_ENABLED = builder
                .comment("Turn On, or Off the Kill-Confirm Module")
                .translation("enable.kill_confirm").define("kill_confirm_enabled", true);
        GAME_PAUSE_ENABLED = builder
                .comment("Turn On, or Off the Game-Pause Module")
                .translation("enable.game_pause").define("game_pause_enabled", true);
        ARROW_HIT_ENABLED = builder
                .comment("Turn On, or Off the Arrow-Hit Module")
                .translation("enable.arrow_hit").define("arrow_hit_enabled", false);
        THROWN_TRIDENT_HIT_ENABLED = builder
                .comment("Turn On, or Off the Trident-Hit Module")
                .translation("enable.thrown_trident_hit").define("thrown_trident_hit_enabled", false);
        TOTEM_POPPED_ENABLED = builder
                .comment("Turn On, or Off the Totem-Popped-Global-Notify Module")
                .translation("enable.totem_popped_notify").define("totem_popped_notify_enabled", false);

        builder.pop(); SPEC = builder.build();
    }
}