package com.mrmx.Barlious_Dream.event;

import com.mrmx.Barlious_Dream.config.ArrowHitConfig;
import com.mrmx.Barlious_Dream.config.KillConfirmConfig;
import com.mrmx.Barlious_Dream.registry.Barlious_Dream;
import com.mrmx.Barlious_Dream.sound.ModSounds;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.NeutralMob;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.ProjectileImpactEvent;

@EventBusSubscriber(modid = Barlious_Dream.MODID)
public class HitConfirmed {

    @SubscribeEvent
    public static void onProjectileImpact(ProjectileImpactEvent event) {
        if (!ArrowHitConfig.ENABLED.get()) { return; }

        if (!(event.getProjectile() instanceof AbstractArrow arrow)) { return; }
        if (event.getRayTraceResult() instanceof BlockHitResult) {
            if (ArrowHitConfig.SHAME_ENABLED.get()) {
                if (arrow.getOwner() instanceof ServerPlayer player) {
                    player.playNotifySound(
                            ModSounds.SPONGEBOB_DISAPPOINTED.get(),
                            SoundSource.PLAYERS,
                            (float) ArrowHitConfig.VOLUME.get().doubleValue(),
                            (float) ArrowHitConfig.PITCH.get().doubleValue()
                    );
                }
            } return;
        }
        if (!(event.getRayTraceResult() instanceof EntityHitResult Successful_Hit)) { return; }
        if (!(arrow.getOwner() instanceof ServerPlayer player)) { return; }
        Entity Mob = Successful_Hit.getEntity();

        boolean ValidHitToConfirm;
        if (Mob instanceof ServerPlayer) {
            ValidHitToConfirm = ArrowHitConfig.PLAYER_ENABLED.get();
        } else if (Mob instanceof Enemy) {
            ValidHitToConfirm = ArrowHitConfig.ENEMY_ENABLED.get();
        } else if (Mob instanceof NeutralMob) {
            ValidHitToConfirm = ArrowHitConfig.NEUTRAL_ENABLED.get();
        } else {
            ValidHitToConfirm = ArrowHitConfig.PACIFIC_ENABLED.get();
        } if (!ValidHitToConfirm) { return; }

        player.playNotifySound(
                ArrowHitConfig.SOUND.get().getSound(),
                SoundSource.PLAYERS,
                (float) ArrowHitConfig.VOLUME.get().doubleValue(),
                (float) ArrowHitConfig.PITCH.get().doubleValue()
        );
    }
}
