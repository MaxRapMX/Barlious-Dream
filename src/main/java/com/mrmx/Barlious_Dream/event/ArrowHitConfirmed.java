package com.mrmx.Barlious_Dream.event;

import com.mrmx.Barlious_Dream.config.ArrowHitConfig;
import com.mrmx.Barlious_Dream.registry.Barlious_Dream;
import com.mrmx.Barlious_Dream.sound.ModSounds;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.NeutralMob;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.projectile.ThrownTrident;
import net.minecraft.world.level.block.TargetBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.entity.ProjectileImpactEvent;

@EventBusSubscriber(modid = Barlious_Dream.MODID)
public class ArrowHitConfirmed {
    private static boolean NewProyectile = false;

    @SubscribeEvent
    public static void onEntityJoin(EntityJoinLevelEvent event) {
        if (!ArrowHitConfig.ENABLED.get()) { return; }
        if (event.getEntity()instanceof AbstractArrow arrow && !(event.getEntity()instanceof ThrownTrident)) {
            if (arrow.getOwner() instanceof ServerPlayer) {
            NewProyectile = true;
            }
        }
    }

    @SubscribeEvent
    public static void onProjectileImpact(ProjectileImpactEvent event) {
        if (!NewProyectile) {return;}

        if (!(event.getProjectile() instanceof AbstractArrow arrow)) { return; }
        if (!(arrow.getOwner() instanceof ServerPlayer player)) { return; }

        if (event.getRayTraceResult() instanceof BlockHitResult blockHit) {

            BlockState state = arrow.level().getBlockState(blockHit.getBlockPos());
            if (state.getBlock() instanceof TargetBlock) {
                player.playNotifySound(
                        ArrowHitConfig.SOUND.get().getSound(),
                        SoundSource.PLAYERS,
                        (float) ArrowHitConfig.VOLUME.get().doubleValue(),
                        (float) ArrowHitConfig.PITCH.get().doubleValue()
                ); NewProyectile = false; return;
            }

            if (ArrowHitConfig.SHAME_ENABLED.get()) {
                player.playNotifySound(
                        ModSounds.SPONGEBOB_DISAPPOINTED.get(),
                        SoundSource.PLAYERS,
                        (float) ArrowHitConfig.VOLUME.get().doubleValue(),
                        (float) ArrowHitConfig.PITCH.get().doubleValue()
                );

            } NewProyectile = false; return;
        }

        if (!(event.getRayTraceResult() instanceof EntityHitResult Successful_Hit)) {
            NewProyectile = false; return;
        } Entity Mob = Successful_Hit.getEntity();

        boolean ValidHitToConfirm;
        if (Mob instanceof ServerPlayer) {
            ValidHitToConfirm = ArrowHitConfig.PLAYER_ENABLED.get();
        } else if (Mob instanceof Enemy) {
            ValidHitToConfirm = ArrowHitConfig.ENEMY_ENABLED.get();
        } else if (Mob instanceof NeutralMob) {
            ValidHitToConfirm = ArrowHitConfig.NEUTRAL_ENABLED.get();
        } else {
            ValidHitToConfirm = ArrowHitConfig.PACIFIC_ENABLED.get();
        } if (!ValidHitToConfirm) { NewProyectile = false; return; }

        player.playNotifySound(
                ArrowHitConfig.SOUND.get().getSound(),
                SoundSource.PLAYERS,
                (float) ArrowHitConfig.VOLUME.get().doubleValue(),
                (float) ArrowHitConfig.PITCH.get().doubleValue()
        ); NewProyectile = false;
    }
}
