package com.mrmx.Barlious_Dream.item.custom.totems;

import com.mrmx.Barlious_Dream.event.TotemPoppedGlobalNotify;
import com.mrmx.Barlious_Dream.network.TotemAnimationPacket;
import com.mrmx.Barlious_Dream.registry.Barlious_Dream;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.EffectCures;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.network.PacketDistributor;

@EventBusSubscriber(modid = Barlious_Dream.MODID)
public class OmnipresenceTotem extends Item {

    public OmnipresenceTotem(Properties properties) {
        super(properties);
    }

    @SubscribeEvent
    public static void onLivingDeath(LivingDeathEvent event) {
        LivingEntity entity = event.getEntity();
        if (entity.level().isClientSide()) { return; }
        if (event.getSource().is(DamageTypeTags.BYPASSES_INVULNERABILITY)) { return; }

        ItemStack totemStack = ItemStack.EMPTY;
        int foundSlot = -1;
        int IsTotemOnInventory = 0;

        for (InteractionHand hand : InteractionHand.values()) {
            ItemStack stack = entity.getItemInHand(hand);
            if (stack.getItem() instanceof OmnipresenceTotem) {
                totemStack = stack;
                break;
            }
        }

        if (totemStack == ItemStack.EMPTY && entity instanceof ServerPlayer serverPlayer) {
            for (int i = 0; i < serverPlayer.getInventory().getContainerSize(); i++) {
                ItemStack stack = serverPlayer.getInventory().getItem(i);
                if (stack.getItem() instanceof OmnipresenceTotem) {
                    totemStack = stack;
                    foundSlot = i;
                    IsTotemOnInventory = 1;
                    break;
                }
            }
        }  if (totemStack.isEmpty()) { return; }

        event.setCanceled(true);
        entity.setHealth(1.0F);
        entity.removeEffectsCuredBy(EffectCures.PROTECTED_BY_TOTEM);
        entity.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 900, 1));
        entity.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 100, 1));
        entity.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 800, 0));

        if (entity instanceof ServerPlayer serverPlayer && IsTotemOnInventory == 1) {
            TotemPoppedGlobalNotify.triggerPopMessage(serverPlayer, totemStack);
            PacketDistributor.sendToPlayer(serverPlayer, new TotemAnimationPacket(totemStack.copy()));
            serverPlayer.getInventory().removeItem(foundSlot, 1);
            serverPlayer.awardStat(Stats.ITEM_USED.get(Items.TOTEM_OF_UNDYING), 1);
            CriteriaTriggers.USED_TOTEM.trigger(serverPlayer, totemStack);

        } if (IsTotemOnInventory == 0) {
            if (entity instanceof ServerPlayer serverPlayer) {
                TotemPoppedGlobalNotify.triggerPopMessage(serverPlayer, totemStack);
                PacketDistributor.sendToPlayer(serverPlayer, new TotemAnimationPacket(totemStack.copy()));
                serverPlayer.awardStat(Stats.ITEM_USED.get(Items.TOTEM_OF_UNDYING), 1);
                CriteriaTriggers.USED_TOTEM.trigger(serverPlayer, totemStack);
            } totemStack.shrink(1);
        }

        entity.level().playSound(
                null,
                entity.blockPosition(),
                SoundEvents.TOTEM_USE,
                SoundSource.PLAYERS,
                1.0F,
                1.0F
        );

        if (entity.level() instanceof ServerLevel serverLevel) {
            serverLevel.sendParticles(
                    ParticleTypes.TOTEM_OF_UNDYING,
                    entity.getX(), entity.getY() + 1.0, entity.getZ(),
                    150, 0.8, 1.0, 0.8, 0.05
            );
        }
    }
}