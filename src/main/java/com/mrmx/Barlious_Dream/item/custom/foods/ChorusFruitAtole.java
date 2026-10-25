package com.mrmx.Barlious_Dream.item.custom.foods;

import com.mrmx.Barlious_Dream.item.ModItems;
import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.level.Level;

import java.util.List;
import java.util.Objects;

public class ChorusFruitAtole extends ChorusFruitItem {

    public ChorusFruitAtole(Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {
        ItemStack copyStack = stack.copy();
        EquipmentSlot slot = livingEntity.getEquipmentSlotForItem(stack);
        ItemStack result = super.finishUsingItem(stack, level, livingEntity);
        if (!level.isClientSide() && livingEntity instanceof Player player) {
            player.getCooldowns().addCooldown(this, 100);
        }

        if (copyStack.isDamageableItem()) {
            return copyStack.hurtAndConvertOnBreak(1, ModItems.ARTISANAL_CUP.get(), livingEntity, slot);
        } if (livingEntity instanceof Player player) {
            return ItemUtils.createFilledResult(stack, player, new ItemStack(ModItems.ARTISANAL_CUP.get()));
        } else {
            return new ItemStack(ModItems.ARTISANAL_CUP.get());
        }
    }

    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.DRINK;
    }
    public SoundEvent getDrinkingSound() {
        return super.getDrinkingSound();
    }
    public SoundEvent getBreakingSound() {
        return SoundEvents.PLAYER_BURP;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable("tooltip.mrmx_dream.atole").withStyle(ChatFormatting.LIGHT_PURPLE));
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
        FoodProperties food = stack.get(DataComponents.FOOD);
        if (food == null || food.effects().isEmpty()) {
            return;
        }
        PotionContents visualPotionContents = PotionContents.EMPTY;
        for (FoodProperties.PossibleEffect possibleEffect : food.effects()) {
            visualPotionContents = visualPotionContents.withEffectAdded(possibleEffect.effect());
        }
        Objects.requireNonNull(tooltipComponents);
        visualPotionContents.addPotionTooltip(tooltipComponents::add, 1.0F, context.tickRate());
    }
}
