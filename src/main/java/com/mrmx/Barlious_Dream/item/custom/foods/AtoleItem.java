package com.mrmx.Barlious_Dream.item.custom.foods;

import com.mrmx.Barlious_Dream.item.ModItems;
import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.level.Level;

import java.util.List;
import java.util.Objects;

// This Class use code adapted from "Crops ´n´ Corpses" mod, by KaiCoyote and Itskillerluc
// used under the MIT License.

public class AtoleItem extends DurabilityFoods {
    public AtoleItem(Properties properties) { super(properties, ModItems.ARTISANAL_CUP); }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {
        ItemStack result = super.finishUsingItem(stack, level, livingEntity);
        if (!level.isClientSide() && livingEntity instanceof Player player) {
            player.getCooldowns().addCooldown(this, 50);
        }

        return result;
    }

    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.DRINK;
    }
    public SoundEvent getDrinkingSound() {
        return super.getDrinkingSound();
    }
    public SoundEvent getBreakingSound() { return SoundEvents.PLAYER_BURP; }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable("tooltip.mrmx_dream.atole").withStyle(ChatFormatting.LIGHT_PURPLE));
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}