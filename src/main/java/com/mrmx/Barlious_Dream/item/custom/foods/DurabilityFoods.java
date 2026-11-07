package com.mrmx.Barlious_Dream.item.custom.foods;

import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

// This Class use code adapted from "Crops ´n´ Corpses" mod, by KaiCoyote and Itskillerluc
// used under the MIT License.

public class DurabilityFoods extends Item {
    private final Supplier<? extends ItemLike> remnantItem;

    public DurabilityFoods(Properties properties, Supplier<? extends ItemLike> remnantItem) {
        super(properties);
        this.remnantItem = remnantItem;
    } public DurabilityFoods(Properties properties) {
        super(properties);
        this.remnantItem = null;
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        FoodProperties foodproperties = itemstack.get(DataComponents.FOOD);

        if (foodproperties != null) {
            if (!player.getAbilities().instabuild) {
                boolean hasNourishment = player.getActiveEffects().stream().anyMatch(effect ->
                        effect.getEffect().unwrapKey().map(key -> key.location().getPath().equals("nourishment")).orElse(false)
                );

                if (!foodproperties.canAlwaysEat() && !player.getFoodData().needsFood()) {
                    if (!hasNourishment) {
                        return InteractionResultHolder.fail(itemstack);
                    }
                }
            }
            player.startUsingItem(hand);
            return InteractionResultHolder.consume(itemstack);
        }
        return super.use(level, player, hand);
    }

    @Override
    public @NotNull ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {
        ItemStack copyStack = stack.copy();
        EquipmentSlot slot = livingEntity.getEquipmentSlotForItem(stack);
        ItemStack result = super.finishUsingItem(stack, level, livingEntity);

        if (this.remnantItem != null && this.remnantItem.get() != null) {
            if (copyStack.isDamageableItem()) {
                return copyStack.hurtAndConvertOnBreak(1, this.remnantItem.get().asItem(), livingEntity, slot);
            } if (livingEntity instanceof Player player) {
                return ItemUtils.createFilledResult(stack, player, new ItemStack(this.remnantItem.get()));
            } else {
                return new ItemStack(this.remnantItem.get());
            }
        }

        if (copyStack.isDamageableItem()) {
            return copyStack.hurtAndConvertOnBreak(1, net.minecraft.world.item.Items.AIR, livingEntity, slot);
        } return result;
    }

    public UseAnim getUseAnimation(ItemStack stack) { return UseAnim.EAT; }
    public SoundEvent getEatingSound() { return super.getEatingSound(); }
    public SoundEvent getBreakingSound() { return SoundEvents.PLAYER_BURP; }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
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