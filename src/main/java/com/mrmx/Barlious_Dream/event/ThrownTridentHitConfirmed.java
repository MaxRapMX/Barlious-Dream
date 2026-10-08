package com.mrmx.Barlious_Dream.event;

import com.mrmx.Barlious_Dream.config.ArrowHitConfig;
import com.mrmx.Barlious_Dream.config.ThrownTridentHitConfig;
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
public class ThrownTridentHitConfirmed {

    @SubscribeEvent
    public static void onProjectileImpact(ProjectileImpactEvent event) {
        if (!ThrownTridentHitConfig.ENABLED.get()) { return; }
        if (!(event.getEntity()instanceof ThrownTrident trident)) { return; }
        if (trident.level().isClientSide()) { return; }

        if (!(trident.getOwner() instanceof ServerPlayer player)) { return; }
        if (event.getRayTraceResult() instanceof BlockHitResult blockHit) {

            if (trident.getTags().contains("hit_marker_played")) { return; }
            BlockState state = trident.level().getBlockState(blockHit.getBlockPos());
            if (state.getBlock() instanceof TargetBlock) {
                player.playNotifySound(
                        ThrownTridentHitConfig.SOUND.get().getSound(),
                        SoundSource.PLAYERS,
                        ThrownTridentHitConfig.VOLUME.get().floatValue(),
                        ThrownTridentHitConfig.PITCH.get().floatValue()
                ); trident.addTag("hit_marker_played"); return;
            }

            if (ThrownTridentHitConfig.SHAME_ENABLED.get()) {
                player.playNotifySound(
                        ModSounds.SPONGEBOB_DISAPPOINTED.get(),
                        SoundSource.PLAYERS,
                        ThrownTridentHitConfig.VOLUME.get().floatValue(),
                        ThrownTridentHitConfig.PITCH.get().floatValue()
                ); trident.addTag("hit_marker_played");

            } return;
        }

        if (!(event.getRayTraceResult() instanceof EntityHitResult Successful_Hit)) { return; }
        if (trident.getTags().contains("hit_marker_played")) { return; }
        Entity Mob = Successful_Hit.getEntity();
        boolean ValidHitToConfirm = false;
        if (Mob instanceof Player) {
            ValidHitToConfirm = ThrownTridentHitConfig.PLAYER_ENABLED.get();
        } else if (Mob instanceof Enemy) {
            ValidHitToConfirm = ThrownTridentHitConfig.ENEMY_ENABLED.get();
        } else if (Mob instanceof NeutralMob) {
            ValidHitToConfirm = ThrownTridentHitConfig.NEUTRAL_ENABLED.get();
        } else {
            ValidHitToConfirm = ThrownTridentHitConfig.PACIFIC_ENABLED.get();
        } if (!ValidHitToConfirm) { return; }

        player.playNotifySound(
                ThrownTridentHitConfig.SOUND.get().getSound(),
                SoundSource.PLAYERS,
                ThrownTridentHitConfig.VOLUME.get().floatValue(),
                ThrownTridentHitConfig.PITCH.get().floatValue()
        ); trident.addTag("hit_marker_played");
    }
}