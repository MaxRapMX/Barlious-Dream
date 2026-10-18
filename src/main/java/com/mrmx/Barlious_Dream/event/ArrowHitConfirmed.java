package com.mrmx.Barlious_Dream.event;

import com.mrmx.Barlious_Dream.config.ArrowHitConfig;
import com.mrmx.Barlious_Dream.config.ModulesEnabler;
import com.mrmx.Barlious_Dream.registry.Barlious_Dream;
import com.mrmx.Barlious_Dream.sound.ModSounds;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.NeutralMob;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.ThrownTrident;
import net.minecraft.world.level.block.TargetBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.ProjectileImpactEvent;

@EventBusSubscriber(modid = Barlious_Dream.MODID)
public class ArrowHitConfirmed {

    @SubscribeEvent
    public static void onProjectileImpact(ProjectileImpactEvent event) {
        if (!ModulesEnabler.ARROW_HIT_ENABLED.get()) { return; }
        if (!(event.getEntity()instanceof AbstractArrow arrow && !(event.getEntity()instanceof ThrownTrident))) { return; }
        if (arrow.level().isClientSide()) { return; }

        if (!(arrow.getOwner() instanceof ServerPlayer player)) { return; }
        if (event.getRayTraceResult() instanceof BlockHitResult blockHit) {

            if (arrow.getTags().contains("hit_marker_played")) { return; }
            BlockState state = arrow.level().getBlockState(blockHit.getBlockPos());
            if (state.getBlock() instanceof TargetBlock) {
                player.playNotifySound(
                        ArrowHitConfig.SOUND.get().getSound(),
                        SoundSource.PLAYERS,
                        ArrowHitConfig.VOLUME.get().floatValue(),
                        ArrowHitConfig.PITCH.get().floatValue()
                ); arrow.addTag("hit_marker_played"); return;
            }

            if (ArrowHitConfig.SHAME_ENABLED.get()) {
                player.playNotifySound(
                        ModSounds.SPONGEBOB_DISAPPOINTED.get(),
                        SoundSource.PLAYERS,
                        ArrowHitConfig.VOLUME.get().floatValue(),
                        ArrowHitConfig.PITCH.get().floatValue()
                ); arrow.addTag("hit_marker_played");

            } return;
        }

        if (!(event.getRayTraceResult() instanceof EntityHitResult Successful_Hit)) { return; }
        if (arrow.getTags().contains("hit_marker_played")) { return; }
        Entity Mob = Successful_Hit.getEntity();
        boolean ValidHitToConfirm = false;
        if (Mob instanceof Player) {
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
                ArrowHitConfig.VOLUME.get().floatValue(),
                ArrowHitConfig.PITCH.get().floatValue()
        ); arrow.addTag("hit_marker_played");
    }
}