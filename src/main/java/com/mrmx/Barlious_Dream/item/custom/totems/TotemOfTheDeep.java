package com.mrmx.Barlious_Dream.item.custom.totems;

import com.mrmx.Barlious_Dream.event.TotemPoppedGlobalNotify;
import com.mrmx.Barlious_Dream.network.TotemAnimationPacket;
import com.mrmx.Barlious_Dream.registry.Barlious_Dream;
import net.minecraft.ChatFormatting;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
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
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.EffectCures;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.neoforge.network.PacketDistributor;

import java.util.List;

@EventBusSubscriber(modid = Barlious_Dream.MODID)
public class TotemOfTheDeep extends Item {

    public TotemOfTheDeep(Properties properties) {
        super(properties);
    }
    private static boolean WasInWater = false;
    private static boolean StillInWater = false;
    private static boolean HasInitialNightVision = false;
    private static final int DelayStartToNightVision = 59;
    private static final int DelayStillNightVision = 49;
    private static int DelayAccountToNightVision = -1; // All the delay are Based in Tick

    @SubscribeEvent
    public static void onLivingDeath(LivingDeathEvent event) {
        LivingEntity entity = event.getEntity();
        if (entity.level().isClientSide()) { return; }
        if (event.getSource().is(DamageTypeTags.BYPASSES_INVULNERABILITY)) { return; }

        ItemStack totemStack = ItemStack.EMPTY;

        for (InteractionHand hand : InteractionHand.values()) {
            ItemStack stack = entity.getItemInHand(hand);
            if (stack.getItem() instanceof TotemOfTheDeep) {
                totemStack = stack;
                break;
            }
        } if (totemStack.isEmpty()) { return; }

        event.setCanceled(true);
        entity.setHealth(1.0F);
        // entity.removeAllEffects();
        entity.removeEffectsCuredBy(EffectCures.PROTECTED_BY_TOTEM);
        entity.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 900, 1));
        entity.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 100, 1));

        boolean diedInWater = event.getSource().is(DamageTypeTags.IS_DROWNING) || entity.isInWater();
        if (diedInWater) {
            entity.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 1200, 0));
            entity.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 1200, 0));
            entity.addEffect(new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 300, 1));
        }

        if (entity instanceof ServerPlayer serverPlayer) {
            TotemPoppedGlobalNotify.triggerPopMessage(serverPlayer, totemStack);
            PacketDistributor.sendToPlayer(serverPlayer, new TotemAnimationPacket(totemStack.copy()));
            serverPlayer.awardStat(Stats.ITEM_USED.get(Items.TOTEM_OF_UNDYING), 1);
            CriteriaTriggers.USED_TOTEM.trigger(serverPlayer, totemStack);
        } totemStack.shrink(1);

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
                    150, 0.8, 1.0, 0.8, 0.25
            );
        }
    }

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {

        Player player = event.getEntity();
        if (player.level().isClientSide()) {
            return;
        }

        boolean isInWater = player.isInWater();
        ItemStack offhandItem = player.getOffhandItem();
        if (isInWater && (offhandItem.getItem() instanceof TotemOfTheDeep)) {
            if (DelayAccountToNightVision == -1) DelayAccountToNightVision = DelayStartToNightVision;
            WasInWater = true;
            player.addEffect(new MobEffectInstance(
                    MobEffects.DOLPHINS_GRACE,
                    2, 0, true, false, false
            ));
        } else WasInWater = false;

        if (StillInWater && DelayAccountToNightVision == 0) {
            if (HasInitialNightVision){
                player.addEffect(new MobEffectInstance(
                        MobEffects.NIGHT_VISION,
                        210, 0, true, false, false
                ));
            }
            if (!player.hasEffect(MobEffects.NIGHT_VISION) && !HasInitialNightVision){
                player.addEffect(new MobEffectInstance(
                        MobEffects.NIGHT_VISION,
                        210, 0, true, false, false
                ));
                HasInitialNightVision = true;
            }
        }
        if (!StillInWater) {
            if (DelayAccountToNightVision > 0) {
                DelayAccountToNightVision--;
                if (DelayAccountToNightVision == 0) {
                    if (isInWater && (offhandItem.getItem() instanceof TotemOfTheDeep) && WasInWater) {
                        StillInWater = true;
                        player.addEffect(new MobEffectInstance(
                                MobEffects.NIGHT_VISION,
                                40, 1, true, true, true
                        ));
                    }
                }
            }
        }
        if (!isInWater || !(offhandItem.getItem() instanceof TotemOfTheDeep)) {
            if (DelayAccountToNightVision == 0) DelayAccountToNightVision = DelayStillNightVision;
        } if (DelayAccountToNightVision > 0) {
            DelayAccountToNightVision--;
            if (DelayAccountToNightVision == 0 && (!isInWater || !(offhandItem.getItem() instanceof TotemOfTheDeep))) {
                    StillInWater = false;
                    HasInitialNightVision = false;
                    DelayAccountToNightVision = -1;
                    player.removeEffect(MobEffects.NIGHT_VISION);
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable("tooltip.mrmx_dream.undying.totem").withStyle(ChatFormatting.GREEN)
                .append(Component.literal(".").withStyle(ChatFormatting.GREEN))
                .append(Component.translatable("tooltip.mrmx_dream.undying.totem.deep").withStyle(ChatFormatting.AQUA)));
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}