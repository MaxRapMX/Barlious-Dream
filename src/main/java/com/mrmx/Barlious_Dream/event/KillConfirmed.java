package com.mrmx.Barlious_Dream.event;

import com.mrmx.Barlious_Dream.config.KillConfirmConfig;
import com.mrmx.Barlious_Dream.registry.Barlious_Dream;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.NeutralMob;
import net.minecraft.world.entity.monster.Enemy;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;

@EventBusSubscriber(modid = Barlious_Dream.MODID)
public class KillConfirmed {

    @SubscribeEvent
    public static void PlayerKillConfirm(LivingDeathEvent event) {
        if (!KillConfirmConfig.ENABLED.get()) { return; }

        Entity KillSource = event.getSource().getEntity();
        if (!(KillSource instanceof ServerPlayer player)) { return; }

        LivingEntity Killed_Mob = event.getEntity();

        boolean ValidKillToConfirm;
        if (Killed_Mob instanceof ServerPlayer) {
            ValidKillToConfirm = KillConfirmConfig.PLAYER_ENABLED.get();
        } else if (Killed_Mob instanceof Enemy) {
            ValidKillToConfirm = KillConfirmConfig.ENEMY_ENABLED.get();
        } else if (Killed_Mob instanceof NeutralMob) {
            ValidKillToConfirm = KillConfirmConfig.NEUTRAL_ENABLED.get();
        } else {
            ValidKillToConfirm = KillConfirmConfig.PACIFIC_ENABLED.get();
        } if (!ValidKillToConfirm) { return; }

        player.playNotifySound(
                KillConfirmConfig.SOUND.get().getSound(),
                SoundSource.PLAYERS,
                (float) KillConfirmConfig.VOLUME.get().doubleValue(),
                (float) KillConfirmConfig.PITCH.get().doubleValue()
        );
    }
}
